'''
This is the main entry point for the Assign/RemoveStudentsToAParent application.
It initializes the look and feel and then displays the ParentDetailsWindow,
which simulates the "viewdetTailsente" use case and allows the administrator to proceed
to child management.
'''
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        // Set an intelligent look and feel for a better user experience
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Simulate that a parent has been selected and their details are being viewed.
            // Create a sample parent object. In a real application, this would come from a database.
            Parent sampleParent = new Parent(1, "John Doe");
            // Display the parent details window, which has the "Parentela" button
            new ParentDetailsWindow(sampleParent).setVisible(true);
        });
    }
}