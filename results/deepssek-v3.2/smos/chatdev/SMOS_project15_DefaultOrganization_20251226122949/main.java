/**
 * Main entry point for the ViewClassDetails application.
 * This class simulates the preconditions: user logged in, class list displayed,
 * and a "Show class details" button clicked. It creates the necessary components
 * and displays the class details window.
 */
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater for thread-safe GUI operations
        SwingUtilities.invokeLater(() -> {
            // Simulate that the Administrator has logged in and viewed the class list.
            // Upon clicking a class (e.g., with ID "CS201") from the list, details are fetched.
            String selectedClassId = "CS201"; // Assume this is selected from the list
            // Create the view with placeholder data
            ClassDetailsFrame frame = new ClassDetailsFrame("Loading...", "Loading...", "Loading...");
            // Create the controller to manage interactions
            ClassDetailsController controller = new ClassDetailsController(frame);
            // Fetch details for the selected class ID
            ClassDetails fetchedDetails = controller.fetchClassDetails(selectedClassId);
            if (fetchedDetails != null) {
                // Update the view with fetched details
                controller.updateViewDetails(
                    fetchedDetails.getClassName(),
                    fetchedDetails.getAddress(),
                    fetchedDetails.getSchoolYear()
                );
            } else {
                // Handle case where class details are not found
                controller.updateViewDetails("Class Not Found", "N/A", "N/A");
            }
            // Display the GUI frame
            frame.setVisible(true);
            // Note: In a full application, the frame would be triggered by a button in the class list window.
            // This main method simulates that button click by directly creating and showing the frame.
        });
    }
}