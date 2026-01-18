package com.school.monitoring.service;

import com.school.monitoring.data.DataSource;
import com.school.monitoring.model.Absence;
import com.school.monitoring.model.Note;
import com.school.monitoring.model.SchoolYear;
import com.school.monitoring.model.Student;
import com.school.monitoring.model.StudentMonitoringReportEntry;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class responsible for implementing the business logic for student monitoring.
 * It interacts with the DataSource to retrieve student, absence, and note information,
 * and processes this data to identify students who meet specific monitoring criteria.
 */
public class StudentMonitoringService {

    private final DataSource dataSource;

    /**
     * Constructs a new StudentMonitoringService with a given DataSource.
     *
     * @param dataSource The data source to retrieve student, absence, and note data from.
     * @throws IllegalArgumentException if the dataSource is null.
     */
    public StudentMonitoringService(DataSource dataSource) {
        if (dataSource == null) {
            throw new IllegalArgumentException("DataSource cannot be null.");
        }
        this.dataSource = dataSource;
    }

    /**
     * Queries the system for students who have, for the current school year,
     * a number of absences and notes higher than a predetermined threshold.
     *
     * @param absenceThreshold The minimum number of absences a student must have to be included.
     * @param noteThreshold    The minimum number of notes a student must have to be included.
     * @return A list of {@link StudentMonitoringReportEntry} for students exceeding both thresholds.
     *         Returns an empty list if no students meet the criteria.
     * @throws IllegalArgumentException if absenceThreshold or noteThreshold is negative.
     */
    public List<StudentMonitoringReportEntry> getStudentsExceedingThresholds(int absenceThreshold, int noteThreshold) {
        if (absenceThreshold < 0) {
            throw new IllegalArgumentException("Absence threshold cannot be negative.");
        }
        if (noteThreshold < 0) {
            throw new IllegalArgumentException("Note threshold cannot be negative.");
        }

        // Get the current school year from the data source
        SchoolYear currentSchoolYear = dataSource.getCurrentSchoolYear();

        // Retrieve all students
        List<Student> allStudents = dataSource.getAllStudents();

        // Retrieve all absences for the current school year
        List<Absence> currentYearAbsences = dataSource.getAbsencesForSchoolYear(currentSchoolYear);

        // Retrieve all notes for the current school year
        List<Note> currentYearNotes = dataSource.getNotesForSchoolYear(currentSchoolYear);

        // Group absences by student ID to count total absences per student
        Map<String, Long> studentAbsenceCounts = currentYearAbsences.stream()
                .collect(Collectors.groupingBy(Absence::getStudentId, Collectors.counting()));

        // Group notes by student ID to count total notes per student
        Map<String, Long> studentNoteCounts = currentYearNotes.stream()
                .collect(Collectors.groupingBy(Note::getStudentId, Collectors.counting()));

        // Filter students based on the thresholds and create report entries
        return allStudents.stream()
                .filter(student -> {
                    long absences = studentAbsenceCounts.getOrDefault(student.getStudentId(), 0L);
                    long notes = studentNoteCounts.getOrDefault(student.getStudentId(), 0L);
                    // A student is included if their absences are strictly greater than the absenceThreshold
                    // AND their notes are strictly greater than the noteThreshold.
                    return absences > absenceThreshold && notes > noteThreshold;
                })
                .map(student -> new StudentMonitoringReportEntry(
                        student.getStudentId(),
                        student.getFullName(),
                        studentAbsenceCounts.getOrDefault(student.getStudentId(), 0L).intValue(),
                        studentNoteCounts.getOrDefault(student.getStudentId(), 0L).intValue(),
                        currentSchoolYear.getYearIdentifier()
                ))
                .collect(Collectors.toList());
    }
}