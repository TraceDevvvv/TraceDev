'''
ErrorDialog is a JDialog used to display error messages to the user.
It acts as the system's notification mechanism for invalid input and requires user confirmation.
'''
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
public class ErrorDialog extends JDialog {
    /**
     * Static method to display a modal error dialog.
     * This method implements steps 1 and 2 of the "Flow of events" in the use case:
     * 1. System not that the data entered for the login is not valid and asks for confirmation of the reading.
     * 2. Confirmation of the reading of the notification.
     *
     * @param parent The parent JFrame over which the dialog should be centered.
     * @param title The title of the error dialog.
     * @param message The error message to be displayed to the user.
     */
    public static void showError(JFrame parent, String title, String message) {
        // JOptionPane.showMessageDialog is a convenient way to display a simple modal dialog.
        // It blocks execution until the user dismisses the dialog (e.g., by clicking OK or closing it).
        // This effectively handles "Confirmation of the reading of the notification" by the user.
        JOptionPane.showMessageDialog(
            parent,      // Parent component (JFrame)
            message,     // Message to display
            title,       // Dialog title
            JOptionPane.ERROR_MESSAGE // Icon type for error message
        );
        // After this line executes, the user has clicked "OK" or closed the dialog,
        // signifying "Confirmation of the reading of the notification."
        System.out.println("User confirmed error notification and dismissed the dialog."); // Log for debugging.
    }
}