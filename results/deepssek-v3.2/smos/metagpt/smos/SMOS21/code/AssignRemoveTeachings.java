import java.util.*;

/**
 * Represents a Teaching with an ID and name.
 */
class Teaching {
    private String id;
    private String name;

    public Teaching(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teaching teaching = (Teaching) o;
        return Objects.equals(id, teaching.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Teaching{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}

/**
 * Represents an Address with an ID and a set of assigned teachings.
 */
class Address {
    private String id;
    private String description;
    private Set<Teaching> assignedTeachings;

    public Address(String id, String description) {
        this.id = id;
        this.description = description;
        this.assignedTeachings = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Set<Teaching> getAssignedTeachings() {
        return new HashSet<>(assignedTeachings); // Return a copy to preserve encapsulation
    }

    /**
     * Assign a teaching to this address.
     * @param teaching Teaching to assign.
     * @return true if teaching was not already assigned, false otherwise.
     */
    public boolean assignTeaching(Teaching teaching) {
        if (teaching == null) {
            throw new IllegalArgumentException("Teaching cannot be null");
        }
        return assignedTeachings.add(teaching);
    }

    /**
     * Remove a teaching from this address.
     * @param teaching Teaching to remove.
     * @return true if teaching was assigned and removed, false otherwise.
     */
    public boolean removeTeaching(Teaching teaching) {
        if (teaching == null) {
            throw new IllegalArgumentException("Teaching cannot be null");
        }
        return assignedTeachings.remove(teaching);
    }

    /**
     * Bulk assign/remove teachings based on a set of selected teaching IDs.
     * This simulates the form submission where user selects teachings to assign/remove.
     * @param selectedTeachingIds Set of teaching IDs that should be assigned after operation.
     * @param availableTeachings Map of all available teachings (ID -> Teaching).
     * @return Set of teaching IDs that were actually changed (added or removed).
     */
    public Set<String> updateTeachings(Set<String> selectedTeachingIds, Map<String, Teaching> availableTeachings) {
        if (selectedTeachingIds == null) {
            throw new IllegalArgumentException("Selected teaching IDs cannot be null");
        }
        if (availableTeachings == null) {
            throw new IllegalArgumentException("Available teachings map cannot be null");
        }

        Set<String> changedIds = new HashSet<>();
        
        // Add teachings that are selected but not currently assigned
        for (String teachingId : selectedTeachingIds) {
            Teaching teaching = availableTeachings.get(teachingId);
            if (teaching != null && !assignedTeachings.contains(teaching)) {
                assignedTeachings.add(teaching);
                changedIds.add(teachingId);
            }
        }

        // Remove teachings that are currently assigned but not in selected set
        // Use iterator to safely remove during iteration
        Iterator<Teaching> iterator = assignedTeachings.iterator();
        while (iterator.hasNext()) {
            Teaching teaching = iterator.next();
            if (!selectedTeachingIds.contains(teaching.getId())) {
                iterator.remove();
                changedIds.add(teaching.getId());
            }
        }

        return changedIds;
    }

    @Override
    public String toString() {
        return "Address{" + "id='" + id + '\'' + ", description='" + description + '\'' + 
               ", assignedTeachings=" + assignedTeachings + '}';
    }
}

/**
 * Represents an Administrator user.
 */
class Administrator {
    private String username;
    private boolean loggedIn;

    public Administrator(String username) {
        this.username = username;
        this.loggedIn = false;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void login() {
        loggedIn = true;
        System.out.println("Administrator " + username + " logged in.");
    }

    public void logout() {
        loggedIn = false;
        System.out.println("Administrator " + username + " logged out.");
    }

    /**
     * Simulates viewing address details (precondition for the use case).
     * @param address Address to view.
     */
    public void viewAddressDetails(Address address) {
        if (!loggedIn) {
            throw new IllegalStateException("Administrator must be logged in to view address details");
        }
        System.out.println("Viewing details for address: " + address.getDescription());
        System.out.println("Currently assigned teachings:");
        for (Teaching teaching : address.getAssignedTeachings()) {
            System.out.println("  - " + teaching.getName() + " (ID: " + teaching.getId() + ")");
        }
    }

    /**
     * Simulates clicking the "Teachings Address" button.
     * This triggers the display of the teaching assignment form.
     * @param address Address to manage teachings for.
     * @param availableTeachings All available teachings in the system.
     * @return A form object containing current assignments and available options.
     */
    public TeachingAssignmentForm clickTeachingsAddressButton(Address address, Map<String, Teaching> availableTeachings) {
        if (!loggedIn) {
            throw new IllegalStateException("Administrator must be logged in");
        }
        System.out.println("Opening teaching assignment form for address: " + address.getDescription());
        return new TeachingAssignmentForm(address, availableTeachings);
    }
}

/**
 * Represents the teaching assignment form where user can select teachings.
 */
class TeachingAssignmentForm {
    private Address address;
    private Map<String, Teaching> availableTeachings;
    private Set<String> selectedTeachingIds;

    public TeachingAssignmentForm(Address address, Map<String, Teaching> availableTeachings) {
        this.address = address;
        this.availableTeachings = new HashMap<>(availableTeachings);
        this.selectedTeachingIds = new HashSet<>();
        
        // Initialize with currently assigned teachings
        for (Teaching teaching : address.getAssignedTeachings()) {
            selectedTeachingIds.add(teaching.getId());
        }
        
        displayForm();
    }

    private void displayForm() {
        System.out.println("\n=== Teaching Assignment Form ===");
        System.out.println("Address: " + address.getDescription());
        System.out.println("\nAvailable teachings (✓ = currently assigned):");
        
        // Display all available teachings with indication of current assignment
        for (Teaching teaching : availableTeachings.values()) {
            boolean isAssigned = address.getAssignedTeachings().contains(teaching);
            System.out.printf("  [%s] %s (ID: %s)%n", 
                isAssigned ? "✓" : " ", 
                teaching.getName(), 
                teaching.getId());
        }
        
        System.out.println("\nInstructions: Modify the selectedTeachingIds set to choose teachings.");
        System.out.println("Current selection: " + selectedTeachingIds);
    }

    /**
     * Updates the selection of teachings.
     * @param teachingIdsToAdd Set of teaching IDs to add to selection.
     * @param teachingIdsToRemove Set of teaching IDs to remove from selection.
     */
    public void updateSelection(Set<String> teachingIdsToAdd, Set<String> teachingIdsToRemove) {
        // Validate all teaching IDs exist
        for (String teachingId : teachingIdsToAdd) {
            if (!availableTeachings.containsKey(teachingId)) {
                throw new IllegalArgumentException("Teaching ID not found: " + teachingId);
            }
        }
        for (String teachingId : teachingIdsToRemove) {
            if (!availableTeachings.containsKey(teachingId)) {
                throw new IllegalArgumentException("Teaching ID not found: " + teachingId);
            }
        }
        
        selectedTeachingIds.addAll(teachingIdsToAdd);
        selectedTeachingIds.removeAll(teachingIdsToRemove);
        
        System.out.println("Selection updated. Current selection: " + selectedTeachingIds);
    }

    /**
     * Simulates clicking the "Send" button to submit the form.
     * @return true if changes were made, false otherwise.
     */
    public boolean clickSendButton() {
        System.out.println("\nSubmitting teaching assignments...");
        Set<String> changedIds = address.updateTeachings(selectedTeachingIds, availableTeachings);
        
        if (changedIds.isEmpty()) {
            System.out.println("No changes were made.");
            return false;
        } else {
            System.out.println("Successfully updated " + changedIds.size() + " teaching(s): " + changedIds);
            return true;
        }
    }

    public Set<String> getSelectedTeachingIds() {
        return new HashSet<>(selectedTeachingIds);
    }
}

/**
 * Main class that simulates the complete AssignRemoveTeachings use case flow.
 */
public class AssignRemoveTeachings {
    
    /**
     * Simulates the SMOS server connection status.
     */
    private static boolean smosServerConnected = true;
    
    /**
     * Checks if SMOS server is connected.
     * @throws IllegalStateException if server is not connected.
     */
    private static void checkServerConnection() {
        if (!smosServerConnected) {
            throw new IllegalStateException("Connection to the SMOS server interrupted");
        }
    }
    
    /**
     * Simulates an intermittent server connection issue.
     */
    private static void simulateServerInterruption() {
        // Simulate 10% chance of server interruption
        if (Math.random() < 0.1) {
            smosServerConnected = false;
            System.out.println("WARNING: Connection to SMOS server interrupted!");
        }
    }
    
    /**
     * Main method demonstrating the complete use case flow.
     */
    public static void main(String[] args) {
        try {
            System.out.println("=== Starting AssignRemoveTeachings Use Case Simulation ===\n");
            
            // 1. Setup initial data
            Map<String, Teaching> availableTeachings = new HashMap<>();
            availableTeachings.put("T001", new Teaching("T001", "Mathematics"));
            availableTeachings.put("T002", new Teaching("T002", "Physics"));
            availableTeachings.put("T003", new Teaching("T003", "Chemistry"));
            availableTeachings.put("T004", new Teaching("T004", "Biology"));
            availableTeachings.put("T005", new Teaching("T005", "Computer Science"));
            
            Address address = new Address("A001", "Main Campus Building A, Room 101");
            
            // Pre-assign some teachings
            address.assignTeaching(availableTeachings.get("T001"));
            address.assignTeaching(availableTeachings.get("T003"));
            
            Administrator admin = new Administrator("admin1");
            
            // 2. Preconditions: Administrator logs in and views address details
            admin.login();
            checkServerConnection();
            
            System.out.println("\n--- Step 1: View address details (precondition) ---");
            admin.viewAddressDetails(address);
            
            // 3. User clicks on "Teachings Address" button
            System.out.println("\n--- Step 2: Click 'Teachings Address' button ---");
            TeachingAssignmentForm form = admin.clickTeachingsAddressButton(address, availableTeachings);
            
            // 4. User selects teachings to be assigned or removed
            System.out.println("\n--- Step 3: Select teachings to assign/remove ---");
            
            // User decides to add Physics and Computer Science, remove Chemistry
            Set<String> teachingsToAdd = new HashSet<>(Arrays.asList("T002", "T005"));
            Set<String> teachingsToRemove = new HashSet<>(Arrays.asList("T003"));
            
            form.updateSelection(teachingsToAdd, teachingsToRemove);
            
            // 5. User clicks on "Send" button
            System.out.println("\n--- Step 4: Click 'Send' button ---");
            
            // Check for potential server interruption before processing
            simulateServerInterruption();
            checkServerConnection();
            
            boolean changesMade = form.clickSendButton();
            
            if (changesMade) {
                // 6. System associates/removes selected teachings at the address
                // (Already done in clickSendButton method)
                
                // 7. Back to display details of the address
                System.out.println("\n--- Step 5: Return to address details display ---");
                admin.viewAddressDetails(address);
                
                // Verify the changes
                Set<Teaching> finalTeachings = address.getAssignedTeachings();
                System.out.println("\nFinal assigned teachings:");
                for (Teaching teaching : finalTeachings) {
                    System.out.println("  - " + teaching.getName());
                }
                
                // Postconditions verification
                System.out.println("\n=== Postconditions ===");
                System.out.println("✓ Administrator performed the operation");
                System.out.println("✓ SMOS server connection: " + (smosServerConnected ? "Active" : "Interrupted"));
                System.out.println("✓ Teachings relating to the address have been changed");
                
            } else {
                System.out.println("No changes were applied.");
            }
            
            // Cleanup
            admin.logout();
            
        } catch (IllegalStateException e) {
            System.err.println("Operation failed: " + e.getMessage());
            System.err.println("Use case cannot proceed due to state violation.");
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n=== Use Case Simulation Complete ===");
    }
}