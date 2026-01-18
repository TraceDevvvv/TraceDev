'''
The main entry point for the ATTIVACONVENZIONE application.
This class is responsible for creating and displaying the ConventionActivationGUI
on the Event Dispatch Thread (EDT) to ensure proper Swing application behavior.
'''
import javax.swing.SwingUtilities;
/**
 * The main entry point for the ATTIVACONVENZIONE application.
 * This class is responsible for creating and displaying the ConventionActivationGUI
 * on the Event Dispatch Thread (EDT) to ensure proper Swing application behavior.
 */
public class Main {
    /**
     * The main method that starts the Convention Activation application.
     * It ensures that the GUI is created and updated on the Swing Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            ConventionActivationGUI gui = new ConventionActivationGUI();
            gui.setVisible(true); // Make the GUI visible
        });
    }
}