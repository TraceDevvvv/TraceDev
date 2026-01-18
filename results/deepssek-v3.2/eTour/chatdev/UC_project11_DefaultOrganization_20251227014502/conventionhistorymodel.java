/**
 * Model class for Convention History.
 * This class manages data operations and server connectivity.
 * It supports both MySQL and H2 in-memory database for runnability.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class ConventionHistoryModel {
    private String selectedPointOfRest;
    private List<Convention> conventions;
    private Connection connection;
    private boolean isServerConnected;
    private DatabaseConfig dbConfig;
    /**
     * Inner class representing a Convention entity.
     */
    public static class Convention {
        private int id;
        private String name;
        private String pointOfRest;
        private String date;
        private String description;
        public Convention(int id, String name, String pointOfRest, String date, String description) {
            this.id = id;
            this.name = name;
            this.pointOfRest = pointOfRest;
            this.date = date;
            this.description = description;
        }
        public int getId() { return id; }
        public String getName() { return name; }
        public String getPointOfRest() { return pointOfRest; }
        public String getDate() { return date; }
        public String getDescription() { return description; }
    }
    /**
     * Configuration class for database connection.
     */
    public static class DatabaseConfig {
        private String url;
        private String username;
        private String password;
        private String driver;
        public DatabaseConfig(String url, String username, String password, String driver) {
            this.url = url;
            this.username = username;
            this.password = password;
            this.driver = driver;
        }
        public String getUrl() { return url; }
        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getDriver() { return driver; }
    }
    public ConventionHistoryModel() {
        this.conventions = new ArrayList<>();
        this.isServerConnected = false;
        // Default to H2 in-memory database for runnability
        this.dbConfig = createH2Config();
    }
    /**
     * Create configuration for H2 in-memory database.
     * This ensures the application runs without external MySQL setup.
     */
    private DatabaseConfig createH2Config() {
        String url = "jdbc:h2:mem:etour_db;DB_CLOSE_DELAY=-1";
        return new DatabaseConfig(url, "sa", "", "org.h2.Driver");
    }
    /**
     * Create configuration for MySQL database (alternative).
     */
    public void setMySQLConfig(String host, String dbName, String username, String password) {
        String url = "jdbc:mysql://" + host + "/" + dbName;
        this.dbConfig = new DatabaseConfig(url, username, password, "com.mysql.cj.jdbc.Driver");
    }
    /**
     * Set the selected point of rest (restaurant).
     * @param pointOfRest The restaurant name to filter conventions
     */
    public void setSelectedPointOfRest(String pointOfRest) {
        this.selectedPointOfRest = pointOfRest;
    }
    /**
     * Get the current conventions loaded from database.
     * @return List of Convention objects
     */
    public List<Convention> getConventions() {
        return new ArrayList<>(conventions);
    }
    /**
     * Check if the server connection is established.
     * @return true if connected, false otherwise
     */
    public boolean isServerConnected() {
        return isServerConnected;
    }
    /**
     * Load convention history from the database for the selected point of rest.
     */
    public void loadConventionHistory() {
        conventions.clear();
        if (!isServerConnected) {
            System.err.println("Error: Not connected to database.");
            return;
        }
        if (selectedPointOfRest == null || selectedPointOfRest.isEmpty()) {
            System.err.println("Error: No point of rest selected.");
            return;
        }
        String query = "SELECT id, name, point_of_rest, date, description FROM conventions WHERE point_of_rest = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, selectedPointOfRest);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Convention convention = new Convention(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("point_of_rest"),
                    rs.getString("date"),
                    rs.getString("description")
                );
                conventions.add(convention);
            }
            System.out.println("Loaded " + conventions.size() + " conventions for: " + selectedPointOfRest);
        } catch (SQLException e) {
            System.err.println("Error loading conventions: " + e.getMessage());
        }
    }
    /**
     * Establish connection to the database.
     * Tries configured database, falls back to H2 if connection fails.
     */
    public void connectToServer() {
        try {
            // Try to load the database driver
            Class.forName(dbConfig.getDriver());
            connection = DriverManager.getConnection(dbConfig.getUrl(), 
                                                     dbConfig.getUsername(), 
                                                     dbConfig.getPassword());
            isServerConnected = true;
            System.out.println("Connected to database successfully using: " + dbConfig.getDriver());
            // Initialize schema and sample data for H2
            if (dbConfig.getDriver().equals("org.h2.Driver")) {
                initializeH2Database();
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found: " + dbConfig.getDriver());
            // Fallback to H2 if driver not found
            fallbackToH2();
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            // Fallback to H2 if connection fails
            fallbackToH2();
        }
    }
    /**
     * Initialize H2 in-memory database with schema and sample data.
     */
    private void initializeH2Database() {
        try (Statement stmt = connection.createStatement()) {
            // Create conventions table
            String createTable = "CREATE TABLE IF NOT EXISTS conventions (" +
                               "id INT AUTO_INCREMENT PRIMARY KEY, " +
                               "name VARCHAR(100) NOT NULL, " +
                               "point_of_rest VARCHAR(100) NOT NULL, " +
                               "date VARCHAR(20) NOT NULL, " +
                               "description TEXT" +
                               ")";
            stmt.executeUpdate(createTable);
            // Create index for faster queries
            stmt.executeUpdate("CREATE INDEX IF NOT EXISTS idx_point_of_rest ON conventions(point_of_rest)");
            // Check if data already exists
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM conventions");
            if (rs.next() && rs.getInt(1) == 0) {
                // Insert sample data
                String insertData = "INSERT INTO conventions (name, point_of_rest, date, description) VALUES " +
                                  "('Annual Food Expo 2024', 'The Gourmet Hub', '2024-01-15', 'Largest annual food industry convention featuring 500+ exhibitors'), " +
                                  "('International Chefs Conference', 'The Gourmet Hub', '2024-02-20', 'Global gathering of Michelin-star chefs for culinary innovation'), " +
                                  "('Wine & Dine Festival', 'Vineyard Restaurant', '2024-03-10', 'Premium wine tasting with 100+ international wineries'), " +
                                  "('Hospitality Leadership Summit', 'Grand Hotel Dining', '2024-01-30', 'Top hoteliers discuss industry trends'), " +
                                  "('Future of Food Tech', 'The Gourmet Hub', '2024-04-05', 'Exploring AI and sustainability in culinary arts'), " +
                                  "('Sustainable Restaurant Conference', 'Green Bistro', '2024-05-12', 'Focus on eco-friendly pract and zero-waste cooking'), " +
                                  "('Asian Fusion Workshop', 'Dragon Palace', '2024-06-08', 'Hands-on training in modern Asian fusion techniques'), " +
                                  "('Artisan Bread Symposium', 'Boulangerie Central', '2024-07-19', 'Traditional and innovative bread-making methods'), " +
                                  "('Coffee Masters Convention', 'Bean There Cafe', '2024-08-25', 'World barista championship and coffee science'), " +
                                  "('Seafood Sustainability Forum', 'Ocean''s Catch', '2024-09-14', 'Sustainable fishing pract and marine conservation')";
                stmt.executeUpdate(insertData);
                System.out.println("Sample data created successfully in H2 database.");
            }
        } catch (SQLException e) {
            System.err.println("Error initializing H2 database: " + e.getMessage());
        }
    }
    /**
     * Fallback to H2 in-memory database when primary connection fails.
     */
    private void fallbackToH2() {
        System.out.println("Falling back to H2 in-memory database...");
        this.dbConfig = createH2Config();
        try {
            Class.forName(dbConfig.getDriver());
            connection = DriverManager.getConnection(dbConfig.getUrl(), 
                                                     dbConfig.getUsername(), 
                                                     dbConfig.getPassword());
            isServerConnected = true;
            System.out.println("Successfully connected to H2 in-memory database.");
            initializeH2Database();
        } catch (Exception e) {
            System.err.println("Failed to connect to H2 database: " + e.getMessage());
            isServerConnected = false;
        }
    }
    /**
     * Disconnect from the database.
     */
    public void disconnectFromServer() {
        if (connection != null) {
            try {
                connection.close();
                isServerConnected = false;
                System.out.println("Disconnected from database.");
            } catch (SQLException e) {
                System.err.println("Error disconnecting from database: " + e.getMessage());
            }
        }
    }
}