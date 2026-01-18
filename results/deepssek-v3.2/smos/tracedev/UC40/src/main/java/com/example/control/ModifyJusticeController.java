package com.example.control;

import com.example.usecase.ModifyJusticeUseCase;
import com.example.request.ModifyJusticeRequest;
import com.example.response.ModifyJusticeResponse;
import com.example.connection.ConnectionHandler;
import com.example.boundary.JusticeChangeForm;
import java.util.Date;
import java.util.Map;

/**
 * Controller that handles save requests from the boundary and interacts with the use case.
 */
public class ModifyJusticeController {
    private ModifyJusticeUseCase modifyJusticeUseCase;
    private ConnectionHandler connectionHandler;

    /**
     * Constructor.
     */
    public ModifyJusticeController(ModifyJusticeUseCase useCase, ConnectionHandler connectionHandler) {
        this.modifyJusticeUseCase = useCase;
        this.connectionHandler = connectionHandler;
    }

    /**
     * Handle save request from the form.
     */
    public Response handleSaveRequest(Map<String, Object> formData) {
        // Extract data from form map and create request
        // Assumption: formData contains "justiceId", "newDate", "newJustificationText"
        int justiceId = (int) formData.get("justiceId");
        Date newDate = (Date) formData.get("newDate");
        String newText = (String) formData.get("newJustificationText");

        ModifyJusticeRequest request = createModifyJusticeRequestFromFormData(formData);
        ModifyJusticeResponse response = modifyJusticeUseCase.execute(request);

        // Wrap the response into a generic Response object for the boundary
        return new Response(response.isSuccess(), response.getMessage());
    }

    /**
     * Create ModifyJusticeRequest from formData.
     */
    public ModifyJusticeRequest createModifyJusticeRequestFromFormData(Map<String, Object> formData) {
        int justiceId = (int) formData.get("justiceId");
        Date newDate = (Date) formData.get("newDate");
        String newText = (String) formData.get("newJustificationText");
        return new ModifyJusticeRequest(justiceId, newDate, newText);
    }

    /**
     * Display success confirmation on the form.
     */
    public void displaySuccessConfirmation(JusticeChangeForm form, String message) {
        form.displaySuccessConfirmation(message);
    }

    /**
     * Display error message on the form.
     */
    public void displayErrorMessage(JusticeChangeForm form, String message) {
        form.displayErrorMessage(message);
    }

    /**
     * Handle connection interruption.
     */
    public void handleConnectionInterruption() {
        connectionHandler.clearSession();
        System.out.println("Controller: Connection interruption handled.");
    }

    /**
     * Inner class to represent a generic response for the boundary.
     */
    public static class Response {
        private boolean success;
        private String message;

        public Response(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}