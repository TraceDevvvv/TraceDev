package com.example.application;

import com.example.application.requestresponse.ViewConventionHistoryRequest;
import com.example.application.requestresponse.ViewConventionHistoryResponse;

/**
 * Use case interface for viewing convention history.
 */
public interface ViewConventionHistoryUseCase {
    ViewConventionHistoryResponse execute(ViewConventionHistoryRequest request);
}