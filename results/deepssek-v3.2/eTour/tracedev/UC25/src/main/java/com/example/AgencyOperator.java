package com.example;

/**
 * Represents the agency operator actor.
 * This class simulates the user interactions.
 */
public class AgencyOperator {
    private ViewStatisticalReportController controller;

    public AgencyOperator() {
        this.controller = new ViewStatisticalReportController();
    }

    /**
     * Simulates the login action.
     * Entry condition: AgencyOperator is logged in.
     */
    public void login() {
        System.out.println("AgencyOperator logged in.");
    }

    /**
     * Activates the view statistical report feature.
     * This is the starting point of the main flow.
     */
    public void activateViewStatisticalReportFeature() {
        System.out.println("AgencyOperator activates view statistical report feature.");
        controller.activateViewStatisticalReportFeature();
    }

    /**
     * Selects a location from the list.
     * This is step 4 in the flow of events.
     * @param locationId The ID of the location to select.
     */
    public void selectLocation(String locationId) {
        System.out.println("AgencyOperator selects location: " + locationId);
        controller.selectLocation(locationId);
    }

    /**
     * Submits the form after selecting a location.
     * This is step 5 in the flow of events.
     * @param locationId The ID of the selected location.
     */
    public void submitForm(String locationId) {
        System.out.println("AgencyOperator submits form for location: " + locationId);
        controller.submitForm(locationId);
    }

    /**
     * Main method to simulate the entire use case.
     * This is a runnable entry point for the application.
     */
    public static void main(String[] args) {
        AgencyOperator operator = new AgencyOperator();
        operator.login();
        operator.activateViewStatisticalReportFeature();
        operator.selectLocation("loc1");
        operator.submitForm("loc1");
    }
}