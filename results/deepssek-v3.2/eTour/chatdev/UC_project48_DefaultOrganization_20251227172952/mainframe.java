/**
 * Main application window that provides access to preference modification.
 */
package modifygenericpreference;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class MainFrame extends JFrame {
    private Tourist tourist;
    private PreferenceService preferenceService;
    public MainFrame(Tourist tourist) {
        this.tourist = tourist;
        this.preferenceService = new PreferenceService();
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Tourist Preferences System - ModifyGenericPreference");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Application closing...");
            }
        });
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome, " + tourist.getFullName() + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        JPanel instructionPanel = new JPanel(new BorderLayout());
        JTextArea instructionArea = new JTextArea(
            "You are now authenticated to the system.\n\n" +
            "Entry Conditions Met:\n" +
            "✓ Tourism has successfully authenticated to the system.\n\n" +
            "To modify your generic personal preferences, click the button below.\n" +
            "This follows the flow of events:\n" +
            "1. Access modification functionality\n" +
            "2. View current preferences in a form\n" +
            "3. Edit fields and submit\n" +
            "4. Confirm changes\n" +
            "5. Store updated preferences"
        );
        instructionArea.setEditable(false);
        instructionArea.setLineWrap(true);
        instructionArea.setWrapStyleWord(true);
        instructionArea.setBackground(getBackground());
        instructionPanel.add(new JScrollPane(instructionArea), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton modifyButton = new JButton("Modify Generic Preferences");
        modifyButton.setFont(new Font("Arial", Font.BOLD, 14));
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPreferenceForm();
            }
        });
        JButton cancelButton = new JButton("Exit Application");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    MainFrame.this,
                    "Are you sure you want to exit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        buttonPanel.add(modifyButton);
        buttonPanel.add(cancelButton);
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel statusLabel = new JLabel("Status: Ready");
        statusPanel.add(statusLabel);
        mainPanel.add(welcomePanel, BorderLayout.NORTH);
        mainPanel.add(instructionPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    private void openPreferenceForm() {
        if (!preferenceService.checkServerConnection()) {
            JOptionPane.showMessageDialog(this,
                "Cannot connect to ETOUR server. Please check your connection and try again.",
                "Connection Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        Preferences currentPreferences = preferenceService.getPreferences(tourist.getUsername());
        PreferenceForm form = new PreferenceForm(this, tourist, currentPreferences, preferenceService);
        form.setVisible(true);
    }
    public void showSuccessNotification() {
        JOptionPane.showMessageDialog(this,
            "Your generic personal preferences have been successfully modified!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
}