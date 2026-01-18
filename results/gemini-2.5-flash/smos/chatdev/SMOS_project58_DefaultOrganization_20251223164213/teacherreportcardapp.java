import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
/*
 * Main application class for the Teacher Report Card Display.
 * This class implements the GUI using Java Swing, allowing a teacher
 * to navigate through academic years, classes, students, and display
 * their report cards for specific quadrimestres.
 *
 * Implements the following use case steps:
 * 1. View the list of academic years.
 * 2. Select the academic year.
 * 3. View the classes for the selected year.
 * 4. Select a class.
 * 5. Display the list of students in the chosen class.
 * 6. Select a pupil and a quadrimestre.
 * 7. Display the chosen student report.
 */
public class TeacherReportCardApp extends JFrame {
    // GUI Components
    private JComboBox<AcademicYear> academicYearComboBox;
    private JComboBox<Classroom> classComboBox;
    private JComboBox<Student> studentComboBox;
    private JComboBox<String> quadrimestreComboBox;
    private JTextArea reportDisplayArea;
    private JButton displayReportButton;
    // Data Source Simulator
    private SMOSServerSimulator smosServer;
    /**
     * Constructs the TeacherReportCardApp frame and initializes its components.
     * Sets up the GUI layout, loads initial data, and attaches event listeners.
     */
    public TeacherReportCardApp() {
        super("Teacher Report Card System");
        smosServer = new SMOSServerSimulator(); // Initialize the mock server
        // Set up the main window
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for main layout
        initComponents(); // Initialize GUI components
        layoutComponents(); // Arrange components within the frame
        setupListeners();   // Set up event handling for components
        loadInitialData();  // Populate initial academic years
        setVisible(true); // Make the frame visible
    }
    /**
     * Initializes all GUI components used in the application.
     */
    private void initComponents() {
        // Dropdowns for selection
        academicYearComboBox = new JComboBox<>();
        classComboBox = new JComboBox<>();
        classComboBox.setEnabled(false); // Start disabled
        studentComboBox = new JComboBox<>();
        studentComboBox.setEnabled(false); // Start disabled
        quadrimestreComboBox = new JComboBox<>(new String[]{"1st Quadrimestre", "2nd Quadrimestre"});
        quadrimestreComboBox.setEnabled(false); // Start disabled
        // Text area for displaying report cards
        reportDisplayArea = new JTextArea(20, 50);
        reportDisplayArea.setEditable(false); // Make it read-only
        reportDisplayArea.setWrapStyleWord(true); // Wrap words
        reportDisplayArea.setLineWrap(true);      // Wrap lines
        JScrollPane scrollPane = new JScrollPane(reportDisplayArea); // Add scroll capability
        // Button to trigger report display
        displayReportButton = new JButton("Display Report Card");
        displayReportButton.setEnabled(false); // Start disabled
    }
    /**
     * Arranges the initialized GUI components into the frame using JPanels and Layouts.
     */
    private void layoutComponents() {
        // Panel for controls (selection combo boxes and button)
        JPanel controlPanel = new JPanel(new GridLayout(5, 2, 5, 5)); // 5 rows, 2 columns, gaps
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.add(new JLabel("Select Academic Year:"));
        controlPanel.add(academicYearComboBox);
        controlPanel.add(new JLabel("Select Class:"));
        controlPanel.add(classComboBox);
        controlPanel.add(new JLabel("Select Student:"));
        controlPanel.add(studentComboBox);
        controlPanel.add(new JLabel("Select Quadrimestre:"));
        controlPanel.add(quadrimestreComboBox);
        controlPanel.add(new JLabel("")); // Empty label for spacing
        controlPanel.add(displayReportButton);
        // Panel to hold the report display area centrally
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder("Student Report Card Details"));
        displayPanel.add(new JScrollPane(reportDisplayArea), BorderLayout.CENTER); // Ensure scroll pane is used
        // Add panels to the main frame
        add(controlPanel, BorderLayout.WEST); // Controls on the left
        add(displayPanel, BorderLayout.CENTER); // Report on the right/center
    }
    /**
     * Sets up action listeners for the interactive GUI components.
     * This includes listeners for combo box selections and the display button.
     */
    private void setupListeners() {
        // Listener for Academic Year selection (System Event 1 & 2)
        academicYearComboBox.addActionListener(e -> {
            AcademicYear selectedYear = (AcademicYear) academicYearComboBox.getSelectedItem();
            if (selectedYear != null) {
                loadClasses(selectedYear); // This will internally call updateSelectionControlStates()
            } else {
                // If selection becomes null (e.g., all years removed, or initial state)
                classComboBox.removeAllItems();
                studentComboBox.removeAllItems();
                updateSelectionControlStates(); // Ensure dependent controls are accurately disabled
            }
            resetReportDisplay(); // Clear report display in all cases
        });
        // Listener for Class selection (System Event 3 & 4)
        classComboBox.addActionListener(e -> {
            Classroom selectedClass = (Classroom) classComboBox.getSelectedItem();
            if (selectedClass != null) {
                loadStudents(selectedClass); // This will internally call updateSelectionControlStates()
            } else {
                // If no class is selected (e.g., year changed to one with no classes)
                studentComboBox.removeAllItems(); // Clear students visually
                updateSelectionControlStates(); // Ensure dependent controls are accurately disabled
            }
            resetReportDisplay();
        });
        // Listener for Student selection (System Event 5 & 6)
        studentComboBox.addActionListener(e -> {
            updateSelectionControlStates(); // Update whenever student selection changes (enables/disables quadrimestre/button)
            resetReportDisplay(); // As new student selected, old report might be irrelevant
        });
        // Listener for Quadrimestre selection
        quadrimestreComboBox.addActionListener(e -> {
            resetReportDisplay(); // As new quadrimestre selected, old report might be irrelevant
            // No updateSelectionControlStates here as quadrimestre selection doesn't impact other components' enabled state
        });
        // Listener for "Display Report Card" button (User Event 6, triggers System Event 7)
        displayReportButton.addActionListener(this::displaySelectedReportCard);
    }
    /**
     * Loads the initial list of academic years into the academicYearComboBox.
     * This corresponds to System Event 1: "View the list of academic years in which at least one class in which the teacher teaches."
     */
    private void loadInitialData() {
        List<AcademicYear> years = smosServer.getAcademicYearsForTeacher();
        academicYearComboBox.removeAllItems(); // Clear any existing items
        for (AcademicYear year : years) {
            academicYearComboBox.addItem(year); // Add each year to the combo box
        }
        // Automatically select the first year if available to trigger class loading
        if (!years.isEmpty()) {
            academicYearComboBox.setSelectedIndex(0); // This will trigger the AcademicYear combo box listener
        } else {
            // Handle case where no academic years are found for the teacher
            reportDisplayArea.setText("No academic years found for this teacher. Please ensure the SMOS server has data for Professor Rossi.");
            academicYearComboBox.setEnabled(false); // Disable if no years for explicit clarity
            classComboBox.removeAllItems();
            studentComboBox.removeAllItems();
        }
        updateSelectionControlStates(); // Update control states after initial data load
    }
    /**
     * Loads classes for the selected academic year into the classComboBox.
     * This corresponds to System Event 3: "View the classes associated with the selected school year."
     *
     * @param selectedYear The academic year for which to load classes.
     */
    private void loadClasses(AcademicYear selectedYear) {
        classComboBox.removeAllItems(); // Clear previous class items
        List<Classroom> classes = smosServer.getClassesForAcademicYear(selectedYear);
        for (Classroom cls : classes) {
            classComboBox.addItem(cls); // Add each class to the combo box
        }
        // Automatically select the first class if available
        if (!classes.isEmpty()) {
            classComboBox.setSelectedIndex(0); // This will trigger classComboBox listener
        } else {
            // No classes found for the selected year
            reportDisplayArea.setText(String.format("No classes found for %s for this teacher.", selectedYear.getYear()));
            studentComboBox.removeAllItems(); // Clear students if no classes are available for the selected year
        }
        updateSelectionControlStates(); // Update controls state after classes are loaded/cleared
        resetReportDisplay();
    }
    /**
     * Loads students for the selected classroom into the studentComboBox.
     * This corresponds to System Event 5: "Displays the list of class students chosen by the user."
     *
     * @param selectedClass The classroom for which to load students.
     */
    private void loadStudents(Classroom selectedClass) {
        studentComboBox.removeAllItems(); // Clear previous student items
        List<Student> students = smosServer.getStudentsForClass(selectedClass);
        for (Student student : students) {
            studentComboBox.addItem(student); // Add each student to the combo box
        }
        if (students.isEmpty() && selectedClass != null) {
            reportDisplayArea.setText(String.format("No students found in class %s.", selectedClass.getName()));
        }
        updateSelectionControlStates(); // Update controls state after students are loaded/cleared
        resetReportDisplay();
    }
    /**
     * Displays the report card for the currently selected student and quadrimestre.
     * This corresponds to System Event 7: "Displays the chosen student report referred to the specified quarter."
     * Handles cases where selections are missing.
     */
    private void displaySelectedReportCard(ActionEvent e) {
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        String selectedQuadrimestre = (String) quadrimestreComboBox.getSelectedItem();
        // With updateSelectionControlStates, these checks are mostly redundant as the button won't be enabled
        // if a student isn't selected, but kept for robustness.
        if (selectedStudent == null) {
            reportDisplayArea.setText("Please select a student.");
            return;
        }
        if (selectedQuadrimestre == null || selectedQuadrimestre.isEmpty()) {
            reportDisplayArea.setText("Please select a quadrimestre.");
            return;
        }
        // Retrieve the report card from the simulated server
        Optional<ReportCard> reportCard = smosServer.getReportCard(selectedStudent, selectedQuadrimestre);
        if (reportCard.isPresent()) {
            reportDisplayArea.setText(reportCard.get().toString()); // Display the formatted report
        } else {
            reportDisplayArea.setText(String.format("No report card found for %s for %s.",
                    selectedStudent.getName(), selectedQuadrimestre));
        }
    }
    /**
     * Clears only the report display area.
     * Used when student or quadrimestre selection changes, but other selections remain valid.
     */
    private void resetReportDisplay() {
        reportDisplayArea.setText("");
    }
    /**
     * Updates the enabled state of the class, student, quadrimestre, and display report controls
     * based on the current selections and item counts in their respective combo boxes.
     * This method ensures controls are only active when upstream selections are valid and
     * there are actual items to select.
     */
    private void updateSelectionControlStates() {
        // Class selection depends on academic year selection
        boolean academicYearSelectedAndAvailable = academicYearComboBox.getSelectedItem() != null && academicYearComboBox.getItemCount() > 0;
        classComboBox.setEnabled(academicYearSelectedAndAvailable && classComboBox.getItemCount() > 0);
        // Student selection depends on class selection
        boolean classSelectedAndAvailable = classComboBox.getSelectedItem() != null && classComboBox.getItemCount() > 0;
        studentComboBox.setEnabled(classSelectedAndAvailable && studentComboBox.getItemCount() > 0);
        // Quadrimestre and display button depend on student selection
        boolean studentSelectedAndAvailable = studentComboBox.getSelectedItem() != null && studentComboBox.getItemCount() > 0;
        quadrimestreComboBox.setEnabled(studentSelectedAndAvailable);
        displayReportButton.setEnabled(studentSelectedAndAvailable);
    }
    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(TeacherReportCardApp::new);
    }
}