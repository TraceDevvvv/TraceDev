'''
Main entry point for the Student-Parent Assignment application.
This GUI application allows an administrator to assign or remove students
to/from a selected parent. It simulates the use case "Assign/RemoveStudentsToAParent".
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        // Run GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
/**
 * LoginFrame: Simulates administrator login.
 * For simplicity, any username/password is accepted.
 */
class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Administrator Login - SMOS");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField();
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // In a real application, validate credentials here.
                // For this demo, any input is accepted.
                String username = userText.getText();
                char[] password = passText.getPassword();
                if (username.isEmpty() || password.length == 0) {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Please enter both username and password.",
                            "Login Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Simulate successful login.
                    dispose();
                    // Open the parent selection frame (simulating "viewdetTailsente").
                    ParentSelectionFrame parentSelectionFrame = new ParentSelectionFrame();
                    parentSelectionFrame.setVisible(true);
                }
            }
        });
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passLabel);
        panel.add(passText);
        panel.add(new JLabel()); // placeholder
        panel.add(loginButton);
        add(panel);
    }
}
/**
 * ParentSelectionFrame: Simulates the step after login where administrator
 * selects a parent to view details (use case "viewdetTailsente").
 * In this demo, we list a few hard‑coded parents.
 */
class ParentSelectionFrame extends JFrame {
    private java.util.List<Parent> parents;
    private JComboBox<Parent> parentComboBox;
    private JButton viewDetailsButton;
    private JButton logoutButton;
    public ParentSelectionFrame() {
        setTitle("Select Parent - SMOS");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        parents = new ArrayList<>();
        parents.add(new Parent(1, "John Doe", "john@example.com"));
        parents.add(new Parent(2, "Jane Smith", "jane@example.com"));
        parents.add(new Parent(3, "Robert Johnson", "robert@example.com"));
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel parentLabel = new JLabel("Select Parent:");
        parentComboBox = new JComboBox<>();
        for (Parent p : parents) {
            parentComboBox.addItem(p);
        }
        viewDetailsButton = new JButton("View Details");
        logoutButton = new JButton("Logout");
        viewDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Parent selectedParent = (Parent) parentComboBox.getSelectedItem();
                if (selectedParent != null) {
                    // Simulate viewing parent details.
                    // In the use case, after viewing details, the administrator
                    // clicks a "Parentela" button to open the assignment form.
                    // For simplicity we directly open the assignment form.
                    dispose();
                    ParentAssignmentFrame assignmentFrame = new ParentAssignmentFrame(selectedParent);
                    assignmentFrame.setVisible(true);
                }
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Simulate logout and server disconnection (postcondition).
                System.out.println("Administrator interrupted connection to SMOS server.");
                System.exit(0);
            }
        });
        panel.add(parentLabel);
        panel.add(parentComboBox);
        panel.add(viewDetailsButton);
        panel.add(logoutButton);
        add(panel);
        // Add keyboard shortcuts
        KeyStroke escKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escKey, "logout");
        panel.getActionMap().put("logout", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                logoutButton.doClick();
            }
        });
    }
}
/**
 * ParentAssignmentFrame: The main form where administrator can assign or remove
 * students to/from the selected parent.
 * This corresponds to step 1 of the system sequence.
 */
class ParentAssignmentFrame extends JFrame {
    private Parent parent;
    private java.util.List<Student> allStudents;
    private JList<Student> availableStudentsList;
    private JList<Student> assignedStudentsList;
    private DefaultListModel<Student> availableModel;
    private DefaultListModel<Student> assignedModel;
    private java.util.Set<Student> assignedStudents;
    private JButton assignButton;
    private JButton removeButton;
    private JButton sendButton;
    private JButton backButton;
    public ParentAssignmentFrame(Parent parent) {
        this.parent = parent;
        this.assignedStudents = new HashSet<>();
        setTitle("Assign/Remove Students for " + parent.getName() + " - SMOS");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        // Create sample data
        initializeStudents();
        // Create UI components
        createUIComponents();
        // Set up layout
        setupLayout();
    }
    /**
     * Initialize the list of all students and their initial assignments.
     * In a real system, this would load from a database.
     */
    private void initializeStudents() {
        allStudents = new ArrayList<>();
        allStudents.add(new Student(101, "Alice Brown", 10));
        allStudents.add(new Student(102, "Bob Green", 11));
        allStudents.add(new Student(103, "Charlie White", 10));
        allStudents.add(new Student(104, "Diana Black", 12));
        allStudents.add(new Student(105, "Eve Gray", 11));
        // In a real application, load existing assignments here
        // For demo, we'll assign one student initially to show functionality
        Student initialAssigned = allStudents.get(0);
        assignedStudents.add(initialAssigned);
    }
    /**
     * Create all UI components for the assignment frame.
     */
    private void createUIComponents() {
        // Create list models
        availableModel = new DefaultListModel<>();
        assignedModel = new DefaultListModel<>();
        // Populate lists based on assignment status
        for (Student s : allStudents) {
            if (assignedStudents.contains(s)) {
                assignedModel.addElement(s);
            } else {
                availableModel.addElement(s);
            }
        }
        // Create student lists
        availableStudentsList = new JList<>(availableModel);
        availableStudentsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        availableStudentsList.setToolTipText("Select students to assign to parent");
        assignedStudentsList = new JList<>(assignedModel);
        assignedStudentsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        assignedStudentsList.setToolTipText("Select students to remove from parent");
        // Create action buttons with keyboard shortcuts
        assignButton = new JButton(">> Assign >>");
        assignButton.setMnemonic(KeyEvent.VK_A);
        assignButton.setToolTipText("Assign selected students (Alt+A)");
        removeButton = new JButton("<< Remove <<");
        removeButton.setMnemonic(KeyEvent.VK_R);
        removeButton.setToolTipText("Remove selected students (Alt+R)");
        sendButton = new JButton("Send");
        sendButton.setMnemonic(KeyEvent.VK_S);
        sendButton.setToolTipText("Save assignments and exit (Alt+S)");
        backButton = new JButton("Back");
        backButton.setMnemonic(KeyEvent.VK_B);
        backButton.setToolTipText("Go back to parent selection (Alt+B)");
        // Add button actions
        setupButtonActions();
    }
    /**
     * Set up the actions for all buttons.
     */
    private void setupButtonActions() {
        assignButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                java.util.List<Student> selected = availableStudentsList.getSelectedValuesList();
                if (!selected.isEmpty()) {
                    assignStudents(selected);
                    // Clear selection after assignment
                    availableStudentsList.clearSelection();
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                java.util.List<Student> selected = assignedStudentsList.getSelectedValuesList();
                if (!selected.isEmpty()) {
                    removeStudents(selected);
                    // Clear selection after removal
                    assignedStudentsList.clearSelection();
                }
            }
        });
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAssignments();
                // Terminate application as per postcondition
                System.out.println("Administrator interrupted connection to SMOS server.");
                System.exit(0);
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ParentSelectionFrame parentSelectionFrame = new ParentSelectionFrame();
                parentSelectionFrame.setVisible(true);
            }
        });
        // Double-click support for quick assignment/removal
        availableStudentsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = availableStudentsList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        Student selected = availableModel.getElementAt(index);
                        assignStudents(java.util.Arrays.asList(selected));
                    }
                }
            }
        });
        assignedStudentsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = assignedStudentsList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        Student selected = assignedModel.getElementAt(index);
                        removeStudents(java.util.Arrays.asList(selected));
                    }
                }
            }
        });
    }
    /**
     * Assign selected students to the parent.
     * @param students List of students to assign
     */
    private void assignStudents(java.util.List<Student> students) {
        for (Student s : students) {
            if (!assignedStudents.contains(s)) {
                assignedStudents.add(s);
                availableModel.removeElement(s);
                assignedModel.addElement(s);
                System.out.println("Assigned student: " + s.getName() + " to parent " + parent.getName());
            }
        }
        if (!students.isEmpty()) {
            String message;
            if (students.size() == 1) {
                message = "1 student assigned to parent " + parent.getName() + ".";
            } else {
                message = students.size() + " students assigned to parent " + parent.getName() + ".";
            }
            JOptionPane.showMessageDialog(ParentAssignmentFrame.this,
                    message,
                    "Assignment Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Remove selected students from the parent.
     * @param students List of students to remove
     */
    private void removeStudents(java.util.List<Student> students) {
        for (Student s : students) {
            if (assignedStudents.contains(s)) {
                assignedStudents.remove(s);
                assignedModel.removeElement(s);
                availableModel.addElement(s);
                System.out.println("Removed student: " + s.getName() + " from parent " + parent.getName());
            }
        }
        if (!students.isEmpty()) {
            String message;
            if (students.size() == 1) {
                message = "1 student removed from parent " + parent.getName() + ".";
            } else {
                message = students.size() + " students removed from parent " + parent.getName() + ".";
            }
            JOptionPane.showMessageDialog(ParentAssignmentFrame.this,
                    message,
                    "Removal Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Save current assignments and display summary.
     * In a real application, this would persist to database.
     */
    private void saveAssignments() {
        StringBuilder sb = new StringBuilder();
        sb.append("Final assignments for parent " + parent.getName() + ":\n");
        if (assignedModel.isEmpty()) {
            sb.append("No students assigned.\n");
        } else {
            for (int i = 0; i < assignedModel.size(); i++) {
                Student s = assignedModel.getElementAt(i);
                sb.append((i + 1) + ". " + s.getName() + "\n");
            }
            sb.append("\nTotal: " + assignedModel.size() + " student(s)");
        }
        // In a real application, here you would:
        // 1. Validate data
        // 2. Save to database
        // 3. Handle any errors
        JOptionPane.showMessageDialog(ParentAssignmentFrame.this,
                sb.toString(),
                "Assignments Summary", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Set up the complete UI layout.
     */
    private void setupLayout() {
        // Create scroll panes for the lists
        JScrollPane availableScrollPane = new JScrollPane(availableStudentsList);
        availableScrollPane.setBorder(BorderFactory.createTitledBorder("Available Students"));
        JScrollPane assignedScrollPane = new JScrollPane(assignedStudentsList);
        assignedScrollPane.setBorder(BorderFactory.createTitledBorder("Assigned Students"));
        // Panel for transfer buttons between the two lists
        JPanel transferPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        transferPanel.add(new JLabel()); // spacer
        transferPanel.add(assignButton);
        transferPanel.add(removeButton);
        transferPanel.add(new JLabel()); // spacer
        // Panel for action buttons (Send and Back)
        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.add(sendButton);
        actionPanel.add(backButton);
        // Main layout
        setLayout(new BorderLayout(10, 10));
        // Top panel with instructions
        JLabel titleLabel = new JLabel("Manage student assignments for parent: " + parent.getName(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);
        // Center panel with lists and transfer buttons
        JPanel listsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listsPanel.add(availableScrollPane);
        listsPanel.add(transferPanel);
        listsPanel.add(assignedScrollPane);
        add(listsPanel, BorderLayout.CENTER);
        // Bottom panel with action buttons
        add(actionPanel, BorderLayout.SOUTH);
        // Add keyboard shortcuts
        setupKeyboardShortcuts();
    }
    /**
     * Set up keyboard shortcuts for the frame.
     */
    private void setupKeyboardShortcuts() {
        // Add Escape key to close window
        KeyStroke escKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escKey, "close");
        getRootPane().getActionMap().put("close", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        // Add Ctrl+Enter as alternative to Send button
        KeyStroke sendKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(sendKey, "send");
        getRootPane().getActionMap().put("send", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                sendButton.doClick();
            }
        });
    }
}
/**
 * Simple data class representing a Parent.
 */
class Parent {
    private int id;
    private String name;
    private String email;
    public Parent(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Parent parent = (Parent) obj;
        return id == parent.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
/**
 * Simple data class representing a Student.
 */
class Student {
    private int id;
    private String name;
    private int age;
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    @Override
    public String toString() {
        return name + " (Age: " + age + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}