import java.util.List;

/**
 * Main class to demonstrate the DeleteUser use case.
 * This class sets up the environment, simulates administrator actions,
 * and triggers the user deletion process.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting DeleteUser Use Case Demonstration ---");

        // 1. Setup: Initialize SMOS Server and Administrator
        SMOSServer smosServer = new SMOSServer();
        DeleteUserSystem deleteUserSystem = new DeleteUserSystem(smosServer);
        Administrator admin = new Administrator("adminUser", "adminPass");

        // 2. Simulate initial state: Add some users to the system
        // Connect to SMOS server to add users
        smosServer.connect();
        smosServer.addUser(new User("user001", "Alice Smith"));
        smosServer.addUser(new User("user002", "Bob Johnson"));
        smosServer.addUser(new User("user003", "Charlie Brown"));
        smosServer.displayAllUsers();
        smosServer.interruptConnection(); // Disconnect after initial setup

        System.out.println("\n--- Scenario 1: Successful User Deletion ---");
        // Precondition: Administrator logs in
        admin.login("adminUser", "adminPass");

        // Precondition: Administrator views details of a user (e.g., user002)
        // This also ensures the SMOS server is connected for the operation.
        User userToViewAndDelete = deleteUserSystem.viewUserDetails(admin, "user002");

        if (userToViewAndDelete != null) {
            // Precondition: Administrator clicks "Delete" button (simulated by calling deleteUser)
            System.out.println("\nAdministrator clicks 'Delete' for user ID: " + userToViewAndDelete.getUserId());
            boolean deleted = deleteUserSystem.deleteUser(admin, userToViewAndDelete.getUserId());

            if (deleted) {
                System.out.println("User deletion successful for ID: " + userToViewAndDelete.getUserId());
            } else {
                System.out.println("User deletion failed for ID: " + userToViewAndDelete.getUserId());
            }
        } else {
            System.out.println("Cannot proceed with deletion as user details could not be viewed.");
        }

        System.out.println("\n--- Scenario 2: Attempt to delete a non-existent user ---");
        // Ensure admin is logged in and server is connected (viewUserDetails handles this)
        deleteUserSystem.viewUserDetails(admin, "user999"); // Try to view non-existent user
        System.out.println("\nAdministrator clicks 'Delete' for non-existent user ID: user999");
        boolean deletedNonExistent = deleteUserSystem.deleteUser(admin, "user999");
        if (!deletedNonExistent) {
            System.out.println("Correctly failed to delete non-existent user 'user999'.");
        }

        System.out.println("\n--- Scenario 3: Attempt to delete user when administrator is not logged in ---");
        admin.logout(); // Log out the administrator
        System.out.println("\nAdministrator (now logged out) clicks 'Delete' for user ID: user001");
        boolean deletedLoggedOut = deleteUserSystem.deleteUser(admin, "user001");
        if (!deletedLoggedOut) {
            System.out.println("Correctly failed to delete user as administrator is not logged in.");
        }

        System.out.println("\n--- Final State Check ---");
        // Reconnect to display all users after all operations
        smosServer.connect();
        smosServer.displayAllUsers();
        smosServer.interruptConnection();

        System.out.println("\n--- DeleteUser Use Case Demonstration Finished ---");
    }
}