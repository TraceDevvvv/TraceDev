'''
NoteDetailsApp.java
Main application window that simulates an administrator logged into the system.
It shows a mock list of notes and a "Details" button to view a selected note's details.
This simulates the pre-condition: the user has already performed "ViewElOnconte".
'''
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
/**
 * Represents a single note with student, teacher, description and date.
 * This is a simple data model class.
 */
class Note {
    private int id;
    private String student;
    private String description;
    private String teacher;
    private String date;
    // Constructor
    public Note(int id, String student, String description, String teacher, String date) {
        this.id = id;
        this.student = student;
        this.description = description;
        this.teacher = teacher;
        this.date = date;
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getStudent() {
        return student;
    }
    public String getDescription() {
        return description;
    }
    public String getTeacher() {
        return teacher;
    }
    public String getDate() {
        return date;
    }
    // Setters for completeness
    public void setStudent(String student) {
        this.student = student;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Note #" + id + ": " + student + " - " + 
               (description.length() > 30 ? description.substring(0, 30) + "..." : description);
    }
}
/**
 * Simulates a database of notes. In a real application this would connect to SMOS server.
 * Here we provide a mock implementation that returns a note by ID.
 */
class NoteDatabase {
    private Map<Integer, Note> noteMap;
    public NoteDatabase() {
        noteMap = new HashMap<>();
        // Seed with sample notes
        noteMap.put(1, new Note(1, "John Doe", "Completed assignment on time with exceptional quality.", "Dr. Smith", "2023-10-05"));
        noteMap.put(2, new Note(2, "Jane Smith", "Excellent participation in class discussions and group activities.", "Prof. Johnson", "2023-10-06"));
        noteMap.put(3, new Note(3, "Alice Brown", "Needs improvement in homework submission timeliness and accuracy.", "Dr. Williams", "2023-10-07"));
    }
    /**
     * Fetches a note by its ID.
     * Returns null if not found (simulating an edge case).
     */
    public Note getNoteById(int id) {
        return noteMap.get(id);
    }
    /**
     * Gets all notes from the database.
     * Used to populate the notes list in the main application.
     */
    public Note[] getAllNotes() {
        return noteMap.values().toArray(new Note[0]);
    }
}
/**
 * A dialog window that displays the details of a selected note.
 * It is shown when the administrator clicks the "Details" button.
 */
class NoteDetailsDialog extends JDialog {
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (!serverConnectionInterrupted) {
                    serverConnectionInterrupted = true;
                    System.out.println("SMOS server connection interrupted by administrator.");
                }
            }
        });
    }
}
/**
 * Main application class that demonstrates the ViewNoteDetails use case.
 * This class integrates all components into a single runnable program.
 */
public class NoteDetailsApp extends JFrame {
    private NoteDatabase database;
    private JList<Note> noteList;
    private DefaultListModel<Note> listModel;
    private boolean isAdministratorLoggedIn = true; // Simulates login state
    public NoteDetailsApp() {
        // Check pre-condition: user must be logged in as administrator
        if (!isAdministratorLoggedIn) {
            JOptionPane.showMessageDialog(null, "Access denied. Please login as administrator.", 
                    "Authentication Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        super("Administrator Dashboard - SMOS");
        database = new NoteDatabase();
        // Simulate administrator login (pre-condition satisfied)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        // Header
        JLabel header = new JLabel("Administrator: View Note Details", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        add(header, BorderLayout.NORTH);
        // Center panel with a list of notes
        listModel = new DefaultListModel<>();
        // Populate list with notes from database (simulating the result of "ViewElOnconte")
        for (Note note : database.getAllNotes()) {
            if (note != null) {
                listModel.addElement(note);
            }
        }
        noteList = new JList<>(listModel);
        noteList.setCellRenderer(new NoteListRenderer());
        noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(noteList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Notes List (click Details for more)"));
        add(scrollPane, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton detailsButton = new JButton("View Details");
        JButton refreshButton = new JButton("Refresh List");
        JButton logoutButton = new JButton("Logout");
        buttonPanel.add(detailsButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // Action for Details button (Event sequence step 1)
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Note selectedNote = noteList.getSelectedValue();
                if (selectedNote == null) {
                    JOptionPane.showMessageDialog(NoteDetailsApp.this,
                            "Please select a note first.",
                            "No Selection",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    // Open the details dialog to show form with note details
                    NoteDetailsDialog dialog = new NoteDetailsDialog(NoteDetailsApp.this, selectedNote);
                    dialog.setVisible(true);
                }
            }
        });
        // Action for Refresh button
        refreshButton.addActionListener(e -> {
            noteList.clearSelection();
            listModel.clear();
            // Re-populate list
            for (Note note : database.getAllNotes()) {
                if (note != null) {
                    listModel.addElement(note);
                }
            }
            JOptionPane.showMessageDialog(this, "List refreshed successfully.");
        });
        // Action for Logout button (simulates ending the session)
        logoutButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to logout?", 
                    "Confirm Logout", 
                    JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                isAdministratorLoggedIn = false;
                JOptionPane.showMessageDialog(this, "Logged out successfully.");
                System.exit(0);
            }
        });
        // Add double-click functionality to view details
        noteList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Note selectedNote = noteList.getSelectedValue();
                    if (selectedNote != null) {
                        NoteDetailsDialog dialog = new NoteDetailsDialog(NoteDetailsApp.this, selectedNote);
                        dialog.setVisible(true);
                    }
                }
            }
        });
        setSize(600, 450);
        setLocationRelativeTo(null); // Center on screen
        setMinimumSize(new Dimension(500, 400));
    }
    /**
     * Custom list cell renderer to show note summary in the JList.
     */
    private static class NoteListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Note) {
                Note note = (Note) value;
                setText(note.toString());
            }
            return this;
        }
    }
    // Main method
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            NoteDetailsApp app = new NoteDetailsApp();
            app.setVisible(true);
        });
    }
}