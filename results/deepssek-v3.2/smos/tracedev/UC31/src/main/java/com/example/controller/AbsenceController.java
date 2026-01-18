package com.example.controller;

import com.example.application.ChangeAbsenceCommand;
import com.example.application.ChangeAbsenceInteractor;
import com.example.domain.Absence;
import com.example.domain.AbsenceStatus;
import java.util.Date;

/**
 * Controller mediating between UI and application layer.
 */
public class AbsenceController {
    private Date selectedDate; // linked to requirement: date selected from use case SviewTetTingloregistration
    private ChangeAbsenceInteractor changeAbsenceInteractor;

    public AbsenceController(ChangeAbsenceInteractor changeAbsenceInteractor) {
        this.changeAbsenceInteractor = changeAbsenceInteractor;
    }

    public void changeAbsence(String absenceId, AbsenceStatus status, String reason) {
        ChangeAbsenceCommand command = new ChangeAbsenceCommand(absenceId, status, reason);
        changeAbsenceInteractor.execute(command);
        System.out.println("AbsenceController: changeAbsence executed.");
    }

    public void cancelRequest() {
        changeAbsenceInteractor.cancel();
        System.out.println("AbsenceController: cancel request processed.");
    }

    public void selectAbsence(String absenceId) {
        // In a real scenario, we would fetch the absence and pass details to UI.
        System.out.println("AbsenceController: absence " + absenceId + " selected.");
    }

    public void updateScreen(Date date) {
        this.selectedDate = date;
        // Logic to update screen based on date (simulated)
        System.out.println("AbsenceController: screen updated for date " + date);
    }

    public void refreshData() {
        // Logic to refresh data from server (simulated)
        System.out.println("AbsenceController: data refreshed from server.");
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    // New method for sequence diagram message
    public void successResponse() {
        System.out.println("AbsenceController: success response sent.");
    }

    public void cancellationConfirmed() {
        System.out.println("AbsenceController: cancellation confirmed.");
    }
}