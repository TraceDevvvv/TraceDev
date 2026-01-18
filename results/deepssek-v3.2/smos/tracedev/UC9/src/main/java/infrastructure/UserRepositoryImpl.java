package infrastructure;

import domain.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation of UserRepository.
 * Uses an in-memory map as a simple data source for demonstration.
 */
public class UserRepositoryImpl implements UserRepository {
    // Simple in-memory data source
    private Map<String, User> dataSource;

    public UserRepositoryImpl() {
        this.dataSource = new HashMap<>();
        // Initialize with some dummy data for testing
        dataSource.put("U001", new User("U001", "john_doe", "john@example.com", true));
        dataSource.put("U002", new User("U002", "jane_smith", "jane@example.com", true));
    }

    @Override
    public User findById(String userId) {
        return dataSource.get(userId);
    }

    @Override
    public boolean delete(String userId) {
        // Returns true if the user existed and was removed
        return dataSource.remove(userId) != null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(dataSource.values());
    }
}