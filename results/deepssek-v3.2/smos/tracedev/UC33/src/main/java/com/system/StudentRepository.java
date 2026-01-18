package com.system;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Repository for managing Student entities.
 */
public class StudentRepository implements IRepository<Student> {
    // In-memory storage for demonstration.
    private List<Student> students = new ArrayList<>();

    public StudentRepository() {
        // Populate with sample data for testing.
        students.add(new Student("S001", "Alice Johnson", true));
        students.add(new Student("S002", "Bob Smith", false));
        students.add(new Student("S003", "Charlie Brown", true));
    }

    /**
     * Finds all students with entries for a given date.
     * (Assumption: In this simulation, all students are returned regardless of date.)
     * @param date the date to filter by.
     * @return list of students.
     */
    public List<Student> findAllByDate(Date date) {
        // Simplified: return all students, ignoring date.
        System.out.println("StudentRepository: Finding all students for date " + date);
        return new ArrayList<>(students);
    }

    @Override
    public void save(Student student) {
        // Update existing or add new.
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(student.getStudentId())) {
                students.set(i, student);
                System.out.println("StudentRepository: Updated student " + student.getStudentId());
                return;
            }
        }
        students.add(student);
        System.out.println("StudentRepository: Added new student " + student.getStudentId());
    }

    @Override
    public void delete(Student student) {
        students.removeIf(s -> s.getStudentId().equals(student.getStudentId()));
        System.out.println("StudentRepository: Deleted student " + student.getStudentId());
    }

    @Override
    public Student findById(String id) {
        return students.stream()
                .filter(s -> s.getStudentId().equals(id))
                .findFirst()
                .orElse(null);
    }
}