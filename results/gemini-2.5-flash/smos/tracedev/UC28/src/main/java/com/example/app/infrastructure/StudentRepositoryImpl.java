package com.example.app.infrastructure;

import com.example.app.domain.Student;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


public class StudentRepositoryImpl implements IStudentRepository {
    private final Map<String, Student> students = new HashMap<>();
    // Map to associate registers with students (many-to-many simplified for mock)
    private final Map<String, List<String>> registerStudents = new HashMap<>(); // registerId -> List<studentId>

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public StudentRepositoryImpl() {
        // Populate with some mock data
        try {
            Student s1 = new Student("S001", "Alice Smith", dateFormat.parse("2007-03-01"));
            Student s2 = new Student("S002", "Bob Johnson", dateFormat.parse("2007-05-10"));
            Student s3 = new Student("S003", "Charlie Brown", dateFormat.parse("2006-11-20"));
            Student s4 = new Student("S004", "Diana Prince", dateFormat.parse("2007-07-25"));

            students.put(s1.getId(), s1);
            students.put(s2.getId(), s2);
            students.put(s3.getId(), s3);
            students.put(s4.getId(), s4);

            registerStudents.put("REG001", Arrays.asList("S001", "S002", "S004"));
            registerStudents.put("REG002", Arrays.asList("S001", "S003"));

        } catch (ParseException e) {
            System.err.println("Error parsing mock date in StudentRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public List<Student> findByRegisterId(String registerId) {
        // Simulates fetching from a database
        System.out.println("DB: SELECT student_details FROM Students WHERE registerId = " + registerId);
        List<String> studentIds = registerStudents.getOrDefault(registerId, new ArrayList<>());
        return studentIds.stream()
                         .map(students::get)
                         .collect(Collectors.toList());
    }
}