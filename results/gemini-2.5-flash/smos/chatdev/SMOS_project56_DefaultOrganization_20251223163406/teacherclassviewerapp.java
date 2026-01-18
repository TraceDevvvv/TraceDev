/**
 * This is the main application class for the Teacher Class Viewer.
 * It provides a graphical user interface for a teacher to view their classes,
 * filtered by academic year, as per the use case requirements.
 * It simulates the user being logged in as a specific teacher and retrieving data from a mock server.
 */
package gui;
import model.AcademicYear;
import model.SchoolClass;
import model.Teacher;
import service.SMOS_Server;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
public class TeacherClassViewerApp extends JFrame {
    // Simulating a logged-in teacher. In a real app, this would come from a login session.
    private final String currentTeacherId = "T001"; // Professor Smith
    private Teacher currentTeacher;
    private JComboBox<AcademicYear> yearComboBox;
    private DefaultListModel<SchoolClass> classListModel;
    private JList<SchoolClass> classList;
    private JLabel teacherInfoLabel;
    private JLabel statusLabel;
    /**
     * Constructor for the TeacherClassViewerApp.
     * Initializes the application by fetching teacher data and setting up the GUI.
     */
    public TeacherClassViewerApp() {
        super("Teacher Class Viewer - Digital Log");
        // Attempt to load the current teacher's information
        currentTeacher = SMOS_Server.getTeacherById(currentTeacherId);
        if (currentTeacher == null) {
            JOptionPane.showMessageDialog(this,
                    "Error: Teacher ID " + currentTeacherId + " not found. Exiting.",
                    "Login Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Exit if the simulated teacher doesn't exist
        }
        initializeGUI(); // Set up the graphical user interface
        loadAcademicYears(); // Populate the academic years dropdown
    }
    /**
     * Initializes all the GUI components and lays them out in the JFrame.
     */
    private void initializeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall layout
        // --- Header Panel (North) ---
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        teacherInfoLabel = new JLabel("Logged in as: " + currentTeacher.getName() + " (ID: " + currentTeacher.getId() + ")");
        Font labelFont = teacherInfoLabel.getFont();
        teacherInfoLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 14));
        headerPanel.add(teacherInfoLabel);
        add(headerPanel, BorderLayout.NORTH);
        // --- Control Panel (Center - Top) ---
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add some padding
        JLabel yearLabel = new JLabel("Select Academic Year:");
        yearComboBox = new JComboBox<>();
        yearComboBox.setPreferredSize(new Dimension(200, 25)); // Set preferred size for consistency
        // Add an action listener to respond to year selection changes
        yearComboBox.addActionListener(e -> {
            loadClassesForSelectedYear();
            statusLabel.setText("Displaying classes for selected year.");
        });
        controlPanel.add(yearLabel);
        controlPanel.add(yearComboBox);
        // --- Class List Panel (Center - Bottom, or filling remaining center) ---
        classListModel = new DefaultListModel<>();
        classList = new JList<>(classListModel);
        classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one class can be selected
        classList.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Consistent font for class display
        JScrollPane scrollPane = new JScrollPane(classList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Classes Taught"));
        // Use a JPanel for the main content area to combine controls and list
        JPanel contentPanel = new JPanel(new BorderLayout(5, 5));
        contentPanel.add(controlPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
        // --- Status Bar (South) ---
        statusLabel = new JLabel("Ready.");
        statusLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        add(statusLabel, BorderLayout.SOUTH); // Fix: Replaced statusPanel with statusLabel
    }
    /**
     * Loads the academic years in which the current teacher teaches at least one class
     * and populates the yearComboBox.
     * Handles the display of academic years (Event Sequence 1).
     */
    private void loadAcademicYears() {
        statusLabel.setText("Fetching academic years...");
        try {
            List<AcademicYear> years = SMOS_Server.getAcademicYearsForTeacher(currentTeacherId);
            if (years.isEmpty()) {
                yearComboBox.addItem(new AcademicYear("NONE", "No Years Available"));
                yearComboBox.setEnabled(false);
                statusLabel.setText("No academic years found for " + currentTeacher.getName() + ".");
            } else {
                yearComboBox.removeAllItems(); // Clear existing items
                for (AcademicYear year : years) {
                    yearComboBox.addItem(year);
                }
                yearComboBox.setEnabled(true);
                // Select the first year by default and load its classes
                if (yearComboBox.getItemCount() > 0) {
                    yearComboBox.setSelectedIndex(0);
                    loadClassesForSelectedYear();
                }
                statusLabel.setText("Academic years loaded. Please select a year.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error fetching academic years: " + e.getMessage(),
                    "Data Error", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error loading academic years.");
            e.printStackTrace();
        }
    }
    /**
     * Loads and displays the classes for the currently selected academic year.
     * This is triggered when the user selects an academic year (Event Sequence 3).
     */
    private void loadClassesForSelectedYear() {
        AcademicYear selectedYear = (AcademicYear) yearComboBox.getSelectedItem();
        classListModel.clear(); // Clear previously displayed classes
        if (selectedYear == null || "NONE".equals(selectedYear.getId())) {
            // Note: Creating a dummy SchoolClass for display purposes.
            // In a real application, you might handle this by adding a simple string message.
            classListModel.addElement(new SchoolClass("", "No classes to display.", "", ""));
            statusLabel.setText("No academic year selected or no classes available.");
            return;
        }
        statusLabel.setText("Fetching classes for " + selectedYear.getDisplayLabel() + "...");
        try {
            List<SchoolClass> classes = SMOS_Server.getClassesForTeacherAndYear(currentTeacherId, selectedYear.getId());
            if (classes.isEmpty()) {
                // Note: Creating a dummy SchoolClass for display purposes.
                classListModel.addElement(new SchoolClass("", "No classes found for this year.", "", ""));
                statusLabel.setText("No classes found for " + selectedYear.getDisplayLabel() + ".");
            } else {
                for (SchoolClass cls : classes) {
                    classListModel.addElement(cls);
                }
                statusLabel.setText("Classes loaded for " + selectedYear.getDisplayLabel() + ".");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error fetching classes: " + e.getMessage(),
                    "Data Error", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error loading classes for " + selectedYear.getDisplayLabel() + ".");
            e.printStackTrace();
        }
        // Postcondition: Connection to the interrupted SMOS server.
        // In this simulated environment, each method call to SMOS_Server
        // acts as a separate, independent query, implicitly interrupting/closing
        // the conceptual connection after data retrieval.
    }
    /**
     * Main method to run the application.
     * Creates and displays the TeacherClassViewerApp.
     * Prerequisites: User is logged in (simulated by currentTeacherId),
     * and clicks "Digital Log" button (simulated by launching the application).
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new TeacherClassViewerApp().setVisible(true);
        });
    }
}