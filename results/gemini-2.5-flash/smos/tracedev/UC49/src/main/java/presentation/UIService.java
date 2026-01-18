package presentation;

/**
 * A service responsible for displaying UI-related messages, such as error messages.
 * In a real application, this would interact with a GUI framework or web UI.
 */
public class UIService {

    /**
     * Displays an error message to the user.
     * For this simulation, it prints the message to the console.
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.err.println("[UI Service] ERROR: " + message);
    }
}