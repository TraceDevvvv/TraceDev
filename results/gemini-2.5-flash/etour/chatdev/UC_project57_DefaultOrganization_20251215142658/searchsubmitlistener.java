package gui;
import model.SearchCriteria;
/*
 * Interface for listening to search form submissions.
 * This allows the AdvancedSearchFormPanel to communicate search requests
 * back to the main application logic (AdvancedSearchApp) without direct coupling.
 */
@FunctionalInterface
public interface SearchSubmitListener {
    /**
     * Called when the advanced search form is submitted.
     *
     * @param criteria The SearchCriteria object containing the user's search parameters.
     */
    void onSearchSubmit(SearchCriteria criteria);
}