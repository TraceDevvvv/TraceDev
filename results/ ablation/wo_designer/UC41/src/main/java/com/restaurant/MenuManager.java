package com.restaurant;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Manages the weekly menu and provides functionality to edit daily menus.
 */
public class MenuManager {
    private Map<DailyMenu.DayOfWeek, DailyMenu> weeklyMenu;
    private Scanner scanner;

    public MenuManager() {
        weeklyMenu = new HashMap<>();
        scanner = new Scanner(System.in);
        initializeWeeklyMenu();
    }

    /**
     * Initializes the weekly menu with empty daily menus for each day.
     */
    private void initializeWeeklyMenu() {
        for (DailyMenu.DayOfWeek day : DailyMenu.DayOfWeek.values()) {
            weeklyMenu.put(day, new DailyMenu(day));
        }
    }

    /**
     * Displays the form with seven days of the week.
     */
    public void displayWeekForm() {
        System.out.println("=== Weekly Menu Editor ===");
        System.out.println("Select a day to edit its menu:");
        int index = 1;
        for (DailyMenu.DayOfWeek day : DailyMenu.DayOfWeek.values()) {
            System.out.println(index + ". " + day);
            index++;
        }
        System.out.println("0. Exit");
    }

    /**
     * Allows the operator to select a day.
     * @return The selected DayOfWeek, or null if exit.
     */
    public DailyMenu.DayOfWeek selectDay() {
        System.out.print("Enter day number: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                return null;
            }
            DailyMenu.DayOfWeek[] days = DailyMenu.DayOfWeek.values();
            if (choice >= 1 && choice <= days.length) {
                return days[choice - 1];
            } else {
                System.out.println("Invalid selection. Please try again.");
                return selectDay();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectDay();
        }
    }

    /**
     * Uploads and loads the menu data for the selected day into a form for editing.
     * @param day The day to edit.
     */
    public void loadAndEditMenu(DailyMenu.DayOfWeek day) {
        DailyMenu dailyMenu = weeklyMenu.get(day);
        System.out.println("\nEditing menu for " + day);
        System.out.println("Current items:");
        if (dailyMenu.getItems().isEmpty()) {
            System.out.println("No items.");
        } else {
            for (int i = 0; i < dailyMenu.getItems().size(); i++) {
                System.out.println((i + 1) + ". " + dailyMenu.getItems().get(i));
            }
        }

        System.out.println("\nOptions:");
        System.out.println("1. Add item");
        System.out.println("2. Remove item");
        System.out.println("3. Edit item");
        System.out.println("4. Save and exit");
        System.out.println("5. Cancel (discard changes)");

        int option = getIntInput("Choose option: ");
        switch (option) {
            case 1:
                addMenuItem(dailyMenu);
                loadAndEditMenu(day); // reload form
                break;
            case 2:
                removeMenuItem(dailyMenu);
                loadAndEditMenu(day);
                break;
            case 3:
                editMenuItem(dailyMenu);
                loadAndEditMenu(day);
                break;
            case 4:
                saveMenu(dailyMenu);
                break;
            case 5:
                System.out.println("Changes discarded.");
                break;
            default:
                System.out.println("Invalid option.");
                loadAndEditMenu(day);
                break;
        }
    }

    /**
     * Adds a new menu item to the daily menu.
     */
    private void addMenuItem(DailyMenu dailyMenu) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item description: ");
        String desc = scanner.nextLine();
        double price = getDoubleInput("Enter item price: ");
        MenuItem item = new MenuItem(name, desc, price);
        dailyMenu.addItem(item);
        System.out.println("Item added.");
    }

    /**
     * Removes a menu item from the daily menu.
     */
    private void removeMenuItem(DailyMenu dailyMenu) {
        if (dailyMenu.getItems().isEmpty()) {
            System.out.println("No items to remove.");
            return;
        }
        int index = getIntInput("Enter item number to remove: ") - 1;
        if (index >= 0 && index < dailyMenu.getItems().size()) {
            dailyMenu.removeItem(dailyMenu.getItems().get(index));
            System.out.println("Item removed.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Edits an existing menu item.
     */
    private void editMenuItem(DailyMenu dailyMenu) {
        if (dailyMenu.getItems().isEmpty()) {
            System.out.println("No items to edit.");
            return;
        }
        int index = getIntInput("Enter item number to edit: ") - 1;
        if (index >= 0 && index < dailyMenu.getItems().size()) {
            MenuItem item = dailyMenu.getItems().get(index);
            System.out.println("Editing: " + item);
            System.out.print("New name (leave blank to keep): ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                item.setName(name);
            }
            System.out.print("New description (leave blank to keep): ");
            String desc = scanner.nextLine();
            if (!desc.trim().isEmpty()) {
                item.setDescription(desc);
            }
            System.out.print("New price (enter 0 to keep): ");
            String priceStr = scanner.nextLine();
            if (!priceStr.trim().isEmpty()) {
                try {
                    double price = Double.parseDouble(priceStr);
                    if (price > 0) {
                        item.setPrice(price);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price. Kept old price.");
                }
            }
            System.out.println("Item updated.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Saves the daily menu after verification and confirmation.
     */
    private void saveMenu(DailyMenu dailyMenu) {
        System.out.println("\nVerifying data...");
        // Simple verification: ensure no empty names and positive pr.
        for (MenuItem item : dailyMenu.getItems()) {
            if (item.getName() == null || item.getName().trim().isEmpty()) {
                System.out.println("Error: Item with empty name found.");
                System.out.println("Save aborted.");
                return;
            }
            if (item.getPrice() <= 0) {
                System.out.println("Error: Item '" + item.getName() + "' has invalid price.");
                System.out.println("Save aborted.");
                return;
            }
        }

        System.out.println("Data verified.");
        System.out.print("Confirm save? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("yes") || confirm.equals("y")) {
            weeklyMenu.put(dailyMenu.getDay(), dailyMenu);
            System.out.println("Menu saved successfully.");
        } else {
            System.out.println("Save cancelled.");
        }
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please try again.");
            return getIntInput(prompt);
        }
    }

    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please try again.");
            return getDoubleInput(prompt);
        }
    }

    /**
     * Main flow for editing the menu as per use case.
     */
    public void startMenuEditing() {
        System.out.println("Welcome, Point Of Restaurant Operator.");
        // Assuming authentication is already done per entry conditions.

        while (true) {
            displayWeekForm();
            DailyMenu.DayOfWeek selectedDay = selectDay();
            if (selectedDay == null) {
                System.out.println("Exiting menu editor.");
                break;
            }
            loadAndEditMenu(selectedDay);
            System.out.println("\nReturning to day selection...");
        }
    }

    public static void main(String[] args) {
        MenuManager manager = new MenuManager();
        manager.startMenuEditing();
    }
}