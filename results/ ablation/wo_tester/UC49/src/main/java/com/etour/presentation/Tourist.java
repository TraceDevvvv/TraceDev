package com.etour.presentation;

/**
 * Represents the Tourist actor.
 */
public class Tourist {
    private String id;
    private String name;
    private String email;

    public Tourist(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // This method corresponds to sequence diagram message m1: Tourst -> UI "Select 'View Favorites'"
    public void selectViewFavorites() {
        // In a real UI, this would trigger the UI component.
        // For simulation, we assume the UI component is informed.
    }
}