package com.example.ui;

import com.example.controller.AbsenceController;
import com.example.domain.AbsenceStatus;
import java.util.Date;

/**
 * UI boundary (simulated) for absence management.
 */
public class AbsenceUI {
    private Date selectedDate;
    private AbsenceController absenceController;

    public AbsenceUI(AbsenceController absenceController) {
        this.absenceController = absenceController;
    }

    public void selectAbsence(String absenceId) {
        // Flow of Events 1: UI selects an absence (triggered from date selection)
        absenceController.selectAbsence(absenceId);
    }

    public void displayAbsenceDetails() {
        // Display details (simulated)
        System.out.println("UI: displaying absence details.");
    }

    public void updateScreen(Date date) {
        // Flow of Events 4: update the screen based on selected date
        selectedDate = date;
        absenceController.updateScreen(date);
        System.out.println("UI: screen updated for date " + date);
    }

    public void refreshData() {
        // Flow of Events 5: refresh data from server
        absenceController.refreshData();
        System.out.println("UI: data refreshed.");
    }

    public void detectConnectionFailure() {
        // Simulate detection of connection failure (opt block in sequence diagram)
        System.out.println("UI: connection to SMOS server interrupted.");
    }

    // Simulate UI actions triggered by the administrator
    public void simulateModifyAndSave(String absenceId, AbsenceStatus status, String reason) {
        System.out.println("UI: administrator modifies absence and clicks Save.");
        absenceController.changeAbsence(absenceId, status, reason);
    }

    public void simulateCancel() {
        System.out.println("UI: administrator cancels operation.");
        absenceController.cancelRequest();
    }

    // Getters and setters
    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    // New methods for sequence diagram messages
    public void updateScreenBasedOnSelectedDate() {
        System.out.println("UI: updateScreenBasedOnSelectedDate() called.");
        updateScreen(selectedDate);
    }

    public void refreshDataFromServer() {
        System.out.println("UI: refreshDataFromServer() called.");
        refreshData();
    }

    public void displayUpdatedAbsenceList() {
        System.out.println("UI: display updated absence list.");
    }

    public void displayErrorMessage() {
        System.out.println("UI: display error message.");
    }

    public void cancelOperation() {
        System.out.println("UI: cancel operation.");
        simulateCancel();
    }

    public void operationCancelled() {
        System.out.println("UI: operation cancelled.");
    }
}