'''
Main class to run the ViewUserList application.
This is the entry point of the program.
'''
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety for GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Set look and feel to system default for better appearance
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    System.err.println("Error setting look and feel: " + e.getMessage());
                }
                // Create and show the application
                ViewUserListApp app = new ViewUserListApp();
                app.setVisible(true);
                System.out.println("User Management System started.");
                System.out.println("Use Case: ViewUserList");
                System.out.println("Actors: Administrator");
            }
        });
    }
}