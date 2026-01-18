/**
 * DayMenu: Represents a day's menu in the restaurant. Implements Serializable to allow saving and loading.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class DayMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    private String dayName;
    private List<String> dishes;
    /**
     * Constructor: creates a DayMenu for a specific day.
     * @param dayName the name of the day (e.g., "Monday")
     */
    public DayMenu(String dayName) {
        this.dayName = dayName;
        this.dishes = new ArrayList<>();
    }
    /**
     * Constructor: creates a DayMenu with initial dishes.
     * @param dayName the name of the day
     * @param initialDishes list of initial dishes
     */
    public DayMenu(String dayName, List<String> initialDishes) {
        this.dayName = dayName;
        this.dishes = new ArrayList<>();
        if (initialDishes != null) {
            for (String dish : initialDishes) {
                if (isValidDishName(dish)) {
                    this.dishes.add(dish.trim());
                }
            }
        }
    }
    /**
     * Adds a dish to the menu with validation.
     * @param dish the dish name to add
     * @return true if dish was added successfully
     */
    public boolean addDish(String dish) {
        if (isValidDishName(dish)) {
            dishes.add(dish.trim());
            return true;
        }
        return false;
    }
    /**
     * Validates a dish name.
     * @param dish the dish name to validate
     * @return true if valid
     */
    private boolean isValidDishName(String dish) {
        if (dish == null || dish.trim().isEmpty()) {
            return false;
        }
        String trimmed = dish.trim();
        return trimmed.length() >= 2 && trimmed.length() <= 100;
    }
    /**
     * Returns the day name.
     */
    public String getDayName() {
        return dayName;
    }
    /**
     * Sets the day name.
     */
    public void setDayName(String dayName) {
        this.dayName = dayName;
    }
    /**
     * Returns the list of dishes.
     */
    public List<String> getDishes() {
        return new ArrayList<>(dishes);
    }
    /**
     * Sets the dishes list with validation.
     */
    public void setDishes(List<String> dishes) {
        this.dishes.clear();
        if (dishes != null) {
            for (String dish : dishes) {
                if (isValidDishName(dish)) {
                    this.dishes.add(dish.trim());
                }
            }
        }
    }
    /**
     * Returns a string representation of the day menu.
     */
    @Override
    public String toString() {
        return "DayMenu{" +
               "dayName='" + dayName + '\'' +
               ", dishes=" + dishes +
               '}';
    }
}