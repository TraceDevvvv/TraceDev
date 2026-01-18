/**
 * The DataManager class is responsible for managing and providing simulated data
 * for academic years, classes, students, and their report cards.
 * In a real-world application, this would interact with a database or a backend service.
 */
package data;
import models.AcademicYear;
import models.ReportCard;
import models.Student;
import models.StudentClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class DataManager {
    private List<AcademicYear> academicYears;
    private List<StudentClass> studentClasses;
    private List<Student> students;
    private List<ReportCard> reportCards;
    public DataManager() {
        initData();
    }
    /**
     * Initializes dummy data for academic years, classes, students, and report cards.
     * This simulates data that would typically come from a database.
     */
    private void initData() {
        academicYears = new ArrayList<>();
        studentClasses = new ArrayList<>();
        students = new ArrayList<>();
        reportCards = new ArrayList<>();
        // Add Academic Years
        AcademicYear year2324 = new AcademicYear("AY2324", "2023-2024");
        AcademicYear year2223 = new AcademicYear("AY2223", "2022-2023");
        academicYears.add(year2324);
        academicYears.add(year2223);
        // Add Classes for 2023-2024
        StudentClass class10A_2324 = new StudentClass("CL10A_AY2324", "10A", year2324.getId());
        StudentClass class11B_2324 = new StudentClass("CL11B_AY2324", "11B", year2324.getId());
        StudentClass class9C_2324 = new StudentClass("CL9C_AY2324", "9C", year2324.getId());
        studentClasses.add(class10A_2324);
        studentClasses.add(class11B_2324);
        studentClasses.add(class9C_2324);
        // Add Classes for 2022-2023
        StudentClass class10A_2223 = new StudentClass("CL10A_AY2223", "10A", year2223.getId());
        StudentClass class11B_2223 = new StudentClass("CL11B_AY2223", "11B", year2223.getId());
        studentClasses.add(class10A_2223);
        studentClasses.add(class11B_2223);
        // Add Students for 10A (2023-2024)
        // Assigning unique IDs to student instances for proper data management.
        Student s1_10A_2324 = new Student("S001-AY2324-10A", "Alice", "Smith", class10A_2324.getId());
        Student s2_10A_2324 = new Student("S002-AY2324-10A", "Bob", "Johnson", class10A_2324.getId());
        students.add(s1_10A_2324);
        students.add(s2_10A_2324);
        // Add Students for 11B (2023-2024)
        Student s3_11B_2324 = new Student("S003-AY2324-11B", "Charlie", "Brown", class11B_2324.getId());
        students.add(s3_11B_2324);
        // Add Students for 10A (2022-2023) - Alice in 10A in previous year
        // Assigning a new unique ID for this specific enrollment instance of Alice Smith
        Student s1_10A_2223 = new Student("S001-AY2223-10A", "Alice", "Smith", class10A_2223.getId());
        students.add(s1_10A_2223);
        // Add Report Cards
        // Alice Smith, 10A, 2023-2024, Q1
        Map<String, String> grades_S001_AY2324_Q1 = new HashMap<>();
        grades_S001_AY2324_Q1.put("Math", "A");
        grades_S001_AY2324_Q1.put("Science", "B+");
        grades_S001_AY2324_Q1.put("English", "A-");
        reportCards.add(new ReportCard(s1_10A_2324.getId(), year2324.getId(), "Q1", grades_S001_AY2324_Q1, "Excellent progress."));
        // Alice Smith, 10A, 2023-2024, Q2
        Map<String, String> grades_S001_AY2324_Q2 = new HashMap<>();
        grades_S001_AY2324_Q2.put("Math", "A-");
        grades_S001_AY2324_Q2.put("Science", "A");
        grades_S001_AY2324_Q2.put("English", "A");
        reportCards.add(new ReportCard(s1_10A_2324.getId(), year2324.getId(), "Q2", grades_S001_AY2324_Q2, "Improved significantly."));
        // Bob Johnson, 10A, 2023-2024, Q1
        Map<String, String> grades_S002_AY2324_Q1 = new HashMap<>();
        grades_S002_AY2324_Q1.put("Math", "C+");
        grades_S002_AY2324_Q1.put("Science", "B");
        grades_S002_AY2324_Q1.put("English", "C");
        reportCards.add(new ReportCard(s2_10A_2324.getId(), year2324.getId(), "Q1", grades_S002_AY2324_Q1, "Needs to focus more on Math and English."));
        // Charlie Brown, 11B, 2023-2024, Q1
        Map<String, String> grades_S003_AY2324_Q1 = new HashMap<>();
        grades_S003_AY2324_Q1.put("Physics", "B+");
        grades_S003_AY2324_Q1.put("Chemistry", "B");
        grades_S003_AY2324_Q1.put("History", "A-");
        reportCards.add(new ReportCard(s3_11B_2324.getId(), year2324.getId(), "Q1", grades_S003_AY2324_Q1, "Good effort in all subjects."));
        // Alice Smith, 10A, 2022-2023, Q1 (previous year)
        Map<String, String> grades_S001_AY2223_Q1 = new HashMap<>();
        grades_S001_AY2223_Q1.put("Math", "B");
        grades_S001_AY2223_Q1.put("Science", "C+");
        grades_S001_AY2223_Q1.put("English", "B+");
        reportCards.add(new ReportCard(s1_10A_2223.getId(), year2223.getId(), "Q1", grades_S001_AY2223_Q1, "Steady performance."));
    }
    /**
     * Retrieves all available academic years.
     *
     * @return A list of AcademicYear objects.
     */
    public List<AcademicYear> getAcademicYears() {
        return new ArrayList<>(academicYears);
    }
    /**
     * Retrieves a list of student classes for a specific academic year.
     *
     * @param academicYearId The ID of the academic year.
     * @return A list of StudentClass objects associated with the given academic year.
     */
    public List<StudentClass> getClassesByAcademicYear(String academicYearId) {
        return studentClasses.stream()
                .filter(studentClass -> studentClass.getAcademicYearId().equals(academicYearId))
                .collect(Collectors.toList());
    }
    /**
     * Retrieves a list of students belonging to a specific class.
     *
     * @param classId The ID of the student class.
     * @return A list of Student objects associated with the given class.
     */
    public List<Student> getStudentsByClass(String classId) {
        return students.stream()
                .filter(student -> student.getClassId().equals(classId))
                .collect(Collectors.toList());
    }
    /**
     * Retrieves a specific report card for a student in a given academic year and quadrimestre.
     *
     * @param studentId       The ID of the student.
     * @param academicYearId The ID of the academic year.
     * @param quadrimestre    The quadrimestre (e.g., "Q1", "Q2").
     * @return The ReportCard object if found, otherwise null.
     */
    public ReportCard getReportCard(String studentId, String academicYearId, String quadrimestre) {
        return reportCards.stream()
                .filter(rc -> rc.getStudentId().equals(studentId) &&
                               rc.getAcademicYearId().equals(academicYearId) &&
                               rc.getQuadrimestre().equals(quadrimestre))
                .findFirst()
                .orElse(null);
    }
}