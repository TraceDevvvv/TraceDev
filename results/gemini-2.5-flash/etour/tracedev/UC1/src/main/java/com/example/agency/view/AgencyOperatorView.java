package com.example.agency.view;

import com.example.domain.CulturalObject;

import java.util.List;

/**
 * Interface defining the view operations for an Agency Operator.
 * This corresponds to the 'AgencyOperatorView' interface in the Class Diagram.
 */
public interface AgencyOperatorView {
    /**
     * Displays a list of cultural objects to the operator.
     * @param objects The list of cultural objects to display.
     */
    void displayCulturalObjects(List<CulturalObject> objects);

    /**
     * Shows a confirmation dialog to the operator and waits for their response.
     * @param message The message to display in the confirmation dialog.
     * @return true if the operator confirms, false if they cancel.
     */
    boolean showConfirmationDialog(String message);

    /**
     * Displays a success message to the operator.
     * @param message The success message.
     */
    void showSuccessMessage(String message);

    /**
     * Displays an error message to the operator.
     * @param message The error message.
     */
    void showErrorMessage(String message);

    /**
     * Displays an informational message to the operator.
     * @param message The informational message.
     */
    void showInfoMessage(String message);

    /**
     * Blocks input controls in the view, typically during an ongoing operation.
     */
    void blockInputControls();

    /**
     * Unblocks input controls in the view, allowing further user interaction.
     */
    void unblockInputControls();

    /**
     * Simulates the Agency Operator selecting a cultural object.
     * This method is added to satisfy the sequence diagram's starting point (R1.4.1).
     * In a real UI, this would be triggered by a button click or selection event.
     * @param objectId The ID of the selected cultural object.
     */
    void selectCulturalObject(String objectId);
}