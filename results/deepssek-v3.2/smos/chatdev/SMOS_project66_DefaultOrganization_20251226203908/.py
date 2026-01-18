/**
 * Database.java
 * Simulates database operations for the report card system
 * Provides mock data for demonstration purposes
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class Database {
    private List<AcademicYear> academicYears;
    private List<Classroom> allClasses;
    private Map<String, List<Classroom>> classesByYear;
    private Map<String, ReportCard> reportCards;
    public Database() {
        initializeMockData();
    }
    private void initializeMockData() {
        // Initialize academic years
        academicYears = new ArrayList<>();
        academicYears.add(new AcademicYear("AY2022", "2022", "2023", false));
        academicYears.add(new AcademicYear("AY2023", "2023", "2024", true));
        academicYears.add(new AcademicYear("AY2021", "2021", "2022", false));
        // Create students
        Student student1 = new Student("S001", "John", "Doe", "2008-05-15", "Male", "555-0101");
        Student student2 = new Student("S002", "Jane", "Smith", "2008-07-22", "Female", "555-0102");
        Student student3 = new Student("S003", "Mike", "Johnson", "2009-02-10", "Male", "555-0103");
        Student student4 = new Student("S004", "Emily", "Williams", "2008-11-30", "Female", "555-0104");
        Student student5 = new Student("S005", "David", "Brown", "2009-04-05", "Male", "555-0105");
        Student student6 = new Student("S006", "Sarah", "Davis", "2008-09-18", "Female", "555-0106");
        // Create classes
        Classroom class1 = new Classroom("C101", "Grade 5A", "5th Grade", "Room 101");
        Classroom class2 = new Classroom("C102", "Grade 5B", "5th Grade", "Room 102");
        Classroom class3 = new Classroom("C201", "Grade 6A", "6th Grade", "Room 201");
        // Assign students to classes
        class1.addStudent(student1);
        class1.addStudent(student2);
        class2.addStudent(student3);
        class2.addStudent(student4);
        class3.addStudent(student5);
        class3.addStudent(student6);
        allClasses = Arrays.asList(class1, class2, class3);
        // Organize classes by academic year
        classesByYear = new HashMap<>();
        classesByYear.put("2022-2023", Arrays.asList(class1, class2));
        classesByYear.put("2023-2024", Arrays.asList(class1, class2, class3));
        classesByYear.put("2021-2022", Arrays.asList(class1));
        // Initialize report cards
        reportCards = new HashMap<>();
        createMockReportCards();
    }
    private void createMockReportCards() {
        // Create sample report card for John Doe - First Quadrimestre 2023-2024
        ReportCard report1 = new ReportCard("S001", "John Doe", "2023-2024", "First Quadrimestre");
        report1.addSubjectGrade(new SubjectGrade("Mathematics", "MATH101", 88.5, 
            "Mr. Johnson", "Excellent progress in algebra"));
        report1.addSubjectGrade(new SubjectGrade("English", "ENG101", 92.0, 
            "Ms. Williams", "Strong writing skills"));
        report1.addSubjectGrade(new SubjectGrade("Science", "SCI101", 85.0, 
            "Dr. Brown", "Good lab work"));
        report1.addSubjectGrade(new SubjectGrade("History", "HIS101", 90.0, 
            "Mr. Davis", "Active participation"));
        report1.addSubjectGrade(new SubjectGrade("Art", "ART101", 95.0, 
            "Ms. Taylor", "Very creative"));
        report1.setTeacherComments("John is a diligent student who shows great potential. " +
            "He participates actively in class discussions and completes all assignments on time.");
        report1.setPrincipalComments("Keep up the good work!");
        reportCards.put("S001-2023-2024-First Quadrimestre", report1);
        // Create sample report card for Jane Smith - First Quadrimestre 2023-2024
        ReportCard report2 = new ReportCard("S002", "Jane Smith", "2023-2024", "First Quadrimestre");
        report2.addSubjectGrade(new SubjectGrade("Mathematics", "MATH101", 95.5, 
            "Mr. Johnson", "Top of the class in geometry"));
        report2.addSubjectGrade(new SubjectGrade("English", "ENG101", 94.0, 
            "Ms. Williams", "Excellent vocabulary"));
        report2.addSubjectGrade(new SubjectGrade("Science", "SCI101", 96.0, 
            "Dr. Brown", "Outstanding in experiments"));
        report2.addSubjectGrade(new SubjectGrade("History", "HIS101", 92.0, 
            "Mr. Davis", "Well-researched projects"));
        report2.addSubjectGrade(new SubjectGrade("Art", "ART101", 88.0, 
            "Ms. Taylor", "Improving techniques"));
        report2.setTeacherComments("Jane is an exceptional student who excels in all subjects. " +
            "She shows leadership qualities and helps her peers.");
        report2.setPrincipalComments("Outstanding performance!");
        reportCards.put("S002-2023-2024-First Quadrimestre", report2);
    }
    public List<AcademicYear> getAcademicYears() {
        return new ArrayList<>(academicYears);
    }
    public List<Classroom> getClassesByYear(String yearLabel) {
        // Extract year range from label (e.g., "2023-2024 (Current)" -> "2023-2024")
        String yearKey = yearLabel.split(" ")[0];
        return classesByYear.getOrDefault(yearKey, new ArrayList<>());
    }
    public ReportCard getReportCard(String studentId, String academicYear, String quadrimestre) {
        String key = studentId + "-" + academicYear + "-" + quadrimestre;
        return reportCards.get(key);
    }
}