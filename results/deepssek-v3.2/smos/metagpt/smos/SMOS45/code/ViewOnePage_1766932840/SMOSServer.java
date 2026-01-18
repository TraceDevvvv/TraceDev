import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SMOSServer.java
 * Class simulating SMOS server connection and data retrieval.
 * This class manages a simulated database of academic years, classes, students, and report cards.
 * It provides methods to connect/disconnect and retrieve data for the report card system.
 */
public class SMOSServer {
    private boolean connected = false;
    
    // Simulated data storage
    private List<AcademicYear> academicYears;
    private List<Class> classes;
    private List<Student> students;
    private Map<String, ReportCard> reportCards;
    
    /**
     * Constructor for SMOSServer. Initializes the simulated data.
     */
    public SMOSServer() {
        initializeData();
    }
    
    /**
     * Initializes simulated data including academic years, classes, students, and report cards.
     */
    private void initializeData() {
        // Initialize academic years
        academicYears = new ArrayList<>();
        academicYears.add(new AcademicYear("AY1", "2023", "2024"));
        academicYears.add(new AcademicYear("AY2", "2022", "2023"));
        academicYears.add(new AcademicYear("AY3", "2021", "2022"));
        
        // Initialize classes
        classes = new ArrayList<>();
        classes.add(new Class("C1", "Class 10A", 10, "Mr. Smith"));
        classes.add(new Class("C2", "Class 10B", 10, "Ms. Johnson"));
        classes.add(new Class("C3", "Class 11A", 11, "Dr. Williams"));
        classes.add(new Class("C4", "Class 11B", 11, "Mrs. Brown"));
        classes.add(new Class("C5", "Class 12A", 12, "Mr. Davis"));
        
        // Initialize students
        students = new ArrayList<>();
        students.add(new Student("S101", "John", "Doe"));
        students.add(new Student("S102", "Jane", "Smith"));
        students.add(new Student("S103", "Robert", "Johnson"));
        students.add(new Student("S104", "Emily", "Williams"));
        students.add(new Student("S105", "Michael", "Brown"));
        students.add(new Student("S106", "Sarah", "Davis"));
        students.add(new Student("S107", "David", "Miller"));
        students.add(new Student("S108", "Lisa", "Wilson"));
        
        // Initialize report cards
        reportCards = new HashMap<>();
        
        // Sample report card data for John Doe
        Map<String, String> grades1 = new HashMap<>();
        grades1.put("Mathematics", "A");
        grades1.put("Science", "B+");
        grades1.put("English", "A-");
        grades1.put("History", "B");
        grades1.put("Physical Education", "A");
        
        List<String> months1 = new ArrayList<>();
        months1.add("September");
        months1.add("October");
        months1.add("November");
        months1.add("December");
        
        ReportCard report1 = new ReportCard(
            "John Doe", "Class 10A", "2023-2024",
            months1, grades1,
            85, 5, "John has shown excellent progress in Mathematics and Physical Education. " +
                  "He participates actively in class discussions and completes assignments on time. " +
                  "Could improve focus in Science classes.",
            "A-", "Excellent"
        );
        
        reportCards.put("S101_2023-2024_Sep-Dec", report1);
        
        // Additional sample report card for Jane Smith
        Map<String, String> grades2 = new HashMap<>();
        grades2.put("Mathematics", "B+");
        grades2.put("Science", "A");
        grades2.put("English", "A");
        grades2.put("History", "A-");
        grades2.put("Physical Education", "B");
        
        List<String> months2 = new ArrayList<>();
        months2.add("January");
        months2.add("February");
        months2.add("March");
        months2.add("April");
        
        ReportCard report2 = new ReportCard(
            "Jane Smith", "Class 10B", "2023-2024",
            months2, grades2,
            90, 2, "Jane is a diligent student with strong performance in Science and English. " +
                  "She shows great analytical skills and works well in group projects.",
            "A", "Outstanding"
        );
        
        reportCards.put("S102_2023-2024_Jan-Apr", report2);
    }
    
    /**
     * Connects to the SMOS server.
     */
    public void connect() {
        connected = true;
        System.out.println("SMOS Server: Connection established.");
    }
    
    /**
     * Disconnects from the SMOS server.
     */
    public void disconnect() {
        connected = false;
        System.out.println("SMOS Server: Connection terminated.");
    }
    
    /**
     * Checks if the server is connected.
     * 
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return connected;
    }
    
    /**
     * Retrieves all available academic years from the server.
     * 
     * @return List of AcademicYear objects
     */
    public List<AcademicYear> getAvailableAcademicYears() {
        if (!connected) {
            System.out.println("SMOS Server: Not connected. Cannot retrieve academic years.");
            return new ArrayList<>();
        }
        return new ArrayList<>(academicYears);
    }
    
    /**
     * Retrieves classes for a given academic year.
     * 
     * @param academicYear The academic year to filter classes
     * @return List of Class objects for the given academic year
     */
    public List<Class> getClassesForAcademicYear(AcademicYear academicYear) {
        if (!connected) {
            System.out.println("SMOS Server: Not connected. Cannot retrieve classes.");
            return new ArrayList<>();
        }
        // In this simulation, all classes are available for all academic years.
        // In a real system, we would filter by academic year.
        return new ArrayList<>(classes);
    }
    
    /**
     * Retrieves students for a given class.
     * 
     * @param cls The class to filter students
     * @return List of Student objects in the given class
     */
    public List<Student> getStudentsForClass(Class cls) {
        if (!connected) {
            System.out.println("SMOS Server: Not connected. Cannot retrieve students.");
            return new ArrayList<>();
        }
        // In this simulation, we return all students regardless of class.
        // In a real system, we would filter by class.
        return new ArrayList<>(students);
    }
    
    /**
     * Retrieves available months for report card generation based on academic year and class.
     * 
     * @param academicYear The academic year
     * @param cls The class
     * @return List of available months as strings
     */
    public List<String> getAvailableMonths(AcademicYear academicYear, Class cls) {
        if (!connected) {
            System.out.println("SMOS Server: Not connected. Cannot retrieve available months.");
            return new ArrayList<>();
        }
        // Simulated available months
        List<String> months = new ArrayList<>();
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        return months;
    }
    
    /**
     * Retrieves a report card for a specific student, academic year, and months.
     * 
     * @param student The student
     * @param academicYear The academic year
     * @param months The list of months for the report
     * @return ReportCard object if found, null otherwise
     */
    public ReportCard getReportCard(Student student, AcademicYear academicYear, List<String> months) {
        if (!connected) {
            System.out.println("SMOS Server: Not connected. Cannot retrieve report card.");
            return null;
        }
        // Generate a key based on student ID, academic year, and months
        String key = student.getStudentId() + "_" + academicYear.getYearRange() + "_" +
                     String.join("-", months.subList(0, Math.min(4, months.size())));
        
        // Return the report card if it exists
        return reportCards.get(key);
    }
    
    /**
     * Adds a new report card to the simulated database (for testing/extensibility).
     * 
     * @param key The unique key for the report card
     * @param reportCard The ReportCard object to add
     */
    public void addReportCard(String key, ReportCard reportCard) {
        reportCards.put(key, reportCard);
    }
    
    /**
     * Returns a string representation of the SMOS server status.
     * 
     * @return String indicating connection status
     */
    @Override
    public String toString() {
        return "SMOSServer [connected=" + connected + 
               ", academicYears=" + academicYears.size() +
               ", classes=" + classes.size() +
               ", students=" + students.size() +
               ", reportCards=" + reportCards.size() + "]";
    }
}