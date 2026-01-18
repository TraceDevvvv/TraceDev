package com.example.ui;

import com.example.model.Convention;

/**
 * Responsible for displaying the convention form.
 */
public class FormDisplay {
    public void displayConventionForm(Convention convention) {
        System.out.println("=== Convention Form ===");
        System.out.println("Convention ID: " + convention.getId());
        System.out.println("Status: " + convention.getStatus());
        if (convention.getDataRequest() != null) {
            System.out.println("Data Request ID: " + convention.getDataRequest().getId());
            System.out.println("Data Content: " + convention.getDataRequest().getContent());
        }
        if (convention.getRefreshmentPoint() != null) {
            System.out.println("Refreshment Point: " + convention.getRefreshmentPoint().getName());
            System.out.println("Point Status: " + convention.getRefreshmentPoint().getStatus());
        }
        System.out.println("=====================");
    }
}