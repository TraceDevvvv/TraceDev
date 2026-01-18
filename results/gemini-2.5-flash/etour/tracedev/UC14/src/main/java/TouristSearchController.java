import java.util.List;
import java.util.Map; // Used by TouristSearchView to pass generic data

/**
 * Controller responsible for handling search requests from the view
 * and coordinating with the service layer.
 */
public class TouristSearchController {
    // Private field for the TouristSearchService dependency
    private final TouristSearchService touristSearchService;
    // Private field for the TouristSearchView dependency (for communication back to view)
    private final TouristSearchView touristSearchView;

    /**
     * Constructor for TouristSearchController, injecting necessary dependencies.
     *
     * @param touristSearchService The service layer for tourist account operations.
     * @param touristSearchView The view layer to interact with.
     */
    public TouristSearchController(TouristSearchService touristSearchService, TouristSearchView touristSearchView) {
        this.touristSearchService = touristSearchService;
        this.touristSearchView = touristSearchView;
    }

    /**
     * Initiates the search for tourist accounts based on provided criteria.
     * This method acts as the entry point from the view layer.
     *
     * @param criteria The DTO containing the search parameters.
     * @return A list of DTOs representing matching tourist accounts.
     * @throws ApplicationException if an application-level error occurs,
     *                              potentially due to underlying service or database issues.
     */
    public List<TouristAccountDTO> searchTouristAccounts(TouristSearchCriteriaDTO criteria) throws ApplicationException {
        System.out.println("Controller: Received search request with criteria: " + criteria);
        List<TouristAccountDTO> touristAccountDTOs;
        try {
            // Delegate the search to the TouristSearchService
            touristAccountDTOs = touristSearchService.findTouristAccounts(criteria);
            System.out.println("Controller: Successfully retrieved " + touristAccountDTOs.size() + " accounts.");
            return touristAccountDTOs;
        } catch (ServiceException e) {
            // Catch ServiceException from the service layer and re-throw as ApplicationException
            // This re-throw satisfies the sequence diagram's error propagation path (m17).
            System.err.println("Controller: Caught ServiceException, propagating as ApplicationException. Message: " + e.getMessage());
            // In a real application, you might log the original exception with more detail
            throw new ApplicationException("Error finding tourist accounts: " + e.getMessage(), e);
        }
    }

    /**
     * Handles the search initiation from the view, processes criteria,
     * and displays results or error messages.
     * This method orchestrates the full search flow from the controller's perspective.
     *
     * @param searchData A map representing raw search data from the form.
     *                   This is converted to TouristSearchCriteriaDTO internally.
     */
    public void handleSearchRequest(Map<String, String> searchData) {
        // Traceability: m6 - Maps raw data to TouristSearchCriteriaDTO.
        // Convert raw search data from Map to TouristSearchCriteriaDTO
        TouristSearchCriteriaDTO criteria = touristSearchView.getSearchCriteria(searchData); // Assuming view has a method for this conversion or helper
        System.out.println("Controller: Converted search data to criteria: " + criteria);

        try {
            // Call the core search logic
            List<TouristAccountDTO> results = searchTouristAccounts(criteria);
            // If successful, display results through the view
            touristSearchView.displaySearchResults(results);
        } catch (ApplicationException e) {
            // If an ApplicationException occurs (e.g., from a ServiceException or DatabaseConnectionException),
            // display an error message through the view. This corresponds to the failure path message m17.
            System.err.println("Controller: Application error during search. Message: " + e.getMessage());
            touristSearchView.displayErrorMessage("Connection to ETOUR interrupted. Please try again later.");
        }
    }
}