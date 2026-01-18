/*
This is the main entry point for the "Elimina Bene Culturale" application.
It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
as per Swing's thread-safety guidelines. It now starts with a Login GUI
to fulfill the "Agency Operator has logged" entry condition.
*/
import javax.swing.SwingUtilities;
public class Main {
    /**
     * The main method that starts the GUI application.
     * It now initializes and displays the LoginGUI first.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the login window first
                new LoginGUI().setVisible(true);
            }
        });
    }
}