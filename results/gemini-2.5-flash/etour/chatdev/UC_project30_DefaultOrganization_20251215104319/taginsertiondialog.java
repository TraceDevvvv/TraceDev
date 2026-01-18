/**
 * A Swing JDialog that provides a form for inserting a new tag.
 * This dialog handles user input, validation, and interaction with the TagService.
 * It corresponds to steps 1, 2, 3, and part of 4 in the use case flow.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicBoolean;
public class TagInsertionDialog extends JDialog {
    private final TagService tagService;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JButton addButton;
    private JButton cancelButton;
    private final AtomicBoolean tagAddedSuccessfully = new AtomicBoolean(false);
    /**
     * Constructs a new TagInsertionDialog.
     * @param owner The Frame from which the dialog is displayed.
     * @param tagService The service responsible for tag operations.
     */
    public TagInsertionDialog(Frame owner, TagService tagService) {
        super(owner, "Insert New Tag", true); // true for modal dialog
        this.tagService = tagService;
        initComponents();
        setupLayout();
        setupListeners();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(owner); // Center relative to the owner frame
    }
    /**
     * Returns whether a tag was successfully added during this dialog's lifecycle.
     * @return true if a tag was added, false otherwise.
     */
    public boolean isTagAddedSuccessfully() {
        return tagAddedSuccessfully.get();
    }
    /**
     * Initializes the GUI components of the dialog.
     */
    private void initComponents() {
        nameField = new JTextField(20);
        nameField.setToolTipText("Enter the name of the new tag (e.g., 'JavaFX')");
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setToolTipText("Provide a brief description for the tag");
        addButton = new JButton("Add Tag");
        cancelButton = new JButton("Cancel");
    }
    /**
     * Sets up the layout of the dialog using GridBagLayout.
     */
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Label for Tag Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Tag Name:"), gbc);
        // Text field for Tag Name
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Allows horizontal expansion
        gbc.anchor = GridBagConstraints.WEST;
        add(nameField, gbc);
        // Label for Description
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST; // Align label to top-right with textarea
        add(new JLabel("Description:"), gbc);
        // Text area for Description
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0; // Allows vertical expansion for descriptionArea
        gbc.fill = GridBagConstraints.BOTH; // Allows descriptionArea to fill available space
        add(new JScrollPane(descriptionArea), gbc);
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weightx = 0; // Reset weightx
        gbc.weighty = 0; // Reset weighty
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
    }
    /**
     * Sets up action listeners for buttons and window close event.
     */
    private void setupListeners() {
        addButton.addActionListener(e -> addTag());
        cancelButton.addActionListener(e -> dispose()); // Close the dialog on cancel
        // Handle window closing to ensure tagAddedSuccessfully is reset if not explicitly added
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                tagAddedSuccessfully.set(false);
            }
        });
    }
    /**
     * Handles the logic for adding a new tag when the "Add Tag" button is clicked.
     * This method corresponds to step 3 and 4 of the use case flow.
     */
    private void addTag() {
        // Step 3: Fill out the form with the required information and submit.
        String name = nameField.getText();
        String description = descriptionArea.getText();
        try {
            // Step 4: Verify the data entered and check if the tag is already present in the system.
            tagService.addTag(name, description);
            // If successful, notify the user and set success flag.
            JOptionPane.showMessageDialog(this,
                    "Tag '" + name + "' successfully added!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            tagAddedSuccessfully.set(true);
            dispose(); // Close the dialog after successful addition
        } catch (InvalidTagDataException ex) {
            // Activates the use case Errored
            JOptionPane.showMessageDialog(this,
                    "Invalid input: " + ex.getMessage(), "Error",
                    JOptionPane.WARNING_MESSAGE);
        } catch (TagAlreadyExistsException ex) {
            // Activates the use case ErroreTagEsistente
            JOptionPane.showMessageDialog(this,
                    "Tag already exists: " + ex.getMessage(), "Duplicate Tag",
                    JOptionPane.WARNING_MESSAGE);
        } catch (ServerConnectionException ex) { // ADDED specific catch for ETOUR
            // Handles the simulated ETOUR condition specifically.
            JOptionPane.showMessageDialog(this,
                    "Connection Error: " + ex.getMessage(), "Server Disconnected (ETOUR)",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // Catches any other unexpected runtime exceptions that were not explicitly handled
            JOptionPane.showMessageDialog(this,
                    "An unexpected system error occurred: " + ex.getMessage(), "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}