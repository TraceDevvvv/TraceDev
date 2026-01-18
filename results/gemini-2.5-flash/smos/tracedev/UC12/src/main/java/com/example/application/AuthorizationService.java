package com.example.application;

import com.example.exception.AuthorizationException;

/**
 * Service responsible for authorization checks.
 */
public class AuthorizationService {

    /**
     * Authorizes an administrator to manage associations for a specific parent.
     * Sequence Diagram: m21 - Service to Authorizer: authorizeAssociationManagement(Admin.id, parentId)
     * @param adminId The ID of the administrator.
     * @param parentId The ID of the parent whose associations are being managed.
     * @throws AuthorizationException if the administrator is not authorized.
     */
    public void authorizeAssociationManagement(String adminId, String parentId) throws AuthorizationException {
        System.out.println("[Authorizer] Authorizing admin '" + adminId + "' for parent '" + parentId + "'");
        // Dummy authorization logic:
        // For demonstration, let's say "unauthorizedAdmin" is not allowed.
        if ("unauthorizedAdmin".equals(adminId)) {
            throw new AuthorizationException("Admin '" + adminId + "' is not authorized for this action.");
        }
        // Also, for demonstration, if parentId is "P_restricted", only "superAdmin" can manage it.
        if ("P_restricted".equals(parentId) && !"superAdmin".equals(adminId)) {
            throw new AuthorizationException("Admin '" + adminId + "' is not authorized to manage parent '" + parentId + "'.");
        }
        System.out.println("[Authorizer] Authorization granted for admin '" + adminId + "' for parent '" + parentId + "'.");
        // Sequence Diagram: m22 - Authorizer to Service: authorizationGranted (implicit return)
    }
}