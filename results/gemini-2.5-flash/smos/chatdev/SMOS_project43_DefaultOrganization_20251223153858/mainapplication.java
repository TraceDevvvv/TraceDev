'''
This is the main application class that sets up the JFrame and manages the switching between different GUI panels (screens).
It acts as the central orchestrator for the application's user interface.
'''
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * MainApplication class that initializes the GUI and the core serv.
 * It uses CardLayout to switch between different screens: Login, Registry, and NoteDetail.
 */
public class MainApplication extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private AuthService authService;
    private ArchiveService archiveService;
    private NotificationService notificationService;
    private SMOSServerConnection smosServerConnection;
    private LoginScreen loginScreen;
    private RegistryScreen registryScreen;
    private NoteDetailScreen noteDetailScreen;
    public static final String LOGIN_SCREEN = "LoginScreen";
    public static final String REGISTRY_SCREEN = "RegistryScreen";
    public static final String NOTE_DETAIL_SCREEN = "NoteDetailScreen";
    /**
     * Constructor for MainApplication.
     * Initializes serv, creates GUI panels, and sets up the JFrame.
     */
    public MainApplication() {
        super("Disciplinary Note Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        // Initialize serv
        authService = new AuthService();
        archiveService = new ArchiveService();
        notificationService = new NotificationService();
        smosServerConnection = new SMOSServerConnection();
        // Populate with some dummy data for demonstration
        initializeDummyData();
        // Set up CardLayout for panel switching
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        // Initialize GUI screens
        loginScreen = new LoginScreen(this, authService);
        registryScreen = new RegistryScreen(this, archiveService);
        noteDetailScreen = new NoteDetailScreen(this, authService, archiveService, notificationService, smosServerConnection);
        // Add screens to the card panel
        cardPanel.add(loginScreen, LOGIN_SCREEN);
        cardPanel.add(registryScreen, REGISTRY_SCREEN);
        cardPanel.add(noteDetailScreen, NOTE_DETAIL_SCREEN);
        add(cardPanel);
        // Start with the login screen
        showScreen(LOGIN_SCREEN);
    }
    /**
     * Populates the archive with some initial disciplinary notes for testing purposes.
     */
    private void initializeDummyData() {
        archiveService.addNote(new DisciplinaryNote(1, "S001", "parent1@example.com", "Late submission of homework."));
        archiveService.addNote(new DisciplinaryNote(2, "S002", "parent2@example.com", "Disruptive behavior in class."));
        archiveService.addNote(new DisciplinaryNote(3, "S003", "parent3@example.com", "Missed exam without valid reason."));
    }
    /**
     * Switches the currently displayed screen in the main application frame.
     *
     * @param screenName The name of the screen to display (e.g., LOGIN_SCREEN, REGISTRY_SCREEN).
     */
    public void showScreen(String screenName) {
        cardLayout.show(cardPanel, screenName);
        // Refresh data every time registry screen is shown
        if (screenName.equals(REGISTRY_SCREEN)) {
            registryScreen.refreshNotes();
        }
    }
    /**
     * Shows a specific note's details on the NoteDetailScreen.
     *
     * @param noteId The ID of the disciplinary note to display.
     */
    public void showNoteDetails(int noteId) {
        noteDetailScreen.displayNote(noteId);
        showScreen(NOTE_DETAIL_SCREEN);
    }
    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainApplication app = new MainApplication();
            app.setVisible(true);
        });
    }
}