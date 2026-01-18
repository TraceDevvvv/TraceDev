package com.example.service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.example.client.SMOSClient;
import com.example.model.AttendanceRecord;
import com.example.model.DisciplinaryNote;
import com.example.model.DelayRecord;
import com.example.model.Justification;

/**
 * Implementation of AttendanceService that uses SMOS client.
 */
public class SMOSAttendanceService implements AttendanceService {
    private SMOSClient smosClient;

    public SMOSAttendanceService(SMOSClient smosClient) {
        this.smosClient = smosClient;
    }

    @Override
    public List<AttendanceRecord> getAttendanceRecords(int childId, Date date) {
        // Ensure connection is active
        if (!smosClient.isConnectionActive()) {
            smosClient.connect();
        }
        
        // Fetch raw data and parse into AttendanceRecord objects
        String rawData = smosClient.fetchAttendanceData(childId);
        // Simulate parsing: create mock records
        List<AttendanceRecord> records = new ArrayList<>();
        if (rawData != null && !rawData.isEmpty()) {
            // Add some mock attendance records (including absences with type "absence")
            records.add(new AttendanceRecord(1, childId, date, "absence", false));
            records.add(new AttendanceRecord(2, childId, date, "presence", true));
        }
        return records;
    }

    @Override
    public List<DisciplinaryNote> getDisciplinaryNotes(int childId) {
        if (!smosClient.isConnectionActive()) {
            smosClient.connect();
        }
        
        String rawData = smosClient.fetchDisciplinaryNotes(childId);
        List<DisciplinaryNote> notes = new ArrayList<>();
        if (rawData != null && !rawData.isEmpty()) {
            notes.add(new DisciplinaryNote(1, childId, new Date(), "Late to class", "Mr. Smith"));
            notes.add(new DisciplinaryNote(2, childId, new Date(), "Disruptive behavior", "Ms. Johnson"));
        }
        return notes;
    }

    @Override
    public List<DelayRecord> getDelayRecords(int childId) {
        if (!smosClient.isConnectionActive()) {
            smosClient.connect();
        }
        
        String rawData = smosClient.fetchDelayRecords(childId);
        List<DelayRecord> delays = new ArrayList<>();
        if (rawData != null && !rawData.isEmpty()) {
            delays.add(new DelayRecord(1, childId, new Date(), 15, false));
            delays.add(new DelayRecord(2, childId, new Date(), 5, true));
        }
        return delays;
    }

    @Override
    public List<Justification> getJustifications(int childId) {
        if (!smosClient.isConnectionActive()) {
            smosClient.connect();
        }
        
        String rawData = smosClient.fetchJustifications(childId);
        List<Justification> justifications = new ArrayList<>();
        if (rawData != null && !rawData.isEmpty()) {
            justifications.add(new Justification(1, 1, "absence", "Doctor appointment", new Date(), "approved"));
            justifications.add(new Justification(2, 1, "delay", "Traffic jam", new Date(), "pending"));
        }
        return justifications;
    }
}