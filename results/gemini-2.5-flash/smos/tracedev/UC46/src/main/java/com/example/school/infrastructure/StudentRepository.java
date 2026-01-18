package com.example.school.infrastructure;

import com.example.school.domain.Student;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IStudentRepository using a DatabaseContext.
 */
public class StudentRepository implements IStudentRepository {
    private DatabaseContext dbContext;

    /**
     * Constructs a StudentRepository with a given DatabaseContext.
     * @param dbContext The database context to use for persistence.
     */
    public StudentRepository(DatabaseContext dbContext) {
        this.dbContext = dbContext;
    }

    /**
     * Finds all students enrolled in a specific class.
     * Simulates a database query.
     * @param classId The ID of the class.
     * @return A list of Student objects.
     */
    @Override
    public List<Student> findStudentsByClass(String classId) {
        // Simulate SQL query: SELECT * FROM Students WHERE classId = classId
        String sql = "SELECT * FROM Students WHERE classId = '" + classId + "'";
        dbContext.executeQuery(sql); // Log the query

        return dbContext.studentsTable.stream()
                .filter(s -> classId.equals(s.get("classId")))
                .map(this::mapToStudent)
                .collect(Collectors.toList());
    }

    /**
     * Finds a student by their unique identifier.
     * Simulates a database query.
     * @param studentId The ID of the student.
     * @return The Student object if found, null otherwise.
     */
    @Override
    public Student findStudentById(String studentId) {
        // Simulate SQL query: SELECT * FROM Students WHERE id = studentId
        String sql = "SELECT * FROM Students WHERE id = '" + studentId + "'";
        dbContext.executeQuery(sql); // Log the query

        Optional<Map<String, Object>> studentRow = dbContext.studentsTable.stream()
                .filter(s -> studentId.equals(s.get("id")))
                .findFirst();

        return studentRow.map(this::mapToStudent).orElse(null);
    }

    /**
     * Helper method to map a database row (Map<String, Object>) to a Student object.
     * @param row The map representing a database row.
     * @return A Student object.
     */
    private Student mapToStudent(Map<String, Object> row) {
        return new Student(
            (String) row.get("id"),
            (String) row.get("name"),
            (String) row.get("classId")
        );
    }
}