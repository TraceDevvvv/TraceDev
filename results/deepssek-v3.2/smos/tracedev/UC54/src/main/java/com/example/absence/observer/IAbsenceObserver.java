package com.example.absence.observer;

import com.example.absence.domain.Absence;

/**
 * Observer interface for absence recording events.
 */
public interface IAbsenceObserver {
    /**
     * Called when an absence is recorded.
     * @param absence the recorded absence
     */
    void onAbsenceRecorded(Absence absence);
}