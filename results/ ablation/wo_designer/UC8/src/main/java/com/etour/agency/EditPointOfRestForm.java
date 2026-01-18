// Simulated GUI form for editing a Point of Rest
package com.etour.agency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class EditPointOfRestForm extends JFrame {
    private PointOfRest point;
    private PointOfRestDAO dao;
    private AgencyOperator operator;
    private boolean submissionBlocked = false;

    // Form components
    private JTextField nameField;
    private JTextField locationField;
    private JTextArea descriptionArea;
    private JTextField capacityField;
    private JCheckBox activeCheckBox;
    private JButton submitButton;
    private JButton cancelButton;

    public EditPointOfRestForm(PointOfRest point, PointOfRestDAO dao, AgencyOperator operator) {
        this.point = point;
        this.dao = dao;
        this.operator = operator;
        initializeUI();
        loadPointData();
    }

    private void initializeUI() {
        setTitle("Edit Point of Rest - " + point.getName());
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Location:"));
        locationField = new JTextField();
        formPanel.add(locationField);

        formPanel.add(new JLabel("Description:"));
        descriptionArea = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        formPanel.add(scrollPane);

        formPanel.add(new JLabel("Capacity:"));
        capacityField = new JTextField();
        formPanel.add(capacityField);

        formPanel.add(new JLabel("Active:"));
        activeCheckBox = new JCheckBox();
        formPanel.add(activeCheckBox);

        add(formPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit Changes");
        cancelButton = new JButton("Cancel");

        submitButton.addActionListener(new SubmitAction());
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadPointData() {
        nameField.setText(point.getName());
        locationField.setText(point.getLocation());
        descriptionArea.setText(point.getDescription());
        capacityField.setText(String.valueOf(point.getCapacity()));
        activeCheckBox.setSelected(point.isActive());
    }

    private class SubmitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (submissionBlocked) {
                JOptionPane.showMessageDialog(EditPointOfRestForm.this,
                        "Submission already in progress. Please wait.",
                        "Blocked", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Step 8: System verifies the data entered in the form.
            if (!validateForm()) {
                return;
            }

            // Step 9: System asks for confirmation of the transaction.
            int confirm = JOptionPane.showConfirmDialog(EditPointOfRestForm.this,
                    "Are you sure you want to update this Point of Rest?",
                    "Confirm Update", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Step 10: Agency Operator confirms the operation.
            // Step 11: System stores the modified data of the point of rest.
            // Quality Requirement: Block input controls to avoid multiple submissions
            blockFormControls(true);

            // Simulate data update
            PointOfRest updated = new PointOfRest(
                    point.getId(),
                    nameField.getText().trim(),
                    locationField.getText().trim(),
                    descriptionArea.getText().trim(),
                    Integer.parseInt(capacityField.getText().trim()),
                    activeCheckBox.isSelected()
            );

            boolean success = dao.updatePoint(updated);
            if (success) {
                JOptionPane.showMessageDialog(EditPointOfRestForm.this,
                        "Point of Rest updated successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                point = updated;
            } else {
                JOptionPane.showMessageDialog(EditPointOfRestForm.this,
                        "Update failed. Point may have been deleted.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Re-enable form after operation
            blockFormControls(false);
        }
    }

    private boolean validateForm() {
        String name = nameField.getText().trim();
        String location = locationField.getText().trim();
        String capacityText = capacityField.getText().trim();

        if (name.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Name and Location are required.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int capacity = Integer.parseInt(capacityText);
            if (capacity <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Capacity must be a positive integer.",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Capacity must be a valid integer.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void blockFormControls(boolean block) {
        submissionBlocked = block;
        nameField.setEnabled(!block);
        locationField.setEnabled(!block);
        descriptionArea.setEnabled(!block);
        capacityField.setEnabled(!block);
        activeCheckBox.setEnabled(!block);
        submitButton.setEnabled(!block);
        cancelButton.setEnabled(!block);
    }
}