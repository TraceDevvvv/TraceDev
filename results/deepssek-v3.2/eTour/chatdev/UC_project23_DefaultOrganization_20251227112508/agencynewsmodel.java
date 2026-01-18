/**
 * Data model simulating news storage and retrieval.
 * Handles CRUD operations and simulates server connection interruptions (ETOUR).
 * In a real system, this would connect to a database or web service.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class AgencyNewsModel {
    private List<NewsItem> newsDatabase;
    private Random random; // For simulating random connection failures
    /**
     * Constructor initializes with sample data.
     */
    public AgencyNewsModel() {
        newsDatabase = new ArrayList<>();
        random = new Random();
        loadSampleData();
    }
    /**
     * Loads initial sample news items.
     */
    private void loadSampleData() {
        addNews(new NewsItem(1, "Java 17 Released", "Oracle Corp", "2023-09-19",
                "Java 17 is the latest long-term support (LTS) release from Oracle. It includes new language features, performance improvements, and enhanced security."));
        addNews(new NewsItem(2, "AI Breakthrough in Code Generation", "TechNews AI", "2023-10-05",
                "Researchers at leading universities have developed an AI model capable of writing functional code in multiple programming languages, potentially revolutionizing software development."));
        addNews(new NewsItem(3, "Critical Security Alert for Web Frameworks", "CyberSecurity Team", "2023-10-10",
                "A serious vulnerability affecting several popular web frameworks has been discovered. Immediate patching is recommended. The vulnerability allows remote code execution."));
        addNews(new NewsItem(4, "New Data Privacy Regulations", "Legal Department", "2023-10-15",
                "Updated data protection regulations will come into effect next quarter. All systems must be compliant by January 1st. Training sessions will be scheduled."));
        addNews(new NewsItem(5, "Cloud Infrastructure Upgrade", "IT Operations", "2023-10-20",
                "Scheduled maintenance window this weekend for cloud infrastructure upgrades. Expected downtime: 2 hours. All teams should save work before the maintenance period."));
    }
    /**
     * Returns a copy of all news items.
     */
    public List<NewsItem> getAllNews() {
        return new ArrayList<>(newsDatabase);
    }
    /**
     * Finds a news item by ID.
     */
    public NewsItem getNewsById(int id) {
        for (NewsItem news : newsDatabase) {
            if (news.getId() == id) {
                return news;
            }
        }
        return null;
    }
    /**
     * Updates an existing news item.
     * Simulates potential server connection interruption (ETOUR).
     * Returns true on success, false on failure.
     * WARNING: This method contains Thread.sleep() for simulation purposes.
     * In production, this should not be called on the EDT.
     */
    public boolean updateNews(NewsItem updatedNews) {
        // Simulate connection interruption (10% chance, as per use case)
        if (random.nextDouble() < 0.1) {
            return false; // ETOUR interruption simulated
        }
        for (int i = 0; i < newsDatabase.size(); i++) {
            if (newsDatabase.get(i).getId() == updatedNews.getId()) {
                newsDatabase.set(i, updatedNews);
                // Simulate persistence delay - WARNING: This blocks the calling thread
                try {
                    Thread.sleep(100 + random.nextInt(200));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
                return true;
            }
        }
        return false; // News not found
    }
    /**
     * Adds a new news item (for completeness, not used in ModifyNews).
     */
    public boolean addNews(NewsItem news) {
        // Generate new ID
        int maxId = 0;
        for (NewsItem n : newsDatabase) {
            if (n.getId() > maxId) {
                maxId = n.getId();
            }
        }
        news.setId(maxId + 1);
        return newsDatabase.add(news);
    }
    /**
     * Deletes a news item by ID (for completeness).
     */
    public boolean deleteNews(int id) {
        return newsDatabase.removeIf(news -> news.getId() == id);
    }
}