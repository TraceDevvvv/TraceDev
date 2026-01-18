package presentation;

import application.DeleteUserUseCase;
import domain.AuthenticationService;
import infrastructure.UserRepository;
import domain.User;
import java.util.List;

/**
 * Controller coordinating between view, use case, and authentication.
 * Implements the sequence diagram flow.
 */
public class DeleteUserController {
    private DeleteUserView deleteUserView;
    private DeleteUserUseCase deleteUserUseCase;
    private AuthenticationService authenticationService;
    private static final String ADMIN_ID = "admin";

    public DeleteUserController(DeleteUserView deleteUserView,
                                DeleteUserUseCase deleteUserUseCase,
                                AuthenticationService authenticationService) {
        this.deleteUserView = deleteUserView;
        this.deleteUserUseCase = deleteUserUseCase;
        this.authenticationService = authenticationService;
    }

    /**
     * Handles delete request from the view.
     * Checks login status, then invokes the use case.
     */
    public void handleDeleteRequest(String userId) {
        System.out.println("DeleteUserController: Handling delete request for user " + userId);

        // Check login status as per sequence diagram
        if (!authenticationService.isUserLoggedIn(ADMIN_ID)) {
            deleteUserView.showErrorMessage("Administrator not logged in.");
            return;
        }

        // Optional confirmation (implied by the class diagram)
        boolean confirmed = deleteUserView.displayConfirmation();
        if (!confirmed) {
            System.out.println("DeleteUserController: Deletion cancelled by user.");
            return;
        }

        // Execute the deletion use case
        deleteUserUseCase.execute(userId);

        // After successful deletion, show updated user list and success message
        // Retrieve updated list from repository (via use case or directly)
        UserRepository repository = deleteUserUseCase.getUserRepository();
        if (repository != null) {
            List<User> updatedList = repository.findAll();
            deleteUserView.displayUpdatedUserList(updatedList);
            deleteUserView.showsUpdatedUserList();
        }

        deleteUserView.showSuccessMessage();
        deleteUserView.showsSuccessMessage();
    }
}