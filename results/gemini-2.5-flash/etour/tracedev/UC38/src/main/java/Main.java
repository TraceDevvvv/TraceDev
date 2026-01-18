
import application.BannerInsertionUseCase;
import domain.ImageBannerValidator;
import infrastructure.BannerRepositoryImpl;
import infrastructure.PointOfRestaurantRepositoryImpl;
// import infrastructure.NetworkErrorSimulatable; // Assuming this interface exists in infrastructure package - Removed as interface definition is moved here.
import presentation.BannerController;
import presentation.BannerInsertionUI;

import java.util.Scanner;

/**
 * Main class to set up the application context and demonstrate the Banner Insertion Use Case.
 * This acts as the entry point for running the simulated application.
 */
public class Main {
    public static void main(String[] args) {
        // --- Infrastructure Layer Setup ---
        BannerRepositoryImpl bannerRepository = new BannerRepositoryImpl();
        PointOfRestaurantRepositoryImpl pointOfRestaurantRepository = new PointOfRestaurantRepositoryImpl();

        // --- Domain Layer Setup ---
        ImageBannerValidator bannerValidator = new ImageBannerValidator();

        // --- Application Layer Setup ---
        BannerInsertionUseCase bannerInsertionUseCase = new BannerInsertionUseCase(
                bannerRepository,
                pointOfRestaurantRepository,
                bannerValidator
        );

        // --- Presentation Layer Setup ---
        BannerController bannerController = new BannerController(bannerInsertionUseCase);
        BannerInsertionUI bannerInsertionUI = new BannerInsertionUI(bannerController);

        System.out.println("--- Banner Insertion System Demo ---");
        System.out.println("Welcome, Point Of Restaurant Operator!");

        Scanner mainScanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\nSelect an option:");
            System.out.println("1. Request New Banner Insertion");
            System.out.println("2. Simulate Network Error (on/off)");
            System.out.println("3. Show current PoR states (for debugging)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = mainScanner.nextLine();

            switch (choice) {
                case "1":
                    bannerInsertionUI.selectsInsertBannerFeature();
                    break;
                case "2":
                    // To access the simulation methods, we assume the concrete repositories implement
                    // a common interface, like NetworkErrorSimulatable, which provides these methods.
                    // Casting to NetworkErrorSimulatable requires the concrete implementations to adhere to this interface.
                    NetworkErrorSimulatable bannerRepoSimulatable = (NetworkErrorSimulatable) bannerRepository;
                    NetworkErrorSimulatable pointRepoSimulatable = (NetworkErrorSimulatable) pointOfRestaurantRepository;

                    // currentErrorState will be true if network error simulation is OFF for BOTH repositories.
                    // This variable represents the state "is error simulation currently OFF?".
                    boolean currentErrorState = !bannerRepoSimulatable.isSimulateNetworkError() && !pointRepoSimulatable.isSimulateNetworkError();
                    System.out.println("Current network error simulation state: " + (currentErrorState ? "OFF" : "ON"));
                    System.out.print("Do you want to turn network error simulation " + (currentErrorState ? "ON" : "OFF") + "? (yes/no): ");
                    String toggle = mainScanner.nextLine().trim().toLowerCase();
                    if (toggle.equals("yes")) {
                        // If currentErrorState is true (simulation was OFF), set to true (turn ON).
                        // If currentErrorState is false (simulation was ON), set to false (turn OFF).
                        bannerRepoSimulatable.setSimulateNetworkError(currentErrorState);
                        pointRepoSimulatable.setSimulateNetworkError(currentErrorState);
                        System.out.println("Network error simulation is now: " + (currentErrorState ? "ON" : "OFF"));
                    } else {
                        System.out.println("Network error simulation state remains unchanged.");
                    }
                    break;
                case "3":
                    try {
                        System.out.println("\nCurrent PointOfRestaurant States:");
                        System.out.println("PoR ID: por123, Banners: " + pointOfRestaurantRepository.findById("por123").getCurrentBannerCount() + "/" + pointOfRestaurantRepository.findById("por123").getMaxBanners());
                        System.out.println("PoR ID: por456, Banners: " + pointOfRestaurantRepository.findById("por456").getCurrentBannerCount() + "/" + pointOfRestaurantRepository.findById("por456").getMaxBanners());
                    } catch (Exception e) {
                        System.err.println("Could not retrieve PoR states due to: " + e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("4"));

        mainScanner.close();
    }
}

// Minimal definition of NetworkErrorSimulatable interface to resolve compilation errors
// This interface is expected to be in the 'infrastructure' package, but for the purpose
// of making Main.java compile as a single unit without external file creation,
// it's defined here. In a real project, this would be in its own file
// (e.g., infrastructure/NetworkErrorSimulatable.java).
interface NetworkErrorSimulatable {
    boolean isSimulateNetworkError();
    void setSimulateNetworkError(boolean simulate);
}
