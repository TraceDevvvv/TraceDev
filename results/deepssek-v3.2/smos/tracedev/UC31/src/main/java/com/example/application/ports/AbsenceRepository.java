package com.example.application.ports;

import com.example.domain.Absence;

/**
 * Repository interface for Absence aggregate.
 */
public interface AbsenceRepository {
    Absence findById(String id);
    Absence save(Absence absence);
}