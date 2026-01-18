/*
 * A custom Swing dialog for selecting an image file.
 * It uses a JFileChooser and can potentially display a preview (though not fully implemented for preview in this version).
 */
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
public class ImageSelectionDialog extends JDialog {
    private String selectedImagePath = null;
    private JLabel previewLabel; // Placeholder for image preview
    /**
     * Constructs an ImageSelectionDialog.
     * @param owner The parent Frame of this dialog.
     */
    public ImageSelectionDialog(Frame owner) {
        super(owner, "Select Banner Image", true); // Modal dialog
        initComponents();
        setSize(600, 400);
        setLocationRelativeTo(owner); // Center dialog on parent frame
    }
    /**
     * Initializes the components of the dialog: JFileChooser, preview area, and buttons.
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        // File Chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an image for the banner");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif"));
        fileChooser.setAcceptAllFileFilterUsed(false); // Only allow image files
        fileChooser.setAccessory(createPreviewPanel(fileChooser)); // Attach a preview panel
        add(fileChooser, BorderLayout.CENTER);
        fileChooser.addActionListener(e -> {
            if (JFileChooser.APPROVE_SELECTION.equals(e.getActionCommand())) {
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    selectedImagePath = selectedFile.getAbsolutePath();
                    dispose(); // Close dialog on selection
                }
            } else if (JFileChooser.CANCEL_SELECTION.equals(e.getActionCommand())) {
                selectedImagePath = null; // Clear selection on cancel
                dispose(); // Close dialog on cancel
            }
        });
    }
    /**
     * Creates a simple accessory panel for image preview within the JFileChooser.
     * This is a basic implementation; a more robust one would load and scale the image.
     * @param fileChooser The JFileChooser to which this panel will be an accessory.
     * @return A JPanel accessory for the file chooser.
     */
    private JPanel createPreviewPanel(JFileChooser fileChooser) {
        JPanel previewPanel = new JPanel(new BorderLayout());
        previewLabel = new JLabel();
        previewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        previewLabel.setVerticalAlignment(SwingConstants.CENTER);
        previewLabel.setPreferredSize(new Dimension(150, 150));
        previewPanel.add(new JScrollPane(previewLabel), BorderLayout.CENTER);
        previewPanel.setBorder(BorderFactory.createTitledBorder("Image Preview"));
        // Listener to update preview when a file is selected
        fileChooser.addPropertyChangeListener(evt -> {
            if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(evt.getPropertyName())) {
                File file = (File) evt.getNewValue();
                if (file != null && file.isFile()) {
                    try {
                        ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                        // Scale image for preview if too large (simple scaling, not optimized)
                        if (imageIcon.getIconWidth() > 150 || imageIcon.getIconHeight() > 150) {
                            Image img = imageIcon.getImage().getScaledInstance(150, -1, Image.SCALE_SMOOTH);
                            previewLabel.setIcon(new ImageIcon(img));
                        } else {
                            previewLabel.setIcon(imageIcon);
                        }
                    } catch (Exception ex) {
                        previewLabel.setText("No Preview");
                        previewLabel.setIcon(null);
                    }
                } else {
                    previewLabel.setText("No Preview");
                    previewLabel.setIcon(null);
                }
            }
        });
        return previewPanel;
    }
    /**
     * Displays the dialog and waits for user interaction.
     * @return The path of the selected image, or null if no image was selected or canceled.
     */
    public String showDialog() {
        setVisible(true); // This call blocks until the dialog is closed implicitly (dispose())
        return selectedImagePath;
    }
}