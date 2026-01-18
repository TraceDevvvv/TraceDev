package com.duplicatefeedback.adapter.web;

/**
 * Represents the user session state.
 * Part of the interface adapters layer.
 */
public class UserSession {
    private final String userId;
    private final boolean authenticated;
    private String selectedSiteId;

    public UserSession(String userId, boolean authenticated) {
        this.userId = userId;
        this.authenticated = authenticated;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getSelectedSiteId() {
        return selectedSiteId;
    }

    public void setSelectedSiteId(String siteId) {
        this.selectedSiteId = siteId;
    }
}