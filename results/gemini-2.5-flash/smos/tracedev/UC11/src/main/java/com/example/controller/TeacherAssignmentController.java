package com.example.controller;

import com.example.model.AcademicYear;
import com.example.model.SchoolClass; // Renamed
import com.example.model.Teacher;
import com.example.model.TeacherAssignment;
import com.example.model.Teaching;
import com.example.service.TeacherAssignmentService;
import com.example.view.TeacherAssignmentView;

import java.util.Collections;
import java.util.List;

/**
 * MVC Controller for managing teacher assignments.
 * Handles user input from the view, interacts with the service layer, and updates the view.
 */
public class TeacherAssignmentController {
    private final TeacherAssignmentService teacherAssignmentService;
    private final TeacherAssignmentView teacherAssignmentView;

    public TeacherAssignmentController(TeacherAssignmentService teacherAssignmentService,
                                      TeacherAssignmentView teacherAssignmentView) {
        this.teacherAssignmentService = teacherAssignmentService;
        this.teacherAssignmentView = teacherAssignmentView;
    }

    /**
     * Displays the initial form for managing teacher teachings.
     *
     * // EC1: Precondition: Administrator is logged in. (Assumed by caller/system setup)
     * // EC3: Precondition: UseCase 'viewdetTailsente' has been executed (teacher details are loaded). (Assumed by caller/system setup)
     * @param teacherId The ID of the teacher for whom to manage assignments.
     */
    public void showTeachingManagementForm(String teacherId) {
        System.out.println("\n[Controller] Received request to show teaching management form for teacher ID: " + teacherId);
        try {
            // Retrieve teacher details
            Teacher teacher = teacherAssignmentService.getTeacherDetails(teacherId);
            // Retrieve available academic years
            List<AcademicYear> academicYears = teacherAssignmentService.getAvailableAcademicYears();
            // Retrieve current assignments for the teacher
            List<TeacherAssignment> currentAssignments = teacherAssignmentService.getCurrentTeacherAssignments(teacherId);

            // Display the form (initially without classes or teachings loaded)
            teacherAssignmentView.displayManagementForm(teacher, academicYears, null, null, currentAssignments);
        } catch (Exception e) {
            teacherAssignmentView.showErrorMessage("Failed to load management form: " + e.getMessage());
            // XC2: Exit Condition: System handles connection interruption gracefully.
            // In a real system, this would involve more sophisticated error handling and possibly logging.
            System.err.println("Error in showTeachingManagementForm: " + e.getMessage());
        }
    }

    /**
     * Handles the selection of an academic year by the user.
     * Renders the classes available for the selected academic year.
     * @param teacherId The ID of the teacher.
     * @param academicYearId The ID of the selected academic year.
     */
    public void handleAcademicYearSelection(String teacherId, String academicYearId) {
        System.out.println("\n[Controller] Academic Year selected: " + academicYearId + " for teacher: " + teacherId);
        try {
            // Get classes for the selected academic year
            List<SchoolClass> classes = teacherAssignmentService.getClassesByAcademicYear(academicYearId); // Renamed
            // Render the classes in the view
            teacherAssignmentView.renderClasses(classes);
        } catch (Exception e) {
            teacherAssignmentView.showErrorMessage("Failed to load classes: " + e.getMessage());
            System.err.println("Error in handleAcademicYearSelection: " + e.getMessage());
        }
    }

    /**
     * Handles the selection of a class by the user.
     * Renders the teachings offered in the selected class, along with the teacher's current assignments.
     * @param teacherId The ID of the teacher.
     * @param academicYearId The ID of the selected academic year (kept for context, not strictly used here).
     * @param schoolClassId The ID of the selected school class.
     */
    public void handleClassSelection(String teacherId, String academicYearId, String schoolClassId) { // Renamed classId to schoolClassId
        System.out.println("\n[Controller] Class selected: " + schoolClassId + " for teacher: " + teacherId);
        try {
            // Get teachings for the selected class
            List<Teaching> teachings = teacherAssignmentService.getTeachingsByClass(schoolClassId); // Renamed
            // Get the current assignments for the teacher (to mark checkboxes)
            List<TeacherAssignment> currentAssignments = teacherAssignmentService.getCurrentTeacherAssignments(teacherId);
            // Render the teachings and current assignments in the view
            teacherAssignmentView.renderTeachings(teachings, currentAssignments);
        } catch (Exception e) {
            teacherAssignmentView.showErrorMessage("Failed to load teachings: " + e.getMessage());
            System.err.println("Error in handleClassSelection: " + e.getMessage());
        }
    }

    /**
     * Handles the update (save) action for teacher teachings.
     *
     * @param teacherId The ID of the teacher.
     * @param selectedTeachingIds A list of IDs of teachings that should be assigned to the teacher.
     */
    public void handleTeachingUpdate(String teacherId, List<String> selectedTeachingIds) {
        System.out.println("\n[Controller] Teaching update requested for teacher ID: " + teacherId + ", selected: " + selectedTeachingIds);
        try {
            // Call the service to update assignments
            teacherAssignmentService.updateTeacherTeachings(teacherId, selectedTeachingIds);
            teacherAssignmentView.showSuccessMessage("Teacher assignments updated successfully for " + teacherId + ".");
            // Optionally, refresh the form after update to reflect changes
            // showTeachingManagementForm(teacherId);
        } catch (Exception e) {
            teacherAssignmentView.showErrorMessage("Failed to update teacher assignments: " + e.getMessage());
            System.err.println("Error in handleTeachingUpdate: " + e.getMessage());
        }
    }
}