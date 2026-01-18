/**
 * Main GUI class for the ViewUserDetails use case.
 * Displays a list of users and shows details when "Details" button is clicked.
 * Implements proper event handling and connection management.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
public class ViewUserDetailsGUI extends JFrame {
    private UserDatabase database;
    private JList<User> userList;
    private DefaultListModel<User> listModel;
    public ViewUserDetailsGUI() {
        setTitle("View User Details - Administrator");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        // Add window listener to handle application closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleApplicationClose();
            }
        });
        database = new UserDatabase();
        // Create the list of users
        listModel = new DefaultListModel<>();
        List<User> users = database.getAllUsers();
        for (User user : users) {
            listModel.addElement(user);
        }
        userList = new JList<>(listModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(userList), BorderLayout.CENTER);
        // Details button
        JButton detailsButton = new JButton("Details");
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUserDetails();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(detailsButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null); // Center the window
    }
    /**
     * Displays detailed information of the selected user in a dialog.
     * Shows all required fields including password as specified in requirements.
     * Implements connection interruption as postcondition.
     */
    private void showUserDetails() {
        User selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this, 
                "Please select a user first.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Build the details message including password as specified
        String details = String.format(
                "Name: %s\nSurname: %s\nE-mail: %s\nCell: %s\nLogin: %s\nPassword: %s",
                selectedUser.getName(), 
                selectedUser.getSurname(), 
                selectedUser.getEmail(),
                selectedUser.getCell(), 
                selectedUser.getLogin(),
                selectedUser.getPassword()
        );
        // Create a custom dialog to display details
        JDialog detailsDialog = new JDialog(this, "User Details", true);
        detailsDialog.setSize(400, 300);
        detailsDialog.setLayout(new BorderLayout());
        detailsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JTextArea detailsArea = new JTextArea(details);
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        detailsArea.setMargin(new Insets(10, 10, 10, 10));
        detailsDialog.add(new JScrollPane(detailsArea), BorderLayout.CENTER);
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate postcondition: interrupt SMOS server connection when closing
                database.disconnect();
                detailsDialog.dispose();
            }
        });
        JPanel closePanel = new JPanel();
        closePanel.add(closeButton);
        detailsDialog.add(closePanel, BorderLayout.SOUTH);
        detailsDialog.setLocationRelativeTo(this);
        detailsDialog.setVisible(true);
    }
    /**
     * Handles application closing by interrupting server connection before exit
     */
    private void handleApplicationClose() {
        database.disconnect(); // Simulate connection interruption
        System.exit(0); // Terminate the application
    }
}