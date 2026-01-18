'''
Main registry screen after login. Allows loading a note and then deleting.
Implements the DeleteNote use case sequence.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class RegistryFrame extends JFrame {
    private NoteSystem system;
    private JTextField noteIdField;
    private JButton loadNoteButton;
    private JButton deleteButton;
    private JButton logoutButton;
    private JTextArea noteDetailsArea;
    private String currentNoteId; // Tracks currently loaded note ID
    public RegistryFrame(NoteSystem system) {
        this.system = system;
        setTitle("Disciplinary Note Registry");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Top panel: note ID input and load
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Enter Note ID:"));
        noteIdField = new JTextField(10);
        topPanel.add(noteIdField);
        loadNoteButton = new JButton("Load Note Details");
        loadNoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadNoteDetails();
            }
        });
        topPanel.add(loadNoteButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        // Center: note details display
        noteDetailsArea = new JTextArea();
        noteDetailsArea.setEditable(false);
        noteDetailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(noteDetailsArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Bottom panel: delete and logout buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());
        deleteButton = new JButton("Delete Note");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteNote();
            }
        });
        bottomPanel.add(deleteButton);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        bottomPanel.add(logoutButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    private void loadNoteDetails() {
        if (!system.isAdminLoggedIn()) {
            JOptionPane.showMessageDialog(this, "You are not logged in as administrator.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String noteId = noteIdField.getText().trim();
        if (noteId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Note ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Note note = system.loadNoteForDeletion(noteId);
        if (note == null) {
            noteDetailsArea.setText("Note not found with ID: " + noteId);
            deleteButton.setEnabled(false);
            currentNoteId = null;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Note ID: ").append(note.getId()).append("\n");
            sb.append("Student Name: ").append(note.getStudentName()).append("\n");
            sb.append("Description: ").append(note.getDescription()).append("\n");
            sb.append("Date: ").append(note.getDate()).append("\n");
            sb.append("Parent Email: ").append(note.getParentEmail()).append("\n");
            sb.append("Parent Phone: ").append(note.getParentPhone()).append("\n");
            noteDetailsArea.setText(sb.toString());
            deleteButton.setEnabled(true);
            currentNoteId = noteId;
            JOptionPane.showMessageDialog(this, "Note details loaded. You can now delete.", "Note Loaded", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void deleteNote() {
        if (!system.isAdminLoggedIn()) {
            JOptionPane.showMessageDialog(this, "Session expired. Please login again.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (currentNoteId == null || !deleteButton.isEnabled()) {
            JOptionPane.showMessageDialog(this, "No note loaded. Please load a note first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (system.simulateInterruption()) {
            JOptionPane.showMessageDialog(this, "Operation interrupted by administrator or connection lost.", "Interruption", JOptionPane.WARNING_MESSAGE);
            resetInterface();
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this note? A notification will be sent to parents.",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        boolean success = system.deleteNoteWithNotification(currentNoteId);
        if (success) {
            JOptionPane.showMessageDialog(this, "Note deleted successfully. Notification sent to parents.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Deletion failed. Notification may not have been sent.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        resetInterface();
    }
    private void logout() {
        system.logout();
        dispose();
        LoginFrame loginFrame = new LoginFrame(system);
        loginFrame.setVisible(true);
    }
    private void resetInterface() {
        noteIdField.setText("");
        noteDetailsArea.setText("");
        deleteButton.setEnabled(false);
        currentNoteId = null;
    }
}