package com.etour.infrastructure;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Simulated database component that executes queries.
 */
public class Database {
    // This method corresponds to sequence diagram message m4: Repository -> DB "Query favorites for tourist"
    public ResultSet query(String sql) throws SQLException {
        // In a real implementation, this would execute the SQL and return a ResultSet.
        // For this example, we return null to keep it simple.
        // Assumption: The database is properly configured and accessible.
        // The return corresponds to message m5: DB -> Repository "Result set"
        return returnResultSet();
    }

    // This method corresponds to sequence diagram message m5: DB -> Repository "Result set"
    private ResultSet returnResultSet() {
        // Simulated return of result set
        return null;
    }
}