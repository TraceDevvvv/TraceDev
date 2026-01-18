import java.util.*;
/**
 * TeacherDataService simulates data retrieval from a backend or database.
 * This class provides methods to fetch academic years and classes for the logged-in teacher.
 * In a real application, this would connect to a database or an external service.
 */
public class TeacherDataService {
    // Simulated data for demonstration.
    // Structure: Map from academic year to list of class names
    private Map<String, List<String>> teacherClassesData;
    public TeacherDataService() {
        // Initialize with sample data for a teacher
        teacherClassesData = new HashMap<>();
        // Academic years in descending order for better user experience
        teacherClassesData.put("2024-2025", Arrays.asList(
            "Advanced Java Programming",
            "Web Development",
            "Mobile Application Development"
        ));
        teacherClassesData.put("2023-2024", Arrays.asList(
            "Introduction to Computer Science",
            "Data Structures and Algorithms",
            "Software Engineering"
        ));
        teacherClassesData.put("2022-2023", Arrays.asList(
            "Programming Fundamentals",
            "Database Systems"
        ));
    }
    /**
     * Returns the list of academic years in which the teacher teaches at least one class.
     * This is used to populate the year combo box.
     * @return List of academic years as strings, sorted in descending order.
     */
    public List<String> getAcademicYearsForTeacher() {
        // Return the keys (academic years) sorted in reverse order (newest first)
        List<String> years = new ArrayList<>(teacherClassesData.keySet());
        years.sort(Collections.reverseOrder());
        return years;
    }
    /**
     * Returns the list of class names for a given academic year.
     * @param academicYear The selected academic year.
     * @return List of class names. Empty list if year not found.
     */
    public List<String> getClassesForYear(String academicYear) {
        // Return the list of classes for the given year, or empty list if year not present
        return teacherClassesData.getOrDefault(academicYear, Collections.emptyList());
    }
}