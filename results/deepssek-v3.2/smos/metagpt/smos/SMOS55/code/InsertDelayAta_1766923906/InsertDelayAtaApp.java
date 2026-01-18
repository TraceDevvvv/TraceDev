import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * InsertDelayAtaApp - Main application class for the ATA Delay Insertion System.
 * This class coordinates the entire application flow including authentication,
 * class selection, GUI initialization, and data handling.
 * Implements the complete InsertDelayAta use case.
 */
public class InsertDelayAtaApp {
    // System configuration
    private static final String APP_NAME = "ATA Delay Insertion System";
    private static final String VERSION = "1.0";
    private static final String DEFAULT_SERVER_URL = "http://localhost:8080/api/delays";
    
    // Application state
    private String currentStaffId;
    private String currentClassName;
    private boolean isAuthenticated;
    private DataHandler dataHandler;
    private InsertDelayAtaGUI currentGUI;
    
    /**
     * Main entry point of the application
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Ensure GUI runs on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                InsertDelayAtaApp app = new InsertDelayAtaApp();
                app.start();
            } catch (Exception e) {
                handleFatalError("Failed to start application", e);
            }
        });
    }
    
    /**
     * Constructor - Initializes application state
     */
    public InsertDelayAtaApp() {
        this.isAuthenticated = false;
        this.currentStaffId = null;
        this.currentClassName = null;
        this.dataHandler = null;
        this.currentGUI = null;
        
        System.out.println(APP_NAME + " v" + VERSION + " initialized.");
        System.out.println("=====================================");
    }
    
    /**
     * Starts the application flow
     */
    public void start() {
        System.out.println("Starting ATA Delay Insertion System...");
        
        try {
            // Step 1: User authentication (Precondition: user must be logged in as ATA staff)
            if (!performAuthentication()) {
                System.out.println("Authentication failed. Application terminating.");
                return;
            }
            
            // Step 2: Class selection (Precondition: user must have selected a class)
            if (!performClassSelection()) {
                System.out.println("Class selection cancelled. Application terminating.");
                return;
            }
            
            // Step 3: Initialize data handler with staff credentials
            initializeDataHandler();
            
            // Step 4: Load student data for the selected class
            List<Student> students = loadClassStudents();
            if (students == null || students.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "No students found for class: " + currentClassName,
                    "No Data",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Step 5: Create and show the main GUI
            startMainGUI(students);
            
        } catch (Exception e) {
            handleFatalError("Application error occurred", e);
        }
    }
    
    /**
     * Perform user authentication as per preconditions
     * @return True if authentication successful, false otherwise
     */
    private boolean performAuthentication() {
        System.out.println("Starting authentication process...");
        
        // In a real application, this would connect to an authentication service
        // For this example, we'll use a simple dialog-based authentication
        while (true) {
            AuthenticationDialog authDialog = new AuthenticationDialog();
            authDialog.setVisible(true);
            
            if (!authDialog.isAuthenticated()) {
                // User cancelled
                System.out.println("Authentication cancelled by user.");
                return false;
            }
            
            currentStaffId = authDialog.getStaffId();
            System.out.println("Authentication successful for staff: " + currentStaffId);
            
            // Additional authentication validation could be added here
            return true;
        }
    }
    
    /**
     * Perform class selection as per preconditions
     * @return True if class selected successfully, false otherwise
     */
    private boolean performClassSelection() {
        System.out.println("Starting class selection...");
        
        // In a real application, this would fetch classes from a database
        // For this example, we'll use a list of predefined classes
        String[] availableClasses = {
            "ATA Class 101 - Morning",
            "ATA Class 102 - Afternoon", 
            "ATA Class 201 - Advanced",
            "ATA Class 301 - Professional",
            "ATA Class 401 - Master"
        };
        
        String selectedClass = (String) JOptionPane.showInputDialog(null,
            "Select the class to enter delay data:",
            "Class Selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            availableClasses,
            availableClasses[0]);
        
        if (selectedClass == null || selectedClass.trim().isEmpty()) {
            System.out.println("Class selection cancelled.");
            return false;
        }
        
        currentClassName = selectedClass;
        System.out.println("Class selected: " + currentClassName);
        return true;
    }
    
    /**
     * Initialize the data handler for server communication
     */
    private void initializeDataHandler() {
        System.out.println("Initializing data handler...");
        
        // Create data handler with current staff ID
        dataHandler = new DataHandler(DEFAULT_SERVER_URL, currentStaffId);
        
        // Test server connection
        System.out.println("Testing server connection...");
        boolean connectionTest = dataHandler.testConnection();
        
        if (!connectionTest) {
            System.out.println("Server connection test failed. Switching to simulation mode.");
            dataHandler.setSimulationMode(true);
            
            int response = JOptionPane.showConfirmDialog(null,
                "Cannot connect to the SMOS server. Continue in offline/simulation mode?",
                "Server Connection Error",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (response != JOptionPane.YES_OPTION) {
                throw new RuntimeException("Application terminated due to server connection issues.");
            }
        } else {
            System.out.println("Server connection test successful.");
        }
    }
    
    /**
     * Load student data for the selected class
     * @return List of Student objects or empty list if none found
     */
    private List<Student> loadClassStudents() {
        System.out.println("Loading students for class: " + currentClassName);
        
        // In a real application, this would fetch data from a database
        // For this example, we'll create sample data
        List<Student> students = new ArrayList<>();
        
        // Generate sample student data based on the class name
        int studentCount = 15; // Sample size
        for (int i = 1; i <= studentCount; i++) {
            String studentId = String.format("ATA%03d", i);
            String firstName = getSampleFirstName(i);
            String lastName = getSampleLastName(i);
            boolean isPresent = Math.random() > 0.1; // 90% attendance rate
            
            students.add(new Student(studentId, firstName, lastName, isPresent));
        }
        
        System.out.println("Loaded " + students.size() + " students.");
        return students;
    }
    
    /**
     * Start the main GUI with the student data
     * @param students List of students to display
     */
    private void startMainGUI(List<Student> students) {
        System.out.println("Starting main GUI...");
        
        // Create the GUI as per Events sequence step 1
        currentGUI = new InsertDelayAtaGUI(currentClassName, currentStaffId, students);
        
        // Set up data handler for the GUI
        currentGUI.setDataHandler(dataHandler);
        
        // Add window listener to handle interruptions (Postcondition: user interrupts operations)
        currentGUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleUserInterruption();
            }
        });
        
        // Show the GUI
        currentGUI.setVisible(true);
        System.out.println("Main GUI is now visible.");
        
        // Log the application state
        logApplicationState();
    }
    
    /**
     * Handle user interruption as per postconditions
     */
    private void handleUserInterruption() {
        System.out.println("User interrupted operations.");
        
        // Check for pending data
        if (dataHandler != null && dataHandler.getPendingEntriesCount() > 0) {
            int response = JOptionPane.showConfirmDialog(null,
                "You have " + dataHandler.getPendingEntriesCount() + 
                " pending delay entries. Save before exiting?",
                "Pending Data",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (response == JOptionPane.YES_OPTION) {
                // Attempt to save data
                System.out.println("Attempting to save pending data before exit...");
                // In a real application, this would trigger data save
            } else if (response == JOptionPane.CANCEL_OPTION) {
                // Cancel exit
                if (currentGUI != null) {
                    currentGUI.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
                return;
            }
        }
        
        // Handle SMOS server connection interruption (Postcondition)
        if (dataHandler != null && !dataHandler.testConnection()) {
            System.out.println("SMOS server connection interruption detected.");
            JOptionPane.showMessageDialog(null,
                "SMOS server connection interrupted. Please check network connectivity.",
                "Server Connection",
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Exit the application
        System.out.println("Application terminating...");
        System.exit(0);
    }
    
    /**
     * Helper method to log application state
     */
    private void logApplicationState() {
        System.out.println("\n=== APPLICATION STATE ===");
        System.out.println("Staff ID: " + currentStaffId);
        System.out.println("Class: " + currentClassName);
        System.out.println("Server URL: " + (dataHandler != null ? dataHandler.getServerUrl() : "Not initialized"));
        System.out.println("Simulation Mode: " + (dataHandler != null && dataHandler.getOperationLog().contains("SIMULATION")));
        System.out.println("=========================\n");
    }
    
    /**
     * Get sample first name based on index (for demo data)
     */
    private String getSampleFirstName(int index) {
        String[] firstNames = {
            "John", "Jane", "Robert", "Emily", "Michael", "Sarah", "David", "Lisa", "James", "Jennifer",
            "William", "Mary", "Richard", "Patricia", "Charles", "Linda", "Thomas", "Barbara", "Christopher", "Susan"
        };
        return firstNames[(index - 1) % firstNames.length];
    }
    
    /**
     * Get sample last name based on index (for demo data)
     */
    private String getSampleLastName(int index) {
        String[] lastNames = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson",
            "Martinez", "Anderson", "Taylor", "Thomas", "Hernandez", "Moore", "Martin", "Jackson", "Thompson", "White"
        };
        return lastNames[(index - 1) % lastNames.length];
    }
    
    /**
     * Handle fatal application errors
     */
    private static void handleFatalError(String message, Exception e) {
        System.err.println("FATAL ERROR: " + message);
        e.printStackTrace();
        
        // Show error message to user
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null,
                message + "\n\nError details: " + e.getMessage(),
                "Fatal Error",
                JOptionPane.ERROR_MESSAGE);
        });
        
        // Exit with error code
        System.exit(1);
    }
    
    /**
     * Authentication dialog for ATA staff login
     */
    private class AuthenticationDialog extends JDialog {
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JButton loginButton;
        private JButton cancelButton;
        private boolean authenticated;
        private String staffId;
        
        public AuthenticationDialog() {
            super((JFrame) null, "ATA Staff Authentication", true);
            this.authenticated = false;
            this.staffId = null;
            
            initComponents();
            setupLayout();
            configureDialog();
        }
        
        private void initComponents() {
            usernameField = new JTextField(20);
            passwordField = new JPasswordField(20);
            
            // Set placeholder text
            usernameField.setText("ATA_Staff_");
            
            loginButton = new JButton("Login");
            loginButton.addActionListener(e -> onLogin());
            
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> onCancel());
            
            // Default button
            getRootPane().setDefaultButton(loginButton);
        }
        
        private void setupLayout() {
            JPanel mainPanel = new JPanel(new SpringLayout());
            
            // Add components
            mainPanel.add(new JLabel("Staff ID:"));
            mainPanel.add(usernameField);
            mainPanel.add(new JLabel("Password:"));
            mainPanel.add(passwordField);
            mainPanel.add(new JLabel(""));
            mainPanel.add(loginButton);
            mainPanel.add(new JLabel(""));
            mainPanel.add(cancelButton);
            
            // Layout configuration
            SpringUtilities.makeCompactGrid(mainPanel,
                4, 2, // rows, cols
                6, 6, // initX, initY
                6, 6); // xPad, yPad
            
            // Set layout and add components
            setLayout(new BorderLayout());
            add(mainPanel, BorderLayout.CENTER);
            
            // Add info label
            JLabel infoLabel = new JLabel("Enter your ATA staff credentials", JLabel.CENTER);
            infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            add(infoLabel, BorderLayout.NORTH);
        }
        
        private void configureDialog() {
            setSize(300, 200);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        
        private void onLogin() {
            String username = usernameField.getText().trim();
            char[] password = passwordField.getPassword();
            
            // Basic validation (in real app, this would connect to authentication service)
            if (username.isEmpty() || password.length == 0) {
                JOptionPane.showMessageDialog(this,
                    "Please enter both staff ID and password.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Demo authentication - accept any username starting with "ATA_"
            if (username.startsWith("ATA_")) {
                // Simulate authentication processing
                try {
                    Thread.sleep(500); // Simulate network delay
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                
                this.staffId = username;
                this.authenticated = true;
                dispose();
                System.out.println("Authentication successful for: " + staffId);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Invalid staff ID format. ATA staff IDs start with 'ATA_'",
                    "Authentication Failed",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        private void onCancel() {
            this.authenticated = false;
            dispose();
        }
        
        public boolean isAuthenticated() {
            return authenticated;
        }
        
        public String getStaffId() {
            return staffId;
        }
    }
    
    /**
     * SpringLayout utilities for dialog layout
     */
    private static class SpringUtilities {
        public static void makeCompactGrid(Container parent,
                                         int rows, int cols,
                                         int initialX, int initialY,
                                         int xPad, int yPad) {
            SpringLayout layout;
            try {
                layout = (SpringLayout) parent.getLayout();
            } catch (ClassCastException exc) {
                System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
                return;
            }

            Spring x = Spring.constant(initialX);
            for (int c = 0; c < cols; c++) {
                Spring width = Spring.constant(0);
                for (int r = 0; r < rows; r++) {
                    width = Spring.max(width,
                            getConstraintsForCell(r, c, parent, cols).getWidth());
                }
                for (int r = 0; r < rows; r++) {
                    SpringLayout.Constraints constraints =
                            getConstraintsForCell(r, c, parent, cols);
                    constraints.setX(x);
                }
                x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
            }

            Spring y = Spring.constant(initialY);
            for (int r = 0; r < rows; r++) {
                Spring height = Spring.constant(0);
                for (int c = 0; c < cols; c++) {
                    height = Spring.max(height,
                            getConstraintsForCell(r, c, parent, cols).getHeight());
                }
                for (int c = 0; c < cols; c++) {
                    SpringLayout.Constraints constraints =
                            getConstraintsForCell(r, c, parent, cols);
                    constraints.setY(y);
                }
                y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
            }

            SpringLayout.Constraints pCons = layout.getConstraints(parent);
            pCons.setConstraint(SpringLayout.SOUTH, y);
            pCons.setConstraint(SpringLayout.EAST, x);
        }
        
        private static SpringLayout.Constraints getConstraintsForCell(
                int row, int col, Container parent, int cols) {
            SpringLayout layout = (SpringLayout) parent.getLayout();
            Component c = parent.getComponent(row * cols + col);
            return layout.getConstraints(c);
        }
    }
}