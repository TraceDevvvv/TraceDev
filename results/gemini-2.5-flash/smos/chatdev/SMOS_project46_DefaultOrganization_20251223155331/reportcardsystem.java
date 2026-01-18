/**
 * Manages the data for school classes, students, and report cards.
 * This class acts as a simplified backend/database for the application.
 * It uses a singleton pattern to ensure only one instance of the system exists.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ReportCardSystem {
    // Singleton instance
    private static ReportCardSystem instance;
    // Data storage (simulating a database)
    private Map<String, SchoolClass> classes; // Key: classId
    private Map<String, Student> students;   // Key: studentId
    private Map<String, ReportCard> reportCards; // Key: studentId
    private ReportCardSystem() {
        classes = new HashMap<>();
        students = new HashMap<>();
        reportCards = new HashMap<>();
    }
    /**
     * Returns the singleton instance of ReportCardSystem.
     * @return The single instance of ReportCardSystem.
     */
    public static ReportCardSystem getInstance() {
        if (instance == null) {
            instance = new ReportCardSystem();
        }
        return instance;
    }
    /**
     * Initializes the system with some dummy data for demonstration.
     */
    public void initializeData() {
        // Clear any existing data
        classes.clear();
        students.clear();
        reportCards.clear();
        // Create dummy classes
        SchoolClass classA = new SchoolClass("C001", "10A", "2023-2024");
        SchoolClass classB = new SchoolClass("C002", "10B", "2023-2024");
        SchoolClass classC = new SchoolClass("C003", "11A", "2023-2024");
        classes.put(classA.getId(), classA);
        classes.put(classB.getId(), classB);
        classes.put(classC.getId(), classC);
        // Create dummy students
        Student student1 = new Student("S001", "Alice Smith", classA.getId());
        Student student2 = new Student("S002", "Bob Johnson", classA.getId());
        Student student3 = new Student("S003", "Charlie Brown", classB.getId());
        Student student4 = new Student("S004", "Diana Prince", classB.getId());
        Student student5 = new Student("S005", "Eve Adams", classC.getId());
        students.put(student1.getId(), student1);
        students.put(student2.getId(), student2);
        students.put(student3.getId(), student3);
        students.put(student4.getId(), student4);
        students.put(student5.getId(), student5);
        // Pre-fill some report cards
        // Retrieve academic year for student1's class directly
        SchoolClass student1Class = getSchoolClassById(student1.getClassId());
        if (student1Class != null) {
            ReportCard rc1 = new ReportCard(student1.getId(), student1Class.getAcademicYear());
            rc1.setGrade("Math", 85.0);
            rc1.setGrade("Science", 90.0);
            reportCards.put(student1.getId(), rc1);
        }
    }
    /**
     * Retrieves a list of all classes in the system for the current academic year.
     * Simulates fetching data from a database.
     * @return A list of SchoolClass objects.
     */
    public List<SchoolClass> getClasses() {
        // In a real system, you might filter by academic year.
        // For this simulation, all initialized classes are assumed to be "in progress".
        return new ArrayList<>(classes.values());
    }
    /**
     * Retrieves a list of students belonging to a specific class.
     * @param classId The ID of the class.
     * @return A list of Student objects in the specified class.
     */
    public List<Student> getStudentsInClass(String classId) {
        List<Student> classStudents = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getClassId().equals(classId)) {
                classStudents.add(student);
            }
        }
        return classStudents;
    }
    /**
     * Retrieves a student by their ID.
     * @param studentId The ID of the student.
     * @return The Student object if found, otherwise null.
     */
    public Student getStudentById(String studentId) {
        return students.get(studentId);
    }
    /**
     * Retrieves a class by its ID.
     * @param classId The ID of the class.
     * @return The SchoolClass object if found, otherwise null.
     */
    public SchoolClass getSchoolClassById(String classId) {
        return classes.get(classId);
    }
    /**
     * Retrieves the report card for a specific student.
     * If no report card exists, a new one is created, deriving the academic year from the student's class.
     * @param studentId The ID of the student.
     * @return The ReportCard object for the student, or null if student or their class is not found.
     */
    public ReportCard getReportCard(String studentId) {
        // If report card already exists, return it
        if (reportCards.containsKey(studentId)) {
            return reportCards.get(studentId);
        }
        // Step 1: Ensure the student exists
        Student student = students.get(studentId);
        if (student == null) {
            System.err.println("Error: Student with ID " + studentId + " not found. Cannot create report card.");
            return null;
        }
        // Step 2: Get the class associated with the student
        String classId = student.getClassId();
        SchoolClass schoolClass = getSchoolClassById(classId);
        if (schoolClass == null) {
            System.err.println("Error: Class '" + classId + "' for student " + studentId + " not found. Cannot create report card.");
            return null;
        }
        // Step 3: Use the academic year from the class to create a new report card
        String academicYear = schoolClass.getAcademicYear();
        ReportCard newReportCard = new ReportCard(studentId, academicYear);
        reportCards.put(studentId, newReportCard);
        return newReportCard;
    }
    /**
     * Saves the report card for a student.
     * This method either updates an existing report card or creates a new one.
     * @param reportCard The ReportCard object to save.
     */
    public void saveReportCard(ReportCard reportCard) {
        if (reportCard != null && students.containsKey(reportCard.getStudentId())) {
            reportCards.put(reportCard.getStudentId(), reportCard);
            System.out.println("Report card for student " + reportCard.getStudentId() + " saved successfully.");
        } else {
            System.err.println("Failed to save report card. Student not found or report card is null.");
        }
    }
}