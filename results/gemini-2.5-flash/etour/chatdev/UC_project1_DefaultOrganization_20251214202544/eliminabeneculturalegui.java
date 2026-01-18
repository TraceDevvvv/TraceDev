/*
This class represents the main graphical user interface (GUI) for the
ELIMINABENECULTURALE use case. It allows an Agency Operator to view a list
of Beni Culturali and select one for deletion after confirmation.
It handles user interactions, updates the display, and communicates with the
BeniCulturaleService.
*/
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;
public class EliminaBeneCulturaleGUI extends JFrame {
    private BeniCulturaleService service; // Service layer to interact with cultural objects
    private JList<BeniCulturale> culturalObjectList; // Displays the list of cultural objects
    private DefaultListModel<BeniCulturale> listModel; // Model for the JList
    private JButton deleteButton; // Button to trigger the deletion process
    private JLabel statusBar; // Displays status messages to the user
    /**
     * Constructor for the EliminaBeneCulturaleGUI.
     * Initializes the service, sets up the UI components, adds event listeners,
     * and loads the initial list of cultural objects.
     */
    public EliminaBeneCulturaleGUI() {
        super("Elimina Bene Culturale"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(600, 400); // Initial window size
        setLocationRelativeTo(null); // Center the window on screen
        setupUI(); // Set up all GUI components
        addListeners(); // Add action listeners to components
        loadCulturalObjects(); // Load initial data into the list
    }
    /**
     * Sets up the graphical user interface components and their layout.
     */
    private void setupUI() {
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with gaps
        // Panel for the main list of cultural objects
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Beni Culturali Disponibili"));
        listModel = new DefaultListModel<>(); // Create the list model
        culturalObjectList = new JList<>(listModel); // Create the JList with the model
        culturalObjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow single selection
        JScrollPane scrollPane = new JScrollPane(culturalObjectList); // Add scroll capability
        listPanel.add(scrollPane, BorderLayout.CENTER);
        add(listPanel, BorderLayout.CENTER); // Add list panel to the center of the frame
        // Panel for the delete button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deleteButton = new JButton("Elimina Bene Culturale Selezionato");
        deleteButton.setEnabled(false); // Initially disabled until an item is selected
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom of the frame
        // Status bar for messages
        statusBar = new JLabel("Benvenuto, operatore dell'agenzia. Seleziona un bene culturale da eliminare.");
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding
        add(statusBar, BorderLayout.NORTH); // Add status bar to the top
    }
    /**
     * Adds event listeners to the GUI components.
     * This includes listeners for the delete button and for list selection changes.
     */
    private void addListeners() {
        // Listener for the delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedCulturalObject(); // Call the deletion method
            }
        });
        // Listener for changes in list selection
        culturalObjectList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Enable the delete button only if an item is selected and the event is not adjusting
                if (!e.getValueIsAdjusting()) {
                    deleteButton.setEnabled(culturalObjectList.getSelectedIndex() != -1);
                }
            }
        });
    }
    /**
     * Loads the list of cultural objects from the BeniCulturaleService and
     * populates the JList. Handles potential connection interruptions.
     */
    private void loadCulturalObjects() {
        // Clear the current list model before loading new data
        listModel.clear();
        updateStatusMessage("Caricamento beni culturali...", Color.BLACK);
        toggleInputControls(false); // Disable controls during loading
        // Use SwingWorker to perform long-running operations off the Event Dispatch Thread (EDT)
        // to keep the UI responsive.
        new SwingWorker<List<BeniCulturale>, Void>() {
            @Override
            protected List<BeniCulturale> doInBackground() throws ConnectionInterruptionException {
                // The logged-in state is now an explicit entry condition handled by LoginGUI.
                return service.getAllBeniCulturali();
            }
            @Override
            protected void done() {
                toggleInputControls(true); // Re-enable controls after loading attempt
                try {
                    List<BeniCulturale> beni = get(); // Get the result from doInBackground
                    if (beni.isEmpty()) {
                        updateStatusMessage("Nessun bene culturale trovato.", Color.ORANGE);
                        deleteButton.setEnabled(false); // Disable delete if no items
                    } else {
                        // Add all fetched cultural objects to the list model
                        for (BeniCulturale bene : beni) {
                            listModel.addElement(bene);
                        }
                        updateStatusMessage("Beni culturali caricati. Selezionare un elemento per eliminare.", Color.BLACK);
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    // Handle exceptions (e.g., ConnectionInterruptionException wrapped in ExecutionException)
                    System.err.println("Error loading cultural objects: " + ex.getMessage());
                    // Extract the most relevant error message
                    Throwable cause = ex.getCause(); // Get the root cause of the exception
                    String errorMessage = (cause != null) ? cause.getMessage() : ex.getMessage();
                    updateStatusMessage("Errore durante il caricamento: " + errorMessage, Color.RED);
                    deleteButton.setEnabled(false); // Disable delete on error
                }
            }
        }.execute(); // Execute the SwingWorker
    }
    /**
     * Initiates the process of deleting a selected cultural object.
     * This involves asking for confirmation, calling the service, and
     * updating the UI based on the outcome.
     */
    private void deleteSelectedCulturalObject() {
        BeniCulturale selectedBene = culturalObjectList.getSelectedValue();
        if (selectedBene == null) {
            updateStatusMessage("Si prega di selezionare un bene culturale da eliminare.", Color.ORANGE);
            return;
        }
        // Step 2: Asks confirmation.
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Sei sicuro di voler eliminare '" + selectedBene.getName() + "' (ID: " + selectedBene.getId() + ")?",
                "Conferma Eliminazione",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        // Step 3: Confirm the operation.
        if (confirmation == JOptionPane.YES_OPTION) {
            updateStatusMessage("Eliminazione di '" + selectedBene.getName() + "' in corso...", Color.BLUE);
            // Block input controls to avoid multiple submissions during the operation
            toggleInputControls(false);
            // Use SwingWorker for background deletion process
            new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws ConnectionInterruptionException {
                    // Step 4: Delete the cultural choice.
                    return service.deleteBeniCulturale(selectedBene.getId());
                }
                @Override
                protected void done() {
                    toggleInputControls(true); // Re-enable controls after operation completes
                    try {
                        Boolean success = get(); // Get the result from doInBackground
                        if (success) {
                            // Exit conditions: The system shall notify the successful elimination.
                            listModel.removeElement(selectedBene); // Remove from GUI list
                            culturalObjectList.clearSelection(); // Clear selection
                            updateStatusMessage("Bene culturale '" + selectedBene.getName() + "' eliminato con successo.", Color.GREEN);
                        } else {
                            updateStatusMessage("Errore: Bene culturale '" + selectedBene.getName() + "' non trovato o già eliminato.", Color.RED);
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        // Handle exceptions (e.g., ConnectionInterruptionException wrapped in ExecutionException)
                        // Exit conditions: Interruption of connection to the server ETOUR.
                        System.err.println("Error deleting cultural object: " + ex.getMessage());
                        // Extract the most relevant error message
                        Throwable cause = ex.getCause(); // Get the root cause of the exception
                        String errorMessage = (cause != null) ? cause.getMessage() : ex.getMessage();
                        updateStatusMessage("Fallito l'eliminazione: " + errorMessage, Color.RED);
                    }
                    // Ensure the delete button's enabled state is correct after operation,
                    // accounting for list model changes and potential empty lists.
                    deleteButton.setEnabled(culturalObjectList.getSelectedIndex() != -1 && !listModel.isEmpty());
                }
            }.execute(); // Execute the SwingWorker
        } else {
            // Exit conditions: Operator Agency cancels the operation.
            updateStatusMessage("Operazione di eliminazione annullata dall'operatore.", Color.BLACK);
        }
    }
    /**
     * Updates the status bar with a given message and color.
     *
     * @param message The message to display.
     * @param color The color of the message text.
     */
    private void updateStatusMessage(String message, Color color) {
        statusBar.setForeground(color);
        statusBar.setText(message);
    }
    /**
     * Enables or disables relevant input controls (e.g., the delete button).
     * This is used to prevent multiple submissions or interactions during an ongoing operation.
     *
     * @param enable If true, controls are enabled; otherwise, they are disabled.
     */
    private void toggleInputControls(boolean enable) {
        // Only enable the delete button if enable is true AND there is a selection
        // or if the list becomes empty, the button should remain disabled
        deleteButton.setEnabled(enable && culturalObjectList.getSelectedIndex() != -1 && !listModel.isEmpty());
        culturalObjectList.setEnabled(enable);
    }
}