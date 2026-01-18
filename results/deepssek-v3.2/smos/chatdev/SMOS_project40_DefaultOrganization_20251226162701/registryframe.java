'''
RegistryFrame.java
Simulates the registry screen GUI that displays a list of justifications.
Provides a method to display the registry and return to it after editing.
'''
import java.util.Map;
public class RegistryFrame {
    public void displayRegistry() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("REGISTRY SCREEN - Justifications List");
        System.out.println("=".repeat(50));
        Map<Integer, Justification> justifications = DatabaseSimulator.getAllJustifications();
        if (justifications.isEmpty()) {
            System.out.println("No justifications found in the registry.");
        } else {
            System.out.printf("%-5s %-12s %-50s%n", "ID", "Date", "Description");
            System.out.println("-".repeat(67));
            justifications.values().stream()
                .sorted((j1, j2) -> Integer.compare(j1.getId(), j2.getId()))
                .forEach(j -> {
                    String desc = j.getDescription();
                    if (desc.length() > 45) {
                        desc = desc.substring(0, 42) + "...";
                    }
                    System.out.printf("%-5d %-12s %-50s%n", 
                                    j.getId(), 
                                    j.getDate(), 
                                    desc);
                });
        }
        System.out.println("=".repeat(50) + "\n");
    }
    public void returnToRegistry() {
        System.out.println("\nReturning to registry screen...");
        displayRegistry();
    }
    public void displayJustificationDetails(int id) {
        Justification j = DatabaseSimulator.getJustification(id);
        if (j != null) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("JUSTIFICATION DETAILS");
            System.out.println("=".repeat(60));
            System.out.println("ID:          " + j.getId());
            System.out.println("Date:        " + j.getDate());
            System.out.println("Description: " + j.getDescription());
            System.out.println("=".repeat(60));
        } else {
            System.out.println("Justification with ID " + id + " not found.");
        }
    }
}