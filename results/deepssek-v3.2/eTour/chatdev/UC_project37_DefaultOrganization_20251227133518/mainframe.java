'''
MainFrame is the main application window that manages panel switching
between login and logout interfaces.
Uses CardLayout to switch between panels efficiently.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private LogoutPanel logoutPanel;
    /**
     * Constructs the main application frame.
     */
    public MainFrame() {
        initializeFrame();
        initializeComponents();
        setupLayout();
        // Start with the login panel
        switchToLoginPanel();
    }
    /**
     * Initializes frame properties.
     */
    private void initializeFrame() {
        setTitle("Logout System - ChatDev Software");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 450);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
        setIconImage(createAppIcon());
    }
    /**
     * Creates a simple application icon.
     * @return Image object for the application icon
     */
    private Image createAppIcon() {
        // Create a simple colored icon using a BufferedImage
        BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        try {
            // Set rendering hints for better quality
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Draw background circle
            g2d.setColor(new Color(52, 152, 219));
            g2d.fillOval(4, 4, 24, 24);
            // Draw text 'C' for ChatDev
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            FontMetrics fm = g2d.getFontMetrics();
            int x = (32 - fm.stringWidth("C")) / 2;
            int y = (32 - fm.getHeight()) / 2 + fm.getAscent();
            g2d.drawString("C", x, y);
        } finally {
            // Ensure graphics context is always disposed
            g2d.dispose();
        }
        return img;
    }
    /**
     * Initializes the main components.
     */
    private void initializeComponents() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Create panels once and reuse them
        loginPanel = new LoginPanel(this);
        logoutPanel = new LogoutPanel(this);
        // Add panels to card layout
        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(logoutPanel, "LOGOUT");
    }
    /**
     * Sets up the main layout.
     */
    private void setupLayout() {
        // Add header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        JLabel headerLabel = new JLabel("ChatDev Logout System");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        // Set up main layout
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        // Add footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(240, 240, 240));
        footerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)));
        JLabel footerLabel = new JLabel("Software Engineering - Logout Use Case Implementation | Thread-Safe Singleton Pattern");
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        footerLabel.setForeground(Color.DARK_GRAY);
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);
    }
    /**
     * Switches to the logout panel.
     * Called after successful login.
     */
    public void switchToLogoutPanel() {
        // Update the logout panel with current user info
        logoutPanel.updateUserInfo();
        cardLayout.show(mainPanel, "LOGOUT");
    }
    /**
     * Switches to the login panel.
     * Called after logout.
     */
    public void switchToLoginPanel() {
        // Reset the login form for a fresh start
        loginPanel.resetForm();
        cardLayout.show(mainPanel, "LOGIN");
    }
}