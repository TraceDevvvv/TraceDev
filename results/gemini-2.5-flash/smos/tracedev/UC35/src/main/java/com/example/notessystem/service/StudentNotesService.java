package com.example.notessystem.service;

import com.example.notessystem.domain.Note;
import com.example.notessystem.dto.NoteDisplayDto;
import com.example.notessystem.repository.INoteRepository;
import com.example.notessystem.exception.ConnectionException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Application Service Layer: Encapsulates use case specific orchestration logic for student notes.
 * This service handles the business logic for retrieving and transforming notes.
 */
public class StudentNotesService {

    private INoteRepository noteRepository;

    /**
     * Constructs a StudentNotesService with a dependency on an INoteRepository.
     * @param noteRepository The repository to use for note data access.
     */
    public StudentNotesService(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Retrieves a list of notes for a given student ID and transforms them into a display format.
     * @param studentId The ID of the student.
     * @return A list of {@link NoteDisplayDto} objects.
     * @throws ConnectionException if the underlying repository encounters a connection issue.
     */
    public List<NoteDisplayDto> getStudentNotes(String studentId) throws ConnectionException {
        System.out.println("StudentNotesService: Requesting notes for studentId: " + studentId);

        // Service -> Repository call
        // This is where the ConnectionException from the repository would propagate
        List<Note> notes = noteRepository.findNotesByStudentId(studentId);

        // Note: Transform List<Note> to List<NoteDisplayDto>
        // Service --> Controller return
        return notes.stream()
                    .map(note -> new NoteDisplayDto(note.getId(), note.getDate(), note.getContent(), note.getAuthor()))
                    .collect(Collectors.toList());
    }
}