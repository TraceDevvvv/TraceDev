package com.example.application;

import com.example.domain.User;
import com.example.infrastructure.RepositoryException;
import com.example.infrastructure.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use Case Controller for listing users.
 * Orchestrates the retrieval of users and creation of summary DTOs.
 */
public class UserListController {
    private UserRepository userRepository;

    public UserListController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Executes the use case: retrieve all users and map to summary DTOs.
     * Performance requirement: should complete promptly.
     * @return List of UserSummaryDTO
     * @throws RepositoryException if there's an issue accessing the repository
     */
    public List<UserSummaryDTO> execute() throws RepositoryException {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserSummaryDTO(
                        user.getId(),
                        user.getName(),
                        user.getSurname()))
                .collect(Collectors.toList());
    }
}
