package interfaces;

import domain.RefreshmentPoint;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for refreshment points.
 */
public interface IRefreshmentPointRepository {
    List<RefreshmentPoint> findAll();
    Optional<RefreshmentPoint> findById(String id);
    void save(RefreshmentPoint point);
    void update(RefreshmentPoint point);
}