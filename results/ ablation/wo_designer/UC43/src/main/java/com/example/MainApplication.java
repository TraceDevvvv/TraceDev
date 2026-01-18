// Main class to run the application
package com.example;

import com.example.controller.RefreshmentPointController;

public class MainApplication {
    public static void main(String[] args) {
        System.out.println("Starting Restaurant Point Operator System...");
        RefreshmentPointController controller = new RefreshmentPointController();
        controller.executeUpdateProcess();
        System.out.println("System operation completed.");
    }
}