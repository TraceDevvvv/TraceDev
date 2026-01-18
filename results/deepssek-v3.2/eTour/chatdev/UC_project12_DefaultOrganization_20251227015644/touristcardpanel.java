/**
 * Panel for displaying detailed tourist information in a card format.
 */
import javax.swing.*;
import java.awt.*;
public class TouristCardPanel extends JPanel {
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel nationalityLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel passportLabel;
    private JLabel dobLabel;
    public TouristCardPanel() {
        initializeComponents();
        setupLayout();
    }
    private void initializeComponents() {
        idLabel = createDetailLabel();
        nameLabel = createDetailLabel();
        nationalityLabel = createDetailLabel();
        emailLabel = createDetailLabel();
        phoneLabel = createDetailLabel();
        passportLabel = createDetailLabel();
        dobLabel = createDetailLabel();
    }
    private JLabel createDetailLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return label;
    }
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Create a visually appealing card border
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Tourist Card"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        // Add fields in two-column layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        add(idLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        add(nameLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Nationality:"), gbc);
        gbc.gridx = 1;
        add(nationalityLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        add(emailLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        add(phoneLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Passport:"), gbc);
        gbc.gridx = 1;
        add(passportLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Date of Birth:"), gbc);
        gbc.gridx = 1;
        add(dobLabel, gbc);
    }
    /**
     * Displays tourist details in the card
     * @param tourist The tourist to display
     */
    public void displayTourist(Tourist tourist) {
        idLabel.setText(tourist.getId());
        nameLabel.setText(tourist.getName());
        nationalityLabel.setText(tourist.getNationality());
        emailLabel.setText(tourist.getEmail());
        phoneLabel.setText(tourist.getPhoneNumber());
        passportLabel.setText(tourist.getPassportNumber());
        dobLabel.setText(tourist.getDateOfBirth());
    }
    /**
     * Clears the card display
     */
    public void clear() {
        idLabel.setText("");
        nameLabel.setText("");
        nationalityLabel.setText("");
        emailLabel.setText("");
        phoneLabel.setText("");
        passportLabel.setText("");
        dobLabel.setText("");
    }
}