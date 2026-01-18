'''
@file FavoritesViewer.java
@brief Displays the list of bookmarks (favorites) for the Tourist.
@details Handles server disconnection with a retry option using a background thread to keep GUI responsive.
@version 1.0
@date 2023-10-15
'''
package project;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.SwingWorker;  // Import added for SwingWorker class
public class FavoritesViewer extends JFrame {
    private String username;
    private ETOURServerMock server;
    private DefaultListModel<String> listModel;
    private JList<String> favoritesList;
    /**
     * Constructor to initialize the favorites viewer.
     * @param username The authenticated user
     * @param server The server mock instance
     */
    public FavoritesViewer(String username, ETOURServerMock server) {
        super("My Favorites - " + username);
        this.username = username;
        this.server = server;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());
        // List model and JList for displaying bookmarks
        listModel = new DefaultListModel<>();
        favoritesList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(favoritesList);
        add(scrollPane, BorderLayout.CENTER);
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadBookmarks());
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
        // Initial load of bookmarks
        loadBookmarks();
    }
    /**
     * Loads bookmarks from server; handles disconnection with retry option.
     * Uses SwingWorker for reconnection to avoid blocking the EDT.
     */
    private void loadBookmarks() {
        listModel.clear(); // Clear previous entries
        try {
            List<String> bookmarks = server.fetchBookmarks(username);
            if (bookmarks.isEmpty()) {
                listModel.addElement("You have no bookmarks yet.");
            } else {
                for (String bm : bookmarks) {
                    listModel.addElement(bm);
                }
            }
            // Update title to indicate successful connection
            setTitle("My Favorites - " + username + " (Connected)");
        } catch (ETOURServerMock.ServerDisconnectedException e) {
            listModel.addElement("Server Error: " + e.getMessage());
            listModel.addElement("Please try again.");
            setTitle("My Favorites - " + username + " (Disconnected)");
            // Offer retry option to the user
            int retry = JOptionPane.showConfirmDialog(this,
                    "Connection lost. Reconnect?",
                    "ETOUR Server",
                    JOptionPane.YES_NO_OPTION);
            if (retry == JOptionPane.YES_OPTION) {
                // Use SwingWorker to perform reconnect in background, then reload bookmarks on EDT
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        server.reconnect(); // This includes Thread.sleep, but runs in background
                        return null;
                    }
                    @Override
                    protected void done() {
                        // Invoke loadBookmarks on EDT after reconnect completes
                        loadBookmarks();
                    }
                }.execute();
                listModel.addElement("Reconnecting...");
            }
        }
    }
}