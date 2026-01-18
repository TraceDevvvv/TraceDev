package com.example.repository;

import com.example.model.TeacherAssignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Date; // For setting assigned date

/**
 * In-memory implementation of TeacherAssignmentRepository for demonstration purposes.
 * Stores TeacherAssignment objects in a HashMap.
 */
public class InMemoryTeacherAssignmentRepository implements TeacherAssignmentRepository {
    // Map to store assignments, using assignment ID as key
    private final Map<String, TeacherAssignment> assignments = new HashMap<>();

    public InMemoryTeacherAssignmentRepository() {
        // Seed with some initial data for TeacherX
        // TeacherX initially assigned to T1 (Algebra Fundamentals) and T2 (Geometry Basics)
        TeacherAssignment ta1 = new TeacherAssignment("assign_id_1", "TeacherX", "T1", new Date());
        TeacherAssignment ta2 = new TeacherAssignment("assign_id_2", "TeacherX", "T2", new Date());
        assignments.put(ta1.getId(), ta1);
        assignments.put(ta2.getId(), ta2);

        // Another assignment for TeacherY
        TeacherAssignment ta3 = new TeacherAssignment("assign_id_3", "TeacherY", "T3", new Date());
        assignments.put(ta3.getId(), ta3);
    }

    @Override
    public List<TeacherAssignment> findByTeacherId(String teacherId) {
        System.out.println("[AssignmentRepo] Finding assignments for Teacher ID: " + teacherId);
        return assignments.values().stream()
                .filter(a -> a.getTeacherId().equals(teacherId))
                .collect(Collectors.toList());
    }

    @Override
    public TeacherAssignment findByTeacherAndTeaching(String teacherId, String teachingId) {
        System.out.println("[AssignmentRepo] Finding assignment for Teacher ID: " + teacherId + " and Teaching ID: " + teachingId);
        return assignments.values().stream()
                .filter(a -> a.getTeacherId().equals(teacherId) && a.getTeachingId().equals(teachingId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(TeacherAssignment assignment) {
        System.out.println("[AssignmentRepo] Saving assignment: " + assignment.getId() + " for Teacher: " + assignment.getTeacherId() + ", Teaching: " + assignment.getTeachingId());
        assignments.put(assignment.getId(), assignment);
    }

    @Override
    public void delete(TeacherAssignment assignment) {
        System.out.println("[AssignmentRepo] Deleting assignment: " + assignment.getId() + " for Teacher: " + assignment.getTeacherId() + ", Teaching: " + assignment.getTeachingId());
        assignments.remove(assignment.getId());
    }

    @Override
    public TeacherAssignment findById(String id) {
        return assignments.get(id);
    }
}