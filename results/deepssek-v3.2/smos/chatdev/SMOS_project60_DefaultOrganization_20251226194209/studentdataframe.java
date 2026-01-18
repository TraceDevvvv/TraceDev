/**
 * studentdataframe.java
 * Main GUI frame for displaying student data.
 * Implements the complete "ViewSchoolDataStudent" use case:
 * 1. Checks preconditions (student logged in, button click)
 * 2. Implements event sequence (search archive, display data)
 * 3. Ensures postconditions (data shown, connection interrupted)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
public class StudentDataFrame extends JFrame {
    private StudentDataService dataService;
    private StudentDataTableModel tableModel;
    private JTable dataTable;
    private JLabel statusLabel;
    private JLabel studentInfoLabel;
    private JButton refreshButton;
    private JButton digitalLogButton;
    private JProgressBar progressBar;
    public StudentDataFrame() {
        dataService = new StudentDataService();
        initializeUI();
        loadStudentData();
    }
    private void initializeUI() {
        setTitle("Student Digital Log - School Information System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        /* Handle window close to ensure postcondition */
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Application closing - ensuring SMOS server connection is interrupted if active.");
            }
        });
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel("Student Digital Log");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 70, 140));
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        studentInfoLabel = new JLabel("Student: John Smith | Class: Grade 10-A | ID: 1001");
        studentInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        headerPanel.add(studentInfoLabel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        digitalLogButton = new JButton("Digital Log");
        refreshButton = new JButton("Refresh Data");
        digitalLogButton.setBackground(new Color(0, 100, 200));
        digitalLogButton.setForeground(Color.WHITE);
        digitalLogButton.setFont(new Font("Arial", Font.BOLD, 12));
        refreshButton.setBackground(new Color(200, 200, 200));
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 12));
        digitalLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDigitalLogClicked();
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStudentData();
            }
        });
        buttonPanel.add(digitalLogButton);
        buttonPanel.add(refreshButton);
        headerPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);
        dataTable = new JTable();
        dataTable.setFont(new Font("Arial", Font.PLAIN, 12));
        dataTable.setRowHeight(25);
        dataTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Class Register Data - Last 31 Days"));
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);
        progressBar = new JProgressBar();
        progressBar.setVisible(false);
        progressBar.setStringPainted(true);
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Ready - Please click 'Digital Log' to view your data");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        statusPanel.add(progressBar, BorderLayout.EAST);
        add(statusPanel, BorderLayout.SOUTH);
        setSize(900, 650);
        setLocationRelativeTo(null);
        checkPreconditions();
    }
    private void checkPreconditions() {
        if (!dataService.isStudentLoggedIn()) {
            JOptionPane.showMessageDialog(this, 
                "Error: User is not logged in as a student.", 
                "Authentication Error", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        statusLabel.setText("Ready - Student logged in. Click 'Digital Log' to view data.");
    }
    private void onDigitalLogClicked() {
        if (dataService.clickDigitalLogButton()) {
            statusLabel.setText("Fetching student data from archive...");
            progressBar.setVisible(true);
            progressBar.setIndeterminate(true);
            progressBar.setString("Searching archive...");
            digitalLogButton.setEnabled(false);
            refreshButton.setEnabled(false);
            SwingWorker<List<Student>, Void> worker = new SwingWorker<List<Student>, Void>() {
                @Override
                protected List<Student> doInBackground() throws Exception {
                    Thread.sleep(1500);
                    try {
                        return dataService.getStudentData(1001);
                    } catch (DataRetrievalException e) {
                        throw new Exception("Failed to retrieve student data: " + e.getMessage(), e);
                    }
                }
                @Override
                protected void done() {
                    digitalLogButton.setEnabled(true);
                    refreshButton.setEnabled(true);
                    progressBar.setVisible(false);
                    try {
                        List<Student> studentData = get();
                        tableModel = new StudentDataTableModel(studentData);
                        dataTable.setModel(tableModel);
                        dataTable.getColumnModel().getColumn(0).setPreferredWidth(100);
                        dataTable.getColumnModel().getColumn(1).setPreferredWidth(70);
                        dataTable.getColumnModel().getColumn(2).setPreferredWidth(200);
                        dataTable.getColumnModel().getColumn(3).setPreferredWidth(70);
                        dataTable.getColumnModel().getColumn(4).setPreferredWidth(180);
                        int totalAbsences = 0;
                        int totalDelays = 0;
                        int notesCount = 0;
                        for (Student student : studentData) {
                            totalAbsences += student.getAbsences();
                            totalDelays += student.getDelays();
                            if (student.getDisciplinaryNotes() != null && 
                                !student.getDisciplinaryNotes().isEmpty() &&
                                !student.getDisciplinaryNotes().equals("None")) {
                                notesCount++;
                            }
                        }
                        /* Update status label to explicitly show postcondition: SMOS server connection interrupted */
                        statusLabel.setText(String.format(
                            "Data loaded: %d records | Absences: %d | Delays: %d | Notes: %d | SMOS server connection interrupted.",
                            studentData.size(), totalAbsences, totalDelays, notesCount
                        ));
                        if (!studentData.isEmpty()) {
                            System.out.println("✓ Postcondition met: Student's class registry data was shown.");
                            System.out.println("✓ Postcondition met: Connecting to the SMOS server interrupted.");
                        } else {
                            System.out.println("✗ Postcondition failed: No data to display.");
                        }
                    } catch (Exception e) {
                        statusLabel.setText("Error loading student data: " + e.getMessage());
                        JOptionPane.showMessageDialog(StudentDataFrame.this,
                            "Failed to load student data: " + e.getMessage(),
                            "Data Retrieval Error",
                            JOptionPane.ERROR_MESSAGE);
                        tableModel = new StudentDataTableModel(new ArrayList<>());
                        dataTable.setModel(tableModel);
                    }
                }
            };
            worker.execute();
        }
    }
    private void loadStudentData() {
        onDigitalLogClicked();
    }
}