package domain;

import infrastructure.SMOSClient;
import infrastructure.UserRepository;
import java.util.List;

/**
 * Domain service orchestrating user deletion and associated operations.
 * Implements the quality requirement of deleting associated data.
 */
public class UserService {
    private UserRepository userRepository;
    private SMOSClient smosClient;

    public UserService(UserRepository userRepository, SMOSClient smosClient) {
        this.userRepository = userRepository;
        this.smosClient = smosClient;
    }

    /**
     * Main method to delete a user. Follows the sequence diagram:
     * 1. Delete associated data (quality requirement)
     * 2. Delete user from repository
     * 3. If successful, get updated list and disconnect SMOS
     */
    public boolean deleteUser(String userId) {
        deleteAssociatedData(userId);
        boolean deleted = userRepository.delete(userId);
        if (deleted) {
            // Retrieve updated list as per sequence diagram
            List<User> updatedList = userRepository.findAll();
            smosClient.disconnect();
            System.out.println("UserService: User " + userId + " deleted successfully.");
            return true;
        } else {
            // Even on failure, disconnect SMOS (recommendation)
            smosClient.disconnect();
            System.out.println("UserService: Deletion failed for user " + userId);
            return false;
        }
    }

    /**
     * Deletes data associated with the user (quality requirement).
     * In a real system, this would involve multiple repositories or serv.
     */
    public void deleteAssociatedData(String userId) {
        System.out.println("UserService: Deleting associated data for user " + userId);
        // Placeholder for actual associated data deletion logic
    }
}