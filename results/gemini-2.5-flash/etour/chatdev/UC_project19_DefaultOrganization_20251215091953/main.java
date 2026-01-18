'''
Main class to launch the EliminaBannerApp.
'''
public class Main {
    /**
     * The main method, which is the entry point of the application.
     * It ensures the GUI is created and run on the Event Dispatch Thread (EDT)
     * as required by Swing applications.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create an instance of the main application class, which will set up the GUI.
                new gui.EliminaBannerApp();
            }
        });
    }
}