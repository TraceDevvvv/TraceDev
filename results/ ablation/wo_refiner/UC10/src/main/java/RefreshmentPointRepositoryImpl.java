import java.util.Optional;

/**
 * Repository implementation that delegates to an adapter.
 */
public class RefreshmentPointRepositoryImpl implements RefreshmentPointRepository {
    private RefreshmentPointRepository dataSourceAdapter;

    public RefreshmentPointRepositoryImpl(RefreshmentPointRepository adapter) {
        this.dataSourceAdapter = adapter;
    }

    @Override
    public Optional<RefreshmentPoint> findById(String id) {
        return dataSourceAdapter.findById(id);
    }

    @Override
    public boolean update(RefreshmentPoint refreshmentPoint) {
        return dataSourceAdapter.update(refreshmentPoint);
    }
}