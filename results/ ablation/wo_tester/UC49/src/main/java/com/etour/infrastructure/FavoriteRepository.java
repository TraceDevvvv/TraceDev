package com.etour.infrastructure;

import com.etour.domain.Favorite;
import com.etour.domain.ConnectionException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Concrete repository for Favorite entities.
 * Implements IFavoriteRepository and uses ETOURServerConnector and Database.
 * Throws ConnectionException when connection is interrupted.
 */
public class FavoriteRepository implements IFavoriteRepository {
    private ETOURServerConnector serverConnector;
    private Database database;

    // Constructor with dependencies injected
    public FavoriteRepository(ETOURServerConnector serverConnector, Database database) {
        this.serverConnector = serverConnector;
        this.database = database;
    }

    @Override
    public List<Favorite> findAllByTouristId(String touristId) throws Exception {
        // Check connection precondition (as per sequence diagram)
        if (!serverConnector.isConnected()) {
            // Connection interrupted - throw ConnectionException
            // This corresponds to note m10: Repository -> Repository "Connection to server interrupted"
            noteConnectionInterrupted();
            throw new ConnectionException("Connection to server ETOUR interrupted");
        }

        // Query database for favorites of the tourist - corresponds to message m4: Repository -> DB "Query favorites for tourist"
        String sql = "SELECT * FROM favorites WHERE tourist_id = '" + touristId + "'";
        ResultSet resultSet = queryFavoritesForTourist(sql);

        // Convert ResultSet to List<Favorite> - corresponds to message m6: Repository -> Repository "Convert results to Favorite objects"
        List<Favorite> favorites = convertResultsToFavoriteObjects(resultSet, touristId);

        return favorites;
    }

    // This method corresponds to sequence diagram message m4: Repository -> DB "Query favorites for tourist"
    private ResultSet queryFavoritesForTourist(String sql) throws SQLException {
        return database.query(sql);
    }

    // This method corresponds to sequence diagram message m6: Repository -> Repository "Convert results to Favorite objects"
    private List<Favorite> convertResultsToFavoriteObjects(ResultSet resultSet, String touristId) throws SQLException {
        List<Favorite> favorites = new ArrayList<>();
        try {
            while (resultSet != null && resultSet.next()) {
                String id = resultSet.getString("id");
                String siteId = resultSet.getString("site_id");
                String siteName = resultSet.getString("site_name");
                Date addedDate = resultSet.getDate("added_date");
                favorites.add(new Favorite(id, touristId, siteId, siteName, addedDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error processing database result set", e);
        }

        return favorites;
    }

    // This method corresponds to sequence diagram note m10: Repository -> Repository "Connection to server interrupted"
    private void noteConnectionInterrupted() {
        // This is a note in the sequence diagram, not a method call.
        // In implementation, it's a condition that leads to throwing ConnectionException.
    }
}