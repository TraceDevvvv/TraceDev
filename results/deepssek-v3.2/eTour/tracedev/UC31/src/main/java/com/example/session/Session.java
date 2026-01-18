package com.example.session;

/**
 * Manages the user session.
 * Added to satisfy requirement Entry Conditions: "Agency Operator IS logged"
 */
public class Session {
    private String loggedInAgencyId;

    public boolean isValid() {
        return loggedInAgencyId != null && !loggedInAgencyId.isEmpty();
    }

    public String getAgencyId() {
        return loggedInAgencyId;
    }

    public void setAgencyId(String agencyId) {
        this.loggedInAgencyId = agencyId;
    }

    public void clear() {
        loggedInAgencyId = null;
    }
}