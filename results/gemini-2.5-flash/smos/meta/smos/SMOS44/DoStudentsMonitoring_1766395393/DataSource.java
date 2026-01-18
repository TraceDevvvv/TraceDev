package com.school.monitoring.data;

import com.school.monitoring.model.Absence;
import com.school.monitoring.model.Note;
import com.school.monitoring.model.SchoolYear;
import com.school.monitoring.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simulates a data source for students, absences, and notes.
 * In a real application, this would interact with a database or external system.
 * For this use case, it provides hardcoded mock data.
 */
public class DataSource {

    private final List<Student> students;
    private final List<Absence> absences;
    private final List<Note> notes;
    private final SchoolYear currentSchoolYear;

    /**
     * Constructs a new DataSource and initializes it with mock data.
     */
    public DataSource() {
        this.students = new ArrayList<>();
        this.absences = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.currentSchoolYear = new SchoolYear("2023-2024"); // Define the current school year

        initializeMockData();
    }

    /**
     * Initializes the mock data for students, absences, and notes.
     * This method populates the lists with sample data to simulate a real system.
     */
    private void initializeMockData() {
        // --- Students ---
        Student s1 = new Student("S001", "Alice", "Smith");
        Student s2 = new Student("S002", "Bob", "Johnson");
        Student s3 = new Student("S003", "Charlie", "Brown");
        Student s4 = new Student("S004", "Diana", "Prince");
        Student s5 = new Student("S005", "Eve", "Adams");

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);

        // --- Absences for current school year (2023-2024) ---
        // Alice: 6 absences (exceeds threshold of 5)
        absences.add(new Absence(s1.getStudentId(), LocalDate.of(2023, 10, 5), "Flu", currentSchoolYear));
        absences.add(new Absence(s1.getStudentId(), LocalDate.of(2023, 10, 6), "Flu", currentSchoolYear));
        absences.add(new Absence(s1.getStudentId(), LocalDate.of(2023, 11, 1), "Appointment", currentSchoolYear));
        absences.add(new Absence(s1.getStudentId(), LocalDate.of(2024, 1, 15), "Sick", currentSchoolYear));
        absences.add(new Absence(s1.getStudentId(), LocalDate.of(2024, 2, 20), "Family event", currentSchoolYear));
        absences.add(new Absence(s1.getStudentId(), LocalDate.of(2024, 3, 10), "Unexcused", currentSchoolYear));

        // Bob: 3 absences (does not exceed threshold of 5)
        absences.add(new Absence(s2.getStudentId(), LocalDate.of(2023, 9, 10), "Cold", currentSchoolYear));
        absences.add(new Absence(s2.getStudentId(), LocalDate.of(2024, 1, 25), "Doctor", currentSchoolYear));
        absences.add(new Absence(s2.getStudentId(), LocalDate.of(2024, 3, 5), "Sick", currentSchoolYear));

        // Charlie: 7 absences (exceeds threshold of 5)
        absences.add(new Absence(s3.getStudentId(), LocalDate.of(2023, 9, 1), "Travel", currentSchoolYear));
        absences.add(new Absence(s3.getStudentId(), LocalDate.of(2023, 9, 2), "Travel", currentSchoolYear));
        absences.add(new Absence(s3.getStudentId(), LocalDate.of(2023, 10, 10), "Sick", currentSchoolYear));
        absences.add(new Absence(s3.getStudentId(), LocalDate.of(2023, 11, 11), "Sick", currentSchoolYear));
        absences.add(new Absence(s3.getStudentId(), LocalDate.of(2024, 1, 1), "Holiday", currentSchoolYear));
        absences.add(new Absence(s3.getStudentId(), LocalDate.of(2024, 2, 2), "Unexcused", currentSchoolYear));
        absences.add(new Absence(s3.getStudentId(), LocalDate.of(2024, 3, 3), "Unexcused", currentSchoolYear));

        // Diana: 2 absences
        absences.add(new Absence(s4.getStudentId(), LocalDate.of(2023, 12, 1), "Dentist", currentSchoolYear));
        absences.add(new Absence(s4.getStudentId(), LocalDate.of(2024, 2, 14), "Sick", currentSchoolYear));

        // Eve: 0 absences

        // --- Notes for current school year (2023-2024) ---
        // Alice: 4 notes (exceeds threshold of 3)
        notes.add(new Note(s1.getStudentId(), "Math", 75.0, LocalDate.of(2023, 10, 15), currentSchoolYear));
        notes.add(new Note(s1.getStudentId(), "Science", 80.0, LocalDate.of(2023, 11, 10), currentSchoolYear));
        notes.add(new Note(s1.getStudentId(), "History", 60.0, LocalDate.of(2024, 1, 20), currentSchoolYear));
        notes.add(new Note(s1.getStudentId(), "English", 55.0, LocalDate.of(2024, 3, 1), currentSchoolYear)); // Low note

        // Bob: 2 notes (does not exceed threshold of 3)
        notes.add(new Note(s2.getStudentId(), "Math", 90.0, LocalDate.of(2023, 10, 20), currentSchoolYear));
        notes.add(new Note(s2.getStudentId(), "Science", 85.0, LocalDate.of(2024, 2, 1), currentSchoolYear));

        // Charlie: 5 notes (exceeds threshold of 3)
        notes.add(new Note(s3.getStudentId(), "Math", 40.0, LocalDate.of(2023, 9, 20), currentSchoolYear)); // Very low note
        notes.add(new Note(s3.getStudentId(), "Science", 50.0, LocalDate.of(2023, 10, 25), currentSchoolYear));
        notes.add(new Note(s3.getStudentId(), "History", 65.0, LocalDate.of(2023, 12, 5), currentSchoolYear));
        notes.add(new Note(s3.getStudentId(), "English", 70.0, LocalDate.of(2024, 1, 10), currentSchoolYear));
        notes.add(new Note(s3.getStudentId(), "Art", 80.0, LocalDate.of(2024, 3, 15), currentSchoolYear));

        // Diana: 3 notes (meets threshold of 3)
        notes.add(new Note(s4.getStudentId(), "Math", 95.0, LocalDate.of(2023, 11, 15), currentSchoolYear));
        notes.add(new Note(s4.getStudentId(), "Science", 92.0, LocalDate.of(2024, 1, 5), currentSchoolYear));
        notes.add(new Note(s4.getStudentId(), "English", 88.0, LocalDate.of(2024, 2, 25), currentSchoolYear));

        // Eve: 1 note
        notes.add(new Note(s5.getStudentId(), "PE", 70.0, LocalDate.of(2023, 12, 10), currentSchoolYear));

        // --- Absences and Notes for a different school year (2022-2023) to ensure filtering works ---
        SchoolYear previousSchoolYear = new SchoolYear("2022-2023");
        absences.add(new Absence(s1.getStudentId(), LocalDate.of(2022, 9, 1), "Old absence", previousSchoolYear));
        notes.add(new Note(s1.getStudentId(), "Old Subject", 88.0, LocalDate.of(2022, 10, 1), previousSchoolYear));
    }

    /**
     * Retrieves all students from the data source.
     *
     * @return A list of all Student objects.
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // Return a copy to prevent external modification
    }

    /**
     * Retrieves absence records for a specific school year.
     *
     * @param schoolYear The school year to filter absences by.
     * @return A list of Absence objects for the given school year.
     */
    public List<Absence> getAbsencesForSchoolYear(SchoolYear schoolYear) {
        if (schoolYear == null) {
            throw new IllegalArgumentException("SchoolYear cannot be null.");
        }
        return absences.stream()
                .filter(absence -> absence.getSchoolYear().equals(schoolYear))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves note records for a specific school year.
     *
     * @param schoolYear The school year to filter notes by.
     * @return A list of Note objects for the given school year.
     */
    public List<Note> getNotesForSchoolYear(SchoolYear schoolYear) {
        if (schoolYear == null) {
            throw new IllegalArgumentException("SchoolYear cannot be null.");
        }
        return notes.stream()
                .filter(note -> note.getSchoolYear().equals(schoolYear))
                .collect(Collectors.toList());
    }

    /**
     * Returns the currently defined school year.
     *
     * @return The current SchoolYear object.
     */
    public SchoolYear getCurrentSchoolYear() {
        return currentSchoolYear;
    }
}