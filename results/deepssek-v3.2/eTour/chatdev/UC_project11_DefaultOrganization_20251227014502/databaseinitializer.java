/**
 * Database Initializer class.
 * This class provides database setup and sample data creation for runnability.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DatabaseInitializer {
    /**
     * Initialize H2 in-memory database with sample data.
     * This ensures the application runs without external database setup.
     */
    public static Connection initializeH2Database() {
        Connection connection = null;
        try {
            // Load H2 database driver
            Class.forName("org.h2.Driver");
            // Create in-memory database connection
            String url = "jdbc:h2:mem:etour_db;DB_CLOSE_DELAY=-1";
            connection = DriverManager.getConnection(url, "sa", "");
            // Create schema and sample data
            createSchema(connection);
            insertSampleData(connection);
            System.out.println("H2 database initialized successfully with sample data.");
        } catch (ClassNotFoundException e) {
            System.err.println("H2 Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error initializing H2 database: " + e.getMessage());
        }
        return connection;
    }
    /**
     * Create the database schema (tables).
     */
    private static void createSchema(Connection connection) throws SQLException {
        String createTableSQL = 
            "CREATE TABLE IF NOT EXISTS conventions (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(100) NOT NULL, " +
            "point_of_rest VARCHAR(100) NOT NULL, " +
            "date VARCHAR(20) NOT NULL, " +
            "description TEXT" +
            ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }
    /**
     * Insert sample data for demonstration.
     */
    private static void insertSampleData(Connection connection) throws SQLException {
        // Check if data already exists
        String countSQL = "SELECT COUNT(*) FROM conventions";
        try (Statement stmt = connection.createStatement()) {
            var resultSet = stmt.executeQuery(countSQL);
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.out.println("Sample data already exists.");
                return;
            }
        }
        // Insert sample conventions
        String insertSQL = 
            "INSERT INTO conventions (name, point_of_rest, date, description) VALUES " +
            "('Annual Food Expo 2024', 'The Gourmet Hub', '2024-01-15', 'Largest annual food industry convention featuring 500+ exhibitors'), " +
            "('International Chefs Conference', 'The Gourmet Hub', '2024-02-20', 'Global gathering of Michelin-star chefs for culinary innovation'), " +
            "('Wine & Dine Festival', 'Vineyard Restaurant', '2024-03-10', 'Premium wine tasting with 100+ international wineries'), " +
            "('Hospitality Leadership Summit', 'Grand Hotel Dining', '2024-01-30', 'Top hoteliers discuss industry trends and challenges'), " +
            "('Future of Food Tech', 'The Gourmet Hub', '2024-04-05', 'Exploring AI, robotics, and sustainability in culinary arts'), " +
            "('Sustainable Restaurant Conference', 'Green Bistro', '2024-05-12', 'Focus on eco-friendly pract and zero-waste cooking'), " +
            "('Asian Fusion Culinary Workshop', 'Dragon Palace', '2024-06-08', 'Hands-on training in modern Asian fusion techniques'), " +
            "('Artisan Bread Symposium', 'Boulangerie Central', '2024-07-19', 'Traditional and innovative bread-making methods'), " +
            "('Coffee Masters Convention', 'Bean There Cafe', '2024-08-25', 'World barista championship and coffee science seminars'), " +
            "('Seafood Sustainability Forum', 'Ocean''s Catch', '2024-09-14', 'Sustainable fishing pract and marine conservation')";
        try (Statement stmt = connection.createStatement()) {
            int rowsInserted = stmt.executeUpdate(insertSQL);
            System.out.println("Inserted " + rowsInserted + " sample convention records.");
        }
    }
    /**
     * Get sample restaurant names for user guidance.
     */
    public static String[] getSampleRestaurants() {
        return new String[] {
            "The Gourmet Hub",
            "Vineyard Restaurant", 
            "Grand Hotel Dining",
            "Green Bistro",
            "Dragon Palace",
            "Boulangerie Central",
            "Bean There Cafe",
            "Ocean's Catch"
        };
    }
}