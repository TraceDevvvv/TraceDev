package adapters;

import application.EditRefreshmentPointUseCaseController;
import application.OperationResult;
import application.RefreshmentPointDTO;
import application.SearchRefreshmentPointUseCase;
import domain.RefreshmentPoint;
import interfaces.ConfirmationHandler;
import interfaces.IRefreshmentPointRepository;
import java.util.List;
import java.util.Optional;

/**
 * Controller for refreshment point operations.
 */
public class RefreshmentPointController {
    private EditRefreshmentPointUseCaseController useCaseController;
    private SearchRefreshmentPointUseCase searchUseCase; // Added for Flow step 1
    private IRefreshmentPointRepository pointRepository;
    private EditPointForm editForm;
    private ConfirmationHandler confirmationHandler;

    public RefreshmentPointController(EditRefreshmentPointUseCaseController useCaseController,
                                      SearchRefreshmentPointUseCase searchUseCase,
                                      IRefreshmentPointRepository pointRepository,
                                      EditPointForm editForm,
                                      ConfirmationHandler confirmationHandler) {
        this.useCaseController = useCaseController;
        this.searchUseCase = searchUseCase;
        this.pointRepository = pointRepository;
        this.editForm = editForm;
        this.confirmationHandler = confirmationHandler;
    }

    /**
     * Displays the list of points (Flow step 1).
     * @return list of DTOs.
     */
    public List<RefreshmentPointDTO> displayPointList() {
        // Assumption: Using search use case to get all points.
        return searchUseCase.getActivePoints();
    }

    /**
     * Selects a point by ID and returns its DTO if active and functional.
     * @param pointId the point ID.
     * @return the DTO or null if not editable.
     */
    public RefreshmentPointDTO selectPoint(String pointId) {
        Optional<RefreshmentPoint> pointOpt = pointRepository.findById(pointId);
        if (pointOpt.isPresent()) {
            RefreshmentPoint point = pointOpt.get();
            if (point.isActive() && point.isFunctional()) {
                return useCaseController.loadPointData(pointId);
            }
        }
        // If not editable, return null (error will be displayed by caller)
        return null;
    }

    /**
     * Shows the edit form with point data.
     * @param pointDTO the DTO containing point data.
     */
    public void showEditForm(RefreshmentPointDTO pointDTO) {
        editForm.setFormData(pointDTO);
        editForm.display();
    }

    /**
     * Submits the edit form.
     * @param formData the DTO with form data.
     * @return the operation result.
     */
    public OperationResult submitEditForm(RefreshmentPointDTO formData) {
        // Enable submission block in form (as per sequence diagram)
        editForm.enableSubmissionBlock();
        // Delegate to use case controller
        OperationResult result = useCaseController.execute(formData, confirmationHandler);
        // Disable submission block after operation
        editForm.disableSubmissionBlock();
        return result;
    }
}