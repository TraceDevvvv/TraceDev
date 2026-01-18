'''
This is the main entry point for the Edit User application.
It initializes the core components and launches the GUI.
'''
import javax.swing.SwingUtilities;
public class MainApp {
    /**
     * The main method to start the application.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create an instance of the UserManager, which handles business logic for users.
        UserManager userManager = new UserManager();
        // Ensure that GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the EditUserGUI
                EditUserGUI gui = new EditUserGUI(userManager);
                gui.setVisible(true);
            }
        });
    }
}