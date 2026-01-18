package com.example.domain;

import java.util.List;
import java.util.ArrayList;

/**
 * Domain model for a Student's Status on a given registry entry date.
 */
public class StudentStatus {
    private Student student;
    private boolean isPresent;
    private boolean isDelayed;
    public List<Justification> justifications;
    public List<DisciplinaryNote> disciplinaryNotes;

    /**
     * Constructs a StudentStatus object.
     * @param student The student associated with this status.
     * @param isPresent True if the student was present.
     * @param isDelayed True if the student was delayed.
     */
    public StudentStatus(Student student, boolean isPresent, boolean isDelayed) {
        this.student = student;
        this.isPresent = isPresent;
        this.isDelayed = isDelayed;
        this.justifications = new ArrayList<>();
        this.disciplinaryNotes = new ArrayList<>();
    }

    public Student getStudent() {
        return student;
    }

    public boolean getIsPresent() {
        return isPresent;
    }

    public boolean getIsDelayed() {
        return isDelayed;
    }

    public List<Justification> getJustifications() {
        return justifications;
    }

    public List<DisciplinaryNote> getDisciplinaryNotes() {
        return disciplinaryNotes;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public void setDelayed(boolean delayed) {
        isDelayed = delayed;
    }

    public void addJustification(Justification justification) {
        this.justifications.add(justification);
    }

    public void addDisciplinaryNote(DisciplinaryNote note) {
        this.disciplinaryNotes.add(note);
    }
}