package infrastructure.repository;

import infrastructure.dao.ClassDAO;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IClassRepository.
 * This class simulates interaction with a database to retrieve class data.
 */
public class ClassRepository implements IClassRepository {

    // Dummy data to simulate database records
    private final List<ClassDAO> classes = Arrays.asList(
        new ClassDAO("C001", "10A", "AY002"), // 2023-2024
        new ClassDAO("C002", "10B", "AY002"), // 2023-2024
        new ClassDAO("C003", "11A", "AY002"), // 2023-2024
        new ClassDAO("C004", "12A", "AY001"), // 2022-2023
        new ClassDAO("C005", "10A", "AY003")  // 2024-2025
    );

    /**
     * Finds classes by academic year ID.
     * @param yearId The ID of the academic year.
     * @return A list of ClassDAO objects filtered by academic year.
     */
    @Override
    public List<ClassDAO> findByAcademicYear(String yearId) {
        System.out.println("Database: SELECT class_id, name FROM classes WHERE academic_year_id = " + yearId); // Simulate DB call
        return classes.stream()
                      .filter(c -> c.getAcademicYearId().equals(yearId))
                      .collect(Collectors.toList());
    }

    /**
     * Finds a class by its ID.
     * @param classId The unique identifier of the class.
     * @return The ClassDAO object if found, otherwise null.
     */
    @Override
    public ClassDAO findById(String classId) {
        return classes.stream()
                .filter(c -> c.getId().equals(classId))
                .findFirst()
                .orElse(null);
    }
}