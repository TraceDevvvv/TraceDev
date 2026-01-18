import javax.swing.SwingUtilities;
/*
 * Main class to launch the InsertNewTeaching application.
 * This class serves as the entry point for the program,
 * initiating the administrative workflow as described in the use case.
 */
public class Main {
    /**
     * The main method that starts the application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT),
     * and sets up the primary view for the administrator.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI creation and updates are performed on the Event Dispatch Thread (EDT)
        // This is crucial for Swing applications to maintain responsiveness and thread safety.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Initialize the TeachingArchive, which acts as the backend data store
                // for our application, managing teaching entities.
                TeachingArchive archive = new TeachingArchive();
                // Create and display the TeachingListView.
                // This window represents the precondition where the administrator is
                // "viewing technology" and sees the list of existing teachings.
                // From here, the administrator can then click "New Teaching" to
                // initiate the InsertNewTeaching use case.
                TeachingListView teachingListView = new TeachingListView(archive);
                teachingListView.setVisible(true);
            }
        });
    }
}