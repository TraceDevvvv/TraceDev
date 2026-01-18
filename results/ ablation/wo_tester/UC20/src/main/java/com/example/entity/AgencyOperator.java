package com.example.entity;

import com.example.dto.InsertBannerRequestDTO;
import com.example.controller.InsertBannerController;

/**
 * Represents an agency operator who can interact with the system to insert banners.
 */
public class AgencyOperator {
    private String operatorId;
    private String name;

    public AgencyOperator(String operatorId, String name) {
        this.operatorId = operatorId;
        this.name = name;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Selects a refreshment point for banner insertion.
     */
    public void selectRefreshmentPoint(RefreshmentPoint point) {
        System.out.println("Operator " + name + " selected refreshment point: " + point.getName());
    }

    /**
     * Submits a banner insertion request to the controller.
     */
    public void submitBannerRequest(InsertBannerRequestDTO bannerRequest) {
        System.out.println("Operator " + name + " submitted banner request for point: " + bannerRequest.getRefreshmentPointId());
    }

    /**
     * Confirms the insertion operation.
     */
    public void confirmInsertion(String confirmationId) {
        System.out.println("Operator " + name + " confirmed insertion with ID: " + confirmationId);
    }

    /**
     * Cancels the ongoing operation.
     */
    public void cancelOperation() {
        System.out.println("Operator " + name + " cancelled the operation.");
    }

    /**
     * Placeholder for login check - in real scenario would call authentication service.
     */
    public boolean loginCheck() {
        System.out.println("Login check for operator: " + operatorId);
        return true;
    }
}