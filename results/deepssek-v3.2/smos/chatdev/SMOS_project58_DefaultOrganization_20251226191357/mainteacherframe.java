'''
Main dashboard for teachers after clicking "On-line report cards" button.
Provides navigation to view report cards and manages SMOS server connection.
'''
package com.chatdev.reportcardsystem.gui;
import com.chatdev.reportcardsystem.model.ReportCardSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class MainTeacherFrame extends JFrame {
    private ReportCardSystem system;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> classComboBox;
    private JComboBox<String> studentComboBox;
    private JComboBox<String> quarterComboBox;
    private JButton viewReportButton;
    private JButton backButton;
    private JButton logoutButton;
    public MainTeacherFrame(ReportCardSystem system) {
        this.system = system;
        initializeUI();
        loadAcademicYears();
    }
    private void initializeUI() {
        setTitle("Online Report Cards - Teacher View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("Online Report Cards System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel instructionLabel = new JLabel("Please follow the steps below to view a student's report card:");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(instructionLabel, gbc);
        JLabel step1Label = new JLabel("1. Select Academic Year:");
        step1Label.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPanel.add(step1Label, gbc);
        yearComboBox = new JComboBox<>();
        yearComboBox.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(yearComboBox, gbc);
        JLabel step2Label = new JLabel("2. Select Class:");
        step2Label.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(step2Label, gbc);
        classComboBox = new JComboBox<>();
        classComboBox.setPreferredSize(new Dimension(200, 25));
        classComboBox.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPanel.add(classComboBox, gbc);
        JLabel step3Label = new JLabel("3. Select Student:");
        step3Label.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(step3Label, gbc);
        studentComboBox = new JComboBox<>();
        studentComboBox.setPreferredSize(new Dimension(200, 25));
        studentComboBox.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPanel.add(studentComboBox, gbc);
        JLabel step4Label = new JLabel("4. Select Quarter:");
        step4Label.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPanel.add(step4Label, gbc);
        quarterComboBox = new JComboBox<>(new String[]{"Q1", "Q2", "Q3", "Q4"});
        quarterComboBox.setPreferredSize(new Dimension(200, 25));
        quarterComboBox.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        contentPanel.add(quarterComboBox, gbc);
        viewReportButton = new JButton("View Report Card");
        viewReportButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewReportButton.setPreferredSize(new Dimension(180, 35));
        viewReportButton.setEnabled(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(viewReportButton, gbc);
        add(contentPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Back to Dashboard");
        backButton.setPreferredSize(new Dimension(150, 30));
        leftButtonPanel.add(backButton);
        JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 30));
        rightButtonPanel.add(logoutButton);
        bottomPanel.add(leftButtonPanel, BorderLayout.WEST);
        bottomPanel.add(rightButtonPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        setupEventListeners();
    }
    private void loadAcademicYears() {
        List<String> years = system.getAvailableAcademicYears();
        yearComboBox.removeAllItems();
        for (String year : years) {
            yearComboBox.addItem(year);
        }
        if (years.isEmpty()) {
            yearComboBox.addItem("No years available");
        }
    }
    private void setupEventListeners() {
        yearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedYear = (String) yearComboBox.getSelectedItem();
                if (selectedYear != null && !selectedYear.equals("No years available")) {
                    loadClassesForYear(selectedYear);
                } else {
                    classComboBox.setEnabled(false);
                    classComboBox.removeAllItems();
                    studentComboBox.setEnabled(false);
                    studentComboBox.removeAllItems();
                    quarterComboBox.setEnabled(false);
                    viewReportButton.setEnabled(false);
                }
            }
        });
        classComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClass = (String) classComboBox.getSelectedItem();
                String selectedYear = (String) yearComboBox.getSelectedItem();
                if (selectedClass != null && selectedYear != null &&
                    !selectedClass.equals("No classes available")) {
                    loadStudentsForClass(selectedYear, selectedClass);
                    quarterComboBox.setEnabled(true);
                } else {
                    studentComboBox.setEnabled(false);
                    studentComboBox.removeAllItems();
                    quarterComboBox.setEnabled(false);
                    viewReportButton.setEnabled(false);
                }
            }
        });
        studentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStudent = (String) studentComboBox.getSelectedItem();
                if (selectedStudent != null && !selectedStudent.equals("No students available")) {
                    viewReportButton.setEnabled(true);
                } else {
                    viewReportButton.setEnabled(false);
                }
            }
        });
        viewReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedYear = (String) yearComboBox.getSelectedItem();
                String selectedClass = (String) classComboBox.getSelectedItem();
                String selectedStudent = (String) studentComboBox.getSelectedItem();
                String selectedQuarter = (String) quarterComboBox.getSelectedItem();
                if (selectedYear != null && selectedClass != null &&
                    selectedStudent != null && selectedQuarter != null) {
                    String studentId = selectedStudent.split(" - ")[0];
                    ReportCardDisplayFrame reportFrame = new ReportCardDisplayFrame(
                        system, studentId, selectedQuarter, selectedYear, selectedClass);
                    reportFrame.setVisible(true);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    MainTeacherFrame.this,
                    "Return to Teacher Dashboard?",
                    "Confirm Navigation",
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    TeacherDashboardFrame dashboard = new TeacherDashboardFrame();
                    dashboard.setVisible(true);
                }
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    MainTeacherFrame.this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    system.disconnectFromServer();
                    JOptionPane.showMessageDialog(
                        MainTeacherFrame.this,
                        "Successfully disconnected from SMOS server.\nSession terminated.",
                        "Server Disconnected",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    dispose();
                    LoginFrame loginFrame = new LoginFrame();
                    loginFrame.setVisible(true);
                }
            }
        });
    }
    private void loadClassesForYear(String year) {
        List<String> classes = system.getClassesForAcademicYear(year);
        classComboBox.removeAllItems();
        if (classes.isEmpty()) {
            classComboBox.addItem("No classes available");
            classComboBox.setEnabled(false);
        } else {
            for (String className : classes) {
                classComboBox.addItem(className);
            }
            classComboBox.setEnabled(true);
        }
        studentComboBox.setEnabled(false);
        studentComboBox.removeAllItems();
        quarterComboBox.setEnabled(false);
        viewReportButton.setEnabled(false);
    }
    private void loadStudentsForClass(String year, String className) {
        List<String> students = system.getStudentsForClass(year, className);
        studentComboBox.removeAllItems();
        if (students.isEmpty()) {
            studentComboBox.addItem("No students available");
            studentComboBox.setEnabled(false);
        } else {
            for (String student : students) {
                studentComboBox.addItem(student);
            }
            studentComboBox.setEnabled(true);
        }
    }
}