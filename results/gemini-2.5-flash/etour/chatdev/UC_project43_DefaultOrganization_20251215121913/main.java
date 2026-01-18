'''
This is the main entry point for the Refreshment Point Editor application.
It initializes the DAO, Service, and GUI components, then makes the GUI visible.
'''
import gui.RefreshmentPointEditorGUI;
import service.RefreshmentPointService;
import dao.RefreshmentPointDAO;
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (EDT) for thread safety in Swing applications.
        SwingUtilities.invokeLater(() -> {
            // Initialize the Data Access Object (DAO)
            RefreshmentPointDAO refreshmentPointDAO = new RefreshmentPointDAO();
            // Initialize some sample data for demonstration
            refreshmentPointDAO.initSampleData();
            // Initialize the Service layer with the DAO
            RefreshmentPointService refreshmentPointService = new RefreshmentPointService(refreshmentPointDAO);
            // Initialize and display the GUI
            RefreshmentPointEditorGUI editorGUI = new RefreshmentPointEditorGUI(refreshmentPointService);
            editorGUI.setVisible(true);
        });
    }
}