package com.example.adapter.out.authentication;

import com.example.domain.UserContext;

public interface SessionManager {
    boolean isAuthenticated(String userId);
    UserContext getCurrentUser();
}