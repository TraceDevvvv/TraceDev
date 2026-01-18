package com.example.agency.view;

import com.example.agency.presenter.DeleteCulturalObjectPresenter;
import com.example.domain.CulturalObject;

import java.util.List;
import java.util.Scanner;

/**
 * Concrete implementation of {@link AgencyOperatorView} for console-based interaction.
 * This class serves as a mock UI for demonstrating the application flow.
 */
public class AgencyOperatorViewImpl implements AgencyOperatorView {

    private DeleteCulturalObjectPresenter presenter;
    private final Scanner scanner = new Scanner(System.in);

    // This field will be set by the main application to link the view to its presenter.
    // In a real UI framework (e.g., Swing, JavaFX), this might be passed during view initialization or
    // set via a controller/presenter factory.
    public void setPresenter(DeleteCulturalObjectPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayCulturalObjects(List<CulturalObject> objects) {
        System.out.println("\n--- Current Cultural Objects ---");
        if (objects.isEmpty()) {
            System.out.println("No cultural objects found.");
            return;
        }
        for (CulturalObject obj : objects) {
            System.out.println(obj);
        }
        System.out.println("--------------------------------\n");
    }

    @Override
    public boolean showConfirmationDialog(String message) {
        System.out.println("\n[VIEW] Confirmation: " + message + " (y/n)");
        String response = scanner.nextLine().trim().toLowerCase();
        return "y".equals(response);
    }

    @Override
    public void showSuccessMessage(String message) {
        System.out.println("\n[VIEW] SUCCESS: " + message);
    }

    @Override
    public void showErrorMessage(String message) {
        System.err.println("\n[VIEW] ERROR: " + message);
    }

    @Override
    public void showInfoMessage(String message) {
        System.out.println("\n[VIEW] INFO: " + message);
    }

    @Override
    public void blockInputControls() {
        System.out.println("[VIEW] Input controls are blocked (simulated). Please wait...");
        // In a real UI, this would disable buttons, text fields, etc.
    }

    @Override
    public void unblockInputControls() {
        System.out.println("[VIEW] Input controls are unblocked (simulated).");
        // In a real UI, this would re-enable buttons, text fields, etc.
    }

    /**
     * Simulates the Agency Operator selecting a cultural object.
     * This acts as the entry point from the UI to the presenter's logic.
     * Corresponds to `AO -> View : selectCulturalObject(objectId : String)` and
     * `View -> Presenter : requestDelete(objectId : String)` in the sequence diagram.
     * @param objectId The ID of the selected cultural object.
     */
    @Override
    public void selectCulturalObject(String objectId) {
        System.out.println("[VIEW] Cultural object '" + objectId + "' selected by Agency Operator.");
        // Delegate to the presenter to handle the delete request
        if (presenter != null) {
            presenter.requestDelete(objectId);
        } else {
            System.err.println("[VIEW] Error: Presenter not set. Cannot process delete request.");
        }
    }
}