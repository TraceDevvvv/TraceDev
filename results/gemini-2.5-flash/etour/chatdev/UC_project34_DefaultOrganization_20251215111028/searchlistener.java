/**
 * An interface for a listener that wants to be notified when a search action is requested.
 * This decouples the {@code SearchPanel} (which collects user input) from the main application logic
 * that performs the search.
 */
public interface SearchListener {
    /**
     * Called when a user submits a search request from the UI.
     * @param criteria The SearchCriteria object containing the user's input.
     */
    void onSearchRequested(SearchCriteria criteria);
}