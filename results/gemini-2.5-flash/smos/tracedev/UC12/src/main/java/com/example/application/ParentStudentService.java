package com.example.application;

import com.example.exception.AuthorizationException;
import com.example.exception.RepositoryAccessException;
import com.example.exception.ServiceException;
import com.example.exception.ValidationException;
import com.example.infrastructure.IParentStudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Application service for managing Parent-Student associations.
 * It orchestrates calls to repository, validation, and authorization serv.
 */
public class ParentStudentService {

    private final IParentStudentRepository parentStudentRepository;
    private final StudentParentValidator studentParentValidator;
    private final AuthorizationService authorizationService;

    /**
     * Constructor for ParentStudentService, injecting its dependencies.
     * @param repository The repository for parent-student data.
     * @param validator The validator for parent and student IDs.
     * @param authService The service for authorization checks.
     */
    public ParentStudentService(IParentStudentRepository repository,
                                StudentParentValidator validator,
                                AuthorizationService authService) {
        this.parentStudentRepository = repository;
        this.studentParentValidator = validator;
        this.authorizationService = authService;
    }

    /**
     * Retrieves the list of student IDs currently associated with a given parent.
     * @param parentId The ID of the parent.
     * @return A list of associated student IDs.
     * @throws ServiceException if validation or repository access fails.
     */
    public List<String> getAssociatedStudentIds(String parentId) throws ServiceException {
        try {
            studentParentValidator.validateParentId(parentId); // Validate parent ID before repository access
            // Sequence Diagram: m4 (Service to Repository) - getAssociatedStudentIds(parentId)
            // Sequence Diagram: m5 (Repository to DB) - SELECT student_id FROM parent_student_associations WHERE parent_id = parentId
            return parentStudentRepository.getAssociatedStudentIds(parentId);
        } catch (ValidationException e) {
            throw new ServiceException("Validation failed for parent ID: " + parentId, e);
        } catch (RepositoryAccessException e) {
            throw new ServiceException("Failed to retrieve associated students due to connection loss for parent: " + parentId, e);
        }
    }

    /**
     * Manages (adds and removes) student associations for a parent based on a new selection.
     * Sequence Diagram: Form to Service - manageStudentAssociations(parentId, newSelectedStudentIds)
     * @param parentId The ID of the parent.
     * @param newStudentIds The list of student IDs that should be associated with the parent after this operation.
     * @throws ServiceException if a general service error occurs (e.g., repository access issues).
     * @throws ValidationException if input parent or student IDs are invalid.
     * @throws AuthorizationException if the action is not authorized.
     */
    public void manageStudentAssociations(String parentId, List<String> newStudentIds)
            throws ServiceException, ValidationException, AuthorizationException {
        // Assume adminId is passed implicitly or derived from session context in a real app
        // For this SD, Admin.id is explicitly mentioned in authorizeAssociationManagement(Admin.id, parentId)
        // We'll use a placeholder 'adminUser123' for now, this could be passed as an argument.
        String adminId = "adminUser123"; // Simulating Admin.id

        System.out.println("[Service] Managing associations for parent: " + parentId + " with new students: " + newStudentIds);

        // 1. Validate inputs
        // Sequence Diagram: m17 (Service to Validator) - validateParentId(parentId)
        // Sequence Diagram: m18 (Validator to Service) - validationSuccess (implicit return)
        // Sequence Diagram: m43 (Validator to Service) - validationSuccess (implicit return)
        studentParentValidator.validateParentId(parentId);

        // Sequence Diagram: m19 (Service to Validator) - validateStudentIds(newStudentIds)
        // Sequence Diagram: m20 (Validator to Service) - validationSuccess (implicit return)
        // Sequence Diagram: m46 (Validator to Service) - validationSuccess (implicit return)
        // Sequence Diagram: m48 (Validator to Service) - validationSuccess (implicit return)
        studentParentValidator.validateStudentIds(newStudentIds);

        // 2. Authorize action
        // Sequence Diagram: m21 (Service to Authorizer) - authorizeAssociationManagement(Admin.id, parentId)
        // Sequence Diagram: m22 (Authorizer to Service) - authorizationGranted (implicit return)
        authorizationService.authorizeAssociationManagement(adminId, parentId);

        // Sequence Diagram: m16 (Note: Validate input and authorize before processing (Quality Requirement))
        // Sequence Diagram: m23 (Note: Retrieves existing associations to compare with new selections.)

        // 3. Retrieve existing associations
        List<String> existingStudentIdsFromDB;
        try {
            // Sequence Diagram: m24 (Service to Repository) - getAssociatedStudentIds(parentId)
            // Sequence Diagram: m25 (Repository to DB) - SELECT student_id FROM parent_student_associations WHERE parent_id = parentId
            existingStudentIdsFromDB = parentStudentRepository.getAssociatedStudentIds(parentId);
        } catch (RepositoryAccessException e) {
            throw new ServiceException("Failed to retrieve existing associations due to connection loss.", e);
        }

        // Sequence Diagram: m29 (Note: Determines students to add and remove by comparing newSelectedStudentIds with existingStudentIdsFromDB.)
        // 4. Determine students to add and remove
        List<String> studentsToAdd = calculateStudentsToAdd(existingStudentIdsFromDB, newStudentIds);
        List<String> studentsToRemove = calculateStudentsToRemove(existingStudentIdsFromDB, newStudentIds);

        // 5. Execute changes (removals first, then additions)
        try {
            for (String studentId : studentsToRemove) {
                // Sequence Diagram: m30 (Service to Repository) - removeAssociation(parentId, studentId)
                // Sequence Diagram: m31 (Repository to DB) - DELETE FROM parent_student_associations WHERE parent_id = parentId AND student_id = studentId
                parentStudentRepository.removeAssociation(parentId, studentId);
                // Sequence Diagram: m32 (DB to Repository) - acknowledgement (implicit return)
                // Sequence Diagram: m33 (Repository to Service) - acknowledgement (implicit return)
            }
            for (String studentId : studentsToAdd) {
                // Sequence Diagram: m34 (Service to Repository) - addAssociation(parentId, studentId)
                // Sequence Diagram: m35 (Repository to DB) - INSERT INTO parent_student_associations (parent_id, student_id) VALUES (parentId, studentId)
                parentStudentRepository.addAssociation(parentId, studentId);
                // Sequence Diagram: m36 (DB to Repository) - acknowledgement (implicit return)
                // Sequence Diagram: m37 (Repository to Service) - acknowledgement (implicit return)
            }
        } catch (RepositoryAccessException e) {
            // If any repository operation fails, wrap it in a ServiceException.
            // A more sophisticated approach might involve transaction management and rollback.
            throw new ServiceException("Failed to update associations due to connection loss or other database issue.", e);
        }

        System.out.println("[Service] Association management completed successfully for parent: " + parentId);
    }

    /**
     * Calculates which students need to be added to the association.
     * @param existing List of currently associated student IDs.
     * @param newSelected List of student IDs that should be associated.
     * @return List of student IDs to add.
     */
    private List<String> calculateStudentsToAdd(List<String> existing, List<String> newSelected) {
        Set<String> existingSet = existing.stream().collect(Collectors.toSet());
        return newSelected.stream()
                .filter(s -> !existingSet.contains(s))
                .collect(Collectors.toList());
    }

    /**
     * Calculates which students need to be removed from the association.
     * @param existing List of currently associated student IDs.
     * @param newSelected List of student IDs that should be associated.
     * @return List of student IDs to remove.
     */
    private List<String> calculateStudentsToRemove(List<String> existing, List<String> newSelected) {
        Set<String> newSelectedSet = newSelected.stream().collect(Collectors.toSet());
        return existing.stream()
                .filter(s -> !newSelectedSet.contains(s))
                .collect(Collectors.toList());
    }
}