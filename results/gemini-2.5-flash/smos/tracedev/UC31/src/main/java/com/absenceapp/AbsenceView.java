
package com.absenceapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Optional; // Added import for Optional
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Represents the UI component for displaying and managing absences.
 * This is a console-based simulation of a graphical user interface.
 */
public class AbsenceView {
    private AbsenceViewController controller;
    private List<AbsenceDTO> currentAbsences; // Absences currently displayed and potentially modified
    private List<AbsenceDTO> originalAbsences; // Absences fetched from service, used for cancel
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public AbsenceView() {
        this.currentAbsences = new ArrayList<>();
        this.originalAbsences = new ArrayList<>();
    }

    /**
     * Sets the controller for this view.
     *
     * @param controller The AbsenceViewController instance.
     */
    public void setController(AbsenceViewController controller) {
        this.controller = controller;
    }

    /**
     * Displays a list of absence DTOs.
     * This method is called by the controller after fetching data.
     *
     * @param absences The list of AbsenceDTOs to display.
     */
    public void displayAbsences(List<AbsenceDTO> absences) {
        System.out.println("\n--- Absence Records for " + (controller.getCurrentDate() != null ? dateFormatter.format(controller.getCurrentDate()) : "Selected Date") + " ---");
        this.currentAbsences = new ArrayList<>(absences); // Create a mutable copy for editing
        this.originalAbsences = absences.stream()
                                    .map(dto -> new AbsenceDTO(dto.id, dto.studentId, dto.date, dto.type, dto.reason, dto.status))
                                    .collect(Collectors.toList()); // Deep copy for original state

        if (currentAbsences.isEmpty()) {
            System.out.println("No absence records found for this date.");
            return;
        }

        System.out.printf("%-5s %-10s %-15s %-10s %-10s %-20s %-10s\n", "#", "ID", "Student ID", "Date", "Type", "Reason", "Status");
        System.out.println("----------------------------------------------------------------------------------");
        AtomicInteger index = new AtomicInteger(1);
        currentAbsences.forEach(dto ->
                System.out.printf("%-5d %-10.10s %-15s %-10s %-10s %-20.20s %-10s\n",
                        index.getAndIncrement(),
                        dto.id != null ? dto.id.substring(0, Math.min(dto.id.length(), 8)) + ".." : "NEW",
                        dto.studentId,
                        dateFormatter.format(dto.date),
                        dto.type,
                        dto.reason,
                        dto.status
                )
        );
        System.out.println("----------------------------------------------------------------------------------");
    }

    /**
     * Simulates user input to get modified absences.
     * In a real UI, this would be handled by event listeners.
     * For this simulation, it provides a menu for adding, updating, or deleting.
     *
     * @return A list of AbsenceDTOs representing changes from the UI.
     */
    public List<AbsenceDTO> getModifiedAbsences() {
        // This method is primarily to illustrate the flow from the UI.
        // The actual changes (add/update/delete) are handled by specific methods
        // like `addAbsence`, `updateAbsence`, `deleteAbsence` which then call the controller.
        // For `saveAbsenceChanges`, the controller needs to know what was added, modified, or deleted.
        // In a real UI, it collects all changes and sends them.
        // For this console simulation, `onSaveClicked` will collect `currentAbsences`
        // and the controller will determine what changed.
        System.out.println("View: Collecting modified absences (This method primarily triggers `onSaveClicked`).");
        return currentAbsences; // Return the current state, assuming controller will figure out changes
    }


    /**
     * Simulates the administrator selecting a date.
     * REQ-006: Administrator -> View : onDateSelected(date : Date)
     */
    public void onDateSelected(Date date) {
        System.out.println("\nView: Administrator selected date: " + dateFormatter.format(date));
        // REQ-007: View -> Controller : selectDate(date : Date)
        if (controller != null) {
            controller.selectDate(date);
        }
    }

    /**
     * Simulates the administrator making changes to absence records.
     * This is a simplified way to represent changes for the sequence diagram.
     * It directly manipulates the `currentAbsences` list and marks DTOs with their change status.
     *
     * @param operation The type of operation ("add", "update", "delete").
     * @param studentId The student ID for the operation.
     * @param date The date for the operation.
     * @param type The absence type for the operation.
     * @param reason The reason for the operation.
     * @param idToDelete The ID of the absence to delete (if operation is "delete").
     */
    public void editAbsenceDetails(String operation, String studentId, Date date, String type, String reason, String idToDelete) {
        System.out.println("View: Administrator is editing absence details: " + operation);
        if (operation.equalsIgnoreCase("add")) {
            AbsenceDTO newDTO = AbsenceDTO.createNew(studentId, date, type, reason);
            currentAbsences.add(newDTO);
            System.out.println("View: Added a new absence to current list (pending save): " + newDTO);
        } else if (operation.equalsIgnoreCase("update")) {
            // Find an existing DTO by studentId and date, then update it
            // For simplicity, let's assume we update the first matching student ID and date
            Optional<AbsenceDTO> dtoToUpdateOpt = currentAbsences.stream()
                    .filter(dto -> dto.studentId.equals(studentId) && dateFormatter.format(dto.date).equals(dateFormatter.format(date)))
                    .findFirst();

            if (dtoToUpdateOpt.isPresent()) {
                AbsenceDTO dtoToUpdate = dtoToUpdateOpt.get();
                dtoToUpdate.type = type;
                dtoToUpdate.reason = reason;
                // Mark as modified if it wasn't already new
                if (!AbsenceDTO.STATUS_NEW.equals(dtoToUpdate.status)) {
                    dtoToUpdate.status = AbsenceDTO.STATUS_MODIFIED;
                }
                System.out.println("View: Updated absence in current list (pending save): " + dtoToUpdate);
            } else {
                System.out.println("View: Could not find absence to update for student " + studentId + " on " + dateFormatter.format(date));
            }
        } else if (operation.equalsIgnoreCase("delete")) {
            Optional<AbsenceDTO> dtoToDeleteOpt = currentAbsences.stream()
                    .filter(dto -> dto.id != null && dto.id.equals(idToDelete))
                    .findFirst();
            if (dtoToDeleteOpt.isPresent()) {
                AbsenceDTO dtoToDelete = dtoToDeleteOpt.get();
                dtoToDelete.status = AbsenceDTO.STATUS_DELETED; // Mark for deletion
                System.out.println("View: Marked absence for deletion (pending save): " + dtoToDelete.id);
            } else {
                System.out.println("View: Absence with ID " + idToDelete + " not found for deletion.");
            }
        }
        displayCurrentChanges();
    }

    private void displayCurrentChanges() {
        System.out.println("\n--- Current Absences with Pending Changes ---");
        System.out.printf("%-5s %-10s %-15s %-10s %-10s %-20s %-10s %-10s\n", "#", "ID", "Student ID", "Date", "Type", "Reason", "DB_Status", "Change");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        AtomicInteger index = new AtomicInteger(1);
        currentAbsences.forEach(dto -> {
            String changeStatus = (dto.status != null && (dto.status.equals(AbsenceDTO.STATUS_NEW) || dto.status.equals(AbsenceDTO.STATUS_MODIFIED) || dto.status.equals(AbsenceDTO.STATUS_DELETED)))
                                ? dto.status : AbsenceDTO.STATUS_UNCHANGED;
            String dbStatus = (dto.status != null && !(dto.status.equals(AbsenceDTO.STATUS_NEW) || dto.status.equals(AbsenceDTO.STATUS_MODIFIED) || dto.status.equals(AbsenceDTO.STATUS_DELETED)))
                              ? dto.status : "N/A"; // Original DB status
            System.out.printf("%-5d %-10.10s %-15s %-10s %-10s %-20.20s %-10s %-10s\n",
                    index.getAndIncrement(),
                    dto.id != null ? dto.id.substring(0, Math.min(dto.id.length(), 8)) + ".." : "NEW",
                    dto.studentId,
                    dateFormatter.format(dto.date),
                    dto.type,
                    dto.reason,
                    dbStatus,
                    changeStatus
            );
        });
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }


    /**
     * Simulates the administrator clicking the "Save" button.
     */
    public void onSaveClicked() {
        System.out.println("\nView: Administrator clicked SAVE. Sending modified absences to controller.");
        if (controller != null) {
            controller.saveAbsenceChanges(currentAbsences); // Send the entire list, controller decides what changed
        }
    }

    /**
     * Simulates the administrator clicking the "Cancel" button.
     */
    public void onCancelClicked() {
        System.out.println("\nView: Administrator clicked CANCEL. Notifying controller.");
        if (controller != null) {
            controller.cancelEditing();
        }
    }

    /**
     * Displays a success message to the administrator.
     *
     * @param message The success message.
     */
    public void showSuccessMessage(String message) {
        System.out.println("\n--- SUCCESS ---");
        System.out.println(message);
        System.out.println("---------------");
    }

    /**
     * Displays an error message to the administrator.
     *
     * @param message The error message.
     */
    public void showErrorMessage(String message) {
        System.err.println("\n--- ERROR ---");
        System.err.println(message);
        System.err.println("-------------");
    }

    // Methods for individual UI actions (called by controller or simulated internally)
    public void addAbsence(AbsenceDTO absence) {
        // In a real UI, this would add a new row/entry to the display
        // For this console view, `displayAbsences` will implicitly show new items after a save/refresh
        System.out.println("View: Adding new absence to display (should refresh entire list after save). DTO: " + absence.id);
    }

    public void updateAbsence(AbsenceDTO absence) {
        // In a real UI, this would update an existing row/entry
        System.out.println("View: Updating absence in display (should refresh entire list after save). DTO: " + absence.id);
    }

    public void deleteAbsence(String absenceId) {
        // In a real UI, this would remove a row/entry
        System.out.println("View: Deleting absence from display (should refresh entire list after save). ID: " + absenceId);
    }

    /**
     * Returns the original list of absences fetched, for use in cancel operations.
     *
     * @return The original list of AbsenceDTOs.
     */
    public List<AbsenceDTO> getOriginalAbsences() {
        return originalAbsences;
    }
}
