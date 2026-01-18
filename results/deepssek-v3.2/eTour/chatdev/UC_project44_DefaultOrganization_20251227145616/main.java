import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
/**
 * Main class to demonstrate the ConventionRequest use case.
 * This program simulates the flow described in the use case.
 */
public class Main {
    /**
     * Represents a simple Convention data model.
     */
    static class Convention {
        String conventionId;
        String restaurantName;
        String agencyName;
        String startDate;
        String endDate;
        String terms; // Additional terms of the convention
        public Convention(String restaurantName, String agencyName,
                          String startDate, String endDate, String terms) {
            this.restaurantName = restaurantName;
            this.agencyName = agencyName;
            this.startDate = startDate;
            this.endDate = endDate;
            this.terms = terms;
            this.conventionId = "CONV-" + System.currentTimeMillis();
        }
        public String toString() {
            return "Convention ID: " + conventionId + "\n" +
                   "Restaurant: " + restaurantName + "\n" +
                   "Agency: " + agencyName + "\n" +
                   "Start Date: " + startDate + "\n" +
                   "End Date: " + endDate + "\n" +
                   "Terms: " + terms;
        }
    }
    /**
     * Static inner class to simulate the ConventionRequest functionality.
     */
    static class ConventionRequest {
        // Simulating server connectivity
        private static boolean serverConnected = true;
        private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        /**
         * Displays the form and collects input data.
         * @param scanner the Scanner for user input.
         * @return a Convention object with the entered data.
         */
        public static Convention displayFormAndCollectData(Scanner scanner) {
            System.out.println("=== Convention Request Form ===");
            System.out.print("Enter Restaurant Name: ");
            String restaurantName = scanner.nextLine().trim();
            System.out.print("Enter Agency Name: ");
            String agencyName = scanner.nextLine().trim();
            System.out.print("Enter Convention Start Date (YYYY-MM-DD): ");
            String startDate = scanner.nextLine();
            System.out.print("Enter Convention End Date (YYYY-MM-DD): ");
            String endDate = scanner.nextLine();
            System.out.print("Enter Additional Terms: ");
            String terms = scanner.nextLine();
            return new Convention(restaurantName, agencyName, startDate, endDate, terms);
        }
        /**
         * Validates the convention data.
         * @param convention the Convention object to validate.
         * @return ValidationResult indicating validity and any error message.
         */
        public static ValidationResult validateConventionData(Convention convention) {
            // Check for empty fields
            if (convention.restaurantName == null || convention.restaurantName.trim().isEmpty()) {
                return new ValidationResult(false, "Restaurant name cannot be empty.");
            }
            if (convention.agencyName == null || convention.agencyName.trim().isEmpty()) {
                return new ValidationResult(false, "Agency name cannot be empty.");
            }
            if (convention.startDate == null || convention.startDate.trim().isEmpty()) {
                return new ValidationResult(false, "Start date cannot be empty.");
            }
            if (convention.endDate == null || convention.endDate.trim().isEmpty()) {
                return new ValidationResult(false, "End date cannot be empty.");
            }
            // Validate date format and logic
            try {
                LocalDate start = LocalDate.parse(convention.startDate, DATE_FORMATTER);
                LocalDate end = LocalDate.parse(convention.endDate, DATE_FORMATTER);
                // Check if start date is before end date
                if (!start.isBefore(end)) {
                    return new ValidationResult(false, "Start date must be before end date.");
                }
                // Check if start date is not in the past
                if (start.isBefore(LocalDate.now())) {
                    return new ValidationResult(false, "Start date cannot be in the past.");
                }
            } catch (DateTimeParseException e) {
                return new ValidationResult(false, "Invalid date format. Please use YYYY-MM-DD format with a valid date.");
            }
            return new ValidationResult(true, "Data is valid.");
        }
        /**
         * Simulates sending the request to the Convention server.
         * @param convention the Convention to send.
         * @return true if sent successfully, false otherwise.
         */
        public static boolean sendConventionRequest(Convention convention) {
            if (!serverConnected) {
                System.out.println("Error: Connection to server ETOUR interrupted.");
                return false;
            }
            // Simulate network delay
            try {
                System.out.println("Sending convention request to server...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Request interrupted.");
                return false;
            }
            System.out.println("Convention request sent successfully.");
            System.out.println("Convention details:\n" + convention);
            return true;
        }
        /**
         * Simulates the main flow of the ConventionRequest use case.
         */
        public static void run() {
            Scanner scanner = new Scanner(System.in);
            Convention convention = null;
            boolean requestComplete = false;
            // Step 1: Enable the functionality to request the Convention to the Agency.
            System.out.println("Convention Request functionality enabled.");
            // Step 2: Display form and collect data.
            convention = displayFormAndCollectData(scanner);
            // Step 3: Data is already inserted via the form above.
            // Step 4: Validate data.
            ValidationResult validationResult = validateConventionData(convention);
            if (!validationResult.isValid()) {
                System.out.println("Validation failed: " + validationResult.getErrorMessage());
                System.out.println("Activating use case Errored.");
                return; // Exit due to error.
            }
            // Display entered data for confirmation
            System.out.println("\n=== Please review your convention request ===");
            System.out.println(convention);
            System.out.println("=============================================\n");
            // Ask for confirmation.
            System.out.print("Do you confirm the request? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            // Step 5: Confirm the operation.
            if (!confirmation.equals("yes")) {
                System.out.println("Operation cancelled by Restaurant Point Of Operator.");
                return; // Exit: cancellation.
            }
            // Step 6: Send the request.
            requestComplete = sendConventionRequest(convention);
            // Exit conditions.
            if (requestComplete) {
                System.out.println("Notification about the call for the Convention to the Agency has been sent.");
            } else {
                System.out.println("Convention request failed due to server interruption.");
            }
        }
        /**
         * Method to simulate server interruption (for testing edge cases).
         */
        public static void setServerConnected(boolean connected) {
            serverConnected = connected;
        }
    }
    /**
     * Main method to run the ConventionRequest simulation.
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Simulate that the Point Of Restaurant Operator has successfully authenticated.
        System.out.println("=== Welcome to Convention Request System ===");
        System.out.println("User authenticated successfully.\n");
        ConventionRequest.run();
        System.out.println("\n=== Convention Request System terminated ===");
    }
}