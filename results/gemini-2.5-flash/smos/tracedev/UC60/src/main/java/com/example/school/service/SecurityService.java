package com.example.school.service;

import com.example.school.model.ClassRegistryDTO;
import java.util.List;

/**
 * Service responsible for security operations such as access control,
 * data encryption, and ensuring secure rendering.
 * As per requirement QR-1, this service manages security aspects.
 */
public class SecurityService {

    /**
     * Checks if a student has access to a specific resource.
     * For this simulation, it always returns true for "classRegistry" resource.
     * In a real application, this would involve role-based access control (RBAC) or attribute-based access control (ABAC).
     *
     * @param studentId The ID of the student requesting access.
     * @param resource The resource being accessed (e.g., "classRegistry").
     * @return true if access is granted, false otherwise.
     */
    public boolean checkAccess(String studentId, String resource) {
        System.out.println("SecurityService: Checking access for student '" + studentId + "' to resource '" + resource + "'.");
        // Assume S1001 has access to "classRegistry"
        if ("S1001".equals(studentId) && "classRegistry".equals(resource)) {
            System.out.println("SecurityService: Access GRANTED for student '" + studentId + "' to '" + resource + "'.");
            return true;
        } else if ("S1002".equals(studentId) && "classRegistry".equals(resource)) {
            System.out.println("SecurityService: Access GRANTED for student '" + studentId + "' to '" + resource + "'.");
            return true;
        } else {
            System.out.println("SecurityService: Access DENIED for student '" + studentId + "' to '" + resource + "'.");
            return false;
        }
    }

    /**
     * Simulates encrypting data.
     *
     * @param data The data string to encrypt.
     * @return A dummy encrypted string.
     */
    public String encryptData(String data) {
        System.out.println("SecurityService: Encrypting data: '" + data.substring(0, Math.min(data.length(), 20)) + "...'");
        // Dummy encryption
        return "ENCRYPTED(" + data.hashCode() + ")";
    }

    /**
     * Simulates decrypting data.
     *
     * @param data The data string to decrypt.
     * @return A dummy decrypted string (in a real app, this would reverse encryptData).
     */
    public String decryptData(String data) {
        System.out.println("SecurityService: Decrypting data: '" + data.substring(0, Math.min(data.length(), 20)) + "...'");
        // Dummy decryption
        return "DECRYPTED(" + data + ")";
    }

    /**
     * Ensures secure rendering of a list of ClassRegistryDTOs.
     * This could involve redacting sensitive information, applying watermarks,
     * or ensuring the data is displayed only in a secure context.
     * As per SD, it indicates "rendering secured".
     *
     * @param data The list of DTOs to be securely rendered.
     */
    public void ensureSecureRender(List<ClassRegistryDTO> data) {
        System.out.println("SecurityService: Ensuring secure rendering for " + data.size() + " ClassRegistryDTOs.");
        // In a real application, this could involve:
        // - Filtering sensitive data from DTOs.
        // - Adding security headers to the response.
        // - Logging access attempts to sensitive data.
        // - Transforming data for a secure display format (e.g., PDF generation with watermarks).
        System.out.println("SecurityService: Data rendering secured.");
    }
}