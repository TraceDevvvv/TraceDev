import java.util.*;
import java.time.*;
import java.time.format.*;

/**
 * ViewTeachingDetails - Main program for viewing teaching details.
 * Simulates a simple teaching management system with an administrative UI.
 * Handles preconditions (logged-in user viewing teaching list) and postconditions
 * (displaying detailed info, connecting to SMOS server).
 */
public class ViewTeachingDetails {
    // Simulated database of teachings
    private static Map<Integer, Teaching> teachingDatabase;

    // Flag to simulate user logged-in state
    private static boolean isLoggedIn = false;

    // Flag to simulate viewing list state
    private static boolean isViewingList = false;

    // Simulated SMOS server connection status
    private static boolean smosServerConnected = false;

    /**
     * Main entry point - simulates the flow:
     * 1. Administrator logs in
     * 2. Views teaching list
     * 3. Selects a teaching to view details
     * 4. Displays detailed information
     */
    public static void main(String[] args) {
        System.out.println("=== Teaching Management System ===");
        
        // Initialize with sample data
        initializeDatabase();
        
        // Simulate login (precondition)
        loginAsAdministrator();
        
        // Simulate viewing teaching list (precondition)
        viewTeachingList();
        
        // Get teaching ID from user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the Teaching ID to view details (or 0 to exit): ");
        
        try {
            int selectedId = scanner.nextInt();
            
            if (selectedId == 0) {
                System.out.println("Exiting system...");
                return;
            }
            
            // Display teaching details (main use case)
            displayTeachingDetails(selectedId);
            
            // Connect to SMOS server (postcondition)
            connectToSMOSServer();
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter a numeric ID.");
        } finally {
            scanner.close();
        }
    }

    /**
     * Initialize the teaching database with sample data.
     * In a real system, this would connect to an actual database.
     */
    private static void initializeDatabase() {
        teachingDatabase = new HashMap<>();
        
        // Create sample teachings
        Teaching t1 = new Teaching(101, "CS101", "Introduction to Programming", 
                                  "Prof. John Smith", "Dr. Alice Johnson", 
                                  LocalDate.of(2024, 9, 1), LocalDate.of(2024, 12, 15),
                                  "MON-WED-FRI 10:00-11:00", "Room 301", 45);
        
        Teaching t2 = new Teaching(102, "MATH201", "Calculus II", 
                                  "Dr. Robert Brown", "Dr. Sarah Wilson",
                                  LocalDate.of(2024, 9, 2), LocalDate.of(2024, 12, 20),
                                  "TUE-THU 13:00-14:30", "Room 205", 60);
        
        Teaching t3 = new Teaching(103, "PHYS101", "Physics Fundamentals", 
                                  "Prof. Maria Garcia", "Dr. James Lee",
                                  LocalDate.of(2024, 9, 3), LocalDate.of(2024, 12, 18),
                                  "MON-WED 14:00-15:30", "Lab Building A", 30);
        
        // Add a teaching with minimal data for edge case testing
        Teaching t4 = new Teaching(104, "CHEM101", null,  // null for missing course name
                                  "Prof. David Kim", null, // null for missing assistant
                                  null, null, // missing dates
                                  "TBD", "Lab 3", 25);
        
        teachingDatabase.put(t1.getId(), t1);
        teachingDatabase.put(t2.getId(), t2);
        teachingDatabase.put(t3.getId(), t3);
        teachingDatabase.put(t4.getId(), t4);
    }

    /**
     * Simulates administrator login.
     * Sets the logged-in flag (precondition).
     */
    private static void loginAsAdministrator() {
        System.out.println("\n--- Administrator Login ---");
        // In real system, would verify credentials
        isLoggedIn = true;
        System.out.println("Administrator logged in successfully.");
    }

    /**
     * Simulates viewing the teaching list (precondition).
     * Displays available teachings.
     */
    private static void viewTeachingList() {
        if (!isLoggedIn) {
            System.out.println("Error: User must be logged in to view teaching list.");
            return;
        }
        
        System.out.println("\n--- Teaching List ---");
        System.out.println("ID\tCourse Code\tCourse Name");
        System.out.println("---------------------------------");
        
        for (Teaching teaching : teachingDatabase.values()) {
            String courseName = teaching.getCourseName();
            if (courseName == null || courseName.isEmpty()) {
                courseName = "[Name Not Available]";
            }
            System.out.printf("%d\t%s\t\t%s%n", 
                teaching.getId(), 
                teaching.getCourseCode(),
                courseName);
        }
        
        isViewingList = true;
        System.out.println("\nClick 'Teaching details' button for any teaching to view details.");
    }

    /**
     * Displays detailed information for a specific teaching.
     * Handles edge cases like missing data.
     * 
     * @param teachingId The ID of the teaching to display
     */
    private static void displayTeachingDetails(int teachingId) {
        // Check preconditions
        if (!isLoggedIn) {
            System.out.println("Error: User must be logged in.");
            return;
        }
        
        if (!isViewingList) {
            System.out.println("Error: Must be viewing teaching list first.");
            return;
        }
        
        // Retrieve teaching from database
        Teaching teaching = teachingDatabase.get(teachingId);
        
        if (teaching == null) {
            System.out.println("Error: Teaching with ID " + teachingId + " not found.");
            return;
        }
        
        System.out.println("\n=== Teaching Details ===");
        System.out.println("==========================================");
        
        // Display all details with null-safe formatting
        System.out.println("ID: " + teaching.getId());
        System.out.println("Course Code: " + teaching.getCourseCode());
        
        // Handle missing course name
        String courseName = teaching.getCourseName();
        if (courseName == null || courseName.isEmpty()) {
            System.out.println("Course Name: [Not Available]");
        } else {
            System.out.println("Course Name: " + courseName);
        }
        
        // Handle missing instructor
        String instructor = teaching.getInstructor();
        if (instructor == null || instructor.isEmpty()) {
            System.out.println("Instructor: [To be assigned]");
        } else {
            System.out.println("Instructor: " + instructor);
        }
        
        // Handle missing teaching assistant
        String teachingAssistant = teaching.getTeachingAssistant();
        if (teachingAssistant == null || teachingAssistant.isEmpty()) {
            System.out.println("Teaching Assistant: [Not assigned]");
        } else {
            System.out.println("Teaching Assistant: " + teachingAssistant);
        }
        
        // Handle missing dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        LocalDate startDate = teaching.getStartDate();
        if (startDate == null) {
            System.out.println("Start Date: [To be scheduled]");
        } else {
            System.out.println("Start Date: " + startDate.format(formatter));
        }
        
        LocalDate endDate = teaching.getEndDate();
        if (endDate == null) {
            System.out.println("End Date: [To be scheduled]");
        } else {
            System.out.println("End Date: " + endDate.format(formatter));
        }
        
        System.out.println("Schedule: " + teaching.getSchedule());
        System.out.println("Location: " + teaching.getLocation());
        System.out.println("Max Students: " + teaching.getMaxStudents());
        
        // Calculate and display additional info
        System.out.println("\n--- Additional Information ---");
        
        // Calculate duration if both dates are available
        if (startDate != null && endDate != null) {
            long weeks = java.time.temporal.ChronoUnit.WEEKS.between(startDate, endDate);
            System.out.println("Duration: " + weeks + " weeks");
        }
        
        // Check if course is ongoing
        if (startDate != null && endDate != null) {
            LocalDate today = LocalDate.now();
            if (!today.isBefore(startDate) && !today.isAfter(endDate)) {
                System.out.println("Status: Currently ongoing");
            } else if (today.isBefore(startDate)) {
                System.out.println("Status: Upcoming");
            } else {
                System.out.println("Status: Completed");
            }
        }
        
        System.out.println("==========================================");
    }

    /**
     * Simulates connecting to the SMOS server (postcondition).
     * Handles connection interruption scenarios.
     */
    private static void connectToSMOSServer() {
        System.out.println("\n--- SMOS Server Connection ---");
        
        // Simulate connection attempt with possible interruption
        Random random = new Random();
        boolean connectionSuccessful = random.nextBoolean();
        
        if (connectionSuccessful) {
            smosServerConnected = true;
            System.out.println("Successfully connected to SMOS server.");
            System.out.println("Teaching details synchronized with SMOS system.");
        } else {
            smosServerConnected = false;
            System.out.println("Warning: Connection to SMOS server interrupted.");
            System.out.println("Teaching details saved locally. Will retry synchronization later.");
        }
    }

    /**
     * Teaching class representing a single teaching record.
     * Contains all details about a teaching.
     */
    static class Teaching {
        private int id;
        private String courseCode;
        private String courseName;
        private String instructor;
        private String teachingAssistant;
        private LocalDate startDate;
        private LocalDate endDate;
        private String schedule;
        private String location;
        private int maxStudents;
        
        // Constructor with all fields
        public Teaching(int id, String courseCode, String courseName, 
                       String instructor, String teachingAssistant,
                       LocalDate startDate, LocalDate endDate,
                       String schedule, String location, int maxStudents) {
            this.id = id;
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.instructor = instructor;
            this.teachingAssistant = teachingAssistant;
            this.startDate = startDate;
            this.endDate = endDate;
            this.schedule = schedule;
            this.location = location;
            this.maxStudents = maxStudents;
        }
        
        // Getters with null-safety for optional fields
        public int getId() { return id; }
        
        public String getCourseCode() { 
            return (courseCode != null) ? courseCode : "N/A"; 
        }
        
        public String getCourseName() { 
            return courseName; // Can be null
        }
        
        public String getInstructor() { 
            return instructor; // Can be null
        }
        
        public String getTeachingAssistant() { 
            return teachingAssistant; // Can be null
        }
        
        public LocalDate getStartDate() { 
            return startDate; // Can be null
        }
        
        public LocalDate getEndDate() { 
            return endDate; // Can be null
        }
        
        public String getSchedule() { 
            return (schedule != null) ? schedule : "Schedule not set"; 
        }
        
        public String getLocation() { 
            return (location != null) ? location : "Location not assigned"; 
        }
        
        public int getMaxStudents() { 
            return maxStudents; 
        }
        
        @Override
        public String toString() {
            return String.format("Teaching[ID=%d, Code=%s, Name=%s]", 
                id, courseCode, courseName);
        }
    }
}