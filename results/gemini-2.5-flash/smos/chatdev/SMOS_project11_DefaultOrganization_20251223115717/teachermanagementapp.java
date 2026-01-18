'''
This is the main application class for the Teacher Management System GUI.
It sets up the primary window and initializes the panel for assigning/removing teachings
to a specific teacher.
'''
import javax.swing.*;
import java.awt.*;
public class TeacherManagementApp extends JFrame {
    /**
     * Constructs the main application frame.
     */
    public TeacherManagementApp() {
        setTitle("Teacher Teaching Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Set a reasonable size for the window
        setLocationRelativeTo(null); // Center the window on the screen
        // Simulate a logged-in administrator and a teacher whose details are being viewed.
        // In a real application, these would come from previous screens/login.
        // Precondition: The user is logged in as an administrator.
        // Precondition: The user has carried out "viewdetTailsente" and the system is viewing details of a teacher.
        // We'll create a dummy teacher for demonstration purposes.
        Teacher mockTeacher = new Teacher(1, "John", "Doe");
        System.out.println("Administrator logged in. Viewing details for teacher: " + mockTeacher.getFirstName() + " " + mockTeacher.getLastName());
        // Create the service layer (in-memory data simulation)
        TeacherService teacherService = new TeacherService();
        // Create and add the TeachingAssignmentPanel
        // Assuming "Teaching Teaching" button was clicked, this panel is displayed.
        TeachingAssignmentPanel assignmentPanel = new TeachingAssignmentPanel(mockTeacher, teacherService);
        add(assignmentPanel);
    }
    /**
     * Main method to run the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            TeacherManagementApp app = new TeacherManagementApp();
            app.setVisible(true); // Make the JFrame visible
        });
    }
}