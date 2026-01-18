'''
The main entry point for the ATA Staff application.
It initializes the Swing GUI by displaying the LoginFrame.
'''
import javax.swing.SwingUtilities;
public class MainApp {
    public static void main(String[] args) {
        // Ensure that GUI updates are done on the Event Dispatch Thread (EDT)
        // This is a crucial practice in Swing to avoid threading issues.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the login frame
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
}