package com.reserve;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DButil {
    private static Connection dbConnection = null;

    public static Connection getConnection() {
        if (dbConnection != null) {
            return dbConnection;
        }
        try {
            InputStream inputStream = DButil.class.getClassLoader()
                    .getResourceAsStream("db.properties");
            Properties properties = new Properties();
            if (properties != null) {
                properties.load(inputStream);

                String dbDriver = properties.getProperty("dbDriver");
                String connectionUrl = properties
                        .getProperty("connectionUrl");
                String userName = properties.getProperty("userName");
                String password = properties.getProperty("password");
                Class.forName(dbDriver).getDeclaredConstructor().newInstance();
                dbConnection = DriverManager.getConnection(connectionUrl,
                        userName, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}
