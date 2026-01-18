package com.example.agency;

/**
 * Represents the EditView from the sequence diagram.
 */
public class EditView {
    private EditNewsController controller;

    public EditView(EditNewsController controller) {
        this.controller = controller;
    }

    // 4-6. display form with news data
    public void displayFormWithNewsData() {
        System.out.println("Displaying form with news data.");
    }

    // 7. change data in form
    public void changeDataInForm() {
        System.out.println("Changing data in form.");
    }

    // 8. submit form
    public void submitForm() {
        System.out.println("Submitting form.");
    }

    // 10. ask for confirmation
    public void askForConfirmation() {
        System.out.println("Asking for confirmation.");
    }

    // 11. confirm operation
    public void confirmOperation() {
        System.out.println("Confirming operation.");
    }

    // 12. notify successful amendment
    public void notifySuccessfulAmendment() {
        System.out.println("Notifying successful amendment.");
    }

    // display error messages
    public void displayErrorMessages() {
        System.out.println("Displaying error messages.");
    }

    // cancel editing
    public void cancelEditing() {
        System.out.println("Cancelling editing.");
    }

    // operation cancelled
    public void operationCancelled() {
        System.out.println("Operation cancelled.");
    }

    // display connection error
    public void displayConnectionError() {
        System.out.println("Displaying connection error.");
    }
}