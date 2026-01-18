package etour.interfaceadapters;

import etour.domain.SearchPreferences;

/**
 * View representing the Preferences form.
 * Traces to: Flow of Events step 3 (displayForm)
 */
public class PreferencesForm {
    private PreferencesViewModel viewModel;

    public PreferencesForm(PreferencesViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Called when the form needs to display preferences.
     * @param preferences the preferences to show
     */
    public void displayForm(SearchPreferences preferences) {
        System.out.println("Form: displaying preferences: " + preferences);
    }

    /**
     * Called when a field is edited by the tourist.
     * @param key field name
     * @param value new value
     */
    public void onFieldEdited(String key, Object value) {
        System.out.println("Form: field edited: " + key + " -> " + value);
        viewModel.updateField(key, value);
    }

    /**
     * Called when the tourist submits the form.
     */
    public void onSubmit() {
        System.out.println("Form: submitting changes...");
        try {
            viewModel.submitChanges();
            showSuccess("Preferences updated");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    /**
     * Show a confirmation dialog (simulated).
     * @param message confirmation prompt
     * @return true if confirmed, false otherwise
     */
    public boolean showConfirmation(String message) {
        System.out.println("Form: show confirmation: " + message);
        // In reality this would block until the user answers.
        // For simulation, we assume 'yes'.
        return true;
    }

    /**
     * Show a success message.
     * @param message success text
     */
    public void showSuccess(String message) {
        System.out.println("Form SUCCESS: " + message);
    }

    /**
     * Show an error message.
     * @param message error text
     */
    public void showError(String message) {
        System.out.println("Form ERROR: " + message);
    }

    /**
     * Simulate authentication check.
     * @return true if authenticated
     */
    public boolean isAuthenticated() {
        // Simplified: always true for demonstration.
        return true;
    }

    /**
     * Show a cancellation message.
     * @param message cancellation text
     */
    public void showCancelledMessage(String message) {
        System.out.println("Form CANCELLED: " + message);
    }

    /**
     * Called when tourist cancels the operation.
     */
    public void cancel() {
        viewModel.cancel();
        showCancelledMessage("Operation cancelled");
    }

    /**
     * Authentication check - corresponds to sequence diagram message m4
     * @return true if authentication passes
     */
    public boolean authenticationCheck() {
        System.out.println("Form: performing authentication check");
        boolean result = isAuthenticated();
        if (result) {
            System.out.println("Form: authentication check (true)");
        } else {
            System.out.println("Form: authentication check (false)");
        }
        return result;
    }

    /**
     * Receives edits field(key, value) message - corresponds to sequence diagram message m24
     * @param key the field name
     * @param value the new value
     */
    public void receiveEditsField(String key, String value) {
        System.out.println("Form received edits field: " + key + " = " + value);
    }

    /**
     * Receives submits form() message - corresponds to sequence diagram message m29
     */
    public void receiveSubmitsForm() {
        System.out.println("Form received submits form()");
    }
}