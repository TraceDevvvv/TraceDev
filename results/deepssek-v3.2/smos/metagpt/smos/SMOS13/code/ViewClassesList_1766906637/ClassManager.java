import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * ClassManager handles all class management operations including
 * searching, filtering, and managing classes by academic year.
 * This is a utility class with static methods for managing class data.
 */
public class ClassManager {
    
    // Private constructor to prevent instantiation
    // This is a utility class with only static methods
    private ClassManager() {
        throw new IllegalStateException("ClassManager is a utility class and cannot be instantiated");
    }
    
    /**
     * Searches for classes in the given academic year from the database.
     * This is the main method called by the ViewClassesList use case.
     * 
     * @param academicYear The academic year to search for (e.g., "2023-2024")
     * @param classDatabase The database containing classes organized by academic year
     * @return List of classes for the specified academic year, or empty list if none found
     * @throws IllegalArgumentException if academicYear is null or empty, or if database is null
     */
    public static List<Class> searchClassesByYear(String academicYear, Map<String, List<Class>> classDatabase) {
        // Validate input parameters
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year cannot be null or empty");
        }
        
        if (classDatabase == null) {
            throw new IllegalArgumentException("Class database cannot be null");
        }
        
        String trimmedYear = academicYear.trim();
        System.out.println("Searching classes for academic year: " + trimmedYear);
        
        // Retrieve classes from database
        List<Class> classes = classDatabase.get(trimmedYear);
        
        if (classes == null) {
            // No classes found for the specified year
            System.out.println("No classes found for academic year: " + trimmedYear);
            return Collections.emptyList();
        }
        
        System.out.println("Found " + classes.size() + " classes for academic year: " + trimmedYear);
        return new ArrayList<>(classes); // Return a copy to prevent modification of original data
    }
    
    /**
     * Searches for a specific class by its code within an academic year.
     * 
     * @param classCode The class code to search for (e.g., "CS101")
     * @param academicYear The academic year to search in
     * @param classDatabase The database containing classes
     * @return The Class object if found, null otherwise
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public static Class searchClassByCode(String classCode, String academicYear, 
                                          Map<String, List<Class>> classDatabase) {
        validateSearchParameters(classCode, academicYear, classDatabase);
        
        List<Class> classes = classDatabase.get(academicYear.trim());
        if (classes == null) {
            return null;
        }
        
        // Search for class with matching code (case-insensitive)
        String searchCode = classCode.trim().toUpperCase();
        for (Class classObj : classes) {
            if (classObj.isSameClass(searchCode)) {
                return classObj;
            }
        }
        
        return null;
    }
    
    /**
     * Searches for classes by instructor name within an academic year.
     * 
     * @param instructorName The name of the instructor to search for
     * @param academicYear The academic year to search in
     * @param classDatabase The database containing classes
     * @return List of classes taught by the specified instructor, empty list if none found
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public static List<Class> searchClassesByInstructor(String instructorName, String academicYear,
                                                        Map<String, List<Class>> classDatabase) {
        validateSearchParameters(instructorName, academicYear, classDatabase);
        
        List<Class> yearClasses = classDatabase.get(academicYear.trim());
        if (yearClasses == null) {
            return Collections.emptyList();
        }
        
        List<Class> result = new ArrayList<>();
        String searchName = instructorName.trim().toLowerCase();
        
        for (Class classObj : yearClasses) {
            if (classObj.getInstructor().toLowerCase().contains(searchName)) {
                result.add(classObj);
            }
        }
        
        return result;
    }
    
    /**
     * Filters classes by minimum capacity within an academic year.
     * 
     * @param minCapacity Minimum capacity to filter by
     * @param academicYear The academic year to search in
     * @param classDatabase The database containing classes
     * @return List of classes with capacity >= minCapacity
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public static List<Class> filterClassesByMinCapacity(int minCapacity, String academicYear,
                                                         Map<String, List<Class>> classDatabase) {
        validateSearchParameters(academicYear, academicYear, classDatabase);
        
        if (minCapacity < 1) {
            throw new IllegalArgumentException("Minimum capacity must be at least 1");
        }
        
        List<Class> yearClasses = classDatabase.get(academicYear.trim());
        if (yearClasses == null) {
            return Collections.emptyList();
        }
        
        List<Class> result = new ArrayList<>();
        
        for (Class classObj : yearClasses) {
            if (classObj.getCapacity() >= minCapacity) {
                result.add(classObj);
            }
        }
        
        return result;
    }
    
    /**
     * Gets the total number of student seats available across all classes in an academic year.
     * 
     * @param academicYear The academic year to calculate for
     * @param classDatabase The database containing classes
     * @return Total capacity across all classes in the specified year
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public static int getTotalCapacityForYear(String academicYear, Map<String, List<Class>> classDatabase) {
        validateSearchParameters(academicYear, academicYear, classDatabase);
        
        List<Class> yearClasses = classDatabase.get(academicYear.trim());
        if (yearClasses == null) {
            return 0;
        }
        
        int totalCapacity = 0;
        for (Class classObj : yearClasses) {
            totalCapacity += classObj.getCapacity();
        }
        
        return totalCapacity;
    }
    
    /**
     * Gets a sorted list of classes for an academic year, sorted by class code.
     * 
     * @param academicYear The academic year to get classes from
     * @param classDatabase The database containing classes
     * @return Sorted list of classes by class code
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public static List<Class> getClassesSortedByCode(String academicYear, Map<String, List<Class>> classDatabase) {
        List<Class> classes = searchClassesByYear(academicYear, classDatabase);
        
        // Sort by class code
        classes.sort(Comparator.comparing(Class::getCode, String.CASE_INSENSITIVE_ORDER));
        
        return classes;
    }
    
    /**
     * Gets a sorted list of classes for an academic year, sorted by instructor name.
     * 
     * @param academicYear The academic year to get classes from
     * @param classDatabase The database containing classes
     * @return Sorted list of classes by instructor name
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public static List<Class> getClassesSortedByInstructor(String academicYear, 
                                                           Map<String, List<Class>> classDatabase) {
        List<Class> classes = searchClassesByYear(academicYear, classDatabase);
        
        // Sort by instructor name
        classes.sort(Comparator.comparing(Class::getInstructor, String.CASE_INSENSITIVE_ORDER));
        
        return classes;
    }
    
    /**
     * Validates the common search parameters used by multiple methods.
     * 
     * @param searchTerm The search term (class code, instructor name, etc.)
     * @param academicYear The academic year to search in
     * @param classDatabase The database containing classes
     * @throws IllegalArgumentException if any parameter is invalid
     */
    private static void validateSearchParameters(String searchTerm, String academicYear,
                                                 Map<String, List<Class>> classDatabase) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be null or empty");
        }
        
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year cannot be null or empty");
        }
        
        if (classDatabase == null) {
            throw new IllegalArgumentException("Class database cannot be null");
        }
    }
    
    /**
     * Generates a statistical report for classes in an academic year.
     * 
     * @param academicYear The academic year to generate report for
     * @param classDatabase The database containing classes
     * @return A formatted statistical report
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public static String generateYearStatistics(String academicYear, Map<String, List<Class>> classDatabase) {
        List<Class> classes = searchClassesByYear(academicYear, classDatabase);
        
        if (classes.isEmpty()) {
            return String.format("No classes found for academic year: %s", academicYear);
        }
        
        // Calculate statistics
        int totalClasses = classes.size();
        int totalCapacity = getTotalCapacityForYear(academicYear, classDatabase);
        double averageCapacity = (double) totalCapacity / totalClasses;
        
        int minCapacity = Integer.MAX_VALUE;
        int maxCapacity = Integer.MIN_VALUE;
        
        for (Class classObj : classes) {
            int capacity = classObj.getCapacity();
            minCapacity = Math.min(minCapacity, capacity);
            maxCapacity = Math.max(maxCapacity, capacity);
        }
        
        // Generate report
        StringBuilder report = new StringBuilder();
        report.append(String.format("=== Class Statistics for Academic Year: %s ===\n", academicYear));
        report.append(String.format("Total Classes: %d\n", totalClasses));
        report.append(String.format("Total Student Capacity: %d\n", totalCapacity));
        report.append(String.format("Average Class Capacity: %.1f\n", averageCapacity));
        report.append(String.format("Minimum Class Capacity: %d\n", minCapacity));
        report.append(String.format("Maximum Class Capacity: %d\n", maxCapacity));
        
        return report.toString();
    }
    
    /**
     * Checks if a class exists in the database for a given academic year.
     * 
     * @param classCode The class code to check
     * @param academicYear The academic year to check in
     * @param classDatabase The database containing classes
     * @return true if the class exists, false otherwise
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public static boolean classExists(String classCode, String academicYear, 
                                      Map<String, List<Class>> classDatabase) {
        return searchClassByCode(classCode, academicYear, classDatabase) != null;
    }
    
    /**
     * Adds a new class to the database for a specific academic year.
     * 
     * @param newClass The class to add
     * @param academicYear The academic year to add the class to
     * @param classDatabase The database to add the class to
     * @return true if the class was added successfully, false if class already exists
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public static boolean addClassToYear(Class newClass, String academicYear,
                                         Map<String, List<Class>> classDatabase) {
        if (newClass == null) {
            throw new IllegalArgumentException("Class cannot be null");
        }
        
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year cannot be null or empty");
        }
        
        if (classDatabase == null) {
            throw new IllegalArgumentException("Class database cannot be null");
        }
        
        String trimmedYear = academicYear.trim();
        
        // Check if class already exists
        if (classExists(newClass.getCode(), trimmedYear, classDatabase)) {
            System.out.println("Class " + newClass.getCode() + " already exists in academic year " + trimmedYear);
            return false;
        }
        
        // Get or create the list for this academic year
        List<Class> yearClasses = classDatabase.get(trimmedYear);
        if (yearClasses == null) {
            yearClasses = new ArrayList<>();
            classDatabase.put(trimmedYear, yearClasses);
        }
        
        // Add the new class
        yearClasses.add(new Class(newClass)); // Add a copy to prevent external modification
        System.out.println("Class " + newClass.getCode() + " added successfully to academic year " + trimmedYear);
        
        return true;
    }
}