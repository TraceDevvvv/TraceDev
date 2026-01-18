import java.sql.ResultSet; // Mocking this for demonstration, actual JDBC would be used
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Concrete implementation of ITouristAccountRepository, handling data access
 * for TouristAccount entities.
 * Traceability: This class represents RepoImpl in the sequence diagram.
 */
public class TouristAccountRepository implements ITouristAccountRepository {
    // Private field for the Database dependency
    private final Database database;

    /**
     * Constructor for TouristAccountRepository, injecting the Database dependency.
     *
     * @param database The database interface for executing queries.
     */
    public TouristAccountRepository(Database database) {
        this.database = database;
    }

    /**
     * Finds a list of TouristAccount entities based on the provided search criteria.
     * This method constructs a SQL query and interacts with the database.
     *
     * @param criteria The DTO containing search parameters.
     * @return A list of matching TouristAccount objects.
     * @throws DatabaseConnectionException if there's an interruption in the database connection.
     */
    @Override
    public List<TouristAccount> findByCriteria(TouristSearchCriteriaDTO criteria) throws DatabaseConnectionException {
        System.out.println("Repository: Searching by criteria: " + criteria);
        // In a real application, this would involve constructing a dynamic SQL query
        // based on the criteria, using prepared statements to prevent SQL injection.
        StringBuilder sqlBuilder = new StringBuilder("SELECT id, name, email, nationality, bookingCount FROM TouristAccount WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (criteria.name != null && !criteria.name.isEmpty()) {
            sqlBuilder.append(" AND name LIKE ?");
            params.add("%" + criteria.name + "%");
        }
        if (criteria.nationality != null && !criteria.nationality.isEmpty()) {
            sqlBuilder.append(" AND nationality = ?");
            params.add(criteria.nationality);
        }
        if (criteria.minBookingCount > 0) {
            sqlBuilder.append(" AND bookingCount >= ?");
            params.add(criteria.minBookingCount);
        }

        String sql = sqlBuilder.toString();
        System.out.println("Repository: Executing SQL (mocked): " + sql + " with params: " + params);

        try {
            // Execute the query using the injected Database dependency
            // Mock ResultSet for simplicity, real JDBC would return a java.sql.ResultSet
            ResultSet resultSet = database.query(sql); // This call sends the message to DB (ETOUR Database).

            // Process the ResultSet and convert to List<TouristAccount>
            List<TouristAccount> accounts = mapResultSetToTouristAccounts(resultSet);
            // Traceability: m13 - Returns touristAccounts : List<TouristAccount> on success.
            return accounts;
        } catch (Exception e) { // Catch generic Exception from database.query for simulation
            // If the query operation fails, re-throw as DatabaseConnectionException
            // This satisfies the sequence diagram's error path where RepoImpl throws DatabaseConnectionException (m15).
            // Also, implicitly handles the lost message m14 connectionError to DB.
            System.err.println("Repository: Database query failed, throwing DatabaseConnectionException. Message: " + e.getMessage());
            // In a real application, check specific exception types for more granular error handling
            throw new DatabaseConnectionException("Failed to connect or query database: " + e.getMessage(), e);
        }
    }

    /**
     * Helper method to map a mocked ResultSet to a list of TouristAccount objects.
     * In a real JDBC scenario, this would iterate through `java.sql.ResultSet`.
     *
     * @param resultSet The mocked ResultSet containing account data.
     * @return A list of TouristAccount objects.
     */
    private List<TouristAccount> mapResultSetToTouristAccounts(ResultSet resultSet) {
        List<TouristAccount> accounts = new ArrayList<>();
        // In a real scenario, you'd iterate `while (resultSet.next()) { ... }`
        // For our mock, we're assuming the ResultSet is a List<Map<String, Object>>
        if (resultSet instanceof MockResultSet) {
            List<Map<String, Object>> rows = ((MockResultSet) resultSet).getRows();
            for (Map<String, Object> row : rows) {
                TouristAccount account = new TouristAccount();
                account.id = (String) row.get("id");
                account.name = (String) row.get("name");
                account.email = (String) row.get("email");
                account.nationality = (String) row.get("nationality");
                account.bookingCount = (Integer) row.get("bookingCount");
                accounts.add(account);
            }
        } else {
            // This block would handle a real JDBC ResultSet
            // For now, it's just a placeholder or could throw an error if not a MockResultSet
            System.err.println("Repository: Warning - ResultSet is not a MockResultSet. Cannot map data.");
        }
        return accounts;
    }
}