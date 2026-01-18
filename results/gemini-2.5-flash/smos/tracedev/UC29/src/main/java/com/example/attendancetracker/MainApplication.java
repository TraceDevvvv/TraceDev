
package com.example.attendancetracker;

import com.example.attendancetracker.controller.AttendanceController;
import com.example.attendancetracker.external.EmailGateway;
import com.example.attendancetracker.external.SMOSServer;
import com.example.attendancetracker.model.AttendanceModel;
import com.example.attendancetracker.model.AttendanceStatus;
import com.example.attendancetracker.model.NotificationEvent;
import com.example.attendancetracker.model.Parent;
import com.example.attendancetracker.repository.AttendanceRecordRepositoryImpl;
import com.example.attendancetracker.repository.StudentRepositoryImpl;
import com.example.attendancetracker.service.AttendanceService;
import com.example.attendancetracker.service.MessageQueueService;
import com.example.attendancetracker.service.NotificationService;
import com.example.attendancetracker.view.AttendanceView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Main application class to demonstrate the attendance tracking system.
 * Simulates Administrator interactions and asynchronous processes.
 */
public class MainApplication {

    public static void main(String[] args) throws ParseException, InterruptedException {
        // --- System Initialization (Dependency Injection setup) ---
        // Repositories (simulating database)
        AttendanceRecordRepositoryImpl attendanceRecordRepo = new AttendanceRecordRepositoryImpl();
        StudentRepositoryImpl studentRepo = new StudentRepositoryImpl();

        // External Serv
        EmailGateway emailGateway = new EmailGateway();
        SMOSServer smosServer = new SMOSServer(); // Not directly injected, but EmailGateway uses its static state

        // Message Queue
        MessageQueueService messageQueueService = new MessageQueueService();

        // Serv
        AttendanceService attendanceService = new AttendanceService(attendanceRecordRepo, studentRepo, messageQueueService);
        NotificationService notificationService = new NotificationService(messageQueueService, emailGateway, attendanceRecordRepo);

        // View
        AttendanceView attendanceView = new AttendanceView();

        // Controller
        AttendanceController attendanceController = new AttendanceController(attendanceService, attendanceView, studentRepo);

        System.out.println("--- Attendance Tracking System Started ---\n");
        System.out.println("Precondition: System has processed \"SeveralTetTingloregister\" use case.");
        System.out.println("Student and parent data are pre-registered in StudentRepositoryImpl.\n");

        // --- Scenario 1: Normal Flow - Administrator records attendance ---
        System.out.println("=== SCENARIO 1: Normal Flow (Record Attendance & Notify Absents) ===");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = sdf.parse("2023-10-26"); // Selected date for data entry

        // Entry Conditions: Date IS selected for data entry.
        // Administrator -> AttendanceController: displayAttendanceForm(selectedDate)
        AttendanceModel initialModel = attendanceController.displayAttendanceForm(today);

        // Administrator fills out form and clicks Save
        // Simulate attendance data: S001 absent, S002 present, S003 absent, S004 present
        Map<String, List<String>> attendanceDataNormal = new HashMap<>();
        attendanceDataNormal.put("S001", Arrays.asList(AttendanceStatus.ABSENT.name())); // Emma Smith (Parent: P001, alice.smith@example.com)
        attendanceDataNormal.put("S002", Arrays.asList(AttendanceStatus.PRESENT.name())); // Liam Johnson (Parent: P002, bob.johnson@example.com)
        attendanceDataNormal.put("S003", Arrays.asList(AttendanceStatus.ABSENT.name())); // Olivia Davis (Parent: P003, charlie.davis@example.com)
        attendanceDataNormal.put("S004", Arrays.asList(AttendanceStatus.PRESENT.name())); // Noah Smith (Parent: P001, alice.smith@example.com)

        // Administrator -> AttendanceController: saveAttendance(attendanceData)
        attendanceController.saveAttendance(attendanceDataNormal);

        // Simulate asynchronous notification processing
        System.out.println("\n--- Initiating Asynchronous Notification Processing ---");
        int processedCount = 0;
        int maxAttempts = 10; // Prevent infinite loop in case of issue
        while (!messageQueueService.isEmpty() && processedCount < maxAttempts) {
            NotificationEvent event = messageQueueService.consume();
            if (event != null) {
                notificationService.processNotificationEvent(event);
                processedCount++;
                TimeUnit.MILLISECONDS.sleep(100); // Simulate some processing time
            }
        }
        System.out.println("--- Asynchronous Notification Processing Finished ---");
        System.out.println("Total events processed: " + messageQueueService.getProcessedEventsCount());
        System.out.println("Total events failed: " + messageQueueService.getFailedEventsCount());

        // Verify notification status in repository (optional, but good for testing)
        System.out.println("\n--- Verifying Notification Status in Repository ---");
        attendanceRecordRepo.findByDate(today).forEach(record ->
            System.out.println("  " + record.getStudentId() + " on " + sdf.format(record.recordDate) + ": Notified=" + record.isNotified + " (Expected for ABSENT: true)"));

        System.out.println("\n=== SCENARIO 1 COMPLETED ===\n");
        TimeUnit.SECONDS.sleep(2);


        // --- Scenario 2: Administrator interrupts operation ---
        System.out.println("=== SCENARIO 2: Administrator Interrupts Operation ===");
        Date tomorrow = sdf.parse("2023-10-27");

        // Administrator -> AttendanceController: displayAttendanceForm(tomorrow)
        attendanceController.displayAttendanceForm(tomorrow);

        // Administrator -> AttendanceController: cancelOperation()
        // Exit Conditions: The administrator interrupts the operation.
        attendanceController.cancelOperation();
        System.out.println("=== SCENARIO 2 COMPLETED ===\n");
        TimeUnit.SECONDS.sleep(2);


        // --- Scenario 3: Connection to SMOS server interrupted ---
        System.out.println("=== SCENARIO 3: Connection to SMOS Server Interrupted during Notification ===");
        Date dayAfterTomorrow = sdf.parse("2023-10-28");

        // Administrator displays form for new date
        attendanceController.displayAttendanceForm(dayAfterTomorrow);

        // Simulate attendance data with absents
        Map<String, List<String>> attendanceDataFailure = new HashMap<>();
        attendanceDataFailure.put("S001", Arrays.asList(AttendanceStatus.ABSENT.name())); // Emma Smith
        attendanceDataFailure.put("S002", Arrays.asList(AttendanceStatus.PRESENT.name()));
        attendanceDataFailure.put("S003", Arrays.asList(AttendanceStatus.ABSENT.name())); // Olivia Davis

        // Administrator saves attendance
        attendanceController.saveAttendance(attendanceDataFailure);

        // Simulate SMOS server becoming unavailable
        SMOSServer.setAvailable(false);

        // Attempt asynchronous notification processing again
        System.out.println("\n--- Initiating Asynchronous Notification Processing (with SMOS server down) ---");
        processedCount = 0;
        messageQueueService = new MessageQueueService(); // Reset queue for this scenario
        final MessageQueueService finalMessageQueueServiceForLambda = messageQueueService; // Make effectively final for lambda usage
        // Republish events to simulate new attempts after the server went down
        attendanceDataFailure.entrySet().stream()
                .filter(e -> AttendanceStatus.ABSENT.name().equals(e.getValue().get(0)))
                .forEach(e -> {
                    com.example.attendancetracker.model.Student student = studentRepo.findById(e.getKey());
                    Parent parent = studentRepo.findParentByStudentId(student.getStudentId());
                    if (parent != null) {
                        finalMessageQueueServiceForLambda.publish(new NotificationEvent(student.getStudentId(), parent.getEmail(), dayAfterTomorrow));
                    }
                });

        while (!messageQueueService.isEmpty() && processedCount < maxAttempts) {
            NotificationEvent event = messageQueueService.consume();
            if (event != null) {
                notificationService.processNotificationEvent(event);
                processedCount++;
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }
        System.out.println("--- Asynchronous Notification Processing Finished (with SMOS server down) ---");
        System.out.println("Total events processed: " + messageQueueService.getProcessedEventsCount());
        System.out.println("Total events failed: " + messageQueueService.getFailedEventsCount());
        System.out.println("Exit Conditions: Connection to the interrupted SMOS server.");

        // Reset SMOS server availability for potential future use or clean shutdown
        SMOSServer.setAvailable(true);

        System.out.println("\n=== SCENARIO 3 COMPLETED ===\n");

        System.out.println("--- Attendance Tracking System Shut Down ---");
    }
}
