package com.example.notessystem.service;

import com.example.notessystem.domain.Student;
import com.example.notessystem.infrastructure.Database; // Assuming StudentService also uses the database

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service Layer for Student Management.
 * Added to satisfy requirement R2 from the class diagram notes.
 * This is a placeholder for actual student management logic.
 */
public class StudentService {
    private Database database; // Assuming StudentService might also interact with the Database

    /**
     * Constructs a new StudentService with a reference to the database.
     * @param database The database instance to query student information.
     */
    public StudentService(Database database) {
        this.database = database;
    }

    /**
     * Retrieves student information by their ID.
     * @param studentId The unique identifier of the student.
     * @return A Student object if found, otherwise null.
     */
    public Student getStudentById(String studentId) {
        System.out.println("StudentService: Retrieving student with ID: " + studentId);
        // Simulate fetching from database
        String sqlQuery = "SELECT * FROM students WHERE studentId = '" + studentId + "'";
        List<Map<String, Object>> studentData = database.executeQuery(sqlQuery);

        if (!studentData.isEmpty()) {
            Map<String, Object> row = studentData.get(0);
            return new Student((String) row.get("studentId"), (String) row.get("name"));
        }
        return null; // Student not found
    }

    // ... other student management methods
}