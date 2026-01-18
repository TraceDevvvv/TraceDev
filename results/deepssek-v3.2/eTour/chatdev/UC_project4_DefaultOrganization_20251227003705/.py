import java.util.*;
import java.util.stream.Collectors;
/**
 * SearchService implements the business logic for searching cultural heritage objects
 * Contains sample data and search algorithms
 */
public class SearchService {
    private List<CulturalObject> culturalObjects;
    public SearchService() {
        initializeSampleData();
    }
    /**
     * Initialize sample cultural heritage data
     */
    private void initializeSampleData() {
        culturalObjects = new ArrayList<>();
        // Add sample cultural heritage objects
        culturalObjects.add(new CulturalObject("1", "Great Wall of China", "Monument", "China", 221, 
            "Ancient fortification system built to protect Chinese states and empires"));
        culturalObjects.add(new CulturalObject("2", "Taj Mahal", "Monument", "India", 1653, 
            "White marble mausoleum built by Mughal emperor Shah Jahan"));
        culturalObjects.add(new CulturalObject("3", "Colosseum", "Monument", "Italy", 80, 
            "Ancient amphitheater in Rome used for gladiatorial contests"));
        culturalObjects.add(new CulturalObject("4", "Machu Picchu", "Site", "Peru", 1450, 
            "15th-century Inca citadel located in the Andes Mountains"));
        culturalObjects.add(new CulturalObject("5", "Pyramids of Giza", "Monument", "Egypt", 2560, 
            "Ancient Egyptian pyramid complex including the Great Pyramid"));
        culturalObjects.add(new CulturalObject("6", "Venice Carnival Masks", "Artifact", "Italy", 1268, 
            "Traditional masks worn during the Venice Carnival festival"));
        culturalObjects.add(new CulturalObject("7", "Easter Island Moai", "Monument", "Chile", 1250, 
            "Monolithic human figures carved by the Rapa Nui people"));
        culturalObjects.add(new CulturalObject("8", "Parthenon", "Building", "Greece", 438, 
            "Ancient temple dedicated to the goddess Athena"));
        culturalObjects.add(new CulturalObject("9", "Angkor Wat", "Building", "Cambodia", 1150, 
            "Largest religious monument in the world, originally a Hindu temple"));
        culturalObjects.add(new CulturalObject("10", "Japanese Tea Ceremony", "Tradition", "Japan", 800, 
            "Traditional ritual influenced by Zen Buddhism"));
        culturalObjects.add(new CulturalObject("11", "Stonehenge", "Monument", "United Kingdom", 3000, 
            "Prehistoric stone circle monument"));
        culturalObjects.add(new CulturalObject("12", "Sistine Chapel Ceiling", "Artifact", "Vatican City", 1512, 
            "Fresco painting by Michelangelo depicting scenes from Genesis"));
        culturalObjects.add(new CulturalObject("13", "Flamenco Dance", "Tradition", "Spain", 1700, 
            "Traditional Spanish art form combining dance, guitar music, and song"));
        culturalObjects.add(new CulturalObject("14", "Gutenberg Bible", "Artifact", "Germany", 1454, 
            "First major book printed using mass-produced movable metal type"));
        culturalObjects.add(new CulturalObject("15", "Forbidden City", "Building", "China", 1420, 
            "Imperial palace complex from the Ming dynasty to the end of the Qing dynasty"));
    }
    /**
     * Search for cultural objects based on provided criteria
     * @param name Name to search (partial match, case-insensitive)
     * @param type Type to filter (exact match, "Any" returns all)
     * @param location Location to filter (partial match, case-insensitive)
     * @param yearStr Year to filter (exact match or empty for any)
     * @return List of matching cultural objects
     */
    public List<CulturalObject> search(String name, String type, String location, String yearStr) {
        // Start timing for quality requirement
        long startTime = System.currentTimeMillis();
        // Build search result list
        List<CulturalObject> results = culturalObjects.stream()
            .filter(obj -> {
                // Filter by name (partial match, case-insensitive)
                if (!name.isEmpty() && !obj.getName().toLowerCase().contains(name.toLowerCase())) {
                    return false;
                }
                // Filter by type (exact match, unless "Any" is selected)
                if (!type.equals("Any") && !obj.getType().equals(type)) {
                    return false;
                }
                // Filter by location (partial match, case-insensitive)
                if (!location.isEmpty() && !obj.getLocation().toLowerCase().contains(location.toLowerCase())) {
                    return false;
                }
                // Filter by year (exact match)
                if (!yearStr.isEmpty()) {
                    try {
                        int year = Integer.parseInt(yearStr);
                        if (obj.getYear() != year) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        return false; // Invalid year format
                    }
                }
                return true;
            })
            .collect(Collectors.toList());
        // Sort results by name for better user experience
        results.sort(Comparator.comparing(CulturalObject::getName));
        // Calculate search time
        long endTime = System.currentTimeMillis();
        long searchTime = endTime - startTime;
        // Log search performance (for monitoring quality requirements)
        System.out.printf("Search completed in %d ms. Found %d result(s). Parameters: name='%s', type='%s', location='%s', year='%s'%n",
            searchTime, results.size(), name, type, location, yearStr);
        return results;
    }
    /**
     * Add more cultural objects to the database (for extensibility)
     * @param object CulturalObject to add
     */
    public void addCulturalObject(CulturalObject object) {
        culturalObjects.add(object);
    }
    /**
     * Get all cultural objects (for debugging/extensibility)
     * @return List of all cultural objects
     */
    public List<CulturalObject> getAllObjects() {
        return new ArrayList<>(culturalObjects);
    }
}