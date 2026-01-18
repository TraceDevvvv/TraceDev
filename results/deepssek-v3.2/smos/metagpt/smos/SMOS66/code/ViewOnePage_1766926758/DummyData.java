import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for generating dummy data (academic years, classes, students, semesters, report cards)
 * for testing the ViewOnePage program.
 * This class follows the singleton pattern to ensure consistent data across the application.
 */
public class DummyData {
    private static DummyData instance;
    
    private final List<AcademicYear> academicYears;
    private final List<Class> classes;
    private final List<Student> students;
    private final List<Semester> semesters;
    private final List<ReportCard> reportCards;
    
    /**
     * Private constructor to prevent external instantiation.
     * Initializes all dummy data.
     */
    private DummyData() {
        academicYears = new ArrayList<>();
        classes = new ArrayList<>();
        students = new ArrayList<>();
        semesters = new ArrayList<>();
        reportCards = new ArrayList<>();
        
        generateAcademicYears();
        generateSemesters();
        generateClasses();
        generateStudents();
        generateReportCards();
    }
    
    /**
     * Gets the singleton instance of DummyData.
     * @return The singleton instance.
     */
    public static synchronized DummyData getInstance() {
        if (instance == null) {
            instance = new DummyData();
        }
        return instance;
    }
    
    /**
     * Generates dummy academic years.
     */
    private void generateAcademicYears() {
        academicYears.clear();
        academicYears.add(new AcademicYear("AY2023", Year.of(2023), Year.of(2024)));
        academicYears.add(new AcademicYear("AY2024", Year.of(2024), Year.of(2025)));
        academicYears.add(new AcademicYear("AY2025", Year.of(2025), Year.of(2026)));
    }
    
    /**
     * Generates dummy semesters for each academic year.
     */
    private void generateSemesters() {
        semesters.clear();
        for (AcademicYear year : academicYears) {
            String yearSuffix = year.getDisplayName().substring(2, 6); // Gets "2023" from "2023-2024"
            semesters.add(new Semester("S1_" + yearSuffix, "First Quadrimestre", year, 1));
            semesters.add(new Semester("S2_" + yearSuffix, "Second Quadrimestre", year, 2));
        }
    }
    
    /**
     * Generates dummy classes for each academic year.
     */
    private void generateClasses() {
        classes.clear();
        for (AcademicYear year : academicYears) {
            String yearSuffix = year.getDisplayName().substring(2, 6);
            String[] classNames = {"A", "B", "C"};
            for (String className : classNames) {
                String gradeLevel = yearSuffix.equals("2023") ? "10" : 
                                   yearSuffix.equals("2024") ? "11" : "12";
                classes.add(new Class("C" + gradeLevel + className + "_" + yearSuffix, 
                                     gradeLevel + className, year));
            }
        }
    }
    
    /**
     * Generates dummy students for each class.
     */
    private void generateStudents() {
        students.clear();
        String[] firstNames = {"John", "Jane", "Robert", "Emily", "Michael", "Sarah", 
                              "David", "Lisa", "James", "Mary", "William", "Patricia"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
                             "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez"};
        
        int studentId = 1001;
        for (Class classObj : classes) {
            String className = classObj.getName();
            AcademicYear year = classObj.getAcademicYear();
            
            // Create 3-5 students per class
            int studentsPerClass = 3 + (className.hashCode() % 3); // Varies between 3-5
            for (int i = 0; i < studentsPerClass; i++) {
                String firstName = firstNames[(studentId + i) % firstNames.length];
                String lastName = lastNames[(studentId + i) % lastNames.length];
                students.add(new Student("S" + studentId, firstName, lastName, className, year));
                studentId++;
            }
        }
    }
    
    /**
     * Generates dummy report cards for each student and semester.
     */
    private void generateReportCards() {
        reportCards.clear();
        String[] subjects = {"Mathematics", "Physics", "Chemistry", "English", "History", "Biology"};
        String[] comments = {
            "Excellent performance, keep up the good work!",
            "Good progress, needs more practice in some areas.",
            "Satisfactory performance, room for improvement.",
            "Showing improvement, continue to work hard.",
            "Outstanding in all subjects!",
            "Needs to focus more on studies.",
            "Strong in theoretical concepts, needs practical improvement.",
            "Very consistent performance across all subjects."
        };
        
        for (Student student : students) {
            for (Semester semester : semesters) {
                // Only generate report cards if the semester belongs to the student's academic year
                if (semester.getAcademicYear().equals(student.getAcademicYear())) {
                    Map<String, Double> grades = generateGrades(subjects);
                    String comment = comments[(student.getStudentId().hashCode() + 
                                              semester.getSemesterId().hashCode()) % comments.length];
                    reportCards.add(new ReportCard(student, semester, grades, comment));
                }
            }
        }
    }
    
    /**
     * Generates random grades for a list of subjects.
     * @param subjects Array of subject names.
     * @return Map of subject names to grades (0-20 scale).
     */
    private Map<String, Double> generateGrades(String[] subjects) {
        Map<String, Double> grades = new HashMap<>();
        for (String subject : subjects) {
            // Generate random grade between 8.0 and 20.0, rounded to 0.5
            double grade = 8.0 + (Math.random() * 12.0); // 8.0 to 20.0
            grade = Math.round(grade * 2) / 2.0; // Round to nearest 0.5
            grades.put(subject, grade);
        }
        return grades;
    }
    
    /**
     * Gets all dummy academic years.
     * @return List of AcademicYear objects.
     */
    public List<AcademicYear> getAcademicYears() {
        return new ArrayList<>(academicYears);
    }
    
    /**
     * Gets all dummy classes.
     * @return List of Class objects.
     */
    public List<Class> getClasses() {
        return new ArrayList<>(classes);
    }
    
    /**
     * Gets all dummy students.
     * @return List of Student objects.
     */
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }
    
    /**
     * Gets all dummy semesters.
     * @return List of Semester objects.
     */
    public List<Semester> getSemesters() {
        return new ArrayList<>(semesters);
    }
    
    /**
     * Gets all dummy report cards.
     * @return List of ReportCard objects.
     */
    public List<ReportCard> getReportCards() {
        return new ArrayList<>(reportCards);
    }
    
    /**
     * Gets classes for a specific academic year.
     * @param academicYear The academic year to filter by.
     * @return List of Class objects for the given academic year.
     * @throws NullPointerException if academicYear is null.
     */
    public List<Class> getClassesByAcademicYear(AcademicYear academicYear) {
        if (academicYear == null) {
            throw new NullPointerException("Academic year cannot be null");
        }
        
        List<Class> result = new ArrayList<>();
        for (Class c : classes) {
            if (c.getAcademicYear().equals(academicYear)) {
                result.add(c);
            }
        }
        return result;
    }
    
    /**
     * Gets students in a specific class.
     * @param classObj The class to get students from.
     * @return List of Student objects in the given class.
     * @throws NullPointerException if classObj is null.
     */
    public List<Student> getStudentsByClass(Class classObj) {
        if (classObj == null) {
            throw new NullPointerException("Class cannot be null");
        }
        
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getClassName().equals(classObj.getName()) && 
                s.getAcademicYear().equals(classObj.getAcademicYear())) {
                result.add(s);
            }
        }
        return result;
    }
    
    /**
     * Gets report cards for a specific student and semester.
     * @param student The student to filter by.
     * @param semester The semester to filter by.
     * @return ReportCard object for the given criteria, or null if not found.
     * @throws NullPointerException if student or semester is null.
     */
    public ReportCard getReportCard(Student student, Semester semester) {
        if (student == null) {
            throw new NullPointerException("Student cannot be null");
        }
        if (semester == null) {
            throw new NullPointerException("Semester cannot be null");
        }
        
        for (ReportCard rc : reportCards) {
            if (rc.getStudent().equals(student) && rc.getSemester().equals(semester)) {
                return rc;
            }
        }
        return null;
    }
    
    /**
     * Resets the singleton instance (useful for testing).
     */
    public static void resetInstance() {
        instance = null;
    }
}