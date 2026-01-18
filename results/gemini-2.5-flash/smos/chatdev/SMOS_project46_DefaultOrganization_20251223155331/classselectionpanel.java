/**
 * Displays a list of school classes.
 * Allows the administrator to choose a pupil class to insert a report card.
 * (Corresponds to System event 1 and User event 2 in the use case).
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class ClassSelectionPanel extends JPanel {
    private Main mainFrame;
    private ReportCardSystem system;
    private JPanel classListPanel; // Panel to hold class buttons
    public ClassSelectionPanel(Main mainFrame, ReportCardSystem system) {
        this.mainFrame = mainFrame;
        this.system = system;
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall layout
        JLabel titleLabel = new JLabel("Select a Class to Manage Report Cards", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        classListPanel = new JPanel();
        classListPanel.setLayout(new BoxLayout(classListPanel, BoxLayout.Y_AXIS)); // Stack classes vertically
        JScrollPane scrollPane = new JScrollPane(classListPanel); // Make it scrollable
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove default scroll pane border
        add(scrollPane, BorderLayout.CENTER);
        // Add a back button to return to the admin dashboard
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> mainFrame.showPanel(Main.ADMIN_DASHBOARD));
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(backButton);
        add(southPanel, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding to the panel
    }
    /**
     * Loads and displays the list of classes from the ReportCardSystem.
     * Each class will have a button to view its report cards.
     */
    public void loadClasses() {
        classListPanel.removeAll(); // Clear previous class list
        List<SchoolClass> classes = system.getClasses();
        if (classes.isEmpty()) {
            classListPanel.add(new JLabel("No classes found in the system."));
        } else {
            for (SchoolClass schoolClass : classes) {
                JPanel classRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel classNameLabel = new JLabel("Class: " + schoolClass.getName() + " (" + schoolClass.getAcademicYear() + ")");
                classNameLabel.setPreferredSize(new Dimension(300, 30)); // Fixed size for alignment
                classNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                JButton reportCardsButton = new JButton("Manage Report Cards");
                // Store classId to be used in the action listener
                reportCardsButton.putClientProperty("classId", schoolClass.getId());
                reportCardsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedClassId = (String) ((JButton) e.getSource()).getClientProperty("classId");
                        // Navigate to the student selection view for the chosen class
                        mainFrame.showStudentSelection(selectedClassId);
                    }
                });
                classRowPanel.add(classNameLabel);
                classRowPanel.add(reportCardsButton);
                classListPanel.add(classRowPanel);
            }
        }
        revalidate(); // Re-layout the panel
        repaint();    // Repaint to show changes
    }
}