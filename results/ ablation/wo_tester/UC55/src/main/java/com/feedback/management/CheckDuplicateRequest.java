// DTO for request to check duplicate feedback.
package com.feedback.management;

public class CheckDuplicateRequest {
    private String siteId;
    private String userId;

    public CheckDuplicateRequest(String siteId, String userId) {
        this.siteId = siteId;
        this.userId = userId;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getUserId() {
        return userId;
    }
}