package com.epam.jwd.db;

import com.epam.jwd.context.ApplicationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private final ConcurrentLinkedQueue<Connection> availableConnections = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Connection> usedConnections = new ConcurrentLinkedQueue<>();

    private final int initSize = ApplicationProperties.APPLICATION_PROPERTIES.getPullInitSize();
    private final int maxSize = ApplicationProperties.APPLICATION_PROPERTIES.getPullMaxSize();
    private final String dbUrl = ApplicationProperties.APPLICATION_PROPERTIES.getDbUrl();
    private final String login = ApplicationProperties.APPLICATION_PROPERTIES.getDbLogin();
    private final String password = ApplicationProperties.APPLICATION_PROPERTIES.getDbPassword();

    public static final ConnectionPool CONNECTION_POOL = new ConnectionPool();

    private ConnectionPool() {

    }

    public Connection getConnection() {
        Connection connection = null;
        if (availableConnections.size() + usedConnections.size() >= maxSize) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (availableConnections.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                addConnection();
            }
        }

        connection = availableConnections.poll();
        usedConnections.add(connection);

        return connection;
    }

    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < initSize; i++) {
            addConnection();
        }
    }

    private Connection addConnection() {

        Connection connection = null;
        try {
            connection = new ConnectionProxy(DriverManager.getConnection(dbUrl, login, password));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        availableConnections.add(connection);
        return connection;
    }

    public void returnConnection(Connection connection) {
        usedConnections.remove(connection);
        availableConnections.add(connection);
    }

    public void closeAll() throws SQLException {
        for (Connection connection : availableConnections) {
            ((ConnectionProxy) connection).realClose();
        }
    }
}
