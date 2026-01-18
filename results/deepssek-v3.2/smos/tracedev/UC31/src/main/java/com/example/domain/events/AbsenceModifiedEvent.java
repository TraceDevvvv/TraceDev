package com.example.domain.events;

import com.example.domain.AbsenceStatus;
import java.util.Date;

/**
 * Event emitted when an absence is modified.
 */
public class AbsenceModifiedEvent {
    private String absenceId;
    private String studentId;
    private Date date;
    private AbsenceStatus oldStatus;
    private AbsenceStatus newStatus;

    public AbsenceModifiedEvent(String absenceId, String studentId, Date date,
                                AbsenceStatus oldStatus, AbsenceStatus newStatus) {
        this.absenceId = absenceId;
        this.studentId = studentId;
        this.date = date;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    public String getAbsenceId() {
        return absenceId;
    }

    public String getStudentId() {
        return studentId;
    }

    public Date getDate() {
        return date;
    }

    public AbsenceStatus getOldStatus() {
        return oldStatus;
    }

    public AbsenceStatus getNewStatus() {
        return newStatus;
    }
}