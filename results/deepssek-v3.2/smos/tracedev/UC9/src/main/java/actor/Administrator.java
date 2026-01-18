package actor;

import domain.AuthenticationService;
import presentation.DeleteUserView;

/**
 * Represents the actor (Administrator) interacting with the system.
 */
public class Administrator {
    private boolean isLoggedIn = false;

    public boolean login(String credentials, AuthenticationService authService) {
        isLoggedIn = authService.authenticate(credentials);
        return isLoggedIn;
    }

    public void logout(AuthenticationService authService) {
        if (isLoggedIn) {
            authService.logout("admin");
            isLoggedIn = false;
        }
    }

    /**
     * Simulates the administrator clicking the delete button in the UI.
     */
    public void triggerDeleteUser(DeleteUserView view, String userId) {
        if (isLoggedIn) {
            view.onDeleteButtonClick(userId);
        } else {
            System.out.println("Administrator: Cannot trigger delete – not logged in.");
        }
    }

    public void clicksDeleteButton(DeleteUserView view) {
        // This method corresponds to the sequence diagram message "clicks "Delete" button"
        // It triggers the view's corresponding method.
        view.clicksDeleteButton();
    }
}