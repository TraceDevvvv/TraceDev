import java.util.*;
/**
 * TeacherDataService simulates data retrieval from a backend or database.
 * This class provides methods to fetch academic years and classes for the logged-in teacher.
 * In a real application, this would connect to a database or an external service.
 */
class TeacherDataService {
    // Simulated data for demonstration.
    // Structure: Map from academic year to list of class names
    private Map<String, List<String>> teacherClassesData;
    public TeacherDataService() {
        // Initialize with sample data for a teacher
        teacherClassesData = new HashMap<>();
        // Academic year 2023-2024
        teacherClassesData.put("2023-2024", Arrays.asList(
            "Introduction to Computer Science",
            "Data Structures and Algorithms",
            "Software Engineering"
        ));
        // Academic year 2022-2023
        teacherClassesData.put("2022-2023", Arrays.asList(
            "Programming Fundamentals",
            "Database Systems"
        ));
        // Academic year 2024-2025 (future)
        teacherClassesData.put("2024-2025", Arrays.asList(
            "Advanced Java Programming",
            "Web Development",
            "Mobile Application Development"
        ));
    }
    /**
     * Returns the list of academic years in which the teacher teaches at least one class.
     * This is used to populate the year combo box.
     * @return List of academic years as strings.
     */
    public List<String> getAcademicYearsForTeacher() {
        // Simply return the keys (academic years) from our map
        return new ArrayList<>(teacherClassesData.keySet());
    }
    /**
     * Returns the list of class names for a given academic year.
     * @param academicYear The selected academic year.
     * @return List of class names. Empty list if year not found.
     */
    public List<String> getClassesForYear(String academicYear) {
        // Return the list of classes for the given year, or empty list if year not present
        return teacherClassesData.getOrDefault(academicYear, new ArrayList<>());
    }
}