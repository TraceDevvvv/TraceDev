'''
A simple dialog to display error messages to the user.
Used when data is invalid or insufficient, or when a service error occurs.
It implements the "Errored" use case.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ErroredDialog extends JDialog {
    /**
     * Constructs an ErroredDialog.
     *
     * @param parent The parent frame for the dialog.
     * @param message The error message to display.
     */
    public ErroredDialog(Frame parent, String message) {
        super(parent, "Error", true); // Create a modal dialog with "Error" title
        initComponents(message);
        pack(); // Pack the components, setting the dialog to its preferred size
        setLocationRelativeTo(parent); // Center the dialog relative to its parent window
    }
    /**
     * Initializes and lays out the components of the error dialog.
     *
     * @param message The error message to be displayed.
     */
    private void initComponents(String message) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Define behavior when the user closes the dialog
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with padding for main layout
        // Add an error icon to the left side for visual indication
        JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.errorIcon"));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); // Add a small left padding for the icon
        add(iconLabel, BorderLayout.WEST);
        // Use a JTextArea for the message to allow for multi-line error messages.
        JTextArea messageArea = new JTextArea(message);
        messageArea.setWrapStyleWord(true); // Wrap lines at word boundaries
        messageArea.setLineWrap(true);       // Enable line wrapping
        messageArea.setEditable(false);      // Make the text area read-only
        messageArea.setBackground(getBackground()); // Match the dialog's background color
        messageArea.setFont(UIManager.getFont("Label.font")); // Use the default font for labels
        messageArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding around the message
        // Put the JTextArea in a JScrollPane in case the message is very long.
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove the default scroll pane border
        add(scrollPane, BorderLayout.CENTER);
        // Create an "OK" button to close the dialog.
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog when "OK" is clicked
            }
        });
        // Panel to hold the "OK" button, center-aligned.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Static helper method to conveniently display an error dialog without needing to
     * instantiate it directly. It attempts to find the parent Frame for correct centering.
     *
     * @param parent The component relative to which the dialog should be centered.
     * @param message The error message to display.
     */
    public static void display(Component parent, String message) {
        // Find the parent frame of the given component to set the dialog's parent.
        Window window = SwingUtilities.getWindowAncestor(parent);
        Frame parentFrame = null;
        if (window instanceof Frame) {
            parentFrame = (Frame) window;
        }
        // Create and show the error dialog.
        ErroredDialog dialog = new ErroredDialog(parentFrame, message);
        dialog.setVisible(true);
    }
}