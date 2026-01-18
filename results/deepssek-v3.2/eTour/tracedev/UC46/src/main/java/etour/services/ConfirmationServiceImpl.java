package etour.serv;

import etour.ports.ConfirmationService;
import java.util.Scanner;

/**
 * Service implementation of the ConfirmationService.
 * In a real UI system, this would show a dialog.
 * Here we simulate via console input.
 */
public class ConfirmationServiceImpl implements ConfirmationService {
    // Simple console‑based confirmation for demonstration.
    // In a real GUI application, this would trigger a modal dialog.
    @Override
    public boolean requestConfirmation(String message) {
        System.out.println("[Confirmation] " + message + " (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }
}