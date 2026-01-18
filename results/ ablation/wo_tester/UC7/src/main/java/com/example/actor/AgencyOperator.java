package com.example.actor;

import com.example.controller.ConventionActivationController;
import com.example.dto.ActivationResultDTO;

/**
 * Represents the Agency Operator actor.
 */
public class AgencyOperator {
    private String name;
    private String id;

    public AgencyOperator(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    /**
     * Initiates the convention activation process.
     */
    public void processConventionActivation(ConventionActivationController controller, String conventionId) {
        System.out.println("Agency Operator " + name + " initiating activation for convention " + conventionId);
        ActivationResultDTO result = controller.processActivation(conventionId);
        System.out.println("Result: " + result.message);
    }

    /**
     * Confirms the activation after reviewing the form.
     */
    public void confirmActivation(ConventionActivationController controller, String conventionId) {
        System.out.println("Agency Operator " + name + " confirming activation for convention " + conventionId);
        controller.confirmActivation(conventionId);
    }
}