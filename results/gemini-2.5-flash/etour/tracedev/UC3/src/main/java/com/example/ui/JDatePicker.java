package com.example.ui;

import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Mock JDatePicker component.
 * In a real application, this would be a third-party library component
 * or a custom implementation providing a date picker functionality.
 * For this example, it's a JTextField with basic Date parsing/formatting.
 */
public class JDatePicker extends JPanel {
    private JTextField dateField;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // Example format

    public JDatePicker() {
        dateField = new JTextField(15);
        this.add(dateField);
    }

    /**
     * Sets the date displayed in the picker.
     * @param date The Date object to set.
     */
    public void setDate(Date date) {
        if (date != null) {
            dateField.setText(dateFormat.format(date));
        } else {
            dateField.setText("");
        }
    }

    /**
     * Gets the date currently selected/displayed in the picker.
     * @return The Date object, or null if the text field is empty or contains an invalid date.
     */
    public Date getDate() {
        String dateText = dateField.getText();
        if (dateText.isEmpty()) {
            return null;
        }
        try {
            return dateFormat.parse(dateText);
        } catch (ParseException e) {
            System.err.println("Invalid date format in JDatePicker: " + dateText);
            return null;
        }
    }

    /**
     * Enables or disables the date picker for user interaction.
     * @param enabled true to enable, false to disable.
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        dateField.setEnabled(enabled);
    }
}