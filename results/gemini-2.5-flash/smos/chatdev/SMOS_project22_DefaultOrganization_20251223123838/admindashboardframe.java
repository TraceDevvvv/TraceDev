/**
 * This class displays the main dashboard for an administrator.
 * It fulfills the precondition: "The user click on the 'Management Management' button."
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AdminDashboardFrame extends JFrame {
    private TeachingService teachingService;
    /**
     * Constructs the AdminDashboardFrame.
     *
     * @param teachingService The service to manage teaching data and simulated SMOS connection.
     */
    public AdminDashboardFrame(TeachingService teachingService) {
        this.teachingService = teachingService;
        setTitle("Administrator Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        initComponents();
    }
    /**
     * Initializes the GUI components for the administrator dashboard.
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Administrator Control Panel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Center alignment with spacing
        // Button corresponding to "Management Management"
        JButton managementButton = new JButton("Management Management");
        managementButton.setFont(new Font("Arial", Font.PLAIN, 18));
        managementButton.setPreferredSize(new Dimension(250, 60)); // Make button larger
        buttonPanel.add(managementButton);
        // Add other placeholder buttons for a more complete dashboard feel (optional)
        buttonPanel.add(new JButton("User Accounts"));
        buttonPanel.add(new JButton("System Settings"));
        add(buttonPanel, BorderLayout.CENTER);
        // Action listener for the "Management Management" button
        managementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Event sequence 1: User looks for archive and teacher screen
                System.out.println("User clicked 'Management Management' button.");
                openTeachingsList();
            }
        });
    }
    /**
     * Opens the TeachingsListFrame and disposes of the current dashboard frame.
     */
    private void openTeachingsList() {
        // Ensure GUI updates happen on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            TeachingsListFrame teachingsListFrame = new TeachingsListFrame(teachingService);
            teachingsListFrame.setVisible(true);
            dispose(); // Close admin dashboard
        });
    }
}