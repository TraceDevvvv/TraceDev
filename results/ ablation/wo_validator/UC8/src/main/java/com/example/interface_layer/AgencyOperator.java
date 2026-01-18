package com.example.interface_layer;

/**
 * Represents the Agency Operator actor.
 * In a real system, this might be a user interface or a test driver.
 * For demonstration, this class simulates user interactions.
 */
public class AgencyOperator {
    private EditRestPointView view;

    public AgencyOperator(EditRestPointView view) {
        this.view = view;
    }

    public void selectRestPoint(String restPointId) {
        System.out.println("AgencyOperator: Selecting rest point " + restPointId);
        view.onSelectRestPoint(restPointId);
    }

    public void activateEditFunction(String restPointId) {
        System.out.println("AgencyOperator: Activating edit function for " + restPointId);
        view.onActivateEditFunction(restPointId);
    }

    public void modifyFormData(String field, String value) {
        System.out.println("AgencyOperator: Modifying " + field + " to " + value);
        view.onModifyFormData(field, value);
    }

    public void submitForm() {
        System.out.println("AgencyOperator: Submitting form");
        view.onSubmitForm();
    }

    public void confirmOperation() {
        System.out.println("AgencyOperator: Confirming operation");
        view.onConfirmOperation();
    }

    public void cancelOperation() {
        System.out.println("AgencyOperator: Cancelling operation");
        view.onCancelOperation();
    }
}