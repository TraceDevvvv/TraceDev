'''
Main application class for the ETOUR system, demonstrating the INSERISCISITONEIPREFERITI use case.
This class sets up the graphical user interface (GUI) using Swing.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Classes in the same default package do not require explicit import statements
// import BookmarkManager;
// import BookmarkManager.ConnectionToServerException;
// import Site; 
public class ETourApp extends JFrame {
    private BookmarkManager bookmarkManager;
    private Site currentSite; // Represents the site where the tourist card is currently located
    private JLabel currentSiteLabel;
    private JButton addFavoriteButton;
    private JList<Site> bookmarksList;
    private DefaultListModel<Site> bookmarksListModel;
    private JButton selectSiteButton; // Button to simulate selecting a new site
    private JScrollPane bookmarksScrollPane; // Member variable to hold the scroll pane for bookmarks list
    /**
     * Constructs the ETourApp main window.
     * Initializes the GUI components and sets up event listeners.
     */
    public ETourApp() {
        super("ETOUR - Tourist Application");
        bookmarkManager = new BookmarkManager();
        this.currentSite = null; // No site selected initially
        // Set up the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null); // Center the window
        initComponents(); // Initialize GUI components
        layoutComponents(); // Arrange components on the frame
        addEventHandlers(); // Add listeners for interactive elements
        updateUIState(); // Update button states initially
    }
    /**
     * Initializes all the graphical user interface components.
     */
    private void initComponents() {
        // Current Site Panel
        currentSiteLabel = new JLabel("No site currently selected.", SwingConstants.CENTER);
        currentSiteLabel.setFont(new Font("Serif", Font.BOLD, 16));
        currentSiteLabel.setBorder(BorderFactory.createTitledBorder("Current Tourist Location"));
        // Action Buttons
        addFavoriteButton = new JButton("Add Current Site to Favorites");
        selectSiteButton = new JButton("Simulate Visiting a New Site");
        // Bookmarks List Panel
        bookmarksListModel = new DefaultListModel<>();
        bookmarksList = new JList<>(bookmarksListModel);
        bookmarksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Initialize the bookmarksScrollPane as a member variable and set its preferred size
        this.bookmarksScrollPane = new JScrollPane(bookmarksList);
        this.bookmarksScrollPane.setPreferredSize(new Dimension(300, 150)); // Set a reasonable preferred height for the scroll pane
        this.bookmarksScrollPane.setBorder(BorderFactory.createTitledBorder("Your Favorite Sites"));
    }
    /**
     * Arranges the initialized components onto the JFrame using various layout managers.
     */
    private void layoutComponents() {
        // Top panel for current site
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(currentSiteLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        // Center panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.add(selectSiteButton);
        buttonPanel.add(addFavoriteButton);
        add(buttonPanel, BorderLayout.CENTER);
        // Bottom panel for bookmarks list container
        JPanel bottomPanel = new JPanel(new BorderLayout());
        // The problematic line bottomPanel.setPreferredSize(new Dimension(getWidth(), 150)); has been removed.
        // Instead, the preferred size is set directly on the `bookmarksScrollPane` itself in initComponents.
        // Use the member variable for the scroll pane.
        bottomPanel.add(bookmarksScrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    /**
     * Adds action listeners to interactive GUI components.
     */
    private void addEventHandlers() {
        // Handler for 'Add Current Site to Favorites' button
        addFavoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activateAddFavoriteFeature();
            }
        });
        // Handler for 'Simulate Visiting a New Site' button
        selectSiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulateSelectNewSite();
            }
        });
    }
    /**
     * Updates the state of GUI elements based on the application's current state,
     * such as enabling/disabling buttons.
     */
    private void updateUIState() {
        if (currentSite == null) {
            currentSiteLabel.setText("No site currently selected. Please simulate visiting a site.");
            addFavoriteButton.setEnabled(false); // Cannot add to favorites if no site is selected
        } else {
            currentSiteLabel.setText("Currently at: " + currentSite.toString());
            // Enable only if a site is selected and not already bookmarked
            addFavoriteButton.setEnabled(!bookmarkManager.isSiteBookmarked(currentSite));
        }
        displayBookmarks(); // Always refresh the bookmarks list
    }
    /**
     * Simulates the feature activation to add the currently visited site to favorites.
     * This method implements the core use case flow.
     */
    private void activateAddFavoriteFeature() {
        if (currentSite == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select or visit a site before adding to favorites.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // 2. Prompt the inclusion (Confirmation dialog)
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Do you want to add '" + currentSite.getName() + "' to your favorites?",
                "Confirm Insertion",
                JOptionPane.YES_NO_OPTION);
        // 3. Confirm the input.
        if (confirmation == JOptionPane.YES_OPTION) {
            // 4. Inserts the selected site in the list of bookmarks.
            try {
                // Using the ConnectionToServerException from BookmarkManager without explicit import
                bookmarkManager.addBookmark(currentSite);
                // Exit condition: Notification about the insertion site to favorites
                JOptionPane.showMessageDialog(this,
                        "'" + currentSite.getName() + "' successfully added to favorites!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                updateUIState(); // Refresh UI after successful addition
            } catch (BookmarkManager.ConnectionToServerException e) { // Qualified name for the nested exception
                // Exit condition: Interruption of connection to the server ETOUR.
                JOptionPane.showMessageDialog(this,
                        "Failed to add to favorites: " + e.getMessage() + "\nPlease try again.",
                        "Connection Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                // Handle case where site is null or already bookmarked
                JOptionPane.showMessageDialog(this,
                        e.getMessage(),
                        "Operation Failed", JOptionPane.WARNING_MESSAGE);
                updateUIState(); // Update UI to reflect it's already bookmarked or other error
            }
        }
    }
    /**
     * Updates the JList displaying the bookmarked sites.
     */
    private void displayBookmarks() {
        bookmarksListModel.clear();
        if (bookmarkManager.getBookmarks().isEmpty()) {
            // Add a placeholder if the list is empty
            bookmarksListModel.addElement(new Site("No favorites yet.", "N/A - Start exploring!"));
        } else {
            for (Site site : bookmarkManager.getBookmarks()) {
                bookmarksListModel.addElement(site);
            }
        }
    }
    /**
     * Simulates a tourist visiting a new site by showing a dialog
     * to select one from a predefined list.
     * This fulfills the "The tourist card is in a particular site" entry condition.
     */
    private void simulateSelectNewSite() {
        Site[] availableSites = {
            new Site("Colosseum", "Rome, Italy"),
            new Site("Eiffel Tower", "Paris, France"),
            new Site("Statue of Liberty", "New York, USA"),
            new Site("Great Wall of China", "Huairou, China"),
            new Site("Pyramids of Giza", "Giza, Egypt"),
            new Site("Opera House", "Sydney, Australia")
        };
        Site selectedSite = (Site) JOptionPane.showInputDialog(
                this,
                "Select a site to simulate your current location:",
                "Select Current Site",
                JOptionPane.QUESTION_MESSAGE,
                null,
                availableSites,
                (currentSite != null ? currentSite : availableSites[0])); // Pre-select current if exists
        if (selectedSite != null) {
            this.currentSite = selectedSite;
            updateUIState();
        }
    }
    /**
     * Main method to run the ETourApp.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ETourApp().setVisible(true);
            }
        });
    }
}