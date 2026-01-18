package application;

import domain.UserService;
import infrastructure.UserRepository;
import infrastructure.SMOSClient;

/**
 * Application layer use case orchestrating the deletion process.
 * Called by the controller and delegates to the domain service.
 */
public class DeleteUserUseCase {
    private UserService userService;
    private UserRepository userRepository;
    private SMOSClient smosClient;

    public DeleteUserUseCase(UserService userService, UserRepository userRepository, SMOSClient smosClient) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.smosClient = smosClient;
    }

    /**
     * Executes the user deletion as per the sequence diagram.
     * @param userId ID of the user to delete.
     */
    public void execute(String userId) {
        userService.deleteUser(userId);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SMOSClient getSmosClient() {
        return smosClient;
    }

    public void setSmosClient(SMOSClient smosClient) {
        this.smosClient = smosClient;
    }
}