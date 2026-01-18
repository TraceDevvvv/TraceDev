
package com.example.controller;

import com.example.dto.*;
import com.example.entity.*;
import com.example.repository.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for managing teaching assignments.
 * Implements the use case flow as per the sequence diagram.
 */
public class ManageTeachingAssignmentsController {
    private TeacherRepository teacherRepository;
    private ClassRepository classRepository;
    private TeachingRepository teachingRepository;
    private TeachingAssignmentRepository teachingAssignmentRepository;

    public ManageTeachingAssignmentsController(TeacherRepository teacherRepository,
                                              ClassRepository classRepository,
                                              TeachingRepository teachingRepository,
                                              TeachingAssignmentRepository teachingAssignmentRepository) {
        this.teacherRepository = teacherRepository;
        this.classRepository = classRepository;
        this.teachingRepository = teachingRepository;
        this.teachingAssignmentRepository = teachingAssignmentRepository;
    }

    /**
     * Retrieves teacher details along with current assignments for the current year.
     * Corresponds to Step 1 in the sequence diagram.
     * @param teacherId the ID of the teacher
     * @return TeacherDetailsDTO containing teacher info and assignments
     */
    public TeacherDetailsDTO getTeacherDetails(String teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId);
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher not found: " + teacherId);
        }
        // Assuming current year is provided by the system; here we use a placeholder.
        String currentYear = "2023-2024";
        List<TeachingAssignment> assignments = teachingAssignmentRepository.findByTeacherAndYear(teacher, currentYear);
        List<TeachingAssignmentDTO> assignmentDTOs = assignments.stream()
                .map(ta -> new TeachingAssignmentDTO(
                        ta.getId(),
                        new TeachingDTO(ta.getTeaching().getId(), ta.getTeaching().getCode(), ta.getTeaching().getName()),
                        true))
                .collect(Collectors.toList());
        return new TeacherDetailsDTO(teacher.getId(), teacher.getName(), assignmentDTOs);
    }

    /**
     * Retrieves classes for a given academic year.
     * Corresponds to Step 3 in the sequence diagram.
     * @param academicYear the academic year
     * @return list of ClassDTO objects
     */
    public List<ClassDTO> getClassesForYear(String academicYear) {
        List<com.example.entity.Class> classes = classRepository.findByAcademicYear(academicYear);
        return classes.stream()
                .map(c -> new ClassDTO(c.getId(), c.getName(), c.getAcademicYear()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves teachings for a given class.
     * Corresponds to Step 5 in the sequence diagram.
     * @param classId the ID of the class
     * @return list of TeachingDTO objects
     */
    public List<TeachingDTO> getTeachingsForClass(String classId) {
        com.example.entity.Class classEntity = classRepository.findById(classId);
        if (classEntity == null) {
            throw new IllegalArgumentException("Class not found: " + classId);
        }
        List<Teaching> teachings = classEntity.getTeachings();
        return teachings.stream()
                .map(t -> new TeachingDTO(t.getId(), t.getCode(), t.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Updates teaching assignments for a teacher.
     * Corresponds to Step 7 in the sequence diagram.
     * Quality requirement: duration ≤ 5 seconds.
     * @param request the update request DTO
     */
    public void updateAssignments(UpdateAssignmentsRequestDTO request) {
        long startTime = System.currentTimeMillis();
        Teacher teacher = teacherRepository.findById(request.getTeacherId());
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher not found: " + request.getTeacherId());
        }

        // Add new assignments
        if (request.getAssignmentsToAdd() != null) {
            for (String teachingId : request.getAssignmentsToAdd()) {
                com.example.entity.Class classEntity = classRepository.findById(request.getClassId());
                if (classEntity == null) {
                    throw new IllegalArgumentException("Class not found: " + request.getClassId());
                }
                Teaching teaching = teachingRepository.findById(teachingId);
                if (teaching == null) {
                    throw new IllegalArgumentException("Teaching not found: " + teachingId);
                }
                TeachingAssignment newAssignment = new TeachingAssignment(teacher, teaching, request.getAcademicYear());
                teachingAssignmentRepository.save(newAssignment);
            }
        }

        // Remove existing assignments
        if (request.getAssignmentsToRemove() != null) {
            for (String assignmentId : request.getAssignmentsToRemove()) {
                TeachingAssignment assignment = teachingAssignmentRepository.findById(assignmentId);
                if (assignment != null) {
                    teachingAssignmentRepository.delete(assignment);
                }
            }
        }

        // Final update: save teacher (though not strictly necessary if only assignments changed)
        teacherRepository.save(teacher);

        long duration = System.currentTimeMillis() - startTime;
        if (duration > 5000) {
            System.err.println("Warning: updateAssignments took " + duration + " ms, exceeding 5-second limit.");
        }
    }

    /**
     * Selects an academic year (message from sequence diagram).
     * This method is called by the UI.
     * @param year the academic year
     */
    public void selectAcademicYear(String year) {
        // This method would typically delegate to getClassesForYear, but it's already used by UI.
        // For traceability, we ensure the method exists.
        getClassesForYear(year);
    }

    /**
     * Selects a class (message from sequence diagram).
     * This method is called by the UI.
     * @param classId the class ID
     */
    public void selectClass(String classId) {
        // This method would typically delegate to getTeachingsForClass, but it's already used by UI.
        // For traceability, we ensure the method exists.
        getTeachingsForClass(classId);
    }

    /**
     * Toggles a teaching assignment (message from sequence diagram).
     * This method would be called by the UI when a checkbox is toggled.
     * @param teachingId the teaching ID
     * @param assigned true if assigned, false if unassigned
     */
    public void toggleTeachingAssignment(String teachingId, boolean assigned) {
        // This is a UI-level action; the actual update is performed later via updateAssignments.
        // For traceability, we provide a placeholder method.
        System.out.println("Toggle teaching assignment: teachingId=" + teachingId + ", assigned=" + assigned);
    }

    /**
     * Submits assignments (message from sequence diagram).
     * This method is called by the UI to finalize changes.
     * @param request the update request DTO
     */
    public void submitAssignments(UpdateAssignmentsRequestDTO request) {
        updateAssignments(request);
    }

    /**
     * Find Teaching by id (message from sequence diagram).
     * @param id the teaching ID
     * @return the Teaching entity
     */
    public Teaching findTeachingById(String id) {
        return teachingRepository.findById(id);
    }

    /**
     * Create a new TeachingAssignment (message from sequence diagram).
     * @param teacher the teacher
     * @param teaching the teaching
     * @param year the academic year
     * @return the saved TeachingAssignment
     */
    public TeachingAssignment newTeachingAssignment(Teacher teacher, Teaching teaching, String year) {
        TeachingAssignment assignment = new TeachingAssignment(teacher, teaching, year);
        return teachingAssignmentRepository.save(assignment);
    }

    /**
     * Return saved assignment (message from sequence diagram).
     * This is essentially the same as saving, but for traceability we add a method.
     * @param assignment the assignment to save
     * @return the saved assignment
     */
    public TeachingAssignment savedAssignment(TeachingAssignment assignment) {
        return teachingAssignmentRepository.save(assignment);
    }
}
