package infrastructure;

import domain.RefreshmentPoint;
import exceptions.EditPointException;
import interfaces.IRefreshmentPointRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the refreshment point repository.
 */
public class RefreshmentPointRepositoryImpl implements IRefreshmentPointRepository {
    private DataSource dataSource;
    private ETOURServer serverConnection; // Added for Exit Conditions

    public RefreshmentPointRepositoryImpl(DataSource dataSource, ETOURServer serverConnection) {
        this.dataSource = dataSource;
        this.serverConnection = serverConnection;
    }

    @Override
    public List<RefreshmentPoint> findAll() {
        // Simulate database query
        checkConnection();
        return dataSource.getAllPoints();
    }

    @Override
    public Optional<RefreshmentPoint> findById(String id) {
        checkConnection();
        RefreshmentPoint point = dataSource.getPointById(id);
        return Optional.ofNullable(point);
    }

    @Override
    public void save(RefreshmentPoint point) {
        checkConnection();
        dataSource.savePoint(point);
    }

    @Override
    public void update(RefreshmentPoint point) {
        checkConnection();
        if (!serverConnection.isAvailable()) {
            throw new EditPointException("Server connection lost", "CONNECTION_ERROR");
        }
        dataSource.updatePoint(point);
    }

    /**
     * Checks the server connection.
     * @return true if connected, false otherwise.
     */
    public boolean checkConnection() {
        return serverConnection.isAvailable();
    }
}

// Simulated DataSource class for database operations
class DataSource {
    private List<RefreshmentPoint> points = new ArrayList<>();

    public List<RefreshmentPoint> getAllPoints() {
        return new ArrayList<>(points);
    }

    public RefreshmentPoint getPointById(String id) {
        return points.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public void savePoint(RefreshmentPoint point) {
        points.add(point);
    }

    public void updatePoint(RefreshmentPoint point) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getId().equals(point.getId())) {
                points.set(i, point);
                return;
            }
        }
        // If not found, add as new (simulated)
        points.add(point);
    }
}