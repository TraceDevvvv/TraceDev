package com.system.ui;

import com.system.dtos.TagDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * UI form for tag deletion.
 */
public class TagDeleteForm {
    private List<TagDTO> displayedTags;
    private List<String> selectedTagIds;

    public TagDeleteForm() {
        displayedTags = new ArrayList<>();
        selectedTagIds = new ArrayList<>();
    }

    public void displayTags(List<TagDTO> tags) {
        this.displayedTags = tags;
        // In a real UI, this would update the form's view.
        System.out.println("Displaying tags in form: " + tags.size() + " tags.");
    }

    public void selectTags(List<String> tagIds) {
        this.selectedTagIds = tagIds;
        System.out.println("Tags selected: " + tagIds);
    }

    public List<String> getSelectedTagIds() {
        return selectedTagIds;
    }

    public void showSuccessMessage(List<String> tagIds, String message) {
        System.out.println("SUCCESS: " + message + " Deleted tag IDs: " + tagIds);
    }

    public void showErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }
}