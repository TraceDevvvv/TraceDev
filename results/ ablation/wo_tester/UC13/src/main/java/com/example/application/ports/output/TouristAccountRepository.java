package com.example.application.ports.output;

import com.example.domain.TouristAccount;

/**
 * Output port for Tourist Account persistence operations.
 */
public interface TouristAccountRepository {
    TouristAccount findById(String accountId);
    void save(TouristAccount account);
}