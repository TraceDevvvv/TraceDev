/**
 * Main application class for the Note Viewer, providing a GUI for administrators
 * to view student notes.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class NoteViewerApp extends JFrame {
    private JComboBox<Student> studentComboBox;
    private JTextArea notesDisplayArea;
    /**
     * Constructs the NoteViewerApp GUI.
     */
    public NoteViewerApp() {
        // --- Frame Setup ---
        super("Administrator - Student Note Viewer"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(600, 400); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        // --- Panel for overall layout ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10)); // Add some padding
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Outer padding
        // --- Top Panel for Student Selection ---
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        selectionPanel.add(new JLabel("Select Student:"));
        // Populate student combo box from DataStore
        List<Student> students = DataStore.getAllStudents();
        studentComboBox = new JComboBox<>(students.toArray(new Student[0]));
        // The custom renderer is unnecessary because Student.toString() already provides the desired display.
        // studentComboBox.setRenderer(new DefaultListCellRenderer() {
        //     @Override
        //     public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        //         super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        //         if (value instanceof Student) {
        //             // Display student's name in the combo box
        //             setText(((Student) value).getName());
        //         }
        //         return this;
        //     }
        // });
        selectionPanel.add(studentComboBox);
        // Add a button to explicitly view notes if selection doesn't auto-update
        JButton viewNotesButton = new JButton("View Notes");
        selectionPanel.add(viewNotesButton);
        mainPanel.add(selectionPanel, BorderLayout.NORTH);
        // --- Center Panel for Notes Display ---
        notesDisplayArea = new JTextArea();
        notesDisplayArea.setEditable(false); // Notes should not be editable by the administrator
        notesDisplayArea.setLineWrap(true);   // Wrap text within the display area
        notesDisplayArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(notesDisplayArea); // Make notes scrollable
        notesDisplayArea.setBorder(BorderFactory.createTitledBorder("Student Notes")); // Add a title border
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // --- Event Handling ---
        // Action listener for the view notes button
        viewNotesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySelectedStudentNotes();
            }
        });
        // Action listener for changes in the combo box selection
        studentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Automatically display notes when a new student is selected
                displaySelectedStudentNotes();
            }
        });
        // Add main panel to the frame
        add(mainPanel);
        // Make the frame visible
        setVisible(true);
        // Display notes for the initially selected student (if any)
        if (studentComboBox.getItemCount() > 0) {
            displaySelectedStudentNotes();
        }
    }
    /**
     * Retrieves and displays the notes for the currently selected student.
     * Handles cases where no student is selected or no notes are found.
     */
    private void displaySelectedStudentNotes() {
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        if (selectedStudent == null) {
            notesDisplayArea.setText("Please select a student to view notes.");
            return;
        }
        List<Note> notes = DataStore.getNotesByStudentId(selectedStudent.getId());
        if (notes.isEmpty()) {
            notesDisplayArea.setText("No notes found for " + selectedStudent.getName() + " for this school year.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Notes for ").append(selectedStudent.getName()).append(":\n\n");
            for (Note note : notes) {
                sb.append(note.toString()).append("\n"); // Append each note's string representation
            }
            notesDisplayArea.setText(sb.toString().trim()); // Set text and trim any trailing newlines
            notesDisplayArea.setCaretPosition(0); // Scroll to the top
        }
    }
    /**
     * Main method to run the application.
     * Includes a mock administrator login to satisfy the use case precondition.
     * Creates and displays the GUI on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // --- Mock Administrator Login ---
        // This simulates the precondition: "The user must be logged in to the system as an administrator"
        // In a real system, this would involve proper credential validation (e.g., username/password check).
        String password = JOptionPane.showInputDialog(null, "Enter Administrator Password:", "Administrator Login", JOptionPane.PLAIN_MESSAGE);
        // For this example, any non-null and non-empty password is considered valid.
        if (password == null || password.trim().isEmpty()) { // User clicked cancel or entered empty
            JOptionPane.showMessageDialog(null, "Administrator login required to proceed.", "Access Denied", JOptionPane.ERROR_MESSAGE);
            System.exit(0); // Exit if not logged in
            return;
        }
        // Display a success message (optional)
        // JOptionPane.showMessageDialog(null, "Welcome, Administrator!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create an instance of our application frame
                new NoteViewerApp();
            }
        });
    }
}