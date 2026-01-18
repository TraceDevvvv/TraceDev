```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

/**
 * Represents a single class in the system.
 * This class holds basic information about a course or educational class.
 */
class Class {
    private final String classId;
    private String className;
    private String description;

    /**
     * Constructs a new Class instance.
     *
     * @param classId     A unique identifier for the class.
     * @param className   The name of the class.
     * @param description A brief description of the class.
     */
    public Class(String classId, String className, String description) {
        if (classId == null || classId.trim().isEmpty()) {
            throw new IllegalArgumentException("Class ID cannot be null or empty.");
        }
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be null or empty.");
        }
        this.classId = classId;
        this.className = className;
        this.description = description;
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getDescription() {
        return description;
    }

    public void setClassName(String className) {
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be null or empty.");
        }
        this.className = className;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Class [ID: " + classId + ", Name: " + className + ", Description: " + description + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return classId.equals(aClass.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId);
    }
}

/**
 * Manages the collection of classes, acting as an archive or database.
 * Provides methods for adding, deleting, and retrieving classes.
 */
class ClassArchive {
    private final List<Class> classes;

    /**
     * Constructs an empty ClassArchive.
     */
    public ClassArchive() {
        this.classes = new ArrayList<>();
    }

    /**
     * Adds a new class to the archive.
     *
     * @param newClass The class to be added.
     * @return true if the class was added successfully, false if a class with the same ID already exists.
     */
    public boolean addClass(Class newClass) {
        if (newClass == null) {
            throw new IllegalArgumentException("Cannot add a null class.");
        }
        // Check for duplicate class ID
        if (classes.stream().anyMatch(c -> c.getClassId().equals(newClass.getClassId()))) {
            System.out.println("Error: Class with ID '" + newClass.getClassId() + "' already exists.");
            return false;
        }
        classes.add(newClass);
        System.out.println("Class '" + newClass.getClassName() + "' (ID: " + newClass.getClassId() + ") added to archive.");
        return true;
    }

    /**
     * Deletes a class from the archive by its ID.
     *
     * @param classId The ID of the class to delete.
     * @return true if the class was found and deleted, false otherwise.
     */
    public boolean deleteClass(String classId) {
        if (classId == null || classId.trim().isEmpty()) {
            throw new IllegalArgumentException("Class ID cannot be null or empty for deletion.");
        }
        boolean removed = classes.removeIf(c -> c.getClassId().equals(classId));
        if (removed) {
            System.out.println("Class with ID '" + classId + "' successfully deleted from archive.");
        } else {
            System.out.println("Error: Class with ID '" + classId + "' not found in archive.");
        }
        return removed;
    }

    /**
     * Retrieves a class by its ID.
     *
     * @param classId The ID of the class to retrieve.
     * @return An Optional containing the Class if found, or an empty Optional if not found.
     */
    public Optional<Class> getClassById(String classId) {
        if (classId == null || classId.trim().isEmpty()) {
            return Optional.empty(); // Or throw IllegalArgumentException
        }
        return classes.stream()
                .filter(c -> c.getClassId().equals(classId))
                .findFirst();
    }

    /**
     * Returns an unmodifiable list of all classes in the archive.
     *
     * @return A List of Class objects.
     */
    public List<Class> getAllClasses() {
        return Collections.unmodifiableList(classes);
    }

    /**
     * Checks if the archive is empty.
     *
     * @return true if the archive contains no classes, false otherwise.
     */
    public boolean isEmpty() {
        return classes.isEmpty();
    }
}

/**
 * Simulates an administrator's session, managing login status.
 */
class AdministratorSession {
    private boolean isLoggedIn;
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password"; // In a real system, this would be hashed and secured.

    /**
     * Constructs a new AdministratorSession, initially logged out.
     */
    public AdministratorSession() {
        this.isLoggedIn = false;
    }

    /**
     * Attempts to log in the administrator.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String username, String password) {
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            this.isLoggedIn = true;
            System.out.println("Administrator logged in successfully.");
            return true;
        } else {
            System.out.println("Login failed: Invalid username or password.");
            this.isLoggedIn = false;
            return false;
        }
    }

    /**
     * Logs out the administrator.
     */
    public void logout() {
        this.isLoggedIn = false;
        System.out.println("Administrator logged out.");
    }

    /**
     * Checks if the administrator is currently logged in.
     *
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}

/**
 * Simulates the connection to an external SMOS server.
 */
class SMOSServerConnection {
    private boolean isConnected;

    /**
     * Constructs a new SMOSServerConnection, initially disconnected.
     */
    public SMOSServerConnection() {
        this.isConnected = false;
    }

    /**
     * Attempts to establish a connection to the SMOS server.
     *
     * @return true if connection is successful, false otherwise (simulated).
     */
    public boolean connect() {
        // Simulate connection logic
        System.out.println("Attempting to connect to SMOS server...");
        this.isConnected = true; // Assume success for simulation
        System.out.println("SMOS server connected.");
        return true;
    }

    /**
     * Disconnects from the SMOS server.
     */
    public void disconnect() {
        if (isConnected) {
            System.out.println("Disconnecting from SMOS server...");
            this.isConnected = false;
            System.out.println("SMOS server disconnected.");
        } else {
            System.out.println("SMOS server is already disconnected.");
        }
    }

    /**
     * Checks the current connection status to the SMOS server.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return isConnected;
    }
}

/**
 * Main class representing the Class Management System.
 * Orchestrates the use case "DeleteClass" based on the provided requirements.
 */
public class ClassManagementSystem {
    private final AdministratorSession adminSession;
    private final ClassArchive classArchive;
    private final SMOSServerConnection smosConnection;
    private final Scanner scanner;

    /**
     * Initializes the ClassManagementSystem with necessary components.
     */
    public ClassManagementSystem() {
        this.adminSession = new AdministratorSession();
        this.classArchive = new ClassArchive();
        this.smosConnection = new SMOSServerConnection();
        this.scanner = new Scanner(System.in);
        // Populate with some initial data for demonstration
        initializeArchive();
    }

    /**
     * Populates the class archive with some sample classes.
     */
    private void initializeArchive() {
        System.out.println("Initializing class archive with sample data...");
        classArchive.addClass(new Class("CS101", "Introduction to Computer Science", "Fundamentals of programming and algorithms."));
        classArchive.addClass(new Class("MA201", "Calculus I", "Differential and integral calculus."));
        classArchive.addClass(new Class("PH305", "Quantum Physics", "Principles of quantum mechanics."));
        System.out.println("Archive initialized.");
        displayAllClasses();
    }

    /**
     * Simulates the administrator login process.
     *
     * @return true if login is successful, false otherwise.
     */
    public boolean simulateAdminLogin() {
        System.out.println("\n--- Administrator Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        return adminSession.login(username, password);
    }

    /**
     * Displays detailed information for a specific class.
     * This simulates the "viewdettagliSlasse" use case.
     *
     * @param classId The ID of the class to view.
     * @return true if the class was found and displayed, false otherwise.
     */
    public boolean simulateViewClassDetails(String classId) {
        if (!adminSession.isLoggedIn()) {
            System.out.println("Error: Administrator not logged in. Cannot view class details.");
            return false;
        }
        System.out.println("\n--- Viewing Class Details (viewdettagliSlasse) ---");
        Optional<Class> foundClass = classArchive.getClassById(classId);
        if (foundClass.isPresent()) {
            System.out.println("Detailed Information for " + foundClass.get().getClassName() + ":");
            System.out.println(foundClass.get());
            return true;
        } else {
            System.out.println("Error: Class with ID '" + classId + "' not found.");
            return false;
        }
    }

    /**
     * Simulates the deletion of a class from the archive.
     * This is the core "DeleteClass" event sequence step 1.
     *
     * @param classId The ID of the class to delete.
     * @return true if the class was successfully deleted, false otherwise.
     */
    public boolean simulateDeleteClass(String classId) {
        if (!adminSession.isLoggedIn()) {
            System.out.println("Error: Administrator not logged in. Cannot delete class.");
            return false;
        }
        System.out.println("\n--- Deleting Class (User clicks 'Delete' button) ---");
        System.out.println("Attempting to delete class with ID: " + classId);
        return classArchive.deleteClass(classId);
    }

    /**
     * Displays the current list of all classes in the archive.
     * This simulates the "Displays the list of updated classes" event sequence step 2.
     */
    public void displayAllClasses() {
        System.out.println("\n--- Current List of Classes ---");
        List<Class> currentClasses = classArchive.getAllClasses();
        if (currentClasses.isEmpty()) {
            System.out.println("No classes in the archive.");
        } else {
            for (Class c : currentClasses) {
                System.out.println(c);
            }
        }
    }

    /**
     * Simulates the interruption of the SMOS server connection.
     * This fulfills the postcondition "Connection to the interrupted SMOS server".
     */
    public void simulateSMOSConnectionInterruption() {
        System.out.println("\n--- Simulating SMOS Server Connection Interruption ---");
        if (smosConnection.isConnected()) {
            smosConnection.disconnect();
        } else {
            System.out.println("SMOS server was already disconnected.");
        }
    }

    /**
     * The main method to run the Class Management System simulation.
     * It follows the use case steps and preconditions.
     */
    public static void main(String[] args) {
        ClassManagementSystem system = new ClassManagementSystem();
        String classIdToDelete = "MA201"; // Example class to delete

        // Precondition 1: The user is logged in to the system as an administrator
        if (!system.simulateAdminLogin()) {
            System.out.println("System cannot proceed without administrator login.");
