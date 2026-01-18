package com.example.infrastructure;

/**
 * Represents the current status of system functionalities.
 * Added to satisfy requirement R3 for entry condition check.
 */
public class SystemStatus {
    public boolean searchFunctionalityAvailable;

    public SystemStatus() {
        // Assume search functionality is available by default, or set based on configuration.
        this.searchFunctionalityAvailable = true;
    }

    public boolean isSearchFunctionalityAvailable() {
        return searchFunctionalityAvailable;
    }

    public void setSearchFunctionalityAvailable(boolean searchFunctionalityAvailable) {
        this.searchFunctionalityAvailable = searchFunctionalityAvailable;
    }
}