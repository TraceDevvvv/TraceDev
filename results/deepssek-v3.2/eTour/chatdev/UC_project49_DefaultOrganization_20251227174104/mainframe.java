'''
@file MainFrame.java
@brief Main window after login; provides "View Favorites" button.
@details This frame serves as the dashboard for the authenticated Tourist.
@version 1.0
@date 2023-10-15
'''
package project;
import javax.swing.*;
import java.awt.*;
public class MainFrame extends JFrame {
    private String currentUser;
    private ETOURServerMock server;
    /**
     * Constructor to initialize the main frame.
     * @param username The authenticated username
     */
    public MainFrame(String username) {
        super("ETOUR - Tourist Dashboard");
        this.currentUser = username;
        this.server = new ETOURServerMock();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);
        JButton viewFavoritesButton = new JButton("View Favorites");
        // ActionListener to open favorites viewer
        viewFavoritesButton.addActionListener(e -> viewFavorites());
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewFavoritesButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
    /**
     * Opens the favorites viewer window.
     */
    private void viewFavorites() {
        new FavoritesViewer(currentUser, server);
    }
    /**
     * Logs out the current user and returns to the login screen.
     */
    private void logout() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose(); // Close current window
            new LoginFrame(); // Return to login
        }
    }
}