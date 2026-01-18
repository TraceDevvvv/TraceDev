/**
 * Main class to launch the Class Register Details application.
 * Initializes and displays the GUI.
 */
import com.chatdev.register.gui.RegisterDetailsApp;
public class Main {
    /**
     * The main method, entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are run on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create an instance of the main application window
                // Simulate receiving a register ID (e.g., from a previous "ViewingAllRegisters" step)
                String simulatedRegisterId = "CLASS_5B_MATH_2023";
                new RegisterDetailsApp(simulatedRegisterId);
            }
        });
    }
}