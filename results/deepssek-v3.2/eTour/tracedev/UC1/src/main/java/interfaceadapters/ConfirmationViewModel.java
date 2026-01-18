package interfaceadapters;

/**
 * View model for confirmation messages.
 */
public class ConfirmationViewModel {
    private String message;

    public ConfirmationViewModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}