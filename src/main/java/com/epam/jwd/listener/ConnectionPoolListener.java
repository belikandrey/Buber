package com.epam.jwd.listener;

import com.epam.jwd.db.ConnectionPool;
import com.epam.jwd.util.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener("/*")
public class ConnectionPoolListener implements ServletContextListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionPoolListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PropertyReader.init();
        ConnectionPool.CONNECTION_POOL.init();
        LOGGER.info("Connection pool initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.CONNECTION_POOL.closeAll();
        } catch (SQLException e) {
            LOGGER.error(e.toString());
        }
    }
}
