package com.epam.jwd.listener;

import com.epam.jwd.db.ConnectionPool;
import com.epam.jwd.util.PropertyReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener("/*")
public class ConnectionPoolListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PropertyReader.init();
        ConnectionPool.CONNECTION_POOL.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.CONNECTION_POOL.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
