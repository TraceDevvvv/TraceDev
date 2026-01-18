package com.example;

import com.example.operator.RestaurantPointOperator;
import com.example.dto.RefreshmentDTO;

/**
 * Main class to run the application and demonstrate the sequence.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Restaurant Point Operator System...");

        // Create a restaurant point operator
        RestaurantPointOperator operator = new RestaurantPointOperator("OP001", "John Doe");

        // Simulate the sequence diagram flow
        System.out.println("\n=== Step 1: View refreshment point info ===");
        operator.viewRefreshmentPointInfo("RP123");

        System.out.println("\n=== Step 2: Modify form data ===");
        // Create modified DTO (simulating form changes)
        RefreshmentDTO modifiedDto = new RefreshmentDTO(null);
        modifiedDto.setId("RP123");
        modifiedDto.setName("Updated Refreshment");
        modifiedDto.setDescription("Updated description with new items");
        modifiedDto.setPrice(15.75);
        modifiedDto.setAvailableQuantity(150);
        operator.modifyRefreshmentData(modifiedDto);

        System.out.println("\n=== Step 3: Submit form (triggers validation, server check, and update) ===");
        operator.confirmOperation();

        // Alternative flow: cancel operation
        // System.out.println("\n=== Alternative: Cancel operation ===");
        // operator.cancelOperation();

        System.out.println("\nSequence completed.");
    }
}