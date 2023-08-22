package model.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import model.exceptions.DbException;

public class DatabaseConnection {
    
    private static Connection dbConnection;
    private static Statement sqlStatement;
    private static ResultSet sqlResultSet;
    private static Properties dbProperties;
    private static PreparedStatement sqlPreparedStatement;

    public DatabaseConnection (){

    }


    public static Properties getProperties() {

        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            dbProperties.load(fileInputStream);            
        } catch (IOException e) {            
            throw new DbException("Configuration file initialization error. Messaege: " + e.getMessage());
        }        
        return dbProperties;
    }


    public static Connection getConnection() {
        try {
            String dbUrl = dbProperties.getProperty("dburl");
            dbConnection=DriverManager.getConnection(dbUrl, dbProperties);
            return dbConnection;
        } catch (SQLException e) {
            throw new DbException("Database connection error. Message: " + e.getMessage());
        }
    }


    public void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            throw new DbException("Database close connection error. Message: " + e.getMessage());
        }
    }


    public static void closeSqlResultSet() {
        try {
            sqlResultSet.close();
        } catch (SQLException e) {
            throw new DbException("Close resultset error. Message " + e.getMessage());
        }
    }

    public static void closeSqlStatement() {
        try {
            sqlStatement.close();
        } catch (SQLException e) {
            throw new DbException("Close statement error. Message " + e.getMessage());
        }
    }

    public static void closeSqlPreparedStatement() {
        try {
            sqlPreparedStatement.close();
        } catch (SQLException e) {
            throw new DbException("Close prepared statement error. Message " + e.getMessage());
        }
    }


}
