package com.touristagency;

import com.touristagency.repository.TouristRepository;
import com.touristagency.service.TouristAccountService;
import com.touristagency.ui.ConsoleUI;

/**
 * Main class to run the Tourist Account Management system.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize components
        TouristRepository touristRepository = new TouristRepository();
        TouristAccountService accountService = new TouristAccountService(touristRepository);
        ConsoleUI consoleUI = new ConsoleUI(accountService);

        // Start the console interface
        consoleUI.start();
    }
}