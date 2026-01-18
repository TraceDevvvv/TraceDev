import java.util.Optional;

/**
 * Service implementing the view refreshment point card use case.
 */
public class ViewRefreshmentPointCardService implements ViewRefreshmentPointCardUseCase {
    private RefreshmentPointRepository refreshmentPointRepository;
    private CacheManager cacheManager;
    private ErrorHandler errorHandler;

    public ViewRefreshmentPointCardService(RefreshmentPointRepository repository,
                                           CacheManager cacheManager,
                                           ErrorHandler errorHandler) {
        this.refreshmentPointRepository = repository;
        this.cacheManager = cacheManager;
        this.errorHandler = errorHandler;
    }

    @Override
    public RefreshmentPointDTO execute(String refreshmentPointId) {
        Optional<RefreshmentPoint> entityOpt = fetchFromCacheOrRepository(refreshmentPointId);
        if (entityOpt.isPresent()) {
            return RefreshmentPointDTO.fromEntity(entityOpt.get());
        } else {
            errorHandler.logError("Refreshment point not found: " + refreshmentPointId);
            errorHandler.handleConnectionError(new Exception("Data not found"));
            throw new DataRetrievalException("Refreshment point not found", "404");
        }
    }

    private Optional<RefreshmentPoint> fetchFromCacheOrRepository(String id) {
        // Check cache first
        Optional<Object> cached = cacheManager.get(id);
        if (cached.isPresent() && cached.get() instanceof RefreshmentPoint) {
            return Optional.of((RefreshmentPoint) cached.get());
        }

        // Cache miss: fetch from repository
        Optional<RefreshmentPoint> entity = refreshmentPointRepository.findById(id);
        if (entity.isPresent()) {
            cacheManager.put(id, entity.get()); // Cache the result
        } else {
            // Connection error handling is done inside repository/adapter
            handleConnectionError();
        }
        return entity;
    }

    private void handleConnectionError() {
        // Delegate to error handler
        errorHandler.handleConnectionError(new Exception("Connection to repository failed"));
    }
}