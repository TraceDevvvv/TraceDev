package com.example.login;

/**
 * Presentation Layer: Represents the user's workspace view after successful login.
 */
public class WorkspaceView {
    private User user; // Holds the currently logged-in user

    /**
     * Displays the workspace for the given user.
     * @param user The user whose workspace is to be displayed.
     * @return A string representing the content of the workspace.
     */
    public String display(User user) {
        this.user = user;
        System.out.println("\nWelcome to your Workspace, " + user.getUsername() + "!");
        System.out.println("User ID: " + user.getId());
        // In a real application, this would render a UI.
        return "Workspace for " + user.getUsername();
    }
}