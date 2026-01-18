package com.example.usecases;

import com.example.presenters.DisplayReportPresenter;
import com.example.requests.DisplayReportRequest;

/**
 * Interface for the Display Report use case.
 */
public interface DisplayReportUseCase {
    void execute(DisplayReportRequest request, DisplayReportPresenter presenter);
}