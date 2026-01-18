package com.example.agency.presenter;

import com.example.agency.view.AgencyOperatorView;
import com.example.usecase.DeleteCulturalObjectUseCase;

/**
 * Presenter for the "Delete Cultural Object" feature.
 * This class manages the interaction between the {@link AgencyOperatorView} and the {@link DeleteCulturalObjectUseCase}.
 * It handles user input from the view, orchestrates the use case, and updates the view.
 * This corresponds to the 'DeleteCulturalObjectPresenter' class in the Class Diagram.
 * Note: Assumes authenticated user (R1.3)
 */
public class DeleteCulturalObjectPresenter {
    private final DeleteCulturalObjectUseCase useCase;
    private final AgencyOperatorView view;

    /**
     * Constructs a new DeleteCulturalObjectPresenter.
     * @param view The view interface that this presenter will interact with.
     * @param useCase The use case for deleting cultural objects.
     */
    public DeleteCulturalObjectPresenter(AgencyOperatorView view, DeleteCulturalObjectUseCase useCase) {
        this.view = view;
        this.useCase = useCase;
    }

    /**
     * Initiates the deletion request flow for a cultural object.
     * This method is renamed from handleDeleteRequest to satisfy R1.4.1 as per the class diagram and sequence diagram.
     * Corresponds to `View -> Presenter : requestDelete(objectId : String)` in the sequence diagram.
     *
     * @param objectId The ID of the cultural object requested for deletion.
     */
    public void requestDelete(String objectId) {
        System.out.println("[Presenter] Received request to delete object ID: " + objectId);

        // Step 2. System asks for confirmation.
        boolean userConfirmed = view.showConfirmationDialog("Are you sure you want to delete cultural object with ID: " + objectId + "?");

        if (userConfirmed) {
            confirmDeletion(objectId);
        } else {
            cancelDeletion();
        }
    }

    /**
     * Handles the confirmation of a deletion request.
     * This method is public as per class diagram, but typically called internally after `requestDelete`
     * in the sequence diagram's flow.
     * @param objectId The ID of the cultural object to be deleted.
     */
    public void confirmDeletion(String objectId) {
        System.out.println("[Presenter] User confirmed deletion for object ID: " + objectId);

        // Quality Requirement: Block input controls during async operation
        view.blockInputControls();

        // Step 4. System deletes the selected cultural object.
        boolean deletionSuccessful = useCase.execute(objectId);

        if (deletionSuccessful) {
            // Exit Condition: Successful elimination
            view.showSuccessMessage("Cultural object with ID '" + objectId + "' deleted successfully.");
        } else {
            // Exit Condition: ETOUR server interrupted / Deletion failed
            view.showErrorMessage("Failed to delete cultural object with ID '" + objectId + "' due to a system error or connection issue.");
        }

        view.unblockInputControls();
    }

    /**
     * Handles the cancellation of a deletion request.
     * This method is public as per class diagram, but typically called internally after `requestDelete`
     * in the sequence diagram's flow.
     * Corresponds to `Presenter -> Presenter : cancelDeletion()` in the sequence diagram (R1.5.2).
     */
    public void cancelDeletion() {
        System.out.println("[Presenter] Deletion operation cancelled by user.");
        // Exit Condition: Agency Operator cancels
        view.showInfoMessage("Deletion operation cancelled.");
    }
}