/**
 * Main class for the ViewTeachingDetails application.
 * This application allows an administrator to view the details of a single, pre-selected teaching.
 * It includes a graphical user interface (GUI) built with Swing.
 * Preconditions: 
 *   - The user has already logged in.
 *   - System is viewing the list of teachings.
 *   - The user clicks on the "Teaching details" button for a specific teaching.
 * Postconditions: 
 *   - The user displays the detailed information of a single teaching.
 *   - Connection to the interrupted SMOS server (simulated).
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ViewTeachingDetails {
    // The teaching to be displayed (simulated as passed from the list view)
    private Teaching teachingToDisplay;
    // GUI Components
    private JFrame frame;
    private JTextArea detailsArea;
    private JButton exitButton;
    /**
     * Constructor that initializes the application with a specific teaching.
     * @param teachingId The identifier for the teaching to display (simulated).
     *                   In a real system, this would be retrieved from a database or service.
     */
    public ViewTeachingDetails(String teachingId) {
        // Retrieve the teaching details based on the provided ID
        teachingToDisplay = retrieveTeachingById(teachingId);
        initializeGUI();
    }
    /**
     * Simulates retrieving a teaching by its ID.
     * In a real application, this would connect to a database or external service.
     * @param teachingId The identifier for the teaching.
     * @return The Teaching object corresponding to the given ID.
     */
    private Teaching retrieveTeachingById(String teachingId) {
        // Simulated data - in reality, this would be fetched from a data source
        // For demonstration, we return a predefined teaching based on the ID.
        if ("CS101".equals(teachingId)) {
            return new Teaching(
                "CS101",
                "Introduction to Computer Science",
                "Dr. John Smith",
                "Mon, Wed 10:00-11:30",
                "Room 101, Building A",
                "This course introduces fundamental concepts of programming and algorithms."
            );
        } else if ("MATH201".equals(teachingId)) {
            return new Teaching(
                "MATH201",
                "Calculus II",
                "Prof. Jane Doe",
                "Tue, Thu 13:00-14:30",
                "Room 205, Building B",
                "Advanced calculus topics including integration techniques and series."
            );
        } else if ("PHYS150".equals(teachingId)) {
            return new Teaching(
                "PHYS150",
                "Physics for Engineers",
                "Dr. Robert Johnson",
                "Mon, Wed, Fri 09:00-10:00",
                "Lab 301, Science Building",
                "Fundamental physics principles with engineering applications."
            );
        } else {
            // Return a default teaching if ID not found (handles edge case)
            return new Teaching(
                "UNKNOWN",
                "Unknown Course",
                "Instructor Not Available",
                "Schedule Not Set",
                "Location Not Assigned",
                "Details for this teaching are not available."
            );
        }
    }
    /**
     * Initializes the GUI components and layout to display details of a single teaching.
     */
    private void initializeGUI() {
        // Create main frame
        frame = new JFrame("Teaching Details Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Title label
        JLabel titleLabel = new JLabel("Teaching Details", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Center panel for details display
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Detailed Information"));
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        // Display the teaching details immediately (pre-selected)
        detailsArea.setText(formatTeachingDetails(teachingToDisplay));
        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        detailsPanel.add(detailsScrollPane, BorderLayout.CENTER);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        // Button panel at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Postcondition: Connection to the interrupted SMOS server (simulated)
                int response = JOptionPane.showConfirmDialog(frame, 
                    "Connection to the SMOS server has been restored and updated.\nDo you want to exit?",
                    "Postcondition",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    frame.dispose();
                }
            }
        });
        buttonPanel.add(exitButton);
        // Add a refresh button to demonstrate functionality
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // In a real system, this would refresh data from the server
                JOptionPane.showMessageDialog(frame,
                    "Refreshed data from SMOS server.",
                    "Refresh",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buttonPanel.add(refreshButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Add main panel to frame and display
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
    /**
     * Formats the teaching details into a readable string.
     * @param teaching The teaching object to display.
     * @return A formatted string containing the teaching details.
     */
    private String formatTeachingDetails(Teaching teaching) {
        StringBuilder sb = new StringBuilder();
        sb.append("==================== TEACHING DETAILS ====================\n\n");
        sb.append("Course Code:    ").append(teaching.getCourseCode()).append("\n");
        sb.append("Course Name:    ").append(teaching.getCourseName()).append("\n");
        sb.append("Instructor:     ").append(teaching.getInstructor()).append("\n");
        sb.append("Schedule:       ").append(teaching.getSchedule()).append("\n");
        sb.append("Location:       ").append(teaching.getLocation()).append("\n\n");
        sb.append("Description:\n");
        sb.append("--------------------------------------------------------\n");
        sb.append(teaching.getDescription()).append("\n");
        sb.append("========================================================\n");
        return sb.toString();
    }
    /**
     * Main method to launch the application.
     * Simulates the precondition: 
     *   - user is logged in and viewing list of teachings.
     *   - user clicks "Teaching details" button for a specific teaching (teaching ID passed as argument).
     * In a real system, the teaching ID would be passed from the previous screen.
     */
    public static void main(String[] args) {
        // Simulate precondition check
        boolean isLoggedIn = true; // Assume the user is logged in
        boolean isViewingList = true; // Assume the system is viewing the list of teachings
        // Simulate passing a teaching ID from the list view (e.g., via click event)
        String teachingId = "CS101"; // Default ID - in reality would come from user selection
        if (args.length > 0) {
            teachingId = args[0]; // Allow ID to be passed as command-line argument for flexibility
        }
        if (isLoggedIn && isViewingList) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new ViewTeachingDetails(teachingId);
                }
            });
        } else {
            System.err.println("Preconditions not met: user must be logged in and viewing the list of teachings.");
        }
    }
}
/**
 * Teaching class to represent a teaching entity.
 */
class Teaching {
    private String courseCode;
    private String courseName;
    private String instructor;
    private String schedule;
    private String location;
    private String description;
    public Teaching(String courseCode, String courseName, String instructor,
                    String schedule, String location, String description) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructor = instructor;
        this.schedule = schedule;
        this.location = location;
        this.description = description;
    }
    // Getters
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public String getInstructor() { return instructor; }
    public String getSchedule() { return schedule; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
}