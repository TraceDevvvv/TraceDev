package com.example.adapters.web;

import com.example.application.usecases.DeleteNewsCommand;
import com.example.application.usecases.DeleteNewsUseCase;
import com.example.application.common.Result;
import com.example.adapters.serv.AuthenticationService;
import com.example.adapters.models.NewsListModel;
import com.example.core.domain.News;

import java.util.List;

/**
 * Controller for news operations.
 * Implements the flow described in the sequence diagram.
 */
public class NewsController {
    private final DeleteNewsUseCase deleteNewsUseCase;
    private final NewsListModel newsListModel;
    private final AuthenticationService authenticationService;

    public NewsController(DeleteNewsUseCase useCase, AuthenticationService authService, NewsListModel model) {
        this.deleteNewsUseCase = useCase;
        this.authenticationService = authService;
        this.newsListModel = model;
    }

    /**
     * Step 1 in sequence diagram: activate delete function.
     * Precondition: Actor is authenticated (checked internally).
     */
    public void activateDeleteFunction() {
        // Entry condition check.
        if (!authenticationService.isLoggedIn()) {
            throw new IllegalStateException("User must be logged in.");
        }
        // Typically would trigger UI to enable delete functionality.
        System.out.println("Delete function activated for user: " + authenticationService.getCurrentUser());
    }

    /**
     * Step 2: list all news.
     */
    public List<News> listNews() {
        return newsListModel.getNewsItems();
    }

    /**
     * Step 3: select a specific news item.
     */
    public void selectNews(String newsId) {
        News selected = newsListModel.selectItem(newsId);
        System.out.println("Selected news: " + selected.getTitle());
    }

    /**
     * Step 4: submit the form with newsId.
     */
    public void submitForm(String newsId) {
        // Usually would validate and store the newsId for next steps.
        System.out.println("Form submitted for news id: " + newsId);
        askConfirmation();
    }

    /**
     * Step 5: ask for confirmation (UI dialog).
     */
    private void askConfirmation() {
        System.out.println("Showing confirmation dialog...");
    }

    /**
     * Step 6 (confirm branch): confirm deletion.
     *
     * @param newsId the id of news to delete
     * @return Response with status and message
     */
    public Response confirmDelete(String newsId) {
        DeleteNewsCommand command = new DeleteNewsCommand(newsId, authenticationService.getCurrentUser());
        Result result = deleteNewsUseCase.execute(command);
        if (result.isSuccess()) {
            // Step 7: delete data news (already done in interactor).
            deleteDataNews();
            // Step 8: notify success.
            return notifySuccess();
        } else {
            return new Response(500, "Deletion failed: " + result.getMessage());
        }
    }

    /**
     * Step 7: delete data news.
     * According to sequence diagram, this is a self-call within Controller.
     */
    private void deleteDataNews() {
        System.out.println("7. delete data news");
    }

    /**
     * Step 8: notifySuccess.
     * According to sequence diagram, this returns to AO.
     */
    private Response notifySuccess() {
        return new Response(200, "News deleted successfully. (notifySuccess)");
    }

    /**
     * Alternative: direct delete method (could be called from UI).
     */
    public Response deleteNews(String newsId) {
        return confirmDelete(newsId);
    }

    /**
     * Exit condition: user cancels operation.
     */
    public void cancelOperation() {
        System.out.println("Operation cancelled by user.");
        // This corresponds to sequence diagram message "operation cancelled"
        // which is a return from Controller to AO.
        // We assume cancelOperation is called from UI and returns to AO.
    }

    /**
     * Handle connection error notification (alternative flow).
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * New method to handle the askConfirmation as per sequence diagram message 5.
     * It is already private askConfirmation, but we add a public version for completeness.
     */
    public void askConfirmationPublic() {
        askConfirmation();
    }
}