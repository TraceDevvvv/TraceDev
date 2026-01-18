package com.example.domain;

import com.example.interfaces.IUserRepository;
import javax.sql.DataSource;
import java.util.Optional;

/**
 * Implementation of the user repository.
 */
public class UserRepositoryImpl implements IUserRepository {
    private DataSource dataSource;

    public UserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        // Implementation would use dataSource to query database.
        // For now, return empty to simulate missing user.
        return Optional.empty();
    }

    @Override
    public void save(LoginAttempt attempt) {
        // Implementation would save the login attempt to the database.
        // For now, do nothing.
    }
}