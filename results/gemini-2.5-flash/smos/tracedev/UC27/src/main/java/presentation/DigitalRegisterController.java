package presentation;

import application.AcademicRecordService;
import domain.AcademicYear;
import domain.DigitalRegister;

import java.util.List;

/**
 * The Presentation Layer controller for the Digital Register feature.
 * It mediates between the DigitalRegisterView and the AcademicRecordService,
 * handling user input events and orchestrating the display of data.
 */
public class DigitalRegisterController {

    // Dependencies injected through the constructor
    private AcademicRecordService academicRecordService;
    private DigitalRegisterView digitalRegisterView;

    /**
     * Constructs a DigitalRegisterController with its required dependencies.
     *
     * @param academicRecordService The service layer component for academic record operations.
     * @param digitalRegisterView The view component for displaying information and receiving input.
     */
    public DigitalRegisterController(AcademicRecordService academicRecordService, DigitalRegisterView digitalRegisterView) {
        this.academicRecordService = academicRecordService;
        this.digitalRegisterView = digitalRegisterView;
        // Ensure the view has a reference back to this controller
        this.digitalRegisterView.setController(this);
    }

    /**
     * Handles the initial selection of the 'Digital Register' feature by the user.
     * This method corresponds to the `handleDigitalRegisterSelection()` call from the View.
     *
     * It initiates the process of fetching available academic years and displaying them.
     * (Corresponds to Sequence Diagram Step 2)
     */
    public void handleDigitalRegisterSelection() {
        System.out.println("[Controller] Handling digital register selection. Getting available years.");
        try {
            // Step 2: System shows screen for year selection.
            List<AcademicYear> availableYears = academicRecordService.getAvailableAcademicYears();
            digitalRegisterView.showYearSelectionScreen(availableYears);
        } catch (Exception e) {
            digitalRegisterView.displayError("Failed to retrieve academic years: " + e.getMessage());
        }
    }

    /**
     * Handles the user's selection of a specific academic year.
     * This method corresponds to the `selectAcademicYear(yearId)` call from the View.
     *
     * It retrieves the digital registers for the chosen year and instructs the view to display them.
     * (Corresponds to Sequence Diagram Step 4)
     *
     * @param yearId The unique identifier of the academic year selected by the user.
     */
    public void selectAcademicYear(String yearId) {
        System.out.println("[Controller] Selected academic year: " + yearId + ". Searching for digital records.");
        if (yearId == null || yearId.trim().isEmpty()) {
            digitalRegisterView.displayError("Academic year ID cannot be empty.");
            return;
        }
        try {
            // Step 4: System searches for digital records.
            List<DigitalRegister> digitalRegisters = academicRecordService.retrieveDigitalRegisters(yearId);

            // Step 5: System displays digital records.
            digitalRegisterView.displayDigitalRegisters(digitalRegisters);
        } catch (Exception e) {
            digitalRegisterView.displayError("Failed to retrieve digital registers for year " + yearId + ": " + e.getMessage());
        }
    }
}