package com.example;

/**
 * Controller for inserting a teaching, extending UseCaseController.
 */
public class InsertTeachingController extends UseCaseController {
    private ErrorHandler errorHandler;

    /**
     * Constructor for InsertTeachingController.
     * @param repository the teaching repository
     * @param errorHandler the error handler
     */
    public InsertTeachingController(TeachingRepository repository, ErrorHandler errorHandler) {
        super(repository);
        this.errorHandler = errorHandler;
    }

    /**
     * Executes the insert teaching request.
     * @param request the insert teaching request
     * @return the insert teaching response
     */
    public InsertTeachingResponse execute(InsertTeachingRequest request) {
        if (!validateData(request.getForm())) {
            errorHandler.handleValidationError("Invalid data");
            return new InsertTeachingResponse(false, null, "Invalid data");
        }

        TeachingService service = new TeachingService(repository);
        try {
            Teaching teaching = service.insertTeaching(request.getForm());
            return new InsertTeachingResponse(true, teaching, null);
        } catch (Exception e) {
            errorHandler.handleConnectionError();
            return new InsertTeachingResponse(false, null, "Connection error: " + e.getMessage());
        }
    }

    /**
     * Validates the form data.
     * @param form the teaching form
     * @return true if data is valid
     */
    boolean validateData(TeachingForm form) {
        return form != null && form.isFilled();
    }

    /**
     * Creates an InsertTeachingRequest from a TeachingForm.
     * @param form the teaching form
     * @return the insert teaching request
     */
    public InsertTeachingRequest createInsertTeachingRequest(TeachingForm form) {
        return new InsertTeachingRequest(form);
    }

    /**
     * Cancels the insertion operation.
     */
    public void cancelInsertion() {
        errorHandler.handleInterruption();
    }

    // Override to match parent signature, delegates to typed method
    @Override
    public Response execute(Object request) {
        if (request instanceof InsertTeachingRequest) {
            return execute((InsertTeachingRequest) request);
        }
        throw new IllegalArgumentException("Request must be InsertTeachingRequest");
    }
}