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
 

    public DatabaseConnection (){

    }


    public static Properties getProperties() {

        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            Properties dbProperties = new Properties();
            dbProperties.load(fileInputStream);  
            return dbProperties;          
        } catch (IOException e) {            
            throw new DbException("Configuration file initialization error. Messaege: " + e.getMessage());
        }                
    }


    public Connection getConnection() {
        try {
            Properties prop = getProperties();
            String dbUrl = prop.getProperty("dburl");
            String dbUser = prop.getProperty("user");
            String dbPassword = prop.getProperty("password");
            dbConnection=DriverManager.getConnection(dbUrl, dbUser, dbPassword);
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


    public static void closeSqlResultSet(ResultSet sqlResultSet) {
        try {
            sqlResultSet.close();
        } catch (SQLException e) {
            throw new DbException("Close resultset error. Message " + e.getMessage());
        }
    }

    public static void closeSqlStatement(Statement sqlStatement) {
        try {
            sqlStatement.close();
        } catch (SQLException e) {
            throw new DbException("Close statement error. Message " + e.getMessage());
        }
    }

    public static void closeSqlPreparedStatement(PreparedStatement sqlPreparedStatement) {
        try {
            sqlPreparedStatement.close();
        } catch (SQLException e) {
            throw new DbException("Close prepared statement error. Message " + e.getMessage());
        }
    }
}
