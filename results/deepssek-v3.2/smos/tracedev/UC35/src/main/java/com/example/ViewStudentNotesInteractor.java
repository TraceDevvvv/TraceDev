package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Interactor implementing the ViewStudentNotesUseCase.
 * Coordinates the retrieval of student notes and mapping to view models.
 */
public class ViewStudentNotesInteractor implements ViewStudentNotesUseCase {
    private StudentNotesRepository notesRepository;

    /**
     * Constructs a ViewStudentNotesInteractor with the given repository.
     *
     * @param repository the repository for student notes
     */
    public ViewStudentNotesInteractor(StudentNotesRepository repository) {
        this.notesRepository = repository;
    }

    /**
     * Executes the use case: retrieves notes for a student and maps them to a view model.
     *
     * @param studentId the ID of the student whose notes are to be viewed
     * @return the view model containing the student's notes
     */
    @Override
    public StudentNotesViewModel execute(String studentId) {
        // Fetch notes from repository
        List<StudentNote> notes = notesRepository.findByStudentId(studentId);
        // Map each note to a NoteViewModel
        List<NoteViewModel> noteViewModels = new ArrayList<>();
        for (StudentNote note : notes) {
            noteViewModels.add(mapToViewModel(note));
        }
        // Create and return the view model
        return new StudentNotesViewModel(studentId, noteViewModels);
    }

    /**
     * Maps a StudentNote domain object to a NoteViewModel.
     *
     * @param note the StudentNote to map
     * @return the corresponding NoteViewModel
     */
    public NoteViewModel mapToViewModel(StudentNote note) {
        // Format the date as a string (simple representation)
        String formattedDate = note.getDateRecorded().toString();
        return new NoteViewModel(note.getContent(), formattedDate);
    }

    /**
     * Simulates the interactor creating the StudentNotesViewModel.
     * This method corresponds to sequence diagram message m24.
     *
     * @param studentId the student ID
     * @return the StudentNotesViewModel
     */
    public StudentNotesViewModel createStudentNotesViewModel(String studentId) {
        return execute(studentId);
    }
}