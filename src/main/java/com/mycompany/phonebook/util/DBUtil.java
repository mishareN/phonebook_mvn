package com.mycompany.phonebook.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Michael Kupryk on 31.03.2017.
 */
public class DBUtil {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    private static final String connURL = "jdbc:mysql://localhost:3306/MySQLCon";
    private static final String user = "root";
    private static final String pass = "root";

    public static void dbConnect () throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found!");
            e.printStackTrace();
            throw e;
        }
        System.out.println("MySQL JDBC driver registered");
        try{
            conn = DriverManager.getConnection(connURL, user, pass);
        }catch (SQLException e){
            System.out.println("Connection failed!" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException, Exception{
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
            throw e;
        }
    }


}
