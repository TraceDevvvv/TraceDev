/**
 * This is the main application screen where ATA staff can register student absences.
 * It displays a list of students with radio buttons to mark them as present or absent.
 * It handles user interaction, sends data to a simulated server, and triggers parent notifications.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class AbsenceTrackerApp extends JFrame {
    private String className;
    private List<Student> students;
    private List<StudentEntryPanel> studentPanels;
    private ClassSelectionScreen parentScreen; // Reference to the previous screen to return to
    /**
     * Constructs the AbsenceTrackerApp.
     *
     * @param className    The name of the class for which absences are being recorded.
     * @param students     The list of students in the selected class.
     * @param parentScreen The ClassSelectionScreen instance that launched this frame.
     */
    public AbsenceTrackerApp(String className, List<Student> students, ClassSelectionScreen parentScreen) {
        this.className = className;
        this.students = students;
        this.parentPanels = new ArrayList<>(); // Initialize the list
        this.parentScreen = parentScreen;
        setTitle("Register Absences for " + className + " - Date: " + LocalDate.now());
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Custom closing behavior
        setLocationRelativeTo(null); // Center the window
        // Handle window closing event to simulate user interrupting operation
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int confirm = JOptionPane.showConfirmDialog(
                        AbsenceTrackerApp.this,
                        "Are you sure you want to interrupt this operation?",
                        "Confirm Interrupt",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.out.println("\nUSER INTERRUPTED OPERATION.");
                    parentScreen.setVisible(true); // Show the previous screen
                    dispose(); // Close this window
                }
            }
        });
        initComponents();
        setVisible(true);
    }
    /**
     * Initializes the GUI components of the application.
     * Sets up student list with radio buttons and the save button.
     */
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Header
        JLabel headerLabel = new JLabel("Mark Student Absences (" + className + ")", SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        // Student list panel
        JPanel studentsPanel = new JPanel();
        studentsPanel.setLayout(new BoxLayout(studentsPanel, BoxLayout.Y_AXIS));
        if (students.isEmpty()) {
            studentsPanel.add(new JLabel("No students found for this class."));
        } else {
            for (Student student : students) {
                StudentEntryPanel studentEntry = new StudentEntryPanel(student);
                studentPanels.add(studentEntry);
                studentsPanel.add(studentEntry);
                studentsPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacing
            }
        }
        JScrollPane scrollPane = new JScrollPane(studentsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Save button
        JButton saveButton = new JButton("Save Absences");
        saveButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAbsences();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Handles the "Save" action.
     * Collects absence data, sends it to the simulated server, triggers notifications,
     * logs data, and then returns to the class selection screen.
     */
    private void saveAbsences() {
        // Update the student objects based on UI selections
        for (StudentEntryPanel panel : studentPanels) {
            panel.updateStudentPresence();
        }
        // Simulate sending data to server
        boolean serverSuccess = ServerCommunicator.sendAbsenceDataToServer(className, students);
        if (!serverSuccess) {
            JOptionPane.showMessageDialog(this,
                    "Failed to communicate with the SMOS server. Please try again later.",
                    "Server Error", JOptionPane.ERROR_MESSAGE);
            // The system should not proceed with notifications or local logging if server communication failed.
            // In some cases, a retry mechanism or local queuing might be implemented.
            // For this use case, we just show an error and stay on the current screen.
            return;
        }
        // Log absence data to the system (simulated as well)
        AbsenceLogger.logAbsenceData(className, students);
        // For each absent student, send a notification email to the parent
        boolean notificationsSent = false;
        for (Student student : students) {
            if (!student.isPresent()) {
                ParentNotifier.sendAbsenceNotification(student.getParentEmail(), student.getName(), className, LocalDate.now());
                notificationsSent = true;
            }
        }
        String message = "Absence data successfully saved.";
        if (notificationsSent) {
            message += "\nNotifications sent to parents of absent students.";
        } else {
            message += "\nNo absences recorded; no notifications sent.";
        }
        JOptionPane.showMessageDialog(this, message, "Save Successful", JOptionPane.INFORMATION_MESSAGE);
        // Postconditions: "The initial screen is shown again." (Class selection screen)
        parentScreen.setVisible(true); // Show the previous screen
        dispose(); // Close this window
    }
    /**
     * Inner class representing a single student entry in the absence tracking list.
     * Each entry contains the student's name and radio buttons for Present/Absent.
     */
    private class StudentEntryPanel extends JPanel {
        private Student student;
        private JRadioButton presentRadioButton;
        private JRadioButton absentRadioButton;
        /**
         * Constructs a StudentEntryPanel for a given student.
         *
         * @param student The student object to display and track presence for.
         */
        public StudentEntryPanel(Student student) {
            this.student = student;
            setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
            setBorder(BorderFactory.createEtchedBorder());
            JLabel nameLabel = new JLabel(student.getName());
            nameLabel.setPreferredSize(new Dimension(150, 20)); // Fixed width for name label
            add(nameLabel);
            presentRadioButton = new JRadioButton("Present");
            absentRadioButton = new JRadioButton("Absent");
            ButtonGroup statusGroup = new ButtonGroup();
            statusGroup.add(presentRadioButton);
            statusGroup.add(absentRadioButton);
            // Default: student is present
            presentRadioButton.setSelected(student.isPresent());
            absentRadioButton.setSelected(!student.isPresent());
            add(presentRadioButton);
            add(absentRadioButton);
        }
        /**
         * Updates the internal {@link Student} object's presence status based on the radio button selection.
         */
        public void updateStudentPresence() {
            student.setPresent(presentRadioButton.isSelected());
        }
    }
}