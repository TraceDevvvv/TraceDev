package com.example.etour;

/**
 * Implementation of UserService.
 */
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
        // Assumption: using concrete implementations.
        this.userRepository = new JpaUserRepository();
        this.passwordEncoder = new PasswordEncoder();
    }

    @Override
    public UserDTO findByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            return null;
        }
        // Convert UserEntity to UserDTO.
        return convertToDTO(userEntity);
    }

    @Override
    public Boolean validateCredentials(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            return false;
        }
        // Verify password using PasswordEncoder.
        return passwordEncoder.matches(password, userEntity.getPasswordHash());
    }

    /**
     * Helper method to verify password (as per sequence diagram).
     * @param passwordHash the stored hash.
     * @param password the raw password.
     * @return true if matches.
     */
    private Boolean verifyPassword(String passwordHash, String password) {
        return passwordEncoder.matches(password, passwordHash);
    }

    /**
     * Converts a UserEntity to a UserDTO.
     * @param userEntity the user entity.
     * @return the user DTO.
     */
    public UserDTO convertToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.userId = userEntity.getUserId();
        userDTO.username = userEntity.getUsername();
        userDTO.email = userEntity.getEmail();
        userDTO.role = userEntity.getRole();
        return userDTO;
    }
}