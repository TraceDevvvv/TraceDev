package com.example.attendancesystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Application service for managing attendance.
 * Defined in the Application (Service Layer) of the Class Diagram.
 * Coordinates interactions between repositories, email service, and external gateways.
 */
public class AttendanceService {
    private StudentRepository studentRepository;
    private AttendanceRepository attendanceRepository;
    private EmailService emailService;
    private ExternalServerGateway externalServerGateway;

    /**
     * Constructs an AttendanceService with necessary dependencies.
     * @param studentRepository The repository for student data.
     * @param attendanceRepository The repository for attendance records.
     * @param emailService The service for sending emails.
     * @param externalServerGateway The gateway for external server communication.
     */
    public AttendanceService(StudentRepository studentRepository, AttendanceRepository attendanceRepository,
                             EmailService emailService, ExternalServerGateway externalServerGateway) {
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
        this.emailService = emailService;
        this.externalServerGateway = externalServerGateway;
    }

    /**
     * Retrieves a list of students for a specific class.
     * @param classId The ID of the class.
     * @return A list of students in the specified class.
     */
    public List<Student> getStudentsForClass(String classId) {
        System.out.println("[AttendanceService] Fetching students for class: " + classId);
        return studentRepository.findStudentsByClass(classId);
    }

    /**
     * Records attendance data for a class, saves it, sends parent notifications,
     * and forwards absence data to an external server.
     *
     * @param classId The ID of the class for which attendance is being recorded.
     * @param attendanceData A map where keys are student IDs and values are their attendance statuses.
     * @throws ServiceError If an error occurs during processing, especially if the external server fails (R16).
     */
    public void recordAttendance(String classId, Map<String, AttendanceStatus> attendanceData) throws ServiceError {
        System.out.println("[AttendanceService] Recording attendance for class: " + classId);

        // 1. Get all students for the class to ensure we have their full details for notifications.
        List<Student> studentsInClass = studentRepository.findStudentsByClass(classId);
        Map<String, Student> studentMap = studentsInClass.stream()
                                                    .collect(Collectors.toMap(Student::getStudentId, s -> s));

        List<AttendanceRecord> recordsToSave = new ArrayList<>();
        List<AttendanceRecord> absenceRecords = new ArrayList<>(); // Collection for external server

        LocalDate today = LocalDate.now(); // Current date for attendance records

        // Process each student's attendance status
        for (Map.Entry<String, AttendanceStatus> entry : attendanceData.entrySet()) {
            String studentId = entry.getKey();
            AttendanceStatus status = entry.getValue();

            // Create an attendance record
            AttendanceRecord record = new AttendanceRecord(UUID.randomUUID().toString(), today, studentId, status);
            recordsToSave.add(record);

            // If a student is absent, prepare for external server and send parent notification
            if (status == AttendanceStatus.ABSENT) {
                absenceRecords.add(record); // Add to list for SMOS server
                Student absentStudent = studentMap.get(studentId);
                if (absentStudent != null) {
                    // Sequence Diagram: loop for each absent student, Service -> Email : sendParentNotification
                    emailService.sendParentNotification(absentStudent, today, status);
                } else {
                    // This scenario indicates data inconsistency, which should ideally be prevented earlier.
                    System.err.println("[AttendanceService] Warning: Absent student " + studentId + " not found in student list for class " + classId);
                }
            }
        }

        // 2. Save attendance records to the internal repository
        // Sequence Diagram: Service -> AttendanceRepo : save(attendanceRecords)
        attendanceRepository.save(recordsToSave);

        // 3. Send absence data to the external server
        // Sequence Diagram: Service -> ServerGateway : sendData(absenceData)
        try {
            externalServerGateway.sendData(absenceRecords);
        } catch (ExternalServerError e) {
            // R16, Sequence Diagram: ServerGateway --x Service : throws ExternalServerError
            // Service catches ExternalServerError and propagates it as a ServiceError.
            throw new ServiceError("Failed to send data to SMOS server: " + e.getMessage(), e);
        }

        System.out.println("[AttendanceService] Attendance recorded and processed successfully.");
    }
}