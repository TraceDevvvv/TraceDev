/**
 * The main application window (JFrame) for the Tag Management System.
 * This frame serves as the user interface for an "Agency Operator".
 * It provides a button to access the "Insert New Tag" functionality
 * and displays a list of currently managed tags.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;
import java.util.stream.Collectors; // Not explicitly used, but can be useful for more complex stream ops
public class TagManagerFrame extends JFrame {
    private final TagService tagService;
    private JList<String> tagListUI;
    private DefaultListModel<String> listModel;
    private JButton insertTagButton;
    /**
     * Constructs the main application frame.
     * @param title The title of the frame.
     * @param tagService The TagService instance for managing tags.
     */
    public TagManagerFrame(String title, TagService tagService) {
        super(title);
        this.tagService = tagService;
        setupFrame();
        initComponents();
        setupLayout();
        setupListeners();
        refreshTagList(); // Populate initial tag list
    }
    /**
     * Configures the basic properties of the JFrame.
     */
    private void setupFrame() {
        setSize(600, 400); // Default size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
    }
    /**
     * Initializes the GUI components for the frame.
     */
    private void initComponents() {
        insertTagButton = new JButton("Insert New Tag");
        insertTagButton.setToolTipText("Click to add a new tag to the system");
        listModel = new DefaultListModel<>();
        tagListUI = new JList<>(listModel);
        tagListUI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tagListUI.setVisibleRowCount(10);
    }
    /**
     * Sets up the layout of the frame using BorderLayout.
     */
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10)); // 10px padding between components
        // Top Panel for the button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(insertTagButton);
        add(topPanel, BorderLayout.NORTH);
        // Center Panel for the tag list
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Existing Tags"));
        centerPanel.add(new JScrollPane(tagListUI), BorderLayout.CENTER); // Add JList to a JScrollPane
        add(centerPanel, BorderLayout.CENTER);
    }
    /**
     * Sets up action listeners for buttons and window events.
     */
    private void setupListeners() {
        // Step 1: Access the functionality of inserting new tag search.
        insertTagButton.addActionListener(e -> openTagInsertionDialog());
        // This listener is useful in real applications but not explicitly part of the use case.
        // It ensures resources are properly handled on close if there were any.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Application closing...");
                // Potential cleanup or final saving operations could go here
            }
        });
    }
    /**
     * Opens the TagInsertionDialog when the "Insert New Tag" button is clicked.
     * This is the entry point for the INSERISCITAG use case.
     */
    private void openTagInsertionDialog() {
        // The operator is assumed to be logged in (entry condition met by running the app)
        TagInsertionDialog dialog = new TagInsertionDialog(this, tagService);
        dialog.setVisible(true); // Blocks until dialog is closed
        // Check if a tag was successfully added after the dialog closes
        if (dialog.isTagAddedSuccessfully()) {
            refreshTagList(); // Exit condition: Notification about the inclusion of the tag.
        }
    }
    /**
     * Refreshes the list of tags displayed in the JList component.
     * This method retrieves all current tags from the TagService and updates the UI.
     */
    private void refreshTagList() {
        listModel.clear(); // Clear existing items
        Set<Tag> currentTags = tagService.getAllTags();
        if (currentTags.isEmpty()) {
            listModel.addElement("No tags available.");
        } else {
            // Sort tags by name before adding to the list for consistent display
            currentTags.stream()
                       .sorted((t1, t2) -> t1.getName().compareToIgnoreCase(t2.getName()))
                       .map(tag -> tag.getName() + " - " + tag.getDescription())
                       .forEach(listModel::addElement);
        }
    }
}