package com.example;

import com.example.service.RegistrationService;
import com.example.view.AdminDashboard;

/**
 * Actor representing an administrator.
 * Note: In UML, actor is not a class but for simulation we include it.
 */
public class Administrator {
    private String userId;
    private String name;

    public Administrator(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    /**
     * Rejects a registration request.
     * @param requestId the ID of the request to reject
     */
    public void rejectRegistration(String requestId) {
        // This method would typically interact with the dashboard or controller.
        // For simulation, we assume the administrator uses the dashboard.
    }

    /**
     * Simulates clicking the reject button on the dashboard.
     * @param dashboard the admin dashboard
     * @param requestId the request ID
     */
    public void clickRejectButton(AdminDashboard dashboard, String requestId) {
        // In reality, the dashboard would call the controller.
        // This is a simulation method.
        System.out.println("Administrator " + name + " clicks reject button for request " + requestId);
    }

    /**
     * Interrupts the connection to the SMOS server.
     * @param service the registration service
     */
    public void interruptConnection(RegistrationService service) {
        System.out.println("Administrator " + name + " interrupts connection.");
        service.handleConnectionInterrupt();
    }
}