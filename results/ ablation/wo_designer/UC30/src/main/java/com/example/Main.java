// Main class to run the application
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TagSearchService service = new TagSearchService();

        System.out.println("=== Tag Search Insertion System ===");
        System.out.println("Agency Operator is logged in.");

        boolean continueInserting = true;
        while (continueInserting) {
            // Step 1 & 2: Access functionality and show form
            System.out.println("\n--- Insert New Tag Search ---");
            System.out.print("Enter tag name: ");
            String tagName = scanner.nextLine().trim();

            // Step 3 & 4: Fill and submit form (implied)
            String result = service.insertTagSearch(tagName);
            System.out.println("Result: " + result);

            // Display current tags
            System.out.println("\nCurrent tags in system:");
            service.getAllTags().forEach(tag -> System.out.println(" - " + tag.getName()));

            // Check if we should continue
            System.out.print("\nInsert another tag? (yes/no): ");
            String response = scanner.nextLine().trim();
            continueInserting = response.equalsIgnoreCase("yes");
        }

        System.out.println("\nGoodbye!");
        scanner.close();
    }
}