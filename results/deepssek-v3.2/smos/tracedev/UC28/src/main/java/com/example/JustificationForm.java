package com.example;

import java.util.Date;

/**
 * Form for managing justifications.
 * Traceability: System displays a form for managing justifications.
 */
public class JustificationForm {
    private String studentId;
    private Date recordDate;

    public JustificationForm(String studentId, Date recordDate) {
        this.studentId = studentId;
        this.recordDate = recordDate;
    }

    public void displayForm() {
        // Display the form UI.
    }

    public void submitJustification(String reason) {
        // Submit justification logic.
    }
}