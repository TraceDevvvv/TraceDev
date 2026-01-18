/**
 * Displays a list of students for a selected class.
 * Allows the administrator to select a student to insert their report card.
 * (Corresponds to System event 3 and User event 4 in the use case).
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class StudentSelectionPanel extends JPanel {
    private Main mainFrame;
    private ReportCardSystem system;
    private JPanel studentListPanel; // Panel to hold student buttons
    private JLabel classInfoLabel; // Label to show current class info
    public StudentSelectionPanel(Main mainFrame, ReportCardSystem system) {
        this.mainFrame = mainFrame;
        this.system = system;
        setLayout(new BorderLayout(10, 10));
        classInfoLabel = new JLabel("Class: ", SwingConstants.CENTER);
        classInfoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(classInfoLabel, BorderLayout.NORTH);
        studentListPanel = new JPanel();
        studentListPanel.setLayout(new BoxLayout(studentListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(studentListPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
        // Add a back button to return to class selection
        JButton backButton = new JButton("Back to Class Selection");
        backButton.addActionListener(e -> mainFrame.showClassSelection());
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(backButton);
        add(southPanel, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
    /**
     * Loads and displays the list of students for the given class ID.
     * Each student will have a button to select them for report card input.
     * @param classId The ID of the class whose students are to be displayed.
     */
    public void loadStudents(String classId) {
        studentListPanel.removeAll(); // Clear previous student list
        List<Student> students = system.getStudentsInClass(classId);
        // Update class info label
        SchoolClass selectedClass = system.getSchoolClassById(classId); // Use new helper method
        if (selectedClass != null) {
            classInfoLabel.setText("Students in Class: " + selectedClass.getName() + " (" + selectedClass.getAcademicYear() + ")");
        } else {
            classInfoLabel.setText("Class students (ID: " + classId + ") - Class not found");
        }
        if (students.isEmpty()) {
            studentListPanel.add(new JLabel("No students found in this class."));
        } else {
            for (Student student : students) {
                JPanel studentRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel studentNameLabel = new JLabel(student.getName() + " (ID: " + student.getId() + ")");
                studentNameLabel.setPreferredSize(new Dimension(300, 30)); // Fixed size for alignment
                studentNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                JButton selectStudentButton = new JButton("Insert Report Card");
                // Store studentId to be used in the action listener
                selectStudentButton.putClientProperty("studentId", student.getId());
                selectStudentButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedStudentId = (String) ((JButton) e.getSource()).getClientProperty("studentId");
                        // Navigate to the report card input view for the chosen student
                        mainFrame.showReportCardInput(selectedStudentId);
                    }
                });
                studentRowPanel.add(studentNameLabel);
                studentRowPanel.add(selectStudentButton);
                studentListPanel.add(studentRowPanel);
            }
        }
        revalidate();
        repaint();
    }
}