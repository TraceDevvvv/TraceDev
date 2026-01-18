'''
Represents the "registry screen" where a list of justifications is displayed.
Allows an administrator to select a justification to view/edit.
'''
package gui;
import model.Justification;
import service.JustificationService;
import service.LoginService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * This JFrame displays a list of justifications and allows an administrator
 * to select one to edit. It acts as the "registry screen" mentioned in the use case.
 * It implements JustificationUpdateListener to refresh its list after an edit.
 */
public class JustificationListFrame extends JFrame implements JustificationUpdateListener {
    private final JustificationService justificationService;
    private JList<Justification> justificationJList;
    private DefaultListModel<Justification> listModel;
    private JButton viewEditButton;
    /**
     * Constructor for JustificationListFrame.
     * @param justificationService The service used to manage justification data.
     */
    public JustificationListFrame(JustificationService justificationService) {
        this.justificationService = justificationService;
        setTitle("Justification Registry - Administrator View");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        initComponents(); // Initialize GUI components
        loadJustifications(); // Load initial data into the list
    }
    /**
     * Initializes the GUI components of the frame.
     * Sets up a JList to display justifications and a button to edit the selected one.
     */
    private void initComponents() {
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with gaps
        // Panel for list header
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(new JLabel("<html><h2>Existing Justifications</h2></html>"));
        add(headerPanel, BorderLayout.NORTH);
        // List model to hold Justification objects
        listModel = new DefaultListModel<>();
        justificationJList = new JList<>(listModel);
        justificationJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one item can be selected at a time
        justificationJList.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Improve readability for list items
        justificationJList.setFixedCellHeight(30); // Give more space to each item
        // Custom renderer for Justification objects to display nicely in JList
        justificationJList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // Let the default renderer paint the component initially
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Justification) {
                    Justification justification = (Justification) value;
                    setText(justification.toString()); // Use the overridden toString method
                }
                return this;
            }
        });
        // Scroll pane for the list
        JScrollPane scrollPane = new JScrollPane(justificationJList);
        add(scrollPane, BorderLayout.CENTER);
        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center flow layout
        viewEditButton = new JButton("View/Edit Selected Justification");
        viewEditButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(viewEditButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // Add action listener to the "View/Edit" button
        viewEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewEditSelectedJustification();
            }
        });
    }
    /**
     * Loads all justifications from the service and populates the JList.
     * This method is called upon initialization and after any modification (callback).
     */
    private void loadJustifications() {
        listModel.clear(); // Clear existing items
        List<Justification> justifications = justificationService.getAllJustifications();
        if (justifications.isEmpty()) {
            listModel.addElement(new Justification(0,"N/A", "No justifications found.")); // Placeholder
        } else {
            for (Justification j : justifications) {
                listModel.addElement(j); // Add each justification to the list model
            }
        }
    }
    /**
     * Handles the action of clicking the "View/Edit Selected Justification" button.
     * Checks for administrator login and selected item, then opens the EditJustificationFrame.
     */
    private void viewEditSelectedJustification() {
        // Precondition: User must be logged in as an administrator
        if (!LoginService.isAdminLoggedIn()) {
            JOptionPane.showMessageDialog(this, "You must be logged in as an administrator to perform this action.", "Authentication Required", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Check if an item is selected in the list
        Justification selectedJustification = justificationJList.getSelectedValue();
        if (selectedJustification == null || selectedJustification.getId() == 0) { // Check for placeholder or no selection
            JOptionPane.showMessageDialog(this, "Please select a justification to edit.", "No Justification Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Precondition: "viewdetalticaustifica" has been carried out:
        // This is simulated by opening the EditJustificationFrame with the selected justification.
        // It's assumed the details are now "being viewed".
        // Create and display the edit frame, passing the selected justification and a listener for updates.
        EditJustificationFrame editFrame = new EditJustificationFrame(this, justificationService, selectedJustification, this);
        editFrame.setVisible(true);
        // This frame can be disabled or hidden until the edit frame is closed if desired,
        // but for simplicity, we'll keep it active and just refresh on callback.
    }
    /**
     * Callback method from the JustificationUpdateListener interface.
     * Called when a justification has been successfully updated in EditJustificationFrame.
     * Postcondition: The system returns to the registry screen (this frame) and justification is modified.
     */
    @Override
    public void onJustificationUpdated() {
        // Reload the justifications to reflect the changes
        loadJustifications();
        JOptionPane.showMessageDialog(this, "Justification has been successfully modified.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        // Postcondition: The administrator interrupts the connection to the SMOS server interrupted.
        // This is a conceptual action. In a real system, this might be a network call or a server-side event.
        // Here, we just acknowledge it conceptually.
        System.out.println("Conceptual: Administrator interrupted connection to SMOS server (post-update cleanup).");
    }
}