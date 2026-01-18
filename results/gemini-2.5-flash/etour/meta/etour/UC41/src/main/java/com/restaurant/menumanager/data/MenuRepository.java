package com.restaurant.menumanager.data;

import com.restaurant.menumanager.model.Menu;
import com.restaurant.menumanager.util.MenuSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handles persistence of Menu objects to and from file storage.
 * Each day's menu is stored in a separate JSON file.
 */
public class MenuRepository {
    private static final String DATA_DIRECTORY = "menu_data";
    private final MenuSerializer serializer;

    /**
     * Constructs a MenuRepository with a given MenuSerializer.
     * Ensures the data directory exists.
     */
    public MenuRepository() {
        this.serializer = new MenuSerializer();
        // Ensure the data directory exists
        try {
            Files.createDirectories(Paths.get(DATA_DIRECTORY));
        } catch (IOException e) {
            System.err.println("Error creating data directory: " + e.getMessage());
        }
    }

    /**
     * Saves a Menu object to a file. The filename is derived from the day of the week.
     *
     * @param dayOfWeek The day of the week for which the menu is being saved.
     * @param menu The Menu object to save.
     * @return true if the menu was saved successfully, false otherwise.
     */
    public boolean saveMenu(String dayOfWeek, Menu menu) {
        if (dayOfWeek == null || dayOfWeek.trim().isEmpty() || menu == null) {
            System.err.println("Cannot save menu: dayOfWeek or menu object is null/empty.");
            return false;
        }
        String fileName = getFileName(dayOfWeek);
        Path filePath = Paths.get(DATA_DIRECTORY, fileName);
        String jsonString = serializer.serialize(menu);

        if (jsonString == null) {
            System.err.println("Failed to serialize menu for " + dayOfWeek);
            return false;
        }

        try {
            Files.writeString(filePath, jsonString);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving menu for " + dayOfWeek + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads a Menu object from a file for a given day of the week.
     *
     * @param dayOfWeek The day of the week for which to load the menu.
     * @return The loaded Menu object, or a new empty Menu if the file does not exist or an error occurs.
     */
    public Menu loadMenu(String dayOfWeek) {
        if (dayOfWeek == null || dayOfWeek.trim().isEmpty()) {
            System.err.println("Cannot load menu: dayOfWeek is null/empty.");
            return new Menu(dayOfWeek); // Return an empty menu for the day
        }
        String fileName = getFileName(dayOfWeek);
        Path filePath = Paths.get(DATA_DIRECTORY, fileName);

        if (!Files.exists(filePath)) {
            System.out.println("No existing menu found for " + dayOfWeek + ". Creating a new empty menu.");
            return new Menu(dayOfWeek); // Return an empty menu if file doesn't exist
        }

        try {
            String jsonString = Files.readString(filePath);
            Menu loadedMenu = serializer.deserialize(jsonString);
            if (loadedMenu == null) {
                System.err.println("Failed to deserialize menu from file for " + dayOfWeek + ". Returning empty menu.");
                return new Menu(dayOfWeek);
            }
            return loadedMenu;
        } catch (IOException e) {
            System.err.println("Error loading menu for " + dayOfWeek + ": " + e.getMessage());
            return new Menu(dayOfWeek); // Return an empty menu on error
        }
    }

    /**
     * Returns a list of all available days for which menus are stored.
     * This is determined by the files present in the data directory.
     *
     * @return A list of day names (e.g., "Monday", "Tuesday").
     */
    public List<String> getAvailableDays() {
        Path dataDirPath = Paths.get(DATA_DIRECTORY);
        if (!Files.exists(dataDirPath) || !Files.isDirectory(dataDirPath)) {
            return Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"); // Default if no data yet
        }

        try (Stream<Path> paths = Files.list(dataDirPath)) {
            return paths
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(name -> name.endsWith(".json"))
                    .map(name -> name.substring(0, name.length() - ".json".length()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error listing available menu files: " + e.getMessage());
            return Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"); // Default on error
        }
    }

    /**
     * Helper method to generate the filename for a given day of the week.
     *
     * @param dayOfWeek The day of the week.
     * @return The filename (e.g., "Monday.json").
     */
    private String getFileName(String dayOfWeek) {
        return dayOfWeek.toLowerCase() + ".json";
    }
}