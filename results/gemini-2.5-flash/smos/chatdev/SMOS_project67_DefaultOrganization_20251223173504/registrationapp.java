'''
    RegistrationApp.java
    This is the main entry point for the Student Registration application.
    It creates and displays the RegistrationForm GUI.
'''
import javax.swing.SwingUtilities;
public class RegistrationApp {
    /**
     * The main method, which is the starting point of the application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
     * as required by Swing for thread safety.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure that GUI updates are done on the Event Dispatch Thread (EDT)
        // This is crucial for Swing applications to maintain responsiveness and thread safety.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create an instance of the RegistrationForm
                RegistrationForm form = new RegistrationForm();
                // Make the form window visible to the user
                form.setVisible(true);
            }
        });
    }
}