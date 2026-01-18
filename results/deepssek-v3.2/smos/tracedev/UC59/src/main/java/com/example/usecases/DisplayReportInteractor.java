package com.example.usecases;

import com.example.domain.Report;
import com.example.presenters.DisplayReportPresenter;
import com.example.repositories.ReportRepository;
import com.example.requests.DisplayReportRequest;
import java.util.List;

/**
 * Implementation of the Display Report use case.
 */
public class DisplayReportInteractor implements DisplayReportUseCase {
    private ReportRepository reportRepository;
    
    public DisplayReportInteractor(ReportRepository repository) {
        this.reportRepository = repository;
    }
    
    @Override
    public void execute(DisplayReportRequest request, DisplayReportPresenter presenter) {
        try {
            if (request.getReportId() == null) {
                // Fetch list of reports for student
                List<Report> reports = reportRepository.getStudentReports(request.getStudentId());
                presenter.presentReportsList(reports);
            } else {
                // Fetch specific report details
                Report report = reportRepository.getReportById(request.getReportId());
                presenter.presentReportDetails(report);
            }
        } catch (Exception e) {
            presenter.presentError("Failed to fetch report: " + e.getMessage());
        }
    }
}