package repository;

import domain.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of IStudentRepository.
 * This class simulates a data store for Student entities.
 */
public class StudentRepository implements IStudentRepository {

    // A simple in-memory map to simulate a database for demonstration purposes.
    private final Map<String, Student> studentsStore = new HashMap<>();

    public StudentRepository() {
        // Initialize with some dummy data
        studentsStore.put("student101", new Student("student101", "Alice Smith"));
        studentsStore.put("student102", new Student("student102", "Bob Johnson"));
        studentsStore.put("student103", new Student("student103", "Charlie Brown"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Student getStudentById(String studentId) {
        System.out.println("[StudentRepository] Retrieving student with ID: " + studentId);
        return studentsStore.get(studentId);
    }
}