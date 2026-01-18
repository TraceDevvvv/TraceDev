'''
The main class to run the User Role Management application.
Initializes the UserManager with sample data (users and roles)
and starts the Administrator Dashboard GUI.
'''
import gui.AdminDashboardFrame;
import model.User;
import model.UserManager;
import service.SMOSServer;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        // Initialize the UserManager
        UserManager userManager = new UserManager();
        // Initialize the SMOS Server simulation
        SMOSServer smosServer = new SMOSServer();
        // Add some available roles
        userManager.addAvailableRole("Administrator");
        userManager.addAvailableRole("Editor");
        userManager.addAvailableRole("Viewer");
        userManager.addAvailableRole("Developer");
        userManager.addAvailableRole("Guest");
        // Create some sample users
        User adminUser = userManager.getOrCreateUser("U001", "Admin Alice");
        userManager.assignRole("U001", "Administrator"); // Assign admin role
        userManager.assignRole("U001", "Editor");
        User userBob = userManager.getOrCreateUser("U002", "User Bob");
        userManager.assignRole("U002", "Viewer");
        User userCarol = userManager.getOrCreateUser("U003", "User Carol");
        userManager.assignRole("U003", "Editor");
        userManager.assignRole("U003", "Developer");
        User userDavid = userManager.getOrCreateUser("U004", "User David"); // User with no roles initially
        // Precondition: The user is logged in as an administrator.
        // We simulate this by directly launching the AdminDashboardFrame.
        // In a full application, a login screen would precede this.
        SwingUtilities.invokeLater(() -> {
            AdminDashboardFrame adminDashboard = new AdminDashboardFrame(userManager, smosServer);
            adminDashboard.setVisible(true);
        });
    }
}