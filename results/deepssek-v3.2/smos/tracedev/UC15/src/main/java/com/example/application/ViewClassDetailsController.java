package com.example.application;

import com.example.ui.ClassDetailsDTO;
import com.example.ui.ErrorDTO;

/**
 * Controller for handling requests to view class details.
 * It depends on the ViewClassDetailsUseCase to execute the business logic.
 */
public class ViewClassDetailsController {
    private ViewClassDetailsUseCase useCase;

    /**
     * Constructor for the controller.
     * @param useCase The use case to be used for retrieving class details.
     */
    public ViewClassDetailsController(ViewClassDetailsUseCase useCase) {
        this.useCase = useCase;
    }

    /**
     * Handles the request to view class details.
     * @param classId The ID of the class to retrieve.
     * @return Either a ClassDetailsDTO or an ErrorDTO.
     */
    public Object handleRequest(String classId) {
        return useCase.execute(classId);
    }

    /**
     * Returns an error response.
     * @return ErrorDTO representing an error.
     */
    public ErrorDTO errorResponse() {
        return new ErrorDTO("Error response from controller", 500);
    }
}