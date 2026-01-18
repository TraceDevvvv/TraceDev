package infrastructure.repository;

import infrastructure.dao.StudentDAO;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IStudentRepository.
 * This class simulates interaction with a database to retrieve student data.
 */
public class StudentRepository implements IStudentRepository {

    // Dummy data to simulate database records
    private final List<StudentDAO> students = Arrays.asList(
        new StudentDAO("S001", "Alice Smith", "C001"), // 10A
        new StudentDAO("S002", "Bob Johnson", "C001"), // 10A
        new StudentDAO("S003", "Charlie Brown", "C002"), // 10B
        new StudentDAO("S004", "Diana Prince", "C003"), // 11A
        new StudentDAO("S005", "Clark Kent", "C001")  // 10A
    );

    /**
     * Finds students by class ID.
     * @param classId The ID of the class.
     * @return A list of StudentDAO objects filtered by class.
     */
    @Override
    public List<StudentDAO> findByClass(String classId) {
        System.out.println("Database: SELECT student_id, name FROM students WHERE class_id = " + classId); // Simulate DB call
        return students.stream()
                       .filter(s -> s.getClassId().equals(classId))
                       .collect(Collectors.toList());
    }

    /**
     * Finds a student by their ID.
     * @param studentId The unique identifier of the student.
     * @return The StudentDAO object if found, otherwise null.
     */
    @Override
    public StudentDAO findById(String studentId) {
        return students.stream()
                .filter(s -> s.getId().equals(studentId))
                .findFirst()
                .orElse(null);
    }
}