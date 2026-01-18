'''
Simulates the ETOUR server for managing refreshment points and banners.
This class provides methods to retrieve and delete data, and can simulate
connection interruptions for testing error handling.
'''
package service;
import model.Banner;
import model.RefreshmentPoint;
import exception.ServerConnectionException;
import exception.BannerNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
public class ETOURServer {
    // In-memory lists to simulate database storage
    private List<RefreshmentPoint> refreshmentPoints;
    private List<Banner> banners;
    private volatile boolean isConnectionInterrupted = false; // Added 'volatile' for thread safety.
    /**
     * Constructs an ETOURServer instance and initializes it with dummy data.
     */
    public ETOURServer() {
        initializeDummyData();
    }
    /**
     * Initializes dummy data for refreshment points and banners.
     * This simulates data that would typically come from a backend database.
     */
    private void initializeDummyData() {
        refreshmentPoints = new ArrayList<>();
        banners = new ArrayList<>();
        // Add dummy refreshment points
        refreshmentPoints.add(new RefreshmentPoint(101, "Coffee Corner"));
        refreshmentPoints.add(new RefreshmentPoint(102, "Snack Stop"));
        refreshmentPoints.add(new RefreshmentPoint(103, "Juice Bar"));
        refreshmentPoints.add(new RefreshmentPoint(104, "Watering Hole"));
        // Add dummy banners associated with refreshment points
        banners.add(new Banner(1, 101, "Morning Coffee Deal"));
        banners.add(new Banner(2, 101, "Fresh Pastries - 20% Off"));
        banners.add(new Banner(3, 102, "Crisps & Dips Combo"));
        banners.add(new Banner(4, 103, "Daily Detox Juice"));
        banners.add(new Banner(5, 103, "Energy Boost Smoothie"));
        banners.add(new Banner(6, 101, "Loyalty Card Program"));
        banners.add(new Banner(7, 102, "Weekend Special Snack"));
    }
    /**
     * Checks if the server connection is currently interrupted.
     * If true, throws a ServerConnectionException.
     *
     * @throws ServerConnectionException If the connection simulation is active.
     */
    private void checkConnection() throws ServerConnectionException {
        if (isConnectionInterrupted) {
            throw new ServerConnectionException("Connection to ETOUR server interrupted.");
        }
    }
    /**
     * Retrieves all available refreshment points from the simulated server.
     *
     * @return A list of RefreshmentPoint objects.
     * @throws ServerConnectionException If the connection to the server is interrupted.
     */
    public List<RefreshmentPoint> getRefreshmentPoints() throws ServerConnectionException {
        checkConnection();
        // Simulate network delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new ArrayList<>(refreshmentPoints); // Return a copy to prevent external modification
    }
    /**
     * Retrieves banners associated with a specific refreshment point ID.
     *
     * @param refreshmentPointId The ID of the refreshment point to find banners for.
     * @return A list of Banner objects.
     * @throws ServerConnectionException If the connection to the server is interrupted.
     */
    public List<Banner> getBannersForRefreshmentPoint(int refreshmentPointId) throws ServerConnectionException {
        checkConnection();
        // Simulate network delay
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return banners.stream()
                .filter(banner -> banner.getRefreshmentPointId() == refreshmentPointId)
                .collect(Collectors.toList());
    }
    /**
     * Deletes a banner from the simulated server based on its ID.
     *
     * @param bannerId The ID of the banner to be deleted.
     * @return true if the banner was successfully deleted, false otherwise.
     * @throws ServerConnectionException If the connection to the server is interrupted.
     * @throws BannerNotFoundException   If no banner with the given ID is found.
     */
    public boolean deleteBanner(int bannerId) throws ServerConnectionException, BannerNotFoundException {
        checkConnection();
        // Simulate a longer network delay for a write operation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Iterator<Banner> iterator = banners.iterator();
        while (iterator.hasNext()) {
            Banner banner = iterator.next();
            if (banner.getId() == bannerId) {
                iterator.remove();
                return true; // Banner found and removed
            }
        }
        // If loop completes, banner was not found
        throw new BannerNotFoundException("Banner with ID " + bannerId + " not found.");
    }
    /**
     * Toggles the connection interruption simulation.
     *
     * @param interrupt If true, the server connection will simulate interruption. If false, it will be normal.
     */
    public void simulateConnectionInterruption(boolean interrupt) {
        this.isConnectionInterrupted = interrupt;
    }
}