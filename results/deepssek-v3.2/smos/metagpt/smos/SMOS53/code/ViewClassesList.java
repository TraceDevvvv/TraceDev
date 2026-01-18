import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * ViewClassesList - A Java program for ATA staff to view classes list
 * 
 * This program simulates a system where staff can view all classes in the database.
 * It includes authentication (simulated), class data model, and console interface.
 */
public class ViewClassesList {
    
    /**
     * Represents a Class/Course in the system
     */
    static class Course {
        private String courseId;
        private String courseName;
        private String instructor;
        private String schedule;
        private int credits;
        
        public Course(String courseId, String courseName, String instructor, String schedule, int credits) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.instructor = instructor;
            this.schedule = schedule;
            this.credits = credits;
        }
        
        public String getCourseId() { return courseId; }
        public String getCourseName() { return courseName; }
        public String getInstructor() { return instructor; }
        public String getSchedule() { return schedule; }
        public int getCredits() { return credits; }
        
        @Override
        public String toString() {
            return String.format("ID: %-10s Name: %-25s Instructor: %-15s Schedule: %-15s Credits: %d",
                    courseId, courseName, instructor, schedule, credits);
        }
    }
    
    /**
     * Simulates database operations for courses
     */
    static class DatabaseSimulator {
        private Map<String, Course> courses;
        
        public DatabaseSimulator() {
            courses = new HashMap<>();
            initializeSampleData();
        }
        
        /**
         * Initialize the database with sample data
         */
        private void initializeSampleData() {
            // Add sample courses
            addCourse(new Course("CS101", "Introduction to Computer Science", "Dr. Smith", "MWF 9:00-10:00", 3));
            addCourse(new Course("CS201", "Data Structures", "Prof. Johnson", "TTH 10:30-12:00", 4));
            addCourse(new Course("MATH101", "Calculus I", "Dr. Williams", "MWF 11:00-12:00", 4));
            addCourse(new Course("PHYS101", "Physics I", "Prof. Brown", "TTH 1:00-2:30", 4));
            addCourse(new Course("ENG101", "English Composition", "Dr. Davis", "MWF 2:00-3:00", 3));
            addCourse(new Course("CS301", "Algorithms", "Prof. Miller", "MW 3:00-4:30", 4));
            addCourse(new Course("CS401", "Database Systems", "Dr. Wilson", "TTH 2:00-3:30", 4));
            addCourse(new Course("CS501", "Software Engineering", "Prof. Moore", "F 1:00-4:00", 3));
        }
        
        public void addCourse(Course course) {
            courses.put(course.getCourseId(), course);
        }
        
        /**
         * Retrieves all courses from the database
         * @return List of all courses
         */
        public List<Course> getAllCourses() {
            return new ArrayList<>(courses.values());
        }
        
        /**
         * Retrieves a specific course by ID
         * @param courseId The course ID to look up
         * @return The Course object or null if not found
         */
        public Course getCourseById(String courseId) {
            return courses.get(courseId);
        }
        
        /**
         * Checks if a course exists in the database
         * @param courseId The course ID to check
         * @return true if course exists, false otherwise
         */
        public boolean courseExists(String courseId) {
            return courses.containsKey(courseId);
        }
    }
    
    /**
     * Authentication service to verify staff login
     */
    static class AuthenticationService {
        // Simulated staff credentials (in real app, this would be in a secure database)
        private static final Map<String, String> STAFF_CREDENTIALS = new HashMap<>();
        
        static {
            // Initialize with sample staff credentials
            STAFF_CREDENTIALS.put("staff01", "password123");
            STAFF_CREDENTIALS.put("admin", "admin123");
            STAFF_CREDENTIALS.put("ata_staff", "ata2024");
        }
        
        /**
         * Authenticates a staff user
         * @param username Staff username
         * @param password Staff password
         * @return true if authentication successful, false otherwise
         */
        public boolean authenticateStaff(String username, String password) {
            String storedPassword = STAFF_CREDENTIALS.get(username);
            return storedPassword != null && storedPassword.equals(password);
        }
    }
    
    /**
     * Main application class to handle the console interface
     */
    static class ClassManagerApp {
        private DatabaseSimulator db;
        private AuthenticationService authService;
        private Scanner scanner;
        private boolean isAuthenticated;
        
        public ClassManagerApp() {
            db = new DatabaseSimulator();
            authService = new AuthenticationService();
            scanner = new Scanner(System.in);
            isAuthenticated = false;
        }
        
        /**
         * Main application entry point
         */
        public void run() {
            System.out.println("=== ATA Class Management System ===");
            System.out.println();
            
            // First, authenticate the user (precondition: must be logged in as staff)
            authenticateUser();
            
            if (isAuthenticated) {
                displayClassesList();
                handleUserSelection();
            } else {
                System.out.println("Authentication failed. Exiting system.");
            }
            
            scanner.close();
        }
        
        /**
         * Authenticates the user (simulating staff login)
         */
        private void authenticateUser() {
            System.out.println("STAFF LOGIN");
            System.out.println("-----------");
            
            int attempts = 0;
            final int MAX_ATTEMPTS = 3;
            
            while (attempts < MAX_ATTEMPTS && !isAuthenticated) {
                System.out.print("Username: ");
                String username = scanner.nextLine().trim();
                
                System.out.print("Password: ");
                String password = scanner.nextLine().trim();
                
                if (authService.authenticateStaff(username, password)) {
                    isAuthenticated = true;
                    System.out.println("Login successful! Welcome, " + username + ".");
                    System.out.println();
                } else {
                    attempts++;
                    System.out.println("Invalid credentials. Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                    if (attempts < MAX_ATTEMPTS) {
                        System.out.println("Please try again.");
                    }
                }
            }
        }
        
        /**
         * Displays all classes from the database (main functionality)
         */
        private void displayClassesList() {
            System.out.println("=== CLASSES LIST ===");
            System.out.println("All available classes in the database:");
            System.out.println();
            
            List<Course> courses = db.getAllCourses();
            
            if (courses.isEmpty()) {
                System.out.println("No classes found in the database.");
                return;
            }
            
            // Display header
            System.out.println("====================================================================================================");
            System.out.println("ID          Name                        Instructor       Schedule         Credits   Registry Key");
            System.out.println("====================================================================================================");
            
            // Display each course with registry key
            int index = 1;
            for (Course course : courses) {
                String registryKey = String.valueOf(index);  // Simple key based on display order
                System.out.printf("%-10s  %-25s  %-15s  %-15s  %-7d  [KEY: %s]%n",
                        course.getCourseId(),
                        course.getCourseName(),
                        course.getInstructor(),
                        course.getSchedule(),
                        course.getCredits(),
                        registryKey);
                index++;
            }
            
            System.out.println("====================================================================================================");
            System.out.println();
            System.out.println("Enter the registry key [KEY: #] to view class details, or '0' to exit.");
        }
        
        /**
         * Handles user selection after displaying classes list
         */
        private void handleUserSelection() {
            List<Course> courses = db.getAllCourses();
            
            while (true) {
                System.out.print("Enter registry key (or '0' to exit): ");
                String input = scanner.nextLine().trim();
                
                if (input.equals("0")) {
                    System.out.println("Exiting system. Goodbye!");
                    break;
                }
                
                try {
                    int key = Integer.parseInt(input);
                    
                    if (key >= 1 && key <= courses.size()) {
                        // Display registry/details for selected class
                        Course selectedCourse = courses.get(key - 1);
                        displayClassRegistry(selectedCourse);
                    } else {
                        System.out.println("Invalid key. Please enter a valid registry key (1-" + courses.size() + ") or '0' to exit.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric key (1-" + courses.size() + ") or '0' to exit.");
                }
                
                // Ask if user wants to see the list again
                System.out.print("View classes list again? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("yes") || response.equals("y")) {
                    displayClassesList();
                } else {
                    System.out.println("Thank you for using the ATA Class Management System!");
                    break;
                }
            }
        }
        
        /**
         * Displays detailed registry information for a specific class
         * @param course The course to display registry for
         */
        private void displayClassRegistry(Course course) {
            System.out.println();
            System.out.println("=== CLASS REGISTRY ===");
            System.out.println("Class ID: " + course.getCourseId());
            System.out.println("Class Name: " + course.getCourseName());
            System.out.println("Instructor: " + course.getInstructor());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Credits: " + course.getCredits());
            System.out.println();
            
            // Simulated registry data (in real app, this would come from database)
            System.out.println("Registry Information:");
            System.out.println("---------------------");
            System.out.println("Enrolled Students: 25");
            System.out.println("Available Seats: 5");
            System.out.println("Room: SCI-201");
            System.out.println("Semester: Fall 2024");
            System.out.println("Status: Active");
            System.out.println();
        }
    }
    
    /**
     * Main method - Entry point of the program
     */
    public static void main(String[] args) {
        try {
            ClassManagerApp app = new ClassManagerApp();
            app.run();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            System.out.println("Please contact system administrator.");
        }
    }
}