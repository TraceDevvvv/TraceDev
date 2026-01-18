/**
 * Main entry point for the EnterAbsencesAdmin application.
 * This program provides a GUI for administrators to record student absences.
 * It allows selecting a date, entering attendance data, saving to a server,
 * and sending email notifications to parents of absent students.
 */
public class main {
    public static void main(String[] args) {
        // Launch the GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AttendanceGUI().setVisible(true);
            }
        });
    }
}