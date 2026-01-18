'''
DOCSTRING:
This JPanel displays the detailed information of a selected PuntoDiRistoro.
It arranges labels for each attribute in a clean, readable format using a GridBagLayout.
'''
import javax.swing.*;
import java.awt.*;
public class DettaglioPuntoDiRistoroUI extends JPanel {
    // UI Components for displaying details
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel addressLabel;
    private JTextArea descriptionTextArea; // Changed from JLabel to JTextArea for multi-line support
    private JLabel typeLabel;
    private JLabel capacityLabel;
    private JLabel contactPhoneLabel;
    private JLabel websiteLabel;
    public DettaglioPuntoDiRistoroUI() {
        // Set up the panel layout
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Dettagli Punto di Ristoro"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        gbc.anchor = GridBagConstraints.WEST; // Align labels to the left
        // Initialize and add labels for each field
        // Row 0: ID
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; idLabel = new JLabel("N/A"); add(idLabel, gbc);
        // Row 1: Name
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; nameLabel = new JLabel("N/A"); add(nameLabel, gbc);
        // Row 2: Type
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; typeLabel = new JLabel("N/A"); add(typeLabel, gbc);
        // Row 3: Address
        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Indirizzo:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; addressLabel = new JLabel("N/A"); add(addressLabel, gbc);
        // Row 4: Description - Using JTextArea wrapped in JScrollPane for multi-line support
        gbc.gridx = 0; gbc.gridy = 4; add(new JLabel("Descrizione:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        descriptionTextArea = new JTextArea("N/A", 3, 25); // Initial text, preferred rows, preferred columns
        descriptionTextArea.setEditable(false); // Make it non-editable
        descriptionTextArea.setLineWrap(true);  // Enable line wrapping
        descriptionTextArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea); // Wrap in JScrollPane
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Only show scrollbar if needed
        gbc.weightx = 1.0; gbc.weighty = 0.5; // Allow description area to expand vertically as well
        gbc.fill = GridBagConstraints.BOTH; // Allow both horizontal and vertical fill
        add(descriptionScrollPane, gbc);
        gbc.weightx = 0; gbc.weighty = 0; gbc.fill = GridBagConstraints.NONE; // Reset for next components
        // Row 5: Capacity
        gbc.gridx = 0; gbc.gridy = 5; add(new JLabel("Capacità:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5; capacityLabel = new JLabel("N/A"); add(capacityLabel, gbc);
        // Row 6: Contact Phone
        gbc.gridx = 0; gbc.gridy = 6; add(new JLabel("Telefono:"), gbc);
        gbc.gridx = 1; gbc.gridy = 6; contactPhoneLabel = new JLabel("N/A"); add(contactPhoneLabel, gbc);
        // Row 7: Website
        gbc.gridx = 0; gbc.gridy = 7; add(new JLabel("Sito web:"), gbc);
        gbc.gridx = 1; gbc.gridy = 7; websiteLabel = new JLabel("N/A"); add(websiteLabel, gbc);
    }
    /**
     * Updates the UI to display the details of the given PuntoDiRistoro object.
     * If the punto is null, it clears the displayed information.
     * @param punto The PuntoDiRistoro object whose details are to be displayed, or null to clear.
     */
    public void displayPuntoDiRistoro(PuntoDiRistoro punto) {
        if (punto != null) {
            idLabel.setText(punto.getId());
            nameLabel.setText(punto.getName());
            typeLabel.setText(punto.getType());
            addressLabel.setText(punto.getAddress());
            descriptionTextArea.setText(punto.getDescription()); // Set text for JTextArea
            descriptionTextArea.setCaretPosition(0); // Scroll to top for long texts
            capacityLabel.setText(String.valueOf(punto.getCapacity()));
            contactPhoneLabel.setText(punto.getContactPhone());
            websiteLabel.setText(punto.getWebsite());
        } else {
            // Clear all fields if punto is null (e.g., if an error occurs)
            idLabel.setText("N/A");
            nameLabel.setText("N/A");
            typeLabel.setText("N/A");
            addressLabel.setText("N/A");
            descriptionTextArea.setText("N/A"); // Clear text for JTextArea
            capacityLabel.setText("N/A");
            contactPhoneLabel.setText("N/A");
            websiteLabel.setText("N/A");
        }
    }
}