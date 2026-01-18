'''
Main frame for viewing student notes.
This is where the "View Notes List" use case is implemented.
'''
package studentnotessystem;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
public class ViewNotesFrame extends JFrame {
    private JTable notesTable;
    private NotesTableModel tableModel;
    private JButton refreshButton;
    private JButton disconnectButton;
    private JComboBox<Student> studentFilterCombo;
    private JLabel statusLabel;
    private DatabaseSimulator database;
    public ViewNotesFrame() {
        database = DatabaseSimulator.getInstance();
        initComponents();
        setupFrame();
        layoutComponents();
        setupListeners();
        loadNotesData();
        this.setVisible(true);
    }
    private void setupFrame() {
        this.setTitle("Student Notes System - Administrator View");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        // Handle window closing to ensure clean disconnection
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disconnectFromServer();
            }
        });
    }
    private void initComponents() {
        refreshButton = new JButton("Refresh Notes");
        disconnectButton = new JButton("Disconnect from Server");
        statusLabel = new JLabel("Status: Connected | Total Notes: 0");
        // Create table with sample data initially
        tableModel = new NotesTableModel(database.getAllNotes());
        notesTable = new JTable(tableModel);
        // Set up table properties
        notesTable.setRowHeight(25);
        notesTable.getColumnModel().getColumn(6).setPreferredWidth(300); // Content column wider
        // Center-align all columns except content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < notesTable.getColumnCount() - 1; i++) {
            notesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        // Left-align content column
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        notesTable.getColumnModel().getColumn(6).setCellRenderer(leftRenderer);
        // Create filter combobox using Student objects
        studentFilterCombo = new JComboBox<>();
        studentFilterCombo.addItem(null); // null item represents "All Students"
        for (Student s : database.getAllStudents()) {
            studentFilterCombo.addItem(s);
        }
        // Custom renderer to display student information
        studentFilterCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null) {
                    setText("All Students");
                } else if (value instanceof Student) {
                    Student s = (Student) value;
                    setText(s.getStudentId() + " - " + s.getFirstName() + " " + s.getLastName());
                }
                return this;
            }
        });
    }
    private void layoutComponents() {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(new JLabel("Student: "));
        headerPanel.add(studentFilterCombo);
        headerPanel.add(Box.createHorizontalStrut(20));
        headerPanel.add(refreshButton);
        headerPanel.add(disconnectButton);
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JScrollPane(notesTable), BorderLayout.CENTER);
        contentPanel.add(statusLabel, BorderLayout.SOUTH);
        this.setLayout(new BorderLayout());
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
    }
    private void setupListeners() {
        refreshButton.addActionListener(e -> refreshNotes());
        disconnectButton.addActionListener(e -> disconnectFromServer());
        studentFilterCombo.addActionListener(e -> {
            Student selectedStudent = (Student) studentFilterCombo.getSelectedItem();
            if (selectedStudent == null) {
                displayAllNotes();
            } else {
                displayNotesForStudent(selectedStudent.getStudentId());
            }
        });
    }
    /**
     * Load all notes from the database and display them
     */
    private void loadNotesData() {
        List<Note> allNotes = database.getAllNotes();
        tableModel.setNotes(allNotes);
        statusLabel.setText("Status: Connected | Total Notes: " + allNotes.size());
        notesTable.repaint();
    }
    /**
     * Refresh the notes list from the database
     */
    private void refreshNotes() {
        // In a real application, this would re-query the database
        loadNotesData();
        JOptionPane.showMessageDialog(this, 
            "Notes list refreshed from database.", 
            "Refresh Complete", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Display notes for a specific student
     */
    private void displayNotesForStudent(String studentId) {
        List<Note> studentNotes = database.getNotesByStudentId(studentId);
        tableModel.setNotes(studentNotes);
        statusLabel.setText("Status: Connected | Notes for Student ID " + studentId + ": " + studentNotes.size());
        notesTable.repaint();
    }
    /**
     * Display all notes
     */
    private void displayAllNotes() {
        List<Note> allNotes = database.getAllNotes();
        tableModel.setNotes(allNotes);
        statusLabel.setText("Status: Connected | Total Notes: " + allNotes.size());
        notesTable.repaint();
    }
    /**
     * Disconnect from the server and close the application
     */
    private void disconnectFromServer() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to disconnect from the SMOS server?", 
            "Confirm Disconnection", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            database.disconnect();
            JOptionPane.showMessageDialog(this,
                "Successfully disconnected from SMOS server. Application will now close.",
                "Disconnected",
                JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}