
package com.convention;

/**
 * Represents a Convention with its data and activation status.
 */
public class Convention {
    private String conventionId;
    private String conventionName;
    private boolean isActive;
    private String agreementData;

    public Convention(String conventionId, String conventionName, String agreementData) {
        this.conventionId = conventionId;
        this.conventionName = conventionName;
        this.agreementData = agreementData;
        this.isActive = false;
    }

    public String getConventionId() {
        return conventionId;
    }

    public String getConventionName() {
        return conventionName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAgreementData() {
        return agreementData;
    }

    @Override
    public String toString() {
        return "Convention{" +
                "conventionId='" + conventionId + '\'' +
                ", conventionName='" + conventionName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
