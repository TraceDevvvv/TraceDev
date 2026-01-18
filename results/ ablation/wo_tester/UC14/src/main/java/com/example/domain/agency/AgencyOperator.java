package com.example.domain.agency;

import com.example.application.AuthenticationService;

/**
 * Represents an agency operator actor.
 */
public class AgencyOperator {
    private String operatorId;
    private String name;
    private AuthenticationService authenticationService;

    public AgencyOperator(String operatorId, String name) {
        this.operatorId = operatorId;
        this.name = name;
        this.authenticationService = new AuthenticationService();
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getName() {
        return name;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    /**
     * Verifies login status as per sequence diagram entry condition.
     */
    public boolean verifyLoginStatus() {
        return authenticationService.isLoggedIn(this.operatorId);
    }
}