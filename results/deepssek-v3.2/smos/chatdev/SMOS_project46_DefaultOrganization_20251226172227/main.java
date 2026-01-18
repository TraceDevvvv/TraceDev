'''
Main entry point for the Report Card Insertion System
Launches the login screen
'''
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DatabaseSimulator.initialize();
                new LoginFrame();
            }
        });
    }
}