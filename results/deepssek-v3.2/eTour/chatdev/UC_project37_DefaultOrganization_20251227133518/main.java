'''
Main entry point for the Logout System application.
This class launches the GUI application.
'''
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI creation
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}