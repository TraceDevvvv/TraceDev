
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {
    static boolean connectionActive = true;

    public static void main(String[] args) {
        // Simulate starting the logging feature (Step 1)
        enableLogging();

        // Display registration form (Step 2)
        RegistrationForm form = new RegistrationForm();
        form.display();
    }

    private static void enableLogging() {
        System.out.println("Logging feature enabled.");
    }
}

class RegistrationForm {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton submitButton;
    private JButton cancelButton;

    public void display() {
        frame = new JFrame("User Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        submitButton.addActionListener(new SubmitAction());
        cancelButton.addActionListener(e -> {
            System.out.println("Operation cancelled by Guest User.");
            frame.dispose();
            System.exit(0);
        });

        frame.setVisible(true);
    }

    private class SubmitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Step 3 & 4: Get form data and submit
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();

            // Step 5: Verify data
            if (validateData(username, password, email)) {
                // Step 6: Ask for confirmation
                int confirm = JOptionPane.showConfirmDialog(frame,
                        "Confirm registration?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Step 7: User confirms, proceed to create account
                    createAccount(username, password, email);
                } else {
                    JOptionPane.showMessageDialog(frame, "Registration cancelled.");
                }
            }
        }

        private boolean validateData(String username, String password, String email) {
            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.");
                return false;
            }
            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(frame, "Invalid email format.");
                return false;
            }
            if (password.length() < 6) {
                JOptionPane.showMessageDialog(frame, "Password must be at least 6 characters.");
                return false;
            }
            return true;
        }

        private void createAccount(String username, String password, String email) {
            // Step 8: Create account with timeout for quality requirement (10 seconds)
            System.out.println("Creating account for: " + username);
            CountDownLatch latch = new CountDownLatch(1);
            Thread accountCreationThread = new Thread(() -> {
                try {
                    // Simulate account creation process
                    Thread.sleep(2000); // Simulate 2 second processing
                    Account newAccount = new Account(username, password, email);
                    AccountManager.addAccount(newAccount);
                    latch.countDown();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            });

            accountCreationThread.start();

            try {
                // Wait for completion with timeout of 10 seconds
                if (latch.await(10, TimeUnit.SECONDS)) {
                    // Check connection status
                    if (Main.connectionActive) {
                        // Success exit condition
                        JOptionPane.showMessageDialog(frame, "Registration successful! Account created.");
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error: Connection to server interrupted.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Error: Operation timed out (exceeded 10 seconds).");
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Account {
    private String username;
    private String password;
    private String email;

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    // Password getter omitted for security
}

class AccountManager {
    private static Map<String, Account> accounts = new HashMap<>();

    public static void addAccount(Account account) {
        accounts.put(account.getUsername(), account);
        System.out.println("Account added: " + account.getUsername());
    }

    public static boolean accountExists(String username) {
        return accounts.containsKey(username);
    }
}
