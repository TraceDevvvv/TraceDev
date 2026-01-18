package com.example;

import java.util.Date;
import java.util.List;

/**
 * Represents records for a specific date.
 * Traceability: System organizes the information by date.
 */
public class RecordByDate {
    private Date recordDate;
    private List<StudentRecord> studentRecords;

    public RecordByDate(Date recordDate, List<StudentRecord> studentRecords) {
        this.recordDate = recordDate;
        this.studentRecords = studentRecords;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public List<StudentRecord> getStudentRecords() {
        return studentRecords;
    }
}