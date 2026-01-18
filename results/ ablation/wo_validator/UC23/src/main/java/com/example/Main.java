package com.example;

import com.example.controller.NewsController;
import com.example.model.News;
import com.example.repository.NewsRepository;
import com.example.repository.NewsRepositoryImpl;
import com.example.service.NewsService;
import com.example.ui.NewsForm;
import javax.sql.DataSource;
import java.util.List;

/**
 * Main class to simulate the sequence diagram flow.
 * Note: DataSource is mocked for simplicity.
 */
public class Main {
    public static void main(String[] args) {
        // Simulate DataSource (not actually used in the in-memory repository).
        DataSource mockDataSource = null;
        // Create repository, service, controller, and UI.
        NewsRepository repository = new NewsRepositoryImpl(mockDataSource);
        NewsService service = new NewsService(repository);
        NewsController controller = new NewsController(service);
        NewsForm ui = new NewsForm(controller);

        System.out.println("=== Simulating Sequence Diagram Flow ===\n");

        // Step 1 & 2: Display all news.
        System.out.println("1. Agency Operator activates editing functionality.");
        List<News> allNews = controller.showAllNews();
        ui.displayNewsList(allNews);

        // Step 3 & 6: Select news and display edit form.
        System.out.println("\n3. Agency Operator selects news from list (ID=1).");
        News selectedNews = controller.showEditForm("1");
        ui.displayEditForm(selectedNews);

        // Step 7 & 8: Change data and submit form.
        System.out.println("\n7. Agency Operator changes data in form.");
        // Simulate changing data via viewModel (in real UI, viewModel would be updated).
        // For simplicity, we just call processEditForm with updated data.
        java.util.Map<String, String> formData = new java.util.HashMap<>();
        formData.put("title", "Updated Title");
        formData.put("content", "Updated Content");
        formData.put("author", "Updated Author");

        System.out.println("8. Agency Operator submits form.");
        String result = controller.processEditForm("1", formData);
        if (result.startsWith("success")) {
            // Step 10 & 11: Confirmation (simulated inside controller for simplicity).
            boolean confirmed = controller.requestConfirmation();
            if (confirmed) {
                // Update already performed in processEditForm, show success.
                ui.showSuccessMessage();
            } else {
                ui.showErrorMessage("Update cancelled.");
            }
        } else {
            ui.showErrorMessage(result.substring(6)); // remove "error:"
        }

        // Display updated news list.
        System.out.println("\n=== Updated News List ===");
        allNews = controller.showAllNews();
        ui.displayNewsList(allNews);
    }
}