import java.io.*;
import java.util.*;

/**
 * MenuManager.java
 * Manages menu data storage and retrieval.
 * This class handles operations for getting, saving, and initializing daily menus.
 * Includes error handling for server interruptions (ETOUR).
 */
public class MenuManager {
    // In-memory storage for demonstration purposes
    // In production, this would be backed by a database or file system
    private Map<String, DailyMenu> menuStorage;
    
    /**
     * Constructs a new MenuManager and initializes with sample data.
     */
    public MenuManager() {
        menuStorage = new HashMap<>();
        initializeSampleData();
    }
    
    /**
     * Retrieves the menu for a specific day of the week.
     * 
     * @param dayOfWeek The day of the week (e.g., "Monday", "Tuesday")
     * @return DailyMenu object for the specified day, or null if not found
     */
    public DailyMenu getMenuForDay(String dayOfWeek) {
        if (dayOfWeek == null || dayOfWeek.trim().isEmpty()) {
            return null;
        }
        return menuStorage.get(dayOfWeek.toLowerCase());
    }
    
    /**
     * Saves or updates a menu for a specific day.
     * Implements error handling for server interruptions (ETOUR).
     * 
     * @param menu The DailyMenu object to save
     * @param operator The RestaurantOperator performing the save operation
     * @return true if save successful, false if an error occurred
     * @throws IllegalArgumentException if menu or operator is null
     */
    public boolean saveMenu(DailyMenu menu, RestaurantOperator operator) {
        // Input validation
        if (menu == null) {
            throw new IllegalArgumentException("Menu cannot be null");
        }
        if (operator == null) {
            throw new IllegalArgumentException("Operator cannot be null");
        }
        
        try {
            // Simulate potential server interruption (ETOUR error condition from use case)
            // 3% chance to simulate server interruption for testing purposes
            if (Math.random() < 0.03) {
                throw new IOException("Server connection interrupted (ETOUR)");
            }
            
            // Save the menu to storage
            menuStorage.put(menu.getDayOfWeek().toLowerCase(), menu);
            
            // Log the successful operation
            System.out.println("Menu saved successfully by operator: " + operator.getName() + 
                             " (" + operator.getOperatorId() + ") at " + new Date());
            System.out.println("Menu for " + menu.getDayOfWeek() + " has been updated.");
            
            return true;
            
        } catch (IOException e) {
            // Handle server interruption (ETOUR error)
            System.out.println("ERROR: Server connection interrupted (ETOUR): " + e.getMessage());
            System.out.println("Menu changes were not saved. Please try again.");
            return false;
            
        } catch (Exception e) {
            // Handle other unexpected errors
            System.out.println("ERROR: Unexpected error occurred while saving menu: " + e.getMessage());
            System.out.println("Menu changes were not saved. Please contact system administrator.");
            return false;
        }
    }
    
    /**
     * Checks if a menu exists for a specific day.
     * 
     * @param dayOfWeek The day of the week to check
     * @return true if menu exists, false otherwise
     */
    public boolean menuExistsForDay(String dayOfWeek) {
        if (dayOfWeek == null || dayOfWeek.trim().isEmpty()) {
            return false;
        }
        return menuStorage.containsKey(dayOfWeek.toLowerCase());
    }
    
    /**
     * Returns all days for which menus exist.
     * 
     * @return Set of days with existing menus
     */
    public Set<String> getDaysWithMenus() {
        Set<String> days = new TreeSet<>();
        for (String day : menuStorage.keySet()) {
            // Capitalize first letter for display
            days.add(Character.toUpperCase(day.charAt(0)) + day.substring(1));
        }
        return days;
    }
    
    /**
     * Returns the total number of menus stored.
     * 
     * @return Number of menus in storage
     */
    public int getMenuCount() {
        return menuStorage.size();
    }
    
    /**
     * Deletes a menu for a specific day.
     * 
     * @param dayOfWeek The day of the week to delete
     * @param operator The RestaurantOperator performing the delete operation
     * @return true if deleted successfully, false if menu not found or error occurred
     */
    public boolean deleteMenuForDay(String dayOfWeek, RestaurantOperator operator) {
        if (dayOfWeek == null || dayOfWeek.trim().isEmpty()) {
            return false;
        }
        
        String dayKey = dayOfWeek.toLowerCase();
        if (!menuStorage.containsKey(dayKey)) {
            System.out.println("No menu found for " + dayOfWeek + " to delete.");
            return false;
        }
        
        try {
            // Simulate potential server interruption
            if (Math.random() < 0.03) {
                throw new IOException("Server connection interrupted (ETOUR) during delete operation");
            }
            
            menuStorage.remove(dayKey);
            System.out.println("Menu for " + dayOfWeek + " has been deleted by operator: " + 
                             operator.getName() + " (" + operator.getOperatorId() + ")");
            return true;
            
        } catch (IOException e) {
            System.out.println("ERROR: Server connection interrupted (ETOUR): " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Returns a copy of all menus for backup or display purposes.
     * 
     * @return Map containing all menus (day -> DailyMenu)
     */
    public Map<String, DailyMenu> getAllMenus() {
        // Return a defensive copy to prevent external modification
        Map<String, DailyMenu> copy = new HashMap<>();
        for (Map.Entry<String, DailyMenu> entry : menuStorage.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }
    
    /**
     * Initializes sample menu data for demonstration.
     * In production, this would load from a database or configuration file.
     */
    private void initializeSampleData() {
        System.out.println("Initializing sample menu data...");
        
        // Initialize menus for all seven days of the week
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        
        // Sample menu templates for different days
        for (int i = 0; i < days.length; i++) {
            DailyMenu menu = new DailyMenu(days[i]);
            
            // Different menu themes for different days
            switch (i) {
                case 0: // Monday
                    menu.setAppetizers(Arrays.asList("Soup of the Day", "House Salad", "Garlic Bread"));
                    menu.setMainCourses(Arrays.asList("Grilled Chicken with Vegetables", "Vegetable Pasta Primavera"));
                    menu.setDesserts(Arrays.asList("Chocolate Cake", "Fresh Fruit Plate"));
                    menu.setBeverages(Arrays.asList("Coffee", "Iced Tea", "Lemonade"));
                    menu.setSpecialNotes("Gluten-free options available upon request");
                    break;
                    
                case 1: // Tuesday
                    menu.setAppetizers(Arrays.asList("Caesar Salad", "Bruschetta"));
                    menu.setMainCourses(Arrays.asList("Beef Steak with Mashed Potatoes", "Grilled Salmon Fillet"));
                    menu.setDesserts(Arrays.asList("Tiramisu", "Vanilla Ice Cream"));
                    menu.setBeverages(Arrays.asList("Red Wine", "White Wine", "Sparkling Water"));
                    break;
                    
                case 2: // Wednesday - Italian Theme
                    menu.setAppetizers(Arrays.asList("Caprese Salad", "Antipasto Platter"));
                    menu.setMainCourses(Arrays.asList("Spaghetti Carbonara", "Eggplant Parmesan"));
                    menu.setDesserts(Arrays.asList("Cannoli", "Panna Cotta"));
                    menu.setBeverages(Arrays.asList("Chianti Wine", "Espresso", "San Pellegrino"));
                    menu.setSpecialNotes("Wednesday Italian Special - All pasta made fresh daily");
                    break;
                    
                case 3: // Thursday - Seafood Theme
                    menu.setAppetizers(Arrays.asList("Shrimp Cocktail", "Clam Chowder"));
                    menu.setMainCourses(Arrays.asList("Lobster Thermidor", "Grilled Sea Bass"));
                    menu.setDesserts(Arrays.asList("Key Lime Pie", "Bread Pudding"));
                    menu.setBeverages(Arrays.asList("Sauvignon Blanc", "Chardonnay", "Mineral Water"));
                    break;
                    
                case 4: // Friday
                    menu.setAppetizers(Arrays.asList("Fried Calamari", "Spinach Artichoke Dip"));
                    menu.setMainCourses(Arrays.asList("Filet Mignon", "Chicken Marsala"));
                    menu.setDesserts(Arrays.asList("Cheesecake", "Chocolate Mousse"));
                    menu.setBeverages(Arrays.asList("Cabernet Sauvignon", "Pinot Noir", "Craft Beer"));
                    break;
                    
                case 5: // Saturday - Weekend Brunch
                    menu.setAppetizers(Arrays.asList("Fresh Fruit Platter", "Yogurt Parfait"));
                    menu.setMainCourses(Arrays.asList("Eggs Benedict", "Steak and Eggs", "Belgian Waffles"));
                    menu.setDesserts(Arrays.asList("French Toast", "Cinnamon Rolls"));
                    menu.setBeverages(Arrays.asList("Mimosa", "Bloody Mary", "Fresh Orange Juice"));
                    menu.setSpecialNotes("Saturday Brunch Special - 10am to 2pm only");
                    break;
                    
                case 6: // Sunday - Family Dinner
                    menu.setAppetizers(Arrays.asList("French Onion Soup", "Stuffed Mushrooms"));
                    menu.setMainCourses(Arrays.asList("Roast Turkey with Gravy", "Pot Roast", "Baked Ham"));
                    menu.setDesserts(Arrays.asList("Apple Pie", "Pumpkin Pie"));
                    menu.setBeverages(Arrays.asList("Merlot", "Riesling", "Apple Cider"));
                    menu.setSpecialNotes("Sunday Family Dinner - All you can eat");
                    break;
            }
            
            menuStorage.put(days[i].toLowerCase(), menu);
        }
        
        System.out.println("Sample menu data initialized for 7 days.");
    }
    
    /**
     * Clears all menu data (for testing/reset purposes).
     * 
     * @return true if cleared successfully
     */
    public boolean clearAllMenus() {
        try {
            menuStorage.clear();
            System.out.println("All menus have been cleared.");
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: Failed to clear menus: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Returns a string representation of all menus for display.
     * 
     * @return Formatted string containing all menus
     */
    public String getAllMenusAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ALL STORED MENUS ===\n");
        sb.append("Total menus: ").append(menuStorage.size()).append("\n\n");
        
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        
        for (String day : days) {
            DailyMenu menu = menuStorage.get(day.toLowerCase());
            if (menu != null) {
                sb.append(menu.toString()).append("\n");
            } else {
                sb.append("Day: ").append(day).append("\n");
                sb.append("No menu data available\n\n");
            }
        }
        
        return sb.toString();
    }
}