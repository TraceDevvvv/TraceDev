/**
 * This class represents the login frame of the application.
 * It simulates a necessary precondition: "The user is logged in to the system with the administrator role."
 * For simplicity, it provides a direct "Login as Administrator" button.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame {
    private TeachingService teachingService; // Inject service to connect SMOS after login
    /**
     * Constructs the LoginFrame.
     *
     * @param teachingService The service to manage teaching data and simulated SMOS connection.
     */
    public LoginFrame(TeachingService teachingService) {
        this.teachingService = teachingService;
        setTitle("Login - Administrator Access");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        initComponents();
    }
    /**
     * Initializes the GUI components for the login frame.
     */
    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        JLabel welcomeLabel = new JLabel("Welcome! Please log in as Administrator.");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(welcomeLabel, gbc);
        JButton loginButton = new JButton("Login as Administrator");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(loginButton, gbc);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate successful administrator login
                JOptionPane.showMessageDialog(LoginFrame.this, "Administrator Login Successful!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                teachingService.connectSMOS(); // Simulate connecting to SMOS server upon successful login
                openAdminDashboard();
            }
        });
    }
    /**
     * Opens the AdminDashboardFrame and disposes of the current login frame.
     */
    private void openAdminDashboard() {
        // Ensure GUI updates happen on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            AdminDashboardFrame adminDashboard = new AdminDashboardFrame(teachingService);
            adminDashboard.setVisible(true);
            dispose(); // Close login window
        });
    }
}