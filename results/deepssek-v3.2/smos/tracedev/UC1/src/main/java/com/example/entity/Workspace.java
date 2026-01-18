package com.example.entity;

/**
 * Represents a user's workspace.
 * Displayed after successful login.
 */
public class Workspace {
    private String workspaceId;
    private String displayName;

    public Workspace(String workspaceId, String displayName) {
        this.workspaceId = workspaceId;
        this.displayName = displayName;
    }

    /**
     * Displays the workspace for the given user.
     * In a real application, this would trigger UI updates.
     */
    public void displayWorkspace(User user) {
        System.out.println("Displaying workspace for user: " + user.getUsername());
        System.out.println("Workspace ID: " + workspaceId);
        System.out.println("Display Name: " + displayName);
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void workspaceDisplayed() {
        // This method corresponds to m37 in sequence diagram: return Workspace displayed.
        // It's a return message from Workspace to UI.
        // We can simply log that the workspace is displayed.
        System.out.println("Workspace displayed.");
    }
}