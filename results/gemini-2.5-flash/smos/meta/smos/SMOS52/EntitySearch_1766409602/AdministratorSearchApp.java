package EntitySearch_1766409602;

import java.util.Arrays;
import java.util.List;

/**
 * Main application class for the Administrator Entity Search.
 * This class orchestrates the initialization of the system components
 * and starts the user interaction.
 */
public class AdministratorSearchApp {

    /**
     * The main method, entry point of the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        System.out.println("Administrator Entity Search Application Starting...");

        // 1. Initialize EntityManager to store and manage entities.
        // This component will hold all searchable data.
        EntityManager entityManager = new EntityManager();

        // 2. Populate EntityManager with sample data.
        // In a real system, this data would be loaded from a database or other persistent storage.
        // For this simulation, we create some dummy entities.
        // Note: Concrete entity classes (Course, Teaching, Address, User) are assumed to exist
        // based on the plan, even if they are defined in subsequent tasks.
        populateSampleData(entityManager);

        // 3. Initialize SearchService with the EntityManager.
        // The SearchService is responsible for performing the actual search logic
        // against the entities managed by EntityManager.
        SearchService searchService = new SearchService(entityManager);

        // 4. Initialize ConsoleUI with the SearchService.
        // The ConsoleUI handles user input and displays search results.
        ConsoleUI consoleUI = new ConsoleUI(searchService);

        // 5. Start the user interaction loop.
        // The application will now wait for administrator input.
        consoleUI.start();

        // 6. Application shutdown message.
        // This simulates the "Connection to the interrupted SMOS server" postcondition.
        System.out.println("Administrator Entity Search Application Shutting Down.");
        System.out.println("Connection to the interrupted SMOS server.");
    }

    /**
     * Populates the EntityManager with sample data for demonstration purposes.
     * This method creates instances of various entity types and adds them to the manager.
     *
     * @param entityManager The EntityManager instance to populate.
     */
    private static void populateSampleData(EntityManager entityManager) {
        System.out.println("Populating sample data...");

        // Sample Courses (Classes)
        entityManager.addEntity(new Course("CS101", "Introduction to Computer Science", "Basic programming concepts."));
        entityManager.addEntity(new Course("MA201", "Calculus I", "Limits, derivatives, and integrals."));
        entityManager.addEntity(new Course("PH100", "General Physics", "Fundamental laws of physics."));

        // Sample Teachings
        entityManager.addEntity(new Teaching("T-CS101-F23", "Fall 2023 - CS101", "Prof. Smith", "Monday, Wednesday, Friday 10:00 AM"));
        entityManager.addEntity(new Teaching("T-MA201-S24", "Spring 2024 - MA201", "Dr. Johnson", "Tuesday, Thursday 1:00 PM"));

        // Sample Addresses
        entityManager.addEntity(new Address("Main Campus", "123 University Ave", "Cityville", "CA", "90210"));
        entityManager.addEntity(new Address("Engineering Building", "456 Tech Blvd", "Cityville", "CA", "90210"));
        entityManager.addEntity(new Address("Library Annex", "789 Book St", "Townsville", "NY", "10001"));

        // Sample Users (Administrators, Students, Faculty)
        entityManager.addEntity(new User("admin1", "Alice Admin", "alice.admin@example.com", "Administrator"));
        entityManager.addEntity(new User("student_a", "Bob Student", "bob.student@example.com", "Student"));
        entityManager.addEntity(new User("prof_smith", "Prof. Smith", "smith@example.com", "Faculty"));
        entityManager.addEntity(new User("guest_user", "Charlie Guest", "charlie.guest@example.com", "Guest"));

        System.out.println("Sample data populated successfully.");
    }
}