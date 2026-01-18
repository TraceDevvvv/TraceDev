package com.absenceapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Controller for the Absence management feature.
 * Coordinates between the AbsenceView and AbsenceService.
 */
public class AbsenceViewController {
    private AbsenceService absenceService;
    private AbsenceView absenceView;
    private Date currentDate; // Keep track of the currently selected date
    private List<AbsenceDTO> fetchedAbsences; // Store the initial fetch to compare against for save

    /**
     * Constructor for AbsenceViewController, injecting dependencies.
     *
     * @param absenceService The AbsenceService instance.
     * @param absenceView The AbsenceView instance.
     */
    public AbsenceViewController(AbsenceService absenceService, AbsenceView absenceView) {
        this.absenceService = absenceService;
        this.absenceView = absenceView;
        this.absenceView.setController(this); // Set controller reference in view
        this.fetchedAbsences = new ArrayList<>();
    }

    /**
     * Handles the event when a date is selected in the view.
     * REQ-007: View -> Controller : selectDate(date : Date)
     *
     * @param date The selected date.
     */
    public void selectDate(Date date) {
        System.out.println("Controller: Date selected: " + date);
        this.currentDate = date;
        try {
            List<AbsenceDTO> absenceDTOs = absenceService.getAbsencesByDate(date);
            this.fetchedAbsences = new ArrayList<>(absenceDTOs); // Store the original fetched list
            absenceView.displayAbsences(absenceDTOs);
        } catch (ApplicationException e) {
            System.err.println("Controller: Error fetching absences for date: " + e.getMessage());
            absenceView.showErrorMessage("Error fetching absences: " + e.getMessage());
        }
    }

    /**
     * Saves all modified (added, updated, deleted) absence changes from the view.
     * This method iterates through the DTOs from the view and calls the appropriate service method.
     *
     * @param modifiedAbsences A list of AbsenceDTOs representing the current state from the UI.
     */
    public void saveAbsenceChanges(List<AbsenceDTO> modifiedAbsences) {
        System.out.println("Controller: Saving absence changes...");
        List<AbsenceDTO> successfulChanges = new ArrayList<>();
        List<AbsenceDTO> failedChanges = new ArrayList<>();

        Map<String, AbsenceDTO> fetchedMap = fetchedAbsences.stream()
                .filter(dto -> dto.id != null) // Only consider DTOs that actually have an ID (were fetched)
                .collect(Collectors.toMap(dto -> dto.id, Function.identity()));

        // Process deletions first
        // Create a copy of the list to allow safe modification during iteration
        List<AbsenceDTO> changesToProcessForSave = new ArrayList<>(modifiedAbsences);

        for (AbsenceDTO dto : new ArrayList<>(changesToProcessForSave)) { // Iterate over a copy to handle removals
            if (AbsenceDTO.STATUS_DELETED.equals(dto.status) && dto.id != null) {
                try {
                    absenceService.deleteAbsence(dto.id);
                    successfulChanges.add(dto);
                    changesToProcessForSave.remove(dto); // Remove from list to avoid processing again
                } catch (ApplicationException e) {
                    System.err.println("Controller: Failed to delete absence " + dto.id + ": " + e.getMessage());
                    failedChanges.add(dto);
                    changesToProcessForSave.remove(dto); // Still remove, as we tried to delete it
                }
            }
        }

        // Then, process additions and updates using the new saveAbsence method
        for (AbsenceDTO dto : changesToProcessForSave) {
            boolean needsSave = false;
            if (AbsenceDTO.STATUS_NEW.equals(dto.status)) {
                needsSave = true; // Explicitly marked as new
            } else if (dto.id != null) { // Existing ID, check for modifications against original fetched data
                AbsenceDTO original = fetchedMap.get(dto.id);
                if (original != null) {
                    // Compare relevant fields for changes. Objects.equals provides null-safe comparison.
                    boolean isModified = !Objects.equals(original.type, dto.type) ||
                                         !Objects.equals(original.reason, dto.reason) ||
                                         !Objects.equals(original.status, dto.status) ||
                                         !Objects.equals(original.date, dto.date);
                    if (isModified || AbsenceDTO.STATUS_MODIFIED.equals(dto.status)) {
                        needsSave = true;
                    }
                } else {
                    // If DTO has an ID but wasn't in original fetchedMap, it could imply it was
                    // added or somehow changed externally. For safety, try to save it as an update.
                    // The AbsenceService.updateAbsence will handle if the ID doesn't actually exist.
                    needsSave = true;
                }
            }

            if (needsSave) {
                try {
                    AbsenceDTO savedDto = absenceService.saveAbsence(dto); // Use the new saveAbsence method
                    successfulChanges.add(savedDto);
                } catch (ApplicationException e) {
                    System.err.println("Controller: Failed to save absence " + (dto.id != null ? dto.id : "new") + ": " + e.getMessage());
                    failedChanges.add(dto);
                }
            }
        }

        if (!failedChanges.isEmpty()) {
            absenceView.showErrorMessage("Some changes failed to save. Please review. Failed items: " + failedChanges.stream().map(d -> d.id != null ? d.id : "new").collect(Collectors.joining(", ")));
        } else {
            absenceView.showSuccessMessage("Absence records updated successfully."); // REQ-010
        }
        // Refresh the view with the latest data after save, or show successful changes
        // For simplicity, let's re-fetch and display for the current date.
        if (currentDate != null) {
            selectDate(currentDate);
        }
    }

    /**
     * Handles the event when the "Cancel" button is clicked.
     * REQ-009: Controller -> View : displayAbsences(originalAbsenceDTOs)
     */
    public void cancelEditing() {
        System.out.println("Controller: Canceling editing. Restoring original data.");
        // Restore the view to its original state (before any edits were made)
        absenceView.displayAbsences(fetchedAbsences); // Display original data
    }

    // Helper method to get the current date, might be useful for View
    public Date getCurrentDate() {
        return currentDate;
    }

    // Individual action methods (can be called by view if more granular control is needed)
    public void addAbsence(AbsenceDTO absenceDTO) {
        System.out.println("Controller: Delegating add absence to service.");
        try {
            AbsenceDTO addedAbsence = absenceService.addAbsence(absenceDTO);
            absenceView.showSuccessMessage("Absence added successfully: " + addedAbsence.id);
            if (currentDate != null) { // Refresh current view if date is selected
                selectDate(currentDate);
            }
        } catch (ApplicationException e) {
            absenceView.showErrorMessage("Failed to add absence: " + e.getMessage());
        }
    }

    public void updateAbsence(AbsenceDTO absenceDTO) {
        System.out.println("Controller: Delegating update absence to service.");
        try {
            AbsenceDTO updatedAbsence = absenceService.updateAbsence(absenceDTO);
            absenceView.showSuccessMessage("Absence updated successfully: " + updatedAbsence.id);
            if (currentDate != null) { // Refresh current view if date is selected
                selectDate(currentDate);
            }
        } catch (ApplicationException e) {
            absenceView.showErrorMessage("Failed to update absence: " + e.getMessage());
        }
    }

    public void deleteAbsence(String absenceId) {
        System.out.println("Controller: Delegating delete absence to service.");
        try {
            absenceService.deleteAbsence(absenceId);
            absenceView.showSuccessMessage("Absence deleted successfully: " + absenceId);
            if (currentDate != null) { // Refresh current view if date is selected
                selectDate(currentDate);
            }
        } catch (ApplicationException e) {
            absenceView.showErrorMessage("Failed to delete absence: " + e.getMessage());
        }
    }
}