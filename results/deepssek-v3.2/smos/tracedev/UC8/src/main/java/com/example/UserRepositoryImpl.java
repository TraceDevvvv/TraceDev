package com.example;

/**
 * Implementation of UserRepository.
 */
public class UserRepositoryImpl implements UserRepository {
    private DataSource dataSource;
    private SMOSClient smosClient;

    public UserRepositoryImpl(DataSource dataSource, SMOSClient smosClient) {
        this.dataSource = dataSource;
        this.smosClient = smosClient;
    }

    @Override
    public User findById(String id) {
        // Simulate fetching from data source
        // In a real implementation, this would query a database.
        return dataSource.getUser(id);
    }

    @Override
    public void save(User user) {
        // Simulate saving to data source
        dataSource.saveUser(user);
        // Send data to SMOS
        smosClient.sendData(user);
    }
}