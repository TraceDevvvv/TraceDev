/**
 * Main class to launch the ATA Delay Insertion Application.
 * This program provides a GUI for ATA staff to register delays for students in a class.
 * It simulates the frontend part of the InsertDelayAta use case, including a mock server connection.
 */
public class InsertDelayAtaApp {
    public static void main(String[] args) {
        // Launch the GUI on the Event Dispatch Thread for thread safety
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    /**
     * Creates and displays the main GUI frame.
     */
    private static void createAndShowGUI() {
        // Create the main frame
        javax.swing.JFrame frame = new javax.swing.JFrame("ATA Delay Registration");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        // Create the main panel with a BorderLayout
        javax.swing.JPanel mainPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        frame.getContentPane().add(mainPanel);
        // Create a label for the title
        javax.swing.JLabel titleLabel = new javax.swing.JLabel("Insert Delay for ATA Class", javax.swing.SwingConstants.CENTER);
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        mainPanel.add(titleLabel, java.awt.BorderLayout.NORTH);
        // Create the central panel to hold student rows
        javax.swing.JPanel studentsPanel = new javax.swing.JPanel();
        studentsPanel.setLayout(new javax.swing.BoxLayout(studentsPanel, javax.swing.BoxLayout.Y_AXIS));
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(studentsPanel);
        mainPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
        // Simulate data: list of students (in a real scenario, this would come from a database)
        String[] studentNames = {"Alice Smith", "Bob Johnson", "Charlie Brown", "Diana Prince", "Evan Davis"};
        // This list will hold references to the delay time fields for each student (null if not delayed)
        java.util.List<javax.swing.JSpinner> delaySpinners = new java.util.ArrayList<>();
        // For each student, create a row with name, delay checkbox, and time spinner (initially disabled)
        for (String name : studentNames) {
            javax.swing.JPanel rowPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
            // Label for student name
            javax.swing.JLabel nameLabel = new javax.swing.JLabel(name);
            nameLabel.setPreferredSize(new java.awt.Dimension(150, 30));
            rowPanel.add(nameLabel);
            // Checkbox for delay
            javax.swing.JCheckBox delayCheckBox = new javax.swing.JCheckBox("Delay");
            rowPanel.add(delayCheckBox);
            // Spinner for selecting delay time (in minutes)
            javax.swing.SpinnerModel spinnerModel = new javax.swing.SpinnerNumberModel(0, 0, 180, 1); // min 0, max 180, step 1
            javax.swing.JSpinner delaySpinner = new javax.swing.JSpinner(spinnerModel);
            delaySpinner.setEnabled(false); // Disabled initially
            delaySpinner.setPreferredSize(new java.awt.Dimension(80, 30));
            rowPanel.add(delaySpinner);
            delaySpinners.add(delaySpinner); // Add to list for later retrieval
            // Add listener to enable/disable spinner based on checkbox
            delayCheckBox.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    delaySpinner.setEnabled(delayCheckBox.isSelected());
                }
            });
            studentsPanel.add(rowPanel);
        }
        // Create a panel for the Confirm button
        javax.swing.JPanel buttonPanel = new javax.swing.JPanel();
        javax.swing.JButton confirmButton = new javax.swing.JButton("Confirm");
        buttonPanel.add(confirmButton);
        mainPanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);
        // Add action listener for the Confirm button
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Gather data: for each student, check if delayed and get the delay time
                java.util.List<StudentDelayRecord> delayRecords = new java.util.ArrayList<>();
                for (int i = 0; i < studentNames.length; i++) {
                    javax.swing.JSpinner spinner = delaySpinners.get(i);
                    boolean isDelayed = spinner.isEnabled(); // enabled only if checkbox selected
                    if (isDelayed) {
                        int delayMinutes = (Integer) spinner.getValue();
                        delayRecords.add(new StudentDelayRecord(studentNames[i], delayMinutes));
                    }
                }
                // Send data to the server (simulated)
                boolean success = sendDelayDataToServer(delayRecords);
                // Show message to user
                if (success) {
                    javax.swing.JOptionPane.showMessageDialog(frame, "Delay data successfully sent to server.", "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    // Reset the form to initial state (as per postconditions: initial screen shown again)
                    resetForm(studentsPanel, delaySpinners);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(frame, "Error: Could not connect to server. Please try again.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Display the frame
        frame.setVisible(true);
    }
    /**
     * Resets the form to its initial state (unchecked checkboxes, disabled spinners, zero values).
     *
     * @param studentsPanel the panel containing all student rows
     * @param delaySpinners list of spinner components for delay time
     */
    private static void resetForm(javax.swing.JPanel studentsPanel, java.util.List<javax.swing.JSpinner> delaySpinners) {
        // Iterate over each component in the studentsPanel
        for (java.awt.Component comp : studentsPanel.getComponents()) {
            if (comp instanceof javax.swing.JPanel) {
                javax.swing.JPanel rowPanel = (javax.swing.JPanel) comp;
                // Each row panel contains: label, checkbox, spinner
                for (java.awt.Component rowComp : rowPanel.getComponents()) {
                    if (rowComp instanceof javax.swing.JCheckBox) {
                        javax.swing.JCheckBox checkBox = (javax.swing.JCheckBox) rowComp;
                        checkBox.setSelected(false);
                    } else if (rowComp instanceof javax.swing.JSpinner) {
                        javax.swing.JSpinner spinner = (javax.swing.JSpinner) rowComp;
                        spinner.setValue(0);
                        spinner.setEnabled(false);
                    }
                }
            }
        }
    }
    /**
     * Simulates sending delay data to a server.
     * In a real application, this would involve network communication.
     *
     * @param delayRecords list of StudentDelayRecord objects
     * @return true if the data was sent successfully, false otherwise (simulated random failure for demonstration)
     */
    private static boolean sendDelayDataToServer(java.util.List<StudentDelayRecord> delayRecords) {
        // Simulate a server connection with a random success/failure (for demo purposes)
        // In a real app, you would use HTTP requests, sockets, etc.
        try {
            // Simulate network delay
            Thread.sleep(1000);
            // Randomly fail 10% of the time to demonstrate error handling
            if (Math.random() < 0.1) {
                return false;
            }
            // Here you would actually send the data. For now, just print to console.
            System.out.println("Sending delay data to server:");
            for (StudentDelayRecord record : delayRecords) {
                System.out.println("  Student: " + record.studentName + ", Delay: " + record.delayMinutes + " minutes");
            }
            return true;
        } catch (InterruptedException e) {
            // If connection is interrupted (simulating SMOS server interruption)
            System.err.println("Connection to server interrupted.");
            return false;
        }
    }
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
}