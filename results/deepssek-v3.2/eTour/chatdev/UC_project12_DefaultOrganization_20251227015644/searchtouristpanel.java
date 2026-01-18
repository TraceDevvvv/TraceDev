/**
 * Panel for searching and selecting tourists.
 * Simulates the SearchTourist use case functionality.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class SearchTouristPanel extends JPanel {
    private JList<Tourist> touristList;
    private DefaultListModel<Tourist> listModel;
    private JButton viewDetailsButton;
    private JButton refreshButton;
    private ViewTouristCardGUI parentGUI;
    public SearchTouristPanel(ViewTouristCardGUI parent) {
        this.parentGUI = parent;
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    private void initializeComponents() {
        listModel = new DefaultListModel<>();
        touristList = new JList<>(listModel);
        touristList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        viewDetailsButton = new JButton("View Tourist Card");
        viewDetailsButton.setEnabled(false);
        refreshButton = new JButton("Refresh List");
    }
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        // Create top panel with title
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Available Tourists (from SearchTourist):"));
        add(topPanel, BorderLayout.NORTH);
        // Create center panel with scrollable list
        JScrollPane scrollPane = new JScrollPane(touristList);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        add(scrollPane, BorderLayout.CENTER);
        // Create bottom panel with buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(refreshButton);
        buttonPanel.add(viewDetailsButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    private void setupEventHandlers() {
        // Enable/disable view button based on selection
        touristList.addListSelectionListener(e -> {
            viewDetailsButton.setEnabled(!touristList.isSelectionEmpty());
        });
        // View details button handler
        viewDetailsButton.addActionListener(e -> {
            Tourist selected = touristList.getSelectedValue();
            if (selected != null) {
                parentGUI.displayTouristCard(selected);
            }
        });
        // Refresh button handler
        refreshButton.addActionListener(e -> {
            refreshTouristList();
        });
    }
    /**
     * Refreshes the tourist list by fetching data from server
     */
    public void refreshTouristList() {
        List<Tourist> tourists = ServerUtils.fetchTouristDataWithRetry(parentGUI);
        if (tourists != null) {
            listModel.clear();
            for (Tourist tourist : tourists) {
                listModel.addElement(tourist);
            }
            JOptionPane.showMessageDialog(this, 
                "Found " + tourists.size() + " tourists", 
                "Search Results", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}