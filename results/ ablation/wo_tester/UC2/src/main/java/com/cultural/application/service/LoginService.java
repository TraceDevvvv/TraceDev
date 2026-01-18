package com.cultural.application.service;

import com.cultural.domain.model.User;

/**
 * Authentication service interface.
 * Added to satisfy entry conditions.
 */
public interface LoginService {
    boolean isLoggedIn(String userId);
    User getCurrentUser();
}