
package com.example.boundary;

import com.example.control.NoteService;
import com.example.dto.NoteDTO;
import com.example.dto.NoteUpdateRequest;
import com.example.dto.NoteUpdateResult;
import java.util.Date;

/**
 * Boundary class representing the UI form for editing a note.
 * Manages user interactions and delegates business logic to NoteService.
 */
public class NoteEditForm {
    private int noteId; // Added to satisfy flow of events (implicit from use case)
    private String studentField;
    private String descriptionField;
    private String teacherField;
    private Date dateField;
    private NoteService noteService; // Dependency
    private RegistryScreen registryScreen; // Added to satisfy exit condition REQ-Exit-Conditions

    /**
     * Constructor initializing with a NoteService instance.
     * Assumption: RegistryScreen is injected or created as needed.
     *
     * @param noteService the service handling note operations
     */
    public NoteEditForm(NoteService noteService) {
        this.noteService = noteService;
        this.registryScreen = new RegistryScreen(); // Default initialization
    }

    /**
     * Renders note details on the form.
     *
     * @param note the NoteDTO containing note details
     */
    public void renderNoteDetails(NoteDTO note) {
        this.noteId = note.getNoteId();
        this.studentField = note.getStudent();
        this.descriptionField = note.getDescription();
        this.teacherField = note.getTeacher();
        this.dateField = note.getDate();
        System.out.println("Rendered note details for note ID: " + noteId);
    }

    /**
     * Gathers user input from the form fields and creates an update request.
     *
     * @return NoteUpdateRequest containing the updated note data
     */
    public NoteUpdateRequest gatherUserInput() {
        NoteUpdateRequest request = new NoteUpdateRequest();
        request.setNoteId(noteId);
        request.setStudent(studentField);
        request.setDescription(descriptionField);
        request.setTeacher(teacherField);
        request.setDate(dateField);
        request.setModifiedBy("Administrator"); // Assumption: current user is admin
        return request;
    }

    /**
     * Displays an error message based on the update result.
     * Updated to satisfy quality requirement.
     *
     * @param error the NoteUpdateResult containing error details
     */
    public void showError(NoteUpdateResult error) {
        System.err.println("Error " + error.getErrorCode() + ": " + error.getMessage());
        System.err.println("Details: " + error.getErrorDetails());
    }

    /**
     * Displays a success message after a successful update.
     *
     * @param message the success message
     */
    public void showSuccess(String message) {
        System.out.println("Success: " + message);
    }

    /**
     * Cancels the edit operation and returns to the registry screen.
     * Added to satisfy exit condition REQ-Exit-Conditions.
     */
    public void cancelEdit() {
        System.out.println("Edit cancelled.");
        returnToRegistryScreen();
    }

    /**
     * Returns to the registry screen.
     * Added to satisfy exit condition REQ-Exit-Conditions.
     */
    public void returnToRegistryScreen() {
        registryScreen.display();
    }

    // Field edit methods - Added to satisfy flow of events 1-5
    public void editStudentField(String value) {
        this.studentField = value;
        System.out.println("Student field updated to: " + value);
    }

    public void editDescriptionField(String value) {
        this.descriptionField = value;
        System.out.println("Description field updated to: " + value);
    }

    public void editTeacherField(String value) {
        this.teacherField = value;
        System.out.println("Teacher field updated to: " + value);
    }

    public void editDateField(Date value) {
        this.dateField = value;
        System.out.println("Date field updated to: " + value);
    }

    // Simulated login method as per sequence diagram
    public Boolean login(String username, String password) {
        // Assuming Administrator class has a constructor taking username and password,
        // or we have a method to create an Administrator instance.
        // For now, create a simple Administrator object.
        // If Administrator is an entity with username/password fields, we adapt accordingly.
        // Administrator admin = new Administrator(username, password);
        // return noteService.verifyAdmin(admin);
        return noteService.verifyAdmin(username, password);
    }

    // Simulated view note details as per sequence diagram
    public void viewNoteDetails(int noteId) {
        NoteDTO noteDTO = noteService.getNoteDetails(noteId);
        if (noteDTO != null) {
            renderNoteDetails(noteDTO);
        }
    }

    // Getters and setters
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getStudentField() {
        return studentField;
    }

    public void setStudentField(String studentField) {
        this.studentField = studentField;
    }

    public String getDescriptionField() {
        return descriptionField;
    }

    public void setDescriptionField(String descriptionField) {
        this.descriptionField = descriptionField;
    }

    public String getTeacherField() {
        return teacherField;
    }

    public void setTeacherField(String teacherField) {
        this.teacherField = teacherField;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }

    public RegistryScreen getRegistryScreen() {
        return registryScreen;
    }

    public void setRegistryScreen(RegistryScreen registryScreen) {
        this.registryScreen = registryScreen;
    }
}
