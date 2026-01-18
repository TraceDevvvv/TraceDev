package com.example.controller;

import com.example.dto.ChildSummaryDTO;
import com.example.service.ParentService;

/**
 * Controller for parent view operations.
 */
public class ParentViewController {
    private ParentService parentService;

    public ParentViewController(ParentService parentService) {
        this.parentService = parentService;
    }

    public ChildSummaryDTO displayChildSummary(int parentId, int childId) {
        // Delegates to parent service to get child summary
        return parentService.getChildSummary(parentId, childId);
    }
}