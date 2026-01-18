package com.activeconvention.ui;

import com.activeconvention.model.Convention;
import com.activeconvention.model.ConventionRequest;
import com.activeconvention.util.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * ConventionView class for displaying the UI and handling user interactions.
 * This class is responsible for rendering the application's graphical user interface,
 * displaying convention requests, convention details, and handling user input.
 * It acts as the 'View' in the MVC pattern.
 */
public class ConventionView extends JPanel {

    private JTable conventionRequestTable;
    private DefaultTableModel tableModel;
    private JButton viewDetailsButton;
    private JButton activateConventionButton;
    private JButton cancelButton;
    private JCheckBox approveActivationCheckbox;
    private JTextArea conventionDetailsArea;
    private JLabel statusLabel;

    // Convention details fields
    private JTextField conventionIdField;
    private JTextField refreshmentPointField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextArea termsArea;

    private Convention selectedConvention; // To hold the currently displayed convention for activation

    /**
     * Constructor for ConventionView.
     * Initializes the UI components and lays them out.
     */
    public ConventionView() {
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(800, 600)); // Set preferred size for the window

        // Initialize components
        initComponents();
        // Lay out components
        layoutComponents();

        Logger.logInfo("ConventionView initialized.");
    }

    /**
     * Initializes all UI components.
     */
    private void initComponents() {
        // Table for convention requests
        tableModel = new DefaultTableModel(new Object[]{"Request ID", "Convention ID", "Refreshment Point", "Request Date", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        conventionRequestTable = new JTable(tableModel);
        conventionRequestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection

        // Buttons
        viewDetailsButton = new JButton("View Details");
        activateConventionButton = new JButton("Activate Convention");
        cancelButton = new JButton("Cancel");
        approveActivationCheckbox = new JCheckBox("I have reviewed the convention data and approve activation.");

        // Convention details display
        conventionDetailsArea = new JTextArea(10, 40);
        conventionDetailsArea.setEditable(false);
        conventionDetailsArea.setLineWrap(true);
        conventionDetailsArea.setWrapStyleWord(true);

        conventionIdField = new JTextField(20);
        conventionIdField.setEditable(false);
        refreshmentPointField = new JTextField(20);
        refreshmentPointField.setEditable(false);
        startDateField = new JTextField(20);
        startDateField.setEditable(false);
        endDateField = new JTextField(20);
        endDateField.setEditable(false);
        termsArea = new JTextArea(5, 20);
        termsArea.setEditable(false);
        termsArea.setLineWrap(true);
        termsArea.setWrapStyleWord(true);
        JScrollPane termsScrollPane = new JScrollPane(termsArea);


        // Status label
        statusLabel = new JLabel("Ready.");
        statusLabel.setForeground(Color.BLUE); // Default status color

        // Initially disable activation related components
        activateConventionButton.setEnabled(false);
        approveActivationCheckbox.setEnabled(false);
        viewDetailsButton.setEnabled(false); // Disable until a request is selected
    }

    /**
     * Lays out the UI components using BorderLayout and JPanel for grouping.
     */
    private void layoutComponents() {
        // Top Panel for Convention Request List
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder("Pending Convention Requests"));
        topPanel.add(new JScrollPane(conventionRequestTable), BorderLayout.CENTER);

        JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topButtonPanel.add(viewDetailsButton);
        topPanel.add(topButtonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel for Convention Details & Activation Form
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Convention Details & Activation"));

        JPanel detailsFormPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Convention ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsFormPanel.add(new JLabel("Convention ID:"), gbc);
        gbc.gridx = 1;
        detailsFormPanel.add(conventionIdField, gbc);

        // Refreshment Point
        gbc.gridx = 0;
        gbc.gridy = 1;
        detailsFormPanel.add(new JLabel("Refreshment Point:"), gbc);
        gbc.gridx = 1;
        detailsFormPanel.add(refreshmentPointField, gbc);

        // Start Date
        gbc.gridx = 0;
        gbc.gridy = 2;
        detailsFormPanel.add(new JLabel("Start Date:"), gbc);
        gbc.gridx = 1;
        detailsFormPanel.add(startDateField, gbc);

        // End Date
        gbc.gridx = 0;
        gbc.gridy = 3;
        detailsFormPanel.add(new JLabel("End Date:"), gbc);
        gbc.gridx = 1;
        detailsFormPanel.add(endDateField, gbc);

        // Terms
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        detailsFormPanel.add(new JLabel("Terms:"), gbc);
        gbc.gridx = 1;
        gbc.weighty = 1.0; // Allow terms area to expand vertically
        gbc.fill = GridBagConstraints.BOTH;
        detailsFormPanel.add(new JScrollPane(termsArea), gbc);

        centerPanel.add(detailsFormPanel, BorderLayout.CENTER);

        JPanel activationControlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        activationControlPanel.add(approveActivationCheckbox);
        activationControlPanel.add(activateConventionButton);
        activationControlPanel.add(cancelButton);
        centerPanel.add(activationControlPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel for Status/Notification Area
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        bottomPanel.add(new JLabel("Status: "));
        bottomPanel.add(statusLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Enables the activation function, which typically means making the UI ready for interaction.
     * In this view, it primarily means ensuring the view details button is enabled when a row is selected.
     */
    public void enableActivationFunction() {
        // This method is called when the application starts.
        // The view details button will be enabled when a row is selected.
        // The activate button and checkbox are enabled only after viewing details.
        Logger.logInfo("Activation function enabled in UI.");
    }

    /**
     * Displays a list of pending convention requests in the table.
     *
     * @param requests A list of ConventionRequest objects to display.
     */
    public void showConventionList(List<ConventionRequest> requests) {
        tableModel.setRowCount(0); // Clear existing data
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (ConventionRequest req : requests) {
            tableModel.addRow(new Object[]{
                    req.getRequestId(),
                    req.getConventionId(),
                    req.getRefreshmentPointName(),
                    sdf.format(req.getRequestDate()),
                    req.getStatus()
            });
        }
        // Enable view details button if there are requests to view
        viewDetailsButton.setEnabled(!requests.isEmpty());
        Logger.logInfo("Displayed " + requests.size() + " convention requests.");
    }

    /**
     * Returns the currently selected ConventionRequest from the table.
     *
     * @return The selected ConventionRequest, or null if none is selected.
     */
    public ConventionRequest getSelectedConventionRequest() {
        int selectedRow = conventionRequestTable.getSelectedRow();
        if (selectedRow >= 0) {
            String requestId = (String) tableModel.getValueAt(selectedRow, 0);
            String conventionId = (String) tableModel.getValueAt(selectedRow, 1);
            String refreshmentPointName = (String) tableModel.getValueAt(selectedRow, 2);
            String requestDateStr = (String) tableModel.getValueAt(selectedRow, 3);
            String status = (String) tableModel.getValueAt(selectedRow, 4);

            try {
                Date requestDate = new SimpleDateFormat("yyyy-MM-dd").parse(requestDateStr);
                return new ConventionRequest(requestId, conventionId, refreshmentPointName, requestDate, status);
            } catch (java.text.ParseException e) {
                Logger.logError("Error parsing request date: " + requestDateStr, e);
                showError("Error parsing request date.");
                return null;
            }
        }
        return null;
    }

    /**
     * Displays the details of a specific convention in the form fields.
     *
     * @param convention The Convention object to display.
     */
    public void displayConventionForm(Convention convention) {
        this.selectedConvention = convention; // Store the convention for activation
        conventionIdField.setText(convention.getId());
        refreshmentPointField.setText(convention.getRefreshmentPointName());
        startDateField.setText(convention.getStartDate());
        endDateField.setText(convention.getEndDate());
        termsArea.setText(convention.getTerms());

        // Enable activation controls
        approveActivationCheckbox.setEnabled(true);
        approveActivationCheckbox.setSelected(false); // Reset checkbox state
        activateConventionButton.setEnabled(false); // Disable until checkbox is ticked
        cancelButton.setEnabled(true); // Enable cancel button

        Logger.logInfo("Displayed details for convention: " + convention.getId());
    }

    /**
     * Asks for confirmation from the user via a dialog.
     *
     * @return true if the user confirms, false otherwise.
     */
    public boolean getConfirmation() {
        int response = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to activate Convention " + selectedConvention.getId() +
                        " for " + selectedConvention.getRefreshmentPointName() + "?",
                "Confirm Activation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return response == JOptionPane.YES_OPTION;
    }

    /**
     * Displays a success message to the user.
     *
     * @param message The success message to display.
     */
    public void showSuccess(String message) {
        statusLabel.setText("Success: " + message);
        statusLabel.setForeground(new Color(0, 128, 0)); // Green color for success
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
        Logger.logInfo("Success message displayed: " + message);
        resetForm(); // Reset form after successful activation
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        statusLabel.setText("Error: " + message);
        statusLabel.setForeground(Color.RED); // Red color for error
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        Logger.logError("Error message displayed: " + message, null);
    }

    /**
     * Resets the convention details form and activation controls to their initial state.
     */
    private void resetForm() {
        conventionIdField.setText("");
        refreshmentPointField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        termsArea.setText("");
        approveActivationCheckbox.setSelected(false);
        approveActivationCheckbox.setEnabled(false);
        activateConventionButton.setEnabled(false);
        cancelButton.setEnabled(false);
        selectedConvention = null; // Clear selected convention
        statusLabel.setText("Ready.");
        statusLabel.setForeground(Color.BLUE);
        Logger.logInfo("Convention details form reset.");
    }

    /**
     * Adds an ActionListener to the 'View Details' button.
     *
     * @param listener The ActionListener to be added.
     */
    public void addViewDetailsListener(ActionListener listener) {
        viewDetailsButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the 'Activate Convention' button.
     *
     * @param listener The ActionListener to be added.
     */
    public void addActivateConventionListener(ActionListener listener) {
        activateConventionButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the 'Cancel' button.
     *
     * @param listener The ActionListener to be added.
     */
    public void addCancelListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the 'Approve Activation' checkbox.
     *
     * @param listener The ActionListener to be added.
     */
    public void addApproveActivationCheckboxListener(ActionListener listener) {
        approveActivationCheckbox.addActionListener(listener);
    }

    /**
     * Adds a ListSelectionListener to the convention request table to enable/disable the 'View Details' button.
     *
     * @param listener The ListSelectionListener to be added.
     */
    public void addConventionRequestTableSelectionListener(javax.swing.event.ListSelectionListener listener) {
        conventionRequestTable.getSelectionModel().addListSelectionListener(listener);
    }

    /**
     * Checks if the 'Approve Activation' checkbox is selected.
     *
     * @return true if the checkbox is selected, false otherwise.
     */
    public boolean isApproveActivationChecked() {
        return approveActivationCheckbox.isSelected();
    }

    /**
     * Enables or disables the 'Activate Convention' button.
     *
     * @param enable true to enable, false to disable.
     */
    public void setActivateConventionButtonEnabled(boolean enable) {
        activateConventionButton.setEnabled(enable);
    }

    /**
     * Returns the currently selected Convention object for activation.
     *
     * @return The Convention object, or null if none is selected.
     */
    public Convention getSelectedConvention() {
        return selectedConvention;
    }

    /**
     * Enables or disables the 'View Details' button.
     *
     * @param enable true to enable, false to disable.
     */
    public void setViewDetailsButtonEnabled(boolean enable) {
        viewDetailsButton.setEnabled(enable);
    }
}