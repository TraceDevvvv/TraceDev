/**
 * Main application class for the Administrator Entity Search GUI.
 * This class sets up the JFrame, search form, and displays results in a tabbed pane.
 * It simulates an administrator searching through various system entities.
 */
package com.chatdev.ui;
import com.chatdev.entities.AddressEntity;
import com.chatdev.entities.ClassEntity;
import com.chatdev.entities.TeachingEntity;
import com.chatdev.entities.UserEntity;
import com.chatdev.serv.EntitySearchService;
import com.chatdev.serv.SearchResult;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
public class AdminEntitySearchApp extends JFrame {
    // UI Components
    private JTextField searchTextField; // Input field for search keywords
    private JButton searchButton;      // Button to trigger the search
    private JTabbedPane resultTabbedPane; // Tabbed pane to display results for different entity types
    // Models for JLists in each tab
    private DefaultListModel<ClassEntity> classListModel;
    private DefaultListModel<TeachingEntity> teachingListModel;
    private DefaultListModel<AddressEntity> addressListModel;
    private DefaultListModel<UserEntity> userListModel;
    // Service for handling search logic
    private EntitySearchService searchService;
    /**
     * Constructor for the AdminEntitySearchApp.
     * Initializes the UI components and the search service.
     */
    public AdminEntitySearchApp() {
        super("Administrator Entity Search"); // Set the title of the JFrame
        searchService = new EntitySearchService(); // Initialize the mock search service
        initComponents(); // Set up the graphical user interface
    }
    /**
     * Initializes all UI components and lays them out.
     */
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation for the window
        setSize(800, 600); // Set initial size of the window
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for the main frame
        // --- Search Panel ---
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search for Entities"));
        searchTextField = new JTextField(30); // Text field for keywords
        searchButton = new JButton("Search"); // Button to initiate search
        searchPanel.add(new JLabel("Keywords:"));
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        // Add action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch(); // Call a method to execute the search
            }
        });
        // Allow pressing Enter in the text field to trigger search
        searchTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        add(searchPanel, BorderLayout.NORTH); // Add search panel to the top of the frame
        // --- Result Tabbed Pane ---
        resultTabbedPane = new JTabbedPane();
        resultTabbedPane.setBorder(BorderFactory.createTitledBorder("Search Results"));
        // Initialize ListModels for each entity type
        classListModel = new DefaultListModel<>();
        teachingListModel = new DefaultListModel<>();
        addressListModel = new DefaultListModel<>();
        userListModel = new DefaultListModel<>();
        // Create and add tabs for each entity type
        setupResultTab(resultTabbedPane, "Classes", classListModel);
        setupResultTab(resultTabbedPane, "Teachings", teachingListModel);
        setupResultTab(resultTabbedPane, "Addresses", addressListModel);
        setupResultTab(resultTabbedPane, "Users", userListModel);
        add(resultTabbedPane, BorderLayout.CENTER); // Add result tabbed pane to the center
        // Add a WindowListener to handle the application closing event
        // This simulates the "Connection to the interrupted SMOS server" postcondition.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Application is closing. Simulating 'Connection to the interrupted SMOS server'.");
                // In a real application, proper resource cleanup would occur here.
                super.windowClosing(e);
            }
        });
    }
    /**
     * Helper method to create and add a JList tab to the JTabbedPane.
     * @param tabbedPane The JTabbedPane to add the tab to.
     * @param title The title of the tab.
     * @param listModel The DefaultListModel to back the JList.
     * @param <T> The type of entity in the list.
     */
    private <T> void setupResultTab(JTabbedPane tabbedPane, String title, DefaultListModel<T> listModel) {
        JList<T> entityList = new JList<>(listModel);
        entityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        entityList.setLayoutOrientation(JList.VERTICAL);
        entityList.setVisibleRowCount(-1); // Display all data
        JScrollPane scrollPane = new JScrollPane(entityList);
        tabbedPane.addTab(title, scrollPane);
    }
    /**
     * Performs the entity search based on keywords from the search text field
     * and updates the JLists in the result tabs.
     */
    private void performSearch() {
        String keywords = searchTextField.getText().trim();
        if (keywords.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter keywords to search.", "Search Warning", JOptionPane.WARNING_MESSAGE);
            clearAllListModels(); // Clear previous results if search is empty
            return;
        }
        System.out.println("Searching for: '" + keywords + "'"); // Log search query
        // Perform search using the service
        SearchResult result = searchService.searchEntities(keywords);
        // Update the JLists with the search results
        updateListModel(classListModel, result.getClasses());
        updateListModel(teachingListModel, result.getTeachings());
        updateListModel(addressListModel, result.getAddresses());
        updateListModel(userListModel, result.getUsers());
        // Optionally switch to the first tab that has results
        // For this example, we will select the first tab that has results (if any).
        // This enhances user experience by immediately showing relevant results.
        if (!result.getClasses().isEmpty()) {
            resultTabbedPane.setSelectedComponent(resultTabbedPane.getComponentAt(0)); // Classes tab
        } else if (!result.getTeachings().isEmpty()) {
            resultTabbedPane.setSelectedComponent(resultTabbedPane.getComponentAt(1)); // Teachings tab
        } else if (!result.getAddresses().isEmpty()) {
            resultTabbedPane.setSelectedComponent(resultTabbedPane.getComponentAt(2)); // Addresses tab
        } else if (!result.getUsers().isEmpty()) {
            resultTabbedPane.setSelectedComponent(resultTabbedPane.getComponentAt(3)); // Users tab
        }
    }
    /**
     * Clears an existing DefaultListModel and populates it with new data.
     * @param model The DefaultListModel to update.
     * @param data The new list of data to add to the model.
     * @param <T> The type of entity in the list.
     */
    private <T> void updateListModel(DefaultListModel<T> model, List<T> data) {
        model.clear(); // Clear existing items
        if (data != null && !data.isEmpty()) {
            for (T item : data) {
                model.addElement(item); // Add each new item
            }
        }
    }
    /**
     * Clears all list models. This is useful when no results are found or
     * when the search query is cleared.
     */
    private void clearAllListModels() {
        classListModel.clear();
        teachingListModel.clear();
        addressListModel.clear();
        userListModel.clear();
    }
    /**
     * Main method to run the application.
     * Ensures the GUI is created and updated on the Event Dispatch Thread (EDT).
     *
     * Preconditions: The user is logged in to the system as administrator. (Implicitly assumed for this standalone app)
     * The user inserts the text to search for and click on the "Search" button. (Handled by UI)
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminEntitySearchApp app = new AdminEntitySearchApp();
                app.setVisible(true); // Make the main window visible
            }
        });
    }
}