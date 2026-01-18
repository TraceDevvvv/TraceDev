'''
JPanel for selecting a Point of Rest.
It displays a list of available points of rest and allows the user to select one
to proceed with banner insertion.
'''
package com.chatdev.bannerapp.gui;
import com.chatdev.bannerapp.model.PointOfRest;
import com.chatdev.bannerapp.service.BannerService;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
public class PointOfRestSelectionPanel extends JPanel {
    private BannerService bannerService;
    private JList<PointOfRest> pointOfRestList;
    private DefaultListModel<PointOfRest> listModel;
    private JButton insertBannerButton;
    // Callbacks to communicate with the parent frame (AgencyOperatorApp)
    private Consumer<PointOfRest> onSelectPointForBannerInsertion;
    private BiConsumer<String, String> errorHandler; // For displaying service errors
    /**
     * Constructs a new PointOfRestSelectionPanel.
     *
     * @param bannerService The service layer to fetch points of rest.
     * @param onSelectPointForBannerInsertion A callback function to be called when a point of rest is selected
     *                                      and the user wishes to insert a banner for it.
     * @param errorHandler A callback function to handle errors from the service layer.
     */
    public PointOfRestSelectionPanel(BannerService bannerService,
                                     Consumer<PointOfRest> onSelectPointForBannerInsertion,
                                     BiConsumer<String, String> errorHandler) {
        this.bannerService = bannerService;
        this.onSelectPointForBannerInsertion = onSelectPointForBannerInsertion;
        this.errorHandler = errorHandler;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initComponents();
        loadPointsOfRest();
    }
    /**
     * Initializes the graphical components of the panel.
     */
    private void initComponents() {
        JLabel titleLabel = new JLabel("Select a Point of Rest", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        listModel = new DefaultListModel<>();
        pointOfRestList = new JList<>(listModel);
        pointOfRestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pointOfRestList.setCellRenderer(new PointOfRestListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(pointOfRestList);
        add(scrollPane, BorderLayout.CENTER);
        insertBannerButton = new JButton("Access Function of Inserting Banners");
        insertBannerButton.setEnabled(false); // Initially disabled until a selection is made
        insertBannerButton.addActionListener(e -> {
            PointOfRest selectedPoint = pointOfRestList.getSelectedValue();
            if (selectedPoint != null) {
                onSelectPointForBannerInsertion.accept(selectedPoint);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a Point of Rest first.",
                        "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });
        // Enable button when an item is selected
        pointOfRestList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Ensure event fires only once per selection
                insertBannerButton.setEnabled(pointOfRestList.getSelectedValue() != null);
            }
        });
        add(insertBannerButton, BorderLayout.SOUTH);
    }
    /**
     * Loads the list of Points of Rest from the BannerService and populates the JList.
     * Handles potential service errors during data retrieval.
     */
    public void loadPointsOfRest() {
        try {
            List<PointOfRest> points = bannerService.getPointsOfRest();
            listModel.clear();
            for (PointOfRest point : points) {
                listModel.addElement(point);
            }
            insertBannerButton.setEnabled(false); // Disable button after refresh, user needs to re-select
        } catch (RuntimeException ex) {
            errorHandler.accept("Error retrieving Points of Rest", ex.getMessage());
        }
    }
    /**
     * Refreshes the list of Points of Rest, typically called when returning from
     * the Banner Insertion Panel to update banner counts.
     */
    public void refreshPointsOfRest() {
        loadPointsOfRest();
    }
    /**
     * Custom ListCellRenderer to display PointOfRest objects in a user-friendly format.
     */
    private static class PointOfRestListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof PointOfRest) {
                PointOfRest point = (PointOfRest) value;
                label.setText(point.getName() + " (ID: " + point.getId() + ") - Banners: " +
                              point.getBanners().size() + "/" + point.getMaxBanners());
            }
            return label;
        }
    }
}