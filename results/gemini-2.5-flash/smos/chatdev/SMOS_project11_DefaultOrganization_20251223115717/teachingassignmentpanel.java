'''
This JPanel implements the GUI for assigning and removing teachings to a teacher.
It follows the event sequence described in the use case.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
public class TeachingAssignmentPanel extends JPanel {
    private Teacher currentTeacher;
    private TeacherService teacherService;
    // GUI Components
    private JLabel teacherNameLabel;
    private JComboBox<AcademicYear> academicYearComboBox;
    private JComboBox<ClassEntity> classComboBox;
    private DefaultListModel<Teaching> availableTeachingsListModel;
    private JList<Teaching> availableTeachingsList;
    private DefaultListModel<Teaching> assignedTeachingsListModel;
    private JList<Teaching> assignedTeachingsList;
    private JButton assignButton;
    private JButton removeButton;
    // Currently selected items
    private AcademicYear selectedAcademicYear;
    private ClassEntity selectedClass;
    /**
     * Constructs the TeachingAssignmentPanel.
     * @param teacher The teacher whose teachings are being managed.
     * @param service The TeacherService instance for data operations.
     */
    public TeachingAssignmentPanel(Teacher teacher, TeacherService service) {
        this.currentTeacher = teacher;
        this.teacherService = service;
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for main structure with gaps
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        // 1. Displays the teaching management form for the teacher in question
        initComponents();
        setupLayout();
        addListeners();
        loadInitialData(); // Load academic years initially and trigger subsequent updates
    }
    /**
     * Initializes all the GUI components.
     */
    private void initComponents() {
        teacherNameLabel = new JLabel("Managing Teachings for: " + currentTeacher.toString());
        teacherNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        academicYearComboBox = new JComboBox<>();
        classComboBox = new JComboBox<>();
        classComboBox.setEnabled(false); // Disabled until an academic year is selected
        availableTeachingsListModel = new DefaultListModel<>();
        availableTeachingsList = new JList<>(availableTeachingsListModel);
        availableTeachingsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allow multiple selections
        assignedTeachingsListModel = new DefaultListModel<>();
        assignedTeachingsList = new JList<>(assignedTeachingsListModel);
        assignedTeachingsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allow multiple selections
        assignButton = new JButton("Assign Selected >>");
        removeButton = new JButton("<< Remove Selected");
    }
    /**
     * Arranges the GUI components using Border and GridBag Layouts.
     * System 1: Displays the teaching management form for the teacher in question.
     */
    private void setupLayout() {
        // Top section for Teacher Name and Year selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topPanel.add(teacherNameLabel);
        topPanel.add(new JLabel("Academic Year:"));
        topPanel.add(academicYearComboBox);
        topPanel.add(new JLabel("Class:"));
        topPanel.add(classComboBox);
        add(topPanel, BorderLayout.NORTH);
        // Center section for teaching lists and action buttons
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        // Left list: Available Teachings
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        centerPanel.add(new JScrollPane(availableTeachingsList), gbc);
        gbc.gridy = 1;
        gbc.weighty = 0; // Don't grow label vertically
        centerPanel.add(new JLabel("Available Teachings", SwingConstants.CENTER), gbc);
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
        gbc.weightx = 0; // Smaller width for buttons, don't grow
        gbc.weighty = 1.0;
        gbc.gridheight = 2; // Span across two rows (for lists and their labels)
        gbc.fill = GridBagConstraints.VERTICAL; // Fill vertically
        centerPanel.add(buttonPanel, gbc);
        // Right list: Assigned Teachings
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridheight = 1; // Reset gridheight for list
        gbc.fill = GridBagConstraints.BOTH; // Fill both for list
        centerPanel.add(new JScrollPane(assignedTeachingsList), gbc);
        gbc.gridy = 1;
        gbc.weighty = 0; // Don't grow label vertically
        centerPanel.add(new JLabel("Assigned Teachings", SwingConstants.CENTER), gbc);
        add(centerPanel, BorderLayout.CENTER);
    }
    /**
     * Adds action listeners to interactive components.
     */
    private void addListeners() {
        // User 2: Select the academic year
        // System 3: View the list of classes available for the selected year
        academicYearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAcademicYear = (AcademicYear) academicYearComboBox.getSelectedItem();
                // Update classes for the newly selected academic year.
                // Pass 'true' to show message if no classes are found, as this is a user-initiated change.
                updateClassComboBox(selectedAcademicYear, true);
            }
        });
        // User 4: Select the desired class
        // System 5: Displays the list of teachings associated with that class
        classComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClass = (ClassEntity) classComboBox.getSelectedItem();
                // System 5: Displays the list of teachings associated with that class
                if (selectedClass != null && selectedAcademicYear != null) { // Ensure both year and class are selected
                    loadTeachingsForSelectedClass();
                } else {
                    // If no class is selected (e.g., unselected or no classes available for the year)
                    availableTeachingsListModel.clear();
                    assignedTeachingsListModel.clear();
                }
            }
        });
        // User 6: Select the teachings to associate or remove the teacher
        // System 7: Assigns or removes the teaching teachings as indicated by the administrator
        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignSelectedTeachings();
            }
        });
        // User 6: Select the teachings to associate or remove the teacher
        // System 7: Assigns or removes the teaching teachings as indicated by the administrator
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedTeachings();
            }
        });
    }
    /**
     * Loads the initial academic years into the combo box and sets up initial data.
     */
    private void loadInitialData() {
        // Clear any existing items first to prevent duplicates on potential re-load
        academicYearComboBox.removeAllItems();
        List<AcademicYear> years = teacherService.getAllAcademicYears();
        for (AcademicYear year : years) {
            academicYearComboBox.addItem(year);
        }
        // Ensure initial state for other components:
        classComboBox.removeAllItems();
        classComboBox.setEnabled(false); // Class selection is disabled until a year is chosen
        availableTeachingsListModel.clear();
        assignedTeachingsListModel.clear();
        selectedAcademicYear = null; // No academic year is initially selected *functionally* until user/program selects one
        selectedClass = null; // No class is selected by default
        if (years.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No academic years configured in the system.", "Configuration Error", JOptionPane.ERROR_MESSAGE);
            academicYearComboBox.setEnabled(false);
        } else {
            academicYearComboBox.setEnabled(true);
            // Simulate the initial selection of the first academic year when the panel loads.
            // This ensures System Event 3 (View list of classes) happens on startup.
            selectedAcademicYear = (AcademicYear) academicYearComboBox.getSelectedItem(); // Get the item visually selected by default
            if (selectedAcademicYear != null) {
                // Update classes for this initial academic year. Don't show messages on startup.
                updateClassComboBox(selectedAcademicYear, false);
            }
        }
    }
    /**
     * Updates the class combo box based on the selected academic year.
     * Clears current classes, loads new ones, and adjusts UI state.
     * @param year The academic year for which to load classes.
     * @param showMessageIfNoClasses If true, displays a JOptionPane if no classes are found for the year.
     */
    private void updateClassComboBox(AcademicYear year, boolean showMessageIfNoClasses) {
        classComboBox.removeAllItems();
        availableTeachingsListModel.clear();
        assignedTeachingsListModel.clear();
        selectedClass = null; // Clear previously selected class
        if (year != null) {
            List<ClassEntity> classesForYear = teacherService.getClassesByAcademicYear(year.getId());
            if (!classesForYear.isEmpty()) {
                for (ClassEntity classEntity : classesForYear) {
                    classComboBox.addItem(classEntity);
                }
                classComboBox.setEnabled(true); // Enable class selection
                // The JComboBox will visually select the first item if .addItem() is called.
                // However, we still rely on user input (Event 4) to trigger teaching loading (Event 5).
            } else {
                classComboBox.setEnabled(false); // Disable if no classes for this year
                if (showMessageIfNoClasses) {
                    JOptionPane.showMessageDialog(this,
                            "No classes found for the selected academic year: " + year.getName(),
                            "No Classes", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            classComboBox.setEnabled(false); // Disable if no academic year is selected
        }
    }
    /**
     * Loads available and assigned teachings into their respective lists
     * based on the currently selected academic year and class.
     */
    private void loadTeachingsForSelectedClass() {
        availableTeachingsListModel.clear();
        assignedTeachingsListModel.clear();
        if (selectedAcademicYear != null && selectedClass != null) {
            // Get all teachings for the selected class
            List<Teaching> allTeachings = teacherService.getTeachingsByClass(selectedClass.getId());
            // Get teachings already assigned to the current teacher for the selected academic year
            Set<Integer> assignedTeachingIds = teacherService.getAssignedTeachingsForTeacher(
                    currentTeacher.getId(), selectedAcademicYear.getId());
            // Populate available and assigned lists
            for (Teaching teaching : allTeachings) {
                if (assignedTeachingIds.contains(teaching.getId())) {
                    assignedTeachingsListModel.addElement(teaching);
                } else {
                    availableTeachingsListModel.addElement(teaching);
                }
            }
        }
    }
    /**
     * Assigns selected teachings from the 'available' list to the teacher.
     * Handles the Postcondition: One or more teachings have been assigned.
     */
    private void assignSelectedTeachings() {
        if (selectedAcademicYear == null || selectedClass == null) {
            JOptionPane.showMessageDialog(this, "Please select an academic year and a class before assigning teachings.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<Teaching> selected = availableTeachingsList.getSelectedValuesList();
        if (selected.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No teachings selected to assign.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        boolean changesMade = false;
        for (Teaching teaching : selected) {
            if (teacherService.assignTeachingToTeacher(currentTeacher.getId(), selectedAcademicYear.getId(), teaching.getId())) {
                changesMade = true;
            }
        }
        if (changesMade) {
            // Refresh lists to reflect changes
            loadTeachingsForSelectedClass();
            JOptionPane.showMessageDialog(this, "Teachings assigned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No new teachings were assigned (they might already be assigned).", "No Changes", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Removes selected teachings from the 'assigned' list from the teacher.
     * Handles the Postcondition: One or more teachings have been removed.
     */
    private void removeSelectedTeachings() {
        if (selectedAcademicYear == null || selectedClass == null) {
            JOptionPane.showMessageDialog(this, "Please select an academic year and a class before removing teachings.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<Teaching> selected = assignedTeachingsList.getSelectedValuesList();
        if (selected.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No teachings selected to remove.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        boolean changesMade = false;
        for (Teaching teaching : selected) {
            if (teacherService.removeTeachingFromTeacher(currentTeacher.getId(), selectedAcademicYear.getId(), teaching.getId())) {
                changesMade = true;
            }
        }
        if (changesMade) {
            // Refresh lists to reflect changes
            loadTeachingsForSelectedClass();
            JOptionPane.showMessageDialog(this, "Teachings removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No assigned teachings were removed (they might not have been assigned).", "No Changes", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}