package ModifyTouristData_1702899395;

import java.util.Scanner;

public class ModifyTouristDataApp {

    public static void main(String[] args) {
        // Entry Conditions: Tourist has successfully authenticated to the system.
        // For this simulation, we assume a tourist with ID "tourist123" is authenticated.
        String authenticatedTouristId = "tourist123";

        TouristDataStore dataStore = new TouristDataStore();
        TouristService touristService = new TouristService(dataStore);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Tourist Data Modification System!");

        try {
            // 1. Access to functionality for the modification of data.
            // 2. Loads the data and displays them in a form.
            Tourist currentTourist = touristService.loadTouristData(authenticatedTouristId);
            if (currentTourist == null) {
                System.out.println("Error: Could not load tourist data. Exiting.");
                return;
            }

            System.out.println("Current Tourist Data:");
            System.out.println("Name: " + currentTourist.getName());
            System.out.println("Email: " + currentTourist.getEmail());
            System.out.println("-------------------------------------");

            // 3. Edit the fields in the form and submit.
            System.out.println("Please enter new data (leave blank to keep current value):");
            System.out.print("New Name (current: " + currentTourist.getName() + "): ");
            String newName = scanner.nextLine();
            if (newName.isEmpty()) {
                newName = currentTourist.getName();
            }

            System.out.print("New Email (current: " + currentTourist.getEmail() + "): ");
            String newEmail = scanner.nextLine();
            if (newEmail.isEmpty()) {
                newEmail = currentTourist.getEmail();
            }

            System.out.print("New Password (current: ********): ");
            String newPassword = scanner.nextLine();
            if (newPassword.isEmpty()) {
                newPassword = currentTourist.getPassword();
            }

            // Simulate user cancellation
            System.out.print("Confirm changes? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (!confirmation.equalsIgnoreCase("yes")) {
                touristService.cancelModification(authenticatedTouristId);
                System.out.println("Operation cancelled by user. Exiting.");
                return;
            }

            // 4. Check the modified information and asks for confirmation of the change.
            // Where the data is invalid or insufficient, the system activates the use case Errored.
            // 5. Confirmation of the transaction change.
            // 6. Stores the modified data.
            boolean success = touristService.modifyTouristData(authenticatedTouristId, newName, newEmail, newPassword);

            if (success) {
                // Exit conditions: The system shall notify the successful modification of data.
                System.out.println("Tourist data successfully updated!");
                System.out.println("Updated Tourist Data: " + dataStore.getTouristById(authenticatedTouristId));
            } else {
                System.out.println("Data modification failed.");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            // This handles the 'Errored' use case activation.
        } catch (Exception e) {
            // Simulate interruption of connection to the server ETOUR.
            touristService.simulateConnectionInterruption();
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
