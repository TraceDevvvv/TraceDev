package com.example.system;

/**
 * Concrete user interface implementing presentation layer.
 */
public class UserInterface extends PresentationLayer {
    public ErrorMessageWindow errorMessageWindow;
    public ConfirmationPrompt confirmationPrompt;

    public UserInterface() {
        this.errorMessageWindow = new ErrorMessageWindow();
        this.confirmationPrompt = new ConfirmationPrompt();
    }

    @Override
    public void displayToUser(String message) {
        System.out.println("UI: " + message);
    }

    @Override
    public String getInputFromUser(String prompt) {
        System.out.print("UI Prompt: " + prompt);
        // Simulating user input for demonstration
        return "sample_tag";
    }

    @Override
    public boolean confirmFromUser(String message) {
        return confirmReading();
    }

    /**
     * Method called when user enters a search tag.
     * @param tag the search tag entered by user
     */
    public void enterSearchTag(String tag) {
        displayToUser("User entered tag: " + tag);
        TagManager tagManager = new TagManager();
        boolean isValid = tagManager.validateTag(tag);
        if (!isValid) {
            ErrorHandler errorHandler = new ErrorHandler();
            errorHandler.notifyUser("Duplicate tag error");
        } else {
            proceedWithValidTag();
        }
    }

    /**
     * Confirms that user has read the error message.
     * @return true if user confirms reading
     */
    public boolean confirmReading() {
        boolean confirmed = confirmationPrompt.ask("Confirm reading notification?");
        if (confirmed) {
            UseCaseController controller = UseCaseController.getInstance();
            controller.coordinateDuplicateTagHandling("recovery_tag");
        }
        return confirmed;
    }

    /**
     * Proceeds when tag is valid.
     */
    public void proceedWithValidTag() {
        displayToUser("Tag is valid, proceeding...");
    }

    /**
     * Displays recovery completion message.
     */
    public void displayRecoveryComplete() {
        displayToUser("System recovery completed. Control returned to user.");
    }
}