package com.example.presenters;

import com.example.domain.Report;
import java.util.List;

/**
 * Interface for presenting display report results.
 */
public interface DisplayReportPresenter {
    void presentReportsList(List<Report> reports);
    void presentReportDetails(Report report);
    void presentError(String error);
}