/**
 * This controller class manages the interaction between the GUI and the model.
 * It handles the logic for fetching class details (simulated here) and updating the view.
 * Now properly simulates SMOS server connection interruption as per requirements.
 */
import javax.swing.*;
public class ClassDetailsController {
    private ClassDetailsFrame view;
    /**
     * Constructor to initialize the controller with a view.
     * @param view The GUI frame for displaying class details
     */
    public ClassDetailsController(ClassDetailsFrame view) {
        this.view = view;
    }
    /**
     * Fetches class details for a given class ID.
     * Simulates server connection: after fetching, interrupts connection to SMOS server.
     * @param classId The unique identifier for the class
     * @return The ClassDetails object or null if not found
     */
    public ClassDetails fetchClassDetails(String classId) {
        ClassDetails details = null;
        // Simulate data retrieval. In production, replace with actual data source.
        if ("MATH101".equals(classId)) {
            details = new ClassDetails("Mathematics 101", "123 University Ave, Campus Building A, Room 205", "2023-2024");
        } else if ("CS201".equals(classId)) {
            details = new ClassDetails("Computer Science 201", "456 Tech Blvd, Engineering Building, Room 101", "2023-2024");
        } else {
            System.err.println("Class not found for ID: " + classId);
        }
        // Simulate server connection interruption after fetching data
        interruptSMOSConnection();
        return details;
    }
    /**
     * Updates the view with new details.
     * @param className New class name
     * @param address New address
     * @param schoolYear New school year
     */
    public void updateViewDetails(String className, String address, String schoolYear) {
        if (view != null) {
            view.updateDetails(className, address, schoolYear);
        }
    }
    /**
     * Simulates interruption of connection to SMOS server as per postconditions.
     */
    private void interruptSMOSConnection() {
        System.out.println("Connection to the SMOS server interrupted.");
        // In a real implementation, this would contain actual server disconnection logic
    }
}