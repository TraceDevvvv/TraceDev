'''
A JPanel that displays a list of disciplinary notes.
It allows an administrator to select a note and view its details.
This fulfills the "SHOWDDETTICLETA" use case precondition.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * JPanel representing the registry screen where administrators can view a list of disciplinary notes.
 * Fulfills the "SHOWDDETTICLETA" use case precondition.
 */
class RegistryScreen extends JPanel {
    private MainApplication mainApp;
    private ArchiveService archiveService;
    private JList<DisciplinaryNote> noteList;
    private DefaultListModel<DisciplinaryNote> listModel;
    private JButton viewDetailsButton;
    private JButton logoutButton;
    /**
     * Constructs a RegistryScreen.
     *
     * @param mainApp      The main application instance for screen navigation.
     * @param archiveService The service to retrieve disciplinary notes.
     */
    public RegistryScreen(MainApplication mainApp, ArchiveService archiveService) {
        this.mainApp = mainApp;
        this.archiveService = archiveService;
        initializeUI();
    }
    /**
     * Initializes the user interface components for the registry screen.
     */
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10)); // Add some padding
        JLabel titleLabel = new JLabel("Disciplinary Notes Registry", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        listModel = new DefaultListModel<>();
        noteList = new JList<>(listModel);
        noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        noteList.setFixedCellHeight(40); // Make rows a bit taller
        JScrollPane scrollPane = new JScrollPane(noteList);
        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        viewDetailsButton = new JButton("View Note Details");
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSelectedNoteDetails();
            }
        });
        buttonPanel.add(viewDetailsButton);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showScreen(MainApplication.LOGIN_SCREEN);
                JOptionPane.showMessageDialog(null, "Logged out successfully.", "Logout", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Refreshes the list of notes displayed on the screen by fetching them from the archive service.
     */
    public void refreshNotes() {
        listModel.clear();
        List<DisciplinaryNote> notes = archiveService.getAllNotes();
        for (DisciplinaryNote note : notes) {
            listModel.addElement(note);
        }
        revalidate(); // Re-layout the components if needed
        repaint();    // Redraw the components
    }
    /**
     * Handles the action when the "View Note Details" button is clicked.
     * Navigates to the NoteDetailScreen for the selected note.
     * Precondition: The user has carried out the case of use "SHOWDDETTICLETA" by selecting a note.
     */
    private void viewSelectedNoteDetails() {
        DisciplinaryNote selectedNote = noteList.getSelectedValue();
        if (selectedNote != null) {
            mainApp.showNoteDetails(selectedNote.getId());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a note to view its details.", "No Note Selected", JOptionPane.WARNING_MESSAGE);
        }
    }
}