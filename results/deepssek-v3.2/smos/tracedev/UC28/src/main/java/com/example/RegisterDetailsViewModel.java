package com.example;

import java.util.List;

/**
 * ViewModel for register details.
 */
public class RegisterDetailsViewModel {
    private ClassRegisterDTO registerData;
    private List<RecordByDateDTO> organizedRecords;

    public void displayRegisterDetails(RegisterDetailsDTO dto) {
        this.registerData = dto.registerData;
        this.organizedRecords = dto.organizedRecords;
        updateView();
    }

    public void showJustificationForm() {
        // Logic to show justification form.
        updateView();
    }

    public void showDisciplinaryNoteForm() {
        // Logic to show disciplinary note form.
        updateView();
    }

    public void updateView() {
        // Update the view based on current state.
    }
}