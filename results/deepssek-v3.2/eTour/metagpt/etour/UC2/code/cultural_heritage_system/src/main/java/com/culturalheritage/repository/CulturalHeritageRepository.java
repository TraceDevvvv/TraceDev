package com.culturalheritage.repository;

import com.culturalheritage.model.CulturalHeritage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CulturalHeritageRepository handles database operations for CulturalHeritage entities.
 * This class provides CRUD operations and implements the persistence layer
 * for the Cultural Heritage Management System.
 */
public class CulturalHeritageRepository {
    
    private final Connection connection;
    
    /**
     * Constructs a CulturalHeritageRepository with the specified database connection.
     * 
     * @param connection The database connection to use for operations
     * @throws IllegalArgumentException if connection is null
     */
    public CulturalHeritageRepository(Connection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("Database connection cannot be null");
        }
        this.connection = connection;
    }
    
    /**
     * Creates the cultural_heritage table in the database if it doesn't exist.
     * This method should be called during system initialization.
     */
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS cultural_heritage (" +
                     "id VARCHAR(255) PRIMARY KEY, " +
                     "name VARCHAR(255) NOT NULL, " +
                     "type VARCHAR(100) NOT NULL, " +
                     "location VARCHAR(255) NOT NULL, " +
                     "era VARCHAR(100), " +
                     "description TEXT, " +
                     "agency_code VARCHAR(100) NOT NULL, " +
                     "registration_date TIMESTAMP NOT NULL" +
                     ")";
        
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Cultural heritage table created successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to create cultural_heritage table: " + e.getMessage());
            throw new RuntimeException("Database table creation failed", e);
        }
    }
    
    /**
     * Saves a CulturalHeritage object to the database.
     * If the heritage has an existing ID, it will be updated; otherwise, a new record will be inserted.
     * 
     * @param heritage The CulturalHeritage object to save
     * @return The saved CulturalHeritage object
     * @throws SQLException if database operation fails
     */
    public CulturalHeritage save(CulturalHeritage heritage) throws SQLException {
        if (heritage == null) {
            throw new IllegalArgumentException("CulturalHeritage cannot be null");
        }
        
        // Check if this heritage already exists in the database
        CulturalHeritage existing = findById(heritage.getId());
        
        if (existing != null) {
            return update(heritage);
        } else {
            return insert(heritage);
        }
    }
    
    /**
     * Inserts a new CulturalHeritage record into the database.
     * 
     * @param heritage The CulturalHeritage object to insert
     * @return The inserted CulturalHeritage object
     * @throws SQLException if database operation fails
     */
    private CulturalHeritage insert(CulturalHeritage heritage) throws SQLException {
        String sql = "INSERT INTO cultural_heritage (id, name, type, location, era, description, agency_code, registration_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, heritage.getId());
            statement.setString(2, heritage.getName());
            statement.setString(3, heritage.getType());
            statement.setString(4, heritage.getLocation());
            statement.setString(5, heritage.getEra());
            statement.setString(6, heritage.getDescription());
            statement.setString(7, heritage.getAgencyCode());
            statement.setTimestamp(8, new Timestamp(heritage.getRegistrationDate().getTime()));
            
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected == 1) {
                System.out.println("Successfully inserted cultural heritage: " + heritage.getName());
                return heritage;
            } else {
                throw new SQLException("Failed to insert cultural heritage, rows affected: " + rowsAffected);
            }
        }
    }
    
    /**
     * Updates an existing CulturalHeritage record in the database.
     * 
     * @param heritage The CulturalHeritage object to update
     * @return The updated CulturalHeritage object
     * @throws SQLException if database operation fails
     */
    private CulturalHeritage update(CulturalHeritage heritage) throws SQLException {
        String sql = "UPDATE cultural_heritage SET name = ?, type = ?, location = ?, era = ?, " +
                     "description = ?, agency_code = ?, registration_date = ? WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, heritage.getName());
            statement.setString(2, heritage.getType());
            statement.setString(3, heritage.getLocation());
            statement.setString(4, heritage.getEra());
            statement.setString(5, heritage.getDescription());
            statement.setString(6, heritage.getAgencyCode());
            statement.setTimestamp(7, new Timestamp(heritage.getRegistrationDate().getTime()));
            statement.setString(8, heritage.getId());
            
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected == 1) {
                System.out.println("Successfully updated cultural heritage: " + heritage.getName());
                return heritage;
            } else {
                throw new SQLException("Failed to update cultural heritage, rows affected: " + rowsAffected);
            }
        }
    }
    
    /**
     * Finds a CulturalHeritage by its unique ID.
     * 
     * @param id The unique identifier of the cultural heritage
     * @return The CulturalHeritage object if found, null otherwise
     */
    public CulturalHeritage findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        
        String sql = "SELECT * FROM cultural_heritage WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCulturalHeritage(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding cultural heritage by ID: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Finds a CulturalHeritage by name and location combination.
     * This method is used for duplicate checking as specified in the quality requirements.
     * 
     * @param name The name of the cultural heritage
     * @param location The location of the cultural heritage
     * @return The CulturalHeritage object if found, null otherwise
     */
    public CulturalHeritage findByNameAndLocation(String name, String location) {
        if (name == null || name.trim().isEmpty() || location == null || location.trim().isEmpty()) {
            return null;
        }
        
        String sql = "SELECT * FROM cultural_heritage WHERE name = ? AND location = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name.trim());
            statement.setString(2, location.trim());
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCulturalHeritage(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding cultural heritage by name and location: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Retrieves all CulturalHeritage records from the database.
     * 
     * @return A list of all CulturalHeritage objects
     */
    public List<CulturalHeritage> findAll() {
        List<CulturalHeritage> heritageList = new ArrayList<>();
        String sql = "SELECT * FROM cultural_heritage ORDER BY registration_date DESC";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                heritageList.add(mapResultSetToCulturalHeritage(resultSet));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving all cultural heritage: " + e.getMessage());
        }
        
        return heritageList;
    }
    
    /**
     * Deletes a CulturalHeritage record by its unique ID.
     * 
     * @param id The unique identifier of the cultural heritage to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean delete(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        
        String sql = "DELETE FROM cultural_heritage WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
            
        } catch (SQLException e) {
            System.err.println("Error deleting cultural heritage: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Maps a ResultSet row to a CulturalHeritage object.
     * This is a helper method to avoid code duplication.
     * 
     * @param resultSet The ResultSet containing the database row
     * @return A CulturalHeritage object populated from the ResultSet
     * @throws SQLException if database access error occurs
     */
    private CulturalHeritage mapResultSetToCulturalHeritage(ResultSet resultSet) throws SQLException {
        CulturalHeritage heritage = new CulturalHeritage();
        heritage.setId(resultSet.getString("id"));
        heritage.setName(resultSet.getString("name"));
        heritage.setType(resultSet.getString("type"));
        heritage.setLocation(resultSet.getString("location"));
        heritage.setEra(resultSet.getString("era"));
        heritage.setDescription(resultSet.getString("description"));
        heritage.setAgencyCode(resultSet.getString("agency_code"));
        heritage.setRegistrationDate(new Date(resultSet.getTimestamp("registration_date").getTime()));
        return heritage;
    }
    
    /**
     * Gets the database connection used by this repository.
     * 
     * @return The database connection
     */
    public Connection getConnection() {
        return connection;
    }
    
    /**
     * Checks if a cultural heritage with the given name and location already exists.
     * This is a convenience method for duplicate checking.
     * 
     * @param name The name to check
     * @param location The location to check
     * @return true if a duplicate exists, false otherwise
     */
    public boolean existsByNameAndLocation(String name, String location) {
        return findByNameAndLocation(name, location) != null;
    }
    
    /**
     * Counts the total number of cultural heritage records in the database.
     * 
     * @return The total count of cultural heritage records
     */
    public int countAll() {
        String sql = "SELECT COUNT(*) as total FROM cultural_heritage";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
            
        } catch (SQLException e) {
            System.err.println("Error counting cultural heritage records: " + e.getMessage());
        }
        
        return 0;
    }
}