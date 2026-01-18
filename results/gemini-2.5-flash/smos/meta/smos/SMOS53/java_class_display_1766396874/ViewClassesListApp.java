import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents a single academic class with its details.
 * This class acts as a data model for the classes in the system.
 */
class Class {
    private final String classId;
    private final String name;
    private final String instructor;
    private final String schedule;

    /**
     * Constructs a new Class instance.
     *
     * @param classId    A unique identifier for the class.
     * @param name       The name of the class.
     * @param instructor The instructor teaching the class.
     * @param schedule   The schedule of the class (e.g., "Mon/Wed 10:00-11:30 AM").
     */
    public Class(String classId, String name, String instructor, String schedule) {
        this.classId = classId;
        this.name = name;
        this.instructor = instructor;
        this.schedule = schedule;
    }

    /**
     * Returns the unique identifier of the class.
     *
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Returns the name of the class.
     *
     * @return The class name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the instructor of the class.
     *
     * @return The instructor's name.
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Returns the schedule of the class.
     *
     * @return The class schedule.
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * Provides a string representation of the Class object, useful for debugging and display.
     *
     * @return A formatted string containing class details.
     */
    @Override
    public String toString() {
        return String.format("ID: %-5s | Name: %-25s | Instructor: %-20s | Schedule: %s",
                classId, name, instructor, schedule);
    }

    /**
     * Compares this Class object to the specified object. The result is true if and only if
     * the argument is not null and is a Class object that has the same classId as this object.
     *
     * @param o The object to compare this Class against.
     * @return true if the given object represents a Class equivalent to this Class, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return Objects.equals(classId, aClass.classId);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(classId);
    }
}

/**
 * Simulates a data repository for classes.
 * In a real application, this would interact with a database.
 * For this exercise, it uses an in-memory ArrayList.
 */
class ClassRepository {
    private final List<Class> classes;

    /**
     * Initializes the repository with some sample class data.
     */
    public ClassRepository() {
        this.classes = new ArrayList<>();
        // Add sample data
        classes.add(new Class("CS101", "Introduction to Programming", "Dr. Smith", "Mon/Wed 09:00-10:30 AM"));
        classes.add(new Class("MA201", "Calculus I", "Prof. Johnson", "Tue/Thu 11:00-12:30 PM"));
        classes.add(new Class("PH105", "Physics for Engineers", "Dr. Lee", "Mon/Wed/Fri 01:00-02:00 PM"));
        classes.add(new Class("HI300", "World History", "Prof. Davis", "Tue/Thu 09:30-11:00 AM"));
        classes.add(new Class("EN101", "English Composition", "Ms. White", "Mon/Wed 02:00-03:30 PM"));
    }

    /**
     * Retrieves all classes stored in the repository.
     *
     * @return An unmodifiable list of all classes. Returns an empty list if no classes are available.
     */
    public List<Class> getAllClasses() {
        // Return an unmodifiable list to prevent external modification of the internal data.
        return Collections.unmodifiableList(classes);
    }

    /**
     * Retrieves a specific class by its ID.
     *
     * @param classId The ID of the class to retrieve.
     * @return The Class object if found, otherwise null.
     */
    public Class getClassById(String classId) {
        for (Class cls : classes) {
            if (cls.getClassId().equalsIgnoreCase(classId)) {
                return cls;
            }
        }
        return null;
    }
}

/**
 * Simulates an authentication service for staff users.
 * In a real system, this would involve secure credential verification.
 */
class AuthService {
    private static final String STAFF_USERNAME = "ata_staff";
    private static final String STAFF_PASSWORD = "password123"; // In a real app, never store passwords like this!

    /**
     * Attempts to log in a user.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return true if the credentials match the hardcoded staff credentials, false otherwise.
     */
    public boolean login(String username, String password) {
        // Simple hardcoded check for demonstration purposes.
        // In a production system, this would involve database lookups, password hashing, etc.
        return STAFF_USERNAME.equals(username) && STAFF_PASSWORD.equals(password);
    }
}

/**
 * The main application class for displaying a list of classes to ATA staff.
 * This class orchestrates the user interaction, authentication, and data display.
 */
public class ViewClassesListApp {

    private final ClassRepository classRepository;
    private final AuthService authService;
    private final Scanner scanner;

    /**
     * Constructs the ViewClassesListApp.
     * Initializes the class repository, authentication service, and scanner for user input.
     */
    public ViewClassesListApp() {
        this.classRepository = new ClassRepository();
        this.authService = new AuthService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the login process for an ATA staff member.
     *
     * @return true if login is successful, false otherwise.
     */
    private boolean performLogin() {
        System.out.println("--- ATA Staff Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authService.login(username, password)) {
            System.out.println("Login successful! Welcome, ATA Staff.");
            return true;
        } else {
            System.out.println("Login failed. Invalid username or password.");
            return false;
        }
    }

    /**
     * Displays the list of classes to the console.
     *
     * @param classes The list of Class objects to display.
     */
    private void displayClasses(List<Class> classes) {
        System.out.println("\n--- Available Classes ---");
        if (classes.isEmpty()) {
            System.out.println("No classes are currently available in the system.");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-25s | %-20s | %s%n", "ID", "Class Name", "Instructor", "Schedule");
        System.out.println("----------------------------------------------------------------------------------------------------");
        for (Class cls : classes) {
            System.out.println(cls);
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("To access the registry of a class, please enter its Class ID.");
    }

    /**
     * Simulates accessing the registry for a given class ID.
     * In a real application, this would navigate to a detailed view or perform specific actions.
     *
     * @param classId The ID of the class whose registry is to be accessed.
     */
    private void accessClassRegistry(String classId) {
        Class selectedClass = classRepository.getClassById(classId);
        if (selectedClass != null) {
            System.out.println("\n--- Accessing Registry for Class: " + selectedClass.getName() + " (" + selectedClass.getClassId() + ") ---");
            System.out.println("This is a placeholder for displaying detailed registry information.");
            System.out.println("For example, student enrollment, grades, attendance, etc., would be shown here.");
            System.out.println("Details: " + selectedClass);
            System.out.println("----------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("Error: Class with ID '" + classId + "' not found.");
        }
    }

    /**
     * Runs the main application flow.
     * This includes login, displaying classes, and allowing staff to select a class.
     */
    public void run() {
        // Precondition: User must be logged in as staff.
        if (!performLogin()) {
            System.out.println("Application terminated due to failed login.");
            scanner.close();
            return;
        }

        // Event sequence: System shows a screen with all classes.
        List<Class> allClasses = classRepository.getAllClasses();
        displayClasses(allClasses);

        // Postcondition: User displays a list from which to choose the class.
        // Provide an option to choose a class or exit.
        if (!allClasses.isEmpty()) {
            System.out.print("Enter Class ID to view its registry (or type 'exit' to quit): ");
            String input = scanner.nextLine();

            if (!input.equalsIgnoreCase("exit")) {
                accessClassRegistry(input);
            }
        } else {
            System.out.println("No classes to select from.");
        }

        System.out.println("\nThank you for using the Class List Viewer.");
        scanner.close(); // Close the scanner to prevent resource leaks.
    }

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        ViewClassesListApp app = new ViewClassesListApp();
        app.run();
    }
}