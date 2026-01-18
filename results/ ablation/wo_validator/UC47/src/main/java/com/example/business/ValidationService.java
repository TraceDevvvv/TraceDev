package com.example.business;

import java.util.List;

/**
 * Service for validating account data.
 */
public interface ValidationService {
    List<String> validateAccountData(String username, String email, String phone);
}