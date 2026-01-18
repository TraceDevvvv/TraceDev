package com.example;

/**
 * Repository matching sequence diagram participant "Repository".
 */
public class Repository {
    private UserRepository userRepository;

    public Repository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Save user.
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Find user by id.
     */
    public User findById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Save failed (message m39).
     */
    public String saveFailed() {
        return "save failed";
    }
}