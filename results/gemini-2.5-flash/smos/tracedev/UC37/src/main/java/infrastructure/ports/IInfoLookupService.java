package infrastructure.ports;

/**
 * Port interface for looking up external information.
 * This defines the contract for retrieving details like parent emails, student names,
 * and teacher names from external systems.
 */
public interface IInfoLookupService {

    /**
     * Retrieves the parent's email address for a given student ID.
     *
     * @param studentId The ID of the student.
     * @return The email address of the student's parent, or null if not found.
     */
    String getParentEmail(String studentId);

    /**
     * Retrieves the full name of a student for a given student ID.
     *
     * @param studentId The ID of the student.
     * @return The student's full name, or null if not found.
     */
    String getStudentName(String studentId);

    /**
     * Retrieves the full name of a teacher for a given teacher ID.
     *
     * @param teacherId The ID of the teacher.
     * @return The teacher's full name, or null if not found.
     */
    String getTeacherName(String teacherId);
}