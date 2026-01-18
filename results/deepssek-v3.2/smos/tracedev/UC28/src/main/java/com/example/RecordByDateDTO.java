package com.example;

import java.util.Date;
import java.util.List;

/**
 * Data Transfer Object for RecordByDate.
 */
public class RecordByDateDTO {
    public Date recordDate;
    public List<StudentRecordDTO> studentRecords;

    public RecordByDateDTO(Date recordDate, List<StudentRecordDTO> studentRecords) {
        this.recordDate = recordDate;
        this.studentRecords = studentRecords;
    }
}