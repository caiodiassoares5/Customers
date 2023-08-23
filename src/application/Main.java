package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.entities.Customer;
import model.entities.DTO.CustomerDTO;
import model.exceptions.DbException;
import model.repository.CustomerRepository;
import model.repository.DatabaseConnection;

public class Main {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DatabaseConnection dbConnection = new DatabaseConnection();     
        int selectedMenuOption;


        try {     
            
            System.out.println("*****CUSTOMER APP*******");
            System.out.println("Please select one of the following options: ");
            System.out.println("1: Create a new Customer." 
                                + "\n2: Find a specific customer."
                                + "\n3: Find all customers."
            );
            
            selectedMenuOption = scanner.nextInt();
            scanner.nextLine();

            switch (selectedMenuOption) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    retrieveCustomerById(); 
                    break;
                case 3:
                    getAllCustomers();
                    break;
            }

        } catch (Exception e) {
            throw new DbException("Runtime error in Main app. MEssages: " + e.getMessage());
        }
        finally{
            scanner.close();
            dbConnection.closeConnection();
        }
    }

    public static void createCustomer() {

        Scanner scanner = new Scanner(System.in);
        DatabaseConnection dbConnection = new DatabaseConnection();

        try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                CustomerRepository customerRepository = new CustomerRepository();
        
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

                customerRepository.save(customerDTO);

                System.out.println("Informações inseridas com êxito.");

        } catch (Exception e) {
            throw new DbException("Error in create user function. MEssages: " + e.getMessage());
        }
        finally{
            scanner.close();
            dbConnection.closeConnection();
        }
    }


    public static void retrieveCustomerById() {
                
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        CustomerRepository customerRepository = new CustomerRepository();
        DatabaseConnection dbConnection = new DatabaseConnection();

        try {
                System.out.println("Insira um ID para pesquisa unitária: ");
                int idForSearch = scanner.nextInt();
                scanner.nextLine();

                customer = customerRepository.findById(idForSearch);

                System.out.println(customer);  

        }  catch (Exception e) {
            throw new DbException("Error in retrieveCustomerById function. MEssages: " + e.getMessage());
        }
        finally{
            scanner.close();
            dbConnection.closeConnection();
        }
         
    }


    public static void getAllCustomers() {
        
        CustomerRepository customerRepository = new CustomerRepository();
        DatabaseConnection dbConnection = new DatabaseConnection();
        List<Customer> customerList = new ArrayList<>();

        try {                
            customerList = customerRepository.findAll();             
            customerList.stream().forEach(System.out::println);            

        }  catch (Exception e) {
            throw new DbException("Error in retrieveAllCustomers function. MEssages: " + e.getMessage());
        }
        finally{            
            dbConnection.closeConnection();
        }
    }



}
