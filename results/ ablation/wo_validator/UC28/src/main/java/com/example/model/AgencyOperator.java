package com.example.model;

import com.example.controller.RemoveTagController;
import java.util.List;

/**
 * Represents an Agency Operator actor.
 */
public class AgencyOperator {
    private String username;
    private RemoveTagController controller;

    public AgencyOperator(String username) {
        this.username = username;
        this.controller = new RemoveTagController();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Simulates login.
     * @return true if login successful.
     */
    public boolean login() {
        System.out.println(username + " logged in.");
        return true;
    }

    /**
     * Retrieves all tags via the controller (first step in sequence).
     * @return list of all tags.
     */
    public List<Tag> getAllTags() {
        return controller.getAllTags();
    }

    /**
     * Deletes selected tags via the controller (second step in sequence).
     * @param tagIds list of tag IDs to delete.
     */
    public void deleteTags(List<String> tagIds) {
        controller.deleteTags(tagIds);
    }
}