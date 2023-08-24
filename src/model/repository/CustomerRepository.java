package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Customer;
import model.entities.DTO.CustomerDTO;
import model.exceptions.DbException;

public class CustomerRepository {

    private Statement sqlStatement = null;
    private PreparedStatement sqlPreparedStatement = null;
    private ResultSet sqlResultSet;
    private static Connection dbConnection = null;
    

    public CustomerRepository () {        
        try {
            dbConnection = DatabaseConnection.getConnection();
            sqlStatement = dbConnection.createStatement();
        } catch (SQLException e) {
            throw new DbException("Connection error: " + e.getMessage());
        }
    }

    public Customer findById(int id) {
        try {
            StringBuilder sqlTextStringBuilder = new StringBuilder("SELECT * FROM Customers ");
            sqlTextStringBuilder.append("WHERE id = ");
            sqlTextStringBuilder.append(id);

            sqlResultSet = sqlStatement.executeQuery(sqlTextStringBuilder.toString());
            sqlResultSet.next();
            CustomerDTO customerDTO = new CustomerDTO();

            customerDTO.withIdInteger(sqlResultSet.getInt("id")).
                    withNameString(sqlResultSet.getString("name")).
                    withAddressString(sqlResultSet.getString("address")).
                    withMarketSegmentString(sqlResultSet.getString("marketSegment")).
                    withZipCodeInteger(sqlResultSet.getInt("zipcode")).
                    withIsActiveInteger(sqlResultSet.getInt("isActive")).
                    withCreatedByString(sqlResultSet.getString("createdBy")).
                    withCreatedDate(sqlResultSet.getDate("createdDate")).
                    withModifiedByString(sqlResultSet.getString("modifiedBy")).
                    withModifiedDate(sqlResultSet.getDate("modifiedDate"));

            Customer customer = new Customer(customerDTO.idInteger, customerDTO.nameString,
            customerDTO.addressString, customerDTO.marketSegmentString, customerDTO.zipCodeInteger, customerDTO.isActiveInteger,
            customerDTO.createdByString, customerDTO.createdDate, customerDTO.modifiedByString, customerDTO.modifiedDate);

            return customer;
        } catch (SQLException e) {
            throw new DbException("SQL statement execution error. Message: " + e.getMessage());
        } finally {
            DatabaseConnection.closeSqlStatement(sqlStatement);
        }        
    }

    public List<Customer> findAll() {

        try {
            StringBuilder sqlTextStringBuilder = new StringBuilder("SELECT * FROM Customers ");
            
            sqlStatement = dbConnection.createStatement();
            sqlResultSet = sqlStatement.executeQuery(sqlTextStringBuilder.toString());
            List<Customer> customerList = new ArrayList<>();

            while (sqlResultSet.next()) {

                CustomerDTO customerDTO = new CustomerDTO();

                customerDTO.withIdInteger(sqlResultSet.getInt("id")).
                        withNameString(sqlResultSet.getString("name")).
                        withAddressString(sqlResultSet.getString("address")).
                        withMarketSegmentString(sqlResultSet.getString("marketSegment")).
                        withZipCodeInteger(sqlResultSet.getInt("zipcode")).
                        withIsActiveInteger(sqlResultSet.getInt("isActive")).
                        withCreatedByString(sqlResultSet.getString("createdBy")).
                        withCreatedDate(sqlResultSet.getDate("createdDate")).
                        withModifiedByString(sqlResultSet.getString("modifiedBy")).
                        withModifiedDate(sqlResultSet.getDate("modifiedDate"));

                Customer customer = new Customer(customerDTO.idInteger, customerDTO.nameString,
                customerDTO.addressString, customerDTO.marketSegmentString, customerDTO.zipCodeInteger, customerDTO.isActiveInteger,
                customerDTO.createdByString, customerDTO.createdDate, customerDTO.modifiedByString, customerDTO.modifiedDate);
                                        
                customerList.add(customer);
            }
            return customerList;
        } catch (SQLException e) {
            throw new DbException("SQL statement execution error. Message: " + e.getMessage());
        } finally {
            DatabaseConnection.closeSqlResultSet(sqlResultSet);
        }
    }

    public void save(CustomerDTO customerDTO) {       
          try {
            sqlPreparedStatement = dbConnection.prepareStatement(
                "INSERT INTO CUSTOMERS "
                + "(name, address, marketSegment, zipcode, isActive, createdBy, createdDate) "
                + "VALUES "
                + "(?,?,?,?,?,?,?);"
                );
            sqlPreparedStatement.setString(1,customerDTO.nameString);
            sqlPreparedStatement.setString(2, customerDTO.addressString);
            sqlPreparedStatement.setString(3, customerDTO.marketSegmentString);
            sqlPreparedStatement.setInt(4, customerDTO.zipCodeInteger);            
            sqlPreparedStatement.setInt(5, customerDTO.isActiveInteger);
            sqlPreparedStatement.setString(6, customerDTO.createdByString);
            sqlPreparedStatement.setDate(7, customerDTO.createdDate);        
    
            sqlPreparedStatement.executeUpdate();  
        } catch (SQLException e) {
            throw new DbException("Insert error. MEssages: " + e.getMessage());
        } finally {
            DatabaseConnection.closeSqlPreparedStatement(sqlPreparedStatement);
        } 
    }

    public void deleteById(int id) {
        try {
            sqlPreparedStatement = dbConnection.prepareStatement("DELETE FROM Customers WHERE id = ?");
            sqlPreparedStatement.setInt(1, id);
            sqlPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("DeleteById function execution error. Message: " + e.getMessage());
        } finally {
            DatabaseConnection.closeSqlPreparedStatement(sqlPreparedStatement);
        }        
    }    

    public void updateById(int id, CustomerDTO customerDTO) {
          try {
            sqlPreparedStatement = dbConnection.prepareStatement(
                 "UPDATE CUSTOMERS "
                +"set name = ? ,"
                +"address = ? , "
                +"marketSegment = ? , "
                +"zipcode = ? , "
                +"isActive = ? , "
                +"modifiedBy = ? , " 
                +"modifiedDate = ? "
                + "WHERE  "
                + "id = ?;"
                );
            sqlPreparedStatement.setString(1,customerDTO.nameString);
            sqlPreparedStatement.setString(2, customerDTO.addressString);
            sqlPreparedStatement.setString(3, customerDTO.marketSegmentString);
            sqlPreparedStatement.setInt(4, customerDTO.zipCodeInteger);            
            sqlPreparedStatement.setInt(5, customerDTO.isActiveInteger);
            sqlPreparedStatement.setString(6, customerDTO.modifiedByString);
            sqlPreparedStatement.setDate(7, customerDTO.modifiedDate);    
            sqlPreparedStatement.setInt(8, id); 
    
            sqlPreparedStatement.executeUpdate();  
        } catch (SQLException e) {
            throw new DbException("Update error. MEssages: " + e.getMessage());
        } finally {
            DatabaseConnection.closeSqlPreparedStatement(sqlPreparedStatement);
        }       
    }   



}
