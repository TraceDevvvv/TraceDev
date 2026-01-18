package com.example.response;

import com.example.model.StatisticalReportDTO;

/**
 * Response DTO for viewing statistical report
 */
public class ViewStatisticalReportResponse {
    private StatisticalReportDTO report;
    private boolean success;
    private String message;

    public StatisticalReportDTO getReport() {
        return report;
    }

    public void setReport(StatisticalReportDTO report) {
        this.report = report;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}