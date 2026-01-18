'''
Main class to launch the Disciplinary Note Management System.
Creates the initial login screen.
'''
public class Main {
    /**
     * Main method initializes the system and displays login screen.
     * 
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        // Initialize system components
        NoteSystem system = new NoteSystem();
        // Create and show login frame
        LoginFrame loginFrame = new LoginFrame(system);
        loginFrame.setVisible(true);
        System.out.println("Disciplinary Note Management System started.");
    }
}