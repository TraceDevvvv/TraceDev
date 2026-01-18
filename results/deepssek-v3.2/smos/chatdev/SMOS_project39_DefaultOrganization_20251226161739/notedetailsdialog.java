'''
NoteDetailsDialog.java
A dialog window that displays the details of a selected note.
It is shown when the administrator clicks the "Details" button.
'''
import javax.swing.*;
import java.awt.*;
public class NoteDetailsDialog extends JDialog {
    private boolean serverConnectionInterrupted = false;
    /**
     * Constructor for the dialog.
     * @param parent The parent frame (main app window).
     * @param note The note whose details are to be displayed.
     */
    public NoteDetailsDialog(Frame parent, Note note) {
        super(parent, "Note Details", true);
        setLayout(new BorderLayout());
        // If note is null, show an error message and return
        if (note == null) {
            JLabel errorLabel = new JLabel("Error: Note not found.", SwingConstants.CENTER);
            errorLabel.setForeground(Color.RED);
            add(errorLabel, BorderLayout.CENTER);
            setSize(300, 100);
            setLocationRelativeTo(parent);
            return;
        }
        // Create a panel to hold the form with GridBagLayout for better control
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Add labels and data fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Student:"), gbc);
        gbc.gridx = 1;
        JTextField studentField = new JTextField(note.getStudent(), 30);
        studentField.setEditable(false);
        formPanel.add(studentField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Teacher:"), gbc);
        gbc.gridx = 1;
        JTextField teacherField = new JTextField(note.getTeacher(), 30);
        teacherField.setEditable(false);
        formPanel.add(teacherField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Date:"), gbc);
        gbc.gridx = 1;
        JTextField dateField = new JTextField(note.getDate(), 30);
        dateField.setEditable(false);
        formPanel.add(dateField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JTextArea descriptionArea = new JTextArea(note.getDescription(), 6, 30);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        formPanel.add(descriptionScroll, gbc);
        add(formPanel, BorderLayout.CENTER);
        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            // Simulate server connection interruption as per postcondition
            serverConnectionInterrupted = true;
            System.out.println("SMOS server connection interrupted by administrator.");
            dispose();
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setMinimumSize(new Dimension(500, 350));
        setLocationRelativeTo(parent);
        // Add window listener to handle connection interruption on close
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (!serverConnectionInterrupted) {
                    serverConnectionInterrupted = true;
                    System.out.println("SMOS server connection interrupted by administrator.");
                }
            }
        });
    }
}