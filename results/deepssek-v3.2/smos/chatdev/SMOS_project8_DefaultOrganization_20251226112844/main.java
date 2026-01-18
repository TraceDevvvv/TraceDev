import java.util.Scanner;
/**
 * Main class to run the user editing simulation.
 * This simulates the complete workflow based on the use case requirements.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserEditor editor = new UserEditor();
        System.out.println("=== User Editing System ===");
        // Step 1: Administrator login (precondition)
        System.out.print("Enter administrator ID: ");
        String adminId = scanner.nextLine();
        if (!editor.login(adminId)) {
            System.out.println("Error: Invalid administrator ID.");
            scanner.close();
            return;
        }
        System.out.println("Administrator '" + adminId + "' logged in successfully.");
        // Step 2: View user details (precondition - "viewdetTailsente")
        System.out.print("\nEnter user ID to view details: ");
        String userId = scanner.nextLine();
        User user = editor.viewUserDetails(userId);
        if (user == null) {
            editor.logout();
            scanner.close();
            return;
        }
        // Step 3: Edit user (user clicks "Edit" button)
        System.out.print("\nPress Enter to click 'Edit' button...");
        scanner.nextLine();
        System.out.println("\n=== Edit User Form ===");
        System.out.println("Current user details:");
        System.out.println(user.toString());
        System.out.print("\nEnter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        // Step 4: System validates and updates user
        editor.editUser(userId, newName, newEmail);
        // Step 5: Display updated user details
        System.out.println("\n=== Updated User Details ===");
        User updatedUser = editor.viewUserDetails(userId);
        // Step 6: Logout (postcondition - connection interrupted)
        editor.logout();
        scanner.close();
        System.out.println("\n=== Program Ended ===");
    }
}