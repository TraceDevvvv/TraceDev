import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Main class implementing the ModifyNews use case.
 * This class provides a console-based interface for the Agency Operator
 * to edit news articles following the specified flow.
 */
public class Main {
    private NewsService newsService;
    private Scanner scanner;
    
    /**
     * Constructor initializes the NewsService and Scanner.
     */
    public Main() {
        newsService = new NewsService();
        scanner = new Scanner(System.in);
    }
    
    /**
     * Main method - entry point of the application.
     * Simulates the Agency Operator logged in and activates editing functionality.
     */
    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }
    
    /**
     * Main application flow.
     * Implements the steps described in the use case.
     */
    private void run() {
        System.out.println("=== Modify News Use Case ===");
        System.out.println("Agency Operator logged in.");
        
        // Step 1: Activate the editing functionality of a news
        System.out.println("\n1. Activating editing functionality...");
        
        // Step 2: View all news in a form
        System.out.println("\n2. Viewing all news articles:");
        newsService.displayAllNews();
        
        // Step 3: Select a news from the list and submit the form
        System.out.println("3. Select a news article to edit (enter ID):");
        int selectedId = readIntInput();
        
        News selectedNews = newsService.getNewsById(selectedId);
        if (selectedNews == null) {
            System.out.println("Error: News with ID " + selectedId + " not found.");
            System.out.println("Activating use case Errored.");
            return;
        }
        
        // Step 4: Load the data of news and display them in a form for editing
        System.out.println("\n4. Current news data:");
        displayNewsForm(selectedNews);
        
        // Step 5: Change data in the form and submit
        System.out.println("\n5. Enter new data for the news article:");
        News updatedNews = readUpdatedNews(selectedNews);
        
        // Step 6: Check the modified information and ask for confirmation
        System.out.println("\n6. Validating modified information...");
        if (!newsService.validateNews(updatedNews)) {
            System.out.println("Error: Invalid or insufficient data.");
            System.out.println("Activating use case Errored.");
            return;
        }
        
        System.out.println("\nModified news data:");
        displayNewsForm(updatedNews);
        
        System.out.print("Confirm changes? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        // Step 7: Confirm the operation of changing the data news
        if (!confirmation.equals("yes")) {
            System.out.println("\nOperation cancelled by Agency Operator.");
            return;
        }
        
        System.out.println("\n7. Confirming operation...");
        
        // Step 8: Store data modified news
        System.out.println("\n8. Storing modified news...");
        boolean updateSuccess = newsService.updateNews(selectedId, updatedNews);
        
        if (updateSuccess) {
            // Save to persistent storage
            boolean saveSuccess = newsService.saveNewsToStorage(updatedNews);
            if (saveSuccess) {
                System.out.println("\n=== Success ===");
                System.out.println("News article has been successfully amended.");
                System.out.println("Exit condition: System notifies successful amendment of news.");
            } else {
                System.out.println("\n=== Error ===");
                System.out.println("Exit condition: Interruption of the connection to the server ETOUR.");
            }
        } else {
            System.out.println("\n=== Error ===");
            System.out.println("Failed to update news. Please try again.");
        }
        
        scanner.close();
    }
    
    /**
     * Displays news data in a form-like format.
     * 
     * @param news the News object to display
     */
    private void displayNewsForm(News news) {
        System.out.println("ID: " + news.getId());
        System.out.println("Title: " + news.getTitle());
        System.out.println("Content: " + news.getContent());
        System.out.println("Author: " + news.getAuthor());
        System.out.println("Date: " + news.getDate());
    }
    
    /**
     * Reads updated news data from user input.
     * Pre-fills with existing values that can be modified.
     * 
     * @param existingNews the original News object
     * @return a new News object with updated values
     */
    private News readUpdatedNews(News existingNews) {
        System.out.print("Title [" + existingNews.getTitle() + "]: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            title = existingNews.getTitle();
        }
        
        System.out.print("Content [" + existingNews.getContent() + "]: ");
        String content = scanner.nextLine().trim();
        if (content.isEmpty()) {
            content = existingNews.getContent();
        }
        
        System.out.print("Author [" + existingNews.getAuthor() + "]: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) {
            author = existingNews.getAuthor();
        }
        
        // For simplicity, keep the original date
        // In a real application, you might allow date editing
        Date date = existingNews.getDate();
        
        return new News(existingNews.getId(), title, content, author, date);
    }
    
    /**
     * Reads an integer input from the user with validation.
     * 
     * @return the integer value entered by the user
     */
    private int readIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}