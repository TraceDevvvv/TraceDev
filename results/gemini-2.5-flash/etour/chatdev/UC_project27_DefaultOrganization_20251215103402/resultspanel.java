/*
A JPanel component responsible for displaying search results or error messages to the user.
It includes a scrollable text area for convenient viewing of potentially long outputs.
*/
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class ResultsPanel extends JPanel {
    // Text area to display search results or error messages
    private JTextArea resultsArea;
    // Scroll pane to make the text area scrollable
    private JScrollPane scrollPane;
    /**
     * Constructs a ResultsPanel.
     * Sets up the layout and initializes the scrollable text area.
     */
    public ResultsPanel() {
        // Use BorderLayout for organizing components
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Search Results")); // Add a titled border
        // Initialize the results text area
        resultsArea = new JTextArea();
        resultsArea.setEditable(false); // Make it read-only
        resultsArea.setLineWrap(true);   // Wrap long lines
        resultsArea.setWrapStyleWord(true); // Wrap at word boundaries
        resultsArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Consistent font
        // Add the text area to a scroll pane
        scrollPane = new JScrollPane(resultsArea);
        // Set preferred size for the scroll pane to help with initial layout
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Add the scroll pane to the panel
        add(scrollPane, BorderLayout.CENTER);
    }
    /**
     * Displays a list of found sites in the results area.
     * Each site's toString() method is used for formatting.
     * @param results The list of Site objects to display.
     */
    public void displayResults(List<Site> results) {
        clearResults(); // Clear previous results
        if (results.isEmpty()) {
            resultsArea.setText("No sites found matching your query.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Found ").append(results.size()).append(" site(s):\n\n");
            for (Site site : results) {
                sb.append(site.toString()).append("--------------------\n");
            }
            resultsArea.setText(sb.toString());
            resultsArea.setCaretPosition(0); // Scroll to the top
        }
    }
    /**
     * Displays an error message in the results area.
     * @param errorMessage The error message to display.
     */
    public void displayError(String errorMessage) {
        clearResults(); // Clear previous content
        resultsArea.setForeground(Color.RED); // Set text color to red for errors
        resultsArea.setText("ERROR: " + errorMessage);
        resultsArea.setCaretPosition(0); // Scroll to the top
    }
    /**
     * Clears the content of the results area and resets its text color.
     */
    public void clearResults() {
        resultsArea.setText("");
        resultsArea.setForeground(Color.BLACK); // Reset text color
    }
}