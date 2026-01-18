/*
The main application class that sets up the GUI for managing and editing disciplinary notes.
It provides a registry screen to view notes and an edit screen to modify selected notes.
It handles user interactions, navigates between screens, and communicates with the NoteService for data operations.
Preconditions and postconditions from the use case are addressed through UI flow and error handling.
*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
/**
 * Main application class for the EditNote use case.
 * Provides a GUI for viewing and editing disciplinary notes for an administrator.
 */
public class EditNoteApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel; // Panel to hold different views (registry, edit form)
    private NoteService noteService;
    private boolean loggedInAsAdmin = true; // Precondition: User must be logged in as administrator
    // GUI Components for Registry Screen
    private JPanel registryPanel;
    private JTable notesTable;
    private DefaultTableModel tableModel;
    private JButton editButton;
    private JButton exitButton;
    // GUI Components for Edit Note Screen
    private JPanel editScreenPanel; // Declared here
    private EditNoteForm editNoteForm;
    private DisciplinaryNote currentNoteForEditing; // The note currently being edited
    private JButton saveButton;
    private JButton cancelButton;
    // Date format for display in table
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * Constructor for EditNoteApp.
     * Initializes the NoteService, GUI components, and sets up the main frame.
     */
    public EditNoteApp() {
        super("Disciplinary Note Management - Administrator");
        noteService = new NoteService();
        // Check pre-condition: Administrator login
        if (!isAdminLoggedIn()) {
            JOptionPane.showMessageDialog(this, "Access Denied. Only administrators can use this application.", "Authentication Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Exit if not authenticated
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
        // Initialize and add the registry screen
        initializeRegistryScreen();
        mainPanel.add(registryPanel, "Registry");
        // Initialize and add the edit screen panel as a card
        initializeEditScreen(); // Call the initialization method for the edit screen
        mainPanel.add(editScreenPanel, "EditNote"); // Add the edit screen panel as a card here
        showRegistryScreen(); // Start by showing the registry screen
    }
    /**
     * Checks if the user is logged in as an administrator.
     * For this example, it's hardcoded to true based on the precondition.
     * In a real application, this would involve actual authentication logic.
     *
     * @return true if the user is an administrator, false otherwise.
     */
    private boolean isAdminLoggedIn() {
        // In a real system, this would involve checking user roles from a session or database.
        // For this use case, we assume the application is launched by an authenticated admin.
        return loggedInAsAdmin;
    }
    /**
     * Initializes the components for the "Registry" screen, which displays a table of notes.
     */
    private void initializeRegistryScreen() {
        registryPanel = new JPanel(new BorderLayout(10, 10));
        registryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Table setup
        String[] columnNames = {"ID", "Student Name", "Teacher Name", "Date", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Cells are not editable directly on the table
            }
        };
        notesTable = new JTable(tableModel);
        notesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row can be selected at a time
        notesTable.getTableHeader().setReorderingAllowed(false); // Prevent column reordering
        JScrollPane scrollPane = new JScrollPane(notesTable);
        registryPanel.add(scrollPane, BorderLayout.CENTER);
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        editButton = new JButton("Edit Selected Note");
        exitButton = new JButton("Exit Application");
        buttonPanel.add(editButton);
        buttonPanel.add(exitButton);
        registryPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Action Listeners
        editButton.addActionListener(e -> {
            int selectedRow = notesTable.getSelectedRow();
            if (selectedRow != -1) {
                int noteId = (int) tableModel.getValueAt(selectedRow, 0); // Get ID from the first column
                Optional<DisciplinaryNote> noteToEdit = noteService.getNoteById(noteId);
                noteToEdit.ifPresent(this::showEditNoteScreen);
            } else {
                JOptionPane.showMessageDialog(registryPanel, "Please select a note to edit.", "No Note Selected", JOptionPane.WARNING_MESSAGE);
            }
        });
        exitButton.addActionListener(e -> System.exit(0));
    }
    /**
     * Initializes the "Edit Note" screen panel and its fixed components like buttons.
     * The `EditNoteForm` itself is created dynamically when `showEditNoteScreen` is called.
     */
    private void initializeEditScreen() {
        editScreenPanel = new JPanel(new BorderLayout(10, 10)); // Initialize editScreenPanel here
        editScreenPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Create and add the button panel to the editScreenPanel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Save Changes");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        editScreenPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Action listeners for save and cancel
        saveButton.addActionListener(e -> saveEditedNote());
        cancelButton.addActionListener(e -> {
            // Postcondition: The administrator interrupts the operation.
            // Action: Return to the registry screen without saving.
            currentNoteForEditing = null; // Clear the reference to the note being edited
            showRegistryScreen();
            JOptionPane.showMessageDialog(this, "Editing operation cancelled.", "Operation Interrupted", JOptionPane.INFORMATION_MESSAGE);
        });
    }
    /**
     * Populates the notes table with current data from the NoteService.
     */
    private void refreshNotesTable() {
        tableModel.setRowCount(0); // Clear existing rows
        for (DisciplinaryNote note : noteService.getAllNotes()) {
            tableModel.addRow(new Object[]{
                    note.getId(),
                    note.getStudentName(),
                    note.getTeacherName(),
                    note.getNoteDate().format(DISPLAY_DATE_FORMATTER),
                    note.getDescription()
            });
        }
    }
    /**
     * Displays the "Registry" screen with the list of notes.
     * This is typically shown after application launch or after editing/canceling an edit.
     * Postcondition: The system returns to the registry screen.
     */
    private void showRegistryScreen() {
        refreshNotesTable(); // Ensure the table is up-to-date
        cardLayout.show(mainPanel, "Registry");
        setTitle("Disciplinary Note Management - Registry");
        // Remove the edit form if it was previously added to the editScreenPanel,
        // to ensure a clean slate when creating a new EditNoteForm for the next edit session.
        if (editNoteForm != null && editScreenPanel != null) {
            editScreenPanel.remove(editNoteForm);
            editNoteForm = null;
            editScreenPanel.revalidate(); // Revalidate after removal
            editScreenPanel.repaint();    // Repaint after removal
        }
    }
    /**
     * Displays the "Edit Note" screen for a specific `DisciplinaryNote`.
     * Precondition: The user has seen the "view details" (implied by selecting from registry).
     *
     * @param noteToEdit The DisciplinaryNote object to be displayed and edited.
     */
    private void showEditNoteScreen(DisciplinaryNote noteToEdit) {
        if (noteToEdit == null) {
            JOptionPane.showMessageDialog(this, "Error: No note provided for editing.", "Error", JOptionPane.ERROR_MESSAGE);
            showRegistryScreen();
            return;
        }
        this.currentNoteForEditing = noteToEdit;
        // Remove any previous EditNoteForm from the center of editScreenPanel
        if (editNoteForm != null) {
            editScreenPanel.remove(editNoteForm);
            // Ensure the UI is updated immediately after removal
            editScreenPanel.revalidate();
            editScreenPanel.repaint();
        }
        editNoteForm = new EditNoteForm(noteToEdit); // Create a new form instance
        editScreenPanel.add(editNoteForm, BorderLayout.CENTER); // Add the new form to the center
        // Revalidate and repaint the editScreenPanel to ensure the new form is displayed correctly
        editScreenPanel.revalidate();
        editScreenPanel.repaint();
        // Now, just show the registered "EditNote" card
        cardLayout.show(mainPanel, "EditNote");
        setTitle("Disciplinary Note Management - Edit Note (ID: " + noteToEdit.getId() + ")");
    }
    /**
     * Handles the saving of an edited note.
     * This method retrieves data from the EditNoteForm, validates it, and attempts to update the note
     * via the NoteService. It also handles potential server interruptions.
     *
     * To prevent UI freezing, the long-running `noteService.updateNote()` call is executed
     * on a background thread using `SwingWorker`.
     * Postcondition: Note data has been modified.
     */
    private void saveEditedNote() {
        if (editNoteForm == null || currentNoteForEditing == null) {
            JOptionPane.showMessageDialog(this, "Error: No note form or note selected for saving.", "Error", JOptionPane.ERROR_MESSAGE);
            showRegistryScreen();
            return;
        }
        final DisciplinaryNote updatedNoteData = editNoteForm.getUpdatedNoteData();
        if (updatedNoteData == null) {
            // Validation failed, message already shown by EditNoteForm.getUpdatedNoteData()
            return;
        }
        // Disable buttons to prevent multiple save operations or cancellation during processing
        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
        // Use SwingWorker to perform the potentially long-running operation in a background thread
        new SwingWorker<Boolean, Void>() {
            private Exception caughtException = null; // To store any exception from the background thread
            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    // This code runs on a background thread
                    return noteService.updateNote(updatedNoteData);
                } catch (NoteService.ServerInterruptionException e) {
                    caughtException = e; // Store specific server interruption exception
                    return false; // Indicate failure
                } catch (Exception e) {
                    caughtException = e; // Store any other unexpected exception
                    return false; // Indicate failure
                }
            }
            @Override
            protected void done() {
                // This code runs back on the Event Dispatch Thread (EDT)
                // Re-enable buttons regardless of the outcome
                saveButton.setEnabled(true);
                cancelButton.setEnabled(true);
                try {
                    if (caughtException != null) {
                        // Handle server interruption or other exceptions caught in doInBackground()
                        // Postcondition: Connection to the SMOS server interrupted.
                        JOptionPane.showMessageDialog(EditNoteApp.this, caughtException.getMessage(), "Server Error", JOptionPane.ERROR_MESSAGE);
                        // Remain on the edit screen to allow the user to retry or cancel.
                    } else {
                        // Operation completed without exception in doInBackground()
                        boolean success = get(); // Get the result (true/false for update success)
                        if (success) {
                            JOptionPane.showMessageDialog(EditNoteApp.this, "Note data has been successfully modified.", "Save Success", JOptionPane.INFORMATION_MESSAGE);
                            // Postcondition: The system returns to the registry screen.
                            currentNoteForEditing = null; // Clear the reference to the note being edited
                            showRegistryScreen();
                        } else {
                            JOptionPane.showMessageDialog(EditNoteApp.this, "Failed to find original note with ID " + updatedNoteData.getId() + ". It might have been deleted.", "Save Failed", JOptionPane.ERROR_MESSAGE);
                            // Stay on the edit screen, or navigate back to registry. For now, we
                            // will assume an error is unexpected and show registry.
                            currentNoteForEditing = null;
                            showRegistryScreen();
                        }
                    }
                } catch (InterruptedException | ExecutionException e) {
                    // Handle exceptions that might occur if this thread is interrupted
                    // or if doInBackground() threw an unchecked exception.
                    JOptionPane.showMessageDialog(EditNoteApp.this, "An error occurred during save operation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    // Decide whether to stay on edit screen or return to registry.
                    // For now, return to registry.
                    currentNoteForEditing = null;
                    showRegistryScreen();
                }
            }
        }.execute(); // Start the SwingWorker, which will execute doInBackground()
    }
    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            EditNoteApp app = new EditNoteApp();
            app.setVisible(true);
        });
    }
}