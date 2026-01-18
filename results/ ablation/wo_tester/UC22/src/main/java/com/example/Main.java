package com.example;

import com.example.adapters.models.NewsListModel;
import com.example.adapters.serv.AuthenticationService;
import com.example.adapters.web.NewsController;
import com.example.adapters.web.Response;
import com.example.application.usecases.DeleteNewsInteractor;
import com.example.core.domain.News;
import com.example.infrastructure.database.DatabaseConnection;
import com.example.infrastructure.database.DatabaseNewsRepository;

import java.util.List;

/**
 * Main class to demonstrate the flow as per sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting News Deletion Flow ===\n");

        // Setup infrastructure
        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.connect();
        DatabaseNewsRepository repository = new DatabaseNewsRepository(dbConnection);

        // Setup application layer
        DeleteNewsInteractor interactor = new DeleteNewsInteractor(repository, dbConnection);

        // Setup adapters
        AuthenticationService authService = new AuthenticationService();
        List<News> initialNews = repository.findAll();
        NewsListModel model = new NewsListModel(initialNews);
        NewsController controller = new NewsController(interactor, authService, model);

        // Simulate Agency Operator actions (sequence diagram flow)
        System.out.println("1. Agency Operator activates delete function.");
        controller.activateDeleteFunction();

        System.out.println("\n2. System lists news.");
        List<News> newsList = controller.listNews();
        newsList.forEach(n -> System.out.println("   - " + n.getTitle() + " (id: " + n.getId() + ")"));

        String targetNewsId = "1";
        System.out.println("\n3. Operator selects news with id: " + targetNewsId);
        controller.selectNews(targetNewsId);

        System.out.println("\n4. Operator submits form.");
        controller.submitForm(targetNewsId);

        System.out.println("\n5. System asks for confirmation.");

        System.out.println("\n6. Operator confirms deletion.");
        Response response = controller.confirmDelete(targetNewsId);
        System.out.println("   Result: " + response.getStatus() + " - " + response.getMessage());

        // Demonstrate alternative flow: connection error
        System.out.println("\n--- Simulating Connection Error ---");
        dbConnection.simulateConnectionLoss();
        try {
            controller.confirmDelete("2");
        } catch (Exception e) {
            controller.showError("Server connection lost");
        }

        System.out.println("\n=== Flow Completed ===");
    }
}