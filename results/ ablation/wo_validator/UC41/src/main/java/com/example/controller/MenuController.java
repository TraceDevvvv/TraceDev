
package com.example.controller;

import com.example.dto.MenuDTO;
import com.example.presenter.EditDailyMenuOutputPort;
import com.example.usecase.EditDailyMenuInputPort;
import com.example.usecase.EditDailyMenuInteractor;

/**
 * Controller that handles edit daily menu requests.
 */
public class MenuController {
    private EditDailyMenuInputPort editDailyMenuUseCase;
    private EditDailyMenuOutputPort presenter;

    // Constructor with dependency injection
    public MenuController(EditDailyMenuInputPort editDailyMenuUseCase, EditDailyMenuOutputPort presenter) {
        this.editDailyMenuUseCase = editDailyMenuUseCase;
        this.presenter = presenter;
    }

    public void editDailyMenu(Long operatorId, String dayOfWeek, MenuDTO menuData) {
        // Step 1: Call interactor
        EditDailyMenuResult result = editDailyMenuUseCase.execute(operatorId, dayOfWeek, menuData);

        // Process result
        switch (result) {
            case VALIDATION_ERROR:
                presenter.presentError("Data insufficient or invalid");
                break;
            case CONFIRMATION_REQUIRED:
                // Step 10: Ask for confirmation
                boolean confirmed = presenter.presentConfirmation("Confirm edit?");
                if (confirmed) {
                    // Step 13: Save changes
                    // Since interactor doesn't have a method to save after confirmation, we assume
                    // we need to call a separate method. For this, we cast to EditDailyMenuInteractor.
                    // This is an assumption because sequence diagram shows Interactor -> Repository save after confirmation.
                    if (editDailyMenuUseCase instanceof EditDailyMenuInteractor) {
                        EditDailyMenuInteractor interactor = (EditDailyMenuInteractor) editDailyMenuUseCase;
                        EditDailyMenuResult saveResult = interactor.proceedWithSave(operatorId, dayOfWeek, menuData);
                        handleSaveResult(saveResult);
                    } else {
                        presenter.presentError("Unexpected interactor type");
                    }
                } else {
                    // Operator cancels
                    presenter.presentError("Operation cancelled");
                }
                break;
            case SUCCESS:
                presenter.presentSuccess("Menu modified successfully");
                break;
            case PERSISTENCE_ERROR:
                presenter.presentError("Server connection interrupted");
                break;
        }
    }

    private void handleSaveResult(EditDailyMenuResult saveResult) {
        switch (saveResult) {
            case SUCCESS:
                presenter.presentSuccess("Menu modified successfully");
                break;
            case PERSISTENCE_ERROR:
                presenter.presentError("Failed to save changes");
                break;
            default:
                presenter.presentError("Unexpected result");
                break;
        }
    }
    
    public enum EditDailyMenuResult {
        VALIDATION_ERROR,
        CONFIRMATION_REQUIRED,
        SUCCESS,
        PERSISTENCE_ERROR
    }
}
