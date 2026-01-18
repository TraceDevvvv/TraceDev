package com.example.actors;

/**
 * Actor representing an Agency Operator.
 */
public class AgencyOperator {
    private String operatorId;
    private String name;
    private boolean loggedIn;

    public AgencyOperator(String operatorId, String name) {
        this.operatorId = operatorId;
        this.name = name;
        this.loggedIn = false;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void activateUseCaseSearchTourist() {
        // This method triggers the SearchTourist use case
        System.out.println("Agency Operator " + name + " activated SearchTourist use case.");
    }

    public void selectTourist(String id) {
        // This method represents selecting a tourist by ID
        System.out.println("Agency Operator selected tourist with ID: " + id);
    }

    public void editFormFields() {
        // This method represents editing form fields
        System.out.println("Agency Operator editing form fields.");
    }

    public void submitForm() {
        // This method represents submitting the form
        System.out.println("Agency Operator submitted form.");
    }

    public void confirmOperation() {
        // This method confirms the operation
        System.out.println("Agency Operator confirmed operation.");
    }

    public void correctErrors() {
        // This method corrects errors
        System.out.println("Agency Operator correcting errors.");
    }
}