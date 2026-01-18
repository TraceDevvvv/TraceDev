package com.example.adapter.out.authentication;

import com.example.application.port.out.AuthenticationPort;
import com.example.domain.UserContext;

public class SessionAuthenticationAdapter implements AuthenticationPort {
    private final SessionManager sessionManager;

    public SessionAuthenticationAdapter(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public boolean isAuthenticated(String userId) {
        return sessionManager.isAuthenticated(userId);
    }

    @Override
    public UserContext getCurrentUser() {
        return sessionManager.getCurrentUser();
    }
}