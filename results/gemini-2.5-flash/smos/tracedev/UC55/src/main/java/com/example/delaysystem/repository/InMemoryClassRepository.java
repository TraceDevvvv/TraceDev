package com.example.delaysystem.repository;

import com.example.delaysystem.model.ClassSession;
import com.example.delaysystem.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete in-memory implementation of IClassRepository for demonstration purposes.
 * Stores class and student data in maps.
 */
public class InMemoryClassRepository implements IClassRepository {
    // Map to store class sessions, keyed by classId
    private Map<String, ClassSession> classSessions = new HashMap<>();

    /**
     * Constructs a new InMemoryClassRepository and initializes it with sample data.
     */
    public InMemoryClassRepository() {
        // Initialize with some dummy data
        ClassSession cs101 = new ClassSession("CS101", "Introduction to Computer Science");
        cs101.addStudent(new Student("S001", "Alice Smith"));
        cs101.addStudent(new Student("S002", "Bob Johnson"));
        cs101.addStudent(new Student("S003", "Charlie Brown"));
        classSessions.put("CS101", cs101);

        ClassSession math201 = new ClassSession("MATH201", "Calculus I");
        math201.addStudent(new Student("S004", "David Lee"));
        math201.addStudent(new Student("S001", "Alice Smith")); // Alice is also in MATH201
        classSessions.put("MATH201", math201);

        System.out.println("[InMemoryClassRepo] Initialized with sample data for " + classSessions.size() + " classes.");
    }

    /**
     * Retrieves a list of students for a given class ID.
     *
     * @param classId The unique identifier of the class.
     * @return A list of Student objects, or an empty list if the class is not found.
     */
    @Override
    public List<Student> getStudentsForClass(String classId) {
        System.out.println("[InMemoryClassRepo] Fetching students for class: " + classId);
        ClassSession session = classSessions.get(classId);
        if (session != null) {
            return new ArrayList<>(session.getStudents()); // Return a new list to prevent external modification
        }
        return new ArrayList<>(); // Return empty list if class not found
    }

    /**
     * Retrieves a ClassSession object for a given class ID.
     *
     * @param classId The unique identifier of the class.
     * @return The ClassSession object, or null if not found.
     */
    @Override
    public ClassSession getClassSession(String classId) {
        System.out.println("[InMemoryClassRepo] Fetching class session for class: " + classId);
        return classSessions.get(classId);
    }
}