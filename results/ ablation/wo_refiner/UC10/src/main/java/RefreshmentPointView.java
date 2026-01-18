import java.util.Map;

/**
 * UI boundary for user interaction.
 */
public class RefreshmentPointView {
    private RefreshmentPointController controller;
    private SearchRefreshmentPointUseCase searchUseCase; // Dependency per REQ-006

    public RefreshmentPointView(RefreshmentPointController controller, SearchRefreshmentPointUseCase searchUseCase) {
        this.controller = controller;
        this.searchUseCase = searchUseCase;
    }

    public void displayRefreshmentPoint(RefreshmentPointDTO dto) {
        System.out.println("Displaying Refreshment Point:");
        System.out.println("ID: " + dto.getId());
        System.out.println("Name: " + dto.getName());
        System.out.println("Description: " + dto.getDescription());
        System.out.println("Location: " + dto.getLocation());
        System.out.println("Details: " + dto.getFormattedDetails());
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void refreshView() {
        System.out.println("View refreshed (REQ-006).");
    }

    // Simulate user selecting a refreshment point
    public void selectRefreshmentPoint(String id) {
        refreshView(); // REQ‑006: Activate view function
        try {
            RefreshmentPointDTO dto = controller.viewCard(id);
            displayRefreshmentPoint(dto);
        } catch (DataRetrievalException e) {
            showError("Unable to retrieve data: " + e.getMessage());
        }
    }

    // Simulate upload data action (REQ-007)
    public void triggerUploadData(String id, Map<String, Object> data) {
        boolean success = controller.handleUpdateRequest(id, data);
        if (success) {
            System.out.println("Update successful.");
        } else {
            System.out.println("Update failed.");
        }
    }
}