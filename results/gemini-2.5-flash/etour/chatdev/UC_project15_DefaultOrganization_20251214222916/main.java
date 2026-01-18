import javax.swing.SwingUtilities;
/*
 * DOCSTRING:
 * The main entry point for the ModificaDatiAccountTurista application.
 * This class is responsible for initializing and launching the GUI.
 * It ensures that the Swing components are created and updated on the
 * Event Dispatch Thread (EDT) for thread-safety, as is required by Swing.
 *
 * This represents the "Actor initialization Agency Operator" and the
 * start of the application.
 */
public class Main {
    /**
     * The main method, which is the entry point of the Java application.
     * It schedules the GUI creation and display to be run on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Create an instance of the main form
            ModificaDatiAccountTuristaForm form = new ModificaDatiAccountTuristaForm();
            // Make the form visible to the user
            form.setVisible(true);
        });
    }
}