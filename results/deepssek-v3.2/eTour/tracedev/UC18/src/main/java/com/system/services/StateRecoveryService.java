package com.system.serv;

/**
 * Service to recover previous state in case of operation rejection.
 */
public class StateRecoveryService {
    public void recoverPreviousState() {
        System.out.println("Previous state recovered.");
        // In a real system, would rollback any transient changes.
    }
}