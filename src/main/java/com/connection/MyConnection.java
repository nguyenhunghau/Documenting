package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
    private static final String hostName = "db4free.net";
    private static final String dbName = "documenting";
    private static final String userName = "minh123";
    private static final String password = "admin123";

    public static Connection get() throws Exception{
            Class.forName("com.mysql.jdbc.Driver");
            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

            Connection conn = DriverManager.getConnection(connectionURL, userName,
                    password);
            return conn;
    }
}
