import javax.swing.JOptionPane;
/*
 * DOCSTRING:
 * This class simulates the 'Errored' use case described in the requirements.
 * It provides a simple method to display an error message to the user,
 * indicating invalid or insufficient data entry.
 * In a more complex system, this might log errors, trigger specific error
 * handling workflows, or provide more detailed user guidance.
 */
class ErroredUseCase {
    /**
     * Displays an error message to the user in a standardized dialog box.
     * This acts as the system's way of "activating the use case Errored."
     *
     * @param message The specific error message to be displayed to the user.
     */
    public static void displayError(String message) {
        JOptionPane.showMessageDialog(null, message, "Errore di Validazione", JOptionPane.ERROR_MESSAGE);
    }
}