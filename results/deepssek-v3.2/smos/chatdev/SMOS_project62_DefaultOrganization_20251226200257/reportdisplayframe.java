'''
ReportDisplayFrame.java
GUI for displaying the student's report card details.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class ReportDisplayFrame extends JFrame {
    public ReportDisplayFrame(ReportCard report) {
        initializeUI(report);
    }
    private void initializeUI(ReportCard report) {
        setTitle("Report Card - " + report.getStudentName());
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Header with student info
        JPanel headerPanel = new JPanel(new GridLayout(3, 1));
        headerPanel.add(new JLabel("Student: " + report.getStudentName(), SwingConstants.CENTER));
        headerPanel.add(new JLabel("Grade Level: " + report.getGradeLevel(), SwingConstants.CENTER));
        headerPanel.add(new JLabel("Semester: " + report.getSemester(), SwingConstants.CENTER));
        // Grades display
        JTextArea gradesArea = new JTextArea(report.toString());
        gradesArea.setEditable(false);
        gradesArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(gradesArea);
        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);
        add(panel);
    }
}