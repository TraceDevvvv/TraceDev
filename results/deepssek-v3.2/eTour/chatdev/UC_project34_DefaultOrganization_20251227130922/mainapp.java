'''
MainApp.java
Main entry point for the Cultural Heritage Search Application.
Launches the GUI application.
'''
package culturalheritage.search;
import javax.swing.SwingUtilities;
public class MainApp {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure GUI is created on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SearchGUI gui = new SearchGUI();
                gui.setVisible(true);
            }
        });
    }
}