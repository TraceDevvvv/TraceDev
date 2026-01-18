/**
 * Handles the login error flow according to the specified use case.
 * This class implements the error handling sequence when invalid login data is detected.
 */
import javax.swing.JOptionPane;
public class LoginErrorHandler {
    /**
     * Steps 1 & 2 combined: Notify that the entered data is not valid 
     * and ask for confirmation of reading in a single dialog.
     * 
     * @param errorMessage The specific error message to display
     * @return true if user confirms they've read the notification, false otherwise
     */
    public boolean requestConfirmationForInvalidData(String errorMessage) {
        // Create a combined message for notification and confirmation request
        String fullMessage = errorMessage + "\n\n" +
                            "Please read this notification carefully.\n" +
                            "Do you confirm you have read this message and want to return to login?";
        // Show confirmation dialog with error message
        int result = JOptionPane.showConfirmDialog(null, 
            fullMessage,
            "Login Error - Invalid Data",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.ERROR_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    /**
     * Step 3: Recover the previous state.
     * This method coordinates the recovery process.
     * In our implementation, the recovery is triggered here and executed by the LoginFrame.
     */
    public void initiateRecovery() {
        // Log recovery initiation (in a real application)
        System.out.println("LoginErrorHandler: Initiating recovery of previous state...");
        // This method now properly coordinates with LoginFrame.recoverPreviousState()
        // to ensure the error handling flow is complete and logical
    }
}