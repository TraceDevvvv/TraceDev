import java.util.List;
import java.util.ArrayList;

/**
 * Main class to orchestrate the application flow for the InsertDelayAta use case.
 * This class sets up the necessary components (students, class, server proxy, UI screen)
 * and simulates the user interaction to register student delays.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting InsertDelayAta Application ---");

        // 1. Setup initial data: Students and ClassData
        // In a real system, this data would come from a database or previous screens.
        ClassData selectedClass = setupClassData();

        // 2. Setup ServerProxy
        ServerProxy serverProxy = new ServerProxy();

        // 3. Simulate user login and class selection (preconditions met)
        // The DelayEntryScreen constructor assumes these preconditions are met.
        System.out.println("\nPrecondition: User logged in as ATA staff.");
        System.out.println("Precondition: User has viewed class data and selected class '" + selectedClass.getClassName() + "'.");

        // 4. Instantiate and display the DelayEntryScreen
        DelayEntryScreen delayEntryScreen = new DelayEntryScreen(selectedClass, serverProxy);

        // Display the screen and handle the outcome
        boolean dataSentSuccessfully = delayEntryScreen.displayScreen();

        // 5. Handle postconditions based on the screen's outcome
        System.out.println("\n--- Application Session Ended ---");
        if (dataSentSuccessfully) {
            System.out.println("Final Status: Delay data was successfully processed and entered into the system.");
            System.out.println("The system is ready for new operations.");
        } else {
            System.out.println("Final Status: Operation was interrupted or failed to send data.");
            // Check server connection status if interrupted
            if (!serverProxy.isConnected()) {
                System.out.println("Server connection remains interrupted as per user's choice.");
            } else {
                System.out.println("Server connection status: " + (serverProxy.isConnected() ? "Connected" : "Disconnected"));
            }
            System.out.println("The system is ready for new operations.");
        }

        // In a real application, the scanner would be closed here if it were managed by Main.
        // Since DelayEntryScreen manages its own scanner, we don't need to close it here.
    }

    /**
     * Helper method to set up some sample ClassData with students.
     *
     * @return A ClassData object populated with sample students.
     */
    private static ClassData setupClassData() {
        ClassData classA = new ClassData("Mathematics 101");

        // Create some sample students
        Student s1 = new Student("S001", "Alice Smith");
        Student s2 = new Student("S002", "Bob Johnson");
        Student s3 = new Student("S003", "Charlie Brown");
        Student s4 = new Student("S004", "Diana Prince");
        Student s5 = new Student("S005", "Eve Adams");

        // Add students to the class
        classA.addStudent(s1);
        classA.addStudent(s2);
        classA.addStudent(s3);
        classA.addStudent(s4);
        classA.addStudent(s5);

        System.out.println("Initialized class: " + classA.getClassName() + " with " + classA.getStudents().size() + " students.");
        return classA;
    }
}