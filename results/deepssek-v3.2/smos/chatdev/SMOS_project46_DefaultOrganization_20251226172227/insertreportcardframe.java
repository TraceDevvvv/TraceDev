'''
Form for inserting report card grades
Allows entry of multiple subject grades with validation
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Student;
import models.SchoolClass;
import models.ReportCard;
public class InsertReportCardFrame extends JFrame {
    private Student selectedStudent;
    private SchoolClass selectedClass;
    private JTextField[] gradeFields;
    private String[] subjects = {"Mathematics", "Science", "English", "History", "Geography",
                                 "Art", "Physical Education", "Computer Science"};
    public InsertReportCardFrame(Student student, SchoolClass classObj) {
        this.selectedStudent = student;
        this.selectedClass = classObj;
        setTitle("Insert Report Card - Report Card System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        String studentInfo = selectedStudent.getFirstName() + " " + selectedStudent.getLastName();
        JLabel headerLabel = new JLabel("Insert Report Card for: " + studentInfo);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel infoLabel = new JLabel("Student: " + studentInfo +
                                     " | Class: " + selectedClass.getClassName());
        infoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(infoLabel, gbc);
        JLabel instructions = new JLabel("Enter grades (0-100) for each subject. Leave blank if not applicable.");
        instructions.setFont(new Font("Arial", Font.PLAIN, 12));
        instructions.setForeground(Color.DARK_GRAY);
        gbc.gridy = 1;
        centerPanel.add(instructions, gbc);
        gradeFields = new JTextField[subjects.length];
        for (int i = 0; i < subjects.length; i++) {
            gbc.gridwidth = 1;
            gbc.gridy = i + 2;
            gbc.gridx = 0;
            JLabel subjectLabel = new JLabel(subjects[i] + ":");
            subjectLabel.setPreferredSize(new Dimension(150, 25));
            centerPanel.add(subjectLabel, gbc);
            gbc.gridx = 1;
            gradeFields[i] = new JTextField(10);
            gradeFields[i].setToolTipText("Enter grade 0-100");
            centerPanel.add(gradeFields[i], gbc);
        }
        gbc.gridwidth = 1;
        gbc.gridy = subjects.length + 2;
        gbc.gridx = 0;
        centerPanel.add(new JLabel("Comments:"), gbc);
        gbc.gridx = 1;
        JTextArea commentsArea = new JTextArea(3, 30);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        JScrollPane commentsScroll = new JScrollPane(commentsArea);
        centerPanel.add(commentsScroll, gbc);
        JScrollPane formScrollPane = new JScrollPane(centerPanel);
        formScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudentListFrame(selectedClass);
            }
        });
        bottomPanel.add(cancelButton);
        JButton saveButton = new JButton("Save Report Card");
        saveButton.setBackground(new Color(46, 125, 50));
        saveButton.setForeground(Color.WHITE);
        saveButton.setPreferredSize(new Dimension(150, 30));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (saveReportCard(commentsArea.getText())) {
                    JOptionPane.showMessageDialog(InsertReportCardFrame.this,
                        "Report card saved successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new StudentListFrame(selectedClass);
                }
            }
        });
        bottomPanel.add(saveButton);
        mainPanel.add(formScrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }
    private boolean saveReportCard(String comments) {
        for (int i = 0; i < subjects.length; i++) {
            String gradeText = gradeFields[i].getText().trim();
            if (!gradeText.isEmpty()) {
                try {
                    int grade = Integer.parseInt(gradeText);
                    if (grade < 0 || grade > 100) {
                        JOptionPane.showMessageDialog(this,
                            "Invalid grade for " + subjects[i] + ". Must be between 0 and 100.",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                        gradeFields[i].requestFocus();
                        return false;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this,
                        "Invalid grade format for " + subjects[i] + ". Please enter a number.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                    gradeFields[i].requestFocus();
                    return false;
                }
            }
        }
        ReportCard reportCard = new ReportCard();
        reportCard.setStudentId(selectedStudent.getStudentId());
        reportCard.setClassId(selectedClass.getClassId());
        for (int i = 0; i < subjects.length; i++) {
            String gradeText = gradeFields[i].getText().trim();
            if (!gradeText.isEmpty()) {
                reportCard.setGrade(subjects[i], Integer.parseInt(gradeText));
            }
        }
        reportCard.setComments(comments);
        boolean success = DatabaseSimulator.saveReportCard(reportCard);
        if (!success) {
            JOptionPane.showMessageDialog(this,
                "Failed to save report card. Connection to SMOS server interrupted.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}