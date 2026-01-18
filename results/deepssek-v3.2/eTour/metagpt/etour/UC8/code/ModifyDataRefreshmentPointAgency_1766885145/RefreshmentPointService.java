import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * RefreshmentPointService handles the business logic for managing refreshment points.
 * This service provides methods for searching, loading, modifying, validating, 
 * and storing refreshment point data as per the use case requirements.
 */
public class RefreshmentPointService {
    
    // File path for storing refreshment point data
    private static final String DATA_FILE = "refreshment_points.dat";
    
    // Lock mechanism to prevent multiple submissions as per quality requirement
    private boolean operationInProgress = false;
    
    /**
     * Searches for refreshment points based on a search term.
     * This simulates the "SearchRefreshmentPoint" use case mentioned in the requirements.
     * 
     * @param searchTerm the term to search for in names and locations
     * @return list of refreshment points matching the search criteria
     */
    public List<RefreshmentPoint> searchRefreshmentPoints(String searchTerm) {
        List<RefreshmentPoint> allPoints = loadAllRefreshmentPoints();
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return allPoints;
        }
        
        String term = searchTerm.toLowerCase();
        return allPoints.stream()
                .filter(rp -> 
                    (rp.getName() != null && rp.getName().toLowerCase().contains(term)) ||
                    (rp.getLocation() != null && rp.getLocation().toLowerCase().contains(term)) ||
                    (rp.getId() != null && rp.getId().toLowerCase().contains(term))
                )
                .collect(Collectors.toList());
    }
    
    /**
     * Loads a refreshment point by its ID.
     * 
     * @param id the ID of the refreshment point to load
     * @return the refreshment point if found, null otherwise
     */
    public RefreshmentPoint loadRefreshmentPoint(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        
        List<RefreshmentPoint> allPoints = loadAllRefreshmentPoints();
        return allPoints.stream()
                .filter(rp -> id.equals(rp.getId()))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Validates a refreshment point's data for modification.
     * This method performs comprehensive validation as per the use case requirement.
     * 
     * @param refreshmentPoint the refreshment point to validate
     * @return ValidationResult containing validation status and any error messages
     */
    public ValidationResult validateRefreshmentPoint(RefreshmentPoint refreshmentPoint) {
        ValidationResult result = new ValidationResult();
        
        if (refreshmentPoint == null) {
            result.setValid(false);
            result.addErrorMessage("Refreshment point cannot be null");
            return result;
        }
        
        // Check essential fields
        if (refreshmentPoint.getId() == null || refreshmentPoint.getId().trim().isEmpty()) {
            result.setValid(false);
            result.addErrorMessage("ID is required");
        }
        
        if (refreshmentPoint.getName() == null || refreshmentPoint.getName().trim().isEmpty()) {
            result.setValid(false);
            result.addErrorMessage("Name is required");
        }
        
        if (refreshmentPoint.getLocation() == null || refreshmentPoint.getLocation().trim().isEmpty()) {
            result.setValid(false);
            result.addErrorMessage("Location is required");
        }
        
        // Validate status
        String status = refreshmentPoint.getStatus();
        if (status != null) {
            String upperStatus = status.toUpperCase();
            if (!upperStatus.equals("ACTIVE") && !upperStatus.equals("INACTIVE") && 
                !upperStatus.equals("UNDER_MAINTENANCE")) {
                result.setValid(false);
                result.addErrorMessage("Status must be ACTIVE, INACTIVE, or UNDER_MAINTENANCE");
            }
        }
        
        // Validate capacity
        if (refreshmentPoint.getCapacity() < 0) {
            result.setValid(false);
            result.addErrorMessage("Capacity cannot be negative");
        }
        
        // Validate email format if provided
        if (refreshmentPoint.getContactEmail() != null && !refreshmentPoint.getContactEmail().trim().isEmpty()) {
            String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
            if (!refreshmentPoint.getContactEmail().matches(emailRegex)) {
                result.setValid(false);
                result.addErrorMessage("Invalid email format");
            }
        }
        
        // Validate phone format if provided
        if (refreshmentPoint.getContactPhone() != null && !refreshmentPoint.getContactPhone().trim().isEmpty()) {
            String phoneRegex = "^[0-9\\s\\-\\(\\)]+$";
            if (!refreshmentPoint.getContactPhone().matches(phoneRegex)) {
                result.setValid(false);
                result.addErrorMessage("Invalid phone number format");
            }
        }
        
        // If no errors were added, the data is valid
        if (result.getErrorMessages().isEmpty()) {
            result.setValid(true);
        }
        
        return result;
    }
    
    /**
     * Stores the modified refreshment point data.
     * This method handles the "Stores the modified data" step from the use case.
     * 
     * @param refreshmentPoint the modified refreshment point to store
     * @return true if storage was successful, false otherwise
     */
    public boolean storeModifiedRefreshmentPoint(RefreshmentPoint refreshmentPoint) {
        if (refreshmentPoint == null) {
            System.err.println("Cannot store null refreshment point");
            return false;
        }
        
        if (!refreshmentPoint.validate()) {
            System.err.println("Refreshment point data is invalid");
            return false;
        }
        
        // Check if operation is already in progress (prevent multiple submissions)
        if (operationInProgress) {
            System.err.println("Operation already in progress. Please wait for completion.");
            return false;
        }
        
        // Lock the operation to prevent multiple submissions
        operationInProgress = true;
        
        try {
            // Load all existing points
            List<RefreshmentPoint> allPoints = loadAllRefreshmentPoints();
            
            // Find and replace the point with the same ID
            boolean found = false;
            for (int i = 0; i < allPoints.size(); i++) {
                if (refreshmentPoint.getId().equals(allPoints.get(i).getId())) {
                    allPoints.set(i, refreshmentPoint);
                    found = true;
                    break;
                }
            }
            
            // If not found, add as new
            if (!found) {
                allPoints.add(refreshmentPoint);
            }
            
            // Save all points back to file
            if (saveAllRefreshmentPoints(allPoints)) {
                System.out.println("Successfully stored modified refreshment point: " + refreshmentPoint.getName());
                return true;
            } else {
                System.err.println("Failed to save refreshment point data");
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("Error storing refreshment point: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // Unlock the operation
            operationInProgress = false;
        }
    }
    
    /**
     * Confirms the modification operation.
     * This simulates the confirmation step from the use case.
     * 
     * @param originalPoint the original refreshment point (for comparison)
     * @param modifiedPoint the modified refreshment point
     * @return true if confirmed, false otherwise
     */
    public boolean confirmModification(RefreshmentPoint originalPoint, RefreshmentPoint modifiedPoint) {
        if (originalPoint == null || modifiedPoint == null) {
            return false;
        }
        
        System.out.println("\n=== CONFIRMATION REQUIRED ===");
        System.out.println("You are about to modify the following refreshment point:");
        System.out.println();
        System.out.println("ORIGINAL DATA:");
        System.out.println(originalPoint.toDetailedString());
        System.out.println();
        System.out.println("MODIFIED DATA:");
        System.out.println(modifiedPoint.toDetailedString());
        System.out.println();
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you confirm these changes? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        return confirmation.equals("yes") || confirmation.equals("y");
    }
    
    /**
     * Simulates server connection interruption as mentioned in exit conditions.
     * 
     * @return true if connection is available, false if interrupted
     */
    public boolean checkServerConnection() {
        // In a real application, this would check actual server connectivity
        // For simulation, we'll randomly determine connection status (90% chance of success)
        double random = Math.random();
        boolean connected = random < 0.9; // 90% chance of being connected
        
        if (!connected) {
            System.err.println("ERROR: Connection to server ETOUR interrupted.");
        }
        
        return connected;
    }
    
    /**
     * Loads all refreshment points from the data file.
     * 
     * @return list of all refreshment points
     */
    @SuppressWarnings("unchecked")
    private List<RefreshmentPoint> loadAllRefreshmentPoints() {
        File file = new File(DATA_FILE);
        
        // If file doesn't exist, create some sample data
        if (!file.exists()) {
            createSampleData();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return (List<RefreshmentPoint>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Data file not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading refreshment points: " + e.getMessage());
        }
        
        // Return empty list if loading fails
        return new ArrayList<>();
    }
    
    /**
     * Saves all refreshment points to the data file.
     * 
     * @param refreshmentPoints list of refreshment points to save
     * @return true if save was successful, false otherwise
     */
    private boolean saveAllRefreshmentPoints(List<RefreshmentPoint> refreshmentPoints) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(refreshmentPoints);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving refreshment points: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Creates sample refreshment point data for testing/demonstration.
     */
    private void createSampleData() {
        List<RefreshmentPoint> samplePoints = new ArrayList<>();
        
        samplePoints.add(new RefreshmentPoint(
                "RP001", 
                "Mountain View Rest Area", 
                "Highway 101, Mile Marker 45",
                "Scenic rest area with mountain views",
                "Restrooms, Picnic Tables, Water Fountain, Vending Machines",
                "ACTIVE",
                true,
                50,
                "(555) 123-4567",
                "mountainview@etour.example.com",
                "24/7"
        ));
        
        samplePoints.add(new RefreshmentPoint(
                "RP002", 
                "Riverside Park Stop", 
                "Downtown Riverfront",
                "Urban park with rest facilities",
                "Restrooms, Benches, Tourist Information, Café",
                "ACTIVE",
                true,
                100,
                "(555) 987-6543",
                "riverside@etour.example.com",
                "6:00 AM - 10:00 PM"
        ));
        
        samplePoints.add(new RefreshmentPoint(
                "RP003", 
                "Forest Trail Rest Point", 
                "National Forest, Trailhead 3",
                "Rest point along hiking trail",
                "Water Source, Basic Shelter, Trash Bins",
                "UNDER_MAINTENANCE",
                false,
                20,
                "(555) 456-7890",
                "foresttrail@etour.example.com",
                "Dawn to Dusk"
        ));
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(samplePoints);
            System.out.println("Created sample refreshment point data.");
        } catch (IOException e) {
            System.err.println("Error creating sample data: " + e.getMessage());
        }
    }
    
    /**
     * Inner class to represent validation results.
     * This is used to return both validation status and error messages.
     */
    public static class ValidationResult {
        private boolean isValid;
        private List<String> errorMessages;
        
        public ValidationResult() {
            this.isValid = false;
            this.errorMessages = new ArrayList<>();
        }
        
        public boolean isValid() {
            return isValid;
        }
        
        public void setValid(boolean valid) {
            this.isValid = valid;
        }
        
        public List<String> getErrorMessages() {
            return errorMessages;
        }
        
        public void addErrorMessage(String message) {
            this.errorMessages.add(message);
        }
        
        public String getFormattedErrors() {
            if (errorMessages.isEmpty()) {
                return "No errors";
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append("Validation Errors:\n");
            for (String error : errorMessages) {
                sb.append("  - ").append(error).append("\n");
            }
            return sb.toString();
        }
    }
    
    /**
     * Checks if an operation is already in progress.
     * Used to prevent multiple submissions as per quality requirements.
     * 
     * @return true if an operation is in progress, false otherwise
     */
    public boolean isOperationInProgress() {
        return operationInProgress;
    }
    
    /**
     * Cancels the current operation.
     * This handles the "Operator Agency cancels the operation" exit condition.
     */
    public void cancelOperation() {
        if (operationInProgress) {
            operationInProgress = false;
            System.out.println("Operation cancelled by agency operator.");
        }
    }
}