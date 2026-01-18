package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of StudentNotesRepository.
 * Supports requirement R3 - Quality Requirement: list must be complete and accurate.
 */
public class StudentNotesRepositoryImpl implements StudentNotesRepository {
    private StudentNotesDataSource dataSource;
    private NoteValidator validator;

    /**
     * Constructs a StudentNotesRepositoryImpl with the given data source and validator.
     *
     * @param dataSource the data source for fetching note DTOs
     * @param validator the validator for checking note completeness and accuracy
     */
    public StudentNotesRepositoryImpl(StudentNotesDataSource dataSource, NoteValidator validator) {
        this.dataSource = dataSource;
        this.validator = validator;
    }

    /**
     * Finds all notes for a given student ID.
     * Fetches DTOs from the data source, maps them to domain objects, and validates them.
     *
     * @param studentId the student ID
     * @return a list of StudentNote objects
     * @throws IllegalStateException if notes fail validation (completeness or accuracy)
     */
    @Override
    public List<StudentNote> findByStudentId(String studentId) {
        // Fetch DTOs from data source
        List<StudentNoteDto> noteDtos = dataSource.fetchNotesByStudentId(studentId);
        List<StudentNote> notes = new ArrayList<>();
        // Map each DTO to a domain object and validate accuracy
        for (StudentNoteDto dto : noteDtos) {
            StudentNote note = mapDtoToDomain(dto);
            // Requirement R3: validate accuracy of each note
            if (!validator.validateAccuracy(note)) {
                throw new IllegalStateException("Note accuracy validation failed for note ID: " + note.getId());
            }
            notes.add(note);
        }
        // Requirement R3: validate completeness of the entire list
        if (!validator.validateCompleteness(notes)) {
            throw new IllegalStateException("Notes completeness validation failed for student ID: " + studentId);
        }
        return notes;
    }

    /**
     * Maps a StudentNoteDto to a StudentNote domain object.
     *
     * @param dto the data transfer object
     * @return the domain object
     */
    public StudentNote mapDtoToDomain(StudentNoteDto dto) {
        return new StudentNote(
            dto.getNoteId(),
            dto.getStudentId(),
            dto.getNoteContent(),
            dto.getCreationDate(),
            dto.getSchoolYear()
        );
    }

    /**
     * Validates the completeness of the notes list.
     * Delegates to the NoteValidator.
     *
     * @param notes the list of notes to validate
     * @return true if the list is complete, false otherwise
     */
    public boolean validateNotesCompleteness(List<StudentNote> notes) {
        return validator.validateCompleteness(notes);
    }

    /**
     * Simulates the repository creating the StudentNotesViewModel.
     * This method corresponds to sequence diagram message m24.
     *
     * @param studentId the student ID
     * @return the StudentNotesViewModel
     */
    public StudentNotesViewModel createStudentNotesViewModel(String studentId) {
        List<StudentNote> notes = findByStudentId(studentId);
        List<NoteViewModel> noteViewModels = new ArrayList<>();
        for (StudentNote note : notes) {
            noteViewModels.add(new NoteViewModel(note.getContent(), note.getDateRecorded().toString()));
        }
        return new StudentNotesViewModel(studentId, noteViewModels);
    }
}