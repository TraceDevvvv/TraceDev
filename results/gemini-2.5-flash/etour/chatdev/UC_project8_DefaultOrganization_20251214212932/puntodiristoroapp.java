'''
Main application class for the Punto Di Ristoro Agency Operator.
It sets up the main JFrame and manages the switching between the
list view and data editing view using a CardLayout.
This class orchestrates the overall flow according to the use case.
'''
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class PuntoDiRistoroApp extends JFrame implements
        PuntoDiRistoroListPanel.PuntoDiRistoroEditListener, // Listener for actions from the list view
        PuntoDiRistoroEditPanel.PuntoDiRistoroActionListener { // Listener for actions from the edit view
    private final PuntoDiRistoroService service;
    private final CardLayout cardLayout; // Layout manager to switch between panels
    private final JPanel mainPanel; // Container for panels managed by CardLayout
    private PuntoDiRistoroListPanel listPanel; // Panel to display the list of points of rest
    private PuntoDiRistoroEditPanel editPanel; // Panel to allow editing of a single point of rest
    // String constants to identify panels in the CardLayout
    private static final String LIST_VIEW = "ListView";
    private static final String EDIT_VIEW = "EditView";
    /**
     * Constructor for the main application frame.
     * Sets up the GUI, initializes the service, and creates/adds the different panels.
     */
    public PuntoDiRistoroApp() {
        super("Agency Operator: Edit Points of Rest"); // Set frame title
        this.service = new PuntoDiRistoroService(); // Initialize the data service
        // Configure the main JFrame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on window close
        setSize(800, 600); // Set a preferred initial size for the window
        setLocationRelativeTo(null); // Center the window on the screen
        cardLayout = new CardLayout(); // Initialize CardLayout
        mainPanel = new JPanel(cardLayout); // Create main panel with CardLayout
        // Initialize the list panel, passing the service and this class as its listener
        this.listPanel = new PuntoDiRistoroListPanel(service, this);
        // Initialize the edit panel, passing the service and this class as its listener
        this.editPanel = new PuntoDiRistoroEditPanel(service, this);
        // Add the panels to the mainPanel, each with a unique name for CardLayout switching
        mainPanel.add(listPanel, LIST_VIEW);
        mainPanel.add(editPanel, EDIT_VIEW);
        add(mainPanel); // Add the mainPanel (which contains all other panels) to the JFrame
        // Show the list view initially when the application starts
        cardLayout.show(mainPanel, LIST_VIEW);
    }
    /**
     * Main method to start the application.
     * Ensures that all Swing UI updates and creation are performed on the Event Dispatch Thread (EDT)
     * as required by Swing's threading model.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Operator conditions: The agency has logged.
                // For this specific use case implementation, we assume that the
                // login is successful and the application is being started by an authorized operator.
                new PuntoDiRistoroApp().setVisible(true); // Create and show the main application window
            }
        });
    }
    // --- Implementation of PuntoDiRistoroListPanel.PuntoDiRistoroEditListener ---
    /**
     * Callback method triggered by the `PuntoDiRistoroListPanel` when a user selects
     * a point of rest to edit and clicks the "Edit Selected" button.
     * (Corresponds to Use Case Step 1 and initiates Step 2).
     *
     * @param punto The `PuntoDiRistoro` object selected for editing.
     */
    @Override
    public void onEditPuntoDiRistoro(PuntoDiRistoro punto) {
        // Use Case Step 2: Upload data from the point of rest and displays the form of change.
        editPanel.loadPuntoDiRistoro(punto); // Load the selected punto's data into the edit form
        cardLayout.show(mainPanel, EDIT_VIEW); // Switch the view to the edit panel
    }
    /**
     * Callback method triggered by the `PuntoDiRistoroListPanel` when an error occurs,
     * typically during loading the list of points of rest (e.g., service interruption).
     *
     * @param message The error message to display to the user.
     */
    @Override
    public void onError(String message) {
        // Activate the "Errored" use case by displaying a comprehensive error dialog.
        ErroredDialog.display(this, message);
    }
    /**
     * Callback method triggered by the `PuntoDiRistoroListPanel` when the user requests
     * to refresh the list of points of rest.
     */
    @Override
    public void onRefreshRequest() {
        listPanel.loadPuntiDiRistoro(); // Reload the data in the list panel
    }
    // --- Implementation of PuntoDiRistoroEditPanel.PuntoDiRistoroActionListener ---
    /**
     * Callback method triggered by the `PuntoDiRistoroEditPanel` when a point of rest
     * is successfully saved after editing.
     * (Corresponds to Use Case Step 6 and the successful exit condition).
     *
     * @param updatedPunto The `PuntoDiRistoro` object with its updated and saved data.
     */
    @Override
    public void onPuntoDiRistoroSaved(PuntoDiRistoro updatedPunto) {
        // Exit condition: "The system has been reporting the information required by the point of rest."
        listPanel.loadPuntiDiRistoro(); // Refresh the list view to reflect the saved changes
        cardLayout.show(mainPanel, LIST_VIEW); // Switch back to the list view
    }
    /**
     * Callback method triggered by the `PuntoDiRistoroEditPanel` when the user
     * cancels the editing operation.
     * (Corresponds to an exit condition: "Operator Agency cancels the operation.").
     */
    @Override
    public void onPuntoDiRistoroCancelled() {
        cardLayout.show(mainPanel, LIST_VIEW); // Simply switch back to the list view without saving any changes
    }
}