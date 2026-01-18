'''
/**
 * The main application entry point for the Administrator Teaching Management System.
 * This class initializes the primary JFrame, sets up the overall GUI,
 * and orchestrates the interaction between the {@code TeachingRepository} and
 * {@code AddressDetailPanel}. It simulates an administrator logged into the system,
 * allowing them to select an address and manage its associated teachings.
 */
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
public class MainApplication extends JFrame {
    private TeachingRepository repository;
    private AddressDetailPanel addressDetailPanel;
    private JComboBox<Address> addressComboBox;
    /**
     * Constructs the main application window.
     */
    public MainApplication() {
        super("Administrator Teaching Management System");
        // Precondition: user is logged in as an administrator (simulated)
        // This application assumes an admin context.
        repository = new TeachingRepository(); // Initialize our data repository
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window on the screen
        initComponents();
        setupLayout();
        populateAddressSelection();
    }
    /**
     * Initializes the main GUI components.
     */
    private void initComponents() {
        addressDetailPanel = new AddressDetailPanel(repository);
        // ComboBox to select an address. Using a custom renderer for display.
        addressComboBox = new JComboBox<>();
        addressComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Address) {
                    Address address = (Address) value;
                    setText(address.getDescription() + " (ID: " + address.getId() + ")");
                }
                return this;
            }
        });
        // Listener for address selection changes
        addressComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Address selectedAddress = (Address) addressComboBox.getSelectedItem();
                // Precondition: user has made the case of use "viewdettaglizzizzo"
                // This simulates selecting an address to view its details.
                addressDetailPanel.setAddress(selectedAddress);
            }
        });
    }
    /**
     * Sets up the layout of the main application window.
     */
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10)); // Overall layout with padding
        // Top panel for address selection
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        selectionPanel.add(new JLabel("Select Address:"));
        selectionPanel.add(addressComboBox);
        add(selectionPanel, BorderLayout.NORTH); // Add selection panel to the top
        add(addressDetailPanel, BorderLayout.CENTER); // Add address details in the center
    }
    /**
     * Populates the address selection combo box with addresses from the repository.
     */
    private void populateAddressSelection() {
        try {
            // Use the public getAllAddresses method from the repository, adhering to encapsulation.
            Collection<Address> addresses = repository.getAllAddresses();
            for (Address address : addresses) {
                addressComboBox.addItem(address);
            }
            // Select the first address by default if available
            if (!addresses.isEmpty()) {
                addressComboBox.setSelectedIndex(0);
            }
        } catch (RuntimeException ex) {
            // Handle simulated connection interruption during initial load
            JOptionPane.showMessageDialog(this, ex.getMessage() + "\nCould not load addresses.", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Main method to start the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainApplication().setVisible(true);
            }
        });
    }
}