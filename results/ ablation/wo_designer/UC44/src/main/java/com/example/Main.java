
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Main class to run the Convention Request System.
 * Handles the flow of requesting a convention between a restaurant operator and an agency.
 */
public class Main {
    public static void main(String[] args) {
        // ConventionRequestSystem class doesn't exist - creating a simple program flow instead
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Convention Request System");
        System.out.println("=========================");
        System.out.println("1. Request Convention");
        System.out.println("2. Exit");
        System.out.print("Enter choice: ");
        
        try {
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("Convention request functionality would start here.");
            } else {
                System.out.println("Exiting system.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        } finally {
            scanner.close();
        }
    }
}
