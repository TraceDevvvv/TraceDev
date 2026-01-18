'''
DOCSTRING:
This is the main application class for the VISUALIZZASCHEDAPUNTODIRISTORO use case.
It creates a JFrame, which includes:
1. A JComboBox populated with a list of PuntoDiRistoro (simulating results from RicercaPuntoDiRistoro).
2. A "View Details" JButton to trigger the display of the selected item's details.
3. A DettaglioPuntoDiRistoroUI panel to show the details.
4. A JCheckBox to simulate server connection errors.
The application allows an "Agency Operator" to view details of a selected point of rest.
It handles user selection, data display, and simulates connection interruptions to the ETOUR server.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
public class MainApplication extends JFrame {
    // Service to handle data retrieval
    private PuntoDiRistoroService puntoDiRistoroService;
    // UI Components
    private JComboBox<PuntoDiRistoro> puntoDiRistoroComboBox;
    private JButton viewDetailsButton;
    private DettaglioPuntoDiRistoroUI dettaglioPanel;
    private JLabel statusLabel;
    private JCheckBox simulateErrorCheckBox;
    public MainApplication() {
        super("ChatDev - Visualizza Scheda Punto di Ristoro"); // Window title
        puntoDiRistoroService = new PuntoDiRistoroService();
        // Configure the main window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        // Set up the main content panel with a BorderLayout
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        setContentPane(contentPane);
        // --- Top Panel for selection and controls ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        // Load points of rest for the combo box
        puntoDiRistoroComboBox = new JComboBox<>();
        loadPuntiDiRistoroIntoComboBox(); // Populate the combo box on startup
        topPanel.add(new JLabel("Seleziona Punto Ristoro:"));
        topPanel.add(puntoDiRistoroComboBox);
        viewDetailsButton = new JButton("Visualizza Dettagli");
        topPanel.add(viewDetailsButton);
        // Checkbox to simulate server error
        simulateErrorCheckBox = new JCheckBox("Simula Errore Server ETOUR");
        topPanel.add(simulateErrorCheckBox);
        contentPane.add(topPanel, BorderLayout.NORTH);
        // --- Center Panel for displaying details ---
        dettaglioPanel = new DettaglioPuntoDiRistoroUI();
        contentPane.add(dettaglioPanel, BorderLayout.CENTER);
        // --- Bottom Panel for status messages ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Pronto.");
        statusLabel.setForeground(Color.BLUE); // Default color
        bottomPanel.add(statusLabel);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
        // --- Action Listeners ---
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSelectedPuntoDiRistoroDetails();
            }
        });
        simulateErrorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSimulatingError = simulateErrorCheckBox.isSelected();
                puntoDiRistoroService.setSimulateConnectionError(isSimulatingError);
                if (isSimulatingError) {
                    dettaglioPanel.displayPuntoDiRistoro(null); // Clear details when error is simulated
                    statusLabel.setText("Stato: Simulazione errore server ETOUR ATTIVA.");
                    statusLabel.setForeground(Color.ORANGE);
                } else {
                    statusLabel.setText("Stato: Simulazione errore server ETOUR DISATTIVA. Pronto.");
                    statusLabel.setForeground(Color.BLUE);
                    // Attempt to reload details for the currently selected item when the simulated error is turned off.
                    // This improves user experience by automatically updating the display.
                    if (puntoDiRistoroComboBox.getSelectedItem() != null) {
                        viewSelectedPuntoDiRistoroDetails();
                    } else {
                        // If no item is selected, ensure the details panel is cleared.
                        dettaglioPanel.displayPuntoDiRistoro(null);
                    }
                }
            }
        });
        // Initially display details of the first item after loading
        if (puntoDiRistoroComboBox.getItemCount() > 0) {
            viewSelectedPuntoDiRistoroDetails();
        }
    }
    /**
     * Loads the list of PuntoDiRistoro objects from the service into the JComboBox.
     * Handles potential ServerConnectionException during initial load.
     */
    private void loadPuntiDiRistoroIntoComboBox() {
        try {
            List<PuntoDiRistoro> punti = puntoDiRistoroService.getAllPuntiDiRistoro();
            puntoDiRistoroComboBox.removeAllItems(); // Clear existing items
            if (punti.isEmpty()) {
                statusLabel.setText("Stato: Nessun Punto di Ristoro trovato.");
                statusLabel.setForeground(Color.RED);
                viewDetailsButton.setEnabled(false); // Disable button if no items
            } else {
                for (PuntoDiRistoro punto : punti) {
                    puntoDiRistoroComboBox.addItem(punto);
                }
                statusLabel.setText("Stato: Elenco punti di ristoro caricato.");
                statusLabel.setForeground(Color.BLUE);
                viewDetailsButton.setEnabled(true); // Enable button if items are loaded
                if (puntoDiRistoroComboBox.getItemCount() > 0) {
                    puntoDiRistoroComboBox.setSelectedIndex(0); // Select first item by default
                }
            }
        } catch (PuntoDiRistoroService.ServerConnectionException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Errore di Connessione", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Errore: " + ex.getMessage());
            statusLabel.setForeground(Color.RED);
            puntoDiRistoroComboBox.removeAllItems(); // Clear items on error
            viewDetailsButton.setEnabled(false); // Disable button on error
            dettaglioPanel.displayPuntoDiRistoro(null); // Clear displayed details
        }
    }
    /**
     * Retrieves and displays the details of the currently selected PuntoDiRistoro.
     * This method directly implements the "Upload data to a selected restaurant"
     * and "The system displays the details of the selected point of rest" parts
     * of the use case flow.
     * It also handles the "Interruption of the connection to the server ETOUR" exit condition.
     */
    private void viewSelectedPuntoDiRistoroDetails() {
        PuntoDiRistoro selectedPunto = (PuntoDiRistoro) puntoDiRistoroComboBox.getSelectedItem();
        if (selectedPunto == null) {
            statusLabel.setText("Attenzione: Nessun Punto di Ristoro selezionato.");
            statusLabel.setForeground(Color.ORANGE);
            dettaglioPanel.displayPuntoDiRistoro(null); // Clear details if nothing is selected
            return;
        }
        try {
            // Attempt to retrieve full details from the service (simulating data upload)
            Optional<PuntoDiRistoro> fullDetails = puntoDiRistoroService.getPuntoDiRistoroById(selectedPunto.getId());
            if (fullDetails.isPresent()) {
                dettaglioPanel.displayPuntoDiRistoro(fullDetails.get());
                statusLabel.setText("Stato: Dettagli di '" + selectedPunto.getName() + "' caricati con successo.");
                statusLabel.setForeground(Color.BLUE);
            } else {
                // This case should ideally not happen if the combo box is populated correctly
                // but handles a scenario where an ID exists but detail fetching fails.
                statusLabel.setText("Errore: Dettagli per '" + selectedPunto.getName() + "' non trovati.");
                statusLabel.setForeground(Color.RED);
                dettaglioPanel.displayPuntoDiRistoro(null); // Clear details
            }
        } catch (PuntoDiRistoroService.ServerConnectionException ex) {
            // Handle the simulated server connection interruption
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Errore di Connessione ETOUR", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Errore ETOUR: " + ex.getMessage());
            statusLabel.setForeground(Color.RED);
            dettaglioPanel.displayPuntoDiRistoro(null); // Clear displayed details on error
        }
    }
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Set the Look and Feel for a more modern appearance (optional)
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    // Fallback to default L&F if system L&F isn't available
                    System.err.println("Could not set System Look and Feel: " + e.getMessage());
                }
                new MainApplication().setVisible(true);
            }
        });
    }
}