/**
 * MenuManager: Manages the weekly menu data and server connections.
 */
import java.util.*;
import java.io.*;
public class MenuManager {
    private HashMap<String, DayMenu> weeklyMenu;
    private static final String MENU_FILE = "weekly_menu.dat";
    /**
     * Constructor: initializes the weekly menu and loads saved data.
     */
    public MenuManager() {
        weeklyMenu = new HashMap<>();
        initializeWeeklyMenu();
        loadFromFile();
    }
    /**
     * Initializes the weekly menu with empty DayMenu objects for each day.
     */
    private void initializeWeeklyMenu() {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days) {
            weeklyMenu.put(day, new DayMenu(day));
        }
    }
    /**
     * Simulates a server connection check.
     * @return true if connection successful, false otherwise
     */
    public boolean checkServerConnection() {
        try {
            // Simulate server connection check with delay
            Thread.sleep(1000);
            // Simulate connection success with 80% probability
            boolean connected = Math.random() > 0.2;
            if (!connected) {
                throw new RuntimeException("Server connection interrupted");
            }
            return connected;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Connection check interrupted: " + e.getMessage());
        }
    }
    /**
     * Retrieves the menu for a specific day.
     * @param dayName the day to retrieve
     * @return DayMenu object for the specified day
     * @throws RuntimeException if day not found
     */
    public DayMenu getDayMenu(String dayName) {
        DayMenu menu = weeklyMenu.get(dayName);
        if (menu == null) {
            throw new RuntimeException("Menu for day '" + dayName + "' not found");
        }
        return menu;
    }
    /**
     * Updates the menu for a specific day.
     * @param dayName the day to update
     * @param dishes new list of dishes
     * @throws RuntimeException if update fails
     */
    public void updateDayMenu(String dayName, List<String> dishes) {
        if (!weeklyMenu.containsKey(dayName)) {
            throw new RuntimeException("Invalid day: " + dayName);
        }
        try {
            checkServerConnection();
            DayMenu dayMenu = weeklyMenu.get(dayName);
            dayMenu.setDishes(dishes);
            saveToFile();
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to update menu: " + e.getMessage());
        }
    }
    /**
     * Saves the weekly menu to a file for persistence.
     * @throws RuntimeException if save fails
     */
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MENU_FILE))) {
            oos.writeObject(weeklyMenu);
        } catch (IOException e) {
            throw new RuntimeException("Error saving menu data: " + e.getMessage());
        }
    }
    /**
     * Loads the weekly menu from a file.
     */
    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(MENU_FILE);
        if (!file.exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MENU_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof HashMap) {
                HashMap<String, DayMenu> loadedMenu = (HashMap<String, DayMenu>) obj;
                for (String day : weeklyMenu.keySet()) {
                    if (loadedMenu.containsKey(day)) {
                        weeklyMenu.put(day, loadedMenu.get(day));
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading menu data: " + e.getMessage());
        }
    }
    /**
     * Returns the entire weekly menu.
     * @return map of days to DayMenu objects
     */
    public HashMap<String, DayMenu> getWeeklyMenu() {
        return new HashMap<>(weeklyMenu);
    }
}