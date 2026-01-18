'''
A JPanel that displays a list of PuntoDiRistoro objects and allows selection for editing.
It serves as the initial view for the agency operator (Use Case Step 1).
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class PuntoDiRistoroListPanel extends JPanel {
    /**
     * Interface to communicate selection and error events from this panel
     * to the main application frame (or a higher-level controller).
     */
    public interface PuntoDiRistoroEditListener {
        /**
         * Called when a PuntoDiRistoro is selected for editing.
         * @param punto The PuntoDiRistoro object to be edited.
         */
        void onEditPuntoDiRistoro(PuntoDiRistoro punto);
        /**
         * Called when an error occurs during an operation, e.g., loading data.
         * @param message The error message to display.
         */
        void onError(String message);
        /**
         * Called when the user requests a refresh of the list data.
         */
        void onRefreshRequest();
    }
    private final PuntoDiRistoroService service;
    private final PuntoDiRistoroEditListener listener;
    private JList<PuntoDiRistoro> puntoList;
    private DefaultListModel<PuntoDiRistoro> listModel;
    private JButton editButton;
    private JButton refreshButton;
    /**
     * Constructs a PuntoDiRistoroListPanel.
     *
     * @param service The service responsible for fetching and managing punto diRISToro data.
     * @param listener The listener to notify about user actions (edit, refresh) and errors.
     */
    public PuntoDiRistoroListPanel(PuntoDiRistoroService service, PuntoDiRistoroEditListener listener) {
        this.service = service;
        this.listener = listener;
        initComponents(); // Initialize the UI components
        loadPuntiDiRistoro(); // Load initial data into the list
    }
    /**
     * Initializes and lays out the UI components of the panel.
     */
    private void initComponents() {
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with gaps
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        // Title label for the panel
        JLabel titleLabel = new JLabel("Points of Rest Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        // List component to display PuntoDiRistoro objects
        listModel = new DefaultListModel<>();
        puntoList = new JList<>(listModel);
        puntoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one item to be selected
        puntoList.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Add a border for visual separation
        puntoList.setFixedCellHeight(25); // Improve visual consistency for list items
        JScrollPane scrollPane = new JScrollPane(puntoList); // Add scroll capability if list items exceed visible area
        add(scrollPane, BorderLayout.CENTER);
        // Panel for action buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Right-aligned buttons with horizontal gap
        refreshButton = new JButton("Refresh List");
        editButton = new JButton("Edit Selected");
        buttonPanel.add(refreshButton);
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // --- Action Listeners ---
        // ActionListener for the Edit button
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PuntoDiRistoro selectedPunto = puntoList.getSelectedValue();
                if (selectedPunto != null) {
                    // Use case Step 1: "selects an active and functional data changes".
                    // This implies that the selected item itself should be active and functional
                    // at the moment of selection for editing.
                    if (selectedPunto.isActive() && selectedPunto.isFunctional()) {
                        listener.onEditPuntoDiRistoro(selectedPunto);
                    } else {
                        // If the selected item is not active or not functional, display an error.
                        ErroredDialog.display(PuntoDiRistoroListPanel.this,
                                "Only active and functional Points of Rest can be edited. " +
                                selectedPunto.getName() + " (ID: " + selectedPunto.getId() + ") is not active or not functional.");
                    }
                } else {
                    // If no item is selected, inform the user.
                    ErroredDialog.display(PuntoDiRistoroListPanel.this, "Please select a Point of Rest to edit.");
                }
            }
        });
        // ActionListener for the Refresh button
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delegate the refresh request to the main application frame's listener.
                // This allows the main application to control the full refresh cycle or UI transitions.
                listener.onRefreshRequest();
            }
        });
        // ListSelectionListener to enable/disable the edit button based on selection state.
        puntoList.addListSelectionListener(e -> {
            // Only respond when the selection change has finished adjusting
            if (!e.getValueIsAdjusting()) {
                // The edit button is enabled only if an item is selected in the list.
                editButton.setEnabled(puntoList.getSelectedValue() != null);
            }
        });
        editButton.setEnabled(false); // Initially disable the edit button as nothing is selected.
    }
    /**
     * Loads the points of rest from the service and populates the JList.
     * This method handles potential ServiceExceptions during data retrieval.
     */
    public void loadPuntiDiRistoro() {
        listModel.clear(); // Clear all existing items from the list model before loading new ones
        try {
            List<PuntoDiRistoro> punti = service.getAllPuntiDiRistoro(); // Fetch data from the service
            if (punti.isEmpty()) {
                // If no points of rest are found, display an informational error.
                ErroredDialog.display(this, "No points of rest found, or the list is empty.");
                // No return here, as we still want to make sure the edit button is disabled.
            } else {
                // Add each retrieved PuntoDiRistoro to the list model.
                for (PuntoDiRistoro punto : punti) {
                    listModel.addElement(punto);
                }
            }
            editButton.setEnabled(false); // Ensure edit button is disabled after refresh, as no item is selected by default.
        } catch (ServiceException ex) {
            // If a service error occurs (e.g., connection interruption), notify the listener.
            listener.onError("Failed to load points of rest: " + ex.getMessage());
        }
    }
}