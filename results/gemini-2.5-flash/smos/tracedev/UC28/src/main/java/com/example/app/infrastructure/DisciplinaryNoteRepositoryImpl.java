package com.example.app.infrastructure;

import com.example.app.domain.DisciplinaryNote;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Mock implementation of IDisciplinaryNoteRepository.
 * Simulates a database by storing DisciplinaryNote objects.
 */
public class DisciplinaryNoteRepositoryImpl implements IDisciplinaryNoteRepository {
    private final List<DisciplinaryNote> notes = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DisciplinaryNoteRepositoryImpl() {
        // Populate with some mock data
        try {
            // For REG001 on 2023-10-27, for S004
            notes.add(new DisciplinaryNote(UUID.randomUUID().toString(), "S004", "REG001", dateFormat.parse("2023-10-27"), "Repeated tardiness.", "Verbal Warning"));

        } catch (ParseException e) {
            System.err.println("Error parsing mock date in DisciplinaryNoteRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public List<DisciplinaryNote> findByRegisterIdAndDate(String registerId, String date) {
        // Simulates fetching from a database
        System.out.println(String.format("DB: SELECT * FROM DisciplinaryNotes WHERE registerId = %s AND date = %s", registerId, date));
        Date targetDate;
        try {
            targetDate = dateFormat.parse(date);
        } catch (ParseException e) {
            System.err.println("Invalid date format: " + date);
            return new ArrayList<>();
        }

        return notes.stream()
                    .filter(n -> n.getRegisterId().equals(registerId) && dateFormat.format(n.getDate()).equals(date))
                    .collect(Collectors.toList());
    }

    @Override
    public DisciplinaryNote save(DisciplinaryNote disciplinaryNote) {
        System.out.println("DB: INSERT INTO DisciplinaryNotes values(...)");
        if (disciplinaryNote.getId() == null) {
            disciplinaryNote = new DisciplinaryNote(UUID.randomUUID().toString(), disciplinaryNote.getStudentId(), disciplinaryNote.getRegisterId(),
                                                    disciplinaryNote.getDate(), disciplinaryNote.getDescription(), disciplinaryNote.getType());
        }
        notes.add(disciplinaryNote);
        System.out.println("Disciplinary note saved: " + disciplinaryNote.getId());
        return disciplinaryNote;
    }

    @Override
    public void update(String disciplinaryNoteId, String description, String type) {
        System.out.println(String.format("DB: UPDATE DisciplinaryNotes SET description = '%s', type = '%s' WHERE id = %s", description, type, disciplinaryNoteId));
        for (DisciplinaryNote note : notes) {
            if (note.getId().equals(disciplinaryNoteId)) {
                note.setDescription(description);
                note.setType(type);
                System.out.println("Disciplinary note " + disciplinaryNoteId + " updated.");
                return;
            }
        }
        System.out.println("Disciplinary note with ID " + disciplinaryNoteId + " not found for update.");
    }
}