package gui;
import model.SearchCriteria;
import model.Site;
import service.SearchService;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
/*
 * The main JFrame for the Advanced Search Application.
 * This class orchestrates the different GUI panels (Personal Area, Search Form, Results)
 * using a CardLayout and handles the interaction between them, including initiating
 * the search service in a background thread.
 */
public class AdvancedSearchApp extends JFrame implements SearchSubmitListener {
    // Panel names for CardLayout navigation
    public static final String PERSONAL_AREA_PANEL = "PersonalArea";
    public static final String SEARCH_FORM_PANEL = "SearchForm";
    public static final String RESULTS_PANEL = "Results";
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final PersonalAreaPanel personalAreaPanel;
    private final AdvancedSearchFormPanel searchFormPanel;
    private final SearchResultsPanel resultsPanel;
    private final SearchService searchService;
    /**
     * Constructs the main application window.
     * Sets up the JFrame, initializes all GUI panels, and manages their layout
     * and interactions using CardLayout.
     */
    public AdvancedSearchApp() {
        super("ETOUR Advanced Search"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        searchService = new SearchService(); // Initialize the search service
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout); // Panel to hold other panels, using CardLayout
        // Initialize panels and pass necessary callbacks for navigation
        personalAreaPanel = new PersonalAreaPanel(this::showPanel);
        searchFormPanel = new AdvancedSearchFormPanel(this); // This class implements SearchSubmitListener
        resultsPanel = new SearchResultsPanel(this::showPanel);
        // Add panels to the cardPanel with unique names
        cardPanel.add(personalAreaPanel, PERSONAL_AREA_PANEL);
        cardPanel.add(searchFormPanel, SEARCH_FORM_PANEL);
        cardPanel.add(resultsPanel, RESULTS_PANEL);
        add(cardPanel); // Add the cardPanel to the JFrame
        showPanel(PERSONAL_AREA_PANEL); // Start by showing the personal area panel
    }
    /**
     * Switches the currently visible panel in the CardLayout.
     *
     * @param panelName The name of the panel to show (e.g., PERSONAL_AREA_PANEL).
     */
    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
        // Only validate and repaint the window ancestor to ensure correct display
        // after switching. Calling pack() repeatedly is generally not advisable
        // when the JFrame's size is explicitly set.
        SwingUtilities.getWindowAncestor(cardPanel).validate();
        SwingUtilities.getWindowAncestor(cardPanel).repaint();
    }
    /**
     * Implements the SearchSubmitListener interface.
     * This method is called by AdvancedSearchFormPanel when the user submits a search.
     * It uses a SwingWorker to perform the search in a background thread to keep the GUI responsive.
     *
     * @param criteria The SearchCriteria object from the form.
     */
    @Override
    public void onSearchSubmit(SearchCriteria criteria) {
        resultsPanel.displayResults(null, null); // Clear previous results and warnings
        resultsPanel.updateStatusMessage("Searching..."); // Show a searching message
        showPanel(RESULTS_PANEL); // Switch to results panel to show progress
        // Use SwingWorker to perform the search operation in a background thread
        // This prevents the GUI from freezing during the potentially long search operation.
        new SwingWorker<List<Site>, Void>() {
            private long startTime;
            @Override
            protected List<Site> doInBackground() throws Exception {
                startTime = System.currentTimeMillis(); // Record start time for performance check
                return searchService.performAdvancedSearch(criteria); // Execute the search service
            }
            @Override
            protected void done() {
                try {
                    List<Site> results = get(); // Get the results from doInBackground()
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    String performanceWarning = null;
                    if (duration > 15000) { // Check if search exceeded 15 seconds
                         performanceWarning = "\nNote: Search completed in " + (double)duration / 1000 + " seconds, which is longer than the recommended 15 seconds.";
                    }
                    if (results.isEmpty()) {
                        resultsPanel.updateStatusMessage(
                            "No sites found matching your criteria.\n" +
                            "Consider broadening your search parameters." + (performanceWarning != null ? performanceWarning : "")
                            // If there's a performance warning, append it here as a status note.
                        );
                    } else {
                        resultsPanel.displayResults(results, performanceWarning); // Display successful results with optional warning
                    }
                    System.out.println("Search operation completed in " + (double)duration / 1000 + " seconds.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    resultsPanel.displayErrorMessage("Search was interrupted: " + e.getMessage());
                } catch (CancellationException e) {
                    resultsPanel.displayErrorMessage("Search was cancelled.");
                } catch (ExecutionException e) {
                    // Handle exceptions thrown from doInBackground()
                    // The underlying cause is wrapped in ExecutionException
                    resultsPanel.displayErrorMessage("Search failed: " + e.getCause().getMessage());
                    System.err.println("Search execution error: " + e.getCause());
                    e.getCause().printStackTrace();
                } finally {
                    // This block will always execute after results or error
                    // You might want to re-enable UI elements here if they were disabled during search.
                }
            }
        }.execute(); // Start the SwingWorker thread
    }
}