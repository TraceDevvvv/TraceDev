'''
Service class for managing student data (simulated from a database).
This class provides methods to access and retrieve student information.
'''
package com.chatdev.absencetracker.service;
import com.chatdev.absencetracker.model.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public class StudentService {
    // In-memory storage for students, mapped by their ID. Simulates a database table.
    private final Map<String, Student> students = new HashMap<>();
    '''
    Constructs a new StudentService and loads initial dummy student data.
    '''
    public StudentService() {
        loadStudents();
    }
    '''
    Loads dummy student data into the service's in-memory storage.
    In a real application, this would fetch data from a persistent data store
    like a database or an external API.
    '''
    private void loadStudents() {
        students.put("S001", new Student("S001", "Alice Smith", "alice.parent@example.com"));
        students.put("S002", new Student("S002", "Bob Johnson", "bob.parent@example.com"));
        students.put("S003", new Student("S003", "Charlie Brown", "charlie.parent@example.com"));
        students.put("S004", new Student("S004", "Diana Prince", "diana.parent@example.com"));
        students.put("S005", new Student("S005", "Eve Adams", "eve.parent@example.com"));
        students.put("S006", new Student("S006", "Frank White", "frank.parent@example.com"));
        students.put("S007", new Student("S007", "Grace Lee", "grace.parent@example.com"));
    }
    '''
    Retrieves a student by their unique ID.
    @param id The ID of the student to retrieve.
    @return An Optional containing the Student object if found, or an empty Optional otherwise.
    '''
    public Optional<Student> getStudentById(String id) {
        return Optional.ofNullable(students.get(id));
    }
    '''
    Retrieves an unmodifiable list of all registered students.
    @return An unmodifiable List of Student objects.
    '''
    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(new ArrayList<>(students.values()));
    }
}