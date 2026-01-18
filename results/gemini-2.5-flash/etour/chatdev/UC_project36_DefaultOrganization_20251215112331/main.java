'''
This is the main entry point for the LOGINERRATO application.
It initializes and displays the LoginFrame, which is the primary window for user interaction.
'''
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Ensure GUI updates are handled on the Event Dispatch Thread (EDT) for thread safety.
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame(); // Create an instance of our main login window.
            frame.setVisible(true); // Make the login window visible to the user.
        });
    }
}