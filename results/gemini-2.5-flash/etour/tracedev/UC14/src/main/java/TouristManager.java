import java.util.List;

/**
 * Manages business logic related to tourist accounts.
 * Acts as an intermediary between the service layer and the data access layer.
 */
public class TouristManager {
    // Private field for the ITouristAccountRepository dependency
    private final ITouristAccountRepository touristAccountRepository;

    /**
     * Constructor for TouristManager, injecting the ITouristAccountRepository dependency.
     *
     * @param touristAccountRepository The data access interface for tourist accounts.
     */
    public TouristManager(ITouristAccountRepository touristAccountRepository) {
        this.touristAccountRepository = touristAccountRepository;
    }

    /**
     * Searches for tourist accounts based on the provided criteria.
     * This method interacts with the repository to retrieve data.
     *
     * @param criteria The DTO containing search parameters.
     * @return A list of TouristAccount domain objects.
     * @throws DatabaseConnectionException if there's an interruption in the database connection.
     */
    public List<TouristAccount> searchAccounts(TouristSearchCriteriaDTO criteria) throws DatabaseConnectionException {
        System.out.println("Manager: Searching accounts with criteria: " + criteria);
        // Delegate the search to the ITouristAccountRepository
        // The exception (DatabaseConnectionException) is propagated directly as per diagram.
        List<TouristAccount> accounts = touristAccountRepository.findByCriteria(criteria);
        System.out.println("Manager: Found " + accounts.size() + " accounts from repository.");
        return accounts;
    }
}