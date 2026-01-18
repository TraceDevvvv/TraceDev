package com.example.infrastructure.presentation;

import com.example.domain.TouristAccount;
import com.example.domain.enums.AccountStatus;
import com.example.domain.enums.AccountOperation;
import com.example.infrastructure.persistence.TouristAccountRepositoryImpl;
import com.example.infrastructure.monitoring.SystemAvailabilityMonitor;

/**
 * Represents the activation/deactivation sheet for a tourist account.
 * Handles entry condition validation and account details retrieval.
 */
public class ActivationDeactivationSheet {
    private String accountId;
    private AccountStatus currentStatus;
    private AccountOperation operationType;
    private boolean isProcessed;
    
    private TouristAccountRepositoryImpl repository;
    private SystemAvailabilityMonitor monitor;

    public ActivationDeactivationSheet(String accountId, AccountOperation operationType) {
        this.accountId = accountId;
        this.operationType = operationType;
        this.isProcessed = false;
        
        // In a real application, these would be injected
        this.monitor = new SystemAvailabilityMonitor();
        this.repository = new TouristAccountRepositoryImpl(monitor);
    }

    // Added to satisfy requirement Entry Conditions
    public boolean isAvailable() {
        // Check if system is available
        if (!monitor.checkAvailability()) {
            return false;
        }
        
        // Check if account exists
        TouristAccount account = repository.findById(accountId);
        if (account == null) {
            return false;
        }
        
        // Check if sheet is already processed
        if (isProcessed) {
            return false;
        }
        
        this.currentStatus = account.getStatus();
        return true;
    }

    // Added to satisfy requirement Flow of Events 1
    public void activateFeature() {
        System.out.println("Sheet: Activation/Deactivation feature activated for account " + accountId);
        // Additional feature activation logic could be added here
    }

    public TouristAccount getAccountDetails() {
        TouristAccount account = repository.findById(accountId);
        if (account != null) {
            return account;
        }
        // Return a dummy account if not found (for demonstration)
        return new TouristAccount(accountId, "Unknown", "unknown@example.com", false);
    }

    public TouristAccount getTouristAccountDetails() {
        return getAccountDetails();
    }

    // Getters
    public String getAccountId() {
        return accountId;
    }

    public AccountStatus getCurrentStatus() {
        return currentStatus;
    }

    public AccountOperation getOperationType() {
        return operationType;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }
}