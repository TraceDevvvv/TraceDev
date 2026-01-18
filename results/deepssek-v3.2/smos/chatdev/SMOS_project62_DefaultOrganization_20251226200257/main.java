'''
Main.java
Entry point for the Student Report Card System.
Launches the application and sets up the main frame.
'''
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety for GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the parent login frame
                ParentLoginFrame loginFrame = new ParentLoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
}