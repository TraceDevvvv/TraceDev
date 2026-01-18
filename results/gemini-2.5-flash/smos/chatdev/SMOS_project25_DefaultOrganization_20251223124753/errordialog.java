/**
 * Utility class to display error messages to the user.
 * Implements the "Errodati" use case.
 */
import javax.swing.JOptionPane;
import javax.swing.JFrame; // Only for parent component, can be null
public class ErrorDialog {
    /**
     * Displays an error message dialog.
     * @param message The error message to display.
     */
    public static void showError(String message) {
        // Use JOptionPane for simplicity to display an error dialog.
        // The first argument null makes the dialog appear in the center of the screen.
        JOptionPane.showMessageDialog(null, message, "Data Entry Error (Errodati)", JOptionPane.ERROR_MESSAGE);
    }
}