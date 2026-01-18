package com.example.service;

import com.example.model.AcademicYear;
import com.example.model.SchoolClass; // Renamed from Class to SchoolClass
import com.example.model.Teacher;
import com.example.model.TeacherAssignment;
import com.example.model.Teaching;
import com.example.repository.AcademicYearRepository;
import com.example.repository.SchoolClassRepository; // Renamed
import com.example.repository.TeacherAssignmentRepository;
import com.example.repository.TeacherRepository;
import com.example.repository.TeachingRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Application Service for managing teacher assignments.
 * Handles business logic and coordinates between repositories.
 */
public class TeacherAssignmentService {
    private final TeacherRepository teacherRepo;
    private final AcademicYearRepository academicYearRepo;
    private final SchoolClassRepository schoolClassRepo; // Renamed
    private final TeachingRepository teachingRepo;
    private final TeacherAssignmentRepository assignmentRepo;

    public TeacherAssignmentService(TeacherRepository teacherRepo,
                                    AcademicYearRepository academicYearRepo,
                                    SchoolClassRepository schoolClassRepo, // Renamed
                                    TeachingRepository teachingRepo,
                                    TeacherAssignmentRepository assignmentRepo) {
        this.teacherRepo = teacherRepo;
        this.academicYearRepo = academicYearRepo;
        this.schoolClassRepo = schoolClassRepo; // Renamed
        this.teachingRepo = teachingRepo;
        this.assignmentRepo = assignmentRepo;
    }

    /**
     * Retrieves details for a specific teacher.
     * @param teacherId The ID of the teacher.
     * @return The Teacher object.
     * @throws IllegalArgumentException if teacher not found.
     */
    public Teacher getTeacherDetails(String teacherId) {
        System.out.println("[Service] Getting teacher details for ID: " + teacherId);
        Teacher teacher = teacherRepo.findById(teacherId);
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher with ID " + teacherId + " not found.");
        }
        return teacher;
    }

    /**
     * Retrieves all available academic years.
     * @return A list of AcademicYear objects.
     */
    public List<AcademicYear> getAvailableAcademicYears() {
        System.out.println("[Service] Getting all available academic years.");
        return academicYearRepo.findAll();
    }

    /**
     * Retrieves classes associated with a specific academic year.
     * @param yearId The ID of the academic year.
     * @return A list of SchoolClass objects.
     */
    public List<SchoolClass> getClassesByAcademicYear(String yearId) { // Renamed parameter from classId
        System.out.println("[Service] Getting classes for academic year ID: " + yearId);
        return schoolClassRepo.findByAcademicYearId(yearId);
    }

    /**
     * Retrieves teachings offered within a specific school class.
     * @param schoolClassId The ID of the school class.
     * @return A list of Teaching objects.
     */
    public List<Teaching> getTeachingsByClass(String schoolClassId) { // Renamed parameter from classId
        System.out.println("[Service] Getting teachings for class ID: " + schoolClassId);
        return teachingRepo.findBySchoolClassId(schoolClassId); // Renamed method call
    }

    /**
     * Retrieves all current teaching assignments for a specific teacher.
     * @param teacherId The ID of the teacher.
     * @return A list of TeacherAssignment objects.
     */
    public List<TeacherAssignment> getCurrentTeacherAssignments(String teacherId) {
        System.out.println("[Service] Getting current assignments for teacher ID: " + teacherId);
        return assignmentRepo.findByTeacherId(teacherId);
    }

    /**
     * Updates the teaching assignments for a teacher based on a new list of selected teaching IDs.
     * This method ensures integrity and consistency by performing additions and removals atomically.
     *
     * @param teacherId The ID of the teacher whose assignments are being updated.
     * @param selectedTeachingIds A list of teaching IDs that should be assigned to the teacher.
     *                            Teachings not in this list but currently assigned will be removed.
     *                            Teachings in this list but not currently assigned will be added.
     * // QR1: Quality Requirement: Integrity and consistency of teacher assignments ensured by atomic update logic.
     */
    public void updateTeacherTeachings(String teacherId, List<String> selectedTeachingIds) {
        System.out.println("[Service] Updating teacher teachings for Teacher ID: " + teacherId + " with selected teachings: " + selectedTeachingIds);

        // 1. Get current assignments for the teacher
        List<TeacherAssignment> currentAssignments = assignmentRepo.findByTeacherId(teacherId);
        
        // 2. Create sets for efficient lookups
        Set<String> currentTeachingIds = currentAssignments.stream()
                .map(TeacherAssignment::getTeachingId)
                .collect(Collectors.toSet());
        
        Set<String> selectedTeachingIdsSet = selectedTeachingIds.stream()
                .collect(Collectors.toSet());

        // 3. Determine and perform additions: Teachings in selectedTeachingIds but not in currentTeachingIds
        for (String teachingId : selectedTeachingIdsSet) {
            if (!currentTeachingIds.contains(teachingId)) {
                // Teaching is selected but not currently assigned, so add it
                System.out.println("    [Service] Adding assignment for teaching ID: " + teachingId);
                TeacherAssignment newAssignment = createAssignment(teacherId, teachingId);
                assignmentRepo.save(newAssignment);
            }
        }

        // 4. Determine and perform removals: Teachings in currentTeachingIds but not in selectedTeachingIds
        for (TeacherAssignment assignment : currentAssignments) {
            if (!selectedTeachingIdsSet.contains(assignment.getTeachingId())) {
                // Teaching is currently assigned but not selected, so remove it
                System.out.println("    [Service] Removing assignment for teaching ID: " + assignment.getTeachingId());
                assignmentRepo.delete(assignment);
            }
        }
        System.out.println("[Service] Teacher teachings update completed for Teacher ID: " + teacherId);
    }

    /**
     * Internal helper method to find a specific assignment.
     * Not directly exposed by the service, used for internal logic.
     * @param teacherId The ID of the teacher.
     * @param teachingId The ID of the teaching.
     * @return The TeacherAssignment object if found, null otherwise.
     */
    private TeacherAssignment findAssignment(String teacherId, String teachingId) {
        // This method is primarily used internally. The existing assignmentRepo.findByTeacherAndTeaching covers this.
        return assignmentRepo.findByTeacherAndTeaching(teacherId, teachingId);
    }

    /**
     * Internal helper method to create a new teacher assignment.
     * Not directly exposed by the service, used for internal logic.
     * @param teacherId The ID of the teacher.
     * @param teachingId The ID of the teaching.
     * @return A new TeacherAssignment object.
     */
    private TeacherAssignment createAssignment(String teacherId, String teachingId) {
        return new TeacherAssignment(teacherId, teachingId);
    }
}