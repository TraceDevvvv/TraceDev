package com.example.app;

/**
 * TeachingRepositoryImpl is a concrete implementation of ITeachingRepository.
 * It uses a TeachingArchiveDBClient to interact with the database.
 */
public class TeachingRepositoryImpl implements ITeachingRepository {
    private final TeachingArchiveDBClient dbClient; // Aggregates the database client

    /**
     * Constructs a new TeachingRepositoryImpl with a given database client.
     *
     * @param dbClient The database client for archive operations.
     */
    public TeachingRepositoryImpl(TeachingArchiveDBClient dbClient) {
        this.dbClient = dbClient;
    }

    /**
     * Saves a Teaching entity by inserting its record into the database.
     *
     * @param teaching The Teaching object to be saved.
     * @throws PersistenceException if the underlying database operation fails.
     */
    @Override
    public void save(Teaching teaching) {
        System.out.println("[RepositoryImpl] Saving teaching: " + teaching.getName());
        try {
            // Sequence Diagram: RepoImpl -> DBClient: insertTeachingRecord(id, name)
            dbClient.insertTeachingRecord(teaching.getId(), teaching.getName());
            System.out.println("[RepositoryImpl] Teaching record inserted via DBClient.");
        } catch (ConnectionException e) {
            // Sequence Diagram: DBClient --> RepoImpl: exception : ConnectionError
            // Translate database-specific connection errors into a more general PersistenceException.
            throw new PersistenceException("Failed to save teaching due to database connection issue: " + e.getMessage(), e);
        } catch (Exception e) {
            // Catch any other unexpected exceptions from the DBClient
            throw new PersistenceException("An unexpected error occurred while saving teaching: " + e.getMessage(), e);
        }
    }

    /**
     * Checks if a teaching with the given name exists in the database.
     *
     * @param name The name of the teaching to check.
     * @return true if a teaching with the name exists, false otherwise.
     * @throws PersistenceException if the underlying database operation fails.
     */
    @Override
    public boolean existsByName(String name) {
        System.out.println("[RepositoryImpl] Checking if teaching with name '" + name + "' exists.");
        try {
            TeachingData existingTeaching = dbClient.queryTeachingByName(name);
            return existingTeaching != null; // If query returns data, it exists
        } catch (ConnectionException e) {
            // Translate database-specific connection errors into a more general PersistenceException.
            throw new PersistenceException("Failed to check existence of teaching due to database connection issue: " + e.getMessage(), e);
        } catch (Exception e) {
            // Catch any other unexpected exceptions from the DBClient
            throw new PersistenceException("An unexpected error occurred while checking teaching existence: " + e.getMessage(), e);
        }
    }
}