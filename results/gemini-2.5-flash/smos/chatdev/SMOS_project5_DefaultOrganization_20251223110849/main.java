'''
This is the main entry point for the Administrator Dashboard application.
It now starts with a login screen to simulate the "logged in as administrator" precondition.
Ensures that GUI updates are performed on the Event Dispatch Thread (EDT)
as per Swing best pract.
'''
package dev.chat.system; // Assuming a package structure
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Ensure GUI updates are handled on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the LoginFrame first
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
}