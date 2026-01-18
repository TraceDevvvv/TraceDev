/**
 * Entry point of the ViewUserDetails application.
 * Initializes and runs the main GUI interface.
 */
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Set system look and feel for better integration
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    System.err.println("Failed to set system look and feel");
                }
                ViewUserDetailsGUI gui = new ViewUserDetailsGUI();
                gui.setVisible(true);
            }
        });
    }
}