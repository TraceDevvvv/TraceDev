
package com.example.news;

import com.example.news.controller.NewsController;
import com.example.news.dto.NewsFormRequest;
import com.example.news.repository.JpaNewsRepository;
import com.example.news.service.NewsService;
import com.example.news.service.NewsValidator;
import com.example.news.view.NewsView;

import java.util.Map;
import java.util.Scanner; // Added import for Scanner

/**
 * Main application class to demonstrate the News Insertion Use Case.
 * Simulates the Agency Operator's interactions and drives the application flow
 * as described in the Sequence Diagram.
 */
public class MainApplication {

    public static void main(String[] args) {
        // --- Dependency Injection / Setup ---
        // Instantiate Repository
        JpaNewsRepository jpaNewsRepository = new JpaNewsRepository();

        // Instantiate Validator
        NewsValidator newsValidator = new NewsValidator();

        // Instantiate Service with its dependencies
        NewsService newsService = new NewsService(jpaNewsRepository, newsValidator);

        // Instantiate View
        NewsView newsView = new NewsView();

        // Instantiate Controller with its dependencies
        NewsController newsController = new NewsController(newsService, newsView);

        // --- Sequence Diagram Flow Simulation ---

        // AO -> View: activatesInsertNews()
        newsView.activatesInsertNews();

        // View -> Controller: showInsertNewsForm()
        // Controller -> View: displayForm(viewModel)
        newsController.showInsertNewsForm();

        // View --> AO: showNewsForm()
        // (Simulated by displayForm and subsequent user input)

        // AO -> View: fillsForm(newsData)
        NewsFormRequest newsFormRequest = newsView.fillsForm();

        // AO -> View: submitsForm()
        newsView.submitsForm();

        // View -> Controller: submitNewsForm(newsFormRequest)
        newsController.submitNewsForm(newsFormRequest);

        // Check if controller already displayed an error and exited the flow (due to invalid input)
        // If the news was invalid, submitNewsForm would have displayed an error and returned.
        // We need to simulate the user deciding what to do next based on the display.
        // For simplicity, we assume if displayConfirmation was shown, validation passed.
        // If no confirmation is displayed, the flow implicitly ended with an error.

        System.out.println("\n--- Awaiting Operator Decision ---");
        System.out.println("1. Confirm (Type 'yes')");
        System.out.println("2. Cancel (Type 'no')");
        System.out.println("3. Simulate Connection Error (Type 'error' - then confirm with 'yes')");
        System.out.print("Your choice: ");

        // Create a local Scanner instance to read operator decision,
        // as newsView.scanner is private.
        Scanner mainAppScanner = new Scanner(System.in);
        String operatorDecision = mainAppScanner.nextLine();

        if (operatorDecision.equalsIgnoreCase("no")) {
            // opt Agency Operator cancels operation
            // AO -> View: cancelOperation()
            // View -> Controller: cancelNewsInsertion()
            newsController.cancelNewsInsertion();
            // Controller --> AO: notifyCancellation() (handled by controller calling view)
        } else if (operatorDecision.equalsIgnoreCase("yes")) {
            // else Agency Operator confirms operation (Step 7)
            // AO -> View: confirmInsertion() (already prompted)
            // View -> Controller: finalizeNewsInsertion(validatedData)
            newsController.finalizeNewsInsertion(newsFormRequest);
            // After this, View will display success/error.
        } else if (operatorDecision.equalsIgnoreCase("error")) {
            // Simulate Connection Interruption (ETOUR) for the next save operation
            jpaNewsRepository.setSimulateConnectionInterruption(true);
            System.out.println("Simulating connection interruption. Now confirm with 'yes' to trigger the error.");
            if (newsView.confirmInsertion()) { // Re-prompt confirmation implicitly
                newsController.finalizeNewsInsertion(newsFormRequest);
            } else {
                newsController.cancelNewsInsertion();
            }
        } else {
            System.out.println("Invalid choice. Operation cancelled.");
            newsController.cancelNewsInsertion();
        }

        // Clean up scanner
        // Close the local Scanner instance used in MainApplication.
        mainAppScanner.close();
    }
}
