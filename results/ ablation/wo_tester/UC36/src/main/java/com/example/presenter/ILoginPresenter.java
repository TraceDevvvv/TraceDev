package com.example.presenter;

import com.example.dto.InvalidLoginResponseDTO;

/**
 * Interface for login presenters.
 */
public interface ILoginPresenter {
    /**
     * Presents the invalid login response to the user.
     * @param response the invalid login response DTO
     */
    void presentInvalidLogin(InvalidLoginResponseDTO response);

    /**
     * Requests confirmation from the user.
     * @param message the confirmation message
     * @return true if confirmed, false otherwise
     */
    boolean requestConfirmation(String message);
}