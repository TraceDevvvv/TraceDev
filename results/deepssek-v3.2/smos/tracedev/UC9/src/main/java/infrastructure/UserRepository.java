package infrastructure;

import domain.User;
import java.util.List;

/**
 * Repository interface for User persistence operations.
 * Acts as a contract for data access.
 */
public interface UserRepository {
    User findById(String userId);
    boolean delete(String userId);
    List<User> findAll();
}