import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the EnterNewAddress use case implementation.
 * This program simulates the process of an administrator entering a new address
 * into the archive system, with validation and error handling.
 */
public class EnterNewAddress {
    
    /**
     * Represents an address entry in the system.
     */
    static class Address {
        private String name;
        private String street;
        private String city;
        private String zipCode;
        private String country;
        
        public Address(String name, String street, String city, String zipCode, String country) {
            this.name = name;
            this.street = street;
            this.city = city;
            this.zipCode = zipCode;
            this.country = country;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getStreet() {
            return street;
        }
        
        public void setStreet(String street) {
            this.street = street;
        }
        
        public String getCity() {
            return city;
        }
        
        public void setCity(String city) {
            this.city = city;
        }
        
        public String getZipCode() {
            return zipCode;
        }
        
        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
        
        public String getCountry() {
            return country;
        }
        
        public void setCountry(String country) {
            this.country = country;
        }
        
        @Override
        public String toString() {
            return "Address [name=" + name + ", street=" + street + ", city=" + city + 
                   ", zipCode=" + zipCode + ", country=" + country + "]";
        }
    }
    
    /**
     * Validates address data according to business rules.
     */
    static class AddressValidator {
        /**
         * Validates the address data.
         * 
         * @param name Address name (required, max 100 chars)
         * @param street Street address (required, max 200 chars)
         * @param city City (required, max 50 chars)
         * @param zipCode Zip code (required, format: 5 digits or 5+4 format)
         * @param country Country (required, max 50 chars)
         * @return List of validation errors, empty if valid
         */
        public static List<String> validate(String name, String street, String city, 
                                           String zipCode, String country) {
            List<String> errors = new ArrayList<>();
            
            // Validate name
            if (name == null || name.trim().isEmpty()) {
                errors.add("Address name is required.");
            } else if (name.length() > 100) {
                errors.add("Address name cannot exceed 100 characters.");
            }
            
            // Validate street
            if (street == null || street.trim().isEmpty()) {
                errors.add("Street address is required.");
            } else if (street.length() > 200) {
                errors.add("Street address cannot exceed 200 characters.");
            }
            
            // Validate city
            if (city == null || city.trim().isEmpty()) {
                errors.add("City is required.");
            } else if (city.length() > 50) {
                errors.add("City cannot exceed 50 characters.");
            }
            
            // Validate zip code
            if (zipCode == null || zipCode.trim().isEmpty()) {
                errors.add("Zip code is required.");
            } else if (!isValidZipCode(zipCode.trim())) {
                errors.add("Zip code must be 5 digits (or 5+4 format).");
            }
            
            // Validate country
            if (country == null || country.trim().isEmpty()) {
                errors.add("Country is required.");
            } else if (country.length() > 50) {
                errors.add("Country cannot exceed 50 characters.");
            }
            
            return errors;
        }
        
        /**
         * Validates zip code format (5 digits or 5+4 format).
         */
        private static boolean isValidZipCode(String zipCode) {
            // Accepts 5 digits or 5+4 format (XXXXX-XXXX)
            return zipCode.matches("\\d{5}(-\\d{4})?");
        }
    }
    
    /**
     * Simulates the archive system for storing addresses.
     */
    static class AddressArchive {
        private List<Address> addresses = new ArrayList<>();
        private boolean smosServerConnected = true;
        
        /**
         * Inserts a new address into the archive.
         * 
         * @param address The address to insert
         * @return true if successful, false otherwise
         * @throws ArchiveException if there's an error with the SMOS server
         */
        public boolean insertAddress(Address address) throws ArchiveException {
            // Simulate SMOS server connection check
            if (!isSmosServerConnected()) {
                throw new ArchiveException("Connection data error to the SMOS server");
            }
            
            addresses.add(address);
            System.out.println("Address '" + address.getName() + "' successfully inserted into archive.");
            System.out.println("Total addresses in archive: " + addresses.size());
            return true;
        }
        
        /**
         * Gets all addresses in the archive.
         */
        public List<Address> getAllAddresses() {
            return new ArrayList<>(addresses);
        }
        
        /**
         * Simulates SMOS server connection status.
         */
        public boolean isSmosServerConnected() {
            // In a real system, this would check actual server connection
            return smosServerConnected;
        }
        
        /**
         * Simulates setting SMOS server connection status for testing.
         */
        public void setSmosServerConnected(boolean connected) {
            this.smosServerConnected = connected;
        }
    }
    
    /**
     * Custom exception for archive-related errors.
     */
    static class ArchiveException extends Exception {
        public ArchiveException(String message) {
            super(message);
        }
    }
    
    /**
     * Simulates the 'Errodati' use case for handling invalid data.
     */
    static class ErrorDataHandler {
        /**
         * Handles invalid data errors as per the 'Errodati' use case.
         * 
         * @param validationErrors List of validation errors
         */
        public static void handleInvalidData(List<String> validationErrors) {
            System.out.println("\n=== ERROR: Invalid Data Entered ===");
            System.out.println("The following validation errors occurred:");
            for (int i = 0; i < validationErrors.size(); i++) {
                System.out.println((i + 1) + ". " + validationErrors.get(i));
            }
            System.out.println("Please correct the errors and try again.");
            System.out.println("Activating 'Errodati' use case...");
        }
    }
    
    /**
     * Main program flow simulating the EnterNewAddress use case.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressArchive archive = new AddressArchive();
        
        System.out.println("=== EnterNewAddress Use Case Simulation ===");
        System.out.println("Preconditions:");
        System.out.println("1. User is logged in as administrator");
        System.out.println("2. User has viewed addresses list (ViewingLenchindirizzi)");
        System.out.println("3. User clicked 'New address' button");
        System.out.println();
        
        // System shows the form (Step 1 in events sequence)
        System.out.println("System: Showing address entry form...");
        System.out.println();
        
        boolean continueAdding = true;
        
        while (continueAdding) {
            try {
                // Simulate administrator operations (Steps 2-3)
                System.out.println("=== New Address Entry Form ===");
                
                System.out.print("Enter address name: ");
                String name = scanner.nextLine();
                
                System.out.print("Enter street address: ");
                String street = scanner.nextLine();
                
                System.out.print("Enter city: ");
                String city = scanner.nextLine();
                
                System.out.print("Enter zip code (5 digits or 5+4 format): ");
                String zipCode = scanner.nextLine();
                
                System.out.print("Enter country: ");
                String country = scanner.nextLine();
                
                System.out.println();
                
                // System checks validity of data entered (Step 4)
                System.out.println("System: Validating entered data...");
                List<String> validationErrors = AddressValidator.validate(name, street, city, zipCode, country);
                
                if (!validationErrors.isEmpty()) {
                    // Activate 'Errodati' use case for invalid data
                    ErrorDataHandler.handleInvalidData(validationErrors);
                    
                    System.out.print("\nDo you want to try again? (yes/no): ");
                    String retry = scanner.nextLine().toLowerCase();
                    
                    if (!retry.equals("yes")) {
                        System.out.println("Operation interrupted by administrator.");
                        continueAdding = false;
                    }
                    continue;
                }
                
                // Create address object
                Address newAddress = new Address(name.trim(), street.trim(), city.trim(), 
                                                zipCode.trim(), country.trim());
                
                // Insert new address in the archive
                System.out.println("System: Attempting to insert address into archive...");
                boolean success = archive.insertAddress(newAddress);
                
                if (success) {
                    System.out.println("System: Address successfully saved!");
                    
                    // Postcondition: User has entered an address
                    System.out.println("\nPostcondition: User has entered an address.");
                    
                    // Show current archive contents
                    System.out.println("\nCurrent addresses in archive:");
                    List<Address> allAddresses = archive.getAllAddresses();
                    if (allAddresses.isEmpty()) {
                        System.out.println("(No addresses in archive)");
                    } else {
                        for (int i = 0; i < allAddresses.size(); i++) {
                            System.out.println((i + 1) + ". " + allAddresses.get(i).getName());
                        }
                    }
                }
                
                System.out.print("\nDo you want to enter another address? (yes/no): ");
                String another = scanner.nextLine().toLowerCase();
                
                if (!another.equals("yes")) {
                    continueAdding = false;
                    System.out.println("Operation completed.");
                }
                
            } catch (ArchiveException e) {
                // Handle SMOS server connection error
                System.out.println("\n=== SYSTEM ERROR ===");
                System.out.println("Error: " + e.getMessage());
                System.out.println("Postcondition: User is notified of connection data error to the SMOS server.");
                System.out.println("Please check server connection and try again later.");
                
                System.out.print("\nDo you want to retry with simulated connection? (yes/no): ");
                String retry = scanner.nextLine().toLowerCase();
                
                if (retry.equals("yes")) {
                    archive.setSmosServerConnected(true);
                    System.out.println("SMOS server connection simulated as restored.");
                } else {
                    continueAdding = false;
                    System.out.println("Operation interrupted due to server error.");
                }
                
            } catch (Exception e) {
                // Handle unexpected errors
                System.out.println("\n=== UNEXPECTED ERROR ===");
                System.out.println("An unexpected error occurred: " + e.getMessage());
                System.out.println("Operation interrupted.");
                continueAdding = false;
            }
        }
        
        System.out.println("\n=== EnterNewAddress Use Case Completed ===");
        scanner.close();
    }
}