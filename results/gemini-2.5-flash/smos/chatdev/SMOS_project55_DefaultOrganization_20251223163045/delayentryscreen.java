/*
This class represents the main GUI screen for the "InsertDelayAta" use case.
It displays a list of students, allows ATA staff to mark delays, specify delay times,
and submit the data.
Preconditions:
- The user is logged in as ATA staff (simulated: application starts, assuming login).
- A class has been selected (simulated: hardcoded student list).
Events sequence:
1. System shows screen with students and "delay" checkbox, activating time selection if checked.
2. User selects delays.
3. User clicks "Confirm".
4. System sends data to server.
Postconditions:
- Delay data entered in the system (simulated: ServerSimulator prints data).
- Initial screen shown again (simulated: UI resets).
- Connection to SMOS server interrupted / user interrupts operations (simulated: via "Cancel" button).
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class DelayEntryScreen extends JFrame implements ActionListener {
    private List<Student> students; // List of students for the selected class
    private Map<Student, JCheckBox> delayCheckboxes; // Maps student to their delay checkbox
    private Map<Student, JSpinner> delaySpinners;       // Maps student to their delay time spinner
    private ServerSimulator serverSimulator;           // Simulator for server interactions
    private JPanel studentsPanel; // Panel to hold student rows
    private JScrollPane scrollPane; // Scroll pane for the students panel
    private JButton confirmButton;
    private JButton cancelButton;
    /**
     * Constructs the DelayEntryScreen.
     * Initializes the GUI components, populates student data, and sets up event listeners.
     */
    public DelayEntryScreen() {
        super("Insert Delay - ATA Staff"); // Set window title
        this.serverSimulator = new ServerSimulator(); // Initialize server simulator
        this.students = new ArrayList<>();
        this.delayCheckboxes = new HashMap<>();
        this.delaySpinners = new HashMap<>();
        // Simulate loaded students for a chosen class
        loadStudents();
        initializeUI(); // Set up the graphical user interface
    }
    /**
     * Simulates loading student data for a specific class.
     * In a real application, this would fetch data from a database or API.
     */
    private void loadStudents() {
        students.add(new Student("S001", "Alice Smith"));
        students.add(new Student("S002", "Bob Johnson"));
        students.add(new Student("S003", "Charlie Brown"));
        students.add(new Student("S004", "Diana Miller"));
        students.add(new Student("S005", "Eve Davis"));
        students.add(new Student("S006", "Frank White"));
        students.add(new Student("S007", "Grace Lee"));
        students.add(new Student("S008", "Henry Clark"));
        students.add(new Student("S009", "Ivy Hall"));
        students.add(new Student("S010", "Jack Wilson"));
    }
    /**
     * Initializes all the UI components and lays them out in the JFrame.
     */
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(500, 600); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for main layout
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("<html><h2>Record Student Delays for Class [ABC-101]</h2></html>"));
        add(titlePanel, BorderLayout.NORTH);
        // Students Panel (where student rows with checkboxes and spinners will be)
        studentsPanel = new JPanel();
        studentsPanel.setLayout(new GridBagLayout()); // Using GridBagLayout for flexible student row alignment
        studentsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Create and add student rows
        createStudentRows();
        scrollPane = new JScrollPane(studentsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // Align buttons to the right
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");
        confirmButton.addActionListener(this); // Register action listener for confirm button
        cancelButton.addActionListener(this);  // Register action listener for cancel button
        buttonPanel.add(cancelButton);
        buttonPanel.add(confirmButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true); // Make the JFrame visible
    }
    /**
     * Creates and adds individual rows for each student to the studentsPanel.
     * Each row contains student name, a "delay" checkbox, and a delay time spinner.
     */
    private void createStudentRows() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Allow components to expand horizontally
        // Add header row
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        studentsPanel.add(new JLabel("<html><b>Student Name</b></html>"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        studentsPanel.add(new JLabel("<html><b>Delay?</b></html>"), gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        studentsPanel.add(new JLabel("<html><b>Delay (minutes)</b></html>"), gbc);
        int row = 1;
        for (Student student : students) {
            gbc.gridy = row;
            // Student Name Label
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.WEST;
            studentsPanel.add(new JLabel(student.getName()), gbc);
            // Delay Checkbox
            JCheckBox delayCheckbox = new JCheckBox();
            delayCheckboxes.put(student, delayCheckbox); // Store checkbox reference
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            studentsPanel.add(delayCheckbox, gbc);
            // Delay Spinner (initially disabled)
            SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 300, 5); // Default 0, min 0, max 300, step 5
            JSpinner delaySpinner = new JSpinner(spinnerModel);
            delaySpinner.setValue(0); // Default value is 0 minutes
            delaySpinner.setEnabled(false); // Spinner is disabled by default
            delaySpinners.put(student, delaySpinner); // Store spinner reference
            gbc.gridx = 2;
            gbc.anchor = GridBagConstraints.EAST;
            studentsPanel.add(delaySpinner, gbc);
            // Add ActionListener to checkbox to enable/disable spinner
            delayCheckbox.addActionListener(e -> {
                delaySpinner.setEnabled(delayCheckbox.isSelected());
                if (!delayCheckbox.isSelected()) {
                    delaySpinner.setValue(0); // Reset spinner value if delay is unchecked
                }
            });
            row++;
        }
        // Add a vertical glue to push components to the top if there are few students
        gbc.gridy = row;
        gbc.weighty = 1.0; // This row will take up all extra vertical space
        studentsPanel.add(Box.createVerticalGlue(), gbc);
    }
    /**
     * Handles action events from buttons.
     *
     * @param e The ActionEvent object generated by user interaction.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            handleConfirm();
        } else if (e.getSource() == cancelButton) {
            handleCancel();
        }
    }
    /**
     * Gathers the entered delay data for each student and sends it to the server simulator.
     * Resets the screen after successful submission.
     */
    private void handleConfirm() {
        List<Student> studentsWithDelays = new ArrayList<>();
        for (Student student : students) {
            JCheckBox checkbox = delayCheckboxes.get(student);
            JSpinner spinner = delaySpinners.get(student);
            if (checkbox != null && spinner != null) {
                boolean hasDelay = checkbox.isSelected();
                student.setHasDelay(hasDelay); // Update student model
                if (hasDelay) {
                    // When using SpinnerNumberModel, spinner.getValue() safely returns a Number (Integer in this case).
                    // No NumberFormatException is expected from this line, so the try-catch block is removed.
                    int delayValue = (Integer) spinner.getValue();
                    student.setDelayMinutes(delayValue); // Update student model
                    studentsWithDelays.add(student); // Add student to list for server
                } else {
                    student.setDelayMinutes(0); // No delay, ensure minutes are 0
                }
            }
        }
        // Send data to server (simulated)
        serverSimulator.sendDelayData(studentsWithDelays);
        // Postcondition: The delay data has been entered in the system.
        // Postcondition: The initial screen is shown again (by resetting current view).
        JOptionPane.showMessageDialog(this,
                "Delay data successfully recorded!",
                "Confirmation",
                JOptionPane.INFORMATION_MESSAGE);
        resetScreen(); // Reset UI for next operation
    }
    /**
     * Handles the cancellation of the operation.
     * Simulates disconnecting from the server and resets the screen.
     */
    private void handleCancel() {
        // Postcondition: Connection to the interrupted SMOS server. The user interrupts the operations.
        serverSimulator.interruptConnection(); // Simulate server interruption
        JOptionPane.showMessageDialog(this,
                "Operation cancelled. Returning to initial screen.",
                "Canceled",
                JOptionPane.INFORMATION_MESSAGE);
        resetScreen(); // Reset UI, effectively returning to an "initial screen" state
    }
    /**
     * Resets the UI components to their initial state (no delays selected, spinners disabled).
     */
    private void resetScreen() {
        for (Student student : students) {
            JCheckBox checkbox = delayCheckboxes.get(student);
            JSpinner spinner = delaySpinners.get(student);
            if (checkbox != null && spinner != null) {
                checkbox.setSelected(false); // Uncheck all delay checkboxes
                spinner.setValue(0);         // Reset all spinners to 0
                spinner.setEnabled(false);   // Disable all spinners
            }
            // Also reset the internal student model data
            student.setHasDelay(false);
            student.setDelayMinutes(0);
        }
    }
}