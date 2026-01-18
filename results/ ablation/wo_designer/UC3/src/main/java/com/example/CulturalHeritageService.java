import java.util.ArrayList;
import java.util.List;

// Service class for cultural heritage management
public class CulturalHeritageService {
    private List<CulturalObject> culturalObjects;
    private boolean submissionBlocked = false; // For quality requirement: block multiple submissions
    
    public CulturalHeritageService() {
        culturalObjects = new ArrayList<>();
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        culturalObjects.add(new CulturalObject("CO001", "Ancient Vase", "A beautiful ancient vase from Roman era", "Rome Museum"));
        culturalObjects.add(new CulturalObject("CO002", "Medieval Tapestry", "Historical tapestry from 14th century", "Paris Gallery"));
        culturalObjects.add(new CulturalObject("CO003", "Renaissance Painting", "Masterpiece by famous Renaissance artist", "Florence Museum"));
    }
    
    // Simulates the SearchCulturalHeritage use case
    public List<CulturalObject> searchCulturalHeritage(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>(culturalObjects);
        }
        
        List<CulturalObject> results = new ArrayList<>();
        for (CulturalObject obj : culturalObjects) {
            if (obj.getName().toLowerCase().contains(query.toLowerCase()) ||
                obj.getDescription().toLowerCase().contains(query.toLowerCase())) {
                results.add(obj);
            }
        }
        return results;
    }
    
    // Updates cultural object with quality requirement to block multiple submissions
    public synchronized boolean updateCulturalObject(ChangeRequest changeRequest) {
        if (submissionBlocked) {
            System.out.println("Error: System is processing another change request. Please wait.");
            return false;
        }
        
        // Block further submissions during this operation
        submissionBlocked = true;
        
        try {
            // Simulate server connection check
            if (!checkServerConnection()) {
                System.out.println("Exit Condition: Connection to server ETOUR is interrupted.");
                return false;
            }
            
            if (!changeRequest.validate()) {
                return false;
            }
            
            CulturalObject updatedObject = changeRequest.applyChanges();
            
            // In a real system, this would persist to database
            // Here we just update in-memory list
            for (int i = 0; i < culturalObjects.size(); i++) {
                if (culturalObjects.get(i).getId().equals(updatedObject.getId())) {
                    culturalObjects.set(i, updatedObject);
                    break;
                }
            }
            
            return true;
        } finally {
            // Unblock submissions after operation completes
            submissionBlocked = false;
        }
    }
    
    // Simulates server connection check
    private boolean checkServerConnection() {
        // In real implementation, this would check actual server connection
        // Here we simulate 90% success rate
        return Math.random() > 0.1;
    }
    
    public List<CulturalObject> getAllCulturalObjects() {
        return new ArrayList<>(culturalObjects);
    }
}