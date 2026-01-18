import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Simulate the Entry Operator checking a refreshment point
        System.out.println("Welcome, Entry Operator.");
        System.out.println("Please enter the refreshment point data (e.g., convention name):");
        String conventionName = scanner.nextLine();
        
        // Load data of the Convention for the refreshment point
        RefreshmentPoint refreshmentPoint = new RefreshmentPoint(conventionName);
        
        // Let the operator specify the current banner number
        System.out.println("Please enter the allowed maximum number of banners:");
        int maxAllowed = scanner.nextInt();
        
        // Create the system to verify
        BannerSystem system = new BannerSystem(refreshmentPoint, maxAllowed);
        
        // Start the verification process
        system.verifyBannerNumber();
        
        scanner.close();
    }
}

class RefreshmentPoint {
    private String conventionName;
    
    public RefreshmentPoint(String conventionName) {
        this.conventionName = conventionName;
    }
    
    public String getConventionName() {
        return conventionName;
    }
}

class BannerSystem {
    private RefreshmentPoint refreshmentPoint;
    private int maxAllowed;
    
    public BannerSystem(RefreshmentPoint refreshmentPoint, int maxAllowed) {
        this.refreshmentPoint = refreshmentPoint;
        this.maxAllowed = maxAllowed;
    }
    
    public void verifyBannerNumber() {
        System.out.println("Verifying banner number for convention: " + refreshmentPoint.getConventionName());
        System.out.println("Maximum allowed banners: " + maxAllowed);
    }
}