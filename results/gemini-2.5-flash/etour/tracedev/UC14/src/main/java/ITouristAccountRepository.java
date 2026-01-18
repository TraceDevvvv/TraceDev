import java.util.List;

/**
 * Interface for repository operations related to TouristAccount entities.
 * Defines the contract for searching tourist accounts based on criteria.
 */
public interface ITouristAccountRepository {

    /**
     * Finds a list of TouristAccount entities based on the provided search criteria.
     *
     * @param criteria The DTO containing search parameters.
     * @return A list of matching TouristAccount objects.
     * @throws DatabaseConnectionException if there's an interruption in the database connection.
     */
    List<TouristAccount> findByCriteria(TouristSearchCriteriaDTO criteria) throws DatabaseConnectionException;
}