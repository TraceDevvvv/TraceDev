/**
 * Main entry point for the ModifyComment application.
 * This program simulates a GUI-based system for an agency operator to edit comments on feedback for a site.
 * It follows the use case "ModifyComment" and includes necessary steps from viewing site list to updating the comment.
 * The code is structured with multiple classes to separate concerns and ensure modularity.
 * All required operations including data validation, user confirmation, and error handling are implemented.
 * The GUI is implemented using Java Swing.
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ModifyCommentApp {
    // Simulated database for sites and feedbacks
    private static Map<String, Site> sites = new HashMap<>();
    private static Site selectedSite = null;
    private static Feedback selectedFeedback = null;
    private static JFrame mainFrame;
    private static CardLayout cardLayout;
    private static JPanel cardPanel;
    public static void main(String[] args) {
        // Initialize sample data
        initializeData();
        // Set up GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    /**
     * Initialize sample sites and feedbacks for demonstration.
     */
    private static void initializeData() {
        Site site1 = new Site("Site001", "Example Site 1");
        site1.addFeedback(new Feedback("F001", "Great site, but could use more features."));
        site1.addFeedback(new Feedback("F002", "The design is outdated."));
        sites.put(site1.getId(), site1);
        Site site2 = new Site("Site002", "Demo Site 2");
        site2.addFeedback(new Feedback("F003", "Excellent service!"));
        sites.put(site2.getId(), site2);
    }
    /**
     * Create and display the main GUI window with CardLayout for multiple screens.
     */
    private static void createAndShowGUI() {
        mainFrame = new JFrame("Modify Comment System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        // Step 1: Site Selection Screen
        cardPanel.add(createSiteSelectionPanel(), "SITE_SELECTION");
        // Step 2: Feedback Selection Screen
        cardPanel.add(createFeedbackSelectionPanel(), "FEEDBACK_SELECTION");
        // Step 3: Comment Edit Screen
        cardPanel.add(createCommentEditPanel(), "COMMENT_EDIT");
        // Step 4: Confirmation Screen
        cardPanel.add(createConfirmationPanel(), "CONFIRMATION");
        // Error Screen
        cardPanel.add(createErrorPanel(), "ERROR");
        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);
        // Start with site selection
        cardLayout.show(cardPanel, "SITE_SELECTION");
    }
    /**
     * Panel for Step 1: Display list of sites (result of SearchSite use case).
     * Allows operator to select a site and proceed to feedback selection.
     */
    private static JPanel createSiteSelectionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel("Select a Site to Modify Feedback Comment", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(title, BorderLayout.NORTH);
        // List of sites (simulated from use case SearchSite)
        DefaultListModel<String> siteListModel = new DefaultListModel<>();
        for (Site site : sites.values()) {
            siteListModel.addElement(site.getName() + " (" + site.getId() + ")");
        }
        JList<String> siteList = new JList<>(siteListModel);
        siteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(siteList);
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton nextButton = new JButton("Next");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(nextButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        // Next button action: go to feedback selection
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = siteList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(mainFrame, "Please select a site first.", "No Selection", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String selectedKey = sites.keySet().toArray()[selectedIndex].toString();
                selectedSite = sites.get(selectedKey);
                cardLayout.show(cardPanel, "FEEDBACK_SELECTION");
            }
        });
        // Cancel button action: exit application (simulates operator cancelling operation)
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to cancel?", "Cancel Operation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        return panel;
    }
    /**
     * Panel for Step 2 and 3: Display feedbacks for selected site and allow selection.
     */
    private static JPanel createFeedbackSelectionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel("Select Feedback to Edit Comment for Site: " + selectedSite.getName(), JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(title, BorderLayout.NORTH);
        // List of feedbacks for the selected site
        DefaultListModel<String> feedbackListModel = new DefaultListModel<>();
        for (Feedback feedback : selectedSite.getFeedbacks()) {
            feedbackListModel.addElement(feedback.getId() + ": " + feedback.getComment());
        }
        JList<String> feedbackList = new JList<>(feedbackListModel);
        feedbackList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(feedbackList);
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton nextButton = new JButton("Edit Selected Feedback");
        JButton backButton = new JButton("Back");
        buttonPanel.add(nextButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        // Next button action: go to comment edit screen
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = feedbackList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(mainFrame, "Please select a feedback first.", "No Selection", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                selectedFeedback = selectedSite.getFeedbacks().get(selectedIndex);
                cardLayout.show(cardPanel, "COMMENT_EDIT");
            }
        });
        // Back button action: return to site selection
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSite = null;
                cardLayout.show(cardPanel, "SITE_SELECTION");
            }
        });
        return panel;
    }
    /**
     * Panel for Step 4 and 5: Display form for editing comment of selected feedback.
     */
    private static JPanel createCommentEditPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel("Edit Comment for Feedback ID: " + selectedFeedback.getId(), JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(title, BorderLayout.NORTH);
        JTextArea commentArea = new JTextArea(selectedFeedback.getComment(), 10, 40);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(commentArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton nextButton = new JButton("Submit");
        JButton backButton = new JButton("Back");
        buttonPanel.add(nextButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        // Submit button action: validate and go to confirmation
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newComment = commentArea.getText().trim();
                // Step 6: Validate data (simplified validation)
                if (newComment.isEmpty() || newComment.length() < 5) {
                    // Activate error use case
                    cardLayout.show(cardPanel, "ERROR");
                    return;
                }
                selectedFeedback.setNewComment(newComment);
                cardLayout.show(cardPanel, "CONFIRMATION");
            }
        });
        // Back button action: return to feedback selection
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFeedback = null;
                cardLayout.show(cardPanel, "FEEDBACK_SELECTION");
            }
        });
        return panel;
    }
    /**
     * Panel for Step 6 and 7: Display confirmation screen before saving changes.
     */
    private static JPanel createConfirmationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel("Confirm Comment Change", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(title, BorderLayout.NORTH);
        JTextArea confirmArea = new JTextArea("Original comment:\n" + selectedFeedback.getOriginalComment() + 
                                              "\n\nNew comment:\n" + selectedFeedback.getNewComment());
        confirmArea.setEditable(false);
        confirmArea.setLineWrap(true);
        confirmArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(confirmArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton confirmButton = new JButton("Confirm");
        JButton backButton = new JButton("Back");
        buttonPanel.add(confirmButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        // Confirm button action: save changes and show success message
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Step 8: Remember the comment changed (simulate persistence)
                selectedFeedback.saveComment();
                JOptionPane.showMessageDialog(mainFrame, "Comment updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Reset and go back to site selection
                selectedSite = null;
                selectedFeedback = null;
                cardLayout.show(cardPanel, "SITE_SELECTION");
            }
        });
        // Back button action: return to comment edit without saving
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFeedback.setNewComment(null);
                cardLayout.show(cardPanel, "COMMENT_EDIT");
            }
        });
        return panel;
    }
    /**
     * Panel for error handling (simulates Errored use case).
     * Displayed when validation fails.
     */
    private static JPanel createErrorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel("Error: Invalid Data", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.RED);
        panel.add(title, BorderLayout.NORTH);
        JTextArea errorArea = new JTextArea("The comment entered is invalid or insufficient.\n" +
                                            "Please ensure the comment is at least 5 characters long and not empty.");
        errorArea.setEditable(false);
        errorArea.setLineWrap(true);
        errorArea.setWrapStyleWord(true);
        panel.add(errorArea, BorderLayout.CENTER);
        JButton backButton = new JButton("Go Back to Edit");
        panel.add(backButton, BorderLayout.SOUTH);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "COMMENT_EDIT");
            }
        });
        return panel;
    }
}
/**
 * Represents a site with an identifier, name, and list of feedbacks.
 */
class Site {
    private String id;
    private String name;
    private List<Feedback> feedbacks;
    public Site(String id, String name) {
        this.id = id;
        this.name = name;
        this.feedbacks = new ArrayList<>();
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }
}
/**
 * Represents a feedback entry with an identifier, original comment, and new comment (for editing).
 */
class Feedback {
    private String id;
    private String originalComment;
    private String newComment;
    public Feedback(String id, String originalComment) {
        this.id = id;
        this.originalComment = originalComment;
        this.newComment = null;
    }
    public String getId() {
        return id;
    }
    public String getComment() {
        return (newComment != null) ? newComment : originalComment;
    }
    public String getOriginalComment() {
        return originalComment;
    }
    public String getNewComment() {
        return newComment;
    }
    public void setNewComment(String newComment) {
        this.newComment = newComment;
    }
    /**
     * Saves the new comment as the original comment (simulate persistence).
     */
    public void saveComment() {
        if (newComment != null) {
            originalComment = newComment;
            newComment = null;
        }
    }
}