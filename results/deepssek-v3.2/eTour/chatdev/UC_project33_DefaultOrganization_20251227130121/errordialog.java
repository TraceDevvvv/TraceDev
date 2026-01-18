/**
 * ErrorDialog class handles the Errored use case.
 * This is activated when invalid or insufficient data is entered.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ErrorDialog extends JDialog {
    /**
     * Constructor for ErrorDialog
     * @param parent The parent frame
     * @param errorMessage The error message to display
     */
    public ErrorDialog(JFrame parent, String errorMessage) {
        super(parent, "Registration Error", true);
        setLayout(new BorderLayout(10, 10));
        setSize(400, 200);
        setLocationRelativeTo(parent);
        // Error message panel
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel errorIcon = new JLabel(new ImageIcon(UIManager.getIcon("OptionPane.errorIcon")));
        errorIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel titleLabel = new JLabel("Registration Error");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextArea errorText = new JTextArea(errorMessage);
        errorText.setEditable(false);
        errorText.setLineWrap(true);
        errorText.setWrapStyleWord(true);
        errorText.setBackground(messagePanel.getBackground());
        errorText.setAlignmentX(Component.CENTER_ALIGNMENT);
        messagePanel.add(errorIcon);
        messagePanel.add(Box.createVerticalStrut(10));
        messagePanel.add(titleLabel);
        messagePanel.add(Box.createVerticalStrut(10));
        messagePanel.add(errorText);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(okButton);
        add(messagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Static method to show error dialog (use case Errored)
     * @param parent The parent frame
     * @param errorMessage The error message
     */
    public static void showErrorDialog(JFrame parent, String errorMessage) {
        ErrorDialog dialog = new ErrorDialog(parent, errorMessage);
        dialog.setVisible(true);
    }
}