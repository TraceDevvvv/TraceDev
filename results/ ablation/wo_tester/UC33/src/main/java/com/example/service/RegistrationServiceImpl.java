package com.example.service;

import com.example.dto.RegistrationRequestDTO;
import com.example.dto.ValidationResult;
import com.example.model.Account;
import com.example.model.AccountStatus;
import com.example.repository.AccountRepository;
import com.example.service.NotificationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of RegistrationService.
 */
public class RegistrationServiceImpl implements RegistrationService {
    private AccountRepository accountRepository;
    private NotificationService notificationService;

    // Constructor for dependency injection
    public RegistrationServiceImpl(AccountRepository accountRepository, NotificationService notificationService) {
        this.accountRepository = accountRepository;
        this.notificationService = notificationService;
    }

    @Override
    public ValidationResult validateRegistrationData(RegistrationRequestDTO dto) {
        List<String> errors = new ArrayList<>();

        // Validate username
        if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
            errors.add("Username is required.");
        } else if (dto.getUsername().length() < 3) {
            errors.add("Username must be at least 3 characters.");
        }

        // Validate email
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            errors.add("Email is required.");
        } else if (!dto.getEmail().contains("@")) {
            errors.add("Invalid email format.");
        }

        // Validate password
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            errors.add("Password is required.");
        } else if (dto.getPassword().length() < 6) {
            errors.add("Password must be at least 6 characters.");
        }

        // Validate password confirmation
        if (dto.getPasswordConfirmation() == null || dto.getPasswordConfirmation().trim().isEmpty()) {
            errors.add("Password confirmation is required.");
        } else if (!dto.getPassword().equals(dto.getPasswordConfirmation())) {
            errors.add("Passwords do not match.");
        }

        boolean isValid = errors.isEmpty();
        return new ValidationResult(isValid, errors);
    }

    @Override
    public Account createAccount(RegistrationRequestDTO dto) {
        // Create a new account with PENDING status
        String accountId = UUID.randomUUID().toString();
        Account account = new Account(accountId, dto.getUsername(), dto.getEmail(), AccountStatus.PENDING, new Date());
        Account savedAccount = accountRepository.save(account);
        return savedAccount;
    }

    @Override
    public boolean confirmTransaction(String confirmationId) {
        // Simulate transaction confirmation
        // In a real scenario, this would check against a transaction database or external service
        return true; // Always returns true for demonstration
    }

    @Override
    public void sendNotification(String message) {
        notificationService.sendNotification(message);
    }
}