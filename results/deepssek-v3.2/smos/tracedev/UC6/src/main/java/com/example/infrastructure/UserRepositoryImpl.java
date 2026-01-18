package com.example.infrastructure;

import com.example.entity.User;
import com.example.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of UserRepository using an in-memory map for simplicity.
 * In a real system, this would connect to a database.
 */
public class UserRepositoryImpl implements UserRepository {
    private Map<Long, User> userStore = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(nextId++);
        }
        userStore.put(user.getId(), user);
        System.out.println("User saved with ID: " + user.getId());
        return user;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userStore.values().stream()
                .filter(u -> login.equals(u.getLogin()))
                .findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userStore.values().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst();
    }
}