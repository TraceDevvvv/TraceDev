package com.example.adapter;

import com.example.entities.Justice;
import com.example.ports.JusticeRepositoryPort;
import com.example.database.DataSource;
import java.util.Optional;

/**
 * Adapter that uses a DataSource to interact with the SMOS server.
 */
public class JusticeRepositoryAdapter implements JusticeRepositoryPort {
    private DataSource dataSource;

    /**
     * Constructor.
     */
    public JusticeRepositoryAdapter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Finds a justice by ID using a SELECT query.
     */
    @Override
    public Optional<Justice> findById(int id) {
        if (!dataSource.isConnected()) {
            throw new IllegalStateException("Not connected to SMOS server");
        }
        // Simulate executing a SELECT query
        String query = "SELECT * FROM justice WHERE id = " + id;
        dataSource.executeQuery(query);
        // In a real application, we would map the ResultSet to a Justice object
        // For this example, we simulate returning a justice if id is positive
        if (id > 0) {
            Justice justice = new Justice(id, new java.util.Date(), "Sample justification from adapter");
            return Optional.of(justice);
        }
        return Optional.empty();
    }

    /**
     * Saves a justice using an UPDATE query.
     */
    @Override
    public Justice save(Justice justice) {
        if (!dataSource.isConnected()) {
            throw new IllegalStateException("Not connected to SMOS server");
        }
        // Simulate executing an UPDATE query
        String query = "UPDATE justice SET date = '" + justice.getDate() +
                "', justification_text = '" + justice.getJustificationText() +
                "' WHERE id = " + justice.getId();
        int rows = dataSource.executeUpdate(query);
        if (rows > 0) {
            return justice;
        }
        return null; // Update failed
    }

    /**
     * Execute SELECT query (for sequence diagram).
     */
    public Optional<Justice> selectQuery(int id) {
        return findById(id);
    }

    /**
     * Execute UPDATE query (for sequence diagram).
     */
    public Justice updateQuery(Justice justice) {
        return save(justice);
    }
}