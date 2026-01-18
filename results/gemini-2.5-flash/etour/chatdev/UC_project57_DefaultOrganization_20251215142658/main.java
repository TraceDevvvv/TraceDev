/*
 * Main class to launch the Advanced Search Application.
 * This class simply creates an instance of AdvancedSearchApp and makes it visible.
 */
public class Main {
    public static void main(String[] args) {
        // Ensure that GUI updates are done on the Event Dispatch Thread (EDT)
        javax.swing.SwingUtilities.invokeLater(() -> {
            new gui.AdvancedSearchApp().setVisible(true);
        });
    }
}