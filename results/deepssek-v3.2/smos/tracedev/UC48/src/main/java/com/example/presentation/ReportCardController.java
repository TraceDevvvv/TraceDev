package com.example.presentation;

import com.example.application.DeleteReportCardUseCase;
import com.example.application.Result;
import com.example.domain.ReportCard;
import java.util.List;

/**
 * Controller coordinating between view and use case.
 */
public class ReportCardController {
    private DeleteReportCardView view;
    private DeleteReportCardUseCase deleteReportCardUseCase;

    public ReportCardController(DeleteReportCardView view, DeleteReportCardUseCase useCase) {
        this.view = view;
        this.deleteReportCardUseCase = useCase;
    }

    /**
     * Called when delete button is clicked in the view.
     */
    public void onDeleteButtonClicked(String reportCardId) {
        // Show confirmation dialog
        boolean confirmed = view.showConfirmationDialog(reportCardId);
        if (!confirmed) {
            return;
        }

        // Execute deletion use case
        Result result = deleteReportCardUseCase.execute(reportCardId);
        
        if (result.isSuccess()) {
            view.showSuccessMessage();
            updateListView();
        } else {
            view.showErrorMessage(result.getMessage());
        }
    }

    /**
     * Loads all report cards and displays them in the view.
     */
    public void loadReportCards() {
        List<ReportCard> reportCards = deleteReportCardUseCase.getAllReportCards();
        view.displayReportCardList(reportCards);
    }

    /**
     * Updates the list view by reloading data.
     */
    private void updateListView() {
        view.updateListView();
        loadReportCards(); // Refresh the list
    }
}