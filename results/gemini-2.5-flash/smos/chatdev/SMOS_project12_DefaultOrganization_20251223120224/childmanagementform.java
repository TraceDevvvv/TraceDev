'''
This class represents the main GUI form for managing student associations for a parent.
It allows an administrator to assign new students to a parent or remove previously
associated students from a parent.
'''
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class ChildManagementForm extends JFrame {
    private Parent currentParent; // The parent whose children are being managed
    private ParentManagementService service;
    private JList<Student> availableStudentsList;
    private DefaultListModel<Student> availableStudentsModel;
    private JList<Student> assignedStudentsList;
    private DefaultListModel<Student> assignedStudentsModel;
    private JButton assignButton;
    private JButton removeButton;
    private JButton sendButton;
    /**
     * Constructs a ChildManagementForm for a given parent.
     * @param parent The parent whose student associations will be managed.
     */
    public ChildManagementForm(Parent parent) {
        this.currentParent = parent;
        this.service = new ParentManagementService();
        setTitle("Manage Children for " + parent.getName());
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close this window and terminate the application
        setLocationRelativeTo(null); // Center the window
        initComponents();
        layoutComponents();
        loadStudentData(); // Populate lists with initial data
    }
    /**
     * Initializes the GUI components for the form.
     */
    private void initComponents() {
        availableStudentsModel = new DefaultListModel<>();
        availableStudentsList = new JList<>(availableStudentsModel);
        availableStudentsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        availableStudentsList.setBorder(BorderFactory.createTitledBorder("Available Students"));
        assignedStudentsModel = new DefaultListModel<>();
        assignedStudentsList = new JList<>(assignedStudentsModel);
        assignedStudentsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        assignedStudentsList.setBorder(BorderFactory.createTitledBorder("Assigned Students"));
        assignButton = new JButton("Assign ->");
        removeButton = new JButton("<- Remove");
        sendButton = new JButton("Send (Save Changes)");
        // Initially disable assign/remove buttons until selections are made
        assignButton.setEnabled(false);
        removeButton.setEnabled(false);
        // Add listeners for enabling/disabling buttons based on list selections
        availableStudentsList.addListSelectionListener(e -> assignButton.setEnabled(!availableStudentsList.isSelectionEmpty()));
        assignedStudentsList.addListSelectionListener(e -> removeButton.setEnabled(!assignedStudentsList.isSelectionEmpty()));
        // Action listeners for buttons
        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignSelectedStudents();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedStudents();
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });
    }
    /**
     * Lays out the components within the frame using BorderLayout and BoxLayout.
     */
    private void layoutComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Center Panel for student lists and buttons
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.45; // Weight for lists
        // Available Students List
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2; // Span two rows for list
        centerPanel.add(new JScrollPane(availableStudentsList), gbc);
        // Buttons in the middle
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(assignButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons
        buttonPanel.add(removeButton);
        buttonPanel.add(Box.createVerticalGlue());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 10;
        gbc.weightx = 0.1; // Smaller weight for buttons column
        centerPanel.add(buttonPanel, gbc);
        // Assigned Students List
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0.45; // Weight for lists
        centerPanel.add(new JScrollPane(assignedStudentsList), gbc);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        // Bottom Panel for Send button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(sendButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Loads initial student data into the available and assigned lists.
     * This method fetches data from the ParentManagementService.
     */
    private void loadStudentData() {
        // Clear existing data
        availableStudentsModel.clear();
        assignedStudentsModel.clear();
        // Get students currently associated with the parent
        List<Student> assigned = service.getStudentsForParent(currentParent);
        for (Student student : assigned) {
            assignedStudentsModel.addElement(student);
        }
        // Get students not associated with the parent
        List<Student> unassociated = service.getUnassociatedStudentsForParent(currentParent);
        for (Student student : unassociated) {
            availableStudentsModel.addElement(student);
        }
    }
    /**
     * Moves selected students from the 'Available Students' list to the 'Assigned Students' list.
     */
    @SuppressWarnings("unchecked") // Safe due to generic type JList<Student>
    private void assignSelectedStudents() {
        List<Student> studentsToAssign = availableStudentsList.getSelectedValuesList();
        if (studentsToAssign.isEmpty()) {
            return; // No students selected
        }
        for (Student student : studentsToAssign) {
            assignedStudentsModel.addElement(student);
            availableStudentsModel.removeElement(student);
        }
        // Ensure buttons are reset after moving elements
        assignButton.setEnabled(false);
        removeButton.setEnabled(false);
    }
    /**
     * Moves selected students from the 'Assigned Students' list to the 'Available Students' list.
     */
    @SuppressWarnings("unchecked") // Safe due to generic type JList<Student>
    private void removeSelectedStudents() {
        List<Student> studentsToRemove = assignedStudentsList.getSelectedValuesList();
        if (studentsToRemove.isEmpty()) {
            return; // No students selected
        }
        for (Student student : studentsToRemove) {
            availableStudentsModel.addElement(student);
            assignedStudentsModel.removeElement(student);
        }
        // Ensure buttons are reset after moving elements
        assignButton.setEnabled(false);
        removeButton.setEnabled(false);
    }
    /**
     * Saves the current assignments to the ParentManagementService.
     * This corresponds to the user clicking the "Send" button.
     */
    private void saveChanges() {
        // Collect all students from the assignedStudentsModel
        List<Student> finalAssignedStudents = new ArrayList<>();
        for (int i = 0; i < assignedStudentsModel.size(); i++) {
            finalAssignedStudents.add(assignedStudentsModel.getElementAt(i));
        }
        // Update the parent's association via the service layer
        service.updateParentStudents(currentParent, finalAssignedStudents);
        // Postcondition: One or more children to a parent were associated or removed.
        JOptionPane.showMessageDialog(this,
                "Student associations for " + currentParent.getName() + " updated successfully!",
                "Update Successful",
                JOptionPane.INFORMATION_MESSAGE);
        // Postcondition: The administrator interrupts the connection to the SMOS server interrupted
        // This is a simulated action. In a real app, this might be a logout or specific session end.
        // For this use case, we'll assume "Send" completes the task and the administrator can "disconnect"
        service.interruptSMOSConnection(); // This method now logs instead of exiting the application.
        // As the task is complete, close this form to return to the previous view or main window.
        dispose();
    }
}