/**
 * Main GUI frame for inserting student absences.
 * Displays a list of students with radio buttons to mark them as present or absent.
 * Implements the sequence described in the use case.
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
public class InsertAbsencesFrame extends JFrame {
    private AbsenceService absenceService;
    private JPanel studentPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private ButtonGroup[] buttonGroups;
    private List<Student> students;
    private String selectedClass = "Class A"; // Default class, would come from previous screen
    /**
     * Constructor for the main application frame.
     * @param absenceService service layer for handling absence operations
     */
    public InsertAbsencesFrame(AbsenceService absenceService) {
        this.absenceService = absenceService;
        // Set up the main frame
        setTitle("Insert Absences - ATA Staff System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        // Add window listener to handle interruptions
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleInterruption();
            }
        });
        // Initialize components
        initializeComponents();
        // Center the frame on screen
        setLocationRelativeTo(null);
    }
    /**
     * Initializes all GUI components and layouts.
     */
    private void initializeComponents() {
        // Create header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel("Insert Absences - " + selectedClass);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);
        // Create instructions panel
        JPanel instructionsPanel = new JPanel(new BorderLayout());
        instructionsPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        JLabel instructionsLabel = new JLabel(
            "<html>Select students who are absent. By default, all students are marked as present.<br>" +
            "Click Save to submit absences and send notifications to parents.</html>"
        );
        instructionsPanel.add(instructionsLabel, BorderLayout.CENTER);
        add(instructionsPanel, BorderLayout.CENTER);
        // Create student list panel with scroll pane
        studentPanel = new JPanel();
        studentPanel.setLayout(new GridLayout(0, 1, 5, 5));
        studentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(studentPanel);
        scrollPane.setBorder(new TitledBorder("Students - Select Absences"));
        add(scrollPane, BorderLayout.CENTER);
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 30));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAbsences();
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetToDefault();
            }
        });
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // Load students for the selected class
        loadStudents();
    }
    /**
     * Loads students for the current class and displays them in the GUI.
     */
    private void loadStudents() {
        students = absenceService.getStudentsByClass(selectedClass);
        buttonGroups = new ButtonGroup[students.size()];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            // Create panel for each student
            JPanel studentEntry = new JPanel(new BorderLayout());
            studentEntry.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            // Student info label
            JLabel studentLabel = new JLabel(
                "<html><b>" + student.getFullName() + "</b><br>" +
                "Parent Email: " + student.getParentEmail() + "</html>"
            );
            studentEntry.add(studentLabel, BorderLayout.WEST);
            // Radio buttons for presence/absence
            JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JRadioButton presentButton = new JRadioButton("Present", student.isPresent());
            JRadioButton absentButton = new JRadioButton("Absent", !student.isPresent());
            buttonGroups[i] = new ButtonGroup();
            buttonGroups[i].add(presentButton);
            buttonGroups[i].add(absentButton);
            // Add action listeners to update student status
            presentButton.addActionListener(e -> student.setPresent(true));
            absentButton.addActionListener(e -> student.setPresent(false));
            radioPanel.add(presentButton);
            radioPanel.add(absentButton);
            studentEntry.add(radioPanel, BorderLayout.EAST);
            studentPanel.add(studentEntry);
        }
        // Refresh the panel
        studentPanel.revalidate();
        studentPanel.repaint();
    }
    /**
     * Handles saving of absence data.
     * Implements step 4 from the use case: sends data to server and sends email notifications.
     */
    private void saveAbsences() {
        // Check if there are any absent students
        boolean hasAbsences = false;
        for (Student student : students) {
            if (!student.isPresent()) {
                hasAbsences = true;
                break;
            }
        }
        if (!hasAbsences) {
            int option = JOptionPane.showConfirmDialog(this,
                "No students are marked as absent. Do you want to save anyway?",
                "No Absences", JOptionPane.YES_NO_OPTION);
            if (option != JOptionPane.YES_OPTION) {
                return;
            }
        }
        try {
            // Show progress dialog
            JDialog progressDialog = new JDialog(this, "Saving Absences", true);
            progressDialog.setSize(300, 100);
            progressDialog.setLocationRelativeTo(this);
            JLabel progressLabel = new JLabel("Saving absence data and sending notifications...");
            progressLabel.setHorizontalAlignment(SwingConstants.CENTER);
            progressDialog.add(progressLabel);
            // Use SwingWorker for background processing
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    // Save absences and send notifications
                    absenceService.saveAbsences(students, selectedClass);
                    return null;
                }
                @Override
                protected void done() {
                    progressDialog.dispose();
                    try {
                        get(); // Check for exceptions
                        // Show success message
                        JOptionPane.showMessageDialog(InsertAbsencesFrame.this,
                            "Absence data has been saved successfully.\n" +
                            "Email notifications have been sent to parents.",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                        // Reset to default (showing initial screen again as per postconditions)
                        resetToDefault();
                    } catch (Exception e) {
                        // Handle specific connection interruption
                        if (e.getCause() instanceof java.net.ConnectException) {
                            JOptionPane.showMessageDialog(InsertAbsencesFrame.this,
                                "Error: Connection to the SMOS server was interrupted.\n" +
                                "Please check your connection and try again.",
                                "Connection Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(InsertAbsencesFrame.this,
                                "Error saving absences: " + e.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            };
            worker.execute();
            progressDialog.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Unexpected error: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Resets the form to default state (all students present).
     * This represents showing the initial screen again as per postconditions.
     */
    private void resetToDefault() {
        // Reset all students to present
        for (Student student : students) {
            student.setPresent(true);
        }
        // Clear and reload the student panel
        studentPanel.removeAll();
        loadStudents();
    }
    /**
     * Handles interruption of the operation (window closing).
     * Implements the interruption scenario from the use case.
     */
    private void handleInterruption() {
        int option = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to cancel? Any unsaved changes will be lost.",
            "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
        // If NO is selected, do nothing (window remains open)
    }
}