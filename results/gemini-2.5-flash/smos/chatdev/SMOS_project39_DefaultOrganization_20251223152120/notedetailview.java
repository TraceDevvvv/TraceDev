/*
 * Provides a graphical user interface (GUI) to display the details of a specific note.
 * This class extends JFrame to create a windowed application.
 */
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionListener; // Required for event handling
import java.awt.event.WindowAdapter; // Import for WindowAdapter
import java.awt.event.WindowEvent;   // Import for WindowEvent
public class NoteDetailView extends JFrame {
    // Labels for displaying note details
    private JLabel studentLabel;
    private JLabel descriptionLabel;
    private JLabel teacherLabel;
    private JLabel dateLabel;
    // Text fields to hold and display the note data (non-editable)
    private JTextField studentField;
    private JTextArea descriptionArea;
    private JTextField teacherField;
    private JTextField dateField;
    /**
     * Constructs a NoteDetailView frame to display the details of a given Note.
     *
     * @param note The Note object whose details are to be displayed.
     */
    public NoteDetailView(Note note) {
        // Set the title of the window
        super("Note Details (Administrator View)");
        // Change default close operation to DO_NOTHING_ON_CLOSE.
        // The window close event will be handled by a WindowListener to trigger disconnection.
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Add a WindowListener to handle closing the window via the 'X' button.
        // This ensures the disconnection logic is always executed, fulfilling the postcondition.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                performDisconnection(); // Trigger the shared disconnection logic
            }
        });
        // Set the preferred size for the window, which pack() will try to honor.
        setPreferredSize(new Dimension(500, 380)); // Slightly increased height for the new button
        // Initialize and populate the GUI components with the note data
        initComponents(note);
        // Pack the components to their preferred size
        pack();
        // Ensure the window is not resizable for a fixed layout
        setResizable(false);
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Make the window visible
        setVisible(true);
    }
    /**
     * Initializes and lays out the GUI components for displaying note details.
     *
     * @param note The Note object from which to retrieve details.
     */
    private void initComponents(Note note) {
        // Use JPanel with GridBagLayout for a flexible and aligned form layout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components will fill horizontally
        // --- Student ---
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.anchor = GridBagConstraints.WEST; // Align label to the west
        studentLabel = new JLabel("Student:");
        panel.add(studentLabel, gbc);
        gbc.gridx = 1; // Column 1
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Allow studentField to stretch horizontally
        studentField = new JTextField(note.getStudent());
        studentField.setEditable(false); // Make it read-only
        studentField.setBackground(Color.LIGHT_GRAY); // Differentiate from editable fields
        panel.add(studentField, gbc);
        // --- Description ---
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Align label to top-left
        descriptionLabel = new JLabel("Description:");
        panel.add(descriptionLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0; // Allow descriptionArea to stretch vertically
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        descriptionArea = new JTextArea(note.getDescription());
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);       // Wrap text to fit
        descriptionArea.setWrapStyleWord(true); // Wrap on word boundaries
        descriptionArea.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(descriptionArea); // Add scrollbar for long descriptions
        scrollPane.setPreferredSize(new Dimension(300, 100)); // Set preferred size for scroll pane
        panel.add(scrollPane, gbc);
        // Reset weightY and fill for subsequent components
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // --- Teacher ---
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        teacherLabel = new JLabel("Teacher:");
        panel.add(teacherLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        teacherField = new JTextField(note.getTeacher());
        teacherField.setEditable(false);
        teacherField.setBackground(Color.LIGHT_GRAY);
        panel.add(teacherField, gbc);
        // --- Date ---
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        dateLabel = new JLabel("Date:");
        panel.add(dateLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        // Format the LocalDate object to a readable string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateField = new JTextField(note.getDate().format(formatter));
        dateField.setEditable(false);
        dateField.setBackground(Color.LIGHT_GRAY);
        panel.add(dateField, gbc);
        // --- Disconnect Button ---
        // This button simulates the administrator interrupting the connection to the SMOS server.
        JButton disconnectButton = new JButton("Finish Viewing & Disconnect");
        gbc.gridx = 0;
        gbc.gridy = 4; // Place it on a new row below the date field
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        gbc.fill = GridBagConstraints.NONE; // Do not make the button fill horizontally
        // Add an ActionListener to simulate the disconnection event
        disconnectButton.addActionListener(e -> {
            performDisconnection(); // Call the shared disconnection logic
        });
        panel.add(disconnectButton, gbc);
        // Add the panel to the frame's content pane
        this.add(panel);
    }
    /**
     * Encapsulates the logic for simulating server disconnection and exiting the application.
     * This method is called whenever the administrator finishes viewing the note,
     * whether by clicking the button or closing the window.
     */
    private void performDisconnection() {
        // Log a message to indicate the simulated server connection interruption.
        System.out.println("INFO: Administrator finished viewing note details and interrupted connection to the SMOS server.");
        // Close the current JFrame window.
        dispose();
        // Terminate the application. This fulfills the requirement of "interrupted".
        System.exit(0);
    }
}