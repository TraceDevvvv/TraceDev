/**
 * studentdataviewer.java
 * Main class to launch the student data viewer application.
 * This serves as the entry point for the GUI application.
 * Simulates the student's digital log functionality as per the use case.
 */
import javax.swing.SwingUtilities;
public class StudentDataViewer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentDataFrame().setVisible(true);
            }
        });
    }
}