import java.util.Optional;

/**
 * Port interface for refreshment point data access.
 */
public interface RefreshmentPointRepository {
    Optional<RefreshmentPoint> findById(String id);
    boolean update(RefreshmentPoint refreshmentPoint);
}