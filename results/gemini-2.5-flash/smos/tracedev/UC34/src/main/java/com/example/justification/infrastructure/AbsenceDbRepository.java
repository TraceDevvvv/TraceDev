package com.example.justification.infrastructure;

import com.example.justification.domain.model.Absence;
import com.example.justification.domain.repository.AbsenceRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Calendar;

/**
 * Concrete implementation of AbsenceRepository for database access.
 * Currently simulates database interaction with in-memory data.
 */
public class AbsenceDbRepository implements AbsenceRepository {

    // Simulates a database table with pre-defined absence records
    private final List<Absence> mockDatabase;

    /**
     * Constructor for AbsenceDbRepository. Initializes mock data.
     */
    public AbsenceDbRepository() {
        this.mockDatabase = new ArrayList<>();
        // Populate with mock data for demonstration
        Calendar cal = Calendar.getInstance();

        // Absences for student 's123'
        cal.set(2023, Calendar.SEPTEMBER, 15); // Sept 15, 2023
        mockDatabase.add(new Absence("A001", "s123", cal.getTime(), "Flu", true, "Doctor's note provided"));

        cal.set(2023, Calendar.OCTOBER, 20); // Oct 20, 2023
        mockDatabase.add(new Absence("A002", "s123", cal.getTime(), "Family event", false, null));

        cal.set(2024, Calendar.FEBRUARY, 10); // Feb 10, 2024
        mockDatabase.add(new Absence("A003", "s123", cal.getTime(), "Dental appointment", true, "Dentist certificate"));

        cal.set(2024, Calendar.MARCH, 5); // March 5, 2024
        mockDatabase.add(new Absence("A004", "s123", cal.getTime(), "Headache", false, null));

        // Absences for another student 's456'
        cal.set(2023, Calendar.NOVEMBER, 1); // Nov 1, 2023
        mockDatabase.add(new Absence("A005", "s456", cal.getTime(), "Cold", true, "Parental note"));
    }

    /**
     * Finds all absences for a given student within a specific year by filtering mock data.
     * This method simulates a database query.
     *
     * @param studentId The ID of the student.
     * @param year The academic year to search within.
     * @return A list of Absence objects matching the criteria.
     */
    @Override
    public List<Absence> findByStudentIdAndYear(String studentId, int year) {
        System.out.println("    [AbsenceRepo] Searching database for studentId: " + studentId + " in year: " + year);
        // Simulate database query delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Filter mock database records
        return mockDatabase.stream()
                .filter(absence -> absence.getStudentId().equals(studentId))
                .filter(absence -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(absence.getDate());
                    return cal.get(Calendar.YEAR) == year;
                })
                .collect(Collectors.toList());
    }
}