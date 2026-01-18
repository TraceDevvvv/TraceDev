package com.example.convention;

/**
 * Main class to demonstrate the flow described in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Setup components
        ConventionRepositoryImpl repository = new ConventionRepositoryImpl();
        ETOURServerAdapterImpl etourAdapter = new ETOURServerAdapterImpl();
        EmailNotificationAdapter notifier = new EmailNotificationAdapter(etourAdapter);
        ConventionService service = new ConventionService(repository, notifier, etourAdapter);
        ConventionController controller = new ConventionController(service);

        // Precondition: Refreshment point is designated.
        // Create a sample convention in DRAFT status.
        Convention convention = new Convention("CONV001", "POINT123", "Agreement details here", ConventionStatus.DRAFT);
        repository.save(convention);

        System.out.println("=== Activate Convention Request ===");
        System.out.println("1. Agency Operator requests convention form for point POINT123");
        ConventionFormDTO form = controller.loadConventionForm("POINT123");
        if (form != null) {
            System.out.println("Form loaded: Convention ID=" + form.conventionId + ", Status=" + form.status);
        } else {
            System.out.println("Form not found.");
            return;
        }

        System.out.println("\n2. Agency Operator checks data and confirms activation.");
        ActivationResult result = controller.confirmActivation("POINT123");
        System.out.println("Activation result: " + (result.success ? "SUCCESS" : "FAILURE") + " - " + result.message);

        // Verify the convention is now active.
        Convention updated = repository.findByRefreshmentPointId("POINT123");
        System.out.println("Updated convention status: " + updated.getStatus());

        // Simulate connection interruption after notification (exit condition).
        System.out.println("\n3. Simulating connection interruption to ETOUR server...");
        etourAdapter.setConnectionUp(false);
        try {
            etourAdapter.notifyActivation("ANY");
        } catch (RuntimeException e) {
            System.out.println("Connection interruption simulated: " + e.getMessage());
        }
    }
}