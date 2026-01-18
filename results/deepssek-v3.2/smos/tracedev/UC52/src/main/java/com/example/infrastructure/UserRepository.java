package com.example.infrastructure;

import com.example.domain.User;
import java.util.List;

/**
 * Repository interface for User entities.
 * Extends the generic SearchRepository for User type.
 */
public interface UserRepository extends SearchRepository<User> {
    /**
     * Finds users containing the specified keywords in their searchable content.
     * @param keywords the search keywords.
     * @return a list of matching users.
     */
    List<User> findByKeywords(String keywords);
    // Additional user-specific methods could be added here
}