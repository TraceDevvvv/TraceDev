"""
Dialog for displaying detailed cultural heritage card information.
Shows all details of a selected cultural heritage item.
Implements step 2 and exit conditions of the use case.
"""
/**
 * Dialog for displaying detailed cultural heritage card information.
 */
class HeritageCardDialog extends JDialog {
    private CulturalHeritage heritage;
    /**
     * Constructor for the heritage card dialog.
     * @param parent The parent frame
     * @param heritage The cultural heritage item to display
     */
    public HeritageCardDialog(Frame parent, CulturalHeritage heritage) {
        super(parent, "Cultural Heritage Card", true);
        this.heritage = heritage;
        initComponents();
        layoutComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(parent);
    }
    /**
     * Initialize dialog components.
     */
    private void initComponents() {
        // Components will be created and populated in layoutComponents
    }
    /**
     * Layout components within the dialog.
     */
    private void layoutComponents() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));
        // Main panel with card layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Title section
        JLabel titleLabel = new JLabel(heritage.getName(), JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleLabel);
        // Details section
        mainPanel.add(createDetailsSection());
        mainPanel.add(Box.createVerticalStrut(10));
        // Images section
        mainPanel.add(createImagesSection());
        mainPanel.add(Box.createVerticalStrut(10));
        // Additional information section
        mainPanel.add(createAdditionalInfoSection());
        // Add main panel to content pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        // Close button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Create the details section panel.
     * @return Panel containing cultural heritage details
     */
    private JPanel createDetailsSection() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Row 0: ID
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(heritage.getId()), gbc);
        // Row 1: Type
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(heritage.getType()), gbc);
        // Row 2: Location
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(heritage.getLocation()), gbc);
        // Row 3: Period
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Period:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(heritage.getPeriod()), gbc);
        // Row 4: Status
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(heritage.getStatus()), gbc);
        return panel;
    }
    /**
     * Create the images section panel.
     * @return Panel containing image information
     */
    private JPanel createImagesSection() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Images"));
        List<String> images = heritage.getImages();
        if (images.isEmpty()) {
            panel.add(new JLabel("No images available", JLabel.CENTER), BorderLayout.CENTER);
        } else {
            JPanel imageListPanel = new JPanel();
            imageListPanel.setLayout(new BoxLayout(imageListPanel, BoxLayout.Y_AXIS));
            for (String image : images) {
                JLabel imageLabel = new JLabel("• " + image);
                imageLabel.setBorder(new EmptyBorder(2, 10, 2, 10));
                imageListPanel.add(imageLabel);
            }
            panel.add(new JScrollPane(imageListPanel), BorderLayout.CENTER);
        }
        return panel;
    }
    /**
     * Create the additional information section panel.
     * @return Panel containing additional heritage information
     */
    private JPanel createAdditionalInfoSection() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Additional Information"));
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        StringBuilder infoText = new StringBuilder();
        infoText.append("Description:\n");
        infoText.append(heritage.getDescription());
        infoText.append("\n\n");
        infoText.append("Historical Significance:\n");
        infoText.append(heritage.getHistoricalSignificance());
        infoText.append("\n\n");
        infoText.append("Conservation Status:\n");
        infoText.append(heritage.getConservationStatus());
        infoArea.setText(infoText.toString());
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);
        return panel;
    }
}