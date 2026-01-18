package com.example;

/**
 * Represents a tourist who can modify comments.
 */
public class Tourist {
    private String id;
    private String name;

    public Tourist(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Chooses to modify a comment (initiates the use case).
     * @param commentId the ID of the comment to modify
     */
    public void chooseToModifyComment(String commentId) {
        // Typically, this would trigger UI logic. For simplicity, we just log.
        System.out.println("Tourist " + name + " chooses to modify comment with ID: " + commentId);
    }

    /**
     * Confirms the modification after preview.
     */
    public void confirmModification() {
        System.out.println("Tourist " + name + " confirms the modification.");
    }
}