package com.example;

/**
 * Controller handling details requests.
 */
public class RegisterDetailsController {
    private RegisterDetailsInteractor interactor;

    public RegisterDetailsController(RegisterDetailsInteractor interactor) {
        this.interactor = interactor;
    }

    public RegisterDetailsDTO handleDetailsRequest(String registerId) {
        try {
            return interactor.getRegisterDetails(registerId);
        } catch (ConnectionException e) {
            // Handle connection error.
            return null; // In reality, return an error DTO.
        }
    }
}