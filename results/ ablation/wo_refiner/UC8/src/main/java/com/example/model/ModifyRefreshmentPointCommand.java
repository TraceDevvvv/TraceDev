package com.example.model;

import com.example.dto.RefreshmentPointFormDTO;

/**
 * Command object for modifying a refreshment point.
 */
public class ModifyRefreshmentPointCommand {
    private String refreshmentPointId;
    private RefreshmentPointFormDTO formData;
    private String operatorId;
    private String confirmationToken;

    public ModifyRefreshmentPointCommand(String refreshmentPointId, RefreshmentPointFormDTO formData, String operatorId, String confirmationToken) {
        this.refreshmentPointId = refreshmentPointId;
        this.formData = formData;
        this.operatorId = operatorId;
        this.confirmationToken = confirmationToken;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public RefreshmentPointFormDTO getFormData() {
        return formData;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }
}