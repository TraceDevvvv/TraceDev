package infrastructure;

import domain.CulturalHeritage;
import domain.CulturalHeritageRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Infrastructure implementation of CulturalHeritageRepository.
 * Uses ETOURServerConnection to interact with the database.
 */
public class CulturalHeritageRepositoryImpl implements CulturalHeritageRepository {
    private ETOURServerConnection dataSource;

    public CulturalHeritageRepositoryImpl(ETOURServerConnection connection) {
        this.dataSource = connection;
    }

    @Override
    public CulturalHeritage findById(String id) {
        // Construct query
        String query = "SELECT * FROM cultural_heritage WHERE id = ?";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        // Assuming fetchData accepts query and parameters
        List<Map<String, Object>> result = dataSource.fetchData(query, params);
        if (result.isEmpty()) {
            return null;
        }
        Map<String, Object> dataRow = result.get(0);
        return toDomainEntity(dataRow);
    }

    @Override
    public void save(CulturalHeritage culturalHeritage) {
        Map<String, Object> dataRow = fromDomainEntity(culturalHeritage);
        // For simplicity, assume an update command
        String command = "UPDATE cultural_heritage SET name=?, description=?, type=?, location=? WHERE id=?";
        boolean success = dataSource.executeUpdate(command, dataRow);
        if (!success) {
            throw new RuntimeException("Failed to save cultural heritage");
        }
    }

    /**
     * Converts a database row map to a domain entity.
     */
    private CulturalHeritage toDomainEntity(Map<String, Object> dataRow) {
        String id = (String) dataRow.get("id");
        String name = (String) dataRow.get("name");
        String description = (String) dataRow.get("description");
        String type = (String) dataRow.get("type");
        String location = (String) dataRow.get("location");
        return new CulturalHeritage(id, name, description, type, location);
    }

    /**
     * Converts a domain entity to a map for database operations.
     */
    private Map<String, Object> fromDomainEntity(CulturalHeritage entity) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", entity.getId());
        map.put("name", entity.getName());
        map.put("description", entity.getDescription());
        map.put("type", entity.getType());
        map.put("location", entity.getLocation());
        return map;
    }
}