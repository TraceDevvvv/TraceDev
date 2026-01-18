'''
Main entry point for the Cultural Heritage Search System
Launches the application with the GUI
'''
import javax.swing.SwingUtilities;
public class SearchCulturalHeritage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Thread serverThread = new Thread(() -> {
                    CulturalHeritageServer server = new CulturalHeritageServer();
                    server.start();
                });
                serverThread.start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CulturalHeritageGUI gui = new CulturalHeritageGUI();
                gui.createAndShowGUI();
            }
        });
    }
}