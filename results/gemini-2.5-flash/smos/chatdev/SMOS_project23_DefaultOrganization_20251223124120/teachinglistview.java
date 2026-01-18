import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
/*
 * Provides the main application window that displays a list of existing teachings
 * and allows the administrator to initiate the "Insert New Teaching" use case.
 * This simulates the "viewing technology" stage as per the preconditions.
 */
public class TeachingListView extends JFrame {
    private TeachingArchive teachingArchive; // Reference to the archive business logic
    private JTextArea teachingListArea;      // Area to display the list of teachings
    private JButton newTeachingButton;       // Button to open the "Insert New Teaching" form
    /**
     * Constructs a new TeachingListView.
     * @param archive The TeachingArchive instance to interact with.
     */
    public TeachingListView(TeachingArchive archive) {
        this.teachingArchive = archive;
        setTitle("Available Teachings");
        setSize(500, 400);
        // Ensure the entire application exits when this main window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); // Center the window
        createUI();             // Initialize GUI components
        refreshTeachingList();  // Load and display initial data
    }
    /**
     * Initializes and lays out the GUI components for the teaching list view.
     */
    private void createUI() {
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall layout
        // Text area to display the list of teachings
        teachingListArea = new JTextArea();
        teachingListArea.setEditable(false); // Make it read-only
        teachingListArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        teachingListArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        JScrollPane scrollPane = new JScrollPane(teachingListArea);
        add(scrollPane, BorderLayout.CENTER);
        // Panel for the "New Teaching" button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        newTeachingButton = new JButton("New Teaching");
        newTeachingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When "New Teaching" is clicked, open the InsertNewTeachingGUI
                InsertNewTeachingGUI insertGUI = new InsertNewTeachingGUI(teachingArchive);
                // Add a WindowListener to the new teaching GUI.
                // This allows the TeachingListView to refresh its list once the InsertNewTeachingGUI is closed,
                // reflecting any newly added teachings.
                insertGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        refreshTeachingList(); // Refresh the list when the insert form closes
                    }
                });
                insertGUI.setVisible(true); // Make the insert form visible
                // Optional: Deactivate the 'New Teaching' button temporarily until the insertGUI closes
                // This would require a more complex listener or a modal dialog.
            }
        });
        buttonPanel.add(newTeachingButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Refreshes the list of teachings displayed in the JTextArea
     * by fetching the latest data from the TeachingArchive.
     */
    public void refreshTeachingList() {
        List<String> teachings = teachingArchive.getTeachings();
        StringBuilder sb = new StringBuilder();
        if (teachings.isEmpty()) {
            sb.append("No teachings available in archive.");
        } else {
            sb.append("--- Current Teachings ---\n\n");
            for (String teaching : teachings) {
                sb.append("  - ").append(teaching).append("\n");
            }
        }
        teachingListArea.setText(sb.toString());
        // For console verification/debugging
        System.out.println("Teaching List Refreshed. Current: " + teachings.size() + " teachings.");
    }
}