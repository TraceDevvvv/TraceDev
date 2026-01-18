package com.system.school.data;

import com.system.school.note.DisciplinaryNote;
import com.system.school.student.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Manages the storage and retrieval of disciplinary notes and student information within the system.
 * This class acts as a simulated database or repository for these entities.
 */
public class NoteArchive {
    // Stores disciplinary notes, keyed by their unique noteId.
    private final Map<String, DisciplinaryNote> disciplinaryNotes;
    // Stores student information, keyed by their unique studentId.
    private final Map<String, Student> students;

    /**
     * Constructs a new NoteArchive instance, initializing the storage for notes and students.
     */
    public NoteArchive() {
        this.disciplinaryNotes = new HashMap<>();
        this.students = new HashMap<>();
    }

    /**
     * Adds a new disciplinary note to the archive.
     *
     * @param note The DisciplinaryNote object to add.
     * @throws IllegalArgumentException if the note is null or a note with the same ID already exists.
     */
    public void addNote(DisciplinaryNote note) {
        if (note == null) {
            throw new IllegalArgumentException("Cannot add a null disciplinary note.");
        }
        if (disciplinaryNotes.containsKey(note.getNoteId())) {
            throw new IllegalArgumentException("A disciplinary note with ID " + note.getNoteId() + " already exists.");
        }
        disciplinaryNotes.put(note.getNoteId(), note);
        System.out.println("Note added to archive: " + note.getNoteId());
    }

    /**
     * Retrieves a disciplinary note by its unique ID.
     *
     * @param noteId The ID of the note to retrieve.
     * @return An Optional containing the DisciplinaryNote if found, or an empty Optional if not found.
     */
    public Optional<DisciplinaryNote> getNoteById(String noteId) {
        if (noteId == null || noteId.trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(disciplinaryNotes.get(noteId));
    }

    /**
     * Deletes a disciplinary note from the archive by its unique ID.
     *
     * @param noteId The ID of the note to delete.
     * @return An Optional containing the deleted DisciplinaryNote if it was found and removed,
     *         or an empty Optional if no note with the given ID was found.
     */
    public Optional<DisciplinaryNote> deleteNote(String noteId) {
        if (noteId == null || noteId.trim().isEmpty()) {
            System.err.println("Error: Cannot delete note. Note ID is null or empty.");
            return Optional.empty();
        }
        DisciplinaryNote removedNote = disciplinaryNotes.remove(noteId);
        if (removedNote != null) {
            System.out.println("Note deleted from archive: " + noteId);
            return Optional.of(removedNote);
        } else {
            System.out.println("Note with ID " + noteId + " not found in archive for deletion.");
            return Optional.empty();
        }
    }

    /**
     * Adds a new student to the archive.
     *
     * @param student The Student object to add.
     * @throws IllegalArgumentException if the student is null or a student with the same ID already exists.
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot add a null student.");
        }
        if (students.containsKey(student.getStudentId())) {
            throw new IllegalArgumentException("A student with ID " + student.getStudentId() + " already exists.");
        }
        students.put(student.getStudentId(), student);
        System.out.println("Student added to archive: " + student.getStudentId());
    }

    /**
     * Retrieves a student by their unique ID.
     *
     * @param studentId The ID of the student to retrieve.
     * @return An Optional containing the Student if found, or an empty Optional if not found.
     */
    public Optional<Student> getStudentById(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(students.get(studentId));
    }

    /**
     * Returns the total number of disciplinary notes currently in the archive.
     *
     * @return The count of disciplinary notes.
     */
    public int getNoteCount() {
        return disciplinaryNotes.size();
    }

    /**
     * Returns the total number of students currently in the archive.
     *
     * @return The count of students.
     */
    public int getStudentCount() {
        return students.size();
    }
}