// Entry point of the ViewFeedback application. Launches the main GUI window.
public class Main {
    public static void main(String[] args) {
        // Launch the GUI application on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ViewFeedbackApp app = new ViewFeedbackApp();
                app.setVisible(true);
            }
        });
    }
}