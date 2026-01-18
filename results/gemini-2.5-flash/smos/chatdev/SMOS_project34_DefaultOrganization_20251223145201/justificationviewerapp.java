package com.chatdev.smos.app;
import com.chatdev.smos.model.Absence;
import com.chatdev.smos.service.AbsenceService;
import com.chatdev.smos.ui.AbsenceStatusCellRenderer;
import com.chatdev.smos.ui.JustificationTableModel;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;
/**
 * {@code JustificationViewerApp} is the main class that creates and manages the graphical user interface
 * for viewing student absence justifications. It simulates the administrator's role in
 * viewing a student's absence records, as described in the use case.
 * The application adheres to Java Swing best pract, including using a {@code SwingWorker}
 * for background data loading to maintain UI responsiveness.
 */
public class JustificationViewerApp extends JFrame {
    private JTable absenceTable;
    private JustificationTableModel tableModel;
    private JLabel statusLabel; // Displays loading status or other messages to the user.
    // A hardcoded student ID for demonstration purposes, simulating the administrator
    // having selected a student after the "SplitTaTtAlTeloregistration" use case.
    private static final String STUDENT_ID = "S12345";
    /**
     * Constructs the {@code JustificationViewerApp}, setting up the main window properties
     * and initializing the user interface components.
     */
    public JustificationViewerApp() {
        super("Student Absences Justification Viewer - " + STUDENT_ID); // Set the window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensures the application exits when the window is closed
        setSize(800, 600); // Set initial dimensions of the window
        setLocationRelativeTo(null); // Centers the window on the screen
        initializeUI(); // Builds and arranges GUI components
        loadAbsences(STUDENT_ID); // Initiates the loading of absence data for the selected student
    }
    /**
     * Initializes and lays out all the graphical user interface components within the frame.
     * This includes a title, the table for absences, and a status bar.
     */
    private void initializeUI() {
        // Use BorderLayout for the main frame to organize components into sections (NORTH, CENTER, SOUTH).
        setLayout(new BorderLayout(10, 10)); // 10-pixel gap between components
        // Header Panel: Contains the main title of the viewer.
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Absence Justifications for Student " + STUDENT_ID + " (Academic Year: 2023-2024)");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22)); // Larger, bold font for prominence
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH); // Place header at the top of the frame
        // Table initialization: JTable to display absence data.
        tableModel = new JustificationTableModel(java.util.Collections.emptyList()); // Initialize with an empty model
        absenceTable = new JTable(tableModel);
        absenceTable.setFillsViewportHeight(true); // Ensures the table fills the available scroll pane height
        absenceTable.setRowSelectionAllowed(false); // Disables row selection as this is a view-only task
        absenceTable.getTableHeader().setReorderingAllowed(false); // Prevents users from dragging columns to reorder them
        absenceTable.getTableHeader().setResizingAllowed(true); // Allows users to resize columns
        // Apply the custom cell renderer to all columns.
        // This renderer colors rows based on the justification status (green for justified, red for to justify).
        TableCellRenderer renderer = new AbsenceStatusCellRenderer();
        for (int i = 0; i < absenceTable.getColumnCount(); i++) {
            absenceTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        // Scroll Pane: Embeds the table to provide scrolling capability if content exceeds window size.
        JScrollPane scrollPane = new JScrollPane(absenceTable);
        add(scrollPane, BorderLayout.CENTER); // Place the scrollable table in the center of the frame
        // Status Bar: Displays messages to the user, such as loading status or success/error messages.
        statusLabel = new JLabel("Application Ready. Loading absences...");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add padding for better appearance
        add(statusLabel, BorderLayout.SOUTH); // Place status bar at the bottom
    }
    /**
     * Initiates the loading of student absences using {@link AbsenceService}.
     * This process is performed in a background thread using {@code SwingWorker} to keep the GUI
     * responsive during potentially time-consuming data retrieval operations, simulating network delays.
     *
     * @param studentId The ID of the student whose absences are to be fetched and displayed.
     */
    private void loadAbsences(String studentId) {
        statusLabel.setText("Connecting to SMOS server and loading absences for student " + studentId + "... Please wait.");
        // SwingWorker enables background tasks, separating long-running operations from the Event Dispatch Thread (EDT).
        SwingWorker<List<Absence>, Void> worker = new SwingWorker<List<Absence>, Void>() {
            /**
             * Performs the data fetching in a background thread.
             * @return A List of Absence objects retrieved from the AbsenceService.
             * @throws Exception if an error occurs during data retrieval.
             */
            @Override
            protected List<Absence> doInBackground() throws Exception {
                // Simulate connection establishment before attempting to fetch data.
                AbsenceService.connect();
                // Call the service to get mock student absence data.
                return AbsenceService.getStudentAbsences(studentId);
            }
            /**
             * Executed on the Event Dispatch Thread after `doInBackground` completes.
             * Updates the GUI with the fetched data or displays an error message.
             */
            @Override
            protected void done() {
                try {
                    List<Absence> absences = get(); // Retrieve the result from the background task.
                    // Update the table model with the newly fetched data.
                    // This must be done on the EDT to ensure thread safety for Swing components.
                    SwingUtilities.invokeLater(() -> {
                        tableModel = new JustificationTableModel(absences);
                        absenceTable.setModel(tableModel); // Set the new model to the table, refreshing its display.
                        // Reapply the custom renderer to ensure colors are correctly displayed with the new data.
                        // This is necessary because setModel() may reset table column properties.
                        TableCellRenderer renderer = new AbsenceStatusCellRenderer();
                        for (int i = 0; i < absenceTable.getColumnCount(); i++) {
                            absenceTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
                        }
                        // Adjust preferred column widths for better readability of data.
                        absenceTable.getColumnModel().getColumn(0).setPreferredWidth(100); // Date column
                        absenceTable.getColumnModel().getColumn(1).setPreferredWidth(400); // Reason column (more space)
                        absenceTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Status column
                        statusLabel.setText("Absences loaded successfully for student " + studentId + ".");
                    });
                } catch (Exception ex) {
                    // Handle any exceptions that occurred during data fetching (e.g., network error, service issue).
                    SwingUtilities.invokeLater(() -> {
                        statusLabel.setText("Error loading absences: " + ex.getMessage());
                        JOptionPane.showMessageDialog(JustificationViewerApp.this,
                                "Failed to load absences: " + ex.getMessage() + "\nCheck console for details.",
                                "Error Loading Absences", JOptionPane.ERROR_MESSAGE);
                        System.err.println("Exception while loading absences: " + ex.getMessage());
                        ex.printStackTrace(); // Print stack trace for debugging purposes.
                    });
                } finally {
                    // Explicitly disconnect from the service after data is loaded or an error occurs,
                    // addressing the postcondition "The administrator interrupts the connection to the SMOS server interrupted".
                    AbsenceService.disconnect();
                }
            }
        };
        worker.execute(); // Start the SwingWorker, initiating the background data load.
    }
    /**
     * The main entry point for the application.
     * It ensures that the GUI creation and updates are performed on the Event Dispatch Thread (EDT),
     * which is a requirement for thread-safe Swing applications.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            new JustificationViewerApp().setVisible(true); // Create an instance and make the window visible.
        });
    }
}