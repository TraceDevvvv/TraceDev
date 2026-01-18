'''
This class implements the graphical user interface (GUI) for the ELIMINATAG
use case. It allows an Agency Operator to view existing search tags, select
one or more for deletion, and receive feedback on the operation's success
or failure (including simulated server connection issues).
It uses the TagManager to interact with the tag data, simulating backend operations.
'''
package com.chatdev.tagmanager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
public class EliminateTagApp extends JFrame {
    private TagManager tagManager; // Instance of TagManager to handle tag data
    private JList<String> tagList; // JList component to display tags
    private DefaultListModel<String> listModel; // Model for the JList
    private JButton deleteButton; // Button to trigger tag deletion
    private JCheckBox simulateFailureCheckBox; // Checkbox to toggle server failure simulation
    /**
     * Constructor for EliminateTagApp.
     * Initializes the TagManager and sets up all GUI components.
     */
    public EliminateTagApp() {
        super("ELIMINATAG - Remove Search Tags"); // Set the window title
        this.tagManager = new TagManager(); // Initialize the tag manager
        // Configure the main frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close
        setSize(500, 400); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with 10-pixel gaps
        initComponents(); // Initialize all graphical components
        loadTags();       // Load initial tags into the JList when the application starts
    }
    /**
     * Initializes all GUI components (JList, Buttons, Checkbox) and lays them out
     * within the JFrame.
     */
    private void initComponents() {
        // Panel for displaying tags
        JPanel tagPanel = new JPanel(new BorderLayout(5, 5));
        tagPanel.setBorder(BorderFactory.createTitledBorder("Available Tags (Select to delete)")); // Add a border with a title
        listModel = new DefaultListModel<>(); // Create a new list model
        tagList = new JList<>(listModel); // Create the JList with the model
        tagList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allow users to select multiple items
        tagList.setVisibleRowCount(10); // Suggest a preferred number of rows for display
        tagPanel.add(new JScrollPane(tagList), BorderLayout.CENTER); // Wrap JList in JScrollPane for scrollability
        add(tagPanel, BorderLayout.CENTER); // Add the tag panel to the center of the frame
        // Panel for buttons and controls
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Use FlowLayout for buttons
        deleteButton = new JButton("Delete Selected Tags");
        // Add an ActionListener to the delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedTags(); // Call the method to handle deletion when button is clicked
            }
        });
        controlPanel.add(deleteButton); // Add delete button to control panel
        // Checkbox to simulate connection failure (ETOUR)
        simulateFailureCheckBox = new JCheckBox("Simulate Server Connection Failure (ETOUR)");
        // Add an ActionListener to the checkbox
        simulateFailureCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the TagManager's simulation state based on checkbox selection
                tagManager.setSimulateConnectionFailure(simulateFailureCheckBox.isSelected());
            }
        });
        controlPanel.add(simulateFailureCheckBox); // Add checkbox to control panel
        add(controlPanel, BorderLayout.SOUTH); // Add the control panel to the bottom of the frame
    }
    /**
     * Loads tags from the TagManager and populates the JList.
     * This operation is performed in a background thread using SwingWorker to prevent
     * the UI from freezing, simulating a network call.
     */
    private void loadTags() {
        // SwingWorker is used to perform long-running tasks in the background thread
        // and update the GUI on the Event Dispatch Thread (EDT) when done.
        SwingWorker<List<String>, Void> worker = new SwingWorker<List<String>, Void>() {
            @Override
            protected List<String> doInBackground() throws Exception {
                // This code runs in a background thread.
                // It simulates fetching data from a server, which can take time.
                return tagManager.getAllTags();
            }
            @Override
            protected void done() {
                // This code runs on the EDT after doInBackground completes.
                try {
                    List<String> tags = get(); // Retrieve the result from doInBackground
                    listModel.clear(); // Clear all existing items from the list model
                    // Add all retrieved tags to the list model.
                    // This automatically updates the JList.
                    for (String tag : tags) {
                        listModel.addElement(tag);
                    }
                } catch (Exception e) {
                    // Display an error message if loading tags failed
                    JOptionPane.showMessageDialog(EliminateTagApp.this,
                                                  "Error loading tags: " + e.getMessage(),
                                                  "Load Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute(); // Start the SwingWorker, executing doInBackground.
    }
    /**
     * Handles the deletion of selected tags.
     * It retrieves selected items, calls TagManager to delete them, and updates the UI
     * based on the success or failure of the operation (including simulated server issues).
     */
    private void deleteSelectedTags() {
        List<String> selectedTags = tagList.getSelectedValuesList(); // Get all selected tags from the JList
        // Check if any tags were selected.
        if (selectedTags.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                                          "Please select one or more tags to delete.",
                                          "No Tags Selected", JOptionPane.INFORMATION_MESSAGE);
            return; // Exit if no tags are selected
        }
        // Disable buttons during the deletion process to prevent multiple, overlapping requests
        deleteButton.setEnabled(false);
        simulateFailureCheckBox.setEnabled(false);
        // Use SwingWorker for deletion to keep the UI responsive during the simulated backend call.
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                // This simulates the network call for deletion which might take time or fail.
                return tagManager.deleteTags(selectedTags);
            }
            @Override
            protected void done() {
                // Re-enable buttons once the operation is complete.
                deleteButton.setEnabled(true);
                simulateFailureCheckBox.setEnabled(true);
                try {
                    boolean success = get(); // Get the boolean result (success/failure) from doInBackground
                    if (success) {
                        // Notify the user about the successful elimination of selected tags.
                        JOptionPane.showMessageDialog(EliminateTagApp.this,
                                                      "Successfully deleted " + selectedTags.size() + " tag(s).",
                                                      "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
                        loadTags(); // Reload tags to display the updated list (reflecting deletions)
                    } else {
                        // Notify about server interruption (ETOUR) as per exit conditions.
                        JOptionPane.showMessageDialog(EliminateTagApp.this,
                                                      "Deletion failed: Interruption of the connection to the server ETOUR.",
                                                      "Deletion Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    // Handle any unexpected errors that might occur during the SwingWorker's execution.
                    JOptionPane.showMessageDialog(EliminateTagApp.this,
                                                  "An unexpected error occurred during deletion: " + e.getMessage(),
                                                  "Deletion Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute(); // Start the SwingWorker to run the deletion process.
    }
    /**
     * Main method to run the ELIMINATAG application.
     * This is the entry point of the GUI application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure that all GUI updates and manipulations are performed on the Event Dispatch Thread (EDT).
        // This is crucial for thread safety in Swing applications.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create an instance of our main application frame and make it visible.
                new EliminateTagApp().setVisible(true);
            }
        });
    }
}