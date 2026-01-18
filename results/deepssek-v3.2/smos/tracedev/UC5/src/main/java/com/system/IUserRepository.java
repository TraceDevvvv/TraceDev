package com.system;

import java.util.List;

/**
 * Repository interface for User entities.
 */
public interface IUserRepository {
    List<User> findAll();
}