package com.example.presentation;

import com.example.domain.ReportCard;
import java.util.List;

/**
 * View interface for deleting report cards.
 * In a real application, this would be a GUI class.
 */
public interface DeleteReportCardView {
    void displayReportCardList(List<ReportCard> reportCards);
    boolean showConfirmationDialog(String reportCardId);
    void showSuccessMessage();
    void showErrorMessage(String message);
    void updateListView();
}