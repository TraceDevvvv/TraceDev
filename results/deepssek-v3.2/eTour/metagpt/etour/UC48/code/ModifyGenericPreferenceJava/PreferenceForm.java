package com.etour.preference;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

/**
 * PreferenceForm class handles the form display, editing, and confirmation
 * for modifying personal preferences in the ETOUR system.
 */
public class PreferenceForm {
    private Preference currentPreferences;
    private Preference editedPreferences;
    private Scanner scanner;
    
    /**
     * Constructor that initializes the form with current preferences.
     * @param currentPreferences the current preferences to display and edit
     */
    public PreferenceForm(Preference currentPreferences) {
        this.currentPreferences = new Preference(currentPreferences);
        this.editedPreferences = new Preference(currentPreferences);
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Displays the current preferences in a form-like format.
     */
    public void displayForm() {
        System.out.println("\n=== EDIT PERSONAL PREFERENCES ===");
        System.out.println("Current settings:");
        System.out.println(currentPreferences);
        System.out.println("\nYou can edit the following fields:");
    }
    
    /**
     * Allows the user to edit form fields through console input.
     * @return true if changes were made, false if user cancelled or made no changes
     */
    public boolean editFields() {
        System.out.println("\n--- Edit Fields ---");
        System.out.println("Enter new values for each field (press Enter to keep current value):");
        
        boolean anyChanges = false;
        
        try {
            // Edit language
            System.out.print("\n1. Language [" + editedPreferences.getLanguage() + "]: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty() && !input.equals(editedPreferences.getLanguage())) {
                editedPreferences.setLanguage(input);
                anyChanges = true;
                System.out.println("   ✓ Language updated to: " + editedPreferences.getLanguage());
            }
            
            // Edit currency
            System.out.print("2. Currency [" + editedPreferences.getCurrency() + "]: ");
            input = scanner.nextLine().trim();
            if (!input.isEmpty() && !input.equals(editedPreferences.getCurrency())) {
                editedPreferences.setCurrency(input);
                anyChanges = true;
                System.out.println("   ✓ Currency updated to: " + editedPreferences.getCurrency());
            }
            
            // Edit temperature unit
            System.out.print("3. Temperature Unit [" + editedPreferences.getTemperatureUnit() + "]: ");
            input = scanner.nextLine().trim();
            if (!input.isEmpty() && !input.equals(editedPreferences.getTemperatureUnit())) {
                editedPreferences.setTemperatureUnit(input);
                anyChanges = true;
                System.out.println("   ✓ Temperature unit updated to: " + editedPreferences.getTemperatureUnit());
            }
            
            // Edit theme
            System.out.print("4. Theme [" + editedPreferences.getTheme() + "]: ");
            input = scanner.nextLine().trim();
            if (!input.isEmpty() && !input.equals(editedPreferences.getTheme())) {
                editedPreferences.setTheme(input);
                anyChanges = true;
                System.out.println("   ✓ Theme updated to: " + editedPreferences.getTheme());
            }
            
            // Edit results per page
            System.out.print("5. Results per page [" + editedPreferences.getResultsPerPage() + "]: ");
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    int newResults = Integer.parseInt(input);
                    if (newResults >= 5 && newResults <= 100 && newResults != editedPreferences.getResultsPerPage()) {
                        editedPreferences.setResultsPerPage(newResults);
                        anyChanges = true;
                        System.out.println("   ✓ Results per page updated to: " + editedPreferences.getResultsPerPage());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("   ✗ Invalid number format. Keeping current value.");
                }
            }
            
            // Edit accessibility mode
            System.out.print("6. Accessibility Mode [" + 
                (editedPreferences.isAccessibilityMode() ? "Enabled" : "Disabled") + "] (enable/disable): ");
            input = scanner.nextLine().trim().toLowerCase();
            if (!input.isEmpty()) {
                if (input.equals("enable") && !editedPreferences.isAccessibilityMode()) {
                    editedPreferences.setAccessibilityMode(true);
                    anyChanges = true;
                    System.out.println("   ✓ Accessibility Mode enabled");
                } else if (input.equals("disable") && editedPreferences.isAccessibilityMode()) {
                    editedPreferences.setAccessibilityMode(false);
                    anyChanges = true;
                    System.out.println("   ✓ Accessibility Mode disabled");
                }
            }
            
            // Edit notification settings
            System.out.println("\n--- Notification Settings ---");
            
            System.out.print("7. Email Notifications [" + 
                (editedPreferences.isEmailNotifications() ? "Enabled" : "Disabled") + "] (enable/disable): ");
            input = scanner.nextLine().trim().toLowerCase();
            if (!input.isEmpty()) {
                boolean newValue = input.equals("enable");
                if (newValue != editedPreferences.isEmailNotifications()) {
                    editedPreferences.setEmailNotifications(newValue);
                    anyChanges = true;
                    System.out.println("   ✓ Email Notifications " + (newValue ? "enabled" : "disabled"));
                }
            }
            
            System.out.print("8. SMS Notifications [" + 
                (editedPreferences.isSmsNotifications() ? "Enabled" : "Disabled") + "] (enable/disable): ");
            input = scanner.nextLine().trim().toLowerCase();
            if (!input.isEmpty()) {
                boolean newValue = input.equals("enable");
                if (newValue != editedPreferences.isSmsNotifications()) {
                    editedPreferences.setSmsNotifications(newValue);
                    anyChanges = true;
                    System.out.println("   ✓ SMS Notifications " + (newValue ? "enabled" : "disabled"));
                }
            }
            
            System.out.print("9. Push Notifications [" + 
                (editedPreferences.isPushNotifications() ? "Enabled" : "Disabled") + "] (enable/disable): ");
            input = scanner.nextLine().trim().toLowerCase();
            if (!input.isEmpty()) {
                boolean newValue = input.equals("enable");
                if (newValue != editedPreferences.isPushNotifications()) {
                    editedPreferences.setPushNotifications(newValue);
                    anyChanges = true;
                    System.out.println("   ✓ Push Notifications " + (newValue ? "enabled" : "disabled"));
                }
            }
            
            // Edit favorite categories
            System.out.println("\n--- Favorite Categories ---");
            System.out.println("Current categories: " + editedPreferences.getFavoriteCategories());
            System.out.print("Add categories (comma-separated) or remove with minus sign (e.g., -Adventure): ");
            input = scanner.nextLine().trim();
            
            if (!input.isEmpty()) {
                String[] operations = input.split(",");
                Set<String> updatedCategories = new HashSet<>(editedPreferences.getFavoriteCategories());
                boolean categoriesChanged = false;
                
                for (String op : operations) {
                    String category = op.trim();
                    if (category.startsWith("-")) {
                        // Remove category
                        String catToRemove = category.substring(1).trim();
                        if (!catToRemove.isEmpty() && updatedCategories.remove(catToRemove)) {
                            categoriesChanged = true;
                            System.out.println("   ✓ Removed category: " + catToRemove);
                        }
                    } else if (!category.isEmpty()) {
                        // Add category
                        if (updatedCategories.add(category)) {
                            categoriesChanged = true;
                            System.out.println("   ✓ Added category: " + category);
                        }
                    }
                }
                
                if (categoriesChanged) {
                    editedPreferences.setFavoriteCategories(updatedCategories);
                    anyChanges = true;
                }
            }
            
            // Show summary of changes
            if (anyChanges) {
                System.out.println("\n=== Changes Summary ===");
                System.out.println("The following changes will be made:");
                showDifference();
            } else {
                System.out.println("\nNo changes were made to preferences.");
            }
            
            return anyChanges;
            
        } catch (Exception e) {
            System.out.println("Error during editing: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Shows the difference between current and edited preferences.
     */
    private void showDifference() {
        if (currentPreferences.equals(editedPreferences)) {
            System.out.println("No changes detected.");
            return;
        }
        
        if (!currentPreferences.getLanguage().equals(editedPreferences.getLanguage())) {
            System.out.println("- Language: " + currentPreferences.getLanguage() + " → " + editedPreferences.getLanguage());
        }
        if (!currentPreferences.getCurrency().equals(editedPreferences.getCurrency())) {
            System.out.println("- Currency: " + currentPreferences.getCurrency() + " → " + editedPreferences.getCurrency());
        }
        if (!currentPreferences.getTemperatureUnit().equals(editedPreferences.getTemperatureUnit())) {
            System.out.println("- Temperature Unit: " + currentPreferences.getTemperatureUnit() + 
                              " → " + editedPreferences.getTemperatureUnit());
        }
        if (!currentPreferences.getTheme().equals(editedPreferences.getTheme())) {
            System.out.println("- Theme: " + currentPreferences.getTheme() + " → " + editedPreferences.getTheme());
        }
        if (currentPreferences.getResultsPerPage() != editedPreferences.getResultsPerPage()) {
            System.out.println("- Results per page: " + currentPreferences.getResultsPerPage() + 
                              " → " + editedPreferences.getResultsPerPage());
        }
        if (currentPreferences.isAccessibilityMode() != editedPreferences.isAccessibilityMode()) {
            System.out.println("- Accessibility Mode: " + 
                (currentPreferences.isAccessibilityMode() ? "Enabled" : "Disabled") + " → " + 
                (editedPreferences.isAccessibilityMode() ? "Enabled" : "Disabled"));
        }
        if (currentPreferences.isEmailNotifications() != editedPreferences.isEmailNotifications()) {
            System.out.println("- Email Notifications: " + 
                (currentPreferences.isEmailNotifications() ? "Enabled" : "Disabled") + " → " + 
                (editedPreferences.isEmailNotifications() ? "Enabled" : "Disabled"));
        }
        if (currentPreferences.isSmsNotifications() != editedPreferences.isSmsNotifications()) {
            System.out.println("- SMS Notifications: " + 
                (currentPreferences.isSmsNotifications() ? "Enabled" : "Disabled") + " → " + 
                (editedPreferences.isSmsNotifications() ? "Enabled" : "Disabled"));
        }
        if (currentPreferences.isPushNotifications() != editedPreferences.isPushNotifications()) {
            System.out.println("- Push Notifications: " + 
                (currentPreferences.isPushNotifications() ? "Enabled" : "Disabled") + " → " + 
                (editedPreferences.isPushNotifications() ? "Enabled" : "Disabled"));
        }
        
        Set<String> currentCats = currentPreferences.getFavoriteCategories();
        Set<String> editedCats = editedPreferences.getFavoriteCategories();
        if (!currentCats.equals(editedCats)) {
            System.out.println("- Favorite Categories: " + currentCats + " → " + editedCats);
        }
    }
    
    /**
     * Asks for confirmation of the changes.
     * @return true if user confirms, false if user cancels
     * @throws OperationCanceledException if user explicitly cancels the operation
     */
    public boolean confirmChanges() throws OperationCanceledException {
        System.out.println("\n=== CONFIRMATION REQUIRED ===");
        System.out.println("Are you sure you want to save these changes?");
        System.out.println("Enter 'yes' to confirm, 'no' to cancel, or 'review' to see changes again.");
        
        while (true) {
            System.out.print("Your choice (yes/no/review): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            
            switch (choice) {
                case "yes":
                    System.out.println("✓ Changes confirmed.");
                    return true;
                case "no":
                    System.out.println("✗ Changes cancelled.");
                    throw new OperationCanceledException("User cancelled the confirmation.");
                case "review":
                    System.out.println("\n=== Reviewing Changes ===");
                    showDifference();
                    System.out.println("\nPlease confirm your decision:");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 'yes', 'no', or 'review'.");
            }
        }
    }
    
    /**
     * Gets the updated preferences after editing.
     * @return the edited Preference object
     */
    public Preference getUpdatedPreferences() {
        return new Preference(editedPreferences);
    }
    
    /**
     * Gets the original (current) preferences.
     * @return the original Preference object
     */
    public Preference getOriginalPreferences() {
        return new Preference(currentPreferences);
    }
    
    /**
     * Checks if any changes were made to preferences.
     * @return true if edited preferences differ from original, false otherwise
     */
    public boolean hasChanges() {
        return !currentPreferences.equals(editedPreferences);
    }
    
    /**
     * Closes the scanner resource.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}