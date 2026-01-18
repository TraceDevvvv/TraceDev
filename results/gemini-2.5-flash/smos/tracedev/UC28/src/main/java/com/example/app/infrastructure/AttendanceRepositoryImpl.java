package com.example.app.infrastructure;

import com.example.app.domain.AttendanceRecord;
import com.example.app.domain.AttendanceStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class AttendanceRepositoryImpl implements IAttendanceRepository {
    private final List<AttendanceRecord> records = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public AttendanceRepositoryImpl() {
        // Populate with some mock data
        try {
            // For REG001 on 2023-10-27
            records.add(new AttendanceRecord(UUID.randomUUID().toString(), "S001", "REG001", dateFormat.parse("2023-10-27"), AttendanceStatus.PRESENT, false, null));
            records.add(new AttendanceRecord(UUID.randomUUID().toString(), "S002", "REG001", dateFormat.parse("2023-10-27"), AttendanceStatus.ABSENT, false, null)); // S002 is absent, no justification yet
            records.add(new AttendanceRecord(UUID.randomUUID().toString(), "S004", "REG001", dateFormat.parse("2023-10-27"), AttendanceStatus.PRESENT, true, null)); // S004 is present but late

            // For REG001 on another date
            records.add(new AttendanceRecord(UUID.randomUUID().toString(), "S001", "REG001", dateFormat.parse("2023-10-26"), AttendanceStatus.ABSENT, false, null));

            // For REG002 on 2023-10-27
            records.add(new AttendanceRecord(UUID.randomUUID().toString(), "S001", "REG002", dateFormat.parse("2023-10-27"), AttendanceStatus.PRESENT, false, null));
            records.add(new AttendanceRecord(UUID.randomUUID().toString(), "S003", "REG002", dateFormat.parse("2023-10-27"), AttendanceStatus.ABSENT, false, null));

        } catch (ParseException e) {
            System.err.println("Error parsing mock date in AttendanceRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public List<AttendanceRecord> findByRegisterIdAndDate(String registerId, String date) {
        // Simulates fetching from a database
        System.out.println(String.format("DB: SELECT * FROM AttendanceRecords WHERE registerId = %s AND date = %s", registerId, date));
        Date targetDate;
        try {
            targetDate = dateFormat.parse(date);
        } catch (ParseException e) {
            System.err.println("Invalid date format: " + date);
            return new ArrayList<>();
        }

        return records.stream()
                      .filter(r -> r.getRegisterId().equals(registerId) && dateFormat.format(r.getDate()).equals(date))
                      .collect(Collectors.toList());
    }
}