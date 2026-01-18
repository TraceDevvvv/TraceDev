package com.example.ui;

import com.example.Home;
import com.example.model.BeanCulturalHeritage;
import com.example.service.ICulturalHeritageAgencyManager;
import com.example.service.SessionManager;
import com.example.util.ErrorMessage;
import com.example.exception.RemoteException; // Using custom RemoteException

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Vector;

/**
 * JInternalFrame representing the main view for Cultural Heritage management.
 * This UI allows users to view and modify cultural heritage objects.
 * Implements ListSelectionListener for table row selection and ActionListener for button clicks.
 */
public class CulturalHeritage extends JInternalFrame implements ListSelectionListener, ActionListener {

    /**
     * The table displaying cultural heritage data.
     * This attribute corresponds to the 'tableBC: JTable' element in the class diagram.
     */
    private JTable tableBC;
    private JButton btnModifyBC;
    private ICulturalHeritageAgencyManager manager;
    private JDesktopPane desktopPane;
    private SessionManager sessionManager; // Added for REQ ID 3

    private DefaultTableModel tableModel;
    private static final String[] COLUMN_NAMES = {"ID", "Name", "City", "Province"};

    /**
     * Constructor for CulturalHeritage UI.
     * @param manager The RMI manager for cultural heritage operations.
     * @param desktopPane The JDesktopPane to which this internal frame and its children will be added.
     */
    public CulturalHeritage(ICulturalHeritageAgencyManager manager, JDesktopPane desktopPane) {
        super("Cultural Heritage Management", true, true, true, true); // resizable, closable, maximizable, iconifiable
        this.manager = manager;
        this.desktopPane = desktopPane;
        this.sessionManager = new SessionManager(); // Initialize session manager

        // REQ ID 3: Check if user is logged in before proceeding
        if (!sessionManager.isUserLoggedIn()) {
            JOptionPane.showMessageDialog(this,
                    ErrorMessage.ERROR_NOT_LOGGED_IN,
                    "Access Denied",
                    JOptionPane.ERROR_MESSAGE);
            this.dispose(); // Close this frame if not logged in
            return;
        }

        initUI();
        updateTableModel(); // Populate table on startup
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Close this frame when done
    }

    /**
     * Initializes the user interface components.
     */
    private void initUI() {
        setLayout(new BorderLayout());

        // Table setup
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        tableBC = new JTable(tableModel);
        tableBC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableBC.getSelectionModel().addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane(tableBC);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        btnModifyBC = new JButton("Modify Cultural Heritage");
        btnModifyBC.addActionListener(this);
        btnModifyBC.setEnabled(false); // Disable until a row is selected
        buttonPanel.add(btnModifyBC);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Updates the table model by fetching the latest cultural heritage data from the manager.
     */
    public void updateTableModel() {
        tableModel.setRowCount(0); // Clear existing data

        try {
            // 1. CH_UI -> Mgr_RMI : getCulturalHeritage()
            List<BeanCulturalHeritage> culturalHeritageList = manager.getCulturalHeritage();
            if (culturalHeritageList != null && !culturalHeritageList.isEmpty()) {
                for (BeanCulturalHeritage bc : culturalHeritageList) {
                    tableModel.addRow(new Object[]{bc.getId(), bc.getName(), bc.getCity(), bc.getProvince()});
                }
            } else {
                System.out.println("No cultural heritage data found.");
            }
        } catch (RemoteException e) {
            // Catch custom RemoteException from service (validation, DB errors)
            JOptionPane.showMessageDialog(this,
                    "Error loading data: " + e.getMessage(),
                    "Data Load Error",
                    JOptionPane.ERROR_MESSAGE);
            // On severe data load error, dispose the frame as it can't function correctly
            this.dispose();
        } catch (java.rmi.RemoteException e) {
            // Catch RMI communication errors
            JOptionPane.showMessageDialog(this,
                    ErrorMessage.ERROR_RMI_CONNECTION + "\nDetails: " + e.getMessage(),
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            // If RMI connection is lost, it's critical, so dispose
            this.dispose();
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.err.println("Unexpected error during table model update: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    ErrorMessage.ERROR_UNKNOWN + "\nDetails: " + e.getMessage(),
                    "Unknown Error",
                    JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    /**
     * Returns the ID of the currently selected cultural heritage object in the table.
     * @return The ID as an int, or -1 if no row is selected or ID cannot be parsed.
     */
    public int getSelectedCulturalHeritageId() {
        int selectedRow = tableBC.getSelectedRow();
        if (selectedRow >= 0) {
            Object idObj = tableBC.getValueAt(selectedRow, 0); // Assuming ID is in the first column
            if (idObj instanceof Integer) {
                return (int) idObj;
            } else if (idObj instanceof String) {
                try {
                    return Integer.parseInt((String) idObj);
                } catch (NumberFormatException e) {
                    System.err.println("Could not parse ID from table: " + idObj);
                }
            }
        }
        return -1;
    }

    /**
     * Handles action events from buttons.
     * Specifically, when the "Modify Cultural Heritage" button is clicked.
     * @param e The ActionEvent generated.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnModifyBC) {
            int selectedId = getSelectedCulturalHeritageId();
            if (selectedId != -1) {
                handleModifyAction(selectedId);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please select a cultural heritage to modify.",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Handles the logic for modifying a cultural heritage item.
     * Fetches the item, creates a CardBC, and adds it to the desktop pane.
     * @param id The ID of the cultural heritage to modify.
     */
    private void handleModifyAction(int id) {
        try {
            // CH_UI -> Mgr_RMI : getCulturalHeritage(id)
            BeanCulturalHeritage selectedBC = manager.getCulturalHeritage(id);
            if (selectedBC != null) {
                // Create and show CardBC
                CardBC cardBC = new CardBC(selectedBC, manager, this);
                addInternalFrame(cardBC, true); // REQ ID 8: addAndCenterFrame() -> addInternalFrame(frame, true)
                cardBC.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Could not retrieve details for selected cultural heritage (ID: " + id + "). It might have been deleted.",
                        "Data Not Found",
                        JOptionPane.WARNING_MESSAGE);
                updateTableModel(); // Refresh table in case item was removed
            }
        } catch (RemoteException ex) {
            // Catches custom RemoteException from service (DB errors)
            JOptionPane.showMessageDialog(this,
                    ErrorMessage.ERROR_DATA_LOAD + ": " + ex.getMessage(),
                    "Data Load Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (java.rmi.RemoteException ex) {
            // Catches RMI communication errors
            JOptionPane.showMessageDialog(this,
                    ErrorMessage.ERROR_RMI_CONNECTION + "\nDetails: " + ex.getMessage(),
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            this.dispose(); // Critical error, close UI
        } catch (Exception ex) {
            // Catch any other unexpected exceptions
            System.err.println("Unexpected error during modify action: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    ErrorMessage.ERROR_UNKNOWN + "\nDetails: " + ex.getMessage(),
                    "Unknown Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles list selection events for the JTable.
     * Enables or disables the "Modify" button based on row selection.
     * @param e The ListSelectionEvent generated.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Ensure event is not triggered during selection adjustment
            boolean rowSelected = tableBC.getSelectedRow() != -1;
            btnModifyBC.setEnabled(rowSelected);
        }
    }

    /**
     * Adds an internal frame to the desktop pane and centers it.
     * Implements requirement REQ ID 10.
     * @param frame The JInternalFrame to add.
     * @param center If true, the frame will be centered on the desktop pane.
     */
    public void addInternalFrame(JInternalFrame frame, boolean center) {
        desktopPane.add(frame);
        if (center) {
            Dimension desktopSize = desktopPane.getSize();
            Dimension frameSize = frame.getSize();
            if (frameSize.getWidth() > 0 && frameSize.getHeight() > 0) { // Ensure frame has a valid size
                frame.setLocation((desktopSize.width - frameSize.width) / 2,
                        (desktopSize.height - frameSize.height) / 2);
            } else {
                // If frame size is not set, default to a reasonable position
                frame.setLocation(50, 50);
            }
        }
        try {
            frame.setSelected(true); // Bring frame to front
        } catch (java.beans.PropertyVetoException e) {
            System.err.println("Could not select internal frame: " + e.getMessage());
        }
    }
}