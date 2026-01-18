package com.example.editabsence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Manages the business logic for student absences.
 * This class handles adding, removing, and retrieving absence records for students.
 * It acts as a central repository for student and absence data within the application's logic layer.
 */
public class AbsenceManager {
    // Stores students, keyed by student ID for quick lookup.
    private final Map<String, Student> students;

    /**
     * Constructs a new AbsenceManager.
     * Initializes the internal storage for students.
     */
    public AbsenceManager() {
        this.students = new HashMap<>();
    }

    /**
     * Adds a new student to the system.
     *
     * @param student The Student object to add.
     * @return true if the student was added successfully, false if a student with the same ID already exists.
     * @throws IllegalArgumentException if the student object is null.
     */
    public boolean addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        // Check if a student with the same ID already exists to prevent duplicates.
        if (students.containsKey(student.getStudentId())) {
            return false; // Student with this ID already exists
        }
        students.put(student.getStudentId(), student);
        return true;
    }

    /**
     * Retrieves a student by their unique ID.
     *
     * @param studentId The unique identifier of the student.
     * @return An Optional containing the Student object if found, or an empty Optional if no student with the given ID exists.
     * @throws IllegalArgumentException if the studentId is null or empty.
     */
    public Optional<Student> getStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        return Optional.ofNullable(students.get(studentId));
    }

    /**
     * Adds an absence record for a specific student.
     * This method encapsulates the logic for creating an Absence object and adding it to the student's record.
     *
     * @param studentId The ID of the student for whom the absence is being recorded.
     * @param date      The date of the absence.
     * @param type      The type of absence (e.g., JUSTIFIED, UNJUSTIFIED).
     * @param reason    An optional reason or description for the absence. Can be null or empty.
     * @return true if the absence was added successfully, false if the student was not found,
     *         or if an identical absence (same date and type) already exists for the student.
     * @throws IllegalArgumentException if studentId, date, or type is null.
     */
    public boolean addAbsenceRecord(String studentId, LocalDate date, Absence.AbsenceType type, String reason) {
        if (studentId == null || date == null || type == null) {
            throw new IllegalArgumentException("Student ID, date, and absence type cannot be null.");
        }

        Optional<Student> studentOpt = getStudent(studentId);
        if (studentOpt.isEmpty()) {
            return false; // Student not found, cannot add absence
        }

        Student student = studentOpt.get();
        // Create a new Absence object. The Absence constructor handles validation of its own fields.
        Absence newAbsence = new Absence(studentId, date, type, reason);
        // Delegate the actual addition and duplicate check to the Student object.
        return student.addAbsence(newAbsence);
    }

    /**
     * Removes an absence record for a specific student.
     * The absence is identified by its student ID, date, and type.
     *
     * @param studentId The ID of the student from whom the absence is being removed.
     * @param date      The date of the absence to remove.
     * @param type      The type of the absence to remove.
     * @return true if the absence was removed successfully, false if the student was not found,
     *         or if the specified absence (date and type) was not found for the student.
     * @throws IllegalArgumentException if studentId, date, or type is null.
     */
    public boolean removeAbsenceRecord(String studentId, LocalDate date, Absence.AbsenceType type) {
        if (studentId == null || date == null || type == null) {
            throw new IllegalArgumentException("Student ID, date, and absence type cannot be null.");
        }

        Optional<Student> studentOpt = getStudent(studentId);
        if (studentOpt.isEmpty()) {
            return false; // Student not found, cannot remove absence
        }

        Student student = studentOpt.get();
        // Create a temporary Absence object for comparison and removal.
        // The reason is not considered in the Absence.equals() method, so an empty string is fine.
        Absence absenceToRemove = new Absence(studentId, date, type, "");
        // Delegate the actual removal to the Student object.
        return student.removeAbsence(absenceToRemove);
    }

    /**
     * Retrieves all absence records for a specific student on a given date.
     * This is useful for displaying existing absences on the UI for a selected date.
     *
     * @param studentId The ID of the student.
     * @param date      The date to filter absences by.
     * @return A list of Absence objects for the specified student and date.
     *         Returns an empty list if the student is not found or if no absences exist on that date.
     * @throws IllegalArgumentException if studentId or date is null.
     */
    public List<Absence> getAbsencesForStudentOnDate(String studentId, LocalDate date) {
        if (studentId == null || date == null) {
            throw new IllegalArgumentException("Student ID and date cannot be null.");
        }

        Optional<Student> studentOpt = getStudent(studentId);
        if (studentOpt.isEmpty()) {
            return new ArrayList<>(); // Student not found, return empty list
        }

        Student student = studentOpt.get();
        List<Absence> filteredAbsences = new ArrayList<>();
        // Iterate through all absences of the student and filter by date.
        for (Absence absence : student.getAbsences()) {
            if (absence.getDate().equals(date)) {
                filteredAbsences.add(absence);
            }
        }
        return filteredAbsences;
    }

    /**
     * Retrieves all absence records for a specific student, regardless of date.
     *
     * @param studentId The ID of the student.
     * @return A list of all Absence objects for the specified student.
     *         Returns an empty list if the student is not found.
     * @throws IllegalArgumentException if studentId is null.
     */
    public List<Absence> getAllAbsencesForStudent(String studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null.");
        }

        Optional<Student> studentOpt = getStudent(studentId);
        if (studentOpt.isEmpty()) {
            return new ArrayList<>(); // Student not found, return empty list
        }
        // Return a new ArrayList to ensure the internal list of the Student object is not directly modified externally.
        return new ArrayList<>(studentOpt.get().getAbsences());
    }
}