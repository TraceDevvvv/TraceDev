'''
Main class to launch the EliminaBanner GUI application.
Ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
'''
import javax.swing.SwingUtilities;
public class Main {
    /**
     * The main entry point for the application.
     * Creates and runs the EliminaBannerGUI on the Event Dispatch Thread.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EliminaBannerGUI();
            }
        });
    }
}