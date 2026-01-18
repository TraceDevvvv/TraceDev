/**
 * This class provides a GUI window for displaying detailed information of a class.
 * It is designed for the Administrator actor and follows the ViewClassDetails use case.
 * The frame shows Class Name, Address, and School Year.
 * Preconditions: user logged in, class list displayed, and "Show class details" button clicked.
 * Postconditions: detailed information displayed and SMOS server connection interrupted.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class ClassDetailsFrame extends JFrame {
    private JLabel classNameLabel;
    private JLabel classAddressLabel;
    private JLabel schoolYearLabel;
    /**
     * Constructor to initialize the frame and layout.
     * @param className The name of the class
     * @param address The address of the class
     * @param schoolYear The school year of the class
     */
    public ClassDetailsFrame(String className, String address, String schoolYear) {
        setTitle("Class Details");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Create a panel for the details with GridBagLayout for flexibility
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        // Class Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsPanel.add(new JLabel("Class Name:"), gbc);
        gbc.gridx = 1;
        classNameLabel = new JLabel(className);
        detailsPanel.add(classNameLabel, gbc);
        // Class Address
        gbc.gridx = 0;
        gbc.gridy = 1;
        detailsPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        classAddressLabel = new JLabel(address);
        detailsPanel.add(classAddressLabel, gbc);
        // School Year
        gbc.gridx = 0;
        gbc.gridy = 2;
        detailsPanel.add(new JLabel("School Year:"), gbc);
        gbc.gridx = 1;
        schoolYearLabel = new JLabel(schoolYear);
        detailsPanel.add(schoolYearLabel, gbc);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        // Close button panel
        JPanel buttonPanel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close this window
            }
        });
        buttonPanel.add(closeButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Updates the displayed class details.
     * @param className New class name
     * @param address New address
     * @param schoolYear New school year
     */
    public void updateDetails(String className, String address, String schoolYear) {
        classNameLabel.setText(className);
        classAddressLabel.setText(address);
        schoolYearLabel.setText(schoolYear);
    }
}