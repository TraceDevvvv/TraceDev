
package com.example;

import com.example.auth.AuthenticationService;
import com.example.connection.ETOURServerConnection;
import com.example.controller.NewsController;
import com.example.database.Database;
import com.example.dto.NewsDTO;
import com.example.repository.NewsRepository;
import com.example.validation.NewsValidator;
import com.example.view.NewsEditView;
import com.example.view.NewsListView;

import java.util.List;

/**
 * Main class to simulate the sequence diagram flow.
 */
public class MainApplication {
    public static void main(String[] args) {
        // Setup components
        Database db = new Database();
        ETOURServerConnection conn = new ETOURServerConnection();
        AuthenticationService auth = new AuthenticationService();
        NewsRepository repository = new NewsRepository(db, conn);
        NewsValidator validator = new NewsValidator();
        NewsController controller = new NewsController(repository, validator, auth);
        NewsListView listView = new NewsListView();
        NewsEditView editView = new NewsEditView();

        System.out.println("=== Starting Sequence Diagram Simulation ===");

        // Step 1: Authentication check (Entry Condition)
        boolean loggedIn = auth.isLoggedIn("operator1");
        if (!loggedIn) {
            System.out.println("User not logged in. Exiting.");
            return;
        }

        // Step 2: Agency Operator activates editing functionality (implicit)
        // Step 3: Agency Operator submits form (REQ-Flow-001)
        listView.submitForm(1L);

        // Step 4: List news
        List<NewsDTO> newsList = controller.listNews();
        listView.displayNewsList(newsList);

        // Step 5: Agency Operator selects news (id=1)
        NewsDTO selected = controller.loadNews(1L);
        if (selected == null) {
            listView.showError("News not found");
            return;
        }
        editView.displayNewsForEdit(selected);

        // Step 6: Agency Operator modifies data (simulated)
        selected.setTitle("Updated Title");
        selected.setContent("Updated content.");

        // Step 7: Agency Operator submits form
        String result = controller.submitEditForm(selected);
        System.out.println("Submit result: " + result);

        // Alternative Flow: Cancel operation (REQ-Exit-001)
        System.out.println("\n--- Simulating Cancel Operation ---");
        editView.cancelEdit();

        // Alternative Flow: Connection Interrupted (REQ-Exit-002)
        System.out.println("\n--- Simulating Connection Interruption ---");
        // Simulate a broken connection by mocking the repository update failure.
        // In a real test, we would inject a mock connection that fails.
        ETOURServerConnection badConn = new ETOURServerConnection() {
            @Override
            public boolean checkConnection() {
                return false;
            }
        };
    }
}
