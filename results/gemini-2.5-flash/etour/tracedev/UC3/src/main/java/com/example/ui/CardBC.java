package com.example.ui;

import com.example.model.BeanCulturalHeritage;
import com.example.service.ICulturalHeritageAgencyManager;
import com.example.exception.RemoteException;
import com.example.util.ErrorMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JInternalFrame for displaying and editing details of a single BeanCulturalHeritage object.
 * This class serves as the UI for modifying cultural heritage data.
 */
public class CardBC extends JInternalFrame implements ActionListener {
    private BeanCulturalHeritage beanCulturalHeritage;
    private ICulturalHeritageAgencyManager manager;
    private CulturalHeritage parentFrame;

    // UI Components
    private JTextField idField;
    private JTextField nameField;
    private JTextField cityField;
    private JTextField phoneField;
    private JTextArea descriptionField;
    private JTextField streetField;
    private JTextField capField;
    private JTextField provinceField;
    private JDatePicker openingTimePicker; // Mocked JDatePicker
    private JDatePicker closingTimePicker; // Mocked JDatePicker
    private JTextField closingDayField;
    private JTextField locationField;

    private JButton saveButton;
    private JButton cancelButton;

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    /**
     * Constructor for CardBC.
     * @param pBC The BeanCulturalHeritage object to display/edit. Can be null for a new entry (not this use case).
     * @param manager The RMI manager for cultural heritage operations.
     * @param parentFrame The parent CulturalHeritage internal frame, used for refreshing the table.
     */
    public CardBC(BeanCulturalHeritage pBC, ICulturalHeritageAgencyManager manager, CulturalHeritage parentFrame) {
        super("Cultural Heritage Details", true, true, true, true); // resizable, closable, maximizable, iconifiable
        this.beanCulturalHeritage = pBC;
        this.manager = manager;
        this.parentFrame = parentFrame;
        initUI();
        if (pBC != null) {
            populateForm(pBC);
        }
        setSize(500, 600);
    }

    /**
     * Initializes the user interface components.
     */
    private void initUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // ID (read-only)
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0;
        idField = new JTextField(20);
        idField.setEditable(false);
        panel.add(idField, gbc);

        // Name
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);

        // City
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("City:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0;
        cityField = new JTextField(20);
        panel.add(cityField, gbc);

        // Phone
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0;
        phoneField = new JTextField(20);
        panel.add(phoneField, gbc);

        // Description
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0;
        descriptionField = new JTextArea(3, 20);
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionField);
        panel.add(descriptionScrollPane, gbc);

        // Street
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Street:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0;
        streetField = new JTextField(20);
        panel.add(streetField, gbc);

        // CAP
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("CAP:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0;
        capField = new JTextField(20);
        panel.add(capField, gbc);

        // Province
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Province:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0;
        provinceField = new JTextField(20);
        panel.add(provinceField, gbc);

        // Opening Time (using mock JDatePicker)
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Opening Time (HH:mm):"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0;
        openingTimePicker = new JDatePicker();
        panel.add(openingTimePicker, gbc);

        // Closing Time (using mock JDatePicker)
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Closing Time (HH:mm):"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0;
        closingTimePicker = new JDatePicker();
        panel.add(closingTimePicker, gbc);

        // Closing Day
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Closing Day:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0;
        closingDayField = new JTextField(20);
        panel.add(closingDayField, gbc);

        // Location
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1; gbc.gridy = row++; gbc.weightx = 1.0;
        locationField = new JTextField(20);
        panel.add(locationField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        add(panel);
    }

    /**
     * Populates the form fields with data from a BeanCulturalHeritage object.
     * @param pBC The BeanCulturalHeritage object whose data will fill the form.
     */
    public void populateForm(BeanCulturalHeritage pBC) {
        if (pBC != null) {
            idField.setText(String.valueOf(pBC.getId()));
            nameField.setText(pBC.getName());
            cityField.setText(pBC.getCity());
            phoneField.setText(pBC.getPhone());
            descriptionField.setText(pBC.getDescription());
            streetField.setText(pBC.getStreet());
            capField.setText(pBC.getCap());
            provinceField.setText(pBC.getProvince());
            openingTimePicker.setDate(pBC.getOpeningTime()); // JDatePicker handles Date
            closingTimePicker.setDate(pBC.getClosingTime()); // JDatePicker handles Date
            closingDayField.setText(pBC.getClosingDay());
            locationField.setText(pBC.getLocation());
        }
    }

    /**
     * Collects data from the form fields into a new or existing BeanCulturalHeritage object.
     * @return A BeanCulturalHeritage object populated with current form data.
     */
    public BeanCulturalHeritage collectFormData() {
        // If updating an existing one, start with its ID, otherwise a new ID would be generated.
        // For this use case, we are always modifying an existing one.
        BeanCulturalHeritage updatedBC = new BeanCulturalHeritage();
        updatedBC.setId(Integer.parseInt(idField.getText())); // ID is read-only, so it's always set

        updatedBC.setName(nameField.getText());
        updatedBC.setCity(cityField.getText());
        updatedBC.setPhone(phoneField.getText());
        updatedBC.setDescription(descriptionField.getText());
        updatedBC.setStreet(streetField.getText());
        updatedBC.setCap(capField.getText());
        updatedBC.setProvince(provinceField.getText());

        // Get Dates from JDatePickers
        updatedBC.setOpeningTime(openingTimePicker.getDate());
        updatedBC.setClosingTime(closingTimePicker.getDate());

        updatedBC.setClosingDay(closingDayField.getText());
        updatedBC.setLocation(locationField.getText());

        return updatedBC;
    }

    /**
     * Handles action events from buttons.
     * Implements the logic for saving or cancelling changes.
     * @param e The ActionEvent generated by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            handleSaveAction();
        } else if (e.getSource() == cancelButton) {
            closeCard();
        }
    }

    /**
     * Handles the save action, including data collection, confirmation,
     * interaction with the manager, and error handling.
     */
    private void handleSaveAction() {
        disableInputControls(); // Quality Requirement: Block input controls

        BeanCulturalHeritage updatedBean = collectFormData();

        // 9. System asks for confirmation of the transaction.
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to save these changes?",
                "Confirm Changes",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // 12. System stores the modified data of the cultural good.
                boolean success = manager.modifyCulturalHeritage(updatedBean);

                if (success) {
                    JOptionPane.showMessageDialog(this,
                            "Cultural Heritage data updated successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    parentFrame.updateTableModel(); // Refresh the parent table
                    closeCard();
                } else {
                    // This path might be taken if modifyCulturalHeritage returns false but doesn't throw specific RemoteException
                    JOptionPane.showMessageDialog(this,
                            ErrorMessage.ERROR_MODIFY_FAILED,
                            "Modification Failed",
                            JOptionPane.ERROR_MESSAGE);
                    enableInputControls(); // Re-enable if modification failed without closing card
                }
            } catch (RemoteException ex) {
                // Catches validation errors (from Checker) or DB errors (from DB/Manager)
                JOptionPane.showMessageDialog(this,
                        "Error saving data: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                enableInputControls(); // Re-enable input controls if validation or DB error occurred
            } catch (java.rmi.RemoteException ex) {
                // Catches RMI transport errors
                JOptionPane.showMessageDialog(this,
                        ErrorMessage.ERROR_RMI_CONNECTION + ": " + ex.getMessage(),
                        "Connection Error",
                        JOptionPane.ERROR_MESSAGE);
                enableInputControls(); // Re-enable input controls on RMI connection error
            } catch (Exception ex) {
                // Catch any other unexpected exceptions
                System.err.println("Unexpected error during save: " + ex.getMessage());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        ErrorMessage.ERROR_UNKNOWN + ": " + ex.getMessage(),
                        "Unknown Error",
                        JOptionPane.ERROR_MESSAGE);
                enableInputControls(); // Re-enable input controls on unexpected error
            }
        } else {
            // Agency Operator cancels the operation
            enableInputControls(); // Re-enable input controls
            // closeCard(); // Depending on UX, might close or stay open
        }
    }

    /**
     * Disables all input controls on the form.
     * Quality Requirement: Block input controls during processing.
     */
    public void disableInputControls() {
        nameField.setEnabled(false);
        cityField.setEnabled(false);
        phoneField.setEnabled(false);
        descriptionField.setEnabled(false);
        streetField.setEnabled(false);
        capField.setEnabled(false);
        provinceField.setEnabled(false);
        openingTimePicker.setEnabled(false);
        closingTimePicker.setEnabled(false);
        closingDayField.setEnabled(false);
        locationField.setEnabled(false);
        saveButton.setEnabled(false);
        cancelButton.setEnabled(false); // Can be kept enabled if cancellation is always an option
    }

    /**
     * Enables all input controls on the form.
     * Quality Requirement: Re-enable input controls after processing or error.
     */
    public void enableInputControls() {
        nameField.setEnabled(true);
        cityField.setEnabled(true);
        phoneField.setEnabled(true);
        descriptionField.setEnabled(true);
        streetField.setEnabled(true);
        capField.setEnabled(true);
        provinceField.setEnabled(true);
        openingTimePicker.setEnabled(true);
        closingTimePicker.setEnabled(true);
        closingDayField.setEnabled(true);
        locationField.setEnabled(true);
        saveButton.setEnabled(true);
        cancelButton.setEnabled(true);
    }

    /**
     * Closes and disposes the internal frame.
     */
    public void closeCard() {
        this.dispose();
    }
}