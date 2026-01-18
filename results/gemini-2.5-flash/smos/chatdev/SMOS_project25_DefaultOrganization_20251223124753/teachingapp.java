/**
 * The main application class for the Teaching Editor.
 * This class sets up the JFrame and manages the switching between
 * the list view (TeachingListPanel) and the edit/add view (EditTeachingPanel)
 * using a CardLayout.
 */
import javax.swing.*;
import java.awt.*;
public class TeachingApp extends JFrame {
    private static final String LIST_PANEL = "List Teachings";
    private static final String EDIT_PANEL = "Edit Teaching";
    private JPanel cardPanel;
    private TeachingListPanel teachingListPanel;
    private EditTeachingPanel editTeachingPanel;
    /**
     * Constructs the main application frame.
     * Initializes the CardLayout and adds the various panels.
     */
    public TeachingApp() {
        super("Teaching Management System (Administrator)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        cardPanel = new JPanel(new CardLayout());
        // Initialize panels
        teachingListPanel = new TeachingListPanel(this);
        editTeachingPanel = new EditTeachingPanel(this);
        // Add panels to the card layout
        cardPanel.add(teachingListPanel, LIST_PANEL);
        cardPanel.add(editTeachingPanel, EDIT_PANEL);
        // Add the card panel to the frame
        add(cardPanel);
        // Show the initial list panel
        showListPanel();
    }
    /**
     * Switches the view to the TeachingListPanel and refreshes its data.
     */
    public void showListPanel() {
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, LIST_PANEL);
        teachingListPanel.refreshTeachings(); // Ensure the list is updated
    }
    /**
     * Switches the view to the EditTeachingPanel and loads the specified teaching data.
     * @param teaching The Teaching object to load for editing, or null to add a new teaching.
     */
    public void showEditPanel(Teaching teaching) {
        editTeachingPanel.loadTeaching(teaching); // Set the teaching to be edited or prepare for new
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, EDIT_PANEL);
    }
    /**
     * Main method to run the application.
     * Ensures the GUI is created on the Event Dispatch Thread (EDT).
     * Precondition: "The user is logged in to the system as an administrator" -
     * for this example, we assume this is implicitly met as the app opens directly.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TeachingApp().setVisible(true);
            }
        });
    }
}