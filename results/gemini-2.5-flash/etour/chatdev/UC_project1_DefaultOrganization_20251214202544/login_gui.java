/*
This class provides a graphical user interface for user login.
It simulates the "Agency Operator has logged" entry condition for the
ELIMINABENECULTURALE use case. Upon successful (mock) login, it
initiates the main application GUI.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private JLabel statusLabel;
    /**
     * Constructor for the LoginGUI.
     * Initializes the UI components and sets up event listeners.
     */
    public LoginGUI() {
        super("Login Operatore Agenzia"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close
        setSize(350, 200); // Set initial window size
        setLocationRelativeTo(null); // Center the window on screen
        setResizable(false); // Prevent resizing
        setupUI(); // Set up all GUI components
        addListeners(); // Add action listeners to components
    }
    /**
     * Sets up the graphical user interface components and their layout for the login window.
     */
    private void setupUI() {
        // Use JPanel with BorderLayout for overall structure
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        // Panel for input fields (username, password)
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField("agency_operator"); // Pre-fill for convenience
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField("password"); // Pre-fill for convenience
        inputPanel.add(passwordField);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        loginButton = new JButton("Login");
        cancelButton = new JButton("Annulla");
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Status label for messages
        statusLabel = new JLabel("Inserisci le tue credenziali.");
        statusLabel.setForeground(Color.BLACK);
        mainPanel.add(statusLabel, BorderLayout.NORTH);
        add(mainPanel); // Add the main panel to the frame
    }
    /**
     * Adds event listeners to the GUI components for login and cancel actions.
     */
    private void addListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin(); // Call method to handle login attempt
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application if cancelled
            }
        });
        // Allow pressing Enter key to trigger login from either field
        usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
    }
    /**
     * Attempts to log in the user by validating credentials.
     * In this mock implementation, it checks for a specific username and password.
     */
    private void attemptLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // Mock authentication:
        if ("agency_operator".equals(username) && "password".equals(password)) {
            // Successful login
            statusLabel.setForeground(Color.GREEN);
            statusLabel.setText("Login riuscito!");
            dispose(); // Close the login window
            // Open the main application GUI on the Event Dispatch Thread (EDT)
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new EliminaBeneCulturaleGUI().setVisible(true);
                }
            });
        } else {
            // Failed login
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Credenziali non valide. Riprova.");
            passwordField.setText(""); // Clear password field for security
        }
    }
}