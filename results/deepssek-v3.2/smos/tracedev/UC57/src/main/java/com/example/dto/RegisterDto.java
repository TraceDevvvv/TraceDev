package com.example.dto;

import java.util.Date;
import java.util.List;
import com.example.model.Absence;
import com.example.model.DisciplinaryNote;
import com.example.model.Delay;
import com.example.model.Justification;

/**
 * Data Transfer Object for register data.
 */
public class RegisterDto {
    private String className;
    private Date date;
    private List<Absence> absences;
    private List<DisciplinaryNote> disciplinaryNotes;
    private List<Delay> delays;
    private List<Justification> justifications;

    public RegisterDto(String className, Date date, List<Absence> absences,
                       List<DisciplinaryNote> disciplinaryNotes, List<Delay> delays,
                       List<Justification> justifications) {
        this.className = className;
        this.date = date;
        this.absences = absences;
        this.disciplinaryNotes = disciplinaryNotes;
        this.delays = delays;
        this.justifications = justifications;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    public List<DisciplinaryNote> getDisciplinaryNotes() {
        return disciplinaryNotes;
    }

    public void setDisciplinaryNotes(List<DisciplinaryNote> disciplinaryNotes) {
        this.disciplinaryNotes = disciplinaryNotes;
    }

    public List<Delay> getDelays() {
        return delays;
    }

    public void setDelays(List<Delay> delays) {
        this.delays = delays;
    }

    public List<Justification> getJustifications() {
        return justifications;
    }

    public void setJustifications(List<Justification> justifications) {
        this.justifications = justifications;
    }
}