package com.example;

import java.util.List;

/**
 * The TagController acts as the entry point for handling tag-related requests
 * from the user interface (Agency Operator). It coordinates between the
 * service layer, security layer, and notification layer.
 * This is part of the Presentation Layer.
 */
public class TagController {

    private final ITagService tagService;
    private final ISecurityService securityService;
    private final INotificationService notificationService;

    /**
     * Constructs a TagController with its required dependencies.
     *
     * @param tagService The service responsible for business logic related to tags.
     * @param securityService The service responsible for authentication and authorization.
     * @param notificationService The service responsible for sending user notifications.
     */
    public TagController(ITagService tagService, ISecurityService securityService, INotificationService notificationService) {
        this.tagService = tagService;
        this.securityService = securityService;
        this.notificationService = notificationService;
    }

    /**
     * Handles the request to display a form for deleting tags.
     * This method includes security checks and error handling for connection issues.
     *
     * @return A list of existing tags to be displayed on the form.
     * @throws ConnectionException if there's an issue connecting to the data source.
     */
    public List<Tag> showDeleteTagsForm() throws ConnectionException {
        System.out.println("TagController: showDeleteTagsForm() called by Agency Operator.");

        // Precondition: Agency Operator is logged in (Entry Condition)
        if (!securityService.isAuthenticated()) {
            System.out.println("TagController: User is not authenticated. Denying access.");
            notificationService.notifyError("Access Denied: Not logged in.");
            // In a real web app, this would redirect to a login page or error page
            displayErrorPage("Access Denied: Not logged in.");
            return List.of(); // Return empty list or throw specific access denied exception
        }
        System.out.println("TagController: User is authenticated. Proceeding.");

        // Step 1: Agency Operator accesses the functionality to delete a tag.
        System.out.println("TagController: User accessing functionality to delete tags.");

        try {
            // Controller -> Service: getExistingTags()
            List<Tag> tags = tagService.getExistingTags();
            System.out.println("TagController: Received " + tags.size() + " existing tags.");

            // Controller -> AO : displayTagsForm(tags : List<Tag>)
            // Step 3: System displays tags in a form (simulated by returning the list)
            System.out.println("TagController: Displaying tags form with available tags: " + tags);
            return tags;
        } catch (ConnectionException e) {
            System.err.println("TagController: Caught ConnectionException during getExistingTags: " + e.getMessage());
            // Controller -> Notifier: notifyError("Connection to server lost...")
            notificationService.notifyError("Connection to server lost. Please try again later. (" + e.getMessage() + ")");
            // Controller -> AO: notifyError("Connection to server lost...")
            // The exception is re-thrown as per SD, the caller (Main) would handle the display.
            throw e;
        }
    }

    /**
     * Handles the request to delete selected tags.
     * This method includes security checks and error handling for connection issues.
     *
     * @param tagIds A list of IDs of the tags to be deleted.
     * @return A message indicating the outcome of the deletion operation.
     * @throws ConnectionException if there's an issue connecting to the data source.
     */
    public String deleteSelectedTags(List<String> tagIds) throws ConnectionException {
        System.out.println("TagController: deleteSelectedTags() called for IDs: " + tagIds);

        // Precondition: Agency Operator is logged in (Entry Condition)
        if (!securityService.isAuthenticated()) {
            System.out.println("TagController: User is not authenticated. Denying deletion.");
            notificationService.notifyError("Access Denied: Not logged in.");
            displayErrorPage("Access Denied: Not logged in.");
            return "Access Denied: Not logged in.";
        }
        System.out.println("TagController: User is authenticated. Proceeding with deletion.");

        // Step 4 & 5: Operator selects tags and sends deletion request.
        System.out.println("TagController: User requested deletion of tags: " + tagIds);

        try {
            // Controller -> Service: deleteTags(tagIds : List<String>)
            tagService.deleteTags(tagIds);
            System.out.println("TagController: Tags deleted successfully by service.");

            // Controller -> Notifier: notifySuccess("Selected tags removed successfully.")
            notificationService.notifySuccess("Selected tags removed successfully.");
            System.out.println("TagController: Deletion process complete. Notified success.");
            return "Selected tags removed successfully.";
        } catch (ConnectionException e) {
            System.err.println("TagController: Caught ConnectionException during deleteTags: " + e.getMessage());
            // Controller -> Notifier: notifyError("Connection to server lost...")
            notificationService.notifyError("Connection to server lost. Please try again later. (" + e.getMessage() + ")");
            // Controller -> AO: notifyError("Connection to server lost...")
            // The exception is re-thrown as per SD, the caller (Main) would handle the display.
            throw e;
        }
    }

    /**
     * Simulates displaying an error page to the user.
     * In a real application, this would render a specific UI page.\
     * @param message The error message to display.
     */
    private void displayErrorPage(String message) {
        System.err.println("TagController: Displaying error page to user: " + message);
        // This method simulates a UI action. In a web app, it would render an error view.
    }
}