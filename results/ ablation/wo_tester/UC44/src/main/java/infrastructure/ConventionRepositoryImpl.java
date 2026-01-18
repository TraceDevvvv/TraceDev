package infrastructure;

import domain.Convention;
import domain.ConventionRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * In-memory implementation of ConventionRepository.
 * In a real application, would use a database client.
 */
public class ConventionRepositoryImpl implements ConventionRepository {
    private final Map<String, Convention> store = new HashMap<>();
    private DatabaseClient databaseClient;

    public ConventionRepositoryImpl(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public void save(Convention convention) {
        store.put(convention.getId(), convention);
    }

    @Override
    public Optional<Convention> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }
}