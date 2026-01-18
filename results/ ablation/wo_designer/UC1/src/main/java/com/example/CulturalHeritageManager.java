import java.util.*;

public class CulturalHeritageManager {
    private Map<String, CulturalHeritage> heritageDatabase;
    private boolean isServerConnected;
    
    public CulturalHeritageManager() {
        heritageDatabase = new HashMap<>();
        isServerConnected = true; // Simulating a connected server initially
        
        // Initialize with some sample data
        heritageDatabase.put("1", new CulturalHeritage("1", "Ancient Vase", "A vase from 500 BC"));
        heritageDatabase.put("2", new CulturalHeritage("2", "Medieval Manuscript", "A handwritten book from 1200 AD"));
        heritageDatabase.put("3", new CulturalHeritage("3", "Renaissance Painting", "Oil painting from 1500 AD"));
    }
    
    // Simulates search functionality from SearchCulturalHeritage use case
    public List<CulturalHeritage> searchCulturalHeritage(String query) {
        List<CulturalHeritage> results = new ArrayList<>();
        for (CulturalHeritage heritage : heritageDatabase.values()) {
            if (query.isEmpty() || 
                heritage.getName().toLowerCase().contains(query.toLowerCase()) ||
                heritage.getDescription().toLowerCase().contains(query.toLowerCase())) {
                results.add(heritage);
            }
        }
        return results;
    }
    
    // Deletes a cultural heritage by ID
    public boolean deleteCulturalHeritage(String id) {
        // Quality Requirement: Block input to avoid multiple submissions
        // This is simulated by checking server connection and ensuring operation atomicity
        if (!isServerConnected) {
            System.out.println("Error: Connection to server ETOUR is interrupted.");
            return false;
        }
        
        if (heritageDatabase.containsKey(id)) {
            heritageDatabase.remove(id);
            // Simulate server operation
            try {
                Thread.sleep(100); // Simulate processing delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return true;
        }
        return false;
    }
    
    // Method to simulate server connection interruption (for testing)
    public void setServerConnected(boolean connected) {
        isServerConnected = connected;
    }
    
    // Additional helper method to get all heritage (for testing)
    public List<CulturalHeritage> getAllCulturalHeritage() {
        return new ArrayList<>(heritageDatabase.values());
    }
}