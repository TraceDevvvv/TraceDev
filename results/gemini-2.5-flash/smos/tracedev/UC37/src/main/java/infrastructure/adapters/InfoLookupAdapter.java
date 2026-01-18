package infrastructure.adapters;

import infrastructure.ports.IInfoLookupService;

/**
 * Adapter implementation for the IInfoLookupService port.
 * This class simulates looking up information from external systems
 * (e.g., student management system, HR system).
 */
public class InfoLookupAdapter implements IInfoLookupService {

    public InfoLookupAdapter() {
        System.out.println("InfoLookupAdapter: Initialized.");
    }

    /**
     * Simulates fetching a parent's email based on student ID.
     * In a real system, this would query a student information system.
     *
     * @param studentId The ID of the student.
     * @return A dummy parent email for demonstration.
     */
    @Override
    public String getParentEmail(String studentId) {
        System.out.println("InfoLookupAdapter: Looking up parent email for student ID: " + studentId);
        // Simulate fetching from an external system
        if ("student123".equals(studentId)) {
            return "parent.of.student123@example.com";
        }
        if ("student456".equals(studentId)) {
            return "parent.of.student456@example.com";
        }
        return "default.parent@example.com"; // Default for unknown students
    }

    /**
     * Simulates fetching a student's name based on student ID.
     *
     * @param studentId The ID of the student.
     * @return A dummy student name for demonstration.
     */
    @Override
    public String getStudentName(String studentId) {
        System.out.println("InfoLookupAdapter: Looking up student name for student ID: " + studentId);
        // Simulate fetching from an external system
        if ("student123".equals(studentId)) {
            return "Alice Smith";
        }
        if ("student456".equals(studentId)) {
            return "Bob Johnson";
        }
        return "Unknown Student";
    }

    /**
     * Simulates fetching a teacher's name based on teacher ID.
     *
     * @param teacherId The ID of the teacher.
     * @return A dummy teacher name for demonstration.
     */
    @Override
    public String getTeacherName(String teacherId) {
        System.out.println("InfoLookupAdapter: Looking up teacher name for teacher ID: " + teacherId);
        // Simulate fetching from an external system
        if ("teacher001".equals(teacherId)) {
            return "Mr. David Lee";
        }
        if ("teacher002".equals(teacherId)) {
            return "Ms. Emily White";
        }
        return "Unknown Teacher";
    }
}