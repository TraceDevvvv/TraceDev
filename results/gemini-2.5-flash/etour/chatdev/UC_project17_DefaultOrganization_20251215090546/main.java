'''
Main entry point for the Banner Modification Application.
This class simply launches the main GUI application by calling its main method.
All GUI initialization and application logic are handled within the BannerModificationApp class.
'''
/**
 * Main entry point for the Banner Modification Application.
 * This class simply launches the main GUI application by calling its main method.
 * All GUI initialization and application logic are handled within the BannerModificationApp class.
 */
public class Main {
    /**
     * The main method that starts the Banner Modification Application.
     * It delegates the application startup to the BannerModificationApp's main method
     * to ensure proper Swing Event Dispatch Thread (EDT) handling.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Call the main method of BannerModificationApp to start the GUI.
        // This implicitly handles SwingUtilities.invokeLater for thread safety.
        gui.BannerModificationApp.main(args);
    }
}