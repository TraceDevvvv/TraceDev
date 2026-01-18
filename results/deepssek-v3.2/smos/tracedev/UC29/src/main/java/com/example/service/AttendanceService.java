package com.example.service;

import com.example.dto.AttendanceFormDTO;
import com.example.dto.AttendanceDisplayData;
import com.example.dto.RegistrationResult;
import com.example.dto.AbsenceNotificationDTO;
import com.example.entity.AbsentStudent;
import com.example.entity.AttendanceRecord;
import com.example.entity.AttendanceLog;
import com.example.entity.Student;
import com.example.repository.AttendanceRepository;
import com.example.repository.StudentRepository;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Service layer for attendance operations.
 */
public class AttendanceService {
    private AttendanceRepository attendanceRepository;
    private StudentRepository studentRepository;
    private NotificationService notificationService;

    public AttendanceService(AttendanceRepository attendanceRepository,
                             StudentRepository studentRepository,
                             NotificationService notificationService) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.notificationService = notificationService;
    }

    /**
     * Updates the display for a given date.
     * Corresponds to sequence diagram step 1.
     */
    public AttendanceDisplayData updateDisplay(Date date) {
        // Fetch students (loop in sequence diagram)
        List<Student> students = studentRepository.findAll();
        // Fetch previous records for the date
        List<AttendanceRecord> records = attendanceRepository.findByDate(date);
        // Convert records to logs
        List<AttendanceLog> logs = new ArrayList<>();
        for (AttendanceRecord rec : records) {
            logs.add(new AttendanceLog("LOG-" + rec.getId(), rec, "Record for " + date));
        }
        return new AttendanceDisplayData(date, logs, students);
    }

    /**
     * Saves an attendance record from form data.
     * Corresponds to sequence diagram message saveAttendanceRecord(formData).
     */
    public RegistrationResult saveAttendanceRecord(AttendanceFormDTO formData) {
        // Convert DTO to entity
        AttendanceRecord record = formData.toEntity();
        
        // Save the record
        AttendanceRecord saved = attendanceRepository.save(record);
        
        // Process absent students for notifications
        List<AbsentStudent> absentees = new ArrayList<>();
        if (formData.absentStudents != null) {
            for (com.example.dto.StudentDTO dto : formData.absentStudents) {
                AbsentStudent absent = new AbsentStudent(dto.studentId, formData.date, "No reason provided");
                absentees.add(absent);
            }
        }
        
        // Notify parents of absentees
        notifyParents(absentees);
        
        // Create successful result
        RegistrationResult result = new RegistrationResult();
        result.success = true;
        result.message = "Attendance saved successfully";
        result.savedRecord = saved;
        result.notificationsSent = absentees.size();
        
        return result;
    }

    /**
     * Notifies parents about absent students.
     * Corresponds to sequence diagram messages.
     */
    public void notifyParents(List<AbsentStudent> absentees) {
        for (AbsentStudent absent : absentees) {
            // Create notification DTO
            AbsenceNotificationDTO notification = absent.toNotificationData();
            
            // Send email
            boolean sent = notificationService.sendEmail(notification);
            
            // If email fails, queue it
            if (!sent) {
                notificationService.queueNotification(notification);
            }
        }
    }

    /**
     * Updates log data (used in sequence diagram).
     */
    public void updateLogData() {
        // This method would typically update internal log data.
        // For the sequence diagram, it returns logData.
        // Implementation left simple.
    }

    /**
     * Cancels pending operations (used in cancellation flow).
     */
    public void cancelPendingOperations() {
        // Clear any pending notifications
        notificationService.clearQueue();
        // Mark notifications as pending in repository
        attendanceRepository.markNotificationPending();
    }

    /**
     * Handles notification failure (used in error flow).
     */
    public void handleNotificationFailure() {
        // Mark notifications as pending
        attendanceRepository.markNotificationPending();
        // Queue any pending notifications
        notificationService.processQueue();
    }
}