package infrastructure;

/**
 * Infrastructure Layer: Represents a connection to a database.
 * Provides methods for executing queries and updates.
 * For this exercise, methods are highly simplified placeholders.
 */
public class DatabaseConnection {

    public DatabaseConnection() {
        System.out.println("[DBConnection] DatabaseConnection initialized.");
    }

    /**
     * Simulates executing a database query.
     * @param query The SQL query string.
     * @return A dummy ResultSet (actual data handling is simplified in Repository).
     */
    public ResultSet executeQuery(String query) {
        System.out.println("[DBConnection] Executing query: " + query);
        // In a real application, this would connect to a DB, execute query and return actual ResultSet.
        // For simulation, we assume it always "returns" something.
        return new ResultSet(); // Dummy ResultSet
    }

    /**
     * Simulates executing a database update (INSERT, UPDATE, DELETE).
     * @param query The SQL update string.
     * @return The number of affected rows (simulated as 1 for success).
     */
    public int executeUpdate(String query) {
        System.out.println("[DBConnection] Executing update: " + query);
        // In a real application, this would connect to a DB, execute update and return affected row count.
        // For simulation, we return 1 to indicate success.
        return 1;
    }

    // Dummy ResultSet class for placeholder purposes
    public static class ResultSet {
        // This class would typically hold rows of data and provide methods to iterate them.
        // For this exercise, it's just a marker.
        public ResultSet() {
            System.out.println("  [DBConnection] Dummy ResultSet created.");
        }
    }
}