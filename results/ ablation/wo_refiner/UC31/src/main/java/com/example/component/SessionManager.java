package com.example.component;

/**
 * Manages session validation for agency operators.
 * Traceability: Satisfies R4 (Validates agency operator is logged in)
 */
public class SessionManager {
    public boolean verifySession(String agencyId) {
        // In a real application, this would check session store
        // For this example, we assume session is valid if agencyId is not null
        return agencyId != null && !agencyId.trim().isEmpty();
    }

    public boolean sessionValid() {
        // This method returns a boolean as per the sequence diagram message m2: "sessionValid : boolean"
        // In the context of sequence diagram, the session validation is performed for a specific agency operator.
        // But the method "sessionValid" without parameters is present in the diagram.
        // We assume it validates the current session.
        // Since we don't have a current session context, we'll return true for simulation.
        return true;
    }
}