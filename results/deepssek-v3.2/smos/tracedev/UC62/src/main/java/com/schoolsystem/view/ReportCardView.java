package com.schoolsystem.view;

import com.schoolsystem.domain.ReportCard;
import java.util.List;

/**
 * Abstract view for displaying report cards.
 * Concrete implementations (e.g., GUI, console) should extend this.
 */
public abstract class ReportCardView {
    public abstract void displayReportCardList(List<ReportCard> reportCards);
    public abstract void displayReportCardDetails(ReportCard reportCard);
    public abstract void onReportCardSelected(String reportId); // Called when user selects a report
    public abstract void showErrorMessage(String message);
}