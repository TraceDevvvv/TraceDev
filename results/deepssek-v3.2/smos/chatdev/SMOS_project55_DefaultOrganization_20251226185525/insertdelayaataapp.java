'''
Main class to launch the ATA Delay Insertion Application.
This program provides a GUI for ATA staff to register delays for students in a class.
It simulates the frontend part of the InsertDelayAta use case, including a mock server connection.
'''
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class InsertDelayAtaApp {
    /**
     * Helper class to represent a student's delay record.
     */
    static class StudentDelayRecord {
        String studentName;
        int delayMinutes;
        StudentDelayRecord(String studentName, int delayMinutes) {
            this.studentName = studentName;
            this.delayMinutes = delayMinutes;
        }
    }
    // Class-level variables to hold student data
    private static String[] studentNames = {"Alice Smith", "Bob Johnson", "Charlie Brown", "Diana Prince", "Evan Davis"};
    private static List<JSpinner> delaySpinners = new ArrayList<>();
    private static List<JCheckBox> delayCheckBoxes = new ArrayList<>();
    private static JFrame frame;
    private static JPanel studentsPanel;
    /**
     * Creates and displays the main GUI frame.
     */
    private static void createAndShowGUI() {
        // Create the main frame
        frame = new JFrame("ATA Delay Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        // Create the main panel with a BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.getContentPane().add(mainPanel);
        // Create a label for the title
        JLabel titleLabel = new JLabel("Insert Delay for ATA Class", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Create the central panel to hold student rows
        studentsPanel = new JPanel();
        studentsPanel.setLayout(new BoxLayout(studentsPanel, BoxLayout.Y_AXIS));
        studentsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(studentsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Clear previous lists and create student rows
        delaySpinners.clear();
        delayCheckBoxes.clear();
        createStudentRows();
        // Create a panel for the Confirm button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setPreferredSize(new Dimension(120, 40));
        buttonPanel.add(confirmButton);
        // Add Cancel button for interruption simulation
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
        cancelButton.setPreferredSize(new Dimension(100, 35));
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Add action listener for the Confirm button
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gather data: for each student, check if delayed and get the delay time
                List<StudentDelayRecord> delayRecords = new ArrayList<>();
                for (int i = 0; i < studentNames.length; i++) {
                    JSpinner spinner = delaySpinners.get(i);
                    JCheckBox checkBox = delayCheckBoxes.get(i);
                    boolean isDelayed = checkBox.isSelected();
                    if (isDelayed) {
                        int delayMinutes = (Integer) spinner.getValue();
                        // Only record if delay is > 0 (edge case handling)
                        if (delayMinutes > 0) {
                            delayRecords.add(new StudentDelayRecord(studentNames[i], delayMinutes));
                        }
                    }
                }
                // Send data to the server (simulated)
                boolean success = sendDelayDataToServer(delayRecords);
                // Show message to user
                if (success) {
                    JOptionPane.showMessageDialog(frame,
                        "Delay data successfully sent to server.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    // Reset the form to initial state (as per postconditions: initial screen shown again)
                    resetForm();
                } else {
                    int option = JOptionPane.showConfirmDialog(frame,
                        "Error: Could not connect to server. Please try again.\n" +
                        "Simulating SMOS server interruption.\n\n" +
                        "Do you want to retry?",
                        "Error",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE);
                    if (option == JOptionPane.NO_OPTION) {
                        resetForm();
                    }
                }
            }
        });
        // Add action listener for the Cancel button (simulates user interruption)
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to cancel? All unsaved data will be lost.",
                    "Confirm Cancellation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame,
                        "Operations interrupted by user. Returning to initial screen.",
                        "Operation Cancelled",
                        JOptionPane.INFORMATION_MESSAGE);
                    resetForm();
                }
            }
        });
        // Center the frame on screen
        frame.setLocationRelativeTo(null);
        // Display the frame
        frame.setVisible(true);
    }
    /**
     * Creates rows for each student with name, checkbox, and spinner.
     * Handles edge case where studentNames might be empty or null.
     */
    private static void createStudentRows() {
        // Remove all existing components
        studentsPanel.removeAll();
        // Edge case handling: if studentNames is null or empty, show message
        if (studentNames == null || studentNames.length == 0) {
            JLabel noStudentsLabel = new JLabel("No students found for this class.", SwingConstants.CENTER);
            noStudentsLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            studentsPanel.add(noStudentsLabel);
            studentsPanel.revalidate();
            studentsPanel.repaint();
            return;
        }
        // For each student, create a row with name, delay checkbox, and time spinner (initially disabled)
        for (String name : studentNames) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            // Label for student name
            JLabel nameLabel = new JLabel(name);
            nameLabel.setPreferredSize(new Dimension(150, 30));
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            rowPanel.add(nameLabel);
            // Checkbox for delay
            JCheckBox delayCheckBox = new JCheckBox("Delay");
            delayCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
            rowPanel.add(delayCheckBox);
            delayCheckBoxes.add(delayCheckBox);
            // Spinner for selecting delay time (in minutes)
            SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 180, 1); // min 0, max 180, step 1
            JSpinner delaySpinner = new JSpinner(spinnerModel);
            delaySpinner.setEnabled(false); // Disabled initially
            delaySpinner.setPreferredSize(new Dimension(80, 30));
            // Create a custom editor for better appearance
            JSpinner.NumberEditor editor = new JSpinner.NumberEditor(delaySpinner, "#");
            delaySpinner.setEditor(editor);
            rowPanel.add(delaySpinner);
            delaySpinners.add(delaySpinner); // Add to list for later retrieval
            // Add label for minutes
            JLabel minutesLabel = new JLabel("minutes");
            minutesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            rowPanel.add(minutesLabel);
            // Add listener to enable/disable spinner based on checkbox
            delayCheckBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    delaySpinner.setEnabled(delayCheckBox.isSelected());
                    if (!delayCheckBox.isSelected()) {
                        delaySpinner.setValue(0); // Reset to 0 when unchecked
                    }
                }
            });
            // Add some space between rows
            rowPanel.add(Box.createHorizontalStrut(20));
            studentsPanel.add(rowPanel);
            studentsPanel.add(Box.createVerticalStrut(10));
        }
        // Revalidate and repaint the panel
        studentsPanel.revalidate();
        studentsPanel.repaint();
    }
    /**
     * Resets the form to its initial state (unchecked checkboxes, disabled spinners, zero values).
     */
    private static void resetForm() {
        for (int i = 0; i < delayCheckBoxes.size(); i++) {
            JCheckBox checkBox = delayCheckBoxes.get(i);
            JSpinner spinner = delaySpinners.get(i);
            checkBox.setSelected(false);
            spinner.setValue(0);
            spinner.setEnabled(false);
        }
    }
    /**
     * Simulates sending delay data to a server.
     * In a real application, this would involve network communication.
     *
     * @param delayRecords list of StudentDelayRecord objects
     * @return true if the data was sent successfully, false otherwise (simulated random failure for demonstration)
     */
    private static boolean sendDelayDataToServer(List<StudentDelayRecord> delayRecords) {
        // Simulate a server connection with a random success/failure (for demo purposes)
        // In a real app, you would use HTTP requests, sockets, etc.
        try {
            // Create progress dialog (but don't show it yet - EDT will handle it)
            final JDialog progressDialog = new JDialog(frame, "Sending Data", true);
            JProgressBar progressBar = new JProgressBar();
            progressBar.setIndeterminate(true);
            JLabel messageLabel = new JLabel("Connecting to SMOS server...");
            JPanel progressPanel = new JPanel(new BorderLayout());
            progressPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
            progressPanel.add(messageLabel, BorderLayout.NORTH);
            progressPanel.add(progressBar, BorderLayout.CENTER);
            progressDialog.getContentPane().add(progressPanel);
            progressDialog.setSize(300, 120);
            progressDialog.setLocationRelativeTo(frame);
            // Show progress dialog on the EDT for thread safety
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    progressDialog.setVisible(true);
                }
            });
            // Simulate network delay
            Thread.sleep(1500);
            // Simulate SMOS server interruption 20% of the time (edge case)
            if (Math.random() < 0.2) {
                // Close dialog on EDT
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dispose();
                    }
                });
                throw new InterruptedException("SMOS server connection interrupted");
            }
            // Here you would actually send the data. For now, just print to console.
            System.out.println("Sending delay data to server:");
            if (delayRecords.isEmpty()) {
                System.out.println("  No delays recorded.");
            } else {
                for (StudentDelayRecord record : delayRecords) {
                    System.out.println("  Student: " + record.studentName +
                                     ", Delay: " + record.delayMinutes + " minutes");
                }
            }
            // Close progress dialog on EDT
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dispose();
                }
            });
            // Simulate successful data entry
            System.out.println("Delay data has been entered in the system.");
            return true;
        } catch (InterruptedException e) {
            // If connection is interrupted (simulating SMOS server interruption)
            System.err.println("Connection to SMOS server interrupted: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            return false;
        }
    }
    /**
     * Main method to launch the ATA Delay Insertion Application.
     * This program provides a GUI for ATA staff to register delays for students in a class.
     * It simulates the frontend part of the InsertDelayAta use case, including a mock server connection.
     */
    public static void main(String[] args) {
        // Launch the GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}