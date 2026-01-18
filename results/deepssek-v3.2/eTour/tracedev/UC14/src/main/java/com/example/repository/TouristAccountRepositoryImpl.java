package com.example.repository;

import com.example.dto.SearchTouristDTO;
import com.example.domain.TouristAccount;
import com.example.exception.ConnectionException;
import com.example.connection.ETOURConnection;
import com.example.connection.QueryResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of TouristAccountRepository using ETOURConnection.
 */
public class TouristAccountRepositoryImpl implements TouristAccountRepository {
    private ETOURConnection etourServerConnection;

    public TouristAccountRepositoryImpl(ETOURConnection connection) {
        this.etourServerConnection = connection;
    }

    @Override
    public List<TouristAccount> findAllByCriteria(SearchTouristDTO criteria) throws ConnectionException {
        // Check connection health before querying (as per sequence diagram)
        if (!etourServerConnection.isAlive()) {
            handleConnectionInterruption();
            throw new ConnectionException("Connection to ETOUR server is not alive.");
        }

        try {
            // Build query based on criteria (simplified for example)
            String queryString = buildQuery(criteria);
            QueryResult result = etourServerConnection.query(queryString);
            return mapResultToAccounts(result);
        } catch (ConnectionException e) {
            // Connection lost during query (as per sequence diagram alternative path)
            handleConnectionInterruption();
            throw e;
        }
    }

    @Override
    public List<TouristAccount> findAllByCriteriaWithRetry(SearchTouristDTO criteria, int maxRetries) throws ConnectionException {
        int attempts = 0;
        ConnectionException lastException = null;
        while (attempts < maxRetries) {
            try {
                return findAllByCriteria(criteria);
            } catch (ConnectionException e) {
                lastException = e;
                attempts++;
                // Wait before retry (simple backoff)
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new ConnectionException("Retry interrupted.");
                }
            }
        }
        throw lastException != null ? lastException : new ConnectionException("Max retries exceeded.");
    }

    /**
     * Handles connection interruption (e.g., logging, cleanup).
     */
    protected void handleConnectionInterruption() {
        // Log the interruption, alert monitoring, etc.
        System.err.println("Connection to ETOUR server interrupted.");
        // Possibly attempt reconnection in the background?
    }

    /**
     * Builds a SQL-like query from criteria (simplified placeholder).
     */
    private String buildQuery(SearchTouristDTO criteria) {
        StringBuilder sb = new StringBuilder("SELECT * FROM tourist_account WHERE 1=1");
        if (criteria.getFirstName() != null && !criteria.getFirstName().isEmpty()) {
            sb.append(" AND first_name LIKE '%").append(criteria.getFirstName()).append("%'");
        }
        if (criteria.getLastName() != null && !criteria.getLastName().isEmpty()) {
            sb.append(" AND last_name LIKE '%").append(criteria.getLastName()).append("%'");
        }
        if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
            sb.append(" AND email LIKE '%").append(criteria.getEmail()).append("%'");
        }
        if (criteria.getAgencyReference() != null && !criteria.getAgencyReference().isEmpty()) {
            sb.append(" AND agency_reference = '").append(criteria.getAgencyReference()).append("'");
        }
        return sb.toString();
    }

    /**
     * Maps QueryResult rows to TouristAccount entities.
     * This is a simplified simulation; a real implementation would parse the result.
     */
    private List<TouristAccount> mapResultToAccounts(QueryResult result) {
        List<TouristAccount> accounts = new ArrayList<>();
        // Simulate processing rows: assume each row has 5 string columns in order: id, first, last, email, agencyRef
        // For demonstration, we create dummy data.
        // In reality, you would iterate through result rows and extract values.
        // Example dummy data:
        accounts.add(new TouristAccount("TA001", "John", "Doe", "john@example.com", "AG123"));
        accounts.add(new TouristAccount("TA002", "Jane", "Smith", "jane@example.com", "AG456"));
        return accounts;
    }

    // New method to correspond to sequence diagram message "add to result list"
    protected void addToResultList(List<TouristAccount> accounts, TouristAccount account) {
        accounts.add(account);
        System.out.println("Repository: Added account " + account.getAccountId() + " to result list.");
    }

    // Updated mapResultToAccounts to use the new method for traceability
    private List<TouristAccount> mapResultToAccountsUpdated(QueryResult result) {
        List<TouristAccount> accounts = new ArrayList<>();
        // Simulate processing rows.
        // For each row, create a TouristAccount and add it via addToResultList
        TouristAccount account1 = new TouristAccount("TA001", "John", "Doe", "john@example.com", "AG123");
        addToResultList(accounts, account1);
        TouristAccount account2 = new TouristAccount("TA002", "Jane", "Smith", "jane@example.com", "AG456");
        addToResultList(accounts, account2);
        return accounts;
    }
}