package com.example.service;

import com.example.model.GenericPreference;
import com.example.repository.PreferenceRepository;
import java.util.Map;
import java.util.HashMap;

/**
 * Implementation of PreferenceCommandService.
 */
public class PreferenceCommandServiceImpl implements PreferenceCommandService {
    private PreferenceRepository preferenceRepository;
    private ETOURConnection connection;
    private QualityMonitor qualityMonitor;
    private int timeout;
    private NotificationService notificationService;

    public PreferenceCommandServiceImpl(PreferenceRepository preferenceRepository, ETOURConnection connection,
                                        QualityMonitor qualityMonitor, NotificationService notificationService) {
        this.preferenceRepository = preferenceRepository;
        this.connection = connection;
        this.qualityMonitor = qualityMonitor;
        this.timeout = 30; // default timeout in seconds
        this.notificationService = notificationService;
    }

    @Override
    public boolean modifyPreferences(GenericPreference preference) {
        // This method is called via confirmModification in the sequence diagram.
        // For simplicity, we just call confirmModification with a generated ID.
        confirmModification("confirm_" + preference.getPreferenceId());
        return true;
    }

    @Override
    public void confirmModification(String confirmationId) {
        System.out.println("PreferenceCommandServiceImpl: Confirm modification with ID " + confirmationId);
        // In real flow, we would retrieve the pending preference using confirmationId.
        // For demonstration, create a mock preference.
        GenericPreference mockPreference = new GenericPreference("pref123", "tourist123", "French", "Light", "Disabled");
        if (validatePreferences(mockPreference)) {
            // Check connection as per sequence message m28.
            if (!connection.checkConnection()) {
                // Sequence m34: notifier returns Connection error notification
                notificationService.sendErrorNotification(mockPreference.getTouristId(), "Connection lost during update");
                return;
            }
            Map<String, String> details = new HashMap<>();
            details.put("confirmationId", confirmationId);
            qualityMonitor.logOperation("update_preferences", details);
            // Sequence m38: repository returns updated preference
            GenericPreference updated = preferenceRepository.update(mockPreference);
            qualityMonitor.monitorPerformance("update_operation");
            // Sequence m41: notifier returns Success notification
            notificationService.sendSuccessNotification(mockPreference.getTouristId(), "Preferences updated");
        } else {
            // Sequence m45: notifier returns Error notification
            notificationService.sendErrorNotification(mockPreference.getTouristId(), "Invalid preference data");
        }
    }

    @Override
    public void cancelModification(String operationId) {
        Map<String, String> details = new HashMap<>();
        details.put("operationId", operationId);
        qualityMonitor.logOperation("cancel_operation", details);
        System.out.println("PreferenceCommandServiceImpl: Cancelled modification " + operationId);
        // Sequence m50: commandService returns cancellation confirmed.
    }

    /**
     * Validates preference data.
     */
    private boolean validatePreferences(GenericPreference preference) {
        return preference != null &&
               preference.getLanguage() != null &&
               preference.getTheme() != null;
    }

    // Additional method to handle connection status check as per sequence diagram.
    public String checkConnectionStatus() {
        boolean connected = connection.checkConnection();
        return connected ? "CONNECTED" : "DISCONNECTED";
    }
}