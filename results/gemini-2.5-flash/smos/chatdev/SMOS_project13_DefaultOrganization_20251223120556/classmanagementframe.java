'''
The main JFrame for the Class Management application.
This frame hosts the {@link ClassManagementPanel}, which contains the core
functionality for viewing classes.
'''
import javax.swing.JFrame;
import java.awt.BorderLayout;
public class ClassManagementFrame extends JFrame {
    /**
     * Constructs a new ClassManagementFrame with the specified title.
     * Initializes the frame's properties and adds the ClassManagementPanel to its content pane.
     *
     * @param title The title to be displayed in the frame's title bar.
     */
    public ClassManagementFrame(String title) {
        super(title); // Call JFrame's constructor
        // Set the layout for the frame's content pane
        setLayout(new BorderLayout());
        // Create and add the ClassManagementPanel to the center of the frame
        add(new ClassManagementPanel(), BorderLayout.CENTER);
    }
}