package gui;
import model.Site;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
/*
 * Displays the results of the advanced search or an error message.
 * Provides a button to return to the search form.
 */
public class SearchResultsPanel extends JPanel {
    private final JTextArea resultsTextArea;
    private final Consumer<String> showPanelCallback;
    /**
     * Constructs a new SearchResultsPanel.
     *
     * @param showPanelCallback A callback function to notify the main application
     *                          to switch to a different panel (e.g., back to search form).
     */
    public SearchResultsPanel(Consumer<String> showPanelCallback) {
        this.showPanelCallback = showPanelCallback;
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for spacing
        // Text area to display results
        resultsTextArea = new JTextArea();
        resultsTextArea.setEditable(false); // Make it read-only
        resultsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Improve readability
        resultsTextArea.setLineWrap(true); // Enable line wrapping
        resultsTextArea.setWrapStyleWord(true); // Wrap at word boundaries
        // Add a scroll pane to the text area in case results are long
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Search Results / Status"));
        add(scrollPane, BorderLayout.CENTER);
        // Back button to return to the search form or personal area
        JButton backButton = new JButton("Back to Search Form");
        backButton.addActionListener(e -> {
            // Invoke the callback to show the advanced search form panel
            showPanelCallback.accept(AdvancedSearchApp.SEARCH_FORM_PANEL);
        });
        JPanel buttonPanel = new JPanel(); // Panel to hold the back button
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom
    }
    /**
     * Displays a list of found sites in the text area.
     * If the list is null or empty, it indicates no results.
     * Optionally appends a warning message.
     *
     * @param sites A list of Site objects to display.
     * @param warningMessage An optional warning message to display after results (e.g., performance notes). Can be null.
     */
    public void displayResults(List<Site> sites, String warningMessage) {
        StringBuilder sb = new StringBuilder();
        if (sites == null || sites.isEmpty()) {
            sb.append("No results to display. Please refine your search.");
        } else {
            sb.append("Found ").append(sites.size()).append(" site(s):\n\n");
            for (int i = 0; i < sites.size(); i++) {
                sb.append("--- Site ").append(i + 1).append(" ---\n");
                sb.append(sites.get(i).toString()).append("\n");
            }
        }
        if (warningMessage != null && !warningMessage.trim().isEmpty()) {
            sb.append("\n").append(warningMessage.trim());
        }
        resultsTextArea.setText(sb.toString());
        resultsTextArea.setCaretPosition(0); // Scroll to the top
    }
    /**
     * Displays a general status message in the text area.
     * This is for non-critical informational messages like "Searching..." or "No results".
     *
     * @param message The status message to display.
     */
    public void updateStatusMessage(String message) {
        resultsTextArea.setText(message);
        resultsTextArea.setCaretPosition(0); // Scroll to the top
    }
    /**
     * Displays a critical error message in the text area,
     * including a standard prefix and troubleshooting suggestion.
     *
     * @param errorMessage The specific error message to display to the user.
     */
    public void displayErrorMessage(String errorMessage) {
        resultsTextArea.setText("ERROR: " + errorMessage + "\n\nPlease try again or check your connection.");
        resultsTextArea.setCaretPosition(0); // Scroll to the top
    }
}