/**
Entry point for the AcceptEnrollmentStudent application.
This class launches the GUI by creating an instance of EnrollmentGUI.
It uses SwingUtilities.invokeLater to ensure thread safety for the GUI.
*/
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure the GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the main GUI window
                new EnrollmentGUI().setVisible(true);
            }
        });
    }
}