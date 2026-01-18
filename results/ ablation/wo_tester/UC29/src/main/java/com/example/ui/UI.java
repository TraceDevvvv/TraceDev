package com.example.ui;

/**
 * UI class representing the UI participant in sequence diagram.
 * This class provides a more structured interface for UI operations.
 */
public class UI {
    
    /**
     * Method corresponding to sequence diagram message m1: "Enters duplicate search tag"
     */
    public void entersDuplicateSearchTag(String tag) {
        System.out.println("UI: User enters duplicate search tag: " + tag);
        // This would typically call SearchUI.enterSearchTag
    }
    
    /**
     * Method corresponding to sequence diagram note m3: "System displaying error message"
     */
    public void systemDisplayingErrorMessage(String message) {
        System.out.println("UI: System displaying error message: " + message);
    }
    
    /**
     * Method corresponding to sequence diagram message m7: "Restores previous UI state"
     */
    public void restoresPreviousUIState() {
        System.out.println("UI: Restores previous UI state");
    }
    
    /**
     * Method corresponding to sequence diagram message m11: "keeps error displayed"
     */
    public void keepsErrorDisplayed() {
        System.out.println("UI: keeps error displayed");
    }
    
    /**
     * Method corresponding to sequence diagram message m12: "prompts to enter new tag"
     */
    public void promptsToEnterNewTag() {
        System.out.println("UI: prompts to enter new tag");
    }
}