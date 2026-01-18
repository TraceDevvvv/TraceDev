import java.util.List;

/**
 * Interface for previous use case that provides refreshment points list (REQ-006).
 */
public interface SearchRefreshmentPointUseCase {
    List<RefreshmentPoint> execute(String searchCriteria);
}

// Dummy implementation for compilation
class DummySearchUseCase implements SearchRefreshmentPointUseCase {
    public List<RefreshmentPoint> execute(String searchCriteria) {
        return List.of();
    }
}