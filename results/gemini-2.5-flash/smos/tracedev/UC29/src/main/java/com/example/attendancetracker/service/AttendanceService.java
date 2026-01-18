
package com.example.attendancetracker.service;

import com.example.attendancetracker.model.AttendanceRecord;
import com.example.attendancetracker.model.AttendanceStatus;
import com.example.attendancetracker.model.NotificationEvent;
import com.example.attendancetracker.model.Parent;
import com.example.attendancetracker.repository.AttendanceRecordRepository;
import com.example.attendancetracker.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service responsible for business logic related to attendance.
 * Handles processing attendance entries and retrieving records.
 * <<Service>> layer.
 */
public class AttendanceService {
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final StudentRepository studentRepository;
    private final MessageQueueService messageQueueService;

    public AttendanceService(AttendanceRecordRepository attendanceRecordRepository,
                             StudentRepository studentRepository,
                             MessageQueueService messageQueueService) {
        this.attendanceRecordRepository = attendanceRecordRepository;
        this.studentRepository = studentRepository;
        this.messageQueueService = messageQueueService;
    }

    /**
     * Processes attendance entries provided by the controller for a specific date.
     * This method saves attendance records and publishes notification events for absent students.
     * @param attendanceData A map where key is student ID and value is a list containing the attendance status string.
     *                       Example: {"S001": ["ABSENT"], "S002": ["PRESENT"]}
     * @param selectedDate The date for which attendance is being recorded.
     * @return A list of updated attendance records after processing.
     */
    public List<AttendanceRecord> processAttendanceEntry(Map<String, List<String>> attendanceData, Date selectedDate) {
        List<AttendanceRecord> updatedRecords = new ArrayList<>();

        System.out.println("[AttendanceService] Processing attendance entries for date: " + selectedDate.toInstant().toString().substring(0,10));

        for (Map.Entry<String, List<String>> entry : attendanceData.entrySet()) {
            String studentId = entry.getKey();
            List<String> statusList = entry.getValue();

            if (statusList == null || statusList.isEmpty()) {
                System.out.println("[AttendanceService] Skipping student " + studentId + ": No status provided.");
                continue;
            }

            AttendanceStatus status = AttendanceStatus.valueOf(statusList.get(0).toUpperCase());

            // Save the attendance record
            AttendanceRecord newRecord = new AttendanceRecord(studentId, selectedDate, status);
            AttendanceRecord savedRecord = attendanceRecordRepository.save(newRecord);
            updatedRecords.add(savedRecord);

            // If savedRecord.status == ABSENT, publish notification
            if (savedRecord.status == AttendanceStatus.ABSENT) {
                // Find parent information
                Parent parentInfo = studentRepository.findParentByStudentId(savedRecord.getStudentId());
                if (parentInfo != null) {
                    System.out.println("[AttendanceService] Student " + studentId + " is ABSENT. Publishing notification event for parent " + parentInfo.getEmail());
                    // Publish notification event asynchronously
                    NotificationEvent event = new NotificationEvent(savedRecord.getStudentId(), parentInfo.getEmail(), savedRecord.recordDate);
                    messageQueueService.publish(event);
                } else {
                    System.out.println("[AttendanceService] No parent found for student " + studentId + " to send absence notification.");
                }
            }
        }
        return updatedRecords;
    }


    /**
     * Retrieves all attendance records for a given date.
     * @param date The date to retrieve records for.
     * @return A list of attendance records for the specified date.
     */
    public List<AttendanceRecord> getAttendanceRecords(Date date) {
        System.out.println("[AttendanceService] Getting attendance records for date: " + date.toInstant().toString().substring(0,10));
        return attendanceRecordRepository.findByDate(date);
    }
}
