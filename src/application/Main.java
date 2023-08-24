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
        int selectedMenuOption;

        try {                 
            System.out.println("*****CUSTOMER APP*******");
            System.out.println("Please select one of the following options: ");
            System.out.println("1: Create a new customer." 
                                + "\n2: Find an existing customer."
                                + "\n3: Find all customers."
                                + "\n4: Remove an existing customer."
                                + "\n5: Modify an existing customer."
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
                case 4:
                    deleteCustomerById();
                    break;
                case 5:
                    modifyCustomerById();
                    break;
            }

        } catch (Exception e) {
            throw new DbException("Runtime error in Main app. MEssages: " + e.getMessage());
        }
        finally{
            scanner.close();
            DatabaseConnection.closeConnection();
            }            
        }
    

    public static void createCustomer() {
        Scanner scanner = new Scanner(System.in);        
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

                System.out.println("User created successfully.");

        } catch (Exception e) {
            throw new DbException("Error in create user function. MEssages: " + e.getMessage());
        }
        finally{
            scanner.close();            
        }
    }


    public static void retrieveCustomerById() {                
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        CustomerRepository customerRepository = new CustomerRepository();
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
        }         
    }


    public static void getAllCustomers() {        
        CustomerRepository customerRepository = new CustomerRepository();        
        List<Customer> customerList = new ArrayList<>();
        try {                
            customerList = customerRepository.findAll();             
            customerList.stream().forEach(System.out::println); 
        }  catch (Exception e) {
            throw new DbException("Error in retrieveAllCustomers function. MEssages: " + e.getMessage());
        }
    }


    public static void deleteCustomerById() {                
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        CustomerRepository customerRepository = new CustomerRepository(); 
        try {
                System.out.println("Please insert a customer Id to delete: ");
                int idForDelete = scanner.nextInt();
                scanner.nextLine();

                customer = customerRepository.findById(idForDelete);

                System.out.println(customer);  

                System.out.println("Customer will be deleted. Are you sure? (y/n)");
                char isSureForDelete = scanner.next().charAt(0);
                scanner.nextLine();
                
                if (isSureForDelete == 'y') {
                    customerRepository.deleteById(idForDelete);
                    System.out.println("User removed successfully");
                }
        }  catch (Exception e) {
            throw new DbException("Error in deleteCustomerById function. MEssages: " + e.getMessage());
        }
        finally{
            scanner.close();            
        }         
    }

    public static void modifyCustomerById() {                
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        CustomerRepository customerRepository = new CustomerRepository();
        try {
                System.out.println("Please insert a customer Id to modify: ");
                int idForModify = scanner.nextInt();
                scanner.nextLine();

                customer = customerRepository.findById(idForModify);

                System.out.println("\nCurrent data for customer: ");
                System.out.println(customer);  
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
                System.out.println("\nPlease enter new data: ");
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
        
                System.out.print("modifiedBy:");
                String modifiedBy = scanner.nextLine();
        
                System.out.print("modifiedDate:");
                String modifiedDate = scanner.nextLine();
        
                customer.setNameString(name).
                setAddressString(address).
                setZipCodeInteger(zipcode).
                setIsActiveChar(isActive).
                setMarketSegmentString(segment).
                setModifiedByString(modifiedBy).
                setModifiedDate(new java.sql.Date(sdf.parse(modifiedDate).getTime()));

                CustomerDTO customerDTO = new CustomerDTO();
        
                customerDTO.withNameString(name).
                withAddressString(address).
                withMarketSegmentString(segment).
                withZipCodeInteger(zipcode).
                withIsActiveInteger(isActive).
                withModifiedByString(modifiedBy).
                withCreatedDate(new java.sql.Date(sdf.parse(modifiedDate).getTime()));

                System.out.println("New data for customer: ");
                System.out.println(customer);  

                System.out.println("Are you sure? (y/n)");
                char isSureForUpdate = scanner.next().charAt(0);
                
                if (isSureForUpdate == 'y') {
                    customerRepository.updateById(idForModify, customerDTO);
                    System.out.println("Change was successfull.");
                }
        }  catch (Exception e) {
            throw new DbException("Error in modifyCustomerById function. MEssages: " + e.getMessage());
        }
        finally{
            scanner.close();            
        }         
    }
}
