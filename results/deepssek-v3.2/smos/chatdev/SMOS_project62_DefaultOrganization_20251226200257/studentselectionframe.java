'''
StudentSelectionFrame.java
GUI for parent to select which child's report to view using individual buttons (Magine buttons).
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class StudentSelectionFrame extends JFrame {
    private String parentUsername;
    private DatabaseSimulator database;
    private JPanel buttonPanel;
    public StudentSelectionFrame(String parentUsername, DatabaseSimulator database) {
        this.parentUsername = parentUsername;
        this.database = database;
        initializeUI();
        loadChildren();
    }
    private void initializeUI() {
        setTitle("Select Student - " + parentUsername);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Instruction label
        JLabel instructionLabel = new JLabel("Click on your child's name to view their report card:");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(instructionLabel, BorderLayout.NORTH);
        // Panel for child buttons (will be populated dynamically)
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel);
    }
    /**
     * Loads the parent's children and creates individual buttons (Magine buttons) for each.
     */
    private void loadChildren() {
        String[] children = database.getChildrenForParent(parentUsername);
        if (children.length == 0) {
            JLabel noChildrenLabel = new JLabel("No children found for this parent account.");
            noChildrenLabel.setHorizontalAlignment(SwingConstants.CENTER);
            buttonPanel.add(noChildrenLabel);
        } else {
            for (String child : children) {
                // Create a button for each child with a "maginer" style
                JButton childButton = new JButton(child);
                childButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                childButton.setMaximumSize(new Dimension(300, 40));
                childButton.setFont(new Font("Arial", Font.BOLD, 14));
                childButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Simulate clicking the Magine button for the selected child
                        viewReportForChild(child);
                    }
                });
                buttonPanel.add(childButton);
                buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing between buttons
            }
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
    /**
     * Views the report card for the selected child when their Magine button is clicked.
     * @param childName The name of the child whose report to view
     */
    private void viewReportForChild(String childName) {
        // Attempt to connect to SMOS server (simulated) as per postcondition
        boolean serverConnected = database.connectToSMOSServer();
        if (!serverConnected) {
            JOptionPane.showMessageDialog(this, 
                "Note: Displaying cached report data. Some information may be outdated.", 
                "Server Warning", JOptionPane.WARNING_MESSAGE);
        }
        // Retrieve and display the report card
        ReportCard report = database.getReportCard(parentUsername, childName);
        if (report != null) {
            new ReportDisplayFrame(report).setVisible(true);
            dispose(); // Close selection window
        } else {
            JOptionPane.showMessageDialog(this, "Report card not found for " + childName, 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}