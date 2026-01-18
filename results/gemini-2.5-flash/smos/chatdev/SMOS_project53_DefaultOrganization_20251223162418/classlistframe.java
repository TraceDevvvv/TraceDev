'''
A JFrame that displays a list of courses (referred to as classes in user context) in a JTable.
Allows staff to view course details by selecting a row.
(References to the data model 'Class' renamed to 'Course').
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
public class ClassListFrame extends JFrame {
    private JTable classTable;
    private DefaultTableModel tableModel;
    /**
     * Constructs the class list frame, initializing the table with course data.
     */
    public ClassListFrame() {
        // Set up the frame properties
        setTitle("ATA Staff - View Classes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        // Set up the main panel
        JPanel panel = new JPanel(new BorderLayout());
        add(panel);
        // Label for the screen title
        JLabel titleLabel = new JLabel("Available Classes", SwingConstants.CENTER); // User still sees 'Classes'
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);
        // Define table columns
        String[] columnNames = {"ID", "Name", "Instructor", "Schedule", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            // Make table cells uneditable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        classTable = new JTable(tableModel);
        // Add sorting capabilities to the table
        classTable.setAutoCreateRowSorter(true);
        // Set column widths for better presentation
        classTable.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
        classTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Name
        classTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Instructor
        classTable.getColumnModel().getColumn(3).setPreferredWidth(150); // Schedule
        classTable.getColumnModel().getColumn(4).setPreferredWidth(300); // Description
        // Populate the table with data from ClassService
        loadCourseData(); // Method name changed to reflect Course type
        // Add the table to a JScrollPane for scrollability if many classes
        JScrollPane scrollPane = new JScrollPane(classTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        // Add a mouse listener to the table to handle row selection and display details
        classTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click to view details
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow(); // Get the selected row index
                    if (row != -1) {
                        // Convert view row index to model row index if table is sorted
                        int modelRow = target.convertRowIndexToModel(row);
                        displayCourseDetails(modelRow); // Method name changed
                    }
                }
            }
        });
    }
    /**
     * Loads course data from the ClassService and populates the JTable.
     */
    private void loadCourseData() { // Method name changed
        // Clear existing data from the table model
        tableModel.setRowCount(0);
        // Retrieve all courses from the service
        List<Course> courses = ClassService.getAllCourses(); // Changed List<Class> to List<Course>, method call updated
        // Add each course as a row in the table
        for (Course c : courses) { // Changed Class c to Course c
            Object[] rowData = {
                    c.getClassId(),
                    c.getName(),
                    c.getInstructor(),
                    c.getSchedule(),
                    c.getDescription()
            };
            tableModel.addRow(rowData);
        }
        // Handle case where no courses are found
        if (courses.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No classes found in the database.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Displays a detailed view of the selected course in a dialog.
     * This simulates "accessing the registry of each class".
     *
     * @param modelRow The row index in the table model (not necessarily the view index if sorted).
     */
    private void displayCourseDetails(int modelRow) { // Method name changed
        // Retrieve the class ID from the selected row
        String classId = (String) tableModel.getValueAt(modelRow, 0);
        // Find the corresponding Course object
        Course selectedCourse = null; // Changed Class selectedClass to Course selectedCourse
        for (Course c : ClassService.getAllCourses()) { // Changed Class c to Course c, method call updated
            if (c.getClassId().equals(classId)) {
                selectedCourse = c; // Assignment to Course object
                break;
            }
        }
        if (selectedCourse != null) { // Check on Course object
            // Display course details in a message dialog
            // The toString() method of the Course object provides a formatted detail string.
            JOptionPane.showMessageDialog(this,
                    selectedCourse.toString(), // Call toString() on Course object
                    "Course Details: " + selectedCourse.getName(), // Display 'Course Details'
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Could not retrieve details for the selected course.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}