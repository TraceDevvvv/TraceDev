package com.example.absence.infrastructure;

import com.example.absence.domain.Absence;
import com.example.absence.observer.IAbsenceObserver;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of the absence repository.
 * Also notifies observers when absences are saved.
 */
public class AbsenceRepositoryImpl implements IAbsenceRepository {
    private List<IAbsenceObserver> observers = new ArrayList<>();
    private DatabaseConnection databaseConnection;

    public AbsenceRepositoryImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void addObserver(IAbsenceObserver observer) {
        observers.add(observer);
    }

    @Override
    public void saveAbsences(List<Absence> absences) throws PersistenceException {
        // Simulate database save operation
        System.out.println("Saving " + absences.size() + " absence(s) to database.");
        // Notify observers for each absence
        for (Absence absence : absences) {
            for (IAbsenceObserver observer : observers) {
                observer.onAbsenceRecorded(absence);
            }
        }
    }

    @Override
    public List<Absence> findByClassAndDate(String classId, Date date) {
        // Mock implementation: return empty list (no existing absences)
        System.out.println("Finding absences for class " + classId + " on " + date);
        return new ArrayList<>();
    }
}