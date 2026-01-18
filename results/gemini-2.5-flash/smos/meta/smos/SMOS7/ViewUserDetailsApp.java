import java.util.Scanner;
import java.util.Set;

/**
 * Main application class to simulate the "ViewUserDetails" use case.
 * This class orchestrates the interaction between the Administrator (simulated via console input)
 * and the system (UserManager and User classes) to display user details.
 *
 * Preconditions simulated:
 * 1. The user is logged in as administrator (assumed for running this application).
 * 2. The user took the case of use "ViewingLanciutenti" and the system is viewing the list of users.
 *    (simulated by displaying available user logins).
 * 3. The user clicks on the "Details" button (simulated by prompting for a login to view).
 *
 * Events sequence:
 * 1. System displays detailed user information (Name, Surname, E-mail, Cell, Login, Password).
 *
 * Postconditions simulated:
 * 1. The system displays detailed information about a user.
 * 2. Connection to the SMOS server interrupted.
 */
public class ViewUserDetailsApp {

    public static void main(String[] args) {
        // 1. Initialize UserManager and populate with sample users
        UserManager userManager = new UserManager();
        System.out.println("--- Initializing User Management System ---");

        // Add some sample users for demonstration
        userManager.addUser(new User("Alice", "Smith", "alice.smith@example.com", "123-456-7890", "alice_s", "pass123"));
        userManager.addUser(new User("Bob", "Johnson", "bob.j@example.com", "098-765-4321", "bob_j", "securepwd"));
        userManager.addUser(new User("Charlie", "Brown", "charlie.b@example.com", "555-111-2222", "charlie_b", "mysecret"));
        System.out.println("--- User data loaded ---");

        // Simulate administrator login (precondition 1)
        System.out.println("\n--- Administrator logged in ---");

        // Simulate "ViewingLanciutenti" use case: display list of users (precondition 2)
        System.out.println("\n--- Viewing list of users (ViewingLanciutenti) ---");
        Set<String> availableLogins = userManager.getAllUserLogins();
        if (availableLogins.isEmpty()) {
            System.out.println("No users currently registered in the system.");
            System.out.println("Exiting application as there are no users to view.");
            return; // Exit if no users to view
        } else {
            System.out.println("Available user logins:");
            for (String login : availableLogins) {
                System.out.println("- " + login);
            }
        }

        // Simulate administrator clicking "Details" button (precondition 3)
        Scanner scanner = new Scanner(System.in);
        String selectedLogin = null;
        boolean userFound = false;

        while (!userFound) {
            System.out.print("\nEnter the login of the user whose details you want to view (or 'exit' to quit): ");
            selectedLogin = scanner.nextLine();

            if ("exit".equalsIgnoreCase(selectedLogin)) {
                System.out.println("Exiting user details view.");
                break; // Exit the loop and application
            }

            // Event sequence 1: System displays detailed user information
            userFound = userManager.displayUserDetails(selectedLogin);
            if (!userFound) {
                System.out.println("Please try again with an existing login from the list above.");
            }
        }

        // Postcondition 2: Connection to the SMOS server interrupted
        userManager.interruptSMOSConnection();

        scanner.close();
        System.out.println("\n--- Application simulation finished ---");
    }
}