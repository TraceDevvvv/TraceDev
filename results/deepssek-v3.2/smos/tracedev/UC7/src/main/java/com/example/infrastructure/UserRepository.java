package com.example.infrastructure;

import com.example.domain.User;
import java.util.List;

/**
 * Repository interface for User persistence operations.
 */
public interface UserRepository {
    User findById(String id) throws RepositoryException;
    List<User> findAll() throws RepositoryException;
}