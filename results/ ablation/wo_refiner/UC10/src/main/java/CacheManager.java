import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Simple cache manager for performance improvement.
 */
public class CacheManager {
    private Map<String, Object> cache;

    public CacheManager() {
        this.cache = new HashMap<>();
    }

    public Optional<Object> get(String key) {
        return Optional.ofNullable(cache.get(key));
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public void invalidate(String key) {
        cache.remove(key);
    }
}