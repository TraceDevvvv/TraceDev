// Simulated list view for selecting a Point of Rest (Step 1-2)
package com.etour.agency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PointOfRestListFrame extends JFrame {
    private PointOfRestDAO dao;
    private AgencyOperator operator;
    private JList<PointOfRest> pointList;
    private DefaultListModel<PointOfRest> listModel;

    public PointOfRestListFrame(PointOfRestDAO dao, AgencyOperator operator) {
        this.dao = dao;
        this.operator = operator;
        initializeUI();
        loadPoints();
    }

    private void initializeUI() {
        setTitle("Points of Rest - Agency Operator: " + operator.getUsername());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        pointList = new JList<>(listModel);
        pointList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pointList.setCellRenderer(new PointOfRestRenderer());

        // Double-click to edit
        pointList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editSelectedPoint();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(pointList);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton editButton = new JButton("Edit Selected");
        JButton refreshButton = new JButton("Refresh");
        JButton logoutButton = new JButton("Logout");

        editButton.addActionListener(e -> editSelectedPoint());
        refreshButton.addActionListener(e -> loadPoints());
        logoutButton.addActionListener(e -> {
            operator.setLoggedIn(false);
            JOptionPane.showMessageDialog(this, "Logged out.");
            dispose();
        });

        buttonPanel.add(editButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadPoints() {
        listModel.clear();
        List<PointOfRest> points = dao.getAllPoints();
        for (PointOfRest p : points) {
            listModel.addElement(p);
        }
    }

    private void editSelectedPoint() {
        PointOfRest selected = pointList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a Point of Rest to edit.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Step 3: Agency Operator activates a data change function.
        // Step 4-5: System uploads data and displays change form.
        EditPointOfRestForm editForm = new EditPointOfRestForm(selected, dao, operator);
        editForm.setVisible(true);
    }

    private static class PointOfRestRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof PointOfRest) {
                PointOfRest p = (PointOfRest) value;
                setText(p.getName() + " - " + p.getLocation() + " (Capacity: " + p.getCapacity() + ")");
            }
            return this;
        }
    }
}