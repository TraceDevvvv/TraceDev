'''
Main entry point for the Report Card System application.
Initializes and starts the GUI application.
'''
package com.chatdev.reportcardsystem;
import com.chatdev.reportcardsystem.gui.LoginFrame;
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}