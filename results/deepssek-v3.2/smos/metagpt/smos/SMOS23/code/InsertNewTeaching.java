import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This program simulates the "InsertNewTeaching" use case for an administrator.
 * The administrator can insert a new teaching into the archive after validating the data.
 * The program simulates form input via console and an in-memory archive.
 * Error handling includes validation errors and simulated SMOS server connection interruption.
 */
public class InsertNewTeaching {

    // Simulated archive storage using an in-memory list
    private static List<Teaching> archive = new ArrayList<>();

    // Simulated connection status to SMOS server
    private static boolean isSmosServerConnected = true;

    /**
     * Represents a Teaching entity with a name.
     */
    static class Teaching {
        private String name;

        public Teaching(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Teaching{name='" + name + "'}";
        }
    }

    /**
     * Validates the teaching name.
     * Rules: non-null, non-empty, and must not contain only whitespace.
     *
     * @param name The teaching name to validate.
     * @return true if valid, false otherwise.
     */
    private static boolean isValidTeachingName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Simulates a check of the SMOS server connection.
     * In a real application, this would be a network call.
     *
     * @return true if connected, false otherwise.
     */
    private static boolean checkSmosConnection() {
        return isSmosServerConnected;
    }

    /**
     * Simulates inserting a teaching into the archive.
     * In a real application, this would persist to a database.
     *
     * @param teaching The teaching to insert.
     * @return true if insertion is successful, false otherwise.
     */
    private static boolean insertIntoArchive(Teaching teaching) {
        if (teaching == null) {
            return false;
        }
        archive.add(teaching);
        return true;
    }

    /**
     * Displays the form for entering a new teaching.
     * This simulates the UI form via console.
     */
    private static void showForm() {
        System.out.println("=== New Teaching Form ===");
        System.out.println("Please enter the Teaching name:");
    }

    /**
     * Handles the error case when validation fails or connection is interrupted.
     * Notifies the user appropriately.
     *
     * @param errorType The type of error: "validation" or "connection".
     */
    private static void handleError(String errorType) {
        if ("validation".equals(errorType)) {
            System.out.println("Error: Invalid teaching name. Please ensure the name is not empty.");
        } else if ("connection".equals(errorType)) {
            System.out.println("Error: Connection to SMOS server interrupted. Please try again later.");
        } else {
            System.out.println("Error: Operation interrupted.");
        }
    }

    /**
     * Main method to run the use case.
     * Simulates the entire flow:
     * 1. Show form.
     * 2. Read user input.
     * 3. Validate.
     * 4. Insert into archive (if valid and connection available).
     * 5. Handle errors if any.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Precondition: Administrator is logged in and has viewed the technology list.
            System.out.println("Administrator logged in. Technology list displayed.");
            System.out.println("User clicks on 'New Teaching' button.");

            // Step 1: System shows the form.
            showForm();

            // Step 2: User fills the form.
            String teachingName = scanner.nextLine();

            // Step 3: User clicks "Save" (simulated by pressing Enter).

            // Step 4: System checks validity of data.
            if (!isValidTeachingName(teachingName)) {
                handleError("validation");
                return; // Terminate as per use case: error case activated.
            }

            // Check SMOS server connection.
            if (!checkSmosConnection()) {
                handleError("connection");
                return; // Terminate as per use case: error case activated.
            }

            // Simulate occasional interruption by administrator (e.g., random chance 10%).
            // This is a simulation of the postcondition about interruption.
            double random = Math.random();
            if (random < 0.1) {
                System.out.println("Operation interrupted by administrator.");
                handleError("interruption");
                return;
            }

            // Create the teaching object.
            Teaching newTeaching = new Teaching(teachingName.trim());

            // Insert into archive.
            boolean insertSuccess = insertIntoArchive(newTeaching);
            if (!insertSuccess) {
                System.out.println("Error: Failed to insert teaching into archive.");
                return;
            }

            // Success.
            System.out.println("Teaching '" + newTeaching.getName() + "' successfully inserted into archive.");
            System.out.println("Current archive contents:");
            for (Teaching t : archive) {
                System.out.println("  - " + t.getName());
            }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}