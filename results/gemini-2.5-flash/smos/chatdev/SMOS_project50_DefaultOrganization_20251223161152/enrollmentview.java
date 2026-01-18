/**
 * Represents the main GUI window for the Administrator to accept student enrollments.
 * It displays a list of pending registration requests and allows activation.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class EnrollmentView extends JFrame {
    private RegistrationService registrationService; // Service layer for business logic
    private JList<Student> registrationList;          // Displays the list of pending students
    private DefaultListModel<Student> listModel;      // Data model for the JList
    private JButton acceptButton;                     // Button to accept a selected student
    private JLabel statusLabel;                       // Displays status messages to the user
    /**
     * Constructor for EnrollmentView.
     *
     * @param registrationService The service layer to interact with student data.
     */
    public EnrollmentView(RegistrationService registrationService) {
        this.registrationService = registrationService;
        initializeUI(); // Setup all GUI components
        refreshPendingRegistrations(); // Load initial data into the list
    }
    /**
     * Initializes all graphical user interface components and sets up the layout.
     */
    private void initializeUI() {
        setTitle("Student Enrollment Acceptance");
        setSize(500, 400); // Set window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation for the window
        setLocationRelativeTo(null); // Center the window on the screen
        // Use BorderLayout for the main frame layout
        setLayout(new BorderLayout(10, 10)); // 10px component gap
        // --- Header Panel ---
        JPanel headerPanel = new JPanel();
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10)); // Padding
        JLabel titleLabel = new JLabel("Pending Registration Requests");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);
        // --- List Panel (Center) ---
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // Padding
        listModel = new DefaultListModel<>();
        registrationList = new JList<>(listModel);
        registrationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one selection at a time
        registrationList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(registrationList); // Make list scrollable
        listPanel.add(scrollPane, BorderLayout.CENTER);
        add(listPanel, BorderLayout.CENTER);
        // --- Control Panel (South) ---
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centered flow layout
        acceptButton = new JButton("Accept Selected Enrollment");
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceptSelectedStudent(); // Call method to handle acceptance
            }
        });
        controlPanel.add(acceptButton);
        add(controlPanel, BorderLayout.SOUTH);
        // --- Status Bar (Bottom) ---
        statusLabel = new JLabel(" "); // Initialize with a space to reserve height
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        add(statusLabel, BorderLayout.PAGE_END); // Use PAGE_END for the very bottom
    }
    /**
     * Refreshes the list of pending student registrations displayed in the JList.
     * This method fetches the latest pending students from the RegistrationService
     * and updates the GUI accordingly.
     */
    private void refreshPendingRegistrations() {
        listModel.clear(); // Clear existing items in the list model
        List<Student> pendingStudents = registrationService.getPendingRegistrations();
        if (pendingStudents.isEmpty()) {
            acceptButton.setEnabled(false); // Disable accept button
            // Update the status label to indicate no pending registrations
            statusLabel.setText("No pending registrations to display.");
        } else {
            // Add each pending student to the list model
            for (Student student : pendingStudents) {
                listModel.addElement(student);
            }
            acceptButton.setEnabled(true); // Enable button if there are pending students
            statusLabel.setText(pendingStudents.size() + " pending registration(s) found.");
        }
        // Ensure the list GUI component updates to reflect changes in its model
        registrationList.revalidate();
        registrationList.repaint();
    }
    /**
     * Handles the action of accepting a selected student's enrollment.
     * This method is called when the "Accept" button is clicked.
     */
    private void acceptSelectedStudent() {
        Student selectedStudent = registrationList.getSelectedValue();
        // Check if an item is actually selected in the list.
        if (selectedStudent == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a student from the list to accept their enrollment.",
                    "No Student Selected",
                    JOptionPane.WARNING_MESSAGE);
            return; // Exit if no student is selected
        }
        try {
            // Attempt to activate the student using the RegistrationService
            registrationService.activateStudent(selectedStudent.getId());
            JOptionPane.showMessageDialog(this,
                    "Student '" + selectedStudent.getName() + "' enrollment accepted successfully!",
                    "Enrollment Accepted",
                    JOptionPane.INFORMATION_MESSAGE);
            refreshPendingRegistrations(); // Refresh the list to remove the activated student
            statusLabel.setText("Enrollment for " + selectedStudent.getName() + " accepted.");
        } catch (IllegalArgumentException ex) {
            // Handle specific business logic exceptions (e.g., student not found or already active)
            JOptionPane.showMessageDialog(this,
                    "Error accepting enrollment: " + ex.getMessage(),
                    "Acceptance Error",
                    JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error: " + ex.getMessage());
        } catch (Exception ex) {
            // Catch any other unexpected errors
            JOptionPane.showMessageDialog(this,
                    "An unexpected error occurred: " + ex.getMessage(),
                    "System Error",
                    JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("System Error during acceptance.");
        }
    }
}