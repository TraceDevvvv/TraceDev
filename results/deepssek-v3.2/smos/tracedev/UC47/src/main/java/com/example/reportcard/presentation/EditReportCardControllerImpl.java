package com.example.reportcard.presentation;

import com.example.reportcard.application.EditReportCardUseCase;
import com.example.reportcard.authentication.AuthenticationService;
import com.example.reportcard.domain.ReportCardDTO;

/**
 * Controller implementation coordinating authentication and use case.
 */
public class EditReportCardControllerImpl implements EditReportCardController {
    private final EditReportCardUseCase editReportCardUseCase;
    private final AuthenticationService authenticationService;
    private final ReportCardEditView view; // Assumes view is injected for callbacks

    public EditReportCardControllerImpl(EditReportCardUseCase editReportCardUseCase,
                                        AuthenticationService authenticationService,
                                        ReportCardEditView view) {
        this.editReportCardUseCase = editReportCardUseCase;
        this.authenticationService = authenticationService;
        this.view = view;
    }

    @Override
    public void handleEditRequest(String studentId) {
        // Verify admin role (entry condition)
        if (!authenticationService.verifyAdminRole("currentAdminId")) {
            view.displayError("Unauthorized access");
            return;
        }
        // Delegate to use case
        ReportCardDTO dto = editReportCardUseCase.loadReportCard(studentId);
        if (dto.getStudentId() == null || dto.getSubjectGrades().isEmpty()) {
            // In our implementation, empty DTO indicates not found
            view.displayError("Report card not found");
        } else {
            view.displayEditForm(dto);
        }
    }

    @Override
    public void handleSaveRequest(ReportCardDTO reportCardData) {
        // Verify admin role again for safety
        if (!authenticationService.verifyAdminRole("currentAdminId")) {
            view.displayError("Unauthorized access");
            return;
        }
        boolean success = editReportCardUseCase.modifyReportCard(reportCardData);
        if (success) {
            view.displayConfirmation();
            view.displayStudentList(); // Flow step 8: after success, show student list
        } else {
            view.displayError("Invalid grade values");
        }
    }
}