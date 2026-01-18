/**
 * Controller class that manages interactions between the view and model.
 * Handles user actions and updates the model accordingly.
 */
import javax.swing.*;
import java.util.*;
public class RegistryController {
    private ClassRegistryModel model;
    private RegistryGUI view;
    public RegistryController(ClassRegistryModel model) {
        this.model = model;
    }
    public void setView(RegistryGUI view) {
        this.view = view;
        // Initialize view with data from model
        view.setDates(model.getDates());
        if (!model.getDates().isEmpty()) {
            loadDate(model.getDates().get(0));
        }
    }
    public void loadDate(Date date) {
        if (!model.isSmosConnected()) {
            JOptionPane.showMessageDialog(null, 
                "Cannot load data: Connection to SMOS server interrupted.", 
                "Connection Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<AttendanceRecord> records = model.getAttendanceForDate(date);
        if (view != null) {
            view.displayAttendanceRecords(records);
        }
    }
    public void updateRecord(Date date, Student student, 
                            String status, String justification, 
                            String disciplinaryNotes) {
        if (!model.isSmosConnected()) {
            JOptionPane.showMessageDialog(null, 
                "Cannot update: Connection to SMOS server interrupted.", 
                "Connection Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        model.updateAttendanceRecord(date, student, status, justification, disciplinaryNotes);
        loadDate(date); // Refresh the view
    }
    public void simulateConnectionInterruption() {
        model.simulateConnectionInterruption();
        JOptionPane.showMessageDialog(null, 
            "Connection to SMOS server has been interrupted.\n" +
            "All data operations are now disabled until reconnection.", 
            "SMOS Connection Interrupted", 
            JOptionPane.WARNING_MESSAGE);
        if (view != null) {
            view.disableDataOperations();
        }
    }
}