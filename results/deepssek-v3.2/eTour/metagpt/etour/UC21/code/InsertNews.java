import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a News article in the system
 */
class News {
    private int id;
    private String title;
    private String content;
    private String category;
    private String author;
    private LocalDateTime publishDate;
    private boolean isPublished;
    
    // Constructor
    public News(int id, String title, String content, String category, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.author = author;
        this.publishDate = LocalDateTime.now();
        this.isPublished = true;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public LocalDateTime getPublishDate() { return publishDate; }
    public void setPublishDate(LocalDateTime publishDate) { this.publishDate = publishDate; }
    
    public boolean isPublished() { return isPublished; }
    public void setPublished(boolean published) { isPublished = published; }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format(
            "News ID: %d\nTitle: %s\nCategory: %s\nAuthor: %s\nPublished: %s\nDate: %s\nContent: %s\n",
            id, title, category, author, isPublished ? "Yes" : "No", 
            publishDate.format(formatter), content.length() > 100 ? content.substring(0, 100) + "..." : content
        );
    }
}

/**
 * Service class to handle news operations including insertion and validation
 */
class NewsService {
    private List<News> newsList;
    private int nextId;
    
    public NewsService() {
        this.newsList = new ArrayList<>();
        this.nextId = 1; // Start IDs from 1
    }
    
    /**
     * Validates news data before insertion
     * @param title News title
     * @param content News content
     * @param category News category
     * @param author News author
     * @return true if data is valid, false otherwise
     */
    public boolean validateNewsData(String title, String content, String category, String author) {
        // Check for null or empty values
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Error: Title cannot be empty.");
            return false;
        }
        
        if (content == null || content.trim().isEmpty()) {
            System.out.println("Error: Content cannot be empty.");
            return false;
        }
        
        if (category == null || category.trim().isEmpty()) {
            System.out.println("Error: Category cannot be empty.");
            return false;
        }
        
        if (author == null || author.trim().isEmpty()) {
            System.out.println("Error: Author cannot be empty.");
            return false;
        }
        
        // Check length constraints
        if (title.length() > 200) {
            System.out.println("Error: Title cannot exceed 200 characters.");
            return false;
        }
        
        if (content.length() > 5000) {
            System.out.println("Error: Content cannot exceed 5000 characters.");
            return false;
        }
        
        return true;
    }
    
    /**
     * Inserts a new news article into the system
     * @param title News title
     * @param content News content
     * @param category News category
     * @param author News author
     * @return The inserted News object, or null if insertion failed
     */
    public News insertNews(String title, String content, String category, String author) {
        // Validate data first
        if (!validateNewsData(title, content, category, author)) {
            System.out.println("Data validation failed. Cannot insert news.");
            return null;
        }
        
        try {
            // Simulate potential server connection interruption (ETOUR)
            if (Math.random() < 0.1) { // 10% chance to simulate connection interruption
                throw new RuntimeException("ETOUR: Connection to server interrupted.");
            }
            
            // Create new news object
            News news = new News(nextId++, title.trim(), content.trim(), category.trim(), author.trim());
            
            // Store the news
            newsList.add(news);
            
            System.out.println("News successfully inserted with ID: " + news.getId());
            return news;
            
        } catch (RuntimeException e) {
            System.out.println("Error during news insertion: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Unexpected error during news insertion: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Gets all news articles
     * @return List of all news
     */
    public List<News> getAllNews() {
        return new ArrayList<>(newsList); // Return copy to prevent external modification
    }
    
    /**
     * Gets a news article by ID
     * @param id News ID
     * @return News object or null if not found
     */
    public News getNewsById(int id) {
        for (News news : newsList) {
            if (news.getId() == id) {
                return news;
            }
        }
        return null;
    }
    
    /**
     * Gets the count of news articles
     * @return Number of news articles
     */
    public int getNewsCount() {
        return newsList.size();
    }
}

/**
 * UI simulation class to handle user interaction for inserting news
 */
class InsertNewsUI {
    private Scanner scanner;
    private NewsService newsService;
    private boolean isLoggedIn;
    
    public InsertNewsUI() {
        this.scanner = new Scanner(System.in);
        this.newsService = new NewsService();
        this.isLoggedIn = false;
    }
    
    /**
     * Simulates agency login (Entry condition: The agency has logged)
     */
    public void login() {
        System.out.println("=== Agency Login ===");
        System.out.print("Enter agency ID: ");
        String agencyId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Simple authentication simulation
        if (agencyId != null && !agencyId.trim().isEmpty() && 
            password != null && !password.trim().isEmpty()) {
            isLoggedIn = true;
            System.out.println("Login successful. Welcome, Agency Operator!");
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }
    
    /**
     * Main method to run the InsertNews use case
     */
    public void runInsertNewsUseCase() {
        System.out.println("\n=== Insert News Use Case ===");
        
        // Check login status (Entry condition)
        if (!isLoggedIn) {
            System.out.println("Error: Agency must be logged in to insert news.");
            System.out.println("Please login first.");
            login();
            
            if (!isLoggedIn) {
                System.out.println("Cannot proceed without login.");
                return;
            }
        }
        
        // Step 1: Activate the feature to insert a news
        System.out.println("\n1. Activating feature to insert news...");
        
        // Step 2: Display the corresponding form
        System.out.println("\n2. Displaying news insertion form...");
        
        // Step 3: Fill out the form and submit
        News news = fillNewsForm();
        if (news == null) {
            System.out.println("Operation cancelled by user.");
            return;
        }
        
        // Step 4: Verify data and ask for confirmation
        System.out.println("\n4. Verifying entered data...");
        System.out.println("Please review the news details:");
        System.out.println(news);
        
        System.out.print("\nDo you want to confirm the insertion? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (!confirmation.equals("yes")) {
            System.out.println("Insertion cancelled by Agency Operator.");
            return;
        }
        
        // Step 5: Confirm the operation of insertion
        System.out.println("\n5. Confirming insertion operation...");
        
        // Step 6: Store the data of the new news
        System.out.println("\n6. Storing news data...");
        News insertedNews = newsService.insertNews(
            news.getTitle(), 
            news.getContent(), 
            news.getCategory(), 
            news.getAuthor()
        );
        
        // Exit condition: Notify proper placement of the news
        if (insertedNews != null) {
            System.out.println("\n=== SUCCESS ===");
            System.out.println("News has been successfully placed in the system!");
            System.out.println("News ID: " + insertedNews.getId());
            System.out.println("Total news in system: " + newsService.getNewsCount());
        } else {
            System.out.println("\n=== FAILURE ===");
            System.out.println("News insertion failed. Please try again.");
        }
    }
    
    /**
     * Fills the news form with user input
     * @return News object with entered data, or null if cancelled
     */
    private News fillNewsForm() {
        System.out.println("\n3. Please fill out the news form:");
        
        System.out.print("Enter news title: ");
        String title = scanner.nextLine();
        
        // Check for cancellation
        if (title.equalsIgnoreCase("cancel")) {
            return null;
        }
        
        System.out.print("Enter news category (e.g., Politics, Sports, Technology): ");
        String category = scanner.nextLine();
        
        if (category.equalsIgnoreCase("cancel")) {
            return null;
        }
        
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        
        if (author.equalsIgnoreCase("cancel")) {
            return null;
        }
        
        System.out.println("Enter news content (type 'END' on a new line to finish):");
        StringBuilder contentBuilder = new StringBuilder();
        String line;
        
        while (true) {
            line = scanner.nextLine();
            if (line.equalsIgnoreCase("END")) {
                break;
            }
            if (line.equalsIgnoreCase("cancel")) {
                return null;
            }
            contentBuilder.append(line).append("\n");
        }
        
        String content = contentBuilder.toString().trim();
        
        // Create a temporary news object for validation preview
        return new News(0, title, content, category, author);
    }
    
    /**
     * Displays all news in the system
     */
    public void displayAllNews() {
        List<News> allNews = newsService.getAllNews();
        if (allNews.isEmpty()) {
            System.out.println("No news in the system.");
        } else {
            System.out.println("\n=== All News in System ===");
            for (News news : allNews) {
                System.out.println(news);
                System.out.println("---");
            }
        }
    }
    
    /**
     * Closes resources
     */
    public void close() {
        scanner.close();
    }
}

/**
 * Main class to run the InsertNews program
 */
public class InsertNews {
    public static void main(String[] args) {
        InsertNewsUI ui = new InsertNewsUI();
        
        try {
            // Simulate the complete flow
            System.out.println("=== News Management System ===");
            
            // First, login is required (Entry condition)
            ui.login();
            
            // Check if login was successful
            if (!checkLoginStatus(ui)) {
                return;
            }
            
            // Main program loop
            boolean running = true;
            Scanner mainScanner = new Scanner(System.in);
            
            while (running) {
                System.out.println("\n=== Main Menu ===");
                System.out.println("1. Insert News");
                System.out.println("2. View All News");
                System.out.println("3. Exit");
                System.out.print("Select option: ");
                
                String choice = mainScanner.nextLine().trim();
                
                switch (choice) {
                    case "1":
                        ui.runInsertNewsUseCase();
                        break;
                    case "2":
                        ui.displayAllNews();
                        break;
                    case "3":
                        System.out.println("Exiting News Management System. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
            
            mainScanner.close();
            
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ui.close();
        }
    }
    
    /**
     * Helper method to check login status
     * @param ui The InsertNewsUI instance
     * @return true if logged in, false otherwise
     */
    private static boolean checkLoginStatus(InsertNewsUI ui) {
        // This would normally check a field in the UI class
        // For simulation, we'll assume login was attempted in the login() method
        // and continue anyway for demonstration
        return true;
    }
}