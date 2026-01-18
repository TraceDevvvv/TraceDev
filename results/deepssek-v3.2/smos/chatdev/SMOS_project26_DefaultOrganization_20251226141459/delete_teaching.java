/**
 * This program simulates the "Delete teaching" use case for an administrator.
 * It includes classes for Teaching, TeachingArchive, Administrator, and SMOSConnection.
 * The program allows an administrator to delete a teaching from the archive,
 * display the updated list of teachings, and interrupt the SMOS server connection.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
/**
 * Represents a teaching with an ID, name, and description.
 */
class Teaching {
    private String id;
    private String name;
    private String description;
    public Teaching(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    @Override
    public String toString() {
        return "Teaching ID: " + id + ", Name: " + name + ", Description: " + description;
    }
}
/**
 * Manages a collection of teachings (archive) with CRUD operations.
 */
class TeachingArchive {
    private HashMap<String, Teaching> teachings;
    public TeachingArchive() {
        teachings = new HashMap<>();
        // Initialize with some sample teachings for demonstration
        initializeSampleTeachings();
    }
    private void initializeSampleTeachings() {
        teachings.put("T001", new Teaching("T001", "Mathematics", "Basic algebra and calculus"));
        teachings.put("T002", new Teaching("T002", "Physics", "Mechanics and thermodynamics"));
        teachings.put("T003", new Teaching("T003", "Chemistry", "Organic and inorganic chemistry"));
    }
    /**
     * Deletes a teaching from the archive by ID.
     * @param teachingId The ID of the teaching to delete.
     * @return true if deletion was successful, false if teaching not found.
     */
    public boolean deleteTeaching(String teachingId) {
        if (teachings.containsKey(teachingId)) {
            teachings.remove(teachingId);
            return true;
        }
        return false;
    }
    /**
     * Returns a list of all current teachings in the archive.
     * @return List of Teaching objects.
     */
    public List<Teaching> getAllTeachings() {
        return new ArrayList<>(teachings.values());
    }
    /**
     * Displays detailed information of a specific teaching.
     * Simulates the "displaydeddailsignment" use case precondition.
     * @param teachingId The ID of the teaching to display.
     */
    public void displayTeachingDetails(String teachingId) {
        Teaching teaching = teachings.get(teachingId);
        if (teaching != null) {
            System.out.println("\n=== Detailed Information ===");
            System.out.println(teaching);
            System.out.println("============================\n");
        } else {
            System.out.println("Teaching with ID " + teachingId + " not found.");
        }
    }
}
/**
 * Simulates a connection to the SMOS server.
 * According to the postcondition, the connection is interrupted after deleting a teaching.
 */
class SMOSConnection {
    private boolean connected;
    public SMOSConnection() {
        connected = true; // Simulate initial connection
        System.out.println("Connected to SMOS server.");
    }
    /**
     * Interrupts the connection to the SMOS server.
     * This is called after a teaching is deleted as per postconditions.
     */
    public void interruptConnection() {
        if (connected) {
            connected = false;
            System.out.println("Connection to SMOS server interrupted.");
        }
    }
    public boolean isConnected() { return connected; }
}
/**
 * Represents an administrator user who can delete teachings.
 */
class Administrator {
    private String username;
    public Administrator(String username) {
        this.username = username;
    }
    public String getUsername() { return username; }
    /**
     * Simulates the administrator clicking the "Delete" button.
     * This triggers the deletion process.
     * @param archive The TeachingArchive to delete from.
     * @param teachingId The ID of the teaching to delete.
     * @param smosConnection The SMOSConnection to interrupt after deletion.
     */
    public void deleteTeaching(TeachingArchive archive, String teachingId, SMOSConnection smosConnection) {
        boolean success = archive.deleteTeaching(teachingId);
        if (success) {
            System.out.println("Administrator '" + username + "' deleted teaching with ID: " + teachingId);
            smosConnection.interruptConnection(); // Postcondition: interrupt SMOS connection
        } else {
            System.out.println("Deletion failed. Teaching ID not found: " + teachingId);
        }
    }
}
/**
 * Main class that runs the complete application.
 */
public class delete_teaching {
    public static void main(String[] args) {
        // Initialization
        TeachingArchive archive = new TeachingArchive();
        SMOSConnection smosConnection = new SMOSConnection();
        Administrator admin = new Administrator("admin_user");
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Teaching Management System ===\n");
        System.out.println("Administrator logged in: " + admin.getUsername());
        // Precondition: Display initial list of teachings
        System.out.println("\nCurrent teachings in archive:");
        displayTeachings(archive);
        // Simulate detailed view of a teaching (precondition: 'displaydeddailsignment')
        System.out.print("\nEnter the Teaching ID to view details: ");
        String viewId = scanner.nextLine();
        archive.displayTeachingDetails(viewId);
        // Administrator clicks "Delete" button
        System.out.println("Click 'Delete' button to proceed with deletion.");
        System.out.print("Enter the Teaching ID to delete: ");
        String deleteId = scanner.nextLine();
        // Event sequence 1: Eliminate teaching from the archive
        admin.deleteTeaching(archive, deleteId, smosConnection);
        // Event sequence 2: Displays the list of updated teachings
        System.out.println("\nUpdated teachings in archive:");
        displayTeachings(archive);
        // Postcondition check using proper verification method
        System.out.println("\n=== Postconditions ===");
        System.out.println("Teaching deleted: " + !teachingExists(archive, deleteId));
        System.out.println("SMOS connection interrupted: " + !smosConnection.isConnected());
        scanner.close();
        System.out.println("\nProgram completed.");
    }
    /**
     * Helper method to display all teachings in the archive.
     * @param archive The TeachingArchive to display.
     */
    private static void displayTeachings(TeachingArchive archive) {
        List<Teaching> teachings = archive.getAllTeachings();
        if (teachings.isEmpty()) {
            System.out.println("No teachings available.");
        } else {
            for (Teaching t : teachings) {
                System.out.println(t);
            }
        }
    }
    /**
     * Helper method to check if a teaching exists in the archive by ID.
     * This replaces the unreliable contains check with proper ID comparison.
     * @param archive The TeachingArchive to search.
     * @param teachingId The ID of the teaching to check.
     * @return true if teaching exists, false otherwise.
     */
    private static boolean teachingExists(TeachingArchive archive, String teachingId) {
        for (Teaching t : archive.getAllTeachings()) {
            if (t.getId().equals(teachingId)) {
                return true;
            }
        }
        return false;
    }
}