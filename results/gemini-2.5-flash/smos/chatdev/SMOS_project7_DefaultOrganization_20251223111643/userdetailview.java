'''
This file defines the UserDetailView class, which is a JDialog window
used to display the detailed information of a selected user.
It presents the user's Name, Surname, E-mail, Cell, Login, and Password in a readable format.
Upon closing, it simulates the "Connection to the SMOS server interrupted" postcondition.
'''
package com.chatdev.adminapp.view;
import com.chatdev.adminapp.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class UserDetailView extends JDialog {
    /**
     * Constructs a new UserDetailView dialog.
     *
     * @param parent The parent Frame of this dialog (e.g., the UserListView).
     * @param user The User object whose details are to be displayed.
     */
    public UserDetailView(Frame parent, User user) {
        super(parent, "User Details: " + user.getLogin(), true); // Modal dialog
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 300); // Set a fixed size for the details window
        setLocationRelativeTo(parent); // Center the dialog relative to the parent frame
        // Use a JPanel with a GridBagLayout for flexible and neat arrangement of labels
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components fill horizontally
        // Helper method to add a label-value pair to the panel
        addDetailRow(panel, gbc, "Name:", user.getName(), 0);
        addDetailRow(panel, gbc, "Surname:", user.getSurname(), 1);
        addDetailRow(panel, gbc, "E-mail:", user.getEmail(), 2);
        addDetailRow(panel, gbc, "Cell:", user.getCell(), 3);
        addDetailRow(panel, gbc, "Login:", user.getLogin(), 4);
        // Warning: In a real app, never display plain password. For this simulation, we follow the requirement.
        addDetailRow(panel, gbc, "Password:", user.getPassword(), 5);
        // Add a close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose()); // Closes the dialog
        gbc.gridx = 0;
        gbc.gridy = 6; // Below the last detail row
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        gbc.insets = new Insets(15, 5, 5, 5); // More padding above the button
        panel.add(closeButton, gbc);
        add(panel, BorderLayout.CENTER);
        // Add a WindowListener to handle the postcondition when the dialog closes
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Postcondition: Connection to the SMOS server interrupted
                System.out.println("LOG: UserDetailView closed. Connection to the SMOS server interrupted.");
            }
        });
    }
    /**
     * Helper method to add a labeled detail row to the panel.
     *
     * @param panel The JPanel to which components are added.
     * @param gbc The GridBagConstraints for layout.
     * @param labelText The text for the label (e.g., "Name:").
     * @param valueText The actual value to display (e.g., "John Doe").
     * @param row The row index for positioning.
     */
    private void addDetailRow(JPanel panel, GridBagConstraints gbc, String labelText, String valueText, int row) {
        // Label for the detail name
        JLabel label = new JLabel(labelText);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3; // Take 30% of horizontal space
        gbc.anchor = GridBagConstraints.WEST; // Align label to the west
        panel.add(label, gbc);
        // Label for the detail value
        JLabel value = new JLabel(valueText);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 0.7; // Take 70% of horizontal space
        gbc.anchor = GridBagConstraints.WEST; // Align value to the west
        panel.add(value, gbc);
    }
}