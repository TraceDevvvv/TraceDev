package com;

import com.system.*;

/**
 * Main class to run the system and simulate the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize components
        SystemStateStore stateStore = new SystemStateStore();
        StateManager stateManager = new StateManager(stateStore);
        ErrorHandler errorHandler = new ErrorHandler(stateManager);
        UserInterface ui = new UserInterface(errorHandler, stateManager);

        // Simulate user interaction
        ui.simulateUserInteraction();
    }
}