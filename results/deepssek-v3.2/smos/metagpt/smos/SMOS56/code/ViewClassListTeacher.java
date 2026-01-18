import java.util.*;
import java.time.Year;
import java.util.concurrent.*;

/**
 * Main program implementing the ViewClassListTeacher use case.
 * This program allows a teacher to view the list of classes they teach
 * for a selected academic year.
 */
public class ViewClassListTeacher {
    
    // Simulated database of teachers and their classes
    private static Map<String, Teacher> teacherDatabase = new HashMap<>();
    private static Map<String, List<SchoolClass>> classesByYear = new HashMap<>();
    
    /**
     * Represents a teacher user in the system.
     */
    static class Teacher {
        String id;
        String name;
        Set<String> academicYears; // Years the teacher has taught in
        
        public Teacher(String id, String name) {
            this.id = id;
            this.name = name;
            this.academicYears = new HashSet<>();
        }
        
        public void addTeachingYear(String academicYear) {
            academicYears.add(academicYear);
        }
        
        public Set<String> getAcademicYears() {
            return new HashSet<>(academicYears);
        }
    }
    
    /**
     * Represents a school class taught by a teacher.
     */
    static class SchoolClass {
        String classId;
        String className;
        String academicYear;
        String teacherId;
        
        public SchoolClass(String classId, String className, String academicYear, String teacherId) {
            this.classId = classId;
            this.className = className;
            this.academicYear = academicYear;
            this.teacherId = teacherId;
        }
        
        @Override
        public String toString() {
            return "Class ID: " + classId + ", Name: " + className + ", Year: " + academicYear;
        }
    }
    
    /**
     * Simulates connection to SMOS server.
     * Handles connection interruption and reconnection logic.
     */
    static class SMOSServerConnection {
        private boolean connected = false;
        private static final int MAX_RETRY_ATTEMPTS = 3;
        private static final int RETRY_DELAY_MS = 1000;
        
        /**
         * Connect to SMOS server with retry logic.
         * @return true if connection successful, false otherwise
         */
        public boolean connect() {
            System.out.println("Attempting to connect to SMOS server...");
            
            for (int attempt = 1; attempt <= MAX_RETRY_ATTEMPTS; attempt++) {
                try {
                    // Simulate connection attempt
                    Thread.sleep(500); // Simulate network delay
                    
                    // Simulate connection success (80% success rate for simulation)
                    boolean success = Math.random() > 0.2;
                    
                    if (success) {
                        connected = true;
                        System.out.println("Successfully connected to SMOS server on attempt " + attempt);
                        return true;
                    } else {
                        System.out.println("Connection attempt " + attempt + " failed");
                        if (attempt < MAX_RETRY_ATTEMPTS) {
                            System.out.println("Retrying in " + RETRY_DELAY_MS + "ms...");
                            Thread.sleep(RETRY_DELAY_MS);
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("Connection interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt();
                    return false;
                }
            }
            
            System.out.println("Failed to connect to SMOS server after " + MAX_RETRY_ATTEMPTS + " attempts");
            return false;
        }
        
        /**
         * Disconnect from SMOS server.
         */
        public void disconnect() {
            if (connected) {
                System.out.println("Disconnecting from SMOS server...");
                connected = false;
            }
        }
        
        public boolean isConnected() {
            return connected;
        }
    }
    
    /**
     * Initialize sample data for the system.
     */
    private static void initializeSampleData() {
        // Create sample teachers
        Teacher teacher1 = new Teacher("T001", "Dr. Smith");
        Teacher teacher2 = new Teacher("T002", "Prof. Johnson");
        
        teacherDatabase.put("T001", teacher1);
        teacherDatabase.put("T002", teacher2);
        
        // Create sample classes
        List<SchoolClass> classes2023 = Arrays.asList(
            new SchoolClass("C101", "Mathematics 101", "2023-2024", "T001"),
            new SchoolClass("C102", "Physics 101", "2023-2024", "T001"),
            new SchoolClass("C103", "Chemistry 101", "2023-2024", "T002")
        );
        
        List<SchoolClass> classes2022 = Arrays.asList(
            new SchoolClass("C201", "Mathematics 101", "2022-2023", "T001"),
            new SchoolClass("C202", "Physics 101", "2022-2023", "T002")
        );
        
        List<SchoolClass> classes2021 = Arrays.asList(
            new SchoolClass("C301", "Biology 101", "2021-2022", "T001")
        );
        
        classesByYear.put("2023-2024", classes2023);
        classesByYear.put("2022-2023", classes2022);
        classesByYear.put("2021-2022", classes2021);
        
        // Update teacher academic years
        teacher1.addTeachingYear("2023-2024");
        teacher1.addTeachingYear("2022-2023");
        teacher1.addTeachingYear("2021-2022");
        teacher2.addTeachingYear("2023-2024");
        teacher2.addTeachingYear("2022-2023");
    }
    
    /**
     * Get academic years for a specific teacher.
     * @param teacherId The teacher's ID
     * @return List of academic years the teacher has taught in
     */
    private static Set<String> getAcademicYearsForTeacher(String teacherId) {
        Teacher teacher = teacherDatabase.get(teacherId);
        if (teacher == null) {
            return new HashSet<>();
        }
        return teacher.getAcademicYears();
    }
    
    /**
     * Get classes for a teacher in a specific academic year.
     * @param teacherId The teacher's ID
     * @param academicYear The academic year to filter by
     * @return List of classes taught by the teacher in that year
     */
    private static List<SchoolClass> getClassesForTeacher(String teacherId, String academicYear) {
        List<SchoolClass> allClasses = classesByYear.get(academicYear);
        if (allClasses == null) {
            return new ArrayList<>();
        }
        
        List<SchoolClass> teacherClasses = new ArrayList<>();
        for (SchoolClass schoolClass : allClasses) {
            if (schoolClass.teacherId.equals(teacherId)) {
                teacherClasses.add(schoolClass);
            }
        }
        return teacherClasses;
    }
    
    /**
     * Display academic years menu and get user selection.
     * @param scanner Scanner for user input
     * @param academicYears Set of available academic years
     * @return Selected academic year or null if invalid
     */
    private static String selectAcademicYear(Scanner scanner, Set<String> academicYears) {
        System.out.println("\n=== Available Academic Years ===");
        
        // Convert to sorted list for consistent display
        List<String> sortedYears = new ArrayList<>(academicYears);
        Collections.sort(sortedYears, Collections.reverseOrder()); // Show most recent first
        
        if (sortedYears.isEmpty()) {
            System.out.println("No academic years found for this teacher.");
            return null;
        }
        
        // Display years with numbers
        for (int i = 0; i < sortedYears.size(); i++) {
            System.out.println((i + 1) + ". " + sortedYears.get(i));
        }
        
        System.out.print("\nSelect academic year (1-" + sortedYears.size() + "): ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= sortedYears.size()) {
                return sortedYears.get(choice - 1);
            } else {
                System.out.println("Invalid selection. Please choose a number between 1 and " + sortedYears.size());
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return null;
        }
    }
    
    /**
     * Simulate teacher login process.
     * @param scanner Scanner for user input
     * @return Teacher ID if login successful, null otherwise
     */
    private static String teacherLogin(Scanner scanner) {
        System.out.println("=== Teacher Login ===");
        System.out.print("Enter Teacher ID: ");
        String teacherId = scanner.nextLine().trim();
        
        if (teacherDatabase.containsKey(teacherId)) {
            System.out.println("Login successful! Welcome " + teacherDatabase.get(teacherId).name);
            return teacherId;
        } else {
            System.out.println("Invalid Teacher ID. Please try again.");
            return null;
        }
    }
    
    /**
     * Main program flow implementing the use case.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SMOSServerConnection smosConnection = new SMOSServerConnection();
        
        // Initialize sample data
        initializeSampleData();
        
        try {
            System.out.println("=== Digital Log System ===");
            
            // Precondition: Teacher must be logged in
            String teacherId = null;
            while (teacherId == null) {
                teacherId = teacherLogin(scanner);
            }
            
            // User clicks "Digital Log" button (simulated)
            System.out.println("\nClicking 'Digital Log' button...");
            
            // Step 1: System displays academic years
            Set<String> academicYears = getAcademicYearsForTeacher(teacherId);
            
            if (academicYears.isEmpty()) {
                System.out.println("No teaching assignments found for this teacher.");
                return;
            }
            
            // Step 2: User selects academic year
            String selectedYear = null;
            while (selectedYear == null) {
                selectedYear = selectAcademicYear(scanner, academicYears);
            }
            
            // Step 3: View classes for selected year
            System.out.println("\n=== Classes for Academic Year: " + selectedYear + " ===");
            List<SchoolClass> teacherClasses = getClassesForTeacher(teacherId, selectedYear);
            
            if (teacherClasses.isEmpty()) {
                System.out.println("No classes found for the selected academic year.");
            } else {
                System.out.println("Total classes: " + teacherClasses.size());
                System.out.println("----------------------------");
                for (SchoolClass schoolClass : teacherClasses) {
                    System.out.println(schoolClass);
                }
            }
            
            // Postcondition: Connect to SMOS server
            System.out.println("\n=== Postcondition: SMOS Server Connection ===");
            boolean connected = smosConnection.connect();
            
            if (connected) {
                System.out.println("System ready for further operations.");
            } else {
                System.out.println("Warning: SMOS server connection failed. Some features may be limited.");
            }
            
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up resources
            if (smosConnection.isConnected()) {
                smosConnection.disconnect();
            }
            scanner.close();
            System.out.println("\n=== Program terminated ===");
        }
    }
}