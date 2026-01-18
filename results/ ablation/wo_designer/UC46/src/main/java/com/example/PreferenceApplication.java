import java.util.*;
import java.util.concurrent.*;

public class PreferenceApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TouristService touristService = new TouristService();

    public static void main(String[] args) {
        System.out.println("=== Tourist Preference Modification ===");
        simulateTouristModification();
        scanner.close();
    }

    private static void simulateTouristModification() {
        try {
            System.out.print("Enter tourist username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (!touristService.authenticate(username, password)) {
                System.out.println("Authentication failed!");
                return;
            }
            System.out.println("Tourist authenticated.");

            // Step 1: Tourist accesses modification functionality
            System.out.println("\n1. Accessing search preference modification...");

            // Step 2: System uploads preferences
            Tourist tourist = touristService.getTourist(username);
            SearchPreference preference = tourist.getSearchPreference();
            System.out.println("2. Current preferences loaded.");

            // Step 3: Display preferences
            System.out.println("3. Current Preferences:");
            displayPreferences(preference);

            // Step 4: Tourist edits fields
            SearchPreference newPreference = editPreferenceForm(preference);

            // Step 5: Tourist submits form
            System.out.println("5. Submitting changes...");

            // Step 6: System asks for confirmation
            System.out.print("6. Confirm changes? (yes/no): ");
            String confirm = scanner.nextLine();

            if ("yes".equalsIgnoreCase(confirm)) {
                // Step 7: Tourist confirms
                System.out.println("7. Tourist confirmed.");

                // Step 8: System memorizes changes
                tourist.setSearchPreference(newPreference);
                touristService.updateTourist(tourist);
                System.out.println("8. Preferences updated successfully.");
                System.out.println("Exit: Successful modification notified.");
            } else {
                System.out.println("Exit: Operation cancelled by Tourist.");
            }
        } catch (ServerConnectionException e) {
            System.out.println("Exit: Connection to server lost - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayPreferences(SearchPreference pref) {
        System.out.println("   Max Price: " + pref.getMaxPrice());
        System.out.println("   Max Distance: " + pref.getMaxDistance() + " km");
        System.out.println("   Categories: " + pref.getCategories());
        System.out.println("   Rating Min: " + pref.getMinRating());
    }

    private static SearchPreference editPreferenceForm(SearchPreference original) {
        System.out.println("\n4. Edit preferences (press Enter to keep current value):");
        System.out.print("   Max Price (current: " + original.getMaxPrice() + "): ");
        String maxPriceInput = scanner.nextLine();
        double maxPrice = maxPriceInput.isEmpty() ? original.getMaxPrice() : Double.parseDouble(maxPriceInput);

        System.out.print("   Max Distance in km (current: " + original.getMaxDistance() + "): ");
        String maxDistInput = scanner.nextLine();
        double maxDistance = maxDistInput.isEmpty() ? original.getMaxDistance() : Double.parseDouble(maxDistInput);

        System.out.print("   Categories (comma-separated) (current: " + original.getCategories() + "): ");
        String categoriesInput = scanner.nextLine();
        List<String> categories = categoriesInput.isEmpty() ? original.getCategories() : Arrays.asList(categoriesInput.split(","));

        System.out.print("   Minimum Rating (1-5) (current: " + original.getMinRating() + "): ");
        String ratingInput = scanner.nextLine();
        int minRating = ratingInput.isEmpty() ? original.getMinRating() : Integer.parseInt(ratingInput);

        SearchPreference newPref = new SearchPreference(maxPrice, maxDistance, categories, minRating);
        if (!newPref.validate()) {
            throw new IllegalArgumentException("Invalid preference data!");
        }
        return newPref;
    }
}