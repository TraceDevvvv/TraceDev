package com.example.application.controller;

import com.example.application.dto.TagSearchDTO;
import com.example.application.service.AuthenticationService;
import com.example.application.service.TagSearchService;

/**
 * Controller for handling tag search insertion.
 */
public class InsertTagSearchController {
    private TagSearchService tagSearchService;
    private AuthenticationService authenticationService;
    private String operatorId; // Authenticated user identity

    public InsertTagSearchController(TagSearchService tagSearchService,
                                     AuthenticationService authenticationService,
                                     String operatorId) {
        this.tagSearchService = tagSearchService;
        this.authenticationService = authenticationService;
        this.operatorId = operatorId;
    }

    public boolean validateFormData(TagSearchDTO dto) {
        // Validate that all required fields are present
        if (dto == null) return false;
        if (dto.getTag() == null || dto.getTag().trim().isEmpty()) return false;
        if (dto.getOperatorId() == null || dto.getOperatorId().trim().isEmpty()) return false;
        if (dto.getTimestamp() == null) return false;
        return true;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
}