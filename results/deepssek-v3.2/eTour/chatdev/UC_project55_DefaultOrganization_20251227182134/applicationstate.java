// Tracks and manages the application state for proper state recovery.
// Contains methods to save and restore the application state.
package feedbacksystem;
import java.io.Serializable;
/**
 * Tracks and manages the application state for proper state recovery.
 * Contains methods to save and restore the application state.
 */
public class ApplicationState implements Serializable {
    private String currentScreen;
    private String currentSite;
    private String currentUser;
    private Object formData;
    private long timestamp;
    /**
     * Constructor for creating a new application state.
     * 
     * @param currentScreen the current screen name
     * @param currentSite the current site being viewed
     * @param currentUser the current user ID
     * @param formData any form data that should be preserved
     */
    public ApplicationState(String currentScreen, String currentSite, 
                           String currentUser, Object formData) {
        this.currentScreen = currentScreen;
        this.currentSite = currentSite;
        this.currentUser = currentUser;
        this.formData = formData;
        this.timestamp = System.currentTimeMillis();
    }
    public String getCurrentScreen() {
        return currentScreen;
    }
    public String getCurrentSite() {
        return currentSite;
    }
    public String getCurrentUser() {
        return currentUser;
    }
    public Object getFormData() {
        return formData;
    }
    public long getTimestamp() {
        return timestamp;
    }
    @Override
    public String toString() {
        return String.format("ApplicationState[screen=%s, site=%s, user=%s, time=%d]", 
            currentScreen, currentSite, currentUser, timestamp);
    }
}