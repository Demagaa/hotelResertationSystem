package com.reserve;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DButil {
    public String getDbDriver() {
        return dbDriver;
    }

    public static String getConnectionUrl() {
        return connectionUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

     static String dbDriver;
     static String connectionUrl;
     static String userName;
     static String password;

    public DButil() {
        InputStream inputStream = DButil.class.getClassLoader()
                .getResourceAsStream("db.properties");
        Properties properties = new Properties();
        if (properties != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            this.dbDriver = properties.getProperty("dbDriver");
            this.connectionUrl = properties.getProperty("connectionUrl");
            this.userName = properties.getProperty("userName");
            this.password = properties.getProperty("password");
        }
    }
}
