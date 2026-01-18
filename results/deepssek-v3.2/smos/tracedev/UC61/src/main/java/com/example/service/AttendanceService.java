package com.example.service;

import java.util.Date;
import java.util.List;
import com.example.model.AttendanceRecord;
import com.example.model.DisciplinaryNote;
import com.example.model.DelayRecord;
import com.example.model.Justification;

/**
 * Service interface for attendance-related data retrieval.
 */
public interface AttendanceService {
    List<AttendanceRecord> getAttendanceRecords(int childId, Date date);
    List<DisciplinaryNote> getDisciplinaryNotes(int childId);
    List<DelayRecord> getDelayRecords(int childId);
    List<Justification> getJustifications(int childId);
}