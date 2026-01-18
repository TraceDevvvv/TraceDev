/**
 * Represents the administrator's dashboard.
 * It provides the initial "Online reports" button to proceed with the use case.
 * This panel is displayed after the simulated administrator login.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AdminDashboard extends JPanel {
    private Main mainFrame;
    public AdminDashboard(Main mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout()); // Use GridBagLayout for flexible centering
        JLabel welcomeLabel = new JLabel("Welcome, Administrator!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JButton onlineReportsButton = new JButton("Online Reports");
        onlineReportsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        onlineReportsButton.setPreferredSize(new Dimension(200, 50)); // Set preferred size
        // Action listener for the "Online Reports" button
        onlineReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When button is clicked, switch to the class selection panel
                mainFrame.showClassSelection();
            }
        });
        // Add components to the panel using GridBagConstraints for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(welcomeLabel, gbc);
        gbc.gridy = 1;
        add(onlineReportsButton, gbc);
    }
}