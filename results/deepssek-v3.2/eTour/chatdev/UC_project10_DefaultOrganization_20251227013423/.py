/**
 * RefreshmentPointCardPanel displays detailed information about a selected refreshment point.
 */
import javax.swing.*;
import java.awt.*;
public class RefreshmentPointCardPanel extends JPanel {
    private JLabel nameLabel;
    private JTextArea descriptionArea;
    private JLabel locationLabel;
    private JLabel typeLabel;
    private JLabel ratingLabel;
    private JList<String> facilitiesList;
    private DefaultListModel<String> facilitiesModel;
    /**
     * Constructor sets up the card panel layout and components.
     */
    public RefreshmentPointCardPanel() {
        // Set layout and border
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Refreshment Point Details"));
        // Create a panel for details
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        detailsPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameLabel = new JLabel("---");
        detailsPanel.add(nameLabel, gbc);
        // Location
        gbc.gridx = 0; gbc.gridy = 1;
        detailsPanel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        locationLabel = new JLabel("---");
        detailsPanel.add(locationLabel, gbc);
        // Type
        gbc.gridx = 0; gbc.gridy = 2;
        detailsPanel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        typeLabel = new JLabel("---");
        detailsPanel.add(typeLabel, gbc);
        // Rating
        gbc.gridx = 0; gbc.gridy = 3;
        detailsPanel.add(new JLabel("Rating:"), gbc);
        gbc.gridx = 1;
        ratingLabel = new JLabel("---");
        detailsPanel.add(ratingLabel, gbc);
        // Facilities
        gbc.gridx = 0; gbc.gridy = 4;
        detailsPanel.add(new JLabel("Facilities:"), gbc);
        gbc.gridx = 1;
        facilitiesModel = new DefaultListModel<>();
        facilitiesList = new JList<>(facilitiesModel);
        JScrollPane facilitiesScroll = new JScrollPane(facilitiesList);
        facilitiesScroll.setPreferredSize(new Dimension(150, 80));
        detailsPanel.add(facilitiesScroll, gbc);
        // Description
        gbc.gridx = 0; gbc.gridy = 5;
        detailsPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        detailsPanel.add(descriptionScroll, gbc);
        add(detailsPanel, BorderLayout.CENTER);
    }
    /**
     * Updates the card panel with details of a refreshment point.
     * @param point The RefreshmentPoint to display, or null to clear the panel.
     */
    public void updateCard(RefreshmentPoint point) {
        if (point == null) {
            nameLabel.setText("---");
            locationLabel.setText("---");
            typeLabel.setText("---");
            ratingLabel.setText("---");
            descriptionArea.setText("");
            facilitiesModel.clear();
            return;
        }
        // Update labels and text areas with point details
        nameLabel.setText(point.getName());
        locationLabel.setText(point.getLocation());
        typeLabel.setText(point.getType());
        ratingLabel.setText(String.format("%.1f", point.getRating()));
        descriptionArea.setText(point.getDescription());
        // Update facilities list
        facilitiesModel.clear();
        for (String facility : point.getFacilities()) {
            facilitiesModel.addElement(facility);
        }
    }
}