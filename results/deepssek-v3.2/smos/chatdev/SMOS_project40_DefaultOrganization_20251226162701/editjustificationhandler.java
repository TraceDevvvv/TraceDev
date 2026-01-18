'''
EditJustificationHandler.java
Handles the edit justification use case: validating input, updating the database, and managing state.
Ensures all preconditions and postconditions are met.
'''
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;
public class EditJustificationHandler {
    private static final Logger LOGGER = Logger.getLogger(EditJustificationHandler.class.getName());
    private final SMOSSystem smosSystem;
    private Justification currentJustification;
    public EditJustificationHandler(SMOSSystem smosSystem) {
        if (smosSystem == null) {
            throw new IllegalArgumentException("SMOSSystem cannot be null");
        }
        this.smosSystem = smosSystem;
    }
    public Justification viewJustificationDetails(int justificationId) {
        if (!smosSystem.isAdminLoggedIn()) {
            String errorMsg = "Admin must be logged in to view justification details";
            LOGGER.severe(errorMsg);
            throw new IllegalStateException(errorMsg);
        }
        if (!smosSystem.isSMOSConnected()) {
            String errorMsg = "SMOS server must be connected";
            LOGGER.severe(errorMsg);
            throw new IllegalStateException(errorMsg);
        }
        currentJustification = DatabaseSimulator.getJustification(justificationId);
        if (currentJustification == null) {
            String errorMsg = "Justification with ID " + justificationId + " not found";
            LOGGER.warning(errorMsg);
            return null;
        }
        LOGGER.info("Viewing justification details: " + currentJustification);
        return currentJustification;
    }
    public boolean editJustificationFields(String newDateStr, String newDescription) {
        if (!smosSystem.isAdminLoggedIn()) {
            LOGGER.severe("Admin must be logged in to edit justification");
            return false;
        }
        if (currentJustification == null) {
            LOGGER.severe("No justification loaded for editing");
            return false;
        }
        try {
            LocalDate newDate = null;
            if (newDateStr != null && !newDateStr.trim().isEmpty()) {
                newDateStr = newDateStr.trim();
                if (!currentJustification.isValidDate(newDateStr)) {
                    LOGGER.warning("Invalid date format: " + newDateStr);
                    return false;
                }
                newDate = LocalDate.parse(newDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            }
            String updatedDescription = null;
            if (newDescription != null && !newDescription.trim().isEmpty()) {
                updatedDescription = newDescription.trim();
            }
            boolean dateChanged = (newDate != null && !newDate.equals(currentJustification.getDate()));
            boolean descChanged = (updatedDescription != null && 
                                 !updatedDescription.equals(currentJustification.getDescription()));
            if (dateChanged) {
                currentJustification.setDate(newDate);
                LOGGER.info("Date updated to: " + newDate);
            }
            if (descChanged) {
                currentJustification.setDescription(updatedDescription);
                LOGGER.info("Description updated");
            }
            if (dateChanged || descChanged) {
                LOGGER.info("Justification fields updated locally");
                return true;
            } else {
                LOGGER.info("No changes detected");
                return true;
            }
        } catch (DateTimeParseException e) {
            LOGGER.severe("Date parsing error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            LOGGER.severe("Unexpected error during edit: " + e.getMessage());
            return false;
        }
    }
    public boolean saveJustification() {
        if (!smosSystem.isAdminLoggedIn()) {
            LOGGER.severe("Admin must be logged in to save justification");
            return false;
        }
        if (!smosSystem.isSMOSConnected()) {
            LOGGER.severe("SMOS server must be connected to save");
            return false;
        }
        if (currentJustification == null) {
            LOGGER.severe("No justification to save");
            return false;
        }
        try {
            boolean success = DatabaseSimulator.updateJustification(currentJustification);
            if (success) {
                LOGGER.info("Justification ID " + currentJustification.getId() + " saved successfully");
                return true;
            } else {
                LOGGER.severe("Failed to save justification ID " + currentJustification.getId());
                return false;
            }
        } catch (Exception e) {
            LOGGER.severe("Database error while saving: " + e.getMessage());
            return false;
        }
    }
    public Justification getCurrentJustification() {
        return currentJustification;
    }
    public void clearCurrentJustification() {
        currentJustification = null;
    }
}