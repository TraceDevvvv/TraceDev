'''
CulturalHeritageDatabase simulates a database for cultural heritage items.
Provides methods for searching, retrieving, and deleting items.
'''
import java.util.ArrayList;
import java.util.List;
public class CulturalHeritageDatabase {
    private List<CulturalHeritage> items;
    public CulturalHeritageDatabase() {
        items = new ArrayList<>();
        // Initialize with some sample data
        initializeSampleData();
    }
    private void initializeSampleData() {
        items.add(new CulturalHeritage(1, "Colosseum", "Ancient Roman amphitheater", "Rome, Italy", "Architecture"));
        items.add(new CulturalHeritage(2, "Great Wall of China", "Series of fortifications", "China", "Architecture"));
        items.add(new CulturalHeritage(3, "Mona Lisa", "Famous painting by Leonardo da Vinci", "Paris, France", "Art"));
        items.add(new CulturalHeritage(4, "Pyramids of Giza", "Ancient Egyptian pyramids", "Giza, Egypt", "Architecture"));
        items.add(new CulturalHeritage(5, "Sistine Chapel", "Renaissance chapel with Michelangelo frescoes", "Vatican City", "Art"));
    }
    /**
     * Searches for cultural heritage items by name or description.
     * Simulates the SearchCulturalHeritage use case.
     */
    public List<CulturalHeritage> searchCulturalHeritage(String query) {
        List<CulturalHeritage> results = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            return items;
        }
        String searchQuery = query.toLowerCase().trim();
        for (CulturalHeritage item : items) {
            if (item.getName().toLowerCase().contains(searchQuery) ||
                item.getDescription().toLowerCase().contains(searchQuery) ||
                item.getLocation().toLowerCase().contains(searchQuery)) {
                results.add(item);
            }
        }
        return results;
    }
    /**
     * Deletes a cultural heritage item by ID.
     * Returns true if deletion was successful.
     */
    public boolean deleteCulturalHeritage(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     * Gets all cultural heritage items.
     */
    public List<CulturalHeritage> getAllCulturalHeritage() {
        return new ArrayList<>(items);
    }
    /**
     * Finds a cultural heritage item by ID.
     */
    public CulturalHeritage getCulturalHeritageById(int id) {
        for (CulturalHeritage item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}