package com.mycompany.phonebook.util;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

/**
 * Created by Michael Kupryk on 31.03.2017.
 */
public class DBUtil {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    private static final String connURL = "jdbc:mysql://localhost:3306/phonebook";
    private static final String USER = "root";
    private static final String PASS = "root";

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
            conn = DriverManager.getConnection(connURL, USER, PASS);
        }catch (SQLException e){
            System.out.println("Connection failed!" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void dbDisconnect() throws Exception{
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
            throw e;
        }
    }

    public static ResultSet dbExecuteQuery (String queryStmnt) throws Exception {
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSetImpl cachedRowSet = null;
        try{
            dbConnect();
            System.out.println("Select statement: " + queryStmnt + "\n");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(queryStmnt);
            cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem with executeQuery operation: " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            dbDisconnect();
        }
        return cachedRowSet;
    }

    public static void dbExecuteUpdate (String sqlStmnt) throws Exception {
        Statement statement = null;
        try {
            dbConnect();
            statement = conn.createStatement();
            statement.executeUpdate(sqlStmnt);
        } catch (SQLException e){
            System.out.println("Problem with executeUpdate operation: " + e);
            throw e;
        }finally {
            if (statement != null) {
                statement.close();
            }
            dbDisconnect();
        }
    }

}
