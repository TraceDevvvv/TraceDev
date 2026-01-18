package com.example.diagram;

import java.util.List;

/**
 * Use case controller for register operations.
 */
public class RegisterUseCaseController {
    private AuthorizationService authService;
    private RegisterService registerService;
    private SMOSConnectionManager connectionManager;

    public RegisterUseCaseController(AuthorizationService authService, RegisterService registerService, SMOSConnectionManager connectionManager) {
        this.authService = authService;
        this.registerService = registerService;
        this.connectionManager = connectionManager;
    }

    public AuthorizationService getAuthService() {
        return authService;
    }

    public void setAuthService(AuthorizationService authService) {
        this.authService = authService;
    }

    public RegisterService getRegisterService() {
        return registerService;
    }

    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    public SMOSConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(SMOSConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Gets registers for a given academic year.
     * Implements the main flow from the sequence diagram.
     * @param year the academic year.
     * @return list of RegisterDTO objects.
     * @throws AccessDeniedException if authorization fails.
     */
    public List<RegisterDTO> getRegistersByAcademicYear(String year) {
        // Check SMOS connection per REQ-002
        if (!connectionManager.checkConnection()) {
            throw new RuntimeException("SMOS connection not available.");
        }

        // Authorization check
        // Assumption: We need a userId; for demo we use a placeholder.
        String userId = "admin123";
        // m8: check user role (delegated to AuthorizationService)
        if (!authService.validateAccess(userId, "Administrator")) {
            // m11: throws AccessDeniedException
            throw new AccessDeniedException("Access denied for user: " + userId);
        }

        // Retrieve registers via service
        List<Register> registers = registerService.findRegistersByAcademicYear(year);
        return registerService.convertToDTO(registers);
    }
}

/**
 * Custom exception for access denial.
 */
class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}