/*
 * Main application class for the Report Card Viewer.
 * This class orchestrates the GUI panels using a CardLayout to follow the use case flow.
 * It serves as the main entry point for the application.
 */
import data.DataStore;
import data.DataStore.ServerConnectionException; // Import the custom exception
import gui.AcademicYearPanel;
import gui.ClassSelectionPanel;
import gui.ReportDisplayPanel;
import gui.StudentSelectionPanel;
// Import the new ReportRequest DTO
import gui.ReportRequest;
import model.ReportCard;
import model.Student;
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class ReportCardApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    // GUI Panels for each step
    private AcademicYearPanel academicYearPanel;
    private ClassSelectionPanel classSelectionPanel;
    private StudentSelectionPanel studentSelectionPanel;
    private ReportDisplayPanel reportDisplayPanel;
    // Data Store (mocked for this example)
    private DataStore dataStore;
    // Current selections
    private String selectedAcademicYear;
    private String selectedClass;
    private Student selectedStudent;
    private List<String> selectedMonths;
    /**
     * Constructs the main application frame and initializes all GUI components and logic.
     */
    public ReportCardApp() {
        super("Administrator Report Card Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        dataStore = new DataStore(); // Initialize DataStore once here
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        // Optional: To test the error simulation, uncomment the line below.
        // DataStore.setSimulateError(true);
        // Initialize panels with their respective callbacks
        initPanels();
        // Add panels to the main card layout
        mainPanel.add(academicYearPanel, "ACADEMIC_YEAR_SELECTION");
        mainPanel.add(classSelectionPanel, "CLASS_SELECTION");
        mainPanel.add(studentSelectionPanel, "STUDENT_MONTH_SELECTION");
        mainPanel.add(reportDisplayPanel, "REPORT_DISPLAY");
        add(mainPanel);
        // Start with the academic year selection panel
        cardLayout.show(mainPanel, "ACADEMIC_YEAR_SELECTION");
    }
    /**
     * Initializes each specific GUI panel and sets up their callback functions.
     * These callbacks define the navigation logic between different steps of the report generation process.
     */
    private void initPanels() {
        // Callback for AcademicYearPanel: when a year is selected, move to class selection
        // Pass the shared DataStore instance
        academicYearPanel = new AcademicYearPanel(dataStore, year -> {
            this.selectedAcademicYear = year;
            classSelectionPanel.setAcademicYear(year);
            cardLayout.show(mainPanel, "CLASS_SELECTION");
        });
        // Callback for ClassSelectionPanel: when a class is selected, move to student/month selection
        // Pass the shared DataStore instance
        classSelectionPanel = new ClassSelectionPanel(dataStore, (year, className) -> {
            this.selectedAcademicYear = year; // Redundant but good for clarity
            this.selectedClass = className;
            studentSelectionPanel.setClassDetails(year, className);
            cardLayout.show(mainPanel, "STUDENT_MONTH_SELECTION");
        });
        // Callback for StudentSelectionPanel: when a student and months are selected, generate and display report
        // Pass the shared DataStore instance
        studentSelectionPanel = new StudentSelectionPanel(dataStore, request -> { // Changed parameter type to ReportRequest
            // Extract data directly from the ReportRequest object
            this.selectedAcademicYear = request.getAcademicYear();
            this.selectedClass = request.getClassName();
            this.selectedStudent = request.getStudent();
            this.selectedMonths = request.getSelectedMonths();
            try {
                // Attempt to retrieve the report card, potentially encountering a simulated error
                ReportCard report = dataStore.getReportCard(
                    selectedAcademicYear, selectedClass, selectedStudent, selectedMonths);
                reportDisplayPanel.displayReport(report);
                cardLayout.show(mainPanel, "REPORT_DISPLAY");
            } catch (ServerConnectionException ex) {
                // Handle the simulated server connection error
                JOptionPane.showMessageDialog(mainPanel, ex.getMessage() + "\nPlease try again later.", "Server Connection Error", JOptionPane.ERROR_MESSAGE);
                // After an error, navigate back to the academic year selection to restart the process
                cardLayout.show(mainPanel, "ACADEMIC_YEAR_SELECTION");
                resetSelections(); // Clear previous selections for a fresh start
                academicYearPanel.resetPanel(); // Reset academic year panel display
            }
        });
        // Callback for ReportDisplayPanel: when "New Report" is clicked, go back to academic year selection
        reportDisplayPanel = new ReportDisplayPanel(aVoid -> {
            // Reset selections and navigate back to the first step
            resetSelections();
            academicYearPanel.resetPanel(); // Reset academic year panel display
            cardLayout.show(mainPanel, "ACADEMIC_YEAR_SELECTION");
        });
    }
    /**
     * Resets all current selections to allow for a new report generation process.
     */
    private void resetSelections() {
        this.selectedAcademicYear = null;
        this.selectedClass = null;
        this.selectedStudent = null;
        this.selectedMonths = null;
        // The individual panels are designed to reset their internal state when their `set` methods are called
        // or when new data is provided (e.g., setAcademicYear, setClassDetails), so explicit resets here
        // are mainly for the application state tracking.
    }
    /**
     * Main method to start the Report Card Viewer application.
     * Ensures GUI updates are done on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new ReportCardApp().setVisible(true);
        });
    }
}