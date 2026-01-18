package com.example.viewstudentdata;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections; // For unmodifiable lists

/**
 * Manages student-related data, including parents, students, and their academic/behavioral records.
 * Provides methods to retrieve and verify data, and initializes mock data for demonstration purposes.
 */
public class StudentService {
    // Stores parents, keyed by parentId
    private Map<String, Parent> parents;
    // Stores students, keyed by studentId
    private Map<String, Student> students;
    // Stores student records, keyed by studentId, with a list of records for that student
    private Map<String, List<StudentRecord>> studentRecords;

    /**
     * Constructs a new StudentService and initializes the internal data structures.
     */
    public StudentService() {
        this.parents = new HashMap<>();
        this.students = new HashMap<>();
        this.studentRecords = new HashMap<>();
    }

    /**
     * Initializes mock data for parents, students, and student records.
     * This method simulates data retrieval from a persistent storage.
     */
    public void initMockData() {
        // --- Mock Parents ---
        Parent parent1 = new Parent("parent123", "john.doe", "password123", Arrays.asList("student101", "student102"));
        Parent parent2 = new Parent("parent456", "jane.smith", "securepass", Arrays.asList("student103"));
        parents.put(parent1.getParentId(), parent1);
        parents.put(parent2.getParentId(), parent2);

        // --- Mock Students ---
        Student student1 = new Student("student101", "Alice Doe", "parent123");
        Student student2 = new Student("student102", "Bob Doe", "parent123");
        Student student3 = new Student("student103", "Charlie Smith", "parent456");
        students.put(student1.getStudentId(), student1);
        students.put(student2.getStudentId(), student2);
        students.put(student3.getStudentId(), student3);

        // --- Mock Student Records ---
        // Records for student101 (Alice Doe)
        addStudentRecord("record001", "student101", LocalDate.of(2023, 10, 26), 0, "Excellent participation.", 0, "N/A");
        addStudentRecord("record002", "student101", LocalDate.of(2023, 10, 27), 1, "Felt unwell.", 0, "Doctor's note provided.");
        addStudentRecord("record003", "student101", LocalDate.of(2023, 10, 28), 0, "Minor disruption in class.", 1, "Traffic delay.");

        // Records for student102 (Bob Doe)
        addStudentRecord("record004", "student102", LocalDate.of(2023, 10, 26), 0, "Good behavior.", 0, "N/A");
        addStudentRecord("record005", "student102", LocalDate.of(2023, 10, 27), 0, "Warning for talking during lecture.", 0, "N/A");

        // Records for student103 (Charlie Smith)
        addStudentRecord("record006", "student103", LocalDate.of(2023, 10, 26), 0, "Completed all assignments.", 0, "N/A");
        addStudentRecord("record007", "student103", LocalDate.of(2023, 10, 27), 0, "N/A", 1, "Overslept.");
    }

    /**
     * Helper method to add a student record to the map.
     *
     * @param recordId          The unique identifier for this record.
     * @param studentId         The ID of the student this record belongs to.
     * @param date              The date to which this record pertains.
     * @param absences          The number of absences recorded for the student on this date.
     * @param disciplinaryNotes Any disciplinary notes for the student on this date.
     * @param delays            The number of delays (tardiness) recorded for the student on this date.
     * @param justification     Any justification provided for absences or delays.
     */
    private void addStudentRecord(String recordId, String studentId, LocalDate date, int absences,
                                  String disciplinaryNotes, int delays, String justification) {
        StudentRecord record = new StudentRecord(recordId, studentId, date, absences, disciplinaryNotes, delays, justification);
        studentRecords.computeIfAbsent(studentId, k -> new ArrayList<>()).add(record);
    }

    /**
     * Retrieves a Parent object by their unique ID.
     *
     * @param parentId The ID of the parent to retrieve.
     * @return The Parent object if found, otherwise null.
     */
    public Parent getParentById(String parentId) {
        return parents.get(parentId);
    }

    /**
     * Retrieves a Student object by their unique ID.
     *
     * @param studentId The ID of the student to retrieve.
     * @return The Student object if found, otherwise null.
     */
    public Student getStudentById(String studentId) {
        return students.get(studentId);
    }

    /**
     * Checks if a given student ID belongs to a specific parent.
     * This is a crucial authorization check.
     *
     * @param parentId  The ID of the parent.
     * @param studentId The ID of the student to check.
     * @return true if the student is a child of the parent, false otherwise.
     */
    public boolean isChildOfParent(String parentId, String studentId) {
        Parent parent = getParentById(parentId);
        if (parent == null) {
            System.err.println("Error: Parent with ID " + parentId + " not found.");
            return false;
        }
        // Check if the studentId is in the parent's list of children
        return parent.getChildrenStudentIds().contains(studentId);
    }

    /**
     * Retrieves a list of student records for a specific child belonging to a specific parent.
     * This method includes an authorization check to ensure the parent can view the child's data.
     *
     * @param parentId  The ID of the logged-in parent.
     * @param studentId The ID of the child whose records are to be retrieved.
     * @return An unmodifiable list of StudentRecord objects for the specified child,
     *         or an empty list if the child does not belong to the parent or no records are found.
     */
    public List<StudentRecord> getStudentRecordsForParentAndChild(String parentId, String studentId) {
        // First, verify that the selected child actually belongs to the logged-in parent
        if (!isChildOfParent(parentId, studentId)) {
            System.err.println("Access denied: Student " + studentId + " is not associated with parent " + parentId + ".");
            return Collections.emptyList(); // Return an empty list if unauthorized
        }

        // If authorized, retrieve the records
        List<StudentRecord> records = studentRecords.get(studentId);
        return records != null ? Collections.unmodifiableList(records) : Collections.emptyList();
    }
}