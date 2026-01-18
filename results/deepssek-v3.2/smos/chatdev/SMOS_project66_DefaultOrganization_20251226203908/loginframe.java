import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
/**
 * LoginFrame.java
 * Handles user authentication and navigation through the report card viewing process
 * Implements the complete use case flow with proper error handling and user experience improvements
 */
public class LoginFrame extends JFrame {
    private Database database;
    private AcademicYear selectedYear;
    private Classroom selectedClass;
    private Student selectedStudent;
    private String selectedQuadrimestre;
    public LoginFrame() {
        database = new Database();
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Report Card Viewing System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));
        // Title
        JLabel titleLabel = new JLabel("Login as Direction", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Login panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBackground(new Color(240, 240, 240));
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        userField.setText("direction"); // Pre-fill for convenience
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        passField.setText("password123"); // Pre-fill for convenience
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(204, 0, 0));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(loginButton);
        loginPanel.add(cancelButton);
        mainPanel.add(loginPanel, BorderLayout.CENTER);
        // Add window listener for connection handling
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleConnectionStop();
            }
        });
        // Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                // Simple authentication for demonstration
                // In real application, this would connect to SMOS server
                if (username.equals("direction") && password.equals("password123")) {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Login successful! Welcome Direction.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    showOnlineReportCardsButton();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Invalid credentials. Please try again.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Cancel button action
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleConnectionStop();
                System.exit(0);
            }
        });
        // Add Enter key support for login
        getRootPane().setDefaultButton(loginButton);
        add(mainPanel);
        setVisible(true);
    }
    private void showOnlineReportCardsButton() {
        // Clear current content
        getContentPane().removeAll();
        // New panel for report cards button
        JPanel panel = new JPanel(new BorderLayout(10, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(240, 240, 240));
        JLabel welcomeLabel = new JLabel("Welcome, Direction!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(0, 102, 204));
        JButton reportCardsButton = new JButton("Online Report Cards");
        reportCardsButton.setFont(new Font("Arial", Font.PLAIN, 16));
        reportCardsButton.setBackground(new Color(0, 153, 76));
        reportCardsButton.setForeground(Color.WHITE);
        reportCardsButton.setFocusPainted(false);
        reportCardsButton.setPreferredSize(new Dimension(250, 50));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(153, 0, 0));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        // Center panel for main button
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBackground(new Color(240, 240, 240));
        centerPanel.add(reportCardsButton);
        // South panel for logout button
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setBackground(new Color(240, 240, 240));
        southPanel.add(logoutButton);
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);
        // Action listener for report cards button
        reportCardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectAcademicYear();
            }
        });
        // Action listener for logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleConnectionStop();
                JOptionPane.showMessageDialog(LoginFrame.this,
                        "Logged out successfully.",
                        "Logout",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new LoginFrame().setVisible(true);
            }
        });
        add(panel);
        revalidate();
        repaint();
    }
    private void selectAcademicYear() {
        // Get academic years from database
        List<AcademicYear> years = database.getAcademicYears();
        // Create dialog for year selection
        JDialog yearDialog = new JDialog(this, "Select Academic Year", true);
        yearDialog.setSize(400, 350);
        yearDialog.setLocationRelativeTo(this);
        yearDialog.setLayout(new BorderLayout(10, 10));
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));
        JLabel titleLabel = new JLabel("Select the academic year:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(new Color(0, 102, 204));
        DefaultListModel<String> yearListModel = new DefaultListModel<>();
        for (AcademicYear year : years) {
            yearListModel.addElement(year.toString());
        }
        JList<String> yearList = new JList<>(yearListModel);
        yearList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        yearList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(yearList);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JButton selectButton = new JButton("Select");
        selectButton.setBackground(new Color(0, 102, 204));
        selectButton.setForeground(Color.WHITE);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(selectButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setBackground(new Color(240, 240, 240));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        // Select button action
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = yearList.getSelectedIndex();
                if (selectedIndex != -1) {
                    selectedYear = years.get(selectedIndex);
                    yearDialog.dispose();
                    displayClassesForYear();
                } else {
                    JOptionPane.showMessageDialog(yearDialog,
                            "Please select an academic year.",
                            "Selection Required",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        // Cancel button action
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yearDialog.dispose();
            }
        });
        yearDialog.add(panel);
        yearDialog.setVisible(true);
    }
    private void displayClassesForYear() {
        // Get classes for selected year
        List<Classroom> classes = database.getClassesByYear(selectedYear.toString());
        // Create dialog for class selection
        JDialog classDialog = new JDialog(this, "Select Class", true);
        classDialog.setSize(500, 450);
        classDialog.setLocationRelativeTo(this);
        classDialog.setLayout(new BorderLayout(10, 10));
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));
        JLabel titleLabel = new JLabel("Classes for " + selectedYear.toString() +
                " - Click 'Report Cards' to select:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(new Color(0, 102, 204));
        DefaultListModel<String> classListModel = new DefaultListModel<>();
        for (Classroom classroom : classes) {
            classListModel.addElement(classroom.toString());
        }
        JList<String> classList = new JList<>(classListModel);
        classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        classList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(classList);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JButton reportCardsButton = new JButton("Report Cards");
        reportCardsButton.setBackground(new Color(0, 102, 204));
        reportCardsButton.setForeground(Color.WHITE);
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(reportCardsButton);
        buttonPanel.add(backButton);
        buttonPanel.setBackground(new Color(240, 240, 240));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        // Report Cards button action
        reportCardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = classList.getSelectedIndex();
                if (selectedIndex != -1) {
                    selectedClass = classes.get(selectedIndex);
                    classDialog.dispose();
                    displayStudentsInClass();
                } else {
                    JOptionPane.showMessageDialog(classDialog,
                            "Please select a class.",
                            "Selection Required",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        // Back button action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classDialog.dispose();
                selectAcademicYear();
            }
        });
        classDialog.add(panel);
        classDialog.setVisible(true);
    }
    private void displayStudentsInClass() {
        // Get students from selected class
        List<Student> students = selectedClass.getStudents();
        // Create dialog for student and quadrimestre selection
        JDialog studentDialog = new JDialog(this, "Select Student and Quadrimestre", true);
        studentDialog.setSize(500, 450);
        studentDialog.setLocationRelativeTo(this);
        studentDialog.setLayout(new BorderLayout(10, 10));
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));
        JLabel titleLabel = new JLabel("Students in " + selectedClass.getName() +
                " - Select student and quadrimestre:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(new Color(0, 102, 204));
        // Student list
        DefaultListModel<String> studentListModel = new DefaultListModel<>();
        for (Student student : students) {
            studentListModel.addElement(student.toString());
        }
        JList<String> studentList = new JList<>(studentListModel);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane studentScrollPane = new JScrollPane(studentList);
        studentScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        // Quadrimestre selection
        JPanel quadrimestrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        quadrimestrePanel.setBackground(new Color(240, 240, 240));
        JLabel quadLabel = new JLabel("Select Quadrimestre:");
        quadLabel.setFont(new Font("Arial", Font.BOLD, 13));
        String[] quadrimestres = {"First Quadrimestre", "Second Quadrimestre", "Third Quadrimestre"};
        JComboBox<String> quadComboBox = new JComboBox<>(quadrimestres);
        quadComboBox.setFont(new Font("Arial", Font.PLAIN, 13));
        quadrimestrePanel.add(quadLabel);
        quadrimestrePanel.add(quadComboBox);
        JButton viewReportButton = new JButton("View Report Card");
        viewReportButton.setBackground(new Color(0, 102, 204));
        viewReportButton.setForeground(Color.WHITE);
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(viewReportButton);
        buttonPanel.add(backButton);
        buttonPanel.setBackground(new Color(240, 240, 240));
        // Main panel layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(studentScrollPane, BorderLayout.CENTER);
        mainPanel.add(quadrimestrePanel, BorderLayout.SOUTH);
        mainPanel.setBackground(new Color(240, 240, 240));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(mainPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        // View Report button action
        viewReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = studentList.getSelectedIndex();
                if (selectedIndex != -1) {
                    selectedStudent = students.get(selectedIndex);
                    selectedQuadrimestre = (String) quadComboBox.getSelectedItem();
                    studentDialog.dispose();
                    displayReportCard();
                } else {
                    JOptionPane.showMessageDialog(studentDialog,
                            "Please select a student.",
                            "Selection Required",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        // Back button action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentDialog.dispose();
                displayClassesForYear();
            }
        });
        studentDialog.add(panel);
        studentDialog.setVisible(true);
    }
    private void displayReportCard() {
        // Get report card from database
        String yearKey = selectedYear.toString().split(" ")[0];
        ReportCard reportCard = database.getReportCard(
                selectedStudent.getId(), yearKey, selectedQuadrimestre);
        if (reportCard == null) {
            int option = JOptionPane.showOptionDialog(this,
                    "No report card found for the selected criteria.\n\n" +
                            "Student: " + selectedStudent.getFullName() + "\n" +
                            "Academic Year: " + yearKey + "\n" +
                            "Quadrimestre: " + selectedQuadrimestre + "\n\n" +
                            "Would you like to return and select different criteria?",
                    "Report Card Not Found",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    new String[]{"Select Again", "Main Menu"},
                    "Select Again");
            if (option == JOptionPane.YES_OPTION) {
                displayStudentsInClass();
            } else {
                selectAcademicYear();
            }
            return;
        }
        // Create dialog for report card display
        JDialog reportDialog = new JDialog(this, "Student Report Card", true);
        reportDialog.setSize(700, 600);
        reportDialog.setLocationRelativeTo(this);
        reportDialog.setLayout(new BorderLayout(10, 10));
        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(240, 240, 240));
        // Header information - centered in a visually appealing panel
        JPanel headerPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        headerPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 102, 204), 2),
                "Student Information"));
        headerPanel.setBackground(Color.WHITE);
        JLabel studentLabel = new JLabel("Student: " + reportCard.getStudentName(), SwingConstants.CENTER);
        studentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel yearLabel = new JLabel("Academic Year: " + reportCard.getAcademicYear(), SwingConstants.CENTER);
        yearLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        JLabel quadLabel = new JLabel("Quadrimestre: " + reportCard.getQuadrimestre(), SwingConstants.CENTER);
        quadLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        JLabel classLabel = new JLabel("Class: " + selectedClass.getName(), SwingConstants.CENTER);
        classLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        headerPanel.add(studentLabel);
        headerPanel.add(yearLabel);
        headerPanel.add(quadLabel);
        headerPanel.add(classLabel);
        // Subject grades table with better formatting
        String[] columnNames = {"Subject", "Code", "Grade", "Letter", "Teacher", "Comments"};
        List<SubjectGrade> subjectGrades = reportCard.getSubjectGrades();
        Object[][] data = new Object[subjectGrades.size()][6];
        for (int i = 0; i < subjectGrades.size(); i++) {
            SubjectGrade sg = subjectGrades.get(i);
            data[i][0] = sg.getSubjectName();
            data[i][1] = sg.getSubjectCode();
            data[i][2] = String.format("%.2f", sg.getGrade());
            data[i][3] = sg.getGradeLetter();
            data[i][4] = sg.getTeacher();
            data[i][5] = sg.getComments();
        }
        JTable gradesTable = new JTable(data, columnNames);
        gradesTable.setFont(new Font("Arial", Font.PLAIN, 12));
        gradesTable.setRowHeight(30);
        gradesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        gradesTable.getTableHeader().setBackground(new Color(0, 102, 204));
        gradesTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane tableScrollPane = new JScrollPane(gradesTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 102, 204), 2),
                "Subject Grades"));
        // Comments and summary section
        JPanel summaryPanel = new JPanel(new BorderLayout(10, 10));
        summaryPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 102, 204), 2),
                "Summary and Comments"));
        // Average grade display
        double averageGrade = reportCard.calculateAverageGrade();
        JPanel averagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        averagePanel.setBackground(Color.WHITE);
        JLabel averageLabel = new JLabel("Overall Average: " + String.format("%.2f", averageGrade) +
                " (" + reportCard.getAverageGradeLetter() + ")");
        averageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        averageLabel.setForeground(new Color(0, 102, 0));
        averagePanel.add(averageLabel);
        // Comments panel
        JPanel commentsPanel = new JPanel(new GridLayout(2, 1, 5, 10));
        commentsPanel.setBackground(Color.WHITE);
        // Teacher comments
        JLabel teacherTitle = new JLabel("Teacher Comments:");
        teacherTitle.setFont(new Font("Arial", Font.BOLD, 12));
        JTextArea teacherComments = new JTextArea(reportCard.getTeacherComments(), 3, 30);
        teacherComments.setEditable(false);
        teacherComments.setLineWrap(true);
        teacherComments.setWrapStyleWord(true);
        teacherComments.setFont(new Font("Arial", Font.PLAIN, 12));
        teacherComments.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        // Principal comments
        JLabel principalTitle = new JLabel("Principal Comments:");
        principalTitle.setFont(new Font("Arial", Font.BOLD, 12));
        JTextArea principalComments = new JTextArea(reportCard.getPrincipalComments(), 2, 30);
        principalComments.setEditable(false);
        principalComments.setLineWrap(true);
        principalComments.setWrapStyleWord(true);
        principalComments.setFont(new Font("Arial", Font.PLAIN, 12));
        principalComments.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        commentsPanel.add(createCommentPanel(teacherTitle, teacherComments));
        commentsPanel.add(createCommentPanel(principalTitle, principalComments));
        summaryPanel.add(averagePanel, BorderLayout.NORTH);
        summaryPanel.add(commentsPanel, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
        JButton printButton = new JButton("Print Report");
        printButton.setBackground(new Color(0, 102, 204));
        printButton.setForeground(Color.WHITE);
        JButton closeButton = new JButton("Close");
        closeButton.setBackground(new Color(153, 0, 0));
        closeButton.setForeground(Color.WHITE);
        JButton anotherButton = new JButton("View Another");
        anotherButton.setBackground(new Color(0, 153, 76));
        anotherButton.setForeground(Color.WHITE);
        buttonPanel.add(printButton);
        buttonPanel.add(closeButton);
        buttonPanel.add(anotherButton);
        // Main layout assembly
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(summaryPanel, BorderLayout.SOUTH);
        reportDialog.add(mainPanel, BorderLayout.CENTER);
        reportDialog.add(buttonPanel, BorderLayout.SOUTH);
        // Print button action (simulated)
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(reportDialog,
                        "Report card printing function would be implemented here.\n" +
                                "This would typically generate a PDF or print to a printer.",
                        "Print Function",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // Close button action
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportDialog.dispose();
                handleConnectionStop();
                int option = JOptionPane.showConfirmDialog(LoginFrame.this,
                        "Report card displayed successfully. Do you want to log out?",
                        "Session Complete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                    new LoginFrame().setVisible(true);
                }
            }
        });
        // View Another button action
        anotherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportDialog.dispose();
                selectAcademicYear();
            }
        });
        // Add window listener to handle connection stop when dialog closes
        reportDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleConnectionStop();
            }
        });
        reportDialog.setVisible(true);
    }
    private JPanel createCommentPanel(JLabel title, JTextArea textArea) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        panel.add(title, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }
    private void handleConnectionStop() {
        // Simulate stopping connection to SMOS server
        System.out.println("Connection to SMOS server stopped.");
        // In a real application, this would close database connections,
        // release resources, and perform proper cleanup
    }
}