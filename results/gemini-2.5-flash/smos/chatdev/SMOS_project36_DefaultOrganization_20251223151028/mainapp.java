/**
 * Main application class to launch the Justification Insertion System.
 * This class sets up the main JFrame and manages the display of different panels,
 * such as the Registry Screen and the Justification Form.
 */
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class MainApp extends JFrame {
    // Panel to display the registry screen with absences.
    private RegistryScreenPanel registryScreenPanel;
    // Panel to display the justification input form.
    private JustificationFormPanel justificationFormPanel;
    // CardLayout to manage switching between different panels
    private CardLayout cardLayout;
    // Panel holding all other panels, managed by CardLayout
    private JPanel cardPanel;
    // Mock data for absences to simulate the "registry"
    private List<Absence> absences;
    /**
     * Constructor for the MainApp.
     * Initializes the JFrame, creates and manages panels.
     */
    public MainApp() {
        setTitle("Justification System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        // Initialize mock absence data. Simulate one "red" (unjustified) absence.
        absences = new ArrayList<>();
        absences.add(new Absence(UUID.randomUUID().toString(), LocalDate.now().minusDays(5), false, "Employee A"));
        absences.add(new Absence(UUID.randomUUID().toString(), LocalDate.now().minusDays(2), true, "Employee B"));
        absences.add(new Absence(UUID.randomUUID().toString(), LocalDate.now().minusDays(10), false, "Employee C")); // The absence to justify
        absences.add(new Absence(UUID.randomUUID().toString(), LocalDate.now().minusDays(1), false, "Employee D"));
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel);
        // Initialize the RegistryScreenPanel
        // Pass a callback to handle when an absence is selected for justification
        registryScreenPanel = new RegistryScreenPanel(absences, this::showJustificationFormForAbsence);
        cardPanel.add(registryScreenPanel, "RegistryScreen");
        // Initially show the registry screen
        cardLayout.show(cardPanel, "RegistryScreen");
    }
    /**
     * Displays the justification form for a selected absence.
     * This method is called as a callback from RegistryScreenPanel when an unjustified absence is selected.
     * @param absence The Absence object to be justified.
     */
    private void showJustificationFormForAbsence(Absence absence) {
        // Remove old justification form if it exists to ensure fresh state
        if (justificationFormPanel != null) {
            cardPanel.remove(justificationFormPanel);
        }
        // Create a new JustificationFormPanel for the selected absence
        // Pass callbacks for save success/failure and cancellation
        justificationFormPanel = new JustificationFormPanel(
            absence,
            this::handleJustificationResult,
            this::returnToRegistryScreen
        );
        cardPanel.add(justificationFormPanel, "JustificationForm");
        cardLayout.show(cardPanel, "JustificationForm");
        setTitle("Justification System - Justify Absence for " + absence.getEmployeeName() + " on " + absence.getDate());
    }
    /**
     * Handles the result of the justification saving operation.
     * This method is called as a callback from JustificationFormPanel.
     * @param success True if justification was saved successfully, false otherwise.
     * @param absenceId The ID of the absence that was justified.
     */
    private void handleJustificationResult(boolean success, String absenceId) {
        if (success) {
            // Update the status of the absence in the mock data
            for (Absence abs : absences) {
                if (abs.getId().equals(absenceId)) {
                    abs.setJustified(true);
                    break;
                }
            }
            // Refresh the registry screen to reflect the updated absence status
            registryScreenPanel.refreshAbsenceList();
            JOptionPane.showMessageDialog(this, "Justification saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Error message would have been shown by JustificationFormPanel within its SwingWorker's done() method.
            // No need to show a generic error here as specific errors are handled there.
        }
        // In any case (success or failure), return to the registry screen
        returnToRegistryScreen();
    }
    /**
     * Returns to the registry screen.
     * This method is called as a callback from JustificationFormPanel when operation is cancelled or completed.
     */
    private void returnToRegistryScreen() {
        cardLayout.show(cardPanel, "RegistryScreen");
        setTitle("Justification System - Registry Screen");
    }
    /**
     * Main method to start the application.
     * Creates an instance of MainApp and makes it visible.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp();
            app.setVisible(true);
        });
    }
}