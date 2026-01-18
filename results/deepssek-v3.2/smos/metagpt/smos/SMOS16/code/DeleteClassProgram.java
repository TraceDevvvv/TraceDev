import java.util.*;

/**
 * Main program to demonstrate the DeleteClass use case.
 * This program simulates an administrator deleting a class from an archive.
 * It includes user authentication, class viewing, deletion, and server connection management.
 */
public class DeleteClassProgram {
    
    public static void main(String[] args) {
        System.out.println("=== DeleteClass Use Case Simulation ===");
        
        // Initialize components
        ClassArchive archive = new ClassArchive();
        SMOSConnection smosConnection = new SMOSConnection();
        Administrator admin = new Administrator(archive, smosConnection);
        
        // Simulate preconditions: admin logged in and viewing class details
        System.out.println("\n1. Preconditions:");
        admin.login();
        admin.viewClassDetails("CS101");
        
        // Simulate delete button click
        System.out.println("\n2. Deleting class:");
        admin.clickDeleteButton("CS101");
        
        System.out.println("\n=== Simulation Complete ===");
    }
}

/**
 * Represents an Administrator user with permissions to delete classes.
 */
class Administrator {
    private ClassArchive archive;
    private SMOSConnection smosConnection;
    private boolean isLoggedIn = false;
    private boolean hasViewedDetails = false;
    
    public Administrator(ClassArchive archive, SMOSConnection smosConnection) {
        this.archive = archive;
        this.smosConnection = smosConnection;
    }
    
    /**
     * Simulates administrator login.
     * Sets isLoggedIn flag to true.
     */
    public void login() {
        isLoggedIn = true;
        System.out.println("Administrator logged in successfully.");
    }
    
    /**
     * Simulates viewing detailed information of a class.
     * Precondition: User must be logged in.
     * 
     * @param classId The ID of the class to view
     */
    public void viewClassDetails(String classId) {
        if (!isLoggedIn) {
            System.out.println("Error: User must be logged in to view class details.");
            return;
        }
        
        boolean exists = archive.getClass(classId) != null;
        if (exists) {
            hasViewedDetails = true;
            System.out.println("Viewing details for class: " + classId);
        } else {
            System.out.println("Error: Class " + classId + " not found.");
        }
    }
    
    /**
     * Simulates clicking the Delete button for a class.
     * Validates preconditions, deletes the class, and updates the display.
     * 
     * @param classId The ID of the class to delete
     */
    public void clickDeleteButton(String classId) {
        // Check preconditions
        if (!isLoggedIn) {
            System.out.println("Error: User must be logged in as administrator.");
            return;
        }
        
        if (!hasViewedDetails) {
            System.out.println("Error: User must have viewed class details before deleting.");
            return;
        }
        
        System.out.println("Delete button clicked for class: " + classId);
        
        // Event 1: Delete the class from the archive
        boolean deleted = archive.deleteClass(classId);
        
        if (deleted) {
            System.out.println("Successfully deleted class: " + classId);
            
            // Event 2: Display the updated list of classes
            System.out.println("\nUpdated class list:");
            archive.displayAllClasses();
            
            // Postcondition: Connection to SMOS server interrupted
            smosConnection.interrupt();
            System.out.println("Connection to SMOS server interrupted.");
        } else {
            System.out.println("Failed to delete class: " + classId + " (may not exist)");
        }
        
        // Reset flag for next operation
        hasViewedDetails = false;
    }
}

/**
 * Represents an archive of classes with storage and retrieval capabilities.
 */
class ClassArchive {
    private Map<String, Class> classes;
    
    public ClassArchive() {
        classes = new HashMap<>();
        // Initialize with some sample classes
        addClass(new Class("CS101", "Introduction to Computer Science", 30));
        addClass(new Class("MATH201", "Calculus II", 25));
        addClass(new Class("ENG101", "English Composition", 40));
    }
    
    /**
     * Adds a class to the archive.
     * 
     * @param cls The class to add
     */
    private void addClass(Class cls) {
        classes.put(cls.getId(), cls);
    }
    
    /**
     * Retrieves a class by its ID.
     * 
     * @param classId The ID of the class to retrieve
     * @return The Class object, or null if not found
     */
    public Class getClass(String classId) {
        return classes.get(classId);
    }
    
    /**
     * Deletes a class from the archive.
     * 
     * @param classId The ID of the class to delete
     * @return true if deletion was successful, false if class not found
     */
    public boolean deleteClass(String classId) {
        if (!classes.containsKey(classId)) {
            return false;
        }
        
        classes.remove(classId);
        return true;
    }
    
    /**
     * Displays all classes currently in the archive.
     */
    public void displayAllClasses() {
        if (classes.isEmpty()) {
            System.out.println("No classes in archive.");
            return;
        }
        
        for (Class cls : classes.values()) {
            System.out.println(cls);
        }
        System.out.println("Total classes: " + classes.size());
    }
}

/**
 * Represents a Class with basic properties.
 */
class Class {
    private String id;
    private String name;
    private int capacity;
    
    public Class(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    
    @Override
    public String toString() {
        return "Class [ID: " + id + ", Name: " + name + ", Capacity: " + capacity + "]";
    }
}

/**
 * Manages connection to the SMOS server.
 * Simulates connection interruption after class deletion.
 */
class SMOSConnection {
    private boolean connected;
    
    public SMOSConnection() {
        connected = true;
    }
    
    /**
     * Interrupts the connection to the SMOS server.
     * This simulates the postcondition requirement.
     */
    public void interrupt() {
        if (connected) {
            connected = false;
            System.out.println("SMOS connection interrupted.");
        }
    }
    
    public boolean isConnected() {
        return connected;
    }
}