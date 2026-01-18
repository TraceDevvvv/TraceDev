/**
 * Main class implementing the ViewStudentData use case.
 * Allows parents to view their children's information including absences, 
 * disciplinary notes, delays, and justifications.
 * Handles SMOS server connections including interruptions.
 */
import java.text.SimpleDateFormat;
import java.util.*;
public class ViewStudentData {
    // Simulated parent-children relationship database
    private static final Map<String, List<String>> parentChildren = new HashMap<>();
    // Simulated parent passwords (in real app, use hashed passwords)
    private static final Map<String, String> parentPasswords = new HashMap<>();
    // Initialize sample data for demonstration
    static {
        // Simulate database records: parent username -> list of child IDs
        parentChildren.put("john_parent", Arrays.asList("child_001", "child_002"));
        parentChildren.put("sarah_parent", Collections.singletonList("child_003"));
        parentChildren.put("mike_parent", Arrays.asList("child_004", "child_005", "child_006"));
        // Simulate passwords (in real app, these would be securely hashed)
        parentPasswords.put("john_parent", "parent123");
        parentPasswords.put("sarah_parent", "parent123");
        parentPasswords.put("mike_parent", "parent123");
    }
    /**
     * Main entry point of the program. Executes the ViewStudentData use case.
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("     SCHOOL PARENT PORTAL - ViewStudentData");
        System.out.println("==========================================");
        System.out.println();
        // Step 1: Parent login simulation (precondition: user is logged in as parent)
        System.out.println("[LOGIN SCREEN]");
        String parentId = simulateParentLogin();
        if (parentId == null) {
            System.out.println("Login failed. Please check your credentials and try again.");
            return;
        }
        System.out.println("Successfully logged in as parent: " + parentId);
        System.out.println();
        // Step 2: Retrieve and display parent's children list
        List<String> children = parentChildren.get(parentId);
        if (children == null || children.isEmpty()) {
            System.out.println("No children found associated with this parent account.");
            return;
        }
        displayChildrenList(children);
        // Step 3: Child selection simulation (user clicks "Register" button for a child)
        System.out.println("\n[CHILD SELECTION]");
        System.out.println("Click 'Register' button for a child to view their data:");
        int choice = selectChild(children.size());
        if (choice < 1 || choice > children.size()) {
            System.out.println("Invalid selection. Operation cancelled.");
            return;
        }
        String selectedChildId = children.get(choice - 1);
        System.out.println("Selected child: " + selectedChildId);
        System.out.println();
        // Step 4: Connect to SMOS server (handles interruptions as per postconditions)
        System.out.println("[SERVER CONNECTION]");
        SMOSConnector connector = new SMOSConnector();
        boolean connected = connector.connect();
        // Step 5: If connection successful, fetch and display child data
        if (connected) {
            System.out.println("\n[DATA RETRIEVAL]");
            System.out.println("Fetching data for " + selectedChildId + "...");
            List<ChildRecord> records = connector.fetchChildData(selectedChildId);
            // Display the summary table as specified in events sequence
            displayChildSummaryTable(records, selectedChildId);
            // Additional summary information
            displayAdditionalSummary(records);
        } else {
            System.out.println("\n[ERROR] Unable to display child data due to SMOS server connection issues.");
            System.out.println("Please try again later or contact system administrator.");
            System.out.println("Postcondition: Connection to the interrupted SMOS server attempted with retry logic.");
        }
        // Clean up resources
        if (connector.isConnected()) {
            connector.disconnect();
        }
        System.out.println("\nThank you for using the School Parent Portal.");
    }
    /**
     * Simulates parent authentication process.
     * @return Parent ID if authentication successful, null otherwise
     */
    private static String simulateParentLogin() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter parent username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();
            // Simple authentication simulation
            // In real application, this would involve secure password hashing and database lookup
            if (parentChildren.containsKey(username) && parentPasswords.containsKey(username) 
                && password.equals(parentPasswords.get(username))) {
                return username;
            }
            // Return null for failed authentication
            return null;
        }
    }
    /**
     * Displays the list of children associated with the logged-in parent.
     * @param children List of child IDs
     */
    private static void displayChildrenList(List<String> children) {
        System.out.println("[YOUR CHILDREN]");
        System.out.println("You have " + children.size() + " child(ren):");
        System.out.println("--------------------------------");
        for (int i = 0; i < children.size(); i++) {
            System.out.println((i + 1) + ". " + children.get(i));
        }
        System.out.println("--------------------------------");
    }
    /**
     * Simulates child selection by parent (clicking "Register" button).
     * @param maxChoice Number of children available for selection
     * @return Selected choice (1 to maxChoice) or -1 for invalid input
     */
    private static int selectChild(int maxChoice) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter child number (1-" + maxChoice + "): ");
            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= maxChoice) {
                    return choice;
                } else {
                    System.out.println("Selection must be between 1 and " + maxChoice);
                    return -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                return -1;
            }
        }
    }
    /**
     * Displays the summary table of child information as specified in events sequence.
     * Includes date, absences, disciplinary notes, delays, and justification.
     * @param records List of ChildRecord objects to display
     * @param childId ID of the child whose data is being displayed
     */
    private static void displayChildSummaryTable(List<ChildRecord> records, String childId) {
        System.out.println("\n==========================================");
        System.out.println("     CHILD INFORMATION SUMMARY TABLE");
        System.out.println("==========================================");
        System.out.println("Child ID: " + childId);
        System.out.println("Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println();
        // Table header
        System.out.println("+--------------+-----------+---------------------------+-----------------+----------------------+");
        System.out.println("| Date         | Absences  | Disciplinary Notes        | Delays          | Justification        |");
        System.out.println("+--------------+-----------+---------------------------+-----------------+----------------------+");
        // Table rows
        if (records.isEmpty()) {
            System.out.println("| No data available                                                              |");
        } else {
            for (ChildRecord record : records) {
                System.out.println(record.toString());
            }
        }
        // Table footer
        System.out.println("+--------------+-----------+---------------------------+-----------------+----------------------+");
        System.out.println();
        System.out.println("Total records displayed: " + records.size());
    }
    /**
     * Displays additional summary statistics for the child's data.
     * @param records List of ChildRecord objects to analyze
     */
    private static void displayAdditionalSummary(List<ChildRecord> records) {
        if (records.isEmpty()) {
            System.out.println("No data available for summary statistics.");
            return;
        }
        int totalAbsences = 0;
        int daysWithDelays = 0;
        int daysWithDisciplinaryNotes = 0;
        for (ChildRecord record : records) {
            totalAbsences += record.getAbsences();
            // Check for delays (not "none" or empty)
            if (record.getDelays() != null && 
                !record.getDelays().trim().isEmpty() && 
                !record.getDelays().equalsIgnoreCase("none")) {
                daysWithDelays++;
            }
            // Check for disciplinary notes (not empty or N/A)
            if (record.getDisciplinaryNotes() != null && 
                !record.getDisciplinaryNotes().trim().isEmpty() && 
                !record.getDisciplinaryNotes().equalsIgnoreCase("N/A")) {
                daysWithDisciplinaryNotes++;
            }
        }
        System.out.println("\n[SUMMARY STATISTICS]");
        System.out.println("--------------------------------");
        System.out.println("Total absences: " + totalAbsences);
        System.out.println("Days with delays: " + daysWithDelays);
        System.out.println("Days with disciplinary notes: " + daysWithDisciplinaryNotes);
        System.out.println("Total days tracked: " + records.size());
        System.out.println("Average absences per day: " + 
                         String.format("%.2f", (double) totalAbsences / records.size()));
        System.out.println("--------------------------------");
    }
}