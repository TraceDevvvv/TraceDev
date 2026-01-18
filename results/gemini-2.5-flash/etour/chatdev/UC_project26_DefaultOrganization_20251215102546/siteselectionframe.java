'''
This JFrame allows the Agency Operator to view a list of sites and select one.
It's the first step in the "MODIFICACOMMENTO" use case, following "RicercaSito".
'''
package com.chatdev.gui.components;
import com.chatdev.models.Site;
import com.chatdev.gui.utils.DialogUtils;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Runnable; // Explicitly import Runnable
public class SiteSelectionFrame extends JFrame {
    private JList<Site> siteList;
    private DefaultListModel<Site> listModel;
    private JButton selectButton;
    /**
     * Constructs a SiteSelectionFrame.
     * @param sites A list of Site objects to display.
     * @param onSelectCallback A Consumer functional interface to be called when a site is selected.
     *                         The selected Site object will be passed to this callback.
     * @param onCancelCallback A Runnable functional interface to be called when the operation is cancelled.
     */
    public SiteSelectionFrame(List<Site> sites, Consumer<Site> onSelectCallback, Runnable onCancelCallback) {
        super("Select a Site for Comment Editing");
        // Use DISPOSE_ON_CLOSE to hide and dispose this frame, but not terminate the JVM
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        listModel = new DefaultListModel<>();
        for (Site site : sites) {
            listModel.addElement(site);
        }
        siteList = new JList<>(listModel);
        siteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        siteList.setLayoutOrientation(JList.VERTICAL);
        siteList.setVisibleRowCount(-1); // Display all rows if enough space, otherwise use scrollPane
        JScrollPane listScrollPane = new JScrollPane(siteList);
        listScrollPane.setPreferredSize(new Dimension(350, 200));
        selectButton = new JButton("Select Site");
        selectButton.addActionListener(e -> {
            Site selectedSite = siteList.getSelectedValue();
            if (selectedSite != null) {
                // If a site is selected, call the callback and dispose of this frame
                onSelectCallback.accept(selectedSite);
                dispose(); // Close this frame
            } else {
                DialogUtils.showError(this, "Please select a site from the list.", "No Site Selected");
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            // Inform the system that the operation was cancelled
            onCancelCallback.run();
            dispose(); // Close this frame
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectButton);
        buttonPanel.add(cancelButton);
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); // Added some spacing
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        mainPanel.add(new JLabel("Available Sites:"), BorderLayout.NORTH);
        mainPanel.add(listScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
}