import java.util.*;

/**
 * DailyMenu.java
 * Represents a daily menu with items categorized by type.
 * This class encapsulates the menu data for a specific day of the week.
 */
public class DailyMenu {
    private String dayOfWeek;
    private List<String> appetizers;
    private List<String> mainCourses;
    private List<String> desserts;
    private List<String> beverages;
    private String specialNotes;
    private Date lastModified;
    
    /**
     * Constructs a new DailyMenu for the specified day.
     * 
     * @param dayOfWeek The day of the week for this menu
     */
    public DailyMenu(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        this.appetizers = new ArrayList<>();
        this.mainCourses = new ArrayList<>();
        this.desserts = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.specialNotes = "";
        this.lastModified = new Date();
    }
    
    /**
     * Returns the day of the week for this menu.
     * 
     * @return Day of the week
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }
    
    /**
     * Returns a copy of the appetizers list.
     * 
     * @return List of appetizers
     */
    public List<String> getAppetizers() {
        return new ArrayList<>(appetizers);
    }
    
    /**
     * Returns a copy of the main courses list.
     * 
     * @return List of main courses
     */
    public List<String> getMainCourses() {
        return new ArrayList<>(mainCourses);
    }
    
    /**
     * Returns a copy of the desserts list.
     * 
     * @return List of desserts
     */
    public List<String> getDesserts() {
        return new ArrayList<>(desserts);
    }
    
    /**
     * Returns a copy of the beverages list.
     * 
     * @return List of beverages
     */
    public List<String> getBeverages() {
        return new ArrayList<>(beverages);
    }
    
    /**
     * Returns special notes for this menu.
     * 
     * @return Special notes
     */
    public String getSpecialNotes() {
        return specialNotes;
    }
    
    /**
     * Returns the last modified timestamp.
     * 
     * @return Last modified date
     */
    public Date getLastModified() {
        return lastModified;
    }
    
    /**
     * Sets the appetizers for this menu.
     * Updates the last modified timestamp.
     * 
     * @param appetizers List of appetizers
     */
    public void setAppetizers(List<String> appetizers) {
        this.appetizers = new ArrayList<>(appetizers);
        updateLastModified();
    }
    
    /**
     * Sets the main courses for this menu.
     * Updates the last modified timestamp.
     * 
     * @param mainCourses List of main courses
     */
    public void setMainCourses(List<String> mainCourses) {
        this.mainCourses = new ArrayList<>(mainCourses);
        updateLastModified();
    }
    
    /**
     * Sets the desserts for this menu.
     * Updates the last modified timestamp.
     * 
     * @param desserts List of desserts
     */
    public void setDesserts(List<String> desserts) {
        this.desserts = new ArrayList<>(desserts);
        updateLastModified();
    }
    
    /**
     * Sets the beverages for this menu.
     * Updates the last modified timestamp.
     * 
     * @param beverages List of beverages
     */
    public void setBeverages(List<String> beverages) {
        this.beverages = new ArrayList<>(beverages);
        updateLastModified();
    }
    
    /**
     * Sets special notes for this menu.
     * Updates the last modified timestamp.
     * 
     * @param specialNotes Special notes
     */
    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
        updateLastModified();
    }
    
    /**
     * Returns all menu items from all categories combined.
     * Useful for validation and processing operations.
     * 
     * @return Combined list of all menu items
     */
    public List<String> getAllItems() {
        List<String> allItems = new ArrayList<>();
        allItems.addAll(appetizers);
        allItems.addAll(mainCourses);
        allItems.addAll(desserts);
        allItems.addAll(beverages);
        return allItems;
    }
    
    /**
     * Updates the last modified timestamp to current time.
     * Called whenever menu data is modified.
     */
    private void updateLastModified() {
        this.lastModified = new Date();
    }
    
    /**
     * Returns a string representation of the menu.
     * Formats the menu in a readable display format.
     * 
     * @return Formatted string representation of the menu
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Day: ").append(dayOfWeek).append("\n");
        
        if (!appetizers.isEmpty()) {
            sb.append("Appetizers:\n");
            for (String item : appetizers) {
                sb.append("  - ").append(item).append("\n");
            }
        }
        
        if (!mainCourses.isEmpty()) {
            sb.append("Main Courses:\n");
            for (String item : mainCourses) {
                sb.append("  - ").append(item).append("\n");
            }
        }
        
        if (!desserts.isEmpty()) {
            sb.append("Desserts:\n");
            for (String item : desserts) {
                sb.append("  - ").append(item).append("\n");
            }
        }
        
        if (!beverages.isEmpty()) {
            sb.append("Beverages:\n");
            for (String item : beverages) {
                sb.append("  - ").append(item).append("\n");
            }
        }
        
        if (!specialNotes.isEmpty()) {
            sb.append("Special Notes: ").append(specialNotes).append("\n");
        }
        
        sb.append("Last Modified: ").append(lastModified).append("\n");
        
        return sb.toString();
    }
}