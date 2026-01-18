'''
JPanel component for displaying a list of {@link RestPoint} objects.
This panel is typically used to show the results of a search operation.
It uses a JList embedded within a JScrollPane for scrollability.
'''
package com.chatdev.ricercapuntidiristoro.ui;
import com.chatdev.ricercapuntidiristoro.model.RestPoint;
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * JPanel component for displaying a list of {@link RestPoint} objects.
 * This panel is typically used to show the results of a search operation.
 * It uses a JList embedded within a JScrollPane for scrollability.
 */
public class RestPointListPanel extends JPanel {
    private DefaultListModel<RestPoint> listModel;
    private JList<RestPoint> restPointList;
    /**
     * Constructs a new RestPointListPanel.
     * Initializes the list model, JList, and arranges them within a JScrollPane.
     */
    public RestPointListPanel() {
        // Use BorderLayout to easily place the scroll pane in the center.
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Risultati Ricerca")); // Add a border with a title
        // DefaultListModel manages the data for the JList.
        listModel = new DefaultListModel<>();
        restPointList = new JList<>(listModel);
        restPointList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one item can be selected at a time
        restPointList.setLayoutOrientation(JList.VERTICAL); // Items are laid out vertically
        restPointList.setVisibleRowCount(-1); // Instructs the JList to display all items if possible
        // Wrap the JList in a JScrollPane to provide scrolling capabilities if items exceed visible area.
        JScrollPane scrollPane = new JScrollPane(restPointList);
        add(scrollPane, BorderLayout.CENTER);
    }
    /**
     * Updates the displayed list with a new set of search results.
     * Any existing items in the list will be cleared before adding the new results.
     * If the results list is null or empty, the list will be cleared.
     *
     * @param results The list of {@link RestPoint} objects to display. Can be null or empty.
     */
    public void updateResults(List<RestPoint> results) {
        listModel.clear(); // Clear all existing items from the list
        if (results != null && !results.isEmpty()) {
            for (RestPoint rp : results) {
                listModel.addElement(rp); // Add each RestPoint to the list model
            }
        }
        // If results is null or empty, the list will simply be cleared, showing an empty list.
    }
    /**
     * Clears all items currently displayed in the list.
     * This is useful before starting a new search or when no results are available.
     */
    public void clearResults() {
        listModel.clear();
    }
}