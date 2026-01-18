/**
 * GUI panel for displaying search results, messages, and error notifications.
 * This class directly relates to "Exit conditions: The system displays a list of sites that meet the search criteria."
 * and "Interruption of the connection to the server ETOUR."
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class ResultPanel extends JPanel {
    private JTextArea resultArea;
    private JScrollPane scrollPane;
    /**
     * Constructs a new ResultPanel.
     * Initializes the UI components for displaying search results and messages.
     */
    public ResultPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Search Results"));
        resultArea = new JTextArea();
        resultArea.setEditable(false); // Make it read-only
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true); // Wrap at word boundaries
        scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);
    }
    /**
     * Displays a general message in the result area, replacing previous content.
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            resultArea.setText(message + "\n");
            resultArea.setForeground(Color.BLACK); // Reset color for general messages
        });
    }
    /**
     * Displays an error message in the result area, replacing previous content.
     * The error message is typically shown in red.
     * @param errorMessage The error message to display.
     */
    public void displayError(String errorMessage) {
        SwingUtilities.invokeLater(() -> {
            resultArea.setText("ERROR: " + errorMessage + "\n");
            resultArea.setForeground(Color.RED); // Set color to red for errors
        });
    }
    /**
     * Displays a list of cultural sites in the result area.
     * Each site is displayed on a new line using its toString() representation.
     * If the list is empty, a message indicating no results found is displayed.
     * @param results The list of CulturalSite objects to display.
     */
    public void displayResults(List<CulturalSite> results) {
        SwingUtilities.invokeLater(() -> {
            resultArea.setText(""); // Clear previous results
            resultArea.setForeground(Color.BLACK); // Reset color
            if (results == null || results.isEmpty()) {
                resultArea.append("No cultural sites found matching your criteria.\n");
            } else {
                resultArea.append("Found " + results.size() + " cultural sites:\n\n");
                for (CulturalSite site : results) {
                    resultArea.append("- " + site.toString() + "\n");
                }
            }
            // Scroll to the top to show the beginning of results
            resultArea.setCaretPosition(0);
        });
    }
}