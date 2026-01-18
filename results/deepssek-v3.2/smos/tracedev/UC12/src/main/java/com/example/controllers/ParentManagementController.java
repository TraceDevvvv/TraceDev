package com.example.controllers;

import com.example.ui.ChildManagementForm;
import com.example.entities.Parent;
import com.example.entities.Student;
import com.example.repositories.IParentRepository;
import com.example.repositories.IStudentRepository;
import com.example.data.IUnitOfWork;
import com.example.serv.AuthenticationService;
import java.util.List;

/**
 * Controller for managing parental associations.
 * Implements the flow described in the sequence diagram.
 * Corresponds to ParentManagementController in the class diagram.
 */
public class ParentManagementController {
    private IParentRepository parentRepository;
    private IStudentRepository studentRepository;
    private IUnitOfWork unitOfWork;
    private AuthenticationService authService;

    public ParentManagementController(IParentRepository repo1, IStudentRepository repo2, IUnitOfWork uow) {
        this.parentRepository = repo1;
        this.studentRepository = repo2;
        this.unitOfWork = uow;
        this.authService = new AuthenticationService();
    }

    /**
     * Validates that the administrator session is active.
     * Added to resolve sequence diagram -> class diagram inconsistency.
     */
    public boolean validateAdminSession() {
        // In a real scenario, we would get the current user ID from session.
        String userId = "admin123"; // Simulated
        return authService.validateSession(userId);
    }

    /**
     * Displays the child management form for a given parent.
     * Corresponds to displayChildManagementForm(parentId : int) in class diagram.
     * Returns a FormData object containing the form and data.
     */
    public FormData displayChildManagementForm(int parentId) {
        // Precondition: administrator must be authenticated.
        if (!validateAdminSession()) {
            throw new SecurityException("Administrator not authenticated.");
        }

        // 1. Fetch parent by ID (via ParentRepository).
        Parent parent = parentRepository.findById(parentId);
        if (parent == null) {
            throw new IllegalArgumentException("Parent not found with ID: " + parentId);
        }

        // 2. Fetch all available students (in a real scenario, we might fetch all students).
        // For simplicity, we fetch a hardcoded list of all student IDs.
        List<Integer> allStudentIds = List.of(101, 102, 103); // From dummy data.
        List<Student> availableStudents = studentRepository.findByIds(allStudentIds);

        // 3. Create the child management form.
        ChildManagementForm form = new ChildManagementForm(parent, availableStudents);

        return new FormData(parent, availableStudents, form);
    }

    /**
     * Submits the child management changes (assign and remove students).
     * Corresponds to submitChildManagementChanges(...) in class diagram.
     * Implements the atomic transaction as per sequence diagram.
     */
    public boolean submitChildManagementChanges(int parentId, List<Integer> assignIds, List<Integer> removeIds) {
        System.out.println("ParentManagementController: Submitting changes for parent ID " + parentId);
        System.out.println("  Assign IDs: " + assignIds);
        System.out.println("  Remove IDs: " + removeIds);

        // Start transaction via UnitOfWork commit.
        // Note: The sequence diagram shows commit() is called first, then operations inside a group.
        // We interpret the group as the operations that are part of the same transaction boundary.
        // In practice, we would start a transaction before the operations.
        // For simplicity, we perform operations then commit.

        // Refetch parent within transaction boundary (as per note in sequence diagram).
        Parent parent = parentRepository.findById(parentId);
        if (parent == null) {
            System.out.println("ParentManagementController: Parent not found, aborting.");
            return false;
        }

        // Process assignments.
        for (int studentId : assignIds) {
            List<Student> students = studentRepository.findByIds(List.of(studentId));
            if (!students.isEmpty()) {
                Student student = students.get(0);
                parent.addChild(student);
                parentRepository.update(parent); // This would typically register the update.
                unitOfWork.registerUpdate(parent);
            }
        }

        // Process removals.
        for (int studentId : removeIds) {
            parent.removeChild(studentId);
            parentRepository.update(parent);
            unitOfWork.registerUpdate(parent);
        }

        // Commit the transaction.
        boolean success = unitOfWork.commit();
        return success;
    }

    /**
     * Cancels pending operations and rolls back any changes.
     * Corresponds to cancelPendingOperations() in class diagram.
     */
    public void cancelPendingOperations() {
        System.out.println("ParentManagementController: Cancelling pending operations.");
        unitOfWork.rollback();
    }

    // The following methods are present in class diagram but not heavily used in sequence diagram.
    // We implement them for completeness.

    public void assignStudentsToParent(int parentId, List<Integer> studentIds) {
        // This could be a helper method, but the sequence diagram uses submitChildManagementChanges.
        // We delegate to submitChildManagementChanges with empty remove list.
        submitChildManagementChanges(parentId, studentIds, List.of());
    }

    public void removeStudentsFromParent(int parentId, List<Integer> studentIds) {
        // Similarly, delegate with empty assign list.
        submitChildManagementChanges(parentId, List.of(), studentIds);
    }
}

/**
 * Simple data holder for returning form data from controller.
 * Not in the original diagrams, but useful for our implementation.
 */
class FormData {
    private Parent parent;
    private List<Student> availableStudents;
    private ChildManagementForm form;

    public FormData(Parent parent, List<Student> availableStudents, ChildManagementForm form) {
        this.parent = parent;
        this.availableStudents = availableStudents;
        this.form = form;
    }

    public Parent getParent() {
        return parent;
    }

    public List<Student> getAvailableStudents() {
        return availableStudents;
    }

    public ChildManagementForm getForm() {
        return form;
    }
}