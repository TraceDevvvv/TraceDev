'''
A JPanel that displays the details of a specific disciplinary note.
It includes a "Delete" button for administrators to trigger the note deletion process.
This implements the core "DeleteNote" use case logic.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
/**
 * JPanel representing the screen for viewing and deleting a specific disciplinary note.
 * This is where the "DeleteNote" use case is initiated.
 */
class NoteDetailScreen extends JPanel {
    private MainApplication mainApp;
    private AuthService authService; // Added to check admin role
    private ArchiveService archiveService;
    private NotificationService notificationService;
    private SMOSServerConnection smosServerConnection;
    private DisciplinaryNote currentNote;
    private JLabel idLabel, studentIdLabel, parentContactLabel;
    private JTextArea noteDetailsArea;
    private JButton deleteButton;
    private JButton backButton;
    /**
     * Constructs a NoteDetailScreen.
     *
     * @param mainApp           The main application instance for screen navigation.
     * @param authService       The authentication service to check user role.
     * @param archiveService    The service to manage disciplinary notes.
     * @param notificationService The service to send notifications.
     * @param smosServerConnection The service to interact with the SMOS server.
     */
    public NoteDetailScreen(MainApplication mainApp, AuthService authService, ArchiveService archiveService,
                            NotificationService notificationService, SMOSServerConnection smosServerConnection) {
        this.mainApp = mainApp;
        this.authService = authService;
        this.archiveService = archiveService;
        this.notificationService = notificationService;
        this.smosServerConnection = smosServerConnection;
        initializeUI();
    }
    /**
     * Initializes the user interface components for the note detail screen.
     */
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        JLabel titleLabel = new JLabel("Note Details", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        // Note ID
        gbc.gridx = 0; gbc.gridy = 0;
        detailsPanel.add(new JLabel("Note ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        idLabel = new JLabel();
        detailsPanel.add(idLabel, gbc);
        // Student ID
        gbc.gridx = 0; gbc.gridy = 1;
        detailsPanel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        studentIdLabel = new JLabel();
        detailsPanel.add(studentIdLabel, gbc);
        // Parent Contact Info
        gbc.gridx = 0; gbc.gridy = 2;
        detailsPanel.add(new JLabel("Parent Contact:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        parentContactLabel = new JLabel();
        detailsPanel.add(parentContactLabel, gbc);
        // Note Details
        gbc.gridx = 0; gbc.gridy = 3;
        detailsPanel.add(new JLabel("Details:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        noteDetailsArea = new JTextArea(10, 30);
        noteDetailsArea.setEditable(false);
        noteDetailsArea.setLineWrap(true);
        noteDetailsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(noteDetailsArea);
        detailsPanel.add(scrollPane, gbc);
        add(detailsPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deleteButton = new JButton("Delete Note");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNote();
            }
        });
        buttonPanel.add(deleteButton);
        backButton = new JButton("Back to Registry");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showScreen(MainApplication.REGISTRY_SCREEN);
            }
        });
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Displays the details of a given disciplinary note on this screen.
     *
     * @param noteId The ID of the DisciplinaryNote to display.
     */
    public void displayNote(int noteId) {
        Optional<DisciplinaryNote> noteOptional = archiveService.getNoteById(noteId);
        if (noteOptional.isPresent()) {
            currentNote = noteOptional.get();
            idLabel.setText(String.valueOf(currentNote.getId()));
            studentIdLabel.setText(currentNote.getStudentId());
            parentContactLabel.setText(currentNote.getParentContactInfo());
            noteDetailsArea.setText(currentNote.getNoteDetails());
            // Only enable delete button if an administrator is logged in
            deleteButton.setEnabled(authService.isAdministrator());
        } else {
            // Handle case where note is not found (e.g., deleted by another admin)
            idLabel.setText("N/A");
            studentIdLabel.setText("N/A");
            parentContactLabel.setText("N/A");
            noteDetailsArea.setText("Note not found or already deleted.");
            deleteButton.setEnabled(false); // Disable delete if note not found
            currentNote = null;
            JOptionPane.showMessageDialog(this, "The selected note could not be found.", "Note Not Found", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Implements the core logic for deleting a disciplinary note.
     * This method follows the events sequence and postconditions outlined in the use case.
     */
    private void deleteNote() {
        if (currentNote == null) {
            JOptionPane.showMessageDialog(this, "No note selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Precondition check: "The user must be logged in to the system as an administrator"
        // This is handled by the login screen and button enable/disable state.
        if (!authService.isAdministrator()) {
            JOptionPane.showMessageDialog(this, "You must be logged in as an administrator to delete notes.", "Access Denied", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Precondition check: Administrator clicks the "Delete" button (handled by button action)
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this disciplinary note?\nThis action cannot be undone.",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (confirm == JOptionPane.YES_OPTION) {
            // Administrator confirms deletion - proceed with events sequence
            boolean notificationSuccess = true; // Flag to track notification status
            try {
                // 1. The system sends a notification of incorrect corrige to the student's parents
                boolean notificationSent = notificationService.sendNotification(
                        currentNote.getParentContactInfo(),
                        "Dear Parent(s),\n\nThis is an automated notification regarding a disciplinary note (" +
                                "ID: " + currentNote.getId() + ") for student " + currentNote.getStudentId() +
                                ". Please be aware that this note has been canceled from our records by an administrator. " +
                                "If you have any queries, please contact the school administration."
                );
                if (!notificationSent) {
                    notificationSuccess = false; // Mark notification as failed
                    // Display a warning but DO NOT return early. Proceed with deletion.
                    JOptionPane.showMessageDialog(this, "Warning: Failed to send notification to parents. Proceeding with note deletion locally.", "Notification Failed", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception notificationEx) {
                notificationSuccess = false; // Mark notification as failed due to an unexpected error
                JOptionPane.showMessageDialog(this, "Warning: An unexpected error occurred while attempting to send notification: " + notificationEx.getMessage() + ". Proceeding with note deletion locally.", "Notification Error", JOptionPane.WARNING_MESSAGE);
            }
            boolean smosConnectionEstablished = false;
            try {
                // Pre-step to deletion: Attempt to connect to SMOS server for synchronization if configured
                smosConnectionEstablished = smosServerConnection.connect();
                if (smosConnectionEstablished) {
                    boolean syncSuccess = smosServerConnection.syncNoteDeletion(currentNote.getId());
                    if (!syncSuccess) {
                        JOptionPane.showMessageDialog(this, "Warning: Failed to synchronize deletion with SMOS server. Local deletion will proceed, but remote might be inconsistent.", "SMOS Synchronization Warning", JOptionPane.WARNING_MESSAGE);
                        // This scenario or a connection failure implies "Connection to the SMOS server interrupted"
                    }
                } else {
                    // This block is now reachable if smosServerConnection.connect() returns false
                    JOptionPane.showMessageDialog(this, "Warning: Could not connect to SMOS server. Local deletion will proceed, but remote might be inconsistent.", "SMOS Connection Warning", JOptionPane.WARNING_MESSAGE);
                    // This outcome perfectly aligns with "Connection to the SMOS server interrupted"
                }
            } finally {
                // Ensure disconnection if a connection was successfully established.
                // If connection failed, there's nothing to disconnect.
                if (smosConnectionEstablished) {
                    smosServerConnection.disconnect();
                }
            }
            // 2. then eliminates the data of the note from the archive.
            boolean deletedFromArchive = archiveService.deleteNote(currentNote.getId());
            if (deletedFromArchive) {
                // Postconditions:
                // - The note has been canceled.
                // - The system sent notification to parents (status reflected in message).
                // - The system returns to the registry screen.
                StringBuilder finalMessage = new StringBuilder("Disciplinary note (ID: " + currentNote.getId() + ") deleted from archive.");
                if (notificationSuccess) {
                    finalMessage.append(" Parents notified.");
                } else {
                    finalMessage.append(" Notification to parents could not be completed.");
                }
                JOptionPane.showMessageDialog(this, finalMessage.toString(), "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
                mainApp.showScreen(MainApplication.REGISTRY_SCREEN);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete note from archive. It might have been deleted already or an unexpected error occurred.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
                // Stay on current screen as deletion failed.
            }
        } else {
            // Postcondition: The administrator interrupts the operation (by canceling the confirmation)
            JOptionPane.showMessageDialog(this, "Note deletion operation cancelled by administrator.", "Operation Interrupted", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}