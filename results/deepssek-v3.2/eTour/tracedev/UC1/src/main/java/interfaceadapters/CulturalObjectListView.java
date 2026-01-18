package interfaceadapters;

import enterprisebusinessrules.CulturalObject;
import java.util.List;

/**
 * View for displaying a list of cultural objects.
 */
public class CulturalObjectListView {
    /**
     * Displays the list of cultural objects.
     * @param items the list of cultural objects
     */
    public void displayList(List<CulturalObject> items) {
        // Simplified: just print the list.
        System.out.println("Displaying cultural objects list:");
        for (CulturalObject item : items) {
            System.out.println("- " + item.getId() + ": " + item.getName());
        }
    }

    /**
     * Gets the selected cultural object ID (simulated).
     * @return the selected ID
     */
    public String getSelectedId() {
        // Simplified: return a dummy ID.
        return "obj123";
    }

    /**
     * Shows a confirmation dialog for deletion.
     * @param objectId the cultural object ID
     * @return true if user confirms, false if cancels
     */
    public boolean showConfirmationDialog(String objectId) {
        // Simplified: print confirmation message and assume user confirms.
        System.out.println("Show confirmation dialog for deleting object: " + objectId);
        return true;
    }

    /**
     * Shows a notification message to the user.
     * @param message the notification message
     */
    public void showNotification(String message) {
        System.out.println("Notification: " + message);
    }

    /**
     * Shows object details (corresponds to sequence message: "Object highlighted / details shown").
     * @param object the cultural object
     */
    public void showObjectDetails(CulturalObject object) {
        System.out.println("Object details:");
        System.out.println("ID: " + object.getId());
        System.out.println("Name: " + object.getName());
        System.out.println("Description: " + object.getDescription());
    }
}