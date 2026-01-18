/**
 * Main application class for Cultural Heritage Management System
 * Implements the InsertCulturalHeritage use case with GUI
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.io.*;
public class Main {
    private JFrame frame;
    private JPanel mainPanel;
    private CulturalHeritageDatabase database;
    private JLabel statusLabel;
    private JProgressBar progressBar;
    public Main() {
        // Database will be initialized asynchronously to avoid blocking EDT
        database = null;
    }
    private void initializeAndShowGUI() {
        // This method is called from the EDT
        frame = new JFrame("Cultural Heritage Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        // Create a temporary loading panel
        JPanel loadingPanel = new JPanel(new BorderLayout());
        JLabel loadingLabel = new JLabel("Loading Cultural Heritage Database...", JLabel.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        loadingPanel.add(loadingLabel, BorderLayout.CENTER);
        loadingPanel.add(progressBar, BorderLayout.SOUTH);
        loadingPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));
        frame.add(loadingPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Load database in background thread using SwingWorker
        SwingWorker<CulturalHeritageDatabase, Void> worker = new SwingWorker<CulturalHeritageDatabase, Void>() {
            @Override
            protected CulturalHeritageDatabase doInBackground() throws Exception {
                // Perform potentially slow I/O operations in background
                return CulturalHeritageDatabase.getInstance();
            }
            @Override
            protected void done() {
                try {
                    database = get();
                    // Database loaded successfully, now create the main UI
                    createMainUI();
                    showLoginMessage();
                } catch (Exception e) {
                    // Handle loading failure gracefully
                    JOptionPane.showMessageDialog(frame,
                        "Failed to initialize database: " + e.getMessage() + 
                        "\nApplication will start with empty database.",
                        "Initialization Error",
                        JOptionPane.ERROR_MESSAGE);
                    database = CulturalHeritageDatabase.getInstance(); // Get fresh instance
                    createMainUI();
                    showLoginMessage();
                }
            }
        };
        worker.execute();
    }
    private void createMainUI() {
        // Remove loading panel and create main UI
        frame.getContentPane().removeAll();
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Cultural Heritage Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        // Form panel
        JPanel formPanel = createFormPanel();
        // Status panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Ready. Database loading complete.");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusPanel.add(statusLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.revalidate();
        frame.repaint();
    }
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Form fields
        String[] labels = {"ID:", "Name:", "Type:", "Location:", "Year:", "Description:"};
        JTextField[] fields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0;
            formPanel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            gbc.weightx = 1;
            fields[i] = new JTextField(20);
            formPanel.add(fields[i], gbc);
        }
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton insertButton = new JButton("Insert Cultural Heritage");
        insertButton.addActionListener(e -> {
            // Step 3: Fill out the form and submit
            CulturalHeritage heritage = createHeritageFromFields(fields);
            if (heritage != null) {
                // Step 4: Verify data and ask for confirmation
                int choice = JOptionPane.showConfirmDialog(frame,
                    "Confirm insertion of cultural heritage:\n" + heritage.toDisplayString(),
                    "Confirm Operation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                if (choice == JOptionPane.YES_OPTION) {
                    // Step 5 & 6: Confirm operation and memorize
                    if (insertCulturalHeritage(heritage)) {
                        // Success - clear form
                        for (JTextField field : fields) {
                            field.setText("");
                        }
                    }
                } else {
                    // Operation cancelled
                    statusLabel.setText("Operation cancelled by Agency Operator.");
                }
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            // Operator cancels the operation
            for (JTextField field : fields) {
                field.setText("");
            }
            statusLabel.setText("Operation cancelled. Form cleared.");
        });
        buttonPanel.add(insertButton);
        buttonPanel.add(cancelButton);
        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        return formPanel;
    }
    private CulturalHeritage createHeritageFromFields(JTextField[] fields) {
        try {
            String id = fields[0].getText().trim();
            String name = fields[1].getText().trim();
            String type = fields[2].getText().trim();
            String location = fields[3].getText().trim();
            String yearStr = fields[4].getText().trim();
            String description = fields[5].getText().trim();
            // Step 4: Validate data
            if (id.isEmpty() || name.isEmpty() || type.isEmpty() || 
                location.isEmpty() || yearStr.isEmpty()) {
                statusLabel.setText("Error: All fields except description are required!");
                JOptionPane.showMessageDialog(frame, 
                    "Invalid or insufficient data!\nAll fields except description are required.",
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return null;
            }
            int year;
            try {
                year = Integer.parseInt(yearStr);
                // Use ValidationUtils for year validation
                ValidationUtils.validateYear(year);
            } catch (NumberFormatException e) {
                statusLabel.setText("Error: Year must be a valid integer number!");
                JOptionPane.showMessageDialog(frame, 
                    "Invalid year format!\nPlease enter a valid integer year.",
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return null;
            } catch (IllegalArgumentException e) {
                statusLabel.setText("Error: " + e.getMessage());
                JOptionPane.showMessageDialog(frame, 
                    "Invalid year: " + e.getMessage(),
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return null;
            }
            // Additional validation using ValidationUtils
            ValidationUtils.validateId(id);
            ValidationUtils.validateText(name, "Name");
            ValidationUtils.validateText(type, "Type");
            ValidationUtils.validateText(location, "Location");
            if (!description.isEmpty()) {
                ValidationUtils.validateText(description, "Description");
            }
            return new CulturalHeritage(id, name, type, location, year, description);
        } catch (IllegalArgumentException e) {
            statusLabel.setText("Validation Error: " + e.getMessage());
            JOptionPane.showMessageDialog(frame, 
                "Invalid input: " + e.getMessage(),
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception e) {
            statusLabel.setText("Error creating cultural heritage object: " + e.getMessage());
            JOptionPane.showMessageDialog(frame, 
                "Error processing form data: " + e.getMessage(),
                "System Error", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    private boolean insertCulturalHeritage(CulturalHeritage heritage) {
        // Check if database is initialized
        if (database == null) {
            statusLabel.setText("Error: Database not initialized!");
            JOptionPane.showMessageDialog(frame,
                "Database is not ready. Please try again or restart the application.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            // Save to database
            boolean success = database.insertCulturalHeritage(heritage);
            if (success) {
                // Exit condition: Notify proper inclusion
                statusLabel.setText("Cultural heritage successfully inserted!");
                JOptionPane.showMessageDialog(frame, 
                    "Cultural heritage has been successfully added to the system.",
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                // Duplicate detected (quality requirement)
                statusLabel.setText("Error: Duplicate cultural heritage detected!");
                JOptionPane.showMessageDialog(frame, 
                    "Cannot insert duplicate cultural heritage!\nA cultural heritage with this ID already exists.",
                    "Duplicate Error", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (IOException e) {
            // Simulate server connection interruption
            statusLabel.setText("Error: Connection to server interrupted!");
            JOptionPane.showMessageDialog(frame, 
                "Interruption of connection to the server!\nPlease try again later.",
                "Connection Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(frame, 
                "An unexpected error occurred: " + e.getMessage(),
                "System Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    private void showLoginMessage() {
        statusLabel.setText("Logged in as Agency Operator. Ready to insert cultural heritage.");
        JOptionPane.showMessageDialog(frame,
            "Welcome Agency Operator!\nYou are now logged in.",
            "Login Successful",
            JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Starting Cultural Heritage Management System...");
            Main app = new Main();
            app.initializeAndShowGUI();
        });
    }
}