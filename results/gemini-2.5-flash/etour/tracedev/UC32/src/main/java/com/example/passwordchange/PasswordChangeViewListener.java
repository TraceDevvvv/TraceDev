package com.example.passwordchange;

/**
 * Interface for listeners interested in events from the PasswordChangeView.
 * This allows the View to communicate user interactions back to its controller.
 */
public interface PasswordChangeViewListener {
    /**
     * Called when the user acknowledges an error message in the view.
     */
    void onAcknowledgeError();
}