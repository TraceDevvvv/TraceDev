package com.example;

import java.util.Date;
import java.util.List;

/**
 * Represents a class register with records, justifications, and disciplinary notes.
 * Traceability: The system shall ensure the registry information is accurate and displayed in a clear, organized format by date.
 */
public class ClassRegister {
    private String registerId;
    private String className;
    private Date startDate;
    private Date endDate;
    private List<RecordByDate> records;
    private List<Justification> justifications;
    private List<DisciplinaryNote> disciplinaryNotes;

    public ClassRegister(String registerId, String className, Date startDate, Date endDate, List<RecordByDate> records, List<Justification> justifications, List<DisciplinaryNote> disciplinaryNotes) {
        this.registerId = registerId;
        this.className = className;
        this.startDate = startDate;
        this.endDate = endDate;
        this.records = records;
        this.justifications = justifications;
        this.disciplinaryNotes = disciplinaryNotes;
    }

    public String getRegisterId() {
        return registerId;
    }

    public String getClassName() {
        return className;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public ClassRegisterDTO getRegisterData() {
        return new ClassRegisterDTO(this);
    }

    public List<RecordByDate> getRecords() {
        return records;
    }

    public List<Justification> getJustifications() {
        return justifications;
    }

    public List<DisciplinaryNote> getDisciplinaryNotes() {
        return disciplinaryNotes;
    }
}