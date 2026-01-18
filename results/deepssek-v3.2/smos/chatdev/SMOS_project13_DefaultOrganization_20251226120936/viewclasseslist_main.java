'''
Main class for the ViewClassesList use case.
This program provides a GUI for an administrator to view the list of classes.
It enforces authentication and includes session management for administrator role.
Simulates connection to SMOS server and handles explicit interruption.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class ViewClassesList {
    private static final String[] ACADEMIC_YEARS = {
        "2020-2021", "2021-2022", "2022-2023", "2023-2024", "2024-2025"
    };
    private static final String[][] CLASSES_BY_YEAR = {
        {"CS101", "MATH201", "ENG102", "PHY101"},
        {"CS102", "MATH202", "ENG103", "CHEM101"},
        {"CS201", "MATH301", "ENG201", "BIO101"},
        {"CS301", "MATH401", "ENG301", "HIST101"},
        {"CS401", "MATH501", "ENG401", "ART101"}
    };
    private JFrame frame;
    private JComboBox<String> yearComboBox;
    private JTextArea classesTextArea;
    private JButton viewButton;
    private JButton cancelButton;
    private JButton logoutButton;
    private JLabel statusLabel;
    private JLabel sessionLabel;
    private ExecutorService executor;
    private Future<?> currentTask;
    private volatile boolean connectionInterrupted = false;
    private String currentUser;
    private LocalDateTime sessionStartTime;
    private boolean administratorRole = false;
    public ViewClassesList(String username) {
        this.currentUser = username;
        this.sessionStartTime = LocalDateTime.now();
        this.administratorRole = true; // Only administrators reach this point
        initialize();
        displayClassManagementScreen();
    }
    private void initialize() {
        frame = new JFrame("Class Management - View Classes List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        // Top panel with session info and logout
        JPanel sessionPanel = new JPanel(new BorderLayout());
        sessionPanel.setBorder(BorderFactory.createEtchedBorder());
        // Session info
        String sessionTime = sessionStartTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        sessionLabel = new JLabel("User: " + currentUser + " | Session started: " + sessionTime);
        sessionPanel.add(sessionLabel, BorderLayout.WEST);
        // Logout button
        logoutButton = new JButton("Logout");
        sessionPanel.add(logoutButton, BorderLayout.EAST);
        frame.add(sessionPanel, BorderLayout.NORTH);
        // Control panel
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(new JLabel("Select Academic Year: "));
        yearComboBox = new JComboBox<>(ACADEMIC_YEARS);
        controlPanel.add(yearComboBox);
        viewButton = new JButton("View Classes");
        controlPanel.add(viewButton);
        cancelButton = new JButton("Cancel Operation");
        cancelButton.setEnabled(false);
        controlPanel.add(cancelButton);
        frame.add(controlPanel, BorderLayout.BEFORE_FIRST_LINE);
        // Classes display area
        classesTextArea = new JTextArea();
        classesTextArea.setEditable(false);
        classesTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        frame.add(new JScrollPane(classesTextArea), BorderLayout.CENTER);
        // Status bar
        statusLabel = new JLabel("Ready. User role: Administrator");
        statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        frame.add(statusLabel, BorderLayout.SOUTH);
        // Event listeners
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verify administrator role before proceeding
                if (!administratorRole) {
                    JOptionPane.showMessageDialog(frame,
                        "Access denied: Administrator role required",
                        "Permission Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                viewClassesForSelectedYear();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interruptOperation();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        executor = Executors.newSingleThreadExecutor();
        frame.setVisible(true);
    }
    private void displayClassManagementScreen() {
        statusLabel.setText("Class Management Screen - Select an academic year to view classes (Administrator)");
        classesTextArea.setText("╔═════════════════════════════════════════════════════╗\n" +
                               "║           CLASS MANAGEMENT SYSTEM                   ║\n" +
                               "╚═════════════════════════════════════════════════════╝\n\n" +
                               "Welcome Administrator: " + currentUser + "\n\n" +
                               "INSTRUCTIONS:\n" +
                               "1. Select an academic year from the dropdown.\n" +
                               "2. Click 'View Classes' to see the list.\n" +
                               "3. Use 'Cancel Operation' to interrupt ongoing search.\n" +
                               "4. Archive search will be performed upon viewing.\n\n" +
                               "Your current role: Administrator ✓\n" +
                               "Session started: " + 
                               sessionStartTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
    private void viewClassesForSelectedYear() {
        if (currentTask != null && !currentTask.isDone()) {
            currentTask.cancel(true);
        }
        String selectedYear = (String) yearComboBox.getSelectedItem();
        if (selectedYear == null) {
            statusLabel.setText("No academic year selected.");
            return;
        }
        viewButton.setEnabled(false);
        cancelButton.setEnabled(true);
        logoutButton.setEnabled(false);
        connectionInterrupted = false;
        statusLabel.setText("Searching archive for classes in " + selectedYear + "... (Connected to SMOS)");
        currentTask = executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Simulate archive search delay (2 seconds)
                    Thread.sleep(2000);
                    // Check for SMOS server interruption
                    if (connectionInterrupted) {
                        throw new RuntimeException("Connection to the SMOS server interrupted");
                    }
                    // Random SMOS connection failure simulation (10% chance)
                    if (Math.random() < 0.1) {
                        throw new RuntimeException("SMOS server connection lost - Network timeout");
                    }
                    // Find index of selected academic year
                    int yearIndex = -1;
                    for (int i = 0; i < ACADEMIC_YEARS.length; i++) {
                        if (ACADEMIC_YEARS[i].equals(selectedYear)) {
                            yearIndex = i;
                            break;
                        }
                    }
                    // Handle case when selected academic year is not found in archive
                    if (yearIndex == -1) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                classesTextArea.setText("ARCHIVE SEARCH RESULTS\n" +
                                                       "══════════════════════\n" +
                                                       "Academic Year: " + selectedYear + "\n" +
                                                       "Status: Not found in SMOS archive\n\n" +
                                                       "Please verify the academic year selection.");
                                statusLabel.setText("Search complete - Year not found in archive");
                                viewButton.setEnabled(true);
                                cancelButton.setEnabled(false);
                                logoutButton.setEnabled(true);
                            }
                        });
                        return;
                    }
                    // Retrieve classes for the selected year
                    final List<String> classes = new ArrayList<>();
                    if (yearIndex >= 0 && yearIndex < CLASSES_BY_YEAR.length) {
                        for (String className : CLASSES_BY_YEAR[yearIndex]) {
                            classes.add(className);
                        }
                    }
                    // Update UI with results
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder sb = new StringBuilder();
                            sb.append("ARCHIVE SEARCH RESULTS\n");
                            sb.append("══════════════════════\n");
                            sb.append("Academic Year: ").append(selectedYear).append("\n");
                            sb.append("Search Time: ").append(LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("HH:mm:ss"))).append("\n");
                            sb.append("Classes Found: ").append(classes.size()).append("\n\n");
                            if (classes.isEmpty()) {
                                sb.append("No active classes found for ").append(selectedYear).append("\n");
                                sb.append("This academic year may not have been populated yet.");
                            } else {
                                sb.append("CLASS LIST:\n");
                                for (int i = 0; i < classes.size(); i++) {
                                    sb.append(String.format("%3d. %-10s\n", i + 1, classes.get(i)));
                                }
                                sb.append("\nTotal: ").append(classes.size()).append(" classes");
                            }
                            classesTextArea.setText(sb.toString());
                            statusLabel.setText("Ready. Results displayed for " + selectedYear);
                            viewButton.setEnabled(true);
                            cancelButton.setEnabled(false);
                            logoutButton.setEnabled(true);
                        }
                    });
                } catch (InterruptedException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            statusLabel.setText("Operation interrupted by administrator");
                            classesTextArea.setText("OPERATION INTERRUPTED\n" +
                                                   "══════════════════════\n" +
                                                   "Search was cancelled by user.\n" +
                                                   "No results available.");
                            viewButton.setEnabled(true);
                            cancelButton.setEnabled(false);
                            logoutButton.setEnabled(true);
                        }
                    });
                } catch (Exception e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            classesTextArea.setText("SYSTEM ERROR\n" +
                                                   "════════════\n" +
                                                   "Error Type: " + e.getClass().getSimpleName() + "\n" +
                                                   "Message: " + e.getMessage() + "\n\n" +
                                                   "Please check SMOS server connection and try again.");
                            statusLabel.setText("Error: " + e.getMessage());
                            viewButton.setEnabled(true);
                            cancelButton.setEnabled(false);
                            logoutButton.setEnabled(true);
                        }
                    });
                }
            }
        });
    }
    private void interruptOperation() {
        if (currentTask != null && !currentTask.isDone()) {
            currentTask.cancel(true);
            connectionInterrupted = true;
            statusLabel.setText("Administrator interrupted the operation");
            classesTextArea.append("\n\n[OPERATION CANCELLED BY USER]");
            cancelButton.setEnabled(false);
            viewButton.setEnabled(true);
            logoutButton.setEnabled(true);
        }
    }
    private void logout() {
        int response = JOptionPane.showConfirmDialog(frame,
            "Are you sure you want to logout?\nSession will be terminated.",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            shutdown();
            // Return to login screen
            SwingUtilities.invokeLater(() -> {
                new AdminLogin();
            });
        }
    }
    public void shutdown() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdownNow();
            try {
                executor.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (frame != null) {
            frame.dispose();
        }
    }
    public JFrame getFrame() {
        return frame;
    }
    public static void main(String[] args) {
        // Direct execution without login (for testing)
        // In production, start with AdminLogin.main()
        JOptionPane.showMessageDialog(null,
            "This application requires login authentication.\n" +
            "For proper execution, run the AdminLogin class.",
            "Launch Error",
            JOptionPane.INFORMATION_MESSAGE);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminLogin();
            }
        });
    }
}