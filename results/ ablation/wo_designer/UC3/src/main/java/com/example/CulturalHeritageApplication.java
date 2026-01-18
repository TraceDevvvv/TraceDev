import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main application class
public class CulturalHeritageApplication {
    public static void main(String[] args) {
        // Simulate logged in Agency Operator
        AgencyOperator operator = new AgencyOperator("operator1", "Operator One");
        CulturalHeritageService service = new CulturalHeritageService();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Cultural Heritage Management System ===");
        System.out.println("Logged in as: " + operator.getName());
        
        // Step 1: View list of cultural goods (result of SearchCulturalHeritage)
        List<CulturalObject> culturalObjects = service.searchCulturalHeritage("");
        System.out.println("\n--- List of Cultural Objects ---");
        for (int i = 0; i < culturalObjects.size(); i++) {
            System.out.println((i + 1) + ". " + culturalObjects.get(i).getName());
        }
        
        // Step 2: Agency Operator selects one
        System.out.print("\nEnter the number of the cultural object to edit: ");
        int selection = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (selection < 1 || selection > culturalObjects.size()) {
            System.out.println("Invalid selection. Exiting.");
            return;
        }
        
        CulturalObject selectedObject = culturalObjects.get(selection - 1);
        
        // Step 3: Activate change function
        ChangeRequest changeRequest = new ChangeRequest(operator, selectedObject);
        
        // Step 4 & 5: System loads data and displays form for editing
        System.out.println("\n--- Edit Cultural Object: " + selectedObject.getName() + " ---");
        changeRequest.displayCurrentData();
        
        // Step 6: Agency Operator changes data in form
        System.out.println("\nEnter new data (press Enter to keep current value):");
        
        System.out.print("Name [" + selectedObject.getName() + "]: ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            changeRequest.setNewName(newName);
        }
        
        System.out.print("Description [" + selectedObject.getDescription() + "]: ");
        String newDescription = scanner.nextLine();
        if (!newDescription.trim().isEmpty()) {
            changeRequest.setNewDescription(newDescription);
        }
        
        System.out.print("Location [" + selectedObject.getLocation() + "]: ");
        String newLocation = scanner.nextLine();
        if (!newLocation.trim().isEmpty()) {
            changeRequest.setNewLocation(newLocation);
        }
        
        // Step 7: Agency Operator submits form
        System.out.print("\nSubmit changes? (yes/no): ");
        String submitChoice = scanner.nextLine().toLowerCase();
        
        if (!submitChoice.equals("yes")) {
            System.out.println("Operation cancelled by Agency Operator.");
            return;
        }
        
        // Step 8: System verifies the data entered
        if (!changeRequest.validate()) {
            // Step 10: Activate use case Errored (simulated)
            System.out.println("Error: Invalid or insufficient data.");
            return;
        }
        
        // Step 9: System asks for confirmation
        System.out.print("\nPlease confirm the transaction (type 'CONFIRM' to proceed): ");
        String confirmation = scanner.nextLine();
        
        if (!confirmation.equals("CONFIRM")) {
            System.out.println("Transaction not confirmed. Operation cancelled.");
            return;
        }
        
        // Step 11: Agency Operator confirms
        // Step 12: System stores modified data
        boolean success = service.updateCulturalObject(changeRequest);
        
        if (success) {
            System.out.println("\nSuccess: Cultural object data has been updated.");
        } else {
            System.out.println("\nError: Failed to update cultural object.");
        }
        
        scanner.close();
    }
}