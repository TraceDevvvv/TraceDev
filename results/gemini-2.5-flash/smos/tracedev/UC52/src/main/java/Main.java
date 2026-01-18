// File: Main.java
import java.util.Scanner;

/**
 * Main class to demonstrate the execution of the Administrator Search Use Case.
 * This class sets up the necessary components and simulates user interaction
 * to trigger the search flow, including success and error scenarios.
 */
public class Main {
    public static void main(String[] args) {
        // --- Setup ---\n        // Instantiate dummy repositories for a runnable example
        IClassRepository classRepo = new IClassRepositoryImpl();
        ITeachingRepository teachingRepo = new ITeachingRepositoryImpl();
        IAddressRepository addressRepo = new IAddressRepositoryImpl();
        IUserRepository userRepo = new IUserRepositoryImpl();

        // Instantiate SearchService with dummy repositories
        SearchService searchService = new SearchService(classRepo, teachingRepo, addressRepo, userRepo);

        // Instantiate AdministratorUI with the SearchService
        AdministratorUI adminUI = new AdministratorUI(searchService);

        // --- Simulation ---\n        System.out.println("--- Starting Administrator Search Use Case Simulation ---");
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            adminUI.displaySearchForm();
            System.out.print("(Type 'exit' to quit, 'cancel' to simulate cancel, 'fail_class', 'fail_teaching', 'fail_address', 'fail_user' to simulate errors): ");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting simulation.");
                break;
            } else if (input.equalsIgnoreCase("cancel")) {
                adminUI.cancelsSearch(); // Updated to match sequence diagram message m5
                System.out.println("Simulated cancellation. Please enter a new search or 'exit'.");
                continue; // Skip triggering clicksSearchButton
            } else {
                // Simulate Admin -> UI : insertsText(searchText : String)
                adminUI.insertsText(input);

                // Simulate Admin -> UI : clicksSearchButton()
                adminUI.clicksSearchButton(); // Updated to match sequence diagram message m4
            }
        }

        scanner.close();
        System.out.println("--- Simulation Ended ---");
    }
}