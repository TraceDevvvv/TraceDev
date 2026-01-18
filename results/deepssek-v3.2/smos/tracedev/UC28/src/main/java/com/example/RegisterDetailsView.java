package com.example;

import java.util.Date;

/**
 * View for displaying register details.
 * Traceability: System ensures registry information is accurate and displayed clearly.
 */
public class RegisterDetailsView {
    private RegisterDetailsViewModel viewModel;
    private RegisterDetailsController controller;
    private DetailsButton detailsButton;
    private JustificationForm justificationForm;
    private DisciplinaryNoteForm disciplinaryNoteForm;

    public RegisterDetailsView(RegisterDetailsViewModel viewModel, RegisterDetailsController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.detailsButton = new DetailsButton("sampleRegisterId");
        this.justificationForm = null;
        this.disciplinaryNoteForm = null;
    }

    public void onDetailsButtonClicked(String registerId) {
        RegisterDetailsDTO dto = controller.handleDetailsRequest(registerId);
        if (dto != null) {
            displayRegisterDetails(dto);
        } else {
            showErrorMessage();
        }
    }

    public void displayRegisterDetails(RegisterDetailsDTO dto) {
        viewModel.displayRegisterDetails(dto);
        // Display organized registry information.
        System.out.println("Displaying register details organized by date.");
        // Check if today's date is in records and highlight.
        Date today = new Date();
        // Simplified: assume today's list is highlighted.
        System.out.println("Highlighting today's student list.");
        // Loop through each student and show status.
        for (RecordByDateDTO record : dto.organizedRecords) {
            for (StudentRecordDTO student : record.studentRecords) {
                displayStudentStatus(student);
            }
        }
        showJustificationForm();
        showDisciplinaryNoteForm();
    }

    public void showJustificationForm() {
        // Display justification form.
        justificationForm = new JustificationForm("student123", new Date());
        justificationForm.displayForm();
        viewModel.showJustificationForm();
    }

    public void showDisciplinaryNoteForm() {
        // Display disciplinary note form.
        disciplinaryNoteForm = new DisciplinaryNoteForm("student123", new Date());
        disciplinaryNoteForm.displayForm();
        viewModel.showDisciplinaryNoteForm();
    }

    public void showErrorMessage() {
        System.err.println("Error: Connection interrupted.");
        viewModel.updateView();
    }

    public void displayStudentStatus(StudentRecordDTO studentRecordDTO) {
        // Display student status.
        System.out.println("Student " + studentRecordDTO.studentId + " is " + studentRecordDTO.status);
    }
}