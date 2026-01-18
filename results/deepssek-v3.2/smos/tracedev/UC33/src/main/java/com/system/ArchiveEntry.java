package com.system;

import java.util.Date;

/**
 * Represents an archive entry that may be flagged as late.
 */
public class ArchiveEntry {
    private String entryId;
    private String studentId;
    private Date date;
    private boolean isLate;

    public ArchiveEntry(String entryId, String studentId, Date date, boolean isLate) {
        this.entryId = entryId;
        this.studentId = studentId;
        this.date = date;
        this.isLate = isLate;
    }

    public String getEntryId() {
        return entryId;
    }

    public String getStudentId() {
        return studentId;
    }

    public Date getDate() {
        return date;
    }

    public boolean getIsLate() {
        return isLate;
    }
}