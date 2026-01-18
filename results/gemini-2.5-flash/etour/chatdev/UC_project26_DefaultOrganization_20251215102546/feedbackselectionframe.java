'''
This JFrame allows the Agency Operator to view a list of feedback
for a selected site and choose one to edit its comment.
'''
package com.chatdev.gui.components;
import com.chatdev.models.Feedback;
import com.chatdev.gui.utils.DialogUtils;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Runnable; // Explicitly import Runnable
public class FeedbackSelectionFrame extends JFrame {
    private JList<Feedback> feedbackList;
    private DefaultListModel<Feedback> listModel;
    private JButton selectButton;
    /**
     * Constructs a FeedbackSelectionFrame.
     * @param siteName The name of the site for which feedback is being displayed.
     * @param feedbackListForSite A list of Feedback objects associated with the selected site.
     * @param onSelectCallback A Consumer functional interface to be called when feedback is selected.
     *                         The selected Feedback object will be passed to this callback.
     * @param onCancelCallback A Runnable functional interface to be called when the operation is cancelled.
     */
    public FeedbackSelectionFrame(String siteName, List<Feedback> feedbackListForSite, Consumer<Feedback> onSelectCallback, Runnable onCancelCallback) {
        super("Select Feedback for Site: " + siteName);
        // Use DISPOSE_ON_CLOSE to hide and dispose this frame, but not terminate the JVM
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null); // Center the window
        listModel = new DefaultListModel<>();
        boolean hasActualFeedback = !feedbackListForSite.isEmpty();
        if (!hasActualFeedback) {
            // Add a dummy element to display a message if no actual feedback
            listModel.addElement(new Feedback(0, 0, "No feedback available for this site."));
        } else {
            for (Feedback feedback : feedbackListForSite) {
                listModel.addElement(feedback);
            }
        }
        feedbackList = new JList<>(listModel);
        feedbackList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        feedbackList.setLayoutOrientation(JList.VERTICAL);
        feedbackList.setVisibleRowCount(-1); // Display all if space permits
        // Disable selection if there's only the "No feedback" message
        if (!hasActualFeedback) {
            feedbackList.setEnabled(false);
        }
        JScrollPane listScrollPane = new JScrollPane(feedbackList);
        listScrollPane.setPreferredSize(new Dimension(450, 250));
        selectButton = new JButton("Select Feedback to Edit");
        // Disable button if no actual feedback
        if (!hasActualFeedback) {
            selectButton.setEnabled(false);
        }
        selectButton.addActionListener(e -> {
            Feedback selectedFeedback = feedbackList.getSelectedValue();
            // Check if a feedback is selected and it's not the dummy "No feedback" entry (ID 0)
            if (selectedFeedback != null && selectedFeedback.getId() != 0) {
                // If valid feedback is selected, call the callback and dispose of this frame
                onSelectCallback.accept(selectedFeedback);
                dispose(); // Close this frame
            } else if (selectedFeedback != null && selectedFeedback.getId() == 0) {
                 DialogUtils.showError(this, "There is no actual feedback to select.", "No Feedback Available");
            }
            else {
                DialogUtils.showError(this, "Please select a feedback from the list.", "No Feedback Selected");
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            // Inform the system that the operation was cancelled
            onCancelCallback.run();
            dispose(); // Close this frame
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectButton);
        buttonPanel.add(cancelButton);
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); // Added some spacing
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        mainPanel.add(new JLabel("Feedback for " + siteName + ":"), BorderLayout.NORTH);
        mainPanel.add(listScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
}