
package service;

import common.DataAccessFailureException;
import domain.Note;
import domain.NoteDetailDTO;
import domain.Student;
import domain.Teacher;
import repository.INoteRepository; // Corrected: Assuming the interface is named INoteRepository
import repository.IStudentRepository;
import repository.ITeacherRepository;

/**
 * Application/Service Layer component responsible for handling business logic
 * related to notes, such as retrieving note details.
 */
public class NoteService {
    // Public attributes for repositories as specified in the Class Diagram
    // Note: In a real-world application, these would typically be private and injected.
    public INoteRepository noteRepository; // Corrected: Assuming the interface is named INoteRepository
    public IStudentRepository studentRepository;
    public ITeacherRepository teacherRepository;

    // A flag to simulate data access failure for testing purposes, as per sequence diagram.
    // If set to true, getNoteDetails will throw DataAccessFailureException for specific noteId.
    private boolean simulateDataAccessFailure = false;

    /**
     * Constructs a NoteService with necessary repository dependencies.
     * @param noteRepository The repository for Note entities.
     * @param studentRepository The repository for Student entities.
     * @param teacherRepository The repository for Teacher entities.
     */
    public NoteService(INoteRepository noteRepository, IStudentRepository studentRepository, ITeacherRepository teacherRepository) { // Corrected: Assuming the interface is named INoteRepository
        this.noteRepository = noteRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    /**
     * Enables or disables the simulation of a data access failure.
     * @param simulate true to simulate failure, false otherwise.
     */
    public void setSimulateDataAccessFailure(boolean simulate) {
        this.simulateDataAccessFailure = simulate;
    }

    /**
     * Retrieves detailed information for a specific note.
     * This method orchestrates calls to various repositories to gather all necessary data.
     *
     * @param noteId The unique identifier of the note.
     * @return A NoteDetailDTO containing aggregated details about the note.
     * @throws DataAccessFailureException if there's an issue accessing the underlying data stores (REQ-013).
     */
    public NoteDetailDTO getNoteDetails(String noteId) throws DataAccessFailureException {
        System.out.println("[NoteService] getNoteDetails for noteId: " + noteId);

        // Simulate a data access failure for specific noteId if flag is set, as per sequence diagram error path.
        if (simulateDataAccessFailure && "errorNoteId".equals(noteId)) {
            System.err.println("[NoteService] Simulating DataAccessFailureException for noteId: " + noteId);
            throw new DataAccessFailureException("SMOS server connection interrupted for noteId: " + noteId);
        }

        // 1. Retrieve Note data
        Note note = noteRepository.getNoteById(noteId);
        if (note == null) {
            // If the note itself is not found, it's not a 'data access failure' but 'data not found'.
            // For simplicity, we can still throw a more specific or general exception,
            // or return null and let the controller handle it.
            // The sequence diagram implies a DataAccessFailureException is for broader connectivity issues.
            // Here, we assume a found note for the success path.
            // If 'null' for note implies a data access failure as per specific business rule,
            // then we'd throw DataAccessFailureException here.
            // Making an assumption that if note is null, it means no data found rather than server issue,
            // but the prompt implies the error path is for server connection.
            // Let's assume for this flow, if noteId is valid and not "errorNoteId", it will be found.
            // If a non-existent noteId is passed (not 'errorNoteId'), it would cause NPEs.
            // Let's make an assumption: if note is null, it's treated as data access failure for this specific sequence logic,
            // as the sequence diagram's error branch is 'Data access failure'.
            System.out.println("[NoteService] Note with ID " + noteId + " not found.");
            throw new DataAccessFailureException("Note with ID " + noteId + " could not be retrieved, possibly due to underlying data issues.");
        }

        // 2. Retrieve Student data
        Student student = studentRepository.getStudentById(note.getStudentId());
        if (student == null) {
            throw new DataAccessFailureException("Student with ID " + note.getStudentId() + " for note " + noteId + " not found, possibly due to underlying data issues.");
        }

        // 3. Retrieve Teacher data
        Teacher teacher = teacherRepository.getTeacherById(note.getTeacherId());
        if (teacher == null) {
            throw new DataAccessFailureException("Teacher with ID " + note.getTeacherId() + " for note " + noteId + " not found, possibly due to underlying data issues.");
        }

        // 4. Create NoteDetailDTO
        return createNoteDetailDTO(note, student, teacher);
    }

    /**
     * Private helper method to aggregate data into a NoteDetailDTO (REQ-007, REQ-008, REQ-009, REQ-010).
     * @param note The Note entity.
     * @param student The Student entity.
     * @param teacher The Teacher entity.
     * @return A new NoteDetailDTO.
     */
    private NoteDetailDTO createNoteDetailDTO(Note note, Student student, Teacher teacher) {
        System.out.println("[NoteService] Creating NoteDetailDTO.");
        return new NoteDetailDTO(
                student.getName(),
                note.getDescription(),
                teacher.getName(),
                note.getDate()
        );
    }
}
