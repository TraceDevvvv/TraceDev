package com.system.controller;

import com.system.auth.AuthorizationService;
import com.system.auth.UserSession;
import com.system.entity.Justification;
import com.system.repository.JustificationRepository;
import com.system.response.ViewJustificationDetailsResponse;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for the View Justification Details use case.
 * Handles viewing, updating, and deleting justifications.
 */
public class ViewJustificationDetailsController {
    private JustificationRepository justificationRepository;
    private AuthorizationService authorizationService;
    private UserSession userSession; // Added as per class diagram relationship

    public ViewJustificationDetailsController(JustificationRepository justificationRepository,
                                              AuthorizationService authorizationService,
                                              UserSession userSession) {
        this.justificationRepository = justificationRepository;
        this.authorizationService = authorizationService;
        this.userSession = userSession;
    }

    /**
     * Executes the main flow: retrieves justification details for viewing.
     * @param adminId The administrator's user ID.
     * @param justificationId The justification ID to view.
     * @return Response containing details and permissions.
     */
    public ViewJustificationDetailsResponse execute(String adminId, String justificationId) {
        // Check if user is logged in (Entry Condition)
        if (userSession == null || !userSession.isLoggedIn()) {
            return new ViewJustificationDetailsResponse(null, false, false, false, "Not logged in");
        }

        // Pre-condition checks: access and role
        boolean hasAccess = authorizationService.validateAccess(adminId, "ViewingLancogiustifies");
        boolean hasRole = authorizationService.checkRole(adminId, "administrator");
        if (!hasAccess || !hasRole) {
            return new ViewJustificationDetailsResponse(null, false, false, false, "Access denied");
        }

        // Retrieve justification from repository
        Optional<Justification> justificationOpt = justificationRepository.findById(justificationId);
        if (!justificationOpt.isPresent()) {
            // Return a response with "Not found" error (m15 in sequence diagram)
            return new ViewJustificationDetailsResponse(null, false, false, false, "Not found");
        }
        Justification justification = justificationOpt.get();

        // Determine modify/delete permissions (simplified: based on status)
        boolean canModify = "PENDING".equals(justification.getStatus());
        boolean canDelete = "PENDING".equals(justification.getStatus());

        // Build response (m12 in sequence diagram)
        ViewJustificationDetailsResponse response = buildResponse(justification);
        response.setCanModify(canModify);
        response.setCanDelete(canDelete);
        response.setSuccess(true);
        return response;
    }

    /**
     * Updates a justification with new details.
     * Corresponds to the update flow in the sequence diagram.
     * @param adminId The administrator's user ID.
     * @param justificationId The justification ID to update.
     * @param details New details to apply.
     * @return Response indicating success or error.
     */
    public ViewJustificationDetailsResponse updateJustification(String adminId, String justificationId, Map<String, Object> details) {
        // Check if user is logged in
        if (userSession == null || !userSession.isLoggedIn()) {
            return new ViewJustificationDetailsResponse(null, false, false, false, "Not logged in");
        }
        // Pre-condition checks
        boolean hasAccess = authorizationService.validateAccess(adminId, "ViewingLancogiustifies");
        boolean hasRole = authorizationService.checkRole(adminId, "administrator");
        if (!hasAccess || !hasRole) {
            return new ViewJustificationDetailsResponse(null, false, false, false, "Access denied");
        }

        Optional<Justification> justificationOpt = justificationRepository.findById(justificationId);
        if (!justificationOpt.isPresent()) {
            return new ViewJustificationDetailsResponse(null, false, false, false, "Not found");
        }
        Justification justification = justificationOpt.get();

        // Update details on the entity
        boolean updated = justification.updateDetails(details);
        if (!updated) {
            return new ViewJustificationDetailsResponse(null, false, false, false, "Update failed");
        }

        // Save via repository (which will call serverConnection.updateJustification)
        Justification saved = justificationRepository.save(justification);
        if (saved == null) {
            return new ViewJustificationDetailsResponse(null, false, false, false, "Save failed");
        }

        // Return success response (m29 in sequence diagram)
        ViewJustificationDetailsResponse response = buildResponse(saved);
        response.setSuccess(true);
        response.setCanModify(true);
        response.setCanDelete(true);
        return response;
    }

    /**
     * Deletes a justification.
     * Corresponds to the delete flow in the sequence diagram.
     * @param adminId The administrator's user ID.
     * @param justificationId The justification ID to delete.
     * @return Response indicating success or error.
     */
    public ViewJustificationDetailsResponse deleteJustification(String adminId, String justificationId) {
        // Check if user is logged in
        if (userSession == null || !userSession.isLoggedIn()) {
            return new ViewJustificationDetailsResponse(null, false, false, false, "Not logged in");
        }
        // Pre-condition checks
        boolean hasAccess = authorizationService.validateAccess(adminId, "ViewingLancogiustifies");
        boolean hasRole = authorizationService.checkRole(adminId, "administrator");
        if (!hasAccess || !hasRole) {
            return new ViewJustificationDetailsResponse(null, false, false, false, "Access denied");
        }

        // Delete via repository (which will call serverConnection.deleteJustification)
        boolean deleted = justificationRepository.deleteById(justificationId);
        if (!deleted) {
            return new ViewJustificationDetailsResponse(null, false, false, false, "Delete failed");
        }

        // Return success response (m39 in sequence diagram)
        ViewJustificationDetailsResponse response = new ViewJustificationDetailsResponse();
        response.setSuccess(true);
        response.setCanModify(false);
        response.setCanDelete(false);
        return response;
    }

    /**
     * Builds a response from a justification entity (m12 in sequence diagram).
     * @param justification The justification.
     * @return A ViewJustificationDetailsResponse.
     */
    private ViewJustificationDetailsResponse buildResponse(Justification justification) {
        Map<String, Object> details = justification.getDetails();
        boolean canModify = "PENDING".equals(justification.getStatus());
        boolean canDelete = "PENDING".equals(justification.getStatus());
        return new ViewJustificationDetailsResponse(details, canModify, canDelete, true, null);
    }
}