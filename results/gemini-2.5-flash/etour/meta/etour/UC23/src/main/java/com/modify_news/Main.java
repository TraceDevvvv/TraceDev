package com.modify_news;

import com.modify_news.model.News;
import com.modify_news.repository.NewsRepository;
import com.modify_news.service.NewsService;
import com.modify_news.view.NewsView;
import com.modify_news.util.InputValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Main class for the ModifyNews application.
 * This class orchestrates the flow of the "ModifyNews" use case,
 * interacting with the view, service, and repository layers.
 */
public class Main {

    private final NewsService newsService;
    private final NewsView newsView;

    /**
     * Constructor for Main.
     * Initializes the NewsService and NewsView components.
     */
    public Main() {
        NewsRepository newsRepository = new NewsRepository();
        this.newsService = new NewsService(newsRepository);
        this.newsView = new NewsView();
    }

    /**
     * The main entry point of the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    /**
     * Runs the ModifyNews use case flow.
     */
    public void run() {
        newsView.displayWelcomeMessage();

        try {
            // 1. Activate the editing functionality of a news.
            newsView.displayActivateEditingMessage();

            // 2. View all news in a form.
            List<News> allNews = newsService.getAllNews();
            if (allNews.isEmpty()) {
                newsView.displayErrorMessage("No news articles available to modify.");
                return;
            }
            newsView.displayNewsList(allNews);

            // 3. Select a news from the list and submit the form.
            String selectedNewsId;
            Optional<News> newsToEditOptional;
            do {
                selectedNewsId = newsView.getNewsIdSelection();
                if (selectedNewsId == null) { // User cancelled
                    newsView.displayOperationCancelled();
                    return;
                }
                if (!InputValidator.isValidNewsId(selectedNewsId)) {
                    newsView.displayErrorMessage("Invalid News ID format. Please try again.");
                    continue;
                }
                newsToEditOptional = newsService.getNewsById(selectedNewsId);
                if (newsToEditOptional.isEmpty()) {
                    newsView.displayErrorMessage("News with ID '" + selectedNewsId + "' not found. Please try again.");
                }
            } while (newsToEditOptional.isEmpty());

            News newsToEdit = newsToEditOptional.get();

            // 4. Load the data of news and displays them in a form for editing.
            newsView.displayNewsForEditing(newsToEdit);

            // 5. Change data in the form and submit.
            String[] modifiedData;
            Optional<LocalDateTime> newPublicationDateOptional;
            String newTitle, newContent, newAuthor;
            LocalDateTime newPublicationDate;

            while (true) {
                modifiedData = newsView.getModifiedNewsData(newsToEdit);
                newTitle = modifiedData[0];
                newContent = modifiedData[1];
                newAuthor = modifiedData[2];
                String newPublicationDateStr = modifiedData[3];

                // Attempt to parse the date, if it's not the original string
                if (!newPublicationDateStr.equals(newsToEdit.getPublicationDate().toString())) {
                    newPublicationDateOptional = newsView.parseDateTime(newPublicationDateStr);
                    if (newPublicationDateOptional.isEmpty()) {
                        // Error message already displayed by parseDateTime
                        continue; // Ask for input again
                    }
                    newPublicationDate = newPublicationDateOptional.get();
                } else {
                    newPublicationDate = newsToEdit.getPublicationDate(); // Keep original if not changed
                }

                // 6. Check the modified information and asks for confirmation of the transaction.
                // Where the data is invalid or insufficient, the system activates the use case Errored.
                if (!newsService.validateNewsData(newTitle, newContent, newAuthor, newPublicationDate)) {
                    newsView.displayErrorMessage("Modified data is invalid or insufficient. Please ensure all fields are properly filled.");
                    // This simulates activating the 'Errored' use case by asking for re-entry.
                    continue; // Ask for input again
                }
                break; // Data is valid, proceed to confirmation
            }

            // Create a temporary News object to display for confirmation
            News confirmedNews = new News(newsToEdit.getId(), newTitle, newContent, newAuthor, newPublicationDate);

            // 7. Confirm the operation of changing the data news.
            if (newsView.confirmModification(confirmedNews)) {
                // 8. Stores data modified news.
                Optional<News> updatedNews = newsService.updateNews(
                    newsToEdit.getId(), newTitle, newContent, newAuthor, newPublicationDate
                );

                if (updatedNews.isPresent()) {
                    // Exit conditions: The system shall notify the successful amendment of news.
                    newsView.displayModificationSuccess(updatedNews.get().getId());
                } else {
                    // This case should ideally not be reached if validation passed, but good for robustness.
                    newsView.displayErrorMessage("Failed to save modified news. Please check logs.");
                }
            } else {
                // The Operator Agency cancels the operation.
                newsView.displayOperationCancelled();
            }

        } catch (Exception e) {
            // Interruption of the connection to the server ETOUR. (Simulated by generic exception)
            newsView.displayErrorMessage("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // For debugging purposes
        } finally {
            newsView.displayGoodbyeMessage();
            newsView.closeScanner();
        }
    }
}