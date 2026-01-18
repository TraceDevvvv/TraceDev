import java.util.Scanner;
/**
 * Main class that implements the complete DeleteUser use case.
 * This program simulates the administrator interface for deleting users.
 */
public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        System.out.println("=== User Management System ===");
        // Use try-with-resources to ensure Scanner is closed automatically
        try (Scanner scanner = new Scanner(System.in)) {
            // Precondition 1: Administrator must be logged in
            System.out.print("Enter administrator username: ");
            String adminUsername = scanner.nextLine();
            if (!userManager.isAdministrator(adminUsername)) {
                System.out.println("Error: You must be logged in as an administrator.");
                return;
            }
            System.out.println("Administrator " + adminUsername + " logged in successfully.");
            // Display current users
            System.out.println("\nCurrent Users:");
            userManager.displayUpdatedUserList();
            // Precondition 2: User has carried out "viewdetTailsente" use case
            System.out.print("\nEnter username to view details (simulating viewdetTailsente): ");
            String usernameToView = scanner.nextLine();
            User userToView = userManager.findUserByUsername(usernameToView);
            if (userToView == null) {
                System.out.println("Error: User '" + usernameToView + "' not found.");
                return;
            }
            System.out.println("User Details:");
            System.out.println("-------------");
            System.out.println("Username: " + userToView.getUsername());
            System.out.println("Email: " + userToView.getEmail());
            System.out.println("Role: " + userToView.getRole());
            System.out.println("Is Admin: " + userToView.isAdmin());
            System.out.println("-------------");
            // Precondition 3: User clicks on "Delete" button
            System.out.print("\nDo you want to delete user '" + usernameToView + "'? (yes/no): ");
            String confirmation = scanner.nextLine().toLowerCase();
            if (!confirmation.equals("yes")) {
                System.out.println("Deletion cancelled.");
                return;
            }
            // Event sequence 1: Delete the user from the archive
            System.out.println("\nDeleting user '" + usernameToView + "'...");
            boolean deletionSuccess = userManager.deleteUser(usernameToView);
            if (deletionSuccess) {
                System.out.println("User '" + usernameToView + "' deleted successfully.");
                // Postcondition 1: The user has been canceled
                System.out.println("User cancellation confirmed.");
                // Postcondition 2: Connection to SMOS server interrupted
                System.out.println("SMOS server connection status: " + 
                    (userManager.isSMOSServerConnected() ? "Connected" : "Interrupted"));
                // Event sequence 2: Displays the list of updated users
                System.out.println("\nDisplaying updated user list...");
                userManager.displayUpdatedUserList();
                // Verify user is no longer in the system
                User deletedUser = userManager.findUserByUsername(usernameToView);
                if (deletedUser == null) {
                    System.out.println("Verification: User '" + usernameToView + "' is no longer in the system.");
                }
            } else {
                System.out.println("Error: Failed to delete user '" + usernameToView + "'.");
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n=== Program Completed ===");
    }
}