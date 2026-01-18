package com.restaurant.menumanager;

import com.restaurant.menumanager.data.MenuRepository;
import com.restaurant.menumanager.model.Menu;
import com.restaurant.menumanager.model.MenuItem;
import com.restaurant.menumanager.service.MenuService;
import com.restaurant.menumanager.util.InputValidator;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the ModifyMenu program.
 * This class handles user interaction, displays menus, and orchestrates
 * the modification process by interacting with the MenuService.
 */
public class Main {
    private final MenuService menuService;
    private final InputValidator inputValidator;
    private final Scanner scanner;

    /**
     * Constructor for Main class.
     * Initializes MenuService, InputValidator, and Scanner.
     */
    public Main() {
        this.scanner = new Scanner(System.in);
        MenuRepository menuRepository = new MenuRepository();
        this.menuService = new MenuService(menuRepository);
        this.inputValidator = new InputValidator(scanner);
        // Ensure initial menu files exist for all days
        this.menuService.initializeDailyMenus();
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    /**
     * Runs the main application loop, presenting menu options to the operator.
     */
    public void run() {
        System.out.println("Welcome to the Restaurant Menu Manager!");
        System.out.println("Operator authenticated successfully."); // As per PRD entry condition assumption

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = inputValidator.getValidIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    modifyMenu();
                    break;
                case 2:
                    viewAllMenus();
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting Menu Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine(); // Consume newline and wait for user to press Enter
        }
        scanner.close();
    }

    /**
     * Displays the main menu options to the user.
     */
    private void displayMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Modify Daily Menu");
        System.out.println("2. View All Daily Menus");
        System.out.println("3. Exit");
        System.out.print("-----------------\n");
    }

    /**
     * Guides the operator through the process of modifying a daily menu.
     * This involves selecting a day, displaying its current menu,
     * allowing edits (add, edit, remove items), and saving changes.
     */
    private void modifyMenu() {
        System.out.println("\n--- Modify Daily Menu ---");
        List<String> days = menuService.getAllDaysOfWeek();
        System.out.println("Available days: " + String.join(", ", days));

        String selectedDay = inputValidator.getValidDayOfWeekInput("Enter the day of the week to modify: ");
        Menu currentMenu = menuService.getMenuForDay(selectedDay);

        System.out.println("\n" + currentMenu); // Display current menu

        boolean editing = true;
        while (editing) {
            displayEditingMenuOptions();
            int choice = inputValidator.getValidIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addItemToMenu(currentMenu);
                    break;
                case 2:
                    editMenuItem(currentMenu);
                    break;
                case 3:
                    removeItemFromMenu(currentMenu);
                    break;
                case 4:
                    editing = false; // Exit editing loop
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("\n" + currentMenu); // Display menu after each edit
        }

        // Step 6: Verify data and ask for confirmation
        System.out.println("Review your changes for " + selectedDay + ":\n" + currentMenu);
        String confirmation = inputValidator.getValidStringInput("Confirm changes? (yes/no): ");

        if (confirmation.equalsIgnoreCase("yes")) {
            // Step 8: Saves changes
            if (menuService.updateMenu(selectedDay, currentMenu)) {
                System.out.println("Menu for " + selectedDay + " successfully updated!");
            } else {
                System.err.println("Failed to save menu changes for " + selectedDay + ".");
            }
        } else {
            System.out.println("Menu modification cancelled for " + selectedDay + ".");
        }
    }

    /**
     * Displays options available during menu editing.
     */
    private void displayEditingMenuOptions() {
        System.out.println("\n--- Edit Menu Options ---");
        System.out.println("1. Add New Item");
        System.out.println("2. Edit Existing Item");
        System.out.println("3. Remove Item");
        System.out.println("4. Finish Editing and Review");
        System.out.print("-------------------------\n");
    }

    /**
     * Adds a new menu item to the given menu.
     *
     * @param menu The Menu object to add the item to.
     */
    private void addItemToMenu(Menu menu) {
        System.out.println("\n--- Add New Menu Item ---");
        String name = inputValidator.getValidStringInput("Enter item name: ");
        if (!inputValidator.isValidMenuItemName(name)) {
            System.out.println("Invalid item name. Must be at least 2 characters.");
            return;
        }
        double price = inputValidator.getValidDoubleInput("Enter item price: ");
        String description = inputValidator.getValidStringInput("Enter item description: ");

        MenuItem newItem = new MenuItem(name, price, description);
        menu.addItem(newItem);
        System.out.println("Item '" + name + "' added to the menu.");
    }

    /**
     * Edits an existing menu item in the given menu.
     *
     * @param menu The Menu object containing the item to edit.
     */
    private void editMenuItem(Menu menu) {
        if (menu.getItems().isEmpty()) {
            System.out.println("No items to edit in this menu.");
            return;
        }

        System.out.println("\n--- Edit Existing Menu Item ---");
        System.out.println(menu); // Display menu with numbered items
        int itemIndex = inputValidator.getValidIntegerInput("Enter the number of the item to edit: ");

        if (itemIndex > 0 && itemIndex <= menu.getItems().size()) {
            MenuItem itemToEdit = menu.getItems().get(itemIndex - 1);
            System.out.println("Editing: " + itemToEdit);

            String newName = inputValidator.getValidStringInput("Enter new name (current: " + itemToEdit.getName() + ", leave blank to keep): ");
            if (!newName.isEmpty()) {
                if (inputValidator.isValidMenuItemName(newName)) {
                    itemToEdit.setName(newName);
                } else {
                    System.out.println("Invalid new name. Keeping original name.");
                }
            }

            String newPriceStr = inputValidator.getValidStringInput("Enter new price (current: " + itemToEdit.getPrice() + ", leave blank to keep): ");
            if (!newPriceStr.isEmpty()) {
                if (inputValidator.isValidMenuItemPrice(newPriceStr)) {
                    itemToEdit.setPrice(Double.parseDouble(newPriceStr));
                } else {
                    System.out.println("Invalid new price. Keeping original price.");
                }
            }

            String newDescription = inputValidator.getValidStringInput("Enter new description (current: " + itemToEdit.getDescription() + ", leave blank to keep): ");
            if (!newDescription.isEmpty()) {
                itemToEdit.setDescription(newDescription);
            }

            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Invalid item number.");
        }
    }

    /**
     * Removes a menu item from the given menu.
     *
     * @param menu The Menu object to remove the item from.
     */
    private void removeItemFromMenu(Menu menu) {
        if (menu.getItems().isEmpty()) {
            System.out.println("No items to remove from this menu.");
            return;
        }

        System.out.println("\n--- Remove Menu Item ---");
        System.out.println(menu); // Display menu with numbered items
        int itemIndex = inputValidator.getValidIntegerInput("Enter the number of the item to remove: ");

        if (itemIndex > 0 && itemIndex <= menu.getItems().size()) {
            MenuItem removedItem = menu.getItems().remove(itemIndex - 1);
            System.out.println("Item '" + removedItem.getName() + "' removed from the menu.");
        } else {
            System.out.println("Invalid item number.");
        }
    }

    /**
     * Displays all daily menus.
     */
    private void viewAllMenus() {
        System.out.println("\n--- All Daily Menus ---");
        List<String> days = menuService.getAllDaysOfWeek();
        for (String day : days) {
            Menu menu = menuService.getMenuForDay(day);
            System.out.println(menu);
        }
        System.out.println("-----------------------");
    }
}