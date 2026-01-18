package ViewClassListTeacher_1766400150;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simulates a backend SMOS (School Management Operating System) server.
 * This class provides methods to retrieve academic years and courses associated with professors.
 * It uses dummy data for demonstration purposes.
 */
public class SMOSServer {

    // --- Simulated Database ---
    // Stores all available academic years
    private static final Set<AcademicYear> ALL_ACADEMIC_YEARS = new HashSet<>();
    // Stores all available professors
    private static final Set<Professor> ALL_PROFESSORS = new HashSet<>();
    // Stores all available courses
    private static final Set<Course> ALL_COURSES = new HashSet<>();
    // Maps a Professor to a set of Courses they teach
    private static final Map<Professor, Set<Course>> PROFESSOR_COURSE_MAP = new HashMap<>();

    // Static initializer block to populate dummy data
    static {
        // 1. Create Academic Years
        AcademicYear ay2022 = new AcademicYear("AY2022-2023", "Academic Year 2022-2023");
        AcademicYear ay2023 = new AcademicYear("AY2023-2024", "Academic Year 2023-2024");
        AcademicYear ay2024 = new AcademicYear("AY2024-2025", "Academic Year 2024-2025");
        AcademicYear ay2025 = new AcademicYear("AY2025-2026", "Academic Year 2025-2026");

        ALL_ACADEMIC_YEARS.add(ay2022);
        ALL_ACADEMIC_YEARS.add(ay2023);
        ALL_ACADEMIC_YEARS.add(ay2024);
        ALL_ACADEMIC_YEARS.add(ay2025);

        // 2. Create Professors
        Professor profAlice = new Professor("P001", "Dr. Alice Smith");
        Professor profBob = new Professor("P002", "Prof. Bob Johnson");
        Professor profCharlie = new Professor("P003", "Dr. Charlie Brown");

        ALL_PROFESSORS.add(profAlice);
        ALL_PROFESSORS.add(profBob);
        ALL_PROFESSORS.add(profCharlie);

        // 3. Create Courses and assign to Professors
        // Courses for Dr. Alice Smith
        Course cs101_2022 = new Course("CS101", "Intro to Programming", ay2022);
        Course ma201_2022 = new Course("MA201", "Calculus I", ay2022);
        Course cs101_2023 = new Course("CS101", "Intro to Programming", ay2023);
        Course ph101_2023 = new Course("PH101", "Physics I", ay2023);
        Course cs305_2024 = new Course("CS305", "Data Structures", ay2024);

        ALL_COURSES.add(cs101_2022);
        ALL_COURSES.add(ma201_2022);
        ALL_COURSES.add(cs101_2023);
        ALL_COURSES.add(ph101_2023);
        ALL_COURSES.add(cs305_2024);

        PROFESSOR_COURSE_MAP.computeIfAbsent(profAlice, k -> new HashSet<>()).add(cs101_2022);
        PROFESSOR_COURSE_MAP.computeIfAbsent(profAlice, k -> new HashSet<>()).add(ma201_2022);
        PROFESSOR_COURSE_MAP.computeIfAbsent(profAlice, k -> new HashSet<>()).add(cs101_2023);
        PROFESSOR_COURSE_MAP.computeIfAbsent(profAlice, k -> new HashSet<>()).add(ph101_2023);
        PROFESSOR_COURSE_MAP.computeIfAbsent(profAlice, k -> new HashSet<>()).add(cs305_2024);

        // Courses for Prof. Bob Johnson
        Course ma201_2023 = new Course("MA201", "Calculus I", ay2023);
        Course ma301_2023 = new Course("MA301", "Linear Algebra", ay2023);
        Course ma201_2024 = new Course("MA201", "Calculus I", ay2024);

        ALL_COURSES.add(ma201_2023);
        ALL_COURSES.add(ma301_2023);
        ALL_COURSES.add(ma201_2024);

        PROFESSOR_COURSE_MAP.computeIfAbsent(profBob, k -> new HashSet<>()).add(ma201_2023);
        PROFESSOR_COURSE_MAP.computeIfAbsent(profBob, k -> new HashSet<>()).add(ma301_2023);
        PROFESSOR_COURSE_MAP.computeIfAbsent(profBob, k -> new HashSet<>()).add(ma201_2024);

        // Prof. Charlie Brown teaches nothing in this dummy data to test edge cases
        // PROFESSOR_COURSE_MAP.computeIfAbsent(profCharlie, k -> new HashSet<>()); // Will be an empty set
    }

    /**
     * Private constructor to prevent instantiation, as this is a utility class.
     */
    private SMOSServer() {
        // Utility class
    }

    /**
     * Simulates retrieving all academic years in which a given professor teaches at least one class.
     *
     * @param professor The professor for whom to retrieve academic years.
     * @return A sorted list of unique AcademicYear objects where the professor teaches,
     *         or an empty list if the professor is null, not found, or teaches no classes.
     */
    public static List<AcademicYear> getAcademicYearsForProfessor(Professor professor) {
        if (professor == null) {
            System.err.println("SMOSServer: Cannot retrieve academic years for a null professor.");
            return Collections.emptyList();
        }

        // Check if the professor exists in our simulated data
        if (!ALL_PROFESSORS.contains(professor)) {
            System.err.println("SMOSServer: Professor with ID " + professor.getId() + " not found.");
            return Collections.emptyList();
        }

        Set<Course> taughtCourses = PROFESSOR_COURSE_MAP.getOrDefault(professor, Collections.emptySet());

        // Extract unique academic years from the courses taught by the professor
        List<AcademicYear> academicYears = taughtCourses.stream()
                .map(Course::getAcademicYear)
                .distinct()
                .sorted((ay1, ay2) -> ay1.getId().compareTo(ay2.getId())) // Sort by ID for consistent order
                .collect(Collectors.toList());

        return academicYears;
    }

    /**
     * Simulates retrieving all courses taught by a given professor in a specific academic year.
     *
     * @param professor    The professor for whom to retrieve courses.
     * @param academicYear The academic year of interest.
     * @return A list of Course objects taught by the professor in the specified academic year,
     *         or an empty list if inputs are null, professor not found, or no courses match.
     */
    public static List<Course> getCoursesForProfessorAndAcademicYear(Professor professor, AcademicYear academicYear) {
        if (professor == null || academicYear == null) {
            System.err.println("SMOSServer: Cannot retrieve courses for null professor or academic year.");
            return Collections.emptyList();
        }

        // Check if the professor exists in our simulated data
        if (!ALL_PROFESSORS.contains(professor)) {
            System.err.println("SMOSServer: Professor with ID " + professor.getId() + " not found.");
            return Collections.emptyList();
        }

        Set<Course> taughtCourses = PROFESSOR_COURSE_MAP.getOrDefault(professor, Collections.emptySet());

        // Filter courses by the specified academic year
        List<Course> coursesInYear = taughtCourses.stream()
                .filter(course -> course.getAcademicYear().equals(academicYear))
                .sorted((c1, c2) -> c1.getName().compareTo(c2.getName())) // Sort by course name for consistent order
                .collect(Collectors.toList());

        return coursesInYear;
    }

    /**
     * Simulates a login process to retrieve a Professor object.
     * In a real system, this would involve authentication. Here, it's a simple lookup.
     *
     * @param professorId The ID of the professor to retrieve.
     * @return The Professor object if found, otherwise null.
     */
    public static Professor getProfessorById(String professorId) {
        if (professorId == null || professorId.trim().isEmpty()) {
            return null;
        }
        return ALL_PROFESSORS.stream()
                .filter(p -> p.getId().equals(professorId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Simulates a connection interruption.
     * This method doesn't actually interrupt anything but serves as a placeholder
     * for the postcondition "Connection to the interrupted SMOS server."
     * In a real application, this might involve closing database connections,
     * releasing resources, or logging out.
     */
    public static void interruptConnection() {
        System.out.println("SMOSServer: Simulating connection interruption. Resources released.");
        // In a real scenario, this might involve closing database connections,
        // clearing session data, or other cleanup.
    }
}