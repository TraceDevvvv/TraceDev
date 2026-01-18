import java.util.Scanner;

/**
 * Main application class to demonstrate the DeleteRefreshmentPoint use case.
 * This class orchestrates the interaction between the OperatorAgency and the CulturalHeritageSystem.
 */
public class DeleteRefreshmentPointApp {

    public static void main(String[] args) {
        // Initialize the system and the actor
        CulturalHeritageSystem system = new CulturalHeritageSystem();
        OperatorAgency agency = new OperatorAgency(system);
        Scanner mainScanner = new Scanner(System.in);

        System.out.println("Welcome to the Refreshment Point Management System!");

        // Entry Operator conditions: The agency has logged.
        // Simulate login
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter agency username: ");
            String username = mainScanner.nextLine();
            System.out.print("Enter agency password: ");
            String password = mainScanner.nextLine();
            loggedIn = agency.login(username, password);
            if (!loggedIn) {
                System.out.println("Incorrect credentials. Please try again.");
            }
        }

        // Once logged in, proceed with the delete refreshment point flow
        // This loop allows for multiple deletion attempts or other operations
        String choice;
        do {
            agency.deleteRefreshmentPointFlow();

            System.out.print("\nDo you want to perform another deletion or exit? (delete/exit): ");
            choice = mainScanner.nextLine();
            // Simulate potential connection re-establishment if it was interrupted in the previous flow
            if (!system.isETOURConnectionActive()) {
                system.reestablishETOURConnection();
            }

        } while ("delete".equalsIgnoreCase(choice));

        System.out.println("Exiting Refreshment Point Management System. Goodbye!");
        agency.closeScanner(); // Close the scanner used by the agency
        mainScanner.close(); // Close the main scanner
    }
}