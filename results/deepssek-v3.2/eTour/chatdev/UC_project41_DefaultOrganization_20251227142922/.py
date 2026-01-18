/**
 * MenuEditorFrame: The main GUI frame for editing the restaurant's weekly menu.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
public class MenuEditorFrame extends JFrame {
    private MenuManager menuManager;
    private JComboBox<String> dayComboBox;
    private JTextArea menuTextArea;
    private JButton loadButton;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton verifyButton;
    /**
     * Constructor: sets up the main editor interface.
     */
    public MenuEditorFrame() {
        setTitle("Restaurant Menu Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null); // Center on screen
        menuManager = new MenuManager();
        // Check server connection on startup
        if (!menuManager.checkServerConnection()) {
            JOptionPane.showMessageDialog(this, "Cannot connect to server. Please check your internet connection.", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Top panel: Day selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Select Day:"));
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<>(days);
        topPanel.add(dayComboBox);
        loadButton = new JButton("Load Menu");
        topPanel.add(loadButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        // Center panel: Text area for menu editing
        menuTextArea = new JTextArea(20, 40);
        menuTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(menuTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Bottom panel: Action buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        verifyButton = new JButton("Verify & Confirm");
        saveButton = new JButton("Save Changes");
        cancelButton = new JButton("Cancel");
        bottomPanel.add(verifyButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(cancelButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        // Action listeners
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMenuForSelectedDay();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMenuForSelectedDay();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelOperation();
            }
        });
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyAndConfirm();
            }
        });
        add(mainPanel);
    }
    /**
     * Load the menu for the selected day into the text area.
     */
    private void loadMenuForSelectedDay() {
        String selectedDay = (String) dayComboBox.getSelectedItem();
        DayMenu dayMenu = menuManager.getDayMenu(selectedDay);
        menuTextArea.setText("");
        if (dayMenu != null) {
            StringBuilder sb = new StringBuilder();
            for (String dish : dayMenu.getDishes()) {
                sb.append(dish).append("\n");
            }
            menuTextArea.setText(sb.toString().trim());
        }
        JOptionPane.showMessageDialog(this, "Menu loaded for " + selectedDay, "Load Complete", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Save the current text area content as the menu for the selected day.
     */
    private void saveMenuForSelectedDay() {
        String selectedDay = (String) dayComboBox.getSelectedItem();
        String text = menuTextArea.getText().trim();
        List<String> dishes = new ArrayList<>();
        if (!text.isEmpty()) {
            String[] lines = text.split("\n");
            for (String line : lines) {
                line = line.trim();
                if (!line.isEmpty()) {
                    dishes.add(line);
                }
            }
        }
        // Update the menu
        menuManager.updateDayMenu(selectedDay, dishes);
        JOptionPane.showMessageDialog(this, "Menu saved for " + selectedDay, "Save Successful", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Cancel the operation and close the application.
     */
    private void cancelOperation() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel? All unsaved changes will be lost.", "Confirm Cancel", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    /**
     * Verify the current input and ask for confirmation before saving.
     */
    private void verifyAndConfirm() {
        String text = menuTextArea.getText().trim();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Menu cannot be empty. Please enter at least one dish.", "Invalid Data", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Check for invalid lines (e.g., too short or too long)
        String[] lines = text.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (line.length() < 2) {
                JOptionPane.showMessageDialog(this, "Dish name must be at least 2 characters: '" + line + "'", "Invalid Data", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (line.length() > 100) {
                JOptionPane.showMessageDialog(this, "Dish name too long (max 100 characters): '" + line + "'", "Invalid Data", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        // Ask for confirmation
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to save the changes?", "Confirm Edit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            saveMenuForSelectedDay(); // Proceed with saving
        }
    }
}