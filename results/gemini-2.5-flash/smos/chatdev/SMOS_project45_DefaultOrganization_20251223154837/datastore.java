/*
 * A mock data store to simulate retrieving academic years, classes, students, and report card data.
 * In a real application, this would interact with a database or a backend API.
 */
package data;
import model.CourseGrade;
import model.ReportCard;
import model.Student;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class DataStore {
    /**
     * Custom exception to simulate connection failure when retrieving data.
     */
    public static class ServerConnectionException extends RuntimeException {
        public ServerConnectionException(String message) {
            super(message);
        }
    }
    // Flag to trigger simulated server connection errors.
    private static volatile boolean simulateError = false;
    /**
     * Sets whether to simulate a server connection error for data retrieval.
     * @param error If true, data retrieval methods (like getReportCard) might throw ServerConnectionException.
     */
    public static void setSimulateError(boolean error) {
        simulateError = error;
    }
    // Mock data for academic years
    private static final List<String> ACADEMIC_YEARS = Arrays.asList(
            "2022-2023",
            "2023-2024"
    );
    // Mock data for classes per academic year
    // Map: Academic Year -> List of Class Names
    private static final Map<String, List<String>> CLASSES_BY_YEAR = new HashMap<>();
    static {
        CLASSES_BY_YEAR.put("2022-2023", Arrays.asList("Grade 10A", "Grade 10B", "Grade 11C"));
        CLASSES_BY_YEAR.put("2023-2024", Arrays.asList("Grade 10A", "Grade 10B", "Grade 11A", "Grade 12X"));
    }
    // Mock data for students per class and academic year
    // Map: Academic Year -> (Map: Class Name -> List of Students)
    private static final Map<String, Map<String, List<Student>>> STUDENTS_BY_CLASS = new HashMap<>();
    static {
        // 2022-2023
        Map<String, List<Student>> year2022_2023 = new HashMap<>();
        year2022_2023.put("Grade 10A", Arrays.asList(
                new Student("S001", "Alice Smith"),
                new Student("S002", "Bob Johnson")
        ));
        year2022_2023.put("Grade 10B", Arrays.asList(
                new Student("S003", "Charlie Brown"),
                new Student("S004", "Diana Prince")
        ));
        year2022_2023.put("Grade 11C", Arrays.asList(
                new Student("S005", "Eve Adams")
        ));
        STUDENTS_BY_CLASS.put("2022-2023", year2022_2023);
        // 2023-2024
        Map<String, List<Student>> year2023_2024 = new HashMap<>();
        year2023_2024.put("Grade 10A", Arrays.asList(
                new Student("S006", "Frank White"),
                new Student("S007", "Grace Black")
        ));
        year2023_2024.put("Grade 10B", Arrays.asList(
                new Student("S008", "Henry Green")
        ));
        year2023_2024.put("Grade 11A", Arrays.asList(
                new Student("S009", "Ivy Blue"),
                new Student("S010", "Jack Red")
        ));
         year2023_2024.put("Grade 12X", Arrays.asList(
                new Student("S011", "Karen Yellow")
        ));
        STUDENTS_BY_CLASS.put("2023-2024", year2023_2024);
    }
    // Mock data for generating grades
    private static final List<String> COURSES = Arrays.asList(
            "Math", "Science", "History", "Literature", "Art", "PE"
    );
     private static final List<String> GRADES = Arrays.asList(
            "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"
    );
    private static final Random random = new Random();
    /**
     * Retrieves a list of available academic years.
     * @return A list of academic year strings.
     */
    public List<String> getAcademicYears() {
        return ACADEMIC_YEARS;
    }
    /**
     * Retrieves a list of class names for a given academic year.
     * @param academicYear The academic year.
     * @return A list of class names, or an empty list if no classes for the year.
     */
    public List<String> getClassesForYear(String academicYear) {
        return CLASSES_BY_YEAR.getOrDefault(academicYear, Collections.emptyList());
    }
    /**
     * Retrieves a list of students for a given academic year and class name.
     * @param academicYear The academic year.
     * @param className The name of the class.
     * @return A list of Student objects, or an empty list if no students found.
     */
    public List<Student> getStudentsForClass(String academicYear, String className) {
        Map<String, List<Student>> classesInYear = STUDENTS_BY_CLASS.get(academicYear);
        if (classesInYear != null) {
            return classesInYear.getOrDefault(className, Collections.emptyList());
        }
        return Collections.emptyList();
    }
    /**
     * Generates a mock report card for a selected student, academic year, class, and four months.
     * Grades are randomly generated for demonstration purposes.
     * This method can simulate a server connection interruption if setSimulateError(true) has been called.
     * @param academicYear The academic year.
     * @param className The class name.
     * @param student The student.
     * @param months A list of exactly four month names.
     * @return A ReportCard object containing mock grades.
     * @throws IllegalArgumentException if the list of months is not exactly four.
     * @throws ServerConnectionException if a simulated server error occurs.
     */
    public ReportCard getReportCard(String academicYear, String className, Student student, List<String> months) {
        // Simulate server connection interruption
        if (simulateError) {
             // Simulate a brief delay to make the error seem more realistic
             try {
                 Thread.sleep(500); // Simulate network latency
             } catch (InterruptedException ex) {
                 Thread.currentThread().interrupt(); // Restore interrupt status
             }
            throw new ServerConnectionException("Connection to SMOS server interrupted.");
        }
        if (months == null || months.size() != 4) {
            throw new IllegalArgumentException("Exactly four months must be selected for the report card.");
        }
        Map<String, List<CourseGrade>> gradesByMonth = new HashMap<>();
        for (String month : months) {
            List<CourseGrade> monthGrades = new ArrayList<>();
            // Simulate 3-5 courses per month with random grades
            int numCourses = 3 + random.nextInt(3); // 3, 4, or 5 courses
            Collections.shuffle(COURSES); // Randomize course selection
            for (int i = 0; i < numCourses; i++) {
                String course = COURSES.get(i % COURSES.size()); // Ensure we don't go out of bounds if numCourses > COURSES.size
                String grade = GRADES.get(random.nextInt(GRADES.size()));
                monthGrades.add(new CourseGrade(course, grade));
            }
            gradesByMonth.put(month, monthGrades);
        }
        return new ReportCard(student, academicYear, months, gradesByMonth);
    }
}