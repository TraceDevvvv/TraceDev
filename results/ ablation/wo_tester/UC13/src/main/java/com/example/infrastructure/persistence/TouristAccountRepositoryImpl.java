package com.example.infrastructure.persistence;

import com.example.application.ports.output.TouristAccountRepository;
import com.example.domain.TouristAccount;
import com.example.infrastructure.monitoring.SystemAvailabilityMonitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of the TouristAccountRepository.
 * Uses an in-memory map for demonstration purposes.
 */
public class TouristAccountRepositoryImpl implements TouristAccountRepository {
    private Map<String, TouristAccount> dataStore;
    private SystemAvailabilityMonitor monitor;

    public TouristAccountRepositoryImpl(SystemAvailabilityMonitor monitor) {
        this.dataStore = new HashMap<>();
        this.monitor = monitor;
        // Initialize with some sample data
        initializeSampleData();
    }

    private void initializeSampleData() {
        TouristAccount account1 = new TouristAccount("ACC001", "John Doe", "john@example.com", true);
        TouristAccount account2 = new TouristAccount("ACC002", "Jane Smith", "jane@example.com", false);
        TouristAccount account3 = new TouristAccount("ACC003", "Bob Johnson", "bob@example.com", true);
        
        dataStore.put(account1.getId(), account1);
        dataStore.put(account2.getId(), account2);
        dataStore.put(account3.getId(), account3);
    }

    @Override
    public TouristAccount findById(String accountId) {
        // Check system availability before operation
        if (!monitor.checkAvailability()) {
            throw new IllegalStateException("System unavailable for repository operations");
        }
        return dataStore.get(accountId);
    }

    @Override
    public void save(TouristAccount account) {
        // Check system availability before operation
        if (!monitor.checkAvailability()) {
            throw new IllegalStateException("System unavailable for repository operations");
        }
        dataStore.put(account.getId(), account);
    }

    // Additional method for demonstration
    public Map<String, TouristAccount> getAllAccounts() {
        return new HashMap<>(dataStore);
    }
}