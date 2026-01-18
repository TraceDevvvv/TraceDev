/**
Confirmation dialog that shows the proposed changes before saving.
Implements the confirmation step from the use case flow.
*/
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PreferenceChangeDialog extends JDialog {
    private boolean confirmed = false;
    public PreferenceChangeDialog(JFrame parent, SearchPreference oldPref, SearchPreference newPref) {
        super(parent, "Confirm Preference Changes", true);
        initializeUI(oldPref, newPref);
    }
    private void initializeUI(SearchPreference oldPref, SearchPreference newPref) {
        setSize(500, 400);
        setLocationRelativeTo(getParent());
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        // Title
        JLabel titleLabel = new JLabel("Please review your changes:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Comparison panel
        JPanel comparisonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        // Old preferences
        JPanel oldPanel = new JPanel(new BorderLayout());
        oldPanel.setBorder(BorderFactory.createTitledBorder("Current Preferences"));
        JTextArea oldText = new JTextArea(oldPref.toString());
        oldText.setEditable(false);
        oldText.setBackground(new Color(240, 240, 240));
        oldPanel.add(new JScrollPane(oldText), BorderLayout.CENTER);
        // New preferences
        JPanel newPanel = new JPanel(new BorderLayout());
        newPanel.setBorder(BorderFactory.createTitledBorder("New Preferences"));
        JTextArea newText = new JTextArea(newPref.toString());
        newText.setEditable(false);
        newText.setBackground(new Color(220, 255, 220));
        newPanel.add(new JScrollPane(newText), BorderLayout.CENTER);
        comparisonPanel.add(oldPanel);
        comparisonPanel.add(newPanel);
        mainPanel.add(comparisonPanel, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton confirmButton = new JButton("Confirm Changes");
        confirmButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    public boolean isConfirmed() {
        return confirmed;
    }
}