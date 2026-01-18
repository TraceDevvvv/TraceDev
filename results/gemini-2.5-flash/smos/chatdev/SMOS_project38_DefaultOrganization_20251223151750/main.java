/**
 * This is the main entry point for the Justification Details application.
 * It initializes the JustificationService with sample data and then allows
 * an administrator to select a justification from a list to view, modify, or delete.
 */
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;
import java.time.format.DateTimeFormatter; // Added to format date in JComboBox renderer
public class Main {
    /**
     * The main method to start the application.
     * It sets up the GUI to allow an administrator to select a justification.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Initialize the JustificationService with some sample data.
            // This simulates a database or backend system.
            JustificationService justificationService = new JustificationService();
            // Retrieve all justifications to simulate the "ViewingLancogiustifies" list
            List<Justification> allJustifications = justificationService.getAllJustifications();
            if (allJustifications.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No justifications available to view.", "Information", JOptionPane.INFORMATION_MESSAGE);
                return; // Exit if no justifications
            }
            // Create a simple dialog for selection to simulate "click on one of the green absences"
            // Using a JComboBox for simplicity, in a real app this might be a JList or table
            JComboBox<Justification> justificationSelector = new JComboBox<>(new Vector<>(allJustifications));
            // Custom renderer to display meaningful text for each Justification object in the JComboBox
            justificationSelector.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Justification) {
                        Justification j = (Justification) value;
                        // Format date to string, handling potential null
                        String absenceDateStr = (j.getAbsenceDate() != null) ? j.getAbsenceDate().format(DateTimeFormatter.ISO_LOCAL_DATE) : "N/A";
                        setText(j.getId() + " - " + j.getEmployeeName() + " (" + absenceDateStr + ") - Status: " + j.getStatus());
                        // Optional: Change color for "green absences" - here we'll simulate 'Pending' or 'Approved' as 'green'
                        // This visually represents the "green absences" referred to in the use case.
                        if ("Pending".equals(j.getStatus()) || "Approved".equals(j.getStatus())) {
                            setForeground(new Color(0, 150, 0)); // Darker green
                        } else {
                            setForeground(list.getForeground()); // Default color
                        }
                    } else if (value == null) {
                        setText("Select a Justification..."); // Placeholder for initial state if no item selected
                    }
                    return this;
                }
            });
            // Show the selection dialog to the administrator
            int result = JOptionPane.showConfirmDialog(null, justificationSelector,
                    "Select Justification to View", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); // Use PLAIN_MESSAGE to remove default icon
            if (result == JOptionPane.OK_OPTION) {
                Justification selectedJustification = (Justification) justificationSelector.getSelectedItem();
                // Check if a justification was selected.
                if (selectedJustification != null) {
                    // Create and display the JustificationDetailsForm with the selected justification.
                    JustificationDetailsForm form = new JustificationDetailsForm(justificationService, selectedJustification);
                    form.setVisible(true); // Make the form visible to the user.
                } else {
                    // This case should ideally not happen if the list is not empty and "OK" is pressed,
                    // but it's good for robustness.
                    JOptionPane.showMessageDialog(null, "No justification selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            // If cancel is pressed, or if the form was not shown (e.g., no justification selected),
            // the application will implicitly end once the initial SwingUtilities.invokeLater block finishes,
            // or if the JDetailsForm is the only active JFrame, it will dispose and the app will exit if no other non-daemon threads are running.
        });
    }
}