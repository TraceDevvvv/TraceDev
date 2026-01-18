import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Represents the Operator Agency actor that interacts with the CulturalHeritageSystem.
 * This class simulates the agency's actions, such as logging in, viewing refreshment points,
 * selecting one for deletion, confirming the deletion, and handling cancellations or errors.
 */
public class OperatorAgency {
    private String username;
    private String password;
    private boolean loggedIn;
    private CulturalHeritageSystem system;
    private Scanner scanner;

    /**
     * Constructs a new OperatorAgency.
     *
     * @param system The CulturalHeritageSystem instance to interact with.
     */
    public OperatorAgency(CulturalHeritageSystem system) {
        this.system = system;
        this.loggedIn = false;
        this.scanner = new Scanner(System.in);
        // For simplicity, hardcode credentials. In a real app, this would be more robust.
        this.username = "agencyUser";
        this.password = "agencyPass";
    }

    /**
     * Simulates the login process for the operator agency.
     *
     * @param inputUsername The username provided by the agency.
     * @param inputPassword The password provided by the agency.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String inputUsername, String inputPassword) {
        if (this.username.equals(inputUsername) && this.password.equals(inputPassword)) {
            this.loggedIn = true;
            System.out.println("Operator Agency: Logged in successfully.");
            return true;
        } else {
            System.out.println("Operator Agency: Login failed. Invalid credentials.");
            return false;
        }
    }

    /**
     * Checks if the operator agency is currently logged in.
     *
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Simulates the process of deleting a refreshment point.
     * This method follows the flow of events described in the use case.
     */
    public void deleteRefreshmentPointFlow() {
        if (!isLoggedIn()) {
            System.out.println("Operator Agency: Please log in first to perform this operation.");
            return;
        }

        System.out.println("\n--- Starting Delete Refreshment Point Process ---");
        List<RefreshmentPoint> points = null;
        try {
            // 1. View a list of points of rest (SearchCulturalHeritage)
            points = system.searchCulturalHeritage();
            if (points.isEmpty()) {
                System.out.println("Operator Agency: No refreshment points found in the system.");
                return;
            }

            System.out.println("Operator Agency: Available Refreshment Points:");
            for (int i = 0; i < points.size(); i++) {
                System.out.println((i + 1) + ". " + points.get(i).getName() + " (ID: " + points.get(i).getId() + ")");
            }

            String selectedId = null;
            RefreshmentPoint selectedPoint = null;
            while (selectedPoint == null) {
                System.out.print("Operator Agency: Enter the ID of the refreshment point to delete (or 'cancel' to abort): ");
                String input = scanner.nextLine();

                if ("cancel".equalsIgnoreCase(input)) {
                    System.out.println("Operator Agency: Operation cancelled by user.");
                    return; // Exit the flow
                }

                String finalInput = input; // For use in lambda
                Optional<RefreshmentPoint> foundPoint = points.stream()
                                                              .filter(rp -> rp.getId().equalsIgnoreCase(finalInput))
                                                              .findFirst();
                if (foundPoint.isPresent()) {
                    selectedPoint = foundPoint.get();
                    selectedId = selectedPoint.getId();
                } else {
                    System.out.println("Operator Agency: Invalid ID. Please try again.");
                }
            }

            System.out.println("Operator Agency: You selected to delete: " + selectedPoint.getName() + " (ID: " + selectedPoint.getId() + ")");

            // 2. Asks for confirmation of the transaction.
            // 3. Confirm the deletion.
            System.out.print("Operator Agency: Are you sure you want to delete this refreshment point? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (!"yes".equalsIgnoreCase(confirmation)) {
                System.out.println("Operator Agency: Deletion cancelled by user.");
                return; // Exit the flow
            }

            // 4. Deletes the selected point of rest.
            boolean deleted = system.deleteRefreshmentPoint(selectedId);

            // Exit conditions: The system shall notify the successful elimination of the point of rest.
            if (deleted) {
                System.out.println("Operator Agency: Notification - Refreshment point '" + selectedPoint.getName() + "' successfully eliminated.");
            } else {
                System.out.println("Operator Agency: Notification - Failed to delete refreshment point '" + selectedPoint.getName() + "'. It might have been removed already or an error occurred.");
            }

        } catch (CulturalHeritageSystem.ETOURConnectionException e) {
            // Interruption ETOUR connection to the server.
            System.err.println("Operator Agency: Error - ETOUR connection interrupted: " + e.getMessage());
            System.out.println("Operator Agency: Attempting to re-establish connection...");
            system.reestablishETOURConnection();
            // Optionally, retry the operation or inform the user to try again.
            System.out.println("Operator Agency: Please try the deletion process again after connection re-establishment.");
        } catch (Exception e) {
            System.err.println("Operator Agency: An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("--- Delete Refreshment Point Process Finished ---");
        }
    }

    /**
     * Closes the scanner used for input.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}