import java.time.LocalDateTime;

// Change request class to handle cultural object modifications
public class ChangeRequest {
    private AgencyOperator operator;
    private CulturalObject culturalObject;
    private String newName;
    private String newDescription;
    private String newLocation;
    private LocalDateTime requestTime;
    private boolean submitted;
    
    public ChangeRequest(AgencyOperator operator, CulturalObject culturalObject) {
        this.operator = operator;
        this.culturalObject = culturalObject;
        this.requestTime = LocalDateTime.now();
        this.submitted = false;
    }
    
    public void setNewName(String newName) {
        this.newName = newName;
    }
    
    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }
    
    public void setNewLocation(String newLocation) {
        this.newLocation = newLocation;
    }
    
    public void displayCurrentData() {
        System.out.println("Current Data:");
        System.out.println("  Name: " + culturalObject.getName());
        System.out.println("  Description: " + culturalObject.getDescription());
        System.out.println("  Location: " + culturalObject.getLocation());
        System.out.println("  Last Modified: " + culturalObject.getLastModified());
    }
    
    public boolean validate() {
        // Check if operator is logged in
        if (!operator.isLoggedIn()) {
            System.out.println("Validation Error: Operator is not logged in.");
            return false;
        }
        
        // Check if any data was actually changed
        if (newName == null && newDescription == null && newLocation == null) {
            System.out.println("Validation Error: No changes were made.");
            return false;
        }
        
        // Validate new name if provided
        if (newName != null && newName.trim().length() < 2) {
            System.out.println("Validation Error: Name must be at least 2 characters.");
            return false;
        }
        
        return true;
    }
    
    public CulturalObject applyChanges() {
        if (newName != null) {
            culturalObject.setName(newName);
        }
        if (newDescription != null) {
            culturalObject.setDescription(newDescription);
        }
        if (newLocation != null) {
            culturalObject.setLocation(newLocation);
        }
        submitted = true;
        return culturalObject;
    }
    
    public boolean isSubmitted() {
        return submitted;
    }
    
    public AgencyOperator getOperator() {
        return operator;
    }
}