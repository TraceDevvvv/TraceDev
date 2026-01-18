import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents the detailed information of a class.
 * This class acts as a data structure to hold the class's name, location (address), and academic year.
 */
class ClassDetails {
    private String name;
    private String address; // Interpreted as the physical location or room of the class
    private String schoolYear;

    /**
     * Constructs a new ClassDetails object.
     *
     * @param name       The name of the class (e.g., "Introduction to Programming").
     * @param address    The physical location or room where the class is held.
     * @param schoolYear The academic year to which the class belongs (e.g., "2023-2024").
     */
    public ClassDetails(String name, String address, String schoolYear) {
        this.name = name;
        this.address = address;
        this.schoolYear = schoolYear;
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
     * Returns the address (location) of the class.
     *
     * @return The class location.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the academic year of the class.
     *
     * @return The school year.
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * Provides a user-friendly string representation of the ClassDetails object.
     *
     * @return A formatted string displaying the class's name, location, and school year.
     */
    @Override
    public String toString() {
        return "Class Name:  " + name + "\n" +
               "Location:    " + address + "\n" +
               "School Year: " + schoolYear;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDetails that = (ClassDetails) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(address, that.address) &&
               Objects.equals(schoolYear, that.schoolYear);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, address, schoolYear);
    }
}

/**
 * Simulates a service layer responsible for fetching class details from a backend system (e.g., SMOS server).
 * It contains a dummy database for demonstration purposes.
 */
class ClassService {
    // A map to store dummy class data, mapping a class ID (e.g., "CS101") to its details.
    private final Map<String, ClassDetails> classDatabase;

    /**
     * Constructs a new ClassService and initializes its dummy database with sample class data.
     */
    public ClassService() {
        classDatabase = new HashMap<>();
        // Populate with some sample data to simulate a database
        classDatabase.put("CS101", new ClassDetails("Introduction to Programming", "Building A, Room 101", "2023-2024"));
        classDatabase.put("MA202", new ClassDetails("Calculus II", "Building B, Room 205", "2023-2024"));
        classDatabase.put("PH301", new ClassDetails("Quantum Mechanics", "Building C, Lecture Hall 3", "2024-2025"));
        classDatabase.put("HI105", new ClassDetails("World History I", "Online Platform", "2023-2024"));
    }

    /**
     * Retrieves the details of a specific class based on its unique identifier (class ID).
     * This method simulates a backend call, including a potential network delay.
     *
     * @param classId The unique identifier for the class (e.g., "CS101").
     * @return A {@link ClassDetails} object if a class with the given ID is found, otherwise {@code null}.
     */
    public ClassDetails getClassDetails(String classId) {
        // Simulate a network delay or database lookup operation
        try {
            Thread.sleep(700); // Simulate 0.7 seconds delay for fetching data
        } catch (InterruptedException e) {
            // Restore the interrupted status of the current thread
            Thread.currentThread().interrupt();
            System.err.println("Warning: Class details retrieval was interrupted.");
        }

        // Retrieve the class details from the dummy database
        return classDatabase.get(classId);
    }
}

/**
 * The main application class for the "View Class Details" use case.
 * It simulates the Administrator's interaction flow, including preconditions and postconditions.
 */
public class ViewClassDetailsApp {

    /**
     * The main method that orchestrates the "View Class Details" use case.
     * It simulates the administrator's actions and the system's responses.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassService classService = new ClassService();

        System.out.println("--- View Class Details Use Case ---");

        // Simulate Preconditions
        System.out.println("Precondition: Administrator has logged in successfully.");
        System.out.println("Precondition: Administrator has viewed a list of classes for an academic year.");
        System.out.println("Precondition: Administrator clicks on 'Show class details' button.");
        System.out.println("-----------------------------------");

        // Simulate displaying available classes for the user to choose from
        System.out.println("Available Class IDs (for demonstration): CS101, MA202, PH301, HI105");
        System.out.print("Please enter the Class ID to view details: ");
        String classId = scanner.nextLine();

        // Validate input
        if (classId == null || classId.trim().isEmpty()) {
            System.out.println("Error: Class ID cannot be empty. Please provide a valid Class ID.");
            // Postcondition: Connection to SMOS server interrupted (or not established for this request)
            System.out.println("Postcondition: Connection to the SMOS server interrupted.");
            scanner.close();
            return;
        }

        System.out.println("\nSystem: Fetching class details for ID: " + classId.trim() + "...");

        // Event Sequence: System displays details
        ClassDetails details = classService.getClassDetails(classId.trim().toUpperCase()); // Use toUpperCase for case-insensitive matching with dummy data

        if (details != null) {
            System.out.println("\n--- Class Details ---");
            System.out.println(details);
            System.out.println("---------------------");
            // Postcondition: The user displays detailed information related to a class.
            System.out.println("Postcondition: Detailed information related to the class is displayed.");
        } else {
            System.out.println("\nError: No class found with ID '" + classId.trim() + "'. Please check the ID and try again.");
            // Postcondition: The user displays detailed information related to a class (or an error message indicating it's not found).
            System.out.println("Postcondition: No class details found for the provided ID.");
        }

        // Postcondition: Connection to the SMOS server interrupted.
        // This simulates the server connection being closed or reset after the request is fulfilled.
        System.out.println("Postcondition: Connection to the SMOS server interrupted.");

        scanner.close(); // Close the scanner to release system resources
        System.out.println("\n--- Use Case Ended ---");
    }
}