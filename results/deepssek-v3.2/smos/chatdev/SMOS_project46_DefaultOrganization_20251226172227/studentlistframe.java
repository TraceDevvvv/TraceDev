'''
Displays list of students in the selected class
Each student has a select button for report card insertion
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import models.SchoolClass;
import models.Student;
public class StudentListFrame extends JFrame {
    private SchoolClass selectedClass;
    public StudentListFrame(SchoolClass selectedClass) {
        this.selectedClass = selectedClass;
        setTitle("Select Student - Report Card System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        String className = selectedClass != null ? selectedClass.getClassName() : "Unknown Class";
        JLabel headerLabel = new JLabel("Students in Class: " + className);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        List<Student> students = DatabaseSimulator.getStudentsByClass(selectedClass);
        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        if (students.isEmpty()) {
            centerPanel.add(new JLabel("No students found in this class"));
        } else {
            JPanel headerRow = new JPanel(new GridLayout(1, 4));
            headerRow.setBackground(new Color(240, 240, 240));
            headerRow.add(new JLabel("Student ID"));
            headerRow.add(new JLabel("Name"));
            headerRow.add(new JLabel("Date of Birth"));
            headerRow.add(new JLabel("Action"));
            centerPanel.add(headerRow);
            for (Student student : students) {
                JPanel studentPanel = new JPanel(new GridLayout(1, 4));
                studentPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                studentPanel.setBackground(Color.WHITE);
                studentPanel.add(new JLabel(student.getStudentId()));
                studentPanel.add(new JLabel(student.getFirstName() + " " + student.getLastName()));
                studentPanel.add(new JLabel(student.getDateOfBirth()));
                JButton selectButton = new JButton("Select");
                selectButton.setBackground(new Color(33, 150, 243));
                selectButton.setForeground(Color.WHITE);
                selectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new InsertReportCardFrame(student, selectedClass);
                    }
                });
                studentPanel.add(selectButton);
                centerPanel.add(studentPanel);
            }
        }
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back to Classes");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ClassSelectionFrame();
            }
        });
        bottomPanel.add(backButton);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }
}