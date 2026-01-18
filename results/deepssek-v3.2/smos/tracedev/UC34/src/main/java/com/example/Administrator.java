package com.example;

import com.example.controller.JustificationController;
import com.example.dto.JustificationViewDTO;
import com.example.manager.SMOSConnectionManager;
import com.example.ui.JustificationUI;

/**
 * Administrator actor that uses the system.
 */
public class Administrator {
    private JustificationUI justificationUI;
    private SMOSConnectionManager smosConnectionManager;

    /**
     * Constructor for Administrator.
     * @param justificationUI the UI for justifications
     * @param smosConnectionManager the manager for SMOS connection
     */
    public Administrator(JustificationUI justificationUI, SMOSConnectionManager smosConnectionManager) {
        this.justificationUI = justificationUI;
        this.smosConnectionManager = smosConnectionManager;
    }

    /**
     * Simulates clicking the "Justification" button for a student.
     * This triggers the whole flow described in the sequence diagram.
     * @param controller the justification controller
     * @param studentId the student identifier
     * @param userId the user identifier (administrator)
     * @param interrupt whether to simulate a connection interruption
     */
    public void clickJustificationButton(JustificationController controller, String studentId, String userId, boolean interrupt) {
        System.out.println("Administrator clicks 'Justification' button for student: " + studentId);

        // Simulate the interruptible region: if interrupt flag is true, break the flow
        if (interrupt) {
            System.out.println("Administrator interrupts connection to SMOS server.");
            smosConnectionManager.interruptConnection();
            smosConnectionManager.returnConnectionInterrupted();
            return;
        }

        // Normal flow: request justification view.
        // Assuming that if handleJustificationViewRequest returns void,
        // it means the controller itself handles the display.
        controller.handleJustificationViewRequest(studentId);
    }

    /**
     * Method corresponding to sequence message m1: Verify admin credentials.
     */
    public void verifyAdminCredentials() {
        System.out.println("Administrator verifies admin credentials");
    }

    /**
     * Method corresponding to sequence message m4: Clicks "Justification" button for a Student.
     * @param controller the justification controller
     * @param studentId the student identifier
     */
    public void clicksJustificationButtonForAStudent(JustificationController controller, String studentId) {
        // In a real scenario, this would trigger the controller method with appropriate userId
        verifyAdminCredentials();
        controller.handleJustificationViewRequest(studentId);
    }
}
