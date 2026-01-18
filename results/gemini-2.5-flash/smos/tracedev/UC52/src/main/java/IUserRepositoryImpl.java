// File: IUserRepositoryImpl.java
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Dummy implementation for IUserRepository.
 * Used to simulate database interactions for UserEntity.
 * Throws SMOSServerException if "fail_user" keyword is used.
 */
class IUserRepositoryImpl implements IUserRepository {
    private List<UserEntity> users;

    public IUserRepositoryImpl() {
        users = new ArrayList<>();
        users.add(new UserEntity("U001", "Alice Admin", "alice@example.com", "Administrator"));
        users.add(new UserEntity("U002", "Bob Student", "bob@example.com", "Student"));
        users.add(new UserEntity("U003", "Charlie Faculty", "charlie@example.com", "Faculty"));
    }

    @Override
    public List<UserEntity> findByKeywords(String keywords) throws SMOSServerException {
        // Simulate a server connection error if specific keyword is used
        if (keywords != null && keywords.toLowerCase().contains("fail_user")) {
            throw new SMOSServerException("Simulated connection error to User Repository.");
        }

        if (keywords == null || keywords.trim().isEmpty()) {
            return new ArrayList<>(users); // Return all if no keywords
        }

        String lowerKeywords = keywords.toLowerCase();
        return users.stream()
                .filter(u -> u.name.toLowerCase().contains(lowerKeywords) ||
                             u.email.toLowerCase().contains(lowerKeywords) ||
                             u.role.toLowerCase().contains(lowerKeywords) ||
                             u.id.toLowerCase().contains(lowerKeywords))
                .collect(Collectors.toList());
    }
}