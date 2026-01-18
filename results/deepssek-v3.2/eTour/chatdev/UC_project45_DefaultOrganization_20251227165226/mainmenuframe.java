'''
Main menu frame that appears after successful login.
Provides navigation to the statistics view feature.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainMenuFrame extends JFrame {
    private RestaurantOperator operator;
    public MainMenuFrame(RestaurantOperator operator) {
        this.operator = operator;
        setTitle("Point of Restaurant - Main Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        // Welcome Panel
        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome, " + operator.getUsername() + 
                                       " - " + operator.getRestaurantName());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomePanel.add(welcomeLabel);
        // Menu Panel
        JPanel menuPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        JButton viewStatsButton = new JButton("View Personal Statistics");
        JButton manageMenuButton = new JButton("Manage Menu");
        JButton viewOrdersButton = new JButton("View Orders");
        JButton logoutButton = new JButton("Logout");
        // Style buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        viewStatsButton.setFont(buttonFont);
        manageMenuButton.setFont(buttonFont);
        viewOrdersButton.setFont(buttonFont);
        logoutButton.setFont(buttonFont);
        viewStatsButton.setPreferredSize(new Dimension(300, 50));
        // Add action listeners
        viewStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Step 1: Select the feature to display personal statistics
                openStatisticsView();
            }
        });
        manageMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenuFrame.this,
                    "Manage Menu feature would be implemented here.",
                    "Feature Not Implemented",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        viewOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenuFrame.this,
                    "View Orders feature would be implemented here.",
                    "Feature Not Implemented",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator.logout();
                dispose();
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
        // Add buttons to menu panel
        menuPanel.add(viewStatsButton);
        menuPanel.add(manageMenuButton);
        menuPanel.add(viewOrdersButton);
        menuPanel.add(logoutButton);
        // Add panels to frame
        add(welcomePanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
        // Add a status label at the bottom
        JLabel statusLabel = new JLabel("Select 'View Personal Statistics' to see restaurant metrics");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(statusLabel, BorderLayout.SOUTH);
    }
    /**
     * Opens the statistics view window.
     * This corresponds to step 1 in the use case flow.
     */
    private void openStatisticsView() {
        // Create and display statistics frame
        StatisticsFrame statsFrame = new StatisticsFrame(operator);
        statsFrame.setVisible(true);
    }
}