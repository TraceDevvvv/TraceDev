'''
Main entry point for the SearchRefreshmentPoint application.
This class sets up the GUI and connects the search functionality.
It simulates a system that returns points of rest (refreshment points)
based on user-provided search criteria (e.g., name, rating, distance).
The GUI is built using Java Swing.
Includes nested classes RefreshmentPoint, SearchService, and ServerConnectionException
to ensure a single, runnable Java file as required.
'''
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
public class SearchRefreshmentPointApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Search Refreshment Point");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // --- Search Form Panel ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(20);
        formPanel.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Minimum Rating (0-5):"), gbc);
        gbc.gridx = 1;
        JSpinner ratingSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 5.0, 0.5));
        formPanel.add(ratingSpinner, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Max Distance (km):"), gbc);
        gbc.gridx = 1;
        JSpinner distanceSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1000.0, 1.0));
        formPanel.add(distanceSpinner, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton searchButton = new JButton("Search Refreshment Points");
        formPanel.add(searchButton, gbc);
        mainPanel.add(formPanel, BorderLayout.NORTH);
        // --- Results Display ---
        JTextArea resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // --- Status Label ---
        JLabel statusLabel = new JLabel("Ready to search.");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(statusLabel, BorderLayout.SOUTH);
        // --- Search Action ---
        searchButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            double minRating = (Double) ratingSpinner.getValue();
            double maxDistance = (Double) distanceSpinner.getValue();
            if (minRating < 0 || minRating > 5) {
                JOptionPane.showMessageDialog(frame, "Rating must be between 0 and 5.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (maxDistance < 0) {
                JOptionPane.showMessageDialog(frame, "Distance must be non-negative.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            statusLabel.setText("Searching... Please wait.");
            searchButton.setEnabled(false);
            SwingWorker<List<RefreshmentPoint>, Void> worker = new SwingWorker<>() {
                @Override
                protected List<RefreshmentPoint> doInBackground() throws Exception {
                    SearchService searchService = new SearchService();
                    try {
                        return searchService.searchRefreshmentPoints(name, minRating, maxDistance);
                    } catch (ServerConnectionException ex) {
                        throw ex;
                    }
                }
                @Override
                protected void done() {
                    searchButton.setEnabled(true);
                    try {
                        List<RefreshmentPoint> results = get();
                        if (results.isEmpty()) {
                            resultsArea.setText("No refreshment points found matching the criteria.");
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Found ").append(results.size()).append(" refreshment point(s):\n\n");
                            for (RefreshmentPoint rp : results) {
                                sb.append("Name: ").append(rp.getName())
                                  .append(" | Rating: ").append(String.format("%.1f", rp.getRating()))
                                  .append(" | Distance: ").append(String.format("%.1f", rp.getDistance())).append(" km\n");
                            }
                            resultsArea.setText(sb.toString());
                        }
                        statusLabel.setText("Search completed successfully.");
                    } catch (InterruptedException | ExecutionException ex) {
                        Throwable cause = ex.getCause();
                        if (cause instanceof ServerConnectionException) {
                            resultsArea.setText("Error: Connection to server ETOUR interrupted. Please try again later.");
                        } else {
                            resultsArea.setText("Error during search: " + cause.getMessage());
                        }
                        statusLabel.setText("Search failed.");
                    }
                }
            };
            worker.execute();
        });
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    /**
     * Represents a refreshment point (point of rest) in the system.
     */
    public static class RefreshmentPoint {
        private String name;
        private double rating;
        private double distance;
        public RefreshmentPoint(String name, double rating, double distance) {
            this.name = name;
            this.rating = rating;
            this.distance = distance;
        }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public double getRating() { return rating; }
        public void setRating(double rating) { this.rating = rating; }
        public double getDistance() { return distance; }
        public void setDistance(double distance) { this.distance = distance; }
        @Override
        public String toString() {
            return "RefreshmentPoint{name='" + name + "', rating=" + rating + ", distance=" + distance + "}";
        }
    }
    /**
     * Service class that simulates the server-side search functionality.
     * Includes simulation of server connection interruption.
     */
    public static class SearchService {
        private List<RefreshmentPoint> mockDatabase;
        public SearchService() {
            initializeMockDatabase();
        }
        private void initializeMockDatabase() {
            mockDatabase = new ArrayList<>();
            mockDatabase.add(new RefreshmentPoint("Cafe Central", 4.5, 2.3));
            mockDatabase.add(new RefreshmentPoint("Rest Stop Alpha", 3.8, 5.7));
            mockDatabase.add(new RefreshmentPoint("Mountain View Diner", 4.9, 12.4));
            mockDatabase.add(new RefreshmentPoint("Quick Bite", 2.5, 1.1));
            mockDatabase.add(new RefreshmentPoint("Lakeside Lounge", 4.2, 8.9));
            mockDatabase.add(new RefreshmentPoint("Highway Oasis", 3.5, 15.0));
            mockDatabase.add(new RefreshmentPoint("Green Valley Rest Area", 4.7, 25.3));
            mockDatabase.add(new RefreshmentPoint("City Plaza Cafe", 4.0, 0.5));
        }
        /**
         * Searches refreshment points based on the given criteria.
         * Simulates server processing time (max 15 seconds) and random connection interruption.
         */
        public List<RefreshmentPoint> searchRefreshmentPoints(String name, double minRating, double maxDistance)
                throws ServerConnectionException {
            if (minRating < 0 || minRating > 5) {
                throw new IllegalArgumentException("Rating must be between 0 and 5.");
            }
            if (maxDistance < 0) {
                throw new IllegalArgumentException("Distance must be non-negative.");
            }
            // Simulate processing delay (under 15 seconds)
            try {
                Random rand = new Random();
                int delay = rand.nextInt(3000);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new ServerConnectionException("Search interrupted.");
            }
            // Simulate random server connection interruption (10% chance)
            Random rand = new Random();
            if (rand.nextInt(100) < 10) {
                throw new ServerConnectionException("Connection to server ETOUR was interrupted.");
            }
            List<RefreshmentPoint> results = new ArrayList<>();
            for (RefreshmentPoint rp : mockDatabase) {
                boolean nameMatch = name.isEmpty() || rp.getName().toLowerCase().contains(name.toLowerCase());
                boolean ratingMatch = rp.getRating() >= minRating;
                boolean distanceMatch = rp.getDistance() <= maxDistance;
                if (nameMatch && ratingMatch && distanceMatch) {
                    results.add(rp);
                }
            }
            return results;
        }
    }
    /**
     * Custom exception for interruption of connection to the server ETOUR.
     */
    public static class ServerConnectionException extends Exception {
        public ServerConnectionException(String message) {
            super(message);
        }
        public ServerConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}