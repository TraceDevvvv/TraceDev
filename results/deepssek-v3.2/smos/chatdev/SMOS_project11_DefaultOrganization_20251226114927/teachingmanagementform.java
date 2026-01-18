/**
 * TeachingManagementForm: the form for managing teachings for a teacher.
 * This mimics the steps described in the use case: select year → class → teachings → assign/remove.
 * Includes server connection handling as per postconditions.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class TeachingManagementForm extends JFrame {
    private Teacher teacher;
    private boolean serverConnected;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> classComboBox;
    private JList<String> teachingsList;
    private DefaultListModel<String> listModel;
    private JButton assignButton;
    private JButton removeButton;
    private JButton closeButton;
    // Simulated data
    private Map<String, List<String>> yearToClasses; // academic year -> list of classes
    private Map<String, List<String>> classToTeachings; // class -> list of teachings
    public TeachingManagementForm(Teacher teacher, boolean serverConnected) {
        this.teacher = teacher;
        this.serverConnected = serverConnected;
        initializeData();
        initialize();
    }
    private void initializeData() {
        // Simulate database data
        yearToClasses = new HashMap<>();
        yearToClasses.put("2023-2024", Arrays.asList("Class 10A", "Class 10B", "Class 11A"));
        yearToClasses.put("2024-2025", Arrays.asList("Class 10A", "Class 10B", "Class 11A", "Class 11B"));
        classToTeachings = new HashMap<>();
        classToTeachings.put("Class 10A", Arrays.asList("Mathematics", "Physics", "Chemistry"));
        classToTeachings.put("Class 10B", Arrays.asList("Mathematics", "Biology", "English"));
        classToTeachings.put("Class 11A", Arrays.asList("Advanced Math", "Computer Science", "History"));
        classToTeachings.put("Class 11B", Arrays.asList("Literature", "Geography", "Art"));
    }
    private void initialize() {
        setTitle("Teaching Management for: " + teacher.getName());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        // Main panel with form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Step 1: Display form (implied by labels and components)
        formPanel.add(new JLabel("Step 1: Select Academic Year:"));
        yearComboBox = new JComboBox<>(new String[]{"2023-2024", "2024-2025"});
        yearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClassList();
            }
        });
        formPanel.add(yearComboBox);
        formPanel.add(new JLabel("Step 2: Select Class:"));
        classComboBox = new JComboBox<>();
        classComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTeachingsList();
            }
        });
        formPanel.add(classComboBox);
        formPanel.add(new JLabel("Step 3: Available Teachings:"));
        listModel = new DefaultListModel<>();
        teachingsList = new JList<>(listModel);
        teachingsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(teachingsList);
        formPanel.add(scrollPane);
        // Add server status indicator
        JLabel serverStatusLabel = new JLabel("Server Status: " + (serverConnected ? "Connected" : "Disconnected"));
        serverStatusLabel.setForeground(serverConnected ? Color.GREEN.darker() : Color.RED);
        formPanel.add(serverStatusLabel);
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        assignButton = new JButton("Assign Selected");
        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignTeachings();
            }
        });
        buttonPanel.add(assignButton);
        removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTeachings();
            }
        });
        buttonPanel.add(removeButton);
        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the form
            }
        });
        buttonPanel.add(closeButton);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        // Trigger initial updates
        updateClassList();
        // Disable buttons if server is disconnected
        if (!serverConnected) {
            assignButton.setEnabled(false);
            removeButton.setEnabled(false);
        }
    }
    // Update the class list based on selected year
    private void updateClassList() {
        String selectedYear = (String) yearComboBox.getSelectedItem();
        if (selectedYear == null) return;
        List<String> classes = yearToClasses.get(selectedYear);
        classComboBox.removeAllItems();
        if (classes != null) {
            for (String cls : classes) {
                classComboBox.addItem(cls);
            }
        }
        // Clear teachings list when year changes
        listModel.clear();
    }
    // Update the teachings list based on selected class
    private void updateTeachingsList() {
        String selectedClass = (String) classComboBox.getSelectedItem();
        if (selectedClass == null) return;
        List<String> teachings = classToTeachings.get(selectedClass);
        listModel.clear();
        if (teachings != null) {
            for (String teaching : teachings) {
                listModel.addElement(teaching);
            }
        }
    }
    // Assign selected teachings to the teacher
    private void assignTeachings() {
        // Check server connection as per postconditions
        if (!serverConnected) {
            JOptionPane.showMessageDialog(this, 
                "Cannot assign teachings: SMOS server connection interrupted", 
                "Server Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<String> selected = teachingsList.getSelectedValuesList();
        if (selected.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please select at least one teaching to assign.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (String teaching : selected) {
            teacher.addTeaching(teaching);
        }
        JOptionPane.showMessageDialog(this, 
            "Successfully assigned teachings: " + selected, 
            "Assignment Successful", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    // Remove selected teachings from the teacher
    private void removeTeachings() {
        // Check server connection as per postconditions
        if (!serverConnected) {
            JOptionPane.showMessageDialog(this, 
                "Cannot remove teachings: SMOS server connection interrupted", 
                "Server Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<String> selected = teachingsList.getSelectedValuesList();
        if (selected.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please select at least one teaching to remove.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (String teaching : selected) {
            teacher.removeTeaching(teaching);
        }
        JOptionPane.showMessageDialog(this, 
            "Successfully removed teachings: " + selected, 
            "Removal Successful", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}