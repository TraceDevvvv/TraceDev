import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Simulate the Agency Operator flow
        CulturalHeritageManager manager = new CulturalHeritageManager();
        Scanner scanner = new Scanner(System.in);
        
        // Assume Agency Operator is logged in (Entry Condition)
        System.out.println("Agency Operator logged in.");
        
        // Step 1: View list of CulturalHeritage (from SearchCulturalHeritage use case)
        List<CulturalHeritage> heritageList = manager.searchCulturalHeritage("");
        System.out.println("Available Cultural Heritage:");
        for (int i = 0; i < heritageList.size(); i++) {
            System.out.println((i + 1) + ". " + heritageList.get(i).getName());
        }
        
        // Step 2: Agency Operator selects one
        System.out.print("Enter the number of the cultural heritage to delete: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (choice < 1 || choice > heritageList.size()) {
            System.out.println("Invalid selection. Operation cancelled.");
            return;
        }
        
        CulturalHeritage selected = heritageList.get(choice - 1);
        
        // Step 3: Activate elimination function
        // Step 4: System asks for confirmation
        System.out.print("Are you sure you want to delete '" + selected.getName() + "'? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        // Step 5: Agency Operator confirms or cancels
        if (confirmation.equalsIgnoreCase("yes")) {
            // Step 6: System deletes the selected cultural heritage
            boolean success = manager.deleteCulturalHeritage(selected.getId());
            if (success) {
                System.out.println("Successfully deleted cultural heritage: " + selected.getName());
            } else {
                System.out.println("Failed to delete cultural heritage. Server connection may be interrupted.");
            }
        } else {
            System.out.println("Operation cancelled by Agency Operator.");
        }
        
        scanner.close();
    }
}