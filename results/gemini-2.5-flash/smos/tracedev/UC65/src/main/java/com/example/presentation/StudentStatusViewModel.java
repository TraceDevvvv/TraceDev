package com.example.presentation;

/**
 * ViewModel for a student's status on a specific date.
 */
public class StudentStatusViewModel {
    public String studentName;
    public boolean isPresent;
    public boolean isDelayed;
    public String justificationDescription; // Aggregated justification info
    public String disciplinaryNoteText;     // Aggregated disciplinary note info

    /**
     * Constructs a StudentStatusViewModel.
     * @param studentName The name of the student.
     * @param isPresent True if the student was present.
     * @param isDelayed True if the student was delayed.
     * @param justificationDescription Description of the student's justification, if any.
     * @param disciplinaryNoteText Text of the student's disciplinary note, if any.
     */
    public StudentStatusViewModel(String studentName, boolean isPresent, boolean isDelayed,
                                  String justificationDescription, String disciplinaryNoteText) {
        this.studentName = studentName;
        this.isPresent = isPresent;
        this.isDelayed = isDelayed;
        this.justificationDescription = justificationDescription;
        this.disciplinaryNoteText = disciplinaryNoteText;
    }

    // Getters for ViewModel properties
    public String getStudentName() {
        return studentName;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public boolean isDelayed() {
        return isDelayed;
    }

    public String getJustificationDescription() {
        return justificationDescription;
    }

    public String getDisciplinaryNoteText() {
        return disciplinaryNoteText;
    }
}