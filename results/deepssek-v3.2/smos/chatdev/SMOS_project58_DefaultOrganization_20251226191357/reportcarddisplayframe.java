'''
Displays a student's report card in a detailed view.
Shows all grades, comments, and student information.
'''
package com.chatdev.reportcardsystem.gui;
import com.chatdev.reportcardsystem.model.ReportCardSystem;
import com.chatdev.reportcardsystem.data.ReportCard;
import com.chatdev.reportcardsystem.data.SubjectGrade;
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class ReportCardDisplayFrame extends JFrame {
    private ReportCardSystem system;
    private ReportCard reportCard;
    public ReportCardDisplayFrame(ReportCardSystem system, String studentId,
                                 String quarter, String academicYear, String className) {
        this.system = system;
        this.reportCard = system.getReportCard(studentId, quarter, academicYear);
        if (reportCard == null) {
            JOptionPane.showMessageDialog(null,
                "Report card not found for the selected criteria.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Student Report Card");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        infoPanel.setBackground(new Color(240, 248, 255));
        infoPanel.add(createLabel("Student ID:"));
        infoPanel.add(createValueLabel(reportCard.getStudentId()));
        infoPanel.add(createLabel("Student Name:"));
        infoPanel.add(createValueLabel(reportCard.getStudentName()));
        infoPanel.add(createLabel("Class:"));
        infoPanel.add(createValueLabel(reportCard.getClassName()));
        infoPanel.add(createLabel("Academic Year:"));
        infoPanel.add(createValueLabel(reportCard.getAcademicYear() + " - " + reportCard.getQuarter()));
        add(infoPanel, BorderLayout.NORTH);
        JPanel gradesPanel = new JPanel(new BorderLayout());
        gradesPanel.setBorder(BorderFactory.createTitledBorder("Grades"));
        String[] columnNames = {"Subject", "Score", "Grade"};
        List<SubjectGrade> grades = reportCard.getGrades();
        Object[][] data = new Object[grades.size()][3];
        for (int i = 0; i < grades.size(); i++) {
            SubjectGrade grade = grades.get(i);
            data[i][0] = grade.getSubjectName();
            data[i][1] = grade.getScore();
            data[i][2] = grade.getGrade();
        }
        JTable gradesTable = new JTable(data, columnNames);
        gradesTable.setFillsViewportHeight(true);
        gradesTable.setRowHeight(25);
        gradesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(gradesTable);
        gradesPanel.add(scrollPane, BorderLayout.CENTER);
        add(gradesPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel commentsPanel = new JPanel(new BorderLayout());
        commentsPanel.setBorder(BorderFactory.createTitledBorder("Teacher's Comments"));
        JTextArea commentsArea = new JTextArea(reportCard.getComments());
        commentsArea.setEditable(false);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        commentsArea.setFont(new Font("Arial", Font.PLAIN, 12));
        commentsArea.setBackground(new Color(255, 255, 240));
        JScrollPane commentsScroll = new JScrollPane(commentsArea);
        commentsScroll.setPreferredSize(new Dimension(500, 80));
        commentsPanel.add(commentsScroll, BorderLayout.CENTER);
        JPanel averagePanel = new JPanel();
        averagePanel.setBorder(BorderFactory.createTitledBorder("Summary"));
        double average = reportCard.calculateAverage();
        String averageGrade = reportCard.calculateOverallGrade();
        JLabel averageLabel = new JLabel(String.format("Average Score: %.1f", average));
        averageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel gradeLabel = new JLabel("Overall Grade: " + averageGrade);
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gradeLabel.setForeground(new Color(0, 100, 0));
        averagePanel.add(averageLabel);
        averagePanel.add(Box.createHorizontalStrut(20));
        averagePanel.add(gradeLabel);
        bottomPanel.add(commentsPanel, BorderLayout.NORTH);
        bottomPanel.add(averagePanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);
        JPanel buttonPanel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }
    private JLabel createValueLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        return label;
    }
}