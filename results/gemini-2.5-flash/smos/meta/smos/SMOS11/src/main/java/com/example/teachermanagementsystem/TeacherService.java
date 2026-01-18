package com.example.teachermanagementsystem;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service layer for managing Teacher data.
 * This class provides business logic and acts as an intermediary between the UI/controller
 * and the TeacherRepository. It encapsulates operations related to teachers,
 * specifically assigning and removing teachings.
 */
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeachingRepository teachingRepository; // Dependency to validate teachings

    /**
     * Constructs a new TeacherService with given repositories.
     * @param teacherRepository The repository to use for teacher data access.
     * @param teachingRepository The repository to use for teaching data access (for validation).
     */
    public TeacherService(TeacherRepository teacherRepository, TeachingRepository teachingRepository) {
        this.teacherRepository = teacherRepository;
        this.teachingRepository = teachingRepository;
    }

    /**
     * Retrieves a teacher by their unique identifier.
     * This method is used when the system is "Viewing the details of a teacher".
     * @param teacherId The ID of the teacher to find.
     * @return An {@code Optional} containing the {@code Teacher} if found,
     *         or an empty {@code Optional} if no teacher with the given ID exists.
     */
    public Optional<Teacher> getTeacherById(String teacherId) {
        return teacherRepository.findById(teacherId);
    }

    /**
     * Assigns a teaching to a specific teacher.
     * This method handles the business logic for associating a teaching with a teacher.
     * It validates the existence of both the teacher and the teaching before performing the assignment.
     *
     * @param teacherId The ID of the teacher to whom the teaching will be assigned.
     * @param teachingId The ID of the teaching to assign.
     * @return true if the teaching was successfully assigned, false if the teacher or teaching
     *         does not exist, or if the teaching was already assigned to the teacher.
     */
    public boolean assignTeachingToTeacher(String teacherId, String teachingId) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
        Optional<Teaching> teachingOpt = teachingRepository.findById(teachingId);

        if (teacherOpt.isEmpty()) {
            System.out.println("Error: Teacher with ID " + teacherId + " not found.");
            return false;
        }
        if (teachingOpt.isEmpty()) {
            System.out.println("Error: Teaching with ID " + teachingId + " not found.");
            return false;
        }

        Teacher teacher = teacherOpt.get();
        Teaching teaching = teachingOpt.get();

        // Attempt to assign the teaching. The Teacher class handles uniqueness.
        boolean assigned = teacher.assignTeaching(teaching);
        if (assigned) {
            teacherRepository.save(teacher); // Persist the change
            System.out.println("Teaching '" + teaching.getName() + "' assigned to teacher '" + teacher.getName() + "'.");
        } else {
            System.out.println("Teaching '" + teaching.getName() + "' was already assigned to teacher '" + teacher.getName() + "'.");
        }
        return assigned;
    }

    /**
     * Removes a teaching from a specific teacher.
     * This method handles the business logic for disassociating a teaching from a teacher.
     * It validates the existence of both the teacher and the teaching before attempting removal.
     *
     * @param teacherId The ID of the teacher from whom the teaching will be removed.
     * @param teachingId The ID of the teaching to remove.
     * @return true if the teaching was successfully removed, false if the teacher or teaching
     *         does not exist, or if the teaching was not assigned to the teacher.
     */
    public boolean removeTeachingFromTeacher(String teacherId, String teachingId) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
        Optional<Teaching> teachingOpt = teachingRepository.findById(teachingId);

        if (teacherOpt.isEmpty()) {
            System.out.println("Error: Teacher with ID " + teacherId + " not found.");
            return false;
        }
        if (teachingOpt.isEmpty()) {
            System.out.println("Error: Teaching with ID " + teachingId + " not found.");
            return false;
        }

        Teacher teacher = teacherOpt.get();
        Teaching teaching = teachingOpt.get();

        // Attempt to remove the teaching.
        boolean removed = teacher.removeTeaching(teaching);
        if (removed) {
            teacherRepository.save(teacher); // Persist the change
            System.out.println("Teaching '" + teaching.getName() + "' removed from teacher '" + teacher.getName() + "'.");
        } else {
            System.out.println("Teaching '" + teaching.getName() + "' was not assigned to teacher '" + teacher.getName() + "'.");
        }
        return removed;
    }

    /**
     * Retrieves all teachers in the system.
     * @return A list of all {@code Teacher} objects.
     */
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    /**
     * Retrieves the names of teachings currently assigned to a specific teacher.
     * @param teacherId The ID of the teacher.
     * @return A list of teaching names, or an empty list if the teacher is not found or has no assigned teachings.
     */
    public List<String> getAssignedTeachingNamesForTeacher(String teacherId) {
        return teacherRepository.findById(teacherId)
                .map(teacher -> teacher.getAssignedTeachings().stream()
                        .map(Teaching::getName)
                        .collect(Collectors.toList()))
                .orElse(List.of()); // Return an empty list if teacher not found
    }

    /**
     * Retrieves the actual Teaching objects currently assigned to a specific teacher.
     * @param teacherId The ID of the teacher.
     * @return A set of Teaching objects, or an empty set if the teacher is not found or has no assigned teachings.
     */
    public Set<Teaching> getAssignedTeachingsForTeacher(String teacherId) {
        return teacherRepository.findById(teacherId)
                .map(Teacher::getAssignedTeachings)
                .orElse(Set.of()); // Return an empty set if teacher not found
    }
}