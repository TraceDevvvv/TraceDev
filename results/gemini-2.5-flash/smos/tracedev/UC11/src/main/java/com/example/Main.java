
package com.example;

import com.example.controller.TeacherAssignmentController;
import com.example.model.AcademicYear;
import com.example.model.SchoolClass;
import com.example.model.Teacher;
import com.example.model.TeacherAssignment; // Added import for TeacherAssignment
import com.example.model.Teaching;
import com.example.repository.*;
import com.example.service.TeacherAssignmentService;
import com.example.view.TeacherAssignmentView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Main application class to demonstrate the Teacher Assignment Management system.
 * This class sets up the dependencies (repositories, service, controller, view)
 * and simulates user interactions based on the sequence diagram.
 */
public class Main {

    public static void main(String[] args) {
        // --- 1. Initialize Repositories (In-memory implementations) ---
        TeacherRepository teacherRepo = new InMemoryTeacherRepository();
        AcademicYearRepository academicYearRepo = new InMemoryAcademicYearRepository();
        SchoolClassRepository schoolClassRepo = new InMemorySchoolClassRepository(); // Renamed
        TeachingRepository teachingRepo = new InMemoryTeachingRepository();
        TeacherAssignmentRepository assignmentRepo = new InMemoryTeacherAssignmentRepository();

        // --- 2. Initialize Service Layer ---
        TeacherAssignmentService assignmentService = new TeacherAssignmentService(
                teacherRepo, academicYearRepo, schoolClassRepo, teachingRepo, assignmentRepo
        );

        // --- 3. Initialize View and Controller ---
        TeacherAssignmentView assignmentView = new TeacherAssignmentView();
        TeacherAssignmentController assignmentController = new TeacherAssignmentController(
                assignmentService, assignmentView
        );

        Scanner scanner = new Scanner(System.in);

        // --- Simulate the Sequence Diagram Flow ---
        String teacherId = "TeacherX"; // We'll manage assignments for "TeacherX"

        System.out.println("--- Starting Teacher Assignment Management Simulation ---");

        // Admin -> View : Clicks "Teaching Management" for TeacherX
        // View -> Controller : showTeachingManagementForm(teacherId: "TeacherX")
        System.out.println("\n[Admin Action] Administrator clicks 'Teaching Management' for TeacherX.");
        assignmentController.showTeachingManagementForm(teacherId);

        // Admin -> View : Selects Academic Year Y1
        // View -> Controller : handleAcademicYearSelection(teacherId: "TeacherX", academicYearId: "Y1")
        System.out.println("\n[Admin Action] Administrator selects Academic Year Y1.");
        String selectedAcademicYearId = "Y1";
        assignmentController.handleAcademicYearSelection(teacherId, selectedAcademicYearId);

        // Admin -> View : Selects Class C1
        // View -> Controller : handleClassSelection(teacherId: "TeacherX", academicYearId: "Y1", classId: "C1")
        System.out.println("\n[Admin Action] Administrator selects Class C1 (Mathematics 101).");
        String selectedSchoolClassId = "C1"; // Renamed
        assignmentController.handleClassSelection(teacherId, selectedAcademicYearId, selectedSchoolClassId);

        // Admin -> View : Selects/deselects teachings (e.g., T1, T3 selected) and clicks Save
        // Initial assignments for TeacherX were T1, T2.
        // Let's simulate: keep T1, remove T2, add T3 (which is Classical Mechanics from class C2, but we simulate it being available here if chosen)
        // For C1, available teachings are T1 (Algebra) and T2 (Geometry).
        // Let's assume the user now *unchecks* T2 and checks T1 and T3 (if T3 was somehow made available on this screen).
        // Since T3 is from C2, not C1, in a real UI, it wouldn't appear as a checkbox for C1.
        // For this simulation, we'll assume the view mechanism would allow selection from *any* available teaching if it makes sense.
        // Given the sequence diagram, the `handleClassSelection` displays teachings FOR THAT CLASS.
        // So, the user can only select from T1, T2 (for C1).
        // Let's assume the user *keeps* T1, and *deselects* T2. The sequence diagram states "T1, T3 selected".
        // This implies T3 might be available from other classes, or it's a simplification.
        // To strictly follow the sequence diagram for "T1, T3 selected" in an `updateTeacherTeachings` call:
        // Current assignments for TeacherX: [T1, T2]
        // New selected teachings: [T1, T3]
        // Expected outcome: T2 removed, T3 added.

        System.out.println("\n[Admin Action] Administrator selects/deselects teachings (e.g., T1, T3 selected) and clicks Save.");
        List<String> newSelectedTeachingIds = Arrays.asList("T1", "T3"); // T1 (Algebra, C1), T3 (Classical Mechanics, C2)
        assignmentController.handleTeachingUpdate(teacherId, newSelectedTeachingIds);

        // Verify the outcome (optional, but good for testing)
        System.out.println("\n--- Verification after update ---");
        List<TeacherAssignment> finalAssignments = assignmentRepo.findByTeacherId(teacherId);
        System.out.println("TeacherX's Final Assignments: " +
                finalAssignments.stream()
                        .map(ta -> ta.getTeachingId() + " (" + assignmentService.getTeachingsByClass(ta.getTeachingId() != null ? teachingRepo.findById(ta.getTeachingId()).getSchoolClassId() : "N/A").stream().filter(t -> t.getId().equals(ta.getTeachingId())).findFirst().orElse(new Teaching("N/A", "N/A", "N/A")).getName() + ")")
                        .collect(Collectors.toList()));


        System.out.println("\n--- End of Simulation ---");

        scanner.close();
    }
}
