/*
 * Main class for the InsertNews application.
 * This file sets up the GUI and coordinates between the data entry, validation, and storage components.
 * Implements the flow described in the use case: display form, validate, confirm, store, notify.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
public class Main {
    public static void main(String[] args) {
        // Create and show the GUI on the Event Dispatch Thread for thread-safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NewsInsertionFrame().setVisible(true);
            }
        });
    }
}
/**
 * JFrame that implements the GUI for the InsertNews use case.
 * Contains form fields, validation, confirmation dialog, and storage logic.
 */
class NewsInsertionFrame extends JFrame {
    private JTextField titleField;
    private JTextArea contentArea;
    private JButton submitButton;
    private JButton cancelButton;
    private List<String> newsList; // In-memory storage for news
    private String dataFile = "news_data.txt"; // File for persistent storage
    private BufferedWriter fileWriter;
    private SimpleDateFormat dateFormat;
    private SwingWorker<Boolean, Void> storageWorker; // Background worker for storage operations
    // Constructor sets up the GUI components and storage
    public NewsInsertionFrame() {
        super("Insert News - Agency Operator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center window
        // Initialize storage (in-memory list and file)
        initStorage();
        // Build GUI
        initUI();
        // Attach event handlers
        attachHandlers();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    // Build the user interface with form elements
    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Form panel with labels and fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        // Title field
        formPanel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        titleField = new JTextField(30);
        formPanel.add(titleField, gbc);
        // Content area
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Content:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        contentArea = new JTextArea(10, 30);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(contentArea);
        formPanel.add(scrollPane, gbc);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Button panel at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        buttonPanel.add(submitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    // Initialize in-memory list and ensure the data file exists
    private void initStorage() {
        newsList = new ArrayList<>();
        try {
            // Ensure the data file exists; create if it doesn't
            File file = new File(dataFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            // Open the file in append mode for writing
            fileWriter = new BufferedWriter(new FileWriter(dataFile, true));
        } catch (IOException e) {
            // Simulate server ETOUR connection interruption at startup
            fileWriter = null;
            JOptionPane.showMessageDialog(this,
                    "Warning: Cannot connect to server ETOUR. Persistent storage unavailable.\n" +
                    "News will only be stored in memory.",
                    "Storage Initialization Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    // Attach action listeners to the buttons
    private void attachHandlers() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCancel();
            }
        });
    }
    // Step 3 & 4: Validate the entered data and ask for confirmation.
    private void handleSubmit() {
        // Retrieve and trim input
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();
        // Validate data (simplified validation)
        if (title.isEmpty() || content.isEmpty()) {
            // Activate the Errored use case (here we just show a message)
            JOptionPane.showMessageDialog(this,
                    "Title and Content must not be empty.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Step 5: Ask for confirmation
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to insert this news?",
                "Confirm Insertion",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Disable submit button to prevent multiple submissions
            submitButton.setEnabled(false);
            // Create a background worker for storage operation to avoid blocking EDT
            storageWorker = new SwingWorker<Boolean, Void>() {
                private String newsTitle;
                private String newsContent;
                private String newsEntry;
                {
                    // Store data for use in background thread
                    this.newsTitle = title;
                    this.newsContent = content;
                }
                @Override
                protected Boolean doInBackground() throws Exception {
                    // Step 6: Store the data in background thread
                    return storeNews(newsTitle, newsContent);
                }
                @Override
                protected void done() {
                    // Re-enable submit button
                    submitButton.setEnabled(true);
                    try {
                        boolean success = get(); // Retrieve result from doInBackground
                        if (success) {
                            // Exit condition: notify proper placement
                            JOptionPane.showMessageDialog(NewsInsertionFrame.this,
                                    "News inserted successfully.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            // Clear form for next entry
                            titleField.setText("");
                            contentArea.setText("");
                        } else {
                            // Handle storage failure (e.g., server interruption)
                            JOptionPane.showMessageDialog(NewsInsertionFrame.this,
                                    "Operation failed due to server connection interruption (ETOUR). News not stored.",
                                    "Storage Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (InterruptedException e) {
                        // Thread was interrupted
                        Thread.currentThread().interrupt();
                        JOptionPane.showMessageDialog(NewsInsertionFrame.this,
                                "Operation interrupted.",
                                "Interruption",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (ExecutionException e) {
                        // Exception occurred during storage
                        JOptionPane.showMessageDialog(NewsInsertionFrame.this,
                                "Error during storage: " + e.getCause().getMessage(),
                                "Storage Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            };
            // Execute the background worker
            storageWorker.execute();
        }
    }
    // Step 6: Store the news in in-memory list and append to file.
    // Returns true on success, false on failure (e.g., server interruption).
    // This method runs in a background thread (not on EDT)
    private boolean storeNews(String title, String content) {
        String timestamp = dateFormat.format(new Date());
        String newsEntry = "[" + timestamp + "] Title: " + title + " | Content: " + content;
        // Add to in-memory list first (tentative)
        synchronized(newsList) {
            newsList.add(newsEntry);
        }
        System.out.println("News temporarily stored in memory: " + newsEntry);
        // Check if fileWriter is null (server connection interrupted at startup)
        if (fileWriter == null) {
            // Server ETOUR connection interruption: rollback in-memory addition
            synchronized(newsList) {
                newsList.remove(newsEntry);
            }
            System.err.println("Connection to server ETOUR interrupted: persistent storage unavailable.");
            return false;
        }
        // Append to file (simulating server ETOUR write operation)
        try {
            synchronized(fileWriter) {
                fileWriter.write(newsEntry);
                fileWriter.newLine();
                fileWriter.flush(); // Ensure data is written immediately
            }
            return true;
        } catch (IOException e) {
            // Simulate interruption of connection to the server ETOUR during write
            // Rollback: remove the entry from in-memory list because file storage failed
            synchronized(newsList) {
                newsList.remove(newsEntry);
            }
            System.err.println("Connection to server ETOUR interrupted during write: " + e.getMessage());
            return false;
        }
    }
    // Handle cancellation (Exit condition: Operator cancels the operation)
    private void handleCancel() {
        int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel the insertion?",
                "Cancel Operation",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // Cancel any ongoing background operation
            if (storageWorker != null && !storageWorker.isDone()) {
                storageWorker.cancel(true);
            }
            dispose(); // Close the window
        }
    }
    // Clean up file writer when the window is closed
    @Override
    public void dispose() {
        // Cancel any ongoing background operation
        if (storageWorker != null && !storageWorker.isDone()) {
            storageWorker.cancel(true);
        }
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            // ignore
        }
        super.dispose();
    }
}