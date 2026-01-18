package com.example.infrastructure.persistence;

import com.example.application.ports.StudentRepository;
import com.example.domain.Student;
import java.sql.Connection;

/**
 * Implementation of StudentRepository using a database connection.
 */
public class StudentRepositoryImpl implements StudentRepository {
    private Connection databaseConnection;

    public StudentRepositoryImpl(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Student findById(String id) {
        // Simulate database SELECT
        System.out.println("StudentRepositoryImpl: SELECT student WHERE id = " + id);
        // Return a dummy student for illustration
        return new Student(id, "John Doe", "parent@example.com");
    }

    // New method for sequence diagram message
    public void selectStudentWhereId(String id) {
        System.out.println("StudentRepositoryImpl: SELECT student WHERE id = " + id);
    }
}