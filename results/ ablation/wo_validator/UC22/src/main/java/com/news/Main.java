package com.news;

import com.news.data.DataSource;
import com.news.data.NewsRepository;
import com.news.data.NewsRepositoryImpl;
import com.news.domain.News;
import com.news.presentation.NewsController;
import com.news.service.NewsService;

import java.util.Date;
import java.util.List;

/**
 * Main class to simulate the runnable application.
 * This simulates the interactions from the sequence diagram.
 * In a real application, this would be replaced by a proper UI and dependency injection.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== News Management System ===");

        // Setup the data layer (simplified - using an in-memory H2 database for demonstration)
        // In a real app, use a proper database URL.
        String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        DataSource dataSource = new DataSource(url);
        initializeDemoDatabase(dataSource);

        NewsRepository repository = new NewsRepositoryImpl(dataSource);
        NewsService service = new NewsService(repository);
        NewsController controller = new NewsController(service);

        // Simulate the "Delete News" use case from the sequence diagram.
        // Step 1: Agency Operator activates "Delete News" function -> listNews()
        System.out.println("\n--- Step 1: List all news ---");
        List<News> newsList = controller.listNews();
        newsList.forEach(System.out::println);

        // Step 2: Operator selects a news item and submits deletion form (newsId = 1)
        System.out.println("\n--- Step 2: Delete news with id=1 ---");
        String result = controller.deleteNews(1);
        System.out.println("Result: " + result);

        // Step 3: Show updated list
        System.out.println("\n--- Step 3: List news after deletion ---");
        List<News> updatedList = controller.listNews();
        updatedList.forEach(System.out::println);

        // Step 4: Try to delete non-existent news (rowsAffected == 0 scenario)
        System.out.println("\n--- Step 4: Delete non-existent news (id=99) ---");
        result = controller.deleteNews(99);
        System.out.println("Result: " + result);

        // Step 5: Simulate connection error (uncomment to test)
        // System.out.println("\n--- Step 5: Simulate connection error ---");
        // DataSource brokenDataSource = new DataSource("jdbc:h2:invalid");
        // NewsRepository brokenRepo = new NewsRepositoryImpl(brokenDataSource);
        // NewsService brokenService = new NewsService(brokenRepo);
        // NewsController brokenController = new NewsController(brokenService);
        // System.out.println("Result: " + brokenController.deleteNews(2));
    }

    /**
     * Helper method to create a demo table and insert sample data.
     * In a real application, use proper database migrations.
     */
    private static void initializeDemoDatabase(DataSource dataSource) {
        try (var conn = dataSource.getConnection();
             var stmt = conn.createStatement()) {

            // Create table
            stmt.execute("CREATE TABLE news (" +
                    "id INT PRIMARY KEY, " +
                    "title VARCHAR(100), " +
                    "content VARCHAR(500), " +
                    "publicationDate DATE)");

            // Insert sample data
            stmt.executeUpdate("INSERT INTO news VALUES " +
                    "(1, 'Java 21 Released', 'New features include virtual threads...', '2023-09-19'), " +
                    "(2, 'Spring Boot 3.2', 'Latest version improves performance...', '2023-11-15')");

            System.out.println("Demo database initialized.");
        } catch (Exception e) {
            System.err.println("Failed to initialize demo database: " + e.getMessage());
        }
    }
}