/**
 * This class simulates the SMOS server.
 * It provides static methods to retrieve data, acting as a mock database or API for academic years and classes.
 * In a real application, this would involve database queries or external API calls.
 * The connection to the SMOS server is implicitly "interrupted" or closed after each data retrieval operation
 * as there's no persistent connection object managed here for this simplified example.
 */
package service;
import model.AcademicYear;
import model.SchoolClass;
import model.Teacher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
public class SMOS_Server {
    // --- Mock Data ---
    private static final List<Teacher> allTeachers = Arrays.asList(
            new Teacher("T001", "Professor Smith"),
            new Teacher("T002", "Dr. Johnson")
    );
    private static final List<AcademicYear> allAcademicYears = Arrays.asList(
            new AcademicYear("AY2022-2023", "Academic Year 2022-2023"),
            new AcademicYear("AY2023-2024", "Academic Year 2023-2024"),
            new AcademicYear("AY2024-2025", "Academic Year 2024-2025")
    );
    private static final List<SchoolClass> allClasses = Arrays.asList(
            // Professor Smith's classes
            new SchoolClass("C101", "Advanced Algorithms", "AY2022-2023", "T001"),
            new SchoolClass("C102", "Data Structures", "AY2022-2023", "T001"),
            new SchoolClass("C103", "Machine Learning", "AY2023-2024", "T001"),
            new SchoolClass("C104", "Software Engineering", "AY2023-2024", "T001"),
            new SchoolClass("C105", "Operating Systems", "AY2024-2025", "T001"),
            new SchoolClass("C106", "Distributed Systems", "AY2024-2025", "T001"),
            // Dr. Johnson's classes
            new SchoolClass("C201", "Calculus I", "AY2022-2023", "T002"),
            new SchoolClass("C202", "Linear Algebra", "AY2022-2023", "T002"),
            new SchoolClass("C203", "Calculus II", "AY2023-2024", "T002")
    );
    // --- End Mock Data ---
    /**
     * Retrieves a list of academic years in which the specified teacher teaches at least one class.
     * This simulates fetching data from a server or database.
     * @param teacherId The ID of the teacher.
     * @return A list of AcademicYear objects. Returns an empty list if no years are found or teacherId is invalid.
     */
    public static List<AcademicYear> getAcademicYearsForTeacher(String teacherId) {
        // Find all academic year IDs associated with classes taught by the given teacher
        Set<String> yearIds = allClasses.stream()
                .filter(c -> c.getTeacherId().equals(teacherId))
                .map(SchoolClass::getAcademicYearId)
                .collect(Collectors.toSet());
        // Filter the complete list of academic years to only include those found for the teacher
        List<AcademicYear> yearsForTeacher = allAcademicYears.stream()
                .filter(ay -> yearIds.contains(ay.getId()))
                .collect(Collectors.toList());
        // Sort by ID to ensure consistent ordering, e.g., "AY2022-2023" before "AY2023-2024"
        yearsForTeacher.sort((y1, y2) -> y1.getId().compareTo(y2.getId()));
        return yearsForTeacher;
    }
    /**
     * Retrieves a list of classes taught by the specified teacher in a given academic year.
     * This simulates fetching data from a server or database.
     * @param teacherId The ID of the teacher.
     * @param academicYearId The ID of the academic year.
     * @return A list of SchoolClass objects. Returns an empty list if no classes are found.
     */
    public static List<SchoolClass> getClassesForTeacherAndYear(String teacherId, String academicYearId) {
        if (teacherId == null || academicYearId == null) {
            return new ArrayList<>(); // Handle edge case of null inputs
        }
        return allClasses.stream()
                .filter(c -> c.getTeacherId().equals(teacherId) && c.getAcademicYearId().equals(academicYearId))
                .collect(Collectors.toList());
    }
    /**
     * Retrieves a teacher by their ID.
     * @param teacherId The ID of the teacher.
     * @return The Teacher object if found, otherwise null.
     */
    public static Teacher getTeacherById(String teacherId) {
        return allTeachers.stream()
                .filter(t -> t.getId().equals(teacherId))
                .findFirst()
                .orElse(null);
    }
}