import java.io.*;
import java.util.*;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Main program implementing the ModifyMenu use case.
 * This console-based application allows a restaurant operator to modify daily menus.
 * 
 * The program follows the use case flow:
 * 1. Operator authentication (entry condition)
 * 2. Activate editing functionality
 * 3. Display form with seven days of the week
 * 4. Select a day and submit form
 * 5. Upload menu data for selected day and load into form
 * 6. Edit menu and submit form
 * 7. Verify data, ask for confirmation (error if insufficient/invalid)
 * 8. Confirm operation
 * 9. Save changes to selected menu
 * 
 * Exit conditions:
 * - Successful modification with notification
 * - Operator cancels operation
 * - Server interruption (ETOUR)
 */
public class ModifyMenuProgram {
    
    // Error handler instance for centralized error handling
    private static ErrorHandler errorHandler = ErrorHandler.getInstance();
    
    /**
     * Main entry point of the program.
     * Implements the complete flow described in the use case.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuManager menuManager = new MenuManager();
        
        System.out.println("=== RESTAURANT MENU MODIFICATION SYSTEM ===");
        System.out.println("Version 1.0 - Follows ModifyMenu Use Case\n");
        
        // Log system startup
        errorHandler.logOperationStart("System Startup", "SYSTEM");
        
        // Step: Authentication (Entry condition)
        RestaurantOperator operator = authenticateOperator(scanner);
        if (operator == null) {
            System.out.println("Authentication failed. Exiting program.");
            scanner.close();
            errorHandler.logOperationEnd("System Shutdown", "SYSTEM", false);
            errorHandler.shutdown();
            return;
        }
        
        System.out.println("\nWelcome, " + operator.getName() + "!");
        System.out.println("Operator ID: " + operator.getOperatorId() + "\n");
        
        errorHandler.logOperationStart("Menu Editing Session", operator.getName());
        
        boolean continueEditing = true;
        
        while (continueEditing) {
            try {
                // Step 1: Activate the editing functionality
                System.out.println("\n--- MENU EDITING ACTIVATED ---");
                
                // Step 2: Display form with seven days of the week
                System.out.println("\nAvailable days for menu modification:");
                String[] daysOfWeek = getDaysOfWeek();
                for (int i = 0; i < daysOfWeek.length; i++) {
                    System.out.println((i + 1) + ". " + daysOfWeek[i]);
                }
                
                // Step 3: Select a day of the week and submit the form
                System.out.print("\nSelect a day (1-7) or 0 to cancel: ");
                int dayChoice = getValidatedIntInput(scanner, 0, 7);
                
                if (dayChoice == 0) {
                    System.out.println("Operation cancelled by operator.");
                    errorHandler.handleCancellation("Menu Editing", operator.getName());
                    continueEditing = false;
                    continue;
                }
                
                String selectedDay = daysOfWeek[dayChoice - 1];
                System.out.println("Selected day: " + selectedDay);
                
                // Step 4: Upload the data menu for the selected day and load into form
                DailyMenu currentMenu = menuManager.getMenuForDay(selectedDay);
                
                if (currentMenu == null) {
                    System.out.println("No existing menu found for " + selectedDay + ". Creating new menu.");
                    currentMenu = new DailyMenu(selectedDay);
                }
                
                // Display current menu
                System.out.println("\nCurrent menu for " + selectedDay + ":");
                System.out.println(currentMenu);
                
                // Step 5: Edit menu of the selected day and submit the form
                DailyMenu editedMenu = editMenuForm(scanner, currentMenu);
                
                // Step 6: Verify the data entered using ValidationUtility
                ValidationUtility.ValidationResult validationResult = ValidationUtility.validateMenu(editedMenu);
                
                if (validationResult.isValid()) {
                    // Display warnings if any
                    String warnings = validationResult.getWarnings();
                    if (!warnings.isEmpty()) {
                        System.out.println("\n⚠ Validation Warnings:");
                        System.out.println(warnings);
                    }
                    
                    // Ask for confirmation
                    System.out.print("\nDo you want to save these changes? (yes/no): ");
                    String confirmation = scanner.nextLine().trim().toLowerCase();
                    
                    if (confirmation.equals("yes") || confirmation.equals("y")) {
                        // Step 7: Confirm the operation
                        System.out.println("Confirming operation...");
                        
                        // Step 8: Save changes to the menu selected
                        if (menuManager.saveMenu(editedMenu, operator)) {
                            System.out.println("\n✓ SUCCESS: Menu for " + selectedDay + " has been modified successfully!");
                            System.out.println("Changes saved at: " + new Date());
                            errorHandler.handleSuccess("Menu Modification", 
                                "Menu for " + selectedDay + " saved by " + operator.getName());
                        } else {
                            System.out.println("\n✗ ERROR: Failed to save menu changes. Please try again.");
                            errorHandler.logError(ErrorHandler.Severity.ERROR, 
                                "Failed to save menu for " + selectedDay, 
                                "ModifyMenuProgram");
                        }
                    } else {
                        System.out.println("Changes not saved. Returning to main menu.");
                        errorHandler.handleCancellation("Menu Save Confirmation", operator.getName());
                    }
                } else {
                    // Display validation errors
                    System.out.println("\n✗ VALIDATION FAILED:");
                    System.out.println(validationResult.getErrors());
                    errorHandler.handleValidationError(selectedDay, validationResult.getErrors());
                    System.out.println("Operation aborted due to validation errors.");
                }
                
                // Ask if operator wants to continue editing
                System.out.print("\nDo you want to edit another day's menu? (yes/no): ");
                String continueChoice = scanner.nextLine().trim().toLowerCase();
                if (!continueChoice.equals("yes") && !continueChoice.equals("y")) {
                    continueEditing = false;
                    System.out.println("Exiting menu editing system.");
                    errorHandler.logOperationEnd("Menu Editing Session", operator.getName(), true);
                }
                
            } catch (Exception e) {
                System.out.println("ERROR: An unexpected error occurred: " + e.getMessage());
                errorHandler.logError(ErrorHandler.Severity.CRITICAL, 
                    "Unexpected error in main loop: " + e.getMessage(), 
                    "ModifyMenuProgram", e);
                System.out.println("Returning to main menu...");
            }
        }
        
        scanner.close();
        System.out.println("\n=== SYSTEM SHUTDOWN ===");
        System.out.println("Thank you for using Restaurant Menu Modification System.");
        errorHandler.logOperationEnd("System Shutdown", "SYSTEM", true);
        errorHandler.shutdown();
    }
    
    /**
     * Authenticates the restaurant operator.
     * Implements entry condition: operator must be successfully authenticated.
     * Uses ValidationUtility for input validation.
     * 
     * @param scanner Scanner for user input
     * @return Authenticated RestaurantOperator object, or null if authentication fails
     */
    private static RestaurantOperator authenticateOperator(Scanner scanner) {
        System.out.println("\n=== OPERATOR AUTHENTICATION ===");
        System.out.println("Please enter your credentials to continue.\n");
        
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter operator ID: ");
            String operatorId = scanner.nextLine().trim();
            
            // Validate operator ID format
            if (!ValidationUtility.isValidOperatorId(operatorId)) {
                errorHandler.handleInputError("Operator ID", operatorId, "3-10 alphanumeric characters");
                attempts--;
                if (attempts > 0) {
                    System.out.println("Invalid operator ID format. " + attempts + " attempts remaining.\n");
                }
                continue;
            }
            
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim(); // In real system, use password masking
            
            // Validate password format
            if (!ValidationUtility.isValidPassword(password)) {
                errorHandler.handleInputError("Password", "***", "at least 6 characters with letters and numbers");
                attempts--;
                if (attempts > 0) {
                    System.out.println("Invalid password format. " + attempts + " attempts remaining.\n");
                }
                continue;
            }
            
            RestaurantOperator operator = RestaurantOperator.authenticate(operatorId, password);
            
            if (operator != null) {
                System.out.println("\n✓ Authentication successful!");
                errorHandler.handleSuccess("Operator Authentication", operatorId);
                return operator;
            } else {
                attempts--;
                errorHandler.handleAuthenticationError(operatorId, attempts);
                if (attempts > 0) {
                    System.out.println("\n✗ Authentication failed. " + attempts + " attempts remaining.\n");
                } else {
                    System.out.println("\n✗ Authentication failed. No attempts remaining.");
                }
            }
        }
        
        return null;
    }
    
    /**
     * Returns an array of the seven days of the week.
     * 
     * @return String array containing day names
     */
    private static String[] getDaysOfWeek() {
        String[] days = new String[7];
        DayOfWeek[] dayValues = DayOfWeek.values();
        
        for (int i = 0; i < 7; i++) {
            days[i] = dayValues[i].getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        }
        
        return days;
    }
    
    /**
     * Presents a form for editing menu data.
     * Implements step 5: Edit menu and submit form.
     * 
     * @param scanner Scanner for user input
     * @param currentMenu The current menu to be edited
     * @return Edited DailyMenu object
     */
    private static DailyMenu editMenuForm(Scanner scanner, DailyMenu currentMenu) {
        System.out.println("\n--- EDIT MENU FORM ---");
        System.out.println("Instructions:");
        System.out.println("1. Enter menu items one per line");
        System.out.println("2. Type 'CATEGORY' to switch between categories");
        System.out.println("3. Type 'DONE' when finished with all categories");
        System.out.println("4. Available categories: APPETIZERS, MAIN_COURSES, DESSERTS, BEVERAGES\n");
        
        List<String> appetizers = new ArrayList<>();
        List<String> mainCourses = new ArrayList<>();
        List<String> desserts = new ArrayList<>();
        List<String> beverages = new ArrayList<>();
        
        String category = "APPETIZERS";
        System.out.println("Currently editing: " + category);
        System.out.println("Enter items for " + category + " (one per line):");
        
        boolean editing = true;
        while (editing) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("DONE")) {
                // Check if at least one item has been entered
                if (appetizers.size() > 0 || mainCourses.size() > 0 || 
                    desserts.size() > 0 || beverages.size() > 0) {
                    editing = false;
                    continue;
                } else {
                    System.out.println("Please enter at least one menu item before finishing.");
                    continue;
                }
            }
            
            if (input.equalsIgnoreCase("CATEGORY")) {
                System.out.println("\nAvailable categories: APPETIZERS, MAIN_COURSES, DESSERTS, BEVERAGES");
                System.out.print("Enter category to switch to: ");
                category = scanner.nextLine().trim().toUpperCase();
                
                // Validate category
                if (!category.equals("APPETIZERS") && !category.equals("MAIN_COURSES") && 
                    !category.equals("DESSERTS") && !category.equals("BEVERAGES")) {
                    System.out.println("Invalid category. Defaulting to APPETIZERS.");
                    category = "APPETIZERS";
                }
                
                System.out.println("\nNow editing: " + category);
                System.out.println("Enter items for " + category + " (one per line):");
                continue;
            }
            
            if (!input.isEmpty()) {
                // Validate item before adding
                if (ValidationUtility.containsInvalidCharacters(input)) {
                    System.out.println("Item contains invalid characters. Please re-enter.");
                    continue;
                }
                
                if (ValidationUtility.containsSqlInjection(input)) {
                    System.out.println("Item contains potentially dangerous content. Please re-enter.");
                    continue;
                }
                
                switch (category) {
                    case "APPETIZERS":
                        appetizers.add(input);
                        System.out.println("Added to appetizers: " + input);
                        break;
                    case "MAIN_COURSES":
                        mainCourses.add(input);
                        System.out.println("Added to main courses: " + input);
                        break;
                    case "DESSERTS":
                        desserts.add(input);
                        System.out.println("Added to desserts: " + input);
                        break;
                    case "BEVERAGES":
                        beverages.add(input);
                        System.out.println("Added to beverages: " + input);
                        break;
                    default:
                        System.out.println("Invalid category. Using APPETIZERS.");
                        appetizers.add(input);
                }
            }
        }
        
        // Create new menu with edited data
        DailyMenu editedMenu = new DailyMenu(currentMenu.getDayOfWeek());
        editedMenu.setAppetizers(appetizers);
        editedMenu.setMainCourses(mainCourses);
        editedMenu.setDesserts(desserts);
        editedMenu.setBeverages(beverages);
        
        // Get special notes
        System.out.print("\nEnter special notes or dietary information (press Enter to skip): ");
        String specialNotes = scanner.nextLine().trim();
        if (!specialNotes.isEmpty()) {
            // Validate special notes
            if (specialNotes.length() > 500) {
                System.out.println("Special notes too long (max 500 characters). Truncating.");
                specialNotes = specialNotes.substring(0, 500);
            }
            editedMenu.setSpecialNotes(specialNotes);
        }
        
        // Get price information if needed
        System.out.print("Enter price information (e.g., 'Lunch: $15, Dinner: $25') or press Enter to skip: ");
        String priceInfo = scanner.nextLine().trim();
        if (!priceInfo.isEmpty()) {
            System.out.println("Price information noted: " + priceInfo);
            errorHandler.logError(ErrorHandler.Severity.INFO, 
                "Price info added for " + currentMenu.getDayOfWeek() + ": " + priceInfo, 
                "ModifyMenuProgram");
        }
        
        System.out.println("\n✓ Menu editing complete. Here's your edited menu:");
        System.out.println(editedMenu);
        
        return editedMenu;
    }
    
    /**
     * Helper method to get validated integer input within a range.
     * Uses ValidationUtility for input validation.
     * 
     * @param scanner Scanner for user input
     * @param min Minimum valid value
     * @param max Maximum valid value
     * @return Validated integer input
     */
    private static int getValidatedIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                
                // Allow empty input to mean cancel (only if min is 0)
                if (input.isEmpty() && min == 0) {
                    return 0;
                }
                
                // Validate using ValidationUtility
                if (!ValidationUtility.isValidIntegerInRange(input, min, max)) {
                    System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
                    continue;
                }
                
                return Integer.parseInt(input);
                
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
            }
        }
    }
    
    /**
     * Utility method to display program help information.
     */
    private static void displayHelp() {
        System.out.println("\n=== HELP: MODIFY MENU SYSTEM ===");
        System.out.println("This system allows restaurant operators to modify daily menus.");
        System.out.println("\nFlow:");
        System.out.println("1. Authenticate with operator ID and password");
        System.out.println("2. Select a day of the week to edit");
        System.out.println("3. View current menu for that day");
        System.out.println("4. Edit menu items by category");
        System.out.println("5. Validate and confirm changes");
        System.out.println("6. Save changes to the system");
        System.out.println("\nAvailable commands during editing:");
        System.out.println("- CATEGORY: Switch between menu categories");
        System.out.println("- DONE: Finish editing and proceed to validation");
        System.out.println("\nExit conditions:");
        System.out.println("- Successful menu modification");
        System.out.println("- Operator cancellation");
        System.out.println("- Server interruption (simulated as ETOUR error)");
        System.out.println("\nFor technical support, check the log file: " + errorHandler.getLogFilePath());
    }
    
    /**
     * Displays system information and statistics.
     */
    private static void displaySystemInfo() {
        System.out.println("\n=== SYSTEM INFORMATION ===");
        System.out.println("Program: Restaurant Menu Modification System");
        System.out.println("Version: 1.0");
        System.out.println("Use Case: ModifyMenu");
        System.out.println("Log File: " + errorHandler.getLogFilePath());
        System.out.println("\nTo display help, type 'HELP' at any menu prompt.");
    }
}