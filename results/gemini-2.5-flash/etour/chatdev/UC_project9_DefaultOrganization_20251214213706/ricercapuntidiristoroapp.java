'''
Main application class for the "RICERCAPUNTIDIRISTORO" use case.
This class extends {@link JFrame} and serves as the main window for the application.
It orchestrates the user interface components (search form, results list, status bar)
and interactions with the business logic layer ({@link RestPointService}).
It implements {@link SearchFormPanel.SearchActionListener} to handle search requests from the UI.
'''
package com.chatdev.ricercapuntidiristoro;
import com.chatdev.ricercapuntidiristoro.model.RestPoint;
import com.chatdev.ricercapuntidiristoro.service.RestPointService;
import com.chatdev.ricercapuntidiristoro.ui.RestPointListPanel;
import com.chatdev.ricercapuntidiristoro.ui.SearchFormPanel;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
/**
 * Main application class for the "RICERCAPUNTIDIRISTORO" use case.
 * This class extends {@link JFrame} and serves as the main window for the application.
 * It orchestrates the user interface components (search form, results list, status bar)
 * and interactions with the business logic layer ({@link RestPointService}).
 * It implements {@link SearchFormPanel.SearchActionListener} to handle search requests from the UI.
 */
public class RicercaPuntiDiRistoroApp extends JFrame implements SearchFormPanel.SearchActionListener {
    private RestPointService restPointService;
    private SearchFormPanel searchFormPanel;
    private RestPointListPanel restPointListPanel;
    private JLabel statusBar;
    // Quality requirement: The system performs the operation in 15 seconds maximum.
    private static final long MAX_OPERATION_TIME_MS = 15000;
    /**
     * Constructor for the main application window.
     * Initializes the service layer, constructs all GUI components, and sets up the frame layout.
     */
    public RicercaPuntiDiRistoroApp() {
        super("Ricerca Punti di Ristoro ETOUR"); // Set the window title
        restPointService = new RestPointService(); // Initialize the business logic service
        // Configure the main JFrame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on window close
        setSize(800, 600); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout()); // Use BorderLayout for overall layout management
        // Initialize UI components:
        // 1. Search form panel, passing 'this' as the callback listener for search events.
        searchFormPanel = new SearchFormPanel(this);
        // 2. Panel to display the list of rest point results.
        restPointListPanel = new RestPointListPanel();
        // 3. Status bar at the bottom to provide user feedback.
        statusBar = new JLabel("Pronto per la ricerca. Inserisci i parametri e clicca 'Cerca'.");
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder()); // Add a recessed border to the status bar
        // Add the UI components to the JFrame according to BorderLayout regions.
        add(searchFormPanel, BorderLayout.NORTH); // Search form at the top
        add(restPointListPanel, BorderLayout.CENTER); // Results list in the center, taking most space
        add(statusBar, BorderLayout.SOUTH); // Status bar at the bottom
    }
    /**
     * This method is the callback handler for the search button in the {@link SearchFormPanel}.
     * It is triggered when the user submits a search request.
     * The actual search operation is performed on a background thread using {@link SwingWorker}
     * to keep the GUI responsive and prevent freezing during potentially long operations.
     *
     * @param name     The search criterion for the rest point's name.
     * @param location The search criterion for the rest point's location.
     * @param type     The search criterion for the rest point's type.
     */
    @Override
    public void onSearch(String name, String location, String type) {
        statusBar.setText("Ricerca in corso... attendere prego."); // Update status bar to indicate ongoing search
        searchFormPanel.setFormEnabled(false); // Disable form to prevent multiple concurrent searches or modifications
        restPointListPanel.clearResults(); // Clear previous results immediately for a fresh start
        // Create and execute a SwingWorker to perform the search in a background thread.
        // This ensures the Event Dispatch Thread (EDT) remains free for UI updates,
        // preventing the application from freezing during the potentially long search operation.
        new SwingWorker<List<RestPoint>, Void>() {
            private long startTime; // To track the duration of the search operation for performance checks
            /**
             * This method runs on a background thread, off the Event Dispatch Thread (EDT).
             * It calls the service layer to perform the search, which includes simulated delay
             * and potential error conditions like connection failure.
             *
             * @return A list of {@link RestPoint} objects matching the criteria.
             * @throws ETOURConnectionException if a simulated connection error occurs.
             * @throws InterruptedException     if the worker thread is interrupted (e.g., during Thread.sleep).
             */
            @Override
            protected List<RestPoint> doInBackground() throws ETOURConnectionException, InterruptedException {
                startTime = System.currentTimeMillis(); // Record start time for performance check
                return restPointService.searchRestPoints(name, location, type);
            }
            /**
             * This method runs back on the Event Dispatch Thread (EDT) after doInBackground() completes.
             * It updates the UI with the search results or displays appropriate error messages,
             * ensuring all GUI modifications are done safely on the EDT.
             */
            @Override
            protected void done() {
                long duration = System.currentTimeMillis() - startTime; // Calculate operation duration
                searchFormPanel.setFormEnabled(true); // Re-enable the search form for new input now that the operation is complete
                try {
                    List<RestPoint> results = get(); // Retrieve the results from doInBackground()
                    restPointListPanel.updateResults(results); // Update the results list UI component with fetched data
                    // Update status bar based on search outcome (found results or none)
                    if (results.isEmpty()) {
                        statusBar.setText("Nessun punto di ristoro trovato per i criteri specificati. Tempo impiegato: " + duration + " ms.");
                    } else {
                        statusBar.setText("Ricerca completata. Trovati " + results.size() + " punti di ristoro. Tempo impiegato: " + duration + " ms.");
                    }
                    // Check against the quality requirement (15 seconds maximum).
                    // Display a warning if the operation exceeded the maximum allowed time.
                    if (duration > MAX_OPERATION_TIME_MS) {
                        JOptionPane.showMessageDialog(RicercaPuntiDiRistoroApp.this,
                                String.format("Attenzione: L'operazione ha superato il tempo massimo di %d secondi. Tempo impiegato: %d ms.",
                                        MAX_OPERATION_TIME_MS / 1000, duration),
                                "Avviso Prestazioni", // Dialog title
                                JOptionPane.WARNING_MESSAGE); // Icon type for warning
                    }
                } catch (InterruptedException ex) {
                    // This block handles cases where the background task was interrupted.
                    statusBar.setText("Ricerca interrotta: " + ex.getMessage());
                    JOptionPane.showMessageDialog(RicercaPuntiDiRistoroApp.this,
                            "La ricerca è stata interrotta: " + ex.getMessage(),
                            "Errore Interno",
                            JOptionPane.ERROR_MESSAGE);
                } catch (ExecutionException ex) {
                    // This block handles exceptions thrown by the doInBackground() method.
                    // The actual exception is wrapped inside ExecutionException.
                    Throwable cause = ex.getCause(); // Get the root cause of the error for specific handling
                    if (cause instanceof ETOURConnectionException) {
                        // Specific handling for our custom ETOUR connection errors
                        statusBar.setText("Errore di connessione: " + cause.getMessage());
                        JOptionPane.showMessageDialog(RicercaPuntiDiRistoroApp.this,
                                "Si è verificato un errore di connessione con il server ETOUR: " + cause.getMessage(),
                                "Errore di Connessione",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        // General error handling for other unexpected exceptions that might occur during background processing
                        statusBar.setText("Errore durante la ricerca: " + cause.getMessage());
                        JOptionPane.showMessageDialog(RicercaPuntiDiRistoroApp.this,
                                "Si è verificato un errore inatteso durante la ricerca: " + cause.getMessage(),
                                "Errore Generico",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }.execute(); // Start the SwingWorker, which triggers doInBackground() on a new thread
    }
    /**
     * Main method to start the "RICERCAPUNTIDIRISTORO" application.
     * It ensures that the GUI creation and updates are performed on the
     * Event Dispatch Thread (EDT) for thread safety in Swing applications.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread (EDT):
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            RicercaPuntiDiRistoroApp app = new RicercaPuntiDiRistoroApp();
            app.setVisible(true); // Make the main application window visible
        });
    }
}