/**
 * EntityService.java
 * Simulates a service layer that performs entity searches.
 * In a real application, this would connect to a database or external API.
 */
import java.util.ArrayList;
import java.util.List;
public class EntityService {
    // Mock data for demonstration
    private List<Entity> mockEntities;
    public EntityService() {
        initializeMockData();
    }
    private void initializeMockData() {
        mockEntities = new ArrayList<>();
        // Adding sample entities of different types
        mockEntities.add(new Entity("Class", "CS101", "Introduction to Computer Science"));
        mockEntities.add(new Entity("Class", "MATH202", "Advanced Calculus"));
        mockEntities.add(new Entity("Teaching", "Fall 2023 CS101", "Taught by Dr. Smith"));
        mockEntities.add(new Entity("Teaching", "Spring 2024 MATH202", "Taught by Prof. Johnson"));
        mockEntities.add(new Entity("Address", "123 University Ave", "Main Campus Building"));
        mockEntities.add(new Entity("Address", "456 College St", "Science Department"));
        mockEntities.add(new Entity("User", "admin", "System Administrator"));
        mockEntities.add(new Entity("User", "john_doe", "Student - Computer Science"));
        mockEntities.add(new Entity("User", "jane_smith", "Professor - Mathematics"));
    }
    /**
     * Searches for entities that match the given keywords.
     * The search is case-insensitive and checks in both name and description.
     *
     * @param keywords the search query
     * @return list of matching entities
     */
    public List<Entity> searchEntities(String keywords) {
        List<Entity> results = new ArrayList<>();
        String lowerKeywords = keywords.toLowerCase();
        for (Entity entity : mockEntities) {
            if (entity.getName().toLowerCase().contains(lowerKeywords) ||
                entity.getDescription().toLowerCase().contains(lowerKeywords) ||
                entity.getType().toLowerCase().contains(lowerKeywords)) {
                results.add(entity);
            }
        }
        return results;
    }
}