
package com.example;

import java.util.List;

/**
 * Controller for the registration list view.
 */
public class RegistrationListViewController {
    private RegistrationService registrationService;
    private RegistrationListView registrationListView;

    public RegistrationListViewController(RegistrationService registrationService, RegistrationListView registrationListView) {
        this.registrationService = registrationService;
        this.registrationListView = registrationListView;
    }

    /**
     * Refreshes the view by fetching pending registrations and displaying them.
     */
    public void refreshView() {
        List<Registration> registrations = registrationService.getPendingRegistrations();
        registrationListView.displayRegistrations(registrations);
    }
}
