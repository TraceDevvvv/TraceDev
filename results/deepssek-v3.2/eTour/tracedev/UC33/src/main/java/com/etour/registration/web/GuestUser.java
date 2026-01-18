package com.etour.registration.web;

/**
 * Actor representing the Guest User from sequence diagram.
 */
public class GuestUser {
    private RegistrationController registrationController;

    public GuestUser(RegistrationController registrationController) {
        this.registrationController = registrationController;
    }

    public void enableLogging() {
        registrationController.enableLogging();
    }

    public RegistrationFormDTO getRegister() {
        return registrationController.GET_register();
    }

    public void viewRegistrationForm() {
        System.out.println("Guest views the registration form.");
    }

    public void fillAndSubmitForm(RegistrationFormDTO formData) {
        System.out.println("Guest fills and submits the form.");
        registrationController.submitRegistration(formData);
    }

    public String confirmOperation() {
        return registrationController.confirmOperation();
    }

    public void displayConfirmationPrompt() {
        System.out.println("Displaying confirmation prompt to user.");
    }

    public void displaySuccessPage() {
        System.out.println(registrationController.displaySuccessPage());
    }

    public void displayErrorPage(java.util.List<String> errors) {
        System.out.println(registrationController.displayErrorPage(errors));
    }

    public void setRegistrationController(RegistrationController controller) {
        this.registrationController = controller;
    }
}