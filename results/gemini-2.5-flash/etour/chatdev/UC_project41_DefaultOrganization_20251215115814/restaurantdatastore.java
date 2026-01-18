/*
 * Represents the data store for restaurant menus.
 * In a real application, this would interact with a database or persistent storage.
 * For this example, it uses an in-memory HashMap to store menu content for each day,
 * simulating data retrieval and saving. It also includes a simulated failure mechanism
 * and simulated network/disk latency to demonstrate SwingWorker necessity.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class RestaurantDataStore {
    // Simulates a map where keys are days of the week and values are menu strings.
    private Map<String, String> dailyMenusData;
    private Random random; // For simulating connection interruptions and delays.
    // Array of standard days of the week.
    public static final String[] DAYS_OF_WEEK = {
        "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"
    };
    /**
     * Constructor for RestaurantDataStore.
     * Initializes the dailyMenusData with some default or empty menus.
     */
    public RestaurantDataStore() {
        dailyMenusData = new HashMap<>();
        random = new Random();
        // Initialize with some dummy data for demonstration.
        for (String day : DAYS_OF_WEEK) {
            dailyMenusData.put(day, "Menu for " + day + ":\n" +
                                     "- Main Course: " + day.toLowerCase() + " special\n" +
                                     "- Dessert: Seasonal Fruit\n" +
                                     "- Drink: Water");
        }
    }
    /**
     * Loads the menu content for a given day of the week.
     * This method fulfills part of step 4 of the use case ("Upload the data menu for the selected day and loads them into a form").
     * Simulates network/disk latency.
     * @param day The day of the week (e.g., "MONDAY").
     * @return The menu content as a String. Returns an empty string if no menu is found.
     */
    public String loadMenu(String day) {
        // Simulate a delay for data retrieval to demonstrate asynchronous loading.
        long delay = 200 + random.nextInt(800); // Simulate 200-1000ms delay.
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status.
            System.err.println("Load menu operation interrupted for " + day);
        }
        System.out.println("DEBUG: Loading menu for " + day + " from data store (simulated delay: " + delay + "ms).");
        // In a real scenario, this would be a database query or file read.
        return dailyMenusData.getOrDefault(day, "");
    }
    /**
     * Saves the menu content for a given day of the week.
     * Includes a simulated connection interruption or server error and latency.
     * @param day The day of the week.
     * @param menuContent The new menu content to save.
     * @return true if the save was successful, false if a simulated error occurred.
     */
    public boolean saveMenu(String day, String menuContent) {
        // Simulate a delay for data persistence to demonstrate asynchronous saving.
        long delay = 200 + random.nextInt(800); // Simulate 200-1000ms delay.
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status.
            System.err.println("Save menu operation interrupted for " + day);
            return false;
        }
        // Simulate a connection/server interruption (ETOUR exit condition).
        // Roughly 10% chance of failure for demonstration purposes.
        if (random.nextInt(10) == 0) {
            System.err.println("Simulated server connection interruption for " + day);
            return false; // Simulate failure.
        }
        // In a real scenario, this would be a database update or file write.
        dailyMenusData.put(day, menuContent);
        System.out.println("Menu for " + day + " successfully saved (simulated delay: " + delay + "ms).");
        return true; // Simulate success.
    }
}