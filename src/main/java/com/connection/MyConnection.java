package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
    private static final String hostName = "";
    private static final String dbName = "";
    private static final String userName = "";
    private static final String password = "";

    public static void main(String args[]) {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/data_file",
                            "postgres", "admin");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static Connection get() throws Exception{
            Class.forName("com.mysql.jdbc.Driver");
            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

            Connection conn = DriverManager.getConnection(connectionURL, userName,
                    password);
            return conn;
    }
}
