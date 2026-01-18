'''
Main admin dashboard with navigation options
Contains the "Online reports" button as specified in the use case
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainAdminFrame extends JFrame {
    public MainAdminFrame() {
        setTitle("Admin Dashboard - Report Card System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Administrator Dashboard");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 20, 20);
        JButton onlineReportsButton = new JButton("Online Reports");
        onlineReportsButton.setFont(new Font("Arial", Font.BOLD, 16));
        onlineReportsButton.setPreferredSize(new Dimension(200, 60));
        onlineReportsButton.setBackground(new Color(46, 125, 50));
        onlineReportsButton.setForeground(Color.WHITE);
        onlineReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ClassSelectionFrame();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(onlineReportsButton, gbc);
        JButton manageClassesButton = new JButton("Manage Classes");
        manageClassesButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridy = 1;
        centerPanel.add(manageClassesButton, gbc);
        JButton manageStudentsButton = new JButton("Manage Students");
        manageStudentsButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridy = 2;
        centerPanel.add(manageStudentsButton, gbc);
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 30));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(MainAdminFrame.this,
                    "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    new LoginFrame();
                }
            }
        });
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(logoutButton);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }
}