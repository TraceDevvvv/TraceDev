package com.school.infrastructure;

import com.school.domain.DisciplinaryNote;

/**
 * Repository interface for disciplinary notes.
 */
public interface DisciplinaryNoteRepository {
    void save(DisciplinaryNote note);
    DisciplinaryNote findById(String id);
}