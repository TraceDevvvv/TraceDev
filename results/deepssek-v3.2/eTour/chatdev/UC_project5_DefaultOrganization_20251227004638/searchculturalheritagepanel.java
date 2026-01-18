"""
Panel for searching and selecting cultural heritage items.
Implements step 1 of the use case: View list of cultural goods.
"""
/**
 * Panel for searching and selecting cultural heritage items.
 */
class SearchCulturalHeritagePanel {
    private ETOURServerConnection serverConnection;
    public SearchCulturalHeritagePanel(ETOURServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }
    /**
     * Displays search results and allows user to select an item.
     * @return Selected CulturalHeritage object, or null if none selected
     * @throws ServerConnectionException if connection is interrupted
     */
    public CulturalHeritage displayAndSelect() throws ServerConnectionException {
        // Simulate loading cultural goods from server
        List<CulturalHeritage> heritageList = serverConnection.searchCulturalHeritage();
        if (heritageList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No cultural heritage items found.",
                "Search Results", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        // Display list in a dialog for selection
        return showSelectionDialog(heritageList);
    }
    /**
     * Shows dialog with cultural heritage items for user selection.
     */
    private CulturalHeritage showSelectionDialog(List<CulturalHeritage> items) {
        // Create array of display names for the list
        String[] itemNames = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            itemNames[i] = items.get(i).getName() + " (" + items.get(i).getType() + ")";
        }
        // Show selection dialog
        String selection = (String) JOptionPane.showInputDialog(null,
            "Select a cultural heritage item to view details:",
            "Cultural Heritage List",
            JOptionPane.QUESTION_MESSAGE,
            null,
            itemNames,
            itemNames[0]);
        // Find and return selected item
        if (selection != null) {
            for (CulturalHeritage item : items) {
                if ((item.getName() + " (" + item.getType() + ")").equals(selection)) {
                    return item;
                }
            }
        }
        return null;
    }
}