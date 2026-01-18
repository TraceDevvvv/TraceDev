/**
 * Entry point of the Report Card Insertion application.
 * Initializes the main JFrame and sets up the CardLayout to switch between different panels.
 * Simulates an administrator's interaction with the system.
 */
import javax.swing.*;
import java.awt.*;
public class Main extends JFrame {
    // CardLayout is used to manage different views (panels).
    private CardLayout cardLayout;
    // JPanel to hold all the different views.
    private JPanel mainPanel;
    // Constants for card names in CardLayout
    public static final String ADMIN_DASHBOARD = "AdminDashboard";
    public static final String CLASS_SELECTION = "ClassSelection";
    public static final String STUDENT_SELECTION = "StudentSelection";
    public static final String REPORT_CARD_INPUT = "ReportCardInput";
    // Reference to the system's data manager
    private ReportCardSystem system;
    // Store selected class and student
    private String selectedClassId;
    private String selectedStudentId;
    public Main() {
        // Initialize the JFrame
        setTitle("Report Card Management System - Administrator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        // Initialize the ReportCardSystem with some dummy data
        system = ReportCardSystem.getInstance();
        system.initializeData();
        // Setup CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        // Add the different panels to the mainPanel
        // The AdminDashboard is the first view presented after "login"
        AdminDashboard adminDashboard = new AdminDashboard(this);
        mainPanel.add(adminDashboard, ADMIN_DASHBOARD);
        // ClassSelectionPanel will be created dynamically or once but updated.
        // For simplicity, we create it once and update its content as needed.
        ClassSelectionPanel classSelectionPanel = new ClassSelectionPanel(this, system);
        mainPanel.add(classSelectionPanel, CLASS_SELECTION);
        // StudentSelectionPanel will be created once and updated.
        StudentSelectionPanel studentSelectionPanel = new StudentSelectionPanel(this, system);
        mainPanel.add(studentSelectionPanel, STUDENT_SELECTION);
        // ReportCardInputPanel will be created once and updated.
        ReportCardInputPanel reportCardInputPanel = new ReportCardInputPanel(this, system);
        mainPanel.add(reportCardInputPanel, REPORT_CARD_INPUT);
        add(mainPanel); // Add the panel with CardLayout to the frame
        // Display the initial view
        cardLayout.show(mainPanel, ADMIN_DASHBOARD);
        setVisible(true);
    }
    /**
     * Navigates to a specific panel by its name.
     * @param panelName The name of the panel to display (e.g., ADMIN_DASHBOARD, CLASS_SELECTION).
     */
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
    /**
     * Shows the class selection panel and ensures its data is refreshed.
     */
    public void showClassSelection() {
        // Find the ClassSelectionPanel and refresh its data
        Component[] components = mainPanel.getComponents();
        for (Component c : components) {
            if (c instanceof ClassSelectionPanel) {
                ((ClassSelectionPanel) c).loadClasses();
                break;
            }
        }
        showPanel(CLASS_SELECTION);
    }
    /**
     * Shows the student selection panel for a given class and ensures its data is refreshed.
     * @param classId The ID of the class whose students are to be displayed.
     */
    public void showStudentSelection(String classId) {
        this.selectedClassId = classId;
        // Find the StudentSelectionPanel and refresh its data
        Component[] components = mainPanel.getComponents();
        for (Component c : components) {
            if (c instanceof StudentSelectionPanel) {
                ((StudentSelectionPanel) c).loadStudents(classId);
                break;
            }
        }
        showPanel(STUDENT_SELECTION);
    }
    /**
     * Shows the report card input panel for a given student and ensures its data is loaded.
     * @param studentId The ID of the student whose report card is to be edited.
     */
    public void showReportCardInput(String studentId) {
        this.selectedStudentId = studentId;
        // Find the ReportCardInputPanel and load its data
        Component[] components = mainPanel.getComponents();
        for (Component c : components) {
            if (c instanceof ReportCardInputPanel) {
                ((ReportCardInputPanel) c).loadReportCard(studentId);
                break;
            }
        }
        showPanel(REPORT_CARD_INPUT);
    }
    /**
     * Gets the currently selected class ID.
     * @return The ID of the currently selected class.
     */
    public String getSelectedClassId() {
        return selectedClassId;
    }
    /**
     * Main method to run the application.
     * Creates an instance of the Main frame on the Event Dispatch Thread (EDT).
     * This is crucial for Swing applications to ensure thread safety.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}