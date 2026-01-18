/*
main.java
This is the main entry point for the Student Report Card Management application.
It initializes the necessary serv and the main graphical user interface frame.
*/
import gui.StudentManagementFrame;
import service.StudentService;
import javax.swing.*;
import java.awt.*;
public class Main {
    /**
     * Main method to start the application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are handled on the Event Dispatch Thread (EDT) for thread safety
        EventQueue.invokeLater(() -> {
            try {
                // Initialize the student service, which manages student data
                StudentService studentService = new StudentService();
                // Create and display the main application window
                StudentManagementFrame frame = new StudentManagementFrame(studentService);
                frame.setVisible(true); // Make the frame visible to the user
            } catch (Exception e) {
                // Log and display any unhandled exceptions that occur during application startup
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Application Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}