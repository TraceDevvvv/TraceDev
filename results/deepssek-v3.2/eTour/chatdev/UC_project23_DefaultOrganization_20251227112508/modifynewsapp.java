/**
 * Main application class for the ModifyNews use case.
 * This class launches the GUI for the Agency Operator to modify news data.
 * The application follows the specified use case flow, handles validation,
 * and simulates potential server interruptions (ETOUR).
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class ModifyNewsApp {
    public static void main(String[] args) {
        // Use the event dispatch thread for Swing components
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    /**
     * Creates and displays the main application window.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("News Modification System - Agency Operator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 650);
        // Create main panel with simulated login barrier
        MainPanel mainPanel = new MainPanel();
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
    }
}