I can see from the compilation error that the Java file has several issues:
1. There are illegal characters at the beginning (backticks from a code block)
2. The file has an unclosed comment and missing StudentDelay class definition

The file appears to have been saved with code block formatting (backticks) from the previous Engineer2.write_new_code command. I need to create a clean, complete Java program without any formatting issues. I'll rewrite the entire InsertDelayAdmin.java file with proper Java syntax, including the complete StudentDelay class and all necessary imports and methods.

Here's the complete, correct Java program:

```
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

/**
 * InsertDelayAdmin - Complete Java program for the InsertDelayAdmin use case.
 * This program simulates the workflow of an administrator entering student delay data,
 * sending it to a server, and logging the operation.
 * 
 * Actors: Administrator
 * Description: Register digitization, data entry by the administrator
 * 
 * Preconditions:
 * - User must be logged in as administrator
 * - User has selected the date for entering information via 'SeveralTetTingloregister'
 * 
 * Events sequence:
 * 1. Update screen displayed according to date
 * 2. Fill out form with students having delays and click "Save"
 * 3. Send data to server
 * 4. Display updated log data
 * 
 * Postconditions:
 * - Delay data entered in system
 * - System sent notifications to parents
 * - System remains on registry screen
 * - Administrator can interrupt operation
 * - Connection to interrupted SMOS server
 */
public class InsertDelayAdmin {
    
    // Logger for the application
    private static final Logger LOGGER = Logger.getLogger(InsertDelayAdmin.class.getName());
    
    // Server connection constants
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;
    
    // Date format for user input and display
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    // Main entry point
    public static void main(String[] args) {
        try {
            // Setup logging
            setupLogging();
            
            LOGGER.info("Starting InsertDelayAdmin application");
            
            // Step 0: Admin login (simulated from previous use case)
            if (!authenticateAdmin()) {
                LOGGER.severe("Authentication failed. Exiting application.");
                System.out.println("ERROR: Authentication failed. You must be logged in as administrator.");
                return;
            }
            
            // Step 1: Update screen according to selected date
            Date selectedDate = getSelectedDate();
            System.out.println("Screen updated for date: " + DATE_FORMAT.format(selectedDate));
            LOGGER.info("Screen updated for date: " + DATE_FORMAT.format(selectedDate));
            
            // Step 2: Fill out form with delayed students
            List<StudentDelay> delayedStudents = fillDelayForm();
            
            // Check if user wants to interrupt operation
            if (checkForInterruption()) {
                handleInterruption();
                return;
            }
            
            // Step 3: Send data to server
            boolean sendSuccess = sendDataToServer(delayedStudents, selectedDate);
            
            if (sendSuccess) {
                // Step 4: Send notifications to parents
                sendNotificationsToParents(delayedStudents);
                
                // Step 5: Display updated log data
                displayUpdatedLogData();
                
                System.out.println("\nOperation completed successfully!");
                System.out.println("Delay data has been entered in the system.");
                System.out.println("System remains on the registry screen.");
                
                LOGGER.info("InsertDelayAdmin operation completed successfully");
            } else {
                System.out.println("\nERROR: Failed to send data to server. Operation aborted.");
                LOGGER.severe("Failed to send data to server");
            }
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error in InsertDelayAdmin", e);
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    /**
     * Setup logging configuration
     */
    private static void setupLogging() {
        try {
            // Remove default handlers
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }
            
            // Create console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            consoleHandler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord record) {
                    return DATE_FORMAT.format(new Date(record.getMillis())) + " " +
                           record.getLevel() + ": " + 
                           record.getMessage() + "\n";
                }
            });
            
            // Create file handler
            FileHandler fileHandler = new FileHandler("InsertDelayAdmin.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord record) {
                    return DATE_FORMAT.format(new Date(record.getMillis())) + " " +
                           record.getLevel() + ": " + 
                           record.getMessage() + "\n";
                }
            });
            
            // Add handlers to logger
            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
            
            LOGGER.config("Logging setup completed");
            
        } catch (IOException e) {
            System.err.println("Failed to setup logging: " + e.getMessage());
        }
    }
    
    /**
     * Simulate admin authentication
     * @return true if authentication successful, false otherwise
     */
    private static boolean authenticateAdmin() {
        System.out.println("=== ADMIN AUTHENTICATION ===");
        System.out.println("(Simulating authentication from previous use case 'SeveralTetTingloregister')");
        
        // In a real application, this would check session or credentials
        // For this simulation, we'll assume successful login from previous use case
        LOGGER.info("Admin authentication simulated - user logged in from previous use case");
        return true;
    }
    
    /**
     * Get the selected date from user (simulating from previous use case)
     * @return Selected date
     */
    private static Date getSelectedDate() {
        Scanner scanner = new Scanner(System.in);
        Date selectedDate = new Date(); // Default to today
        
        System.out.println("\n=== DATE SELECTION ===");
        System.out.println("(Simulating date selection from 'SeveralTetTingloregister')");
        System.out.print("Enter date for delay entry (YYYY-MM-DD) [Press Enter for today]: ");
        
        try {
            String dateInput = scanner.nextLine().trim();
            
            if (!dateInput.isEmpty()) {
                selectedDate = DATE_FORMAT.parse(dateInput);
                LOGGER.info("Date selected by user: " + dateInput);
            } else {
                LOGGER.info("Using default date (today): " + DATE_FORMAT.format(selectedDate));
            }
            
        } catch (Exception e) {
            System.out.println("Invalid date format. Using today's date.");
            LOGGER.warning("Invalid date input, using default: " + e.getMessage());
        }
        
        return selectedDate;
    }
    
    /**
     * Fill out the delay form with student information
     * @return List of students with delay information
     */
    private static List<StudentDelay> fillDelayForm() {
        System.out.println("\n=== DELAY ENTRY FORM ===");
        System.out.println("Enter students with delays (type 'done' when finished):");
        
        List<StudentDelay> delayedStudents = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n--- Student Entry ---");
            
            System.out.print("Student ID: ");
            String studentId = scanner.nextLine().trim();
            
            if (studentId.equalsIgnoreCase("done")) {
                break;
            }
            
            if (studentId.isEmpty()) {
                System.out.println("ERROR: Student ID cannot be empty. Skipping entry.");
                LOGGER.warning("Empty student ID encountered, skipping entry");
                continue;
            }
            
            System.out.print("Student Name: ");
            String studentName = scanner.nextLine().trim();
            
            if (studentName.isEmpty()) {
                System.out.println("ERROR: Student name cannot be empty. Skipping entry.");
                LOGGER.warning("Empty student name encountered for ID " + studentId + ", skipping entry");
                continue;
            }
            
            System.out.print("Delay Reason: ");
            String delayReason = scanner.nextLine().trim();
            
            if (delayReason.isEmpty()) {
                System.out.println("WARNING: No delay reason provided. Using 'Unknown'.");
                delayReason = "Unknown";
                LOGGER.warning("Empty delay reason for student " + studentId);
            }
            
            System.out.print("Parent Email (for notification): ");
            String parentEmail = scanner.nextLine().trim();
            
            // Validate email format (basic validation)
            if (!parentEmail.isEmpty() && !parentEmail.contains("@")) {
                System.out.println("WARNING: Invalid email format. Notification may fail.");
                LOGGER.warning("Invalid email format for student " + studentId + ": " + parentEmail);
            }
            
            // Create and add student delay record
            StudentDelay studentDelay = new StudentDelay(studentId, studentName, delayReason, parentEmail);
            delayedStudents.add(studentDelay);
            
            System.out.println("Student " + studentName + " added to delay list.");
            LOGGER.info("Added student to delay list: " + studentDelay);
        }
        
        if (delayedStudents.isEmpty()) {
            System.out.println("\nWARNING: No students entered. Form submission will be empty.");
            LOGGER.warning("Delay form submitted with no students");
        } else {
            System.out.println("\nForm completed with " + delayedStudents.size() + " student(s).");
            LOGGER.info("Form completed with " + delayedStudents.size() + " student(s)");
        }
        
        return delayedStudents;
    }
    
    /**
     * Check if administrator wants to interrupt the operation
     * @return true if interruption requested, false otherwise
     */
    private static boolean checkForInterruption() {
        System.out.print("\nDo you want to interrupt the operation? (yes/no) [default: no]: ");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        
        if (response.equals("yes") || response.equals("y")) {
            LOGGER.info("Administrator requested operation interruption");
            return true;
        }
        
        return false;
    }
    
    /**
     * Handle operation interruption
     */
    private static void handleInterruption() {
        System.out.println("\n=== OPERATION INTERRUPTED ===");
        System.out.println("System remains on the registry screen.");
        
        // Simulate connection to interrupted SMOS server
        System.out.println("Connecting to interrupted SMOS server...");
        simulateSMOSServerConnection();
        
        LOGGER.info("Operation interrupted by administrator");
        LOGGER.info("Connected to interrupted SMOS server");
    }
    
    /**
     * Simulate connection to SMOS server
     */
    private static void simulateSMOSServerConnection() {
        try {
            // Simulate server connection attempt
            Thread.sleep(1000); // Simulate network delay
            System.out.println("Connected to SMOS server successfully.");
            LOGGER.info("SMOS server connection successful");
        } catch (InterruptedException e) {
            LOGGER.warning("SMOS server connection interrupted: " + e.getMessage());
        }
    }
    
    /**
     * Send delay data to server
     * @param delayedStudents List of student delay records
     * @param date Date of the delay entries
     * @return true if send successful, false otherwise
     */
    private static boolean sendDataToServer(List<StudentDelay> delayedStudents, Date date) {
        System.out.println("\n=== SENDING DATA TO SERVER ===");
        
        if (delayedStudents.isEmpty()) {
            System.out.println("WARNING: No data to send to server.");
            LOGGER.warning("Attempted to send empty data to server");
            return true; // Considered successful for empty data
        }
        
        try {
            // Simulate server connection
            System.out.println("Connecting to server at " + SERVER_HOST + ":" + SERVER_PORT + "...");
            
            // In a real implementation, this would use Socket or HTTP connection
            // For simulation, we'll create a mock server response
            boolean connectionSuccess = simulateServerConnection();
            
            if (!connectionSuccess) {
                System.out.println("ERROR: Failed to connect to server.");
                LOGGER.severe("Server connection failed");
                return false;
            }
            
            // Prepare data for transmission
            String dataPayload = prepareDataPayload(delayedStudents, date);
            
            // Simulate data transmission
            System.out.println("Sending data to server...");
            boolean transmissionSuccess = simulateDataTransmission(dataPayload);
            
            if (!transmissionSuccess) {
                System.out.println("ERROR: Data transmission failed.");
                LOGGER.severe("Data transmission to server failed");
                return false;
            }
            
            System.out.println("Data sent to server successfully.");
            System.out.println("Records sent: " + delayedStudents.size());
            
            LOGGER.info("Data sent to server successfully: " + delayedStudents.size() + " records");
            return true;
            
        } catch (Exception e) {
            System.out.println("ERROR during server communication: " + e.getMessage());
            LOGGER.log(Level.SEVERE, "Server communication error", e);
            return false;
        }
    }
    
    /**
     * Simulate server connection
     * @return true if connection successful, false otherwise
     */
    private static boolean simulateServerConnection() {
        try {
            // Simulate network delay
            Thread.sleep(500);
            
            // 90% chance of successful connection for simulation
            return Math.random() > 0.1;
        } catch (InterruptedException e) {
            LOGGER.warning("Server connection simulation interrupted");
            return false;
        }
    }
    
    /**
     * Prepare data payload for server transmission
     * @param delayedStudents List of student delays
     * @param date Date of delays
     * @return Formatted data payload
     */
    private static String prepareDataPayload(List<StudentDelay> delayedStudents, Date date) {
        StringBuilder payload = new StringBuilder();
        payload.append("DELAY_DATA|").append(DATE_FORMAT.format(date)).append("|");
        payload.append(delayedStudents.size()).append("\n");
        
        for (StudentDelay student : delayedStudents) {
            payload.append(student.toCSV()).append("\n");
        }
        
        LOGGER.fine("Prepared data payload: " + payload.toString().split("\n")[0] + "...");
        return payload.toString();
    }
    
    /**
     * Simulate data transmission to server
     * @param data Data to transmit
     * @return true if transmission successful, false otherwise
     */
    private static boolean simulateDataTransmission(String data) {
        try {
            // Simulate transmission delay
            Thread.sleep(300);
            
            // 95% chance of successful transmission for simulation
            boolean success = Math.random() > 0.05;
            
            if (success) {
                LOGGER.fine("Data transmission simulation successful");
            } else {
                LOGGER.warning("Data transmission simulation failed");
            }
            
            return success;
        } catch (InterruptedException e) {
            LOGGER.warning("Data transmission simulation interrupted");
            return false;
        }
    }
    
    /**
     * Send notifications to parents of delayed students
     * @param delayedStudents List of delayed students
     */
    private static void sendNotificationsToParents(List<StudentDelay> delayedStudents) {
        if (delayedStudents.isEmpty()) {
            return;
        }
        
        System.out.println("\n=== SENDING PARENT NOTIFICATIONS ===");
        
        int notificationsSent = 0;
        int notificationsFailed = 0;
        
        for (StudentDelay student : delayedStudents) {
            if (student.getParentEmail() != null && !student.getParentEmail().isEmpty()) {
                boolean sent = simulateNotificationSend(student);
                
                if (sent) {
                    notificationsSent++;
                    System.out.println("Notification sent to parent of " + student.getStudentName());
                    LOGGER.info("Notification sent for student: " + student.getStudentId());
                } else {
                    notificationsFailed++;
                    System.out.println("WARNING: Failed to send notification to parent of " + student.getStudentName());
                    LOGGER.warning("Failed to send notification for student: " + student.getStudentId());
                }
            } else {
                System.out.println("WARNING: No email for parent of " + student.getStudentName() + ", skipping notification");
                LOGGER.warning("No parent email for student: " + student.getStudentId());
            }
        }
        
        System.out.println("\nNotifications summary:");
        System.out.println("  Sent: " + notificationsSent);
        System.out.println("  Failed: " + notificationsFailed);
        System.out.println("  Skipped (no email): " + (delayedStudents.size() - (notificationsSent + notificationsFailed)));
        
        LOGGER.info("Parent notifications completed: " + notificationsSent + " sent, " + 
                   notificationsFailed + " failed");
    }
    
    /**
     * Simulate sending a notification to a parent
     * @param student Student delay record
     * @return true if notification sent successfully, false otherwise
     */
    private static boolean simulateNotificationSend(StudentDelay student) {
        try {
            // Simulate notification delay
            Thread.sleep(100);
            
            // 85% chance of successful notification for simulation
            return Math.random() > 0.15;
        } catch (InterruptedException e) {
            LOGGER.warning("Notification simulation interrupted for student: " + student.getStudentId());
            return false;
        }
    }
    
    /**
     * Display updated log data
     */
    private static void displayUpdatedLogData() {
        System.out.println("\n=== UPDATED LOG DATA ===");
        System.out.println("Timestamp                | Level   | Message");
        System.out.println("-------------------------|---------|--------------------------------");
        
        // Read and display recent log entries from file
        try {
            File logFile = new File("InsertDelayAdmin.log");
            if (logFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(logFile));
                String line;
                int lineCount = 0;
                
                // Display last 10 log entries
                List<String> lastLines = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    lastLines.add(line);
                    if (lastLines.size() > 10) {
                        lastLines.remove(0);
                    }
                }
                reader.close();
                
                for (String logLine : lastLines) {
                    System.out.println(logLine);
                }
                
                LOGGER.fine("Displayed updated log data");
            } else {
                System