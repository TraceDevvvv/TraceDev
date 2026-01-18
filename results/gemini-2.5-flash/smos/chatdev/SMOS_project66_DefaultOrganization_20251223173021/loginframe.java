/**
 * This class represents a simplified login window.
 * As per the use case, the user is already logged in as 'direction' and clicks the
 * "Online report cards" button. This frame simulates that initial action.
 */
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("SMOS - Report Card System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        initializeUI();
    }
    /**
     * Initializes the user interface for the login frame.
     * Provides a button to simulate clicking "Online report cards".
     */
    private void initializeUI() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Welcome to SMOS Reporting System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        // Simulate login (as per precondition, user is already logged in as 'direction')
        // We just need a button to initiate the "Online report cards" action.
        JButton reportCardsButton = new JButton("Online Report Cards");
        reportCardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the login frame
                setVisible(false);
                dispose(); // Release resources
                // Open the main report card viewing frame
                ReportCardViewFrame reportCardViewFrame = new ReportCardViewFrame();
                reportCardViewFrame.setVisible(true);
            }
        });
        panel.add(reportCardsButton, gbc);
        add(panel, BorderLayout.CENTER);
        JLabel footerLabel = new JLabel("Logged in as: Direction", SwingConstants.CENTER);
        add(footerLabel, BorderLayout.SOUTH);
    }
}