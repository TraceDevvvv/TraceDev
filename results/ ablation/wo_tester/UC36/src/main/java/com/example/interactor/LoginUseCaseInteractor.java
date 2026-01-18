package com.example.interactor;

import com.example.dto.InvalidLoginRequestDTO;
import com.example.dto.InvalidLoginResponseDTO;
import com.example.presenter.ILoginPresenter;
import com.example.service.LoginService;
import com.example.service.NotificationService;
import com.example.service.StateRecoveryService;

/**
 * Interactor for handling invalid login use case.
 * Implements requirement: Handles invalid login scenarios according to business requirements.
 */
public class LoginUseCaseInteractor {
    private LoginService loginService;
    private NotificationService notificationService;
    private StateRecoveryService stateRecoveryService;
    private ILoginPresenter presenter;

    public LoginUseCaseInteractor(LoginService loginService,
                                  NotificationService notificationService,
                                  StateRecoveryService stateRecoveryService) {
        this.loginService = loginService;
        this.notificationService = notificationService;
        this.stateRecoveryService = stateRecoveryService;
    }

    /**
     * Sets the presenter for this interactor.
     * @param presenter the presenter
     */
    public void setPresenter(ILoginPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Handles an invalid login request.
     * Implements sequence diagram interactions.
     * @param invalidLoginRequest the invalid login request DTO
     * @return the invalid login response DTO
     */
    public InvalidLoginResponseDTO handleInvalidLogin(InvalidLoginRequestDTO invalidLoginRequest) {
        String username = invalidLoginRequest.getUsername();
        String errorMessage = invalidLoginRequest.getErrorMessage();

        // Step 1: Send error notification (quality requirement: clearly notify user)
        notificationService.sendErrorNotification("Invalid login. Confirm you've read this error to return to the login screen.", username);
        notificationService.notificationSent(); // m6 return

        // Step 2: Request confirmation from user via NotificationService
        boolean confirmed = notificationService.requestConfirmation("Invalid login. Confirm you've read this error to return to the login screen.", username);
        // Note: In this implementation, NotificationService returns true (confirmed) as per sequence diagram.
        notificationService.returnTrueConfirmed(); // m8 return

        // Step 3: Restore previous state (requirement: Flow of Events #5)
        Object previousState = stateRecoveryService.restoreState();
        stateRecoveryService.returnPreviousState(); // m10 return

        // Step 4: Create response DTO (m11 sync message)
        InvalidLoginResponseDTO response = createResponseDTO(true, previousState);

        // Step 5: Return response DTO (Presenter will be notified via the presenter callback)
        // In this flow, the presenter calls presentInvalidLogin after receiving the response.
        return response; // m12 return
    }

    /**
     * Creates a response DTO as per sequence diagram message m11 (sync message).
     * @param requiresConfirmation whether confirmation is required
     * @param recoveryState the recovery state
     * @return the response DTO
     */
    private InvalidLoginResponseDTO createResponseDTO(boolean requiresConfirmation, Object recoveryState) {
        return new InvalidLoginResponseDTO(requiresConfirmation, recoveryState);
    }

    /**
     * Returns responseDTO as per sequence diagram message m12.
     * This method explicitly implements the return message from Interactor to Presenter.
     * @param response the response DTO
     * @return the same response DTO
     */
    public InvalidLoginResponseDTO returnResponseDTO(InvalidLoginResponseDTO response) {
        return response;
    }
}