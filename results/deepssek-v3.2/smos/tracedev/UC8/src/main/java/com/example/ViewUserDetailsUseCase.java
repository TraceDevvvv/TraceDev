package com.example;

/**
 * Use case for viewing user details.
 */
public class ViewUserDetailsUseCase {
    private UserRepository userRepository;

    public ViewUserDetailsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String userId) {
        return userRepository.findById(userId);
    }
}