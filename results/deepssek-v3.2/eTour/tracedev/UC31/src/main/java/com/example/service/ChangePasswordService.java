package com.example.service;

import com.example.repository.IAgencyRepository;
import com.example.domain.Agency;
import com.example.dto.ChangePasswordRequest;
import com.example.dto.ChangePasswordResult;
import com.example.exception.NetworkException;

/**
 * Service layer for changing password. Implements the business logic.
 */
public class ChangePasswordService {
    private IAgencyRepository agencyRepository;
    private PasswordEncoder passwordEncoder;

    public ChangePasswordService(IAgencyRepository agencyRepository, PasswordEncoder passwordEncoder) {
        this.agencyRepository = agencyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Validates the request (step 8 in sequence diagram).
     * This is a separate method to satisfy sequence diagram step 6 consistency.
     */
    public boolean validateRequest(ChangePasswordRequest request) {
        return request.validate();
    }

    /**
     * Executes the password change use case (step 7 in sequence diagram).
     */
    public ChangePasswordResult changePassword(ChangePasswordRequest request) {
        // Simulate potential network interruption
        if (Math.random() < 0.1) { // 10% chance for demonstration
            throw new NetworkException("Connection lost during password change", 1001);
        }

        // Step 8: Validate request
        if (!validateRequest(request)) {
            return new ChangePasswordResult(false, "Invalid input");
        }

        // Step 9: Find agency
        Agency agency = agencyRepository.findById(request.getAgencyId());
        if (agency == null) {
            return new ChangePasswordResult(false, "Agency not found");
        }

        // Step 11-13: Verify current password
        String storedHash = agency.getPasswordHash();
        String storedSalt = agency.getPassword().getSalt(); // Assuming Agency provides salt via Password object
        boolean currentPasswordCorrect = passwordEncoder.verify(request.getCurrentPassword(), storedHash, storedSalt);
        if (!currentPasswordCorrect) {
            return new ChangePasswordResult(false, "Current password is incorrect");
        }

        // Steps 14-17: Generate new hash and salt
        String newSalt = passwordEncoder.generateSalt();
        String newHash = passwordEncoder.hash(request.getNewPassword(), newSalt);

        // Steps 18-20: Update agency and save
        agency.getPassword().setHash(newHash);
        agency.getPassword().setSalt(newSalt);
        agencyRepository.save(agency);

        // Step 21: Return success
        return new ChangePasswordResult(true, "Password updated");
    }
}