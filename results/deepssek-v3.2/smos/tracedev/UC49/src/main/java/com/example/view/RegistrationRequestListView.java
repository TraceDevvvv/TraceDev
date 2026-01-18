package com.example.view;

import com.example.dto.RegistrationRequestDTO;
import java.util.List;

/**
 * View component responsible for rendering registration request lists.
 */
public class RegistrationRequestListView {
    public void renderRequestList(List<RegistrationRequestDTO> registrationRequestDTOs) {
        if (registrationRequestDTOs == null || registrationRequestDTOs.isEmpty()) {
            System.out.println("No pending registration requests found.");
            return;
        }

        System.out.println("\n=== Pending Registration Requests ===");
        System.out.println("Total requests: " + registrationRequestDTOs.size());
        System.out.println("--------------------------------------");
        
        int index = 1;
        for (RegistrationRequestDTO dto : registrationRequestDTOs) {
            System.out.println(index + ". Student ID: " + dto.getStudentId());
            System.out.println("   Name: " + dto.getStudentName());
            System.out.println("   Date: " + dto.getRequestDate());
            System.out.println("   Status: " + dto.getStatus());
            System.out.println("--------------------------------------");
            index++;
        }
    }

    public void clearDisplay() {
        System.out.println("\nDisplay cleared due to connection interruption.");
    }
}