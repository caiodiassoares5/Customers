package application;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import model.entities.DTO.CustomerDTO;
import model.exceptions.DbException;
import model.repository.CustomerRepository;
import model.repository.DatabaseConnection;

public class Main {
    
    public static void main(String args) {

        Scanner scanner = new Scanner(System.in);
        DatabaseConnection dbConnection = new DatabaseConnection();

        try {
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
                System.out.print("Name:");
                String name = scanner.nextLine();
                System.out.print("Address:");
                String address = scanner.nextLine();
                System.out.print("Segment:");
                String segment = scanner.nextLine();
        
                System.out.print("Zip:");
                int zipcode = scanner.nextInt();
                scanner.nextLine();
        
                System.out.print("isActive:");
                int isActive = scanner.nextInt();
                scanner.nextLine();
        
                System.out.print("createdBy:");
                String createdBy = scanner.nextLine();
        
                System.out.print("createdDate:");
                String createdDate = scanner.nextLine();
        
                CustomerDTO customerDTO = new CustomerDTO();
        
                customerDTO.withNameString(name).
                withAddressString(address).
                withMarketSegmentString(segment).
                withZipCodeInteger(zipcode).
                withIsActiveInteger(isActive).
                withCreatedByString(createdBy).
                withCreatedDate(new java.sql.Date(sdf.parse(createdDate).getTime()));

                CustomerRepository.save(customerDTO);

        } catch (Exception e) {
            throw new DbException("Runtime error. MEssages: " + e.getMessage());
        }
        finally{
            scanner.close();
            dbConnection.closeConnection();
        }

    }


}
