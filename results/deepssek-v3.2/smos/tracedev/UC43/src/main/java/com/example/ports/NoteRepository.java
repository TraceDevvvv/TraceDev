package com.example.ports;

import com.example.domain.DisciplinaryNote;

public interface NoteRepository {
    DisciplinaryNote findById(String noteId);
    void delete(String noteId);
    void save(DisciplinaryNote note);
}