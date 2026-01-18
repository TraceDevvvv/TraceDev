'''
Represents a single news item in the system.
Each news item has a unique ID, a title, content, and a creation date.
'''
import java.util.Date;
import java.util.UUID; // Used to generate unique identifiers for news items
class News {
    private String id;
    private String title;
    private String content;
    private Date creationDate;
    /**
     * Constructs a new News object with a generated unique ID and current date.
     *
     * @param title The title of the news item.
     * @param content The main body content of the news item.
     */
    public News(String title, String content) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID for the news
        this.title = title;
        this.content = content;
        this.creationDate = new Date(); // Set creation date to current time
    }
    /**
     * Constructs a new News object with a specified ID (useful for testing or specific scenarios).
     *
     * @param id The unique identifier for the news item.
     * @param title The title of the news item.
     * @param content The main body content of the news item.
     */
    public News(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = new Date(); // Set creation date
    }
    /**
     * Gets the unique ID of the news item.
     * @return The news ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the title of the news item.
     * @return The news title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Gets the content of the news item.
     * @return The news content.
     */
    public String getContent() {
        return content;
    }
    /**
     * Gets the creation date of the news item.
     * @return The news creation date.
     */
    public Date getCreationDate() {
        return creationDate;
    }
    /**
     * Overrides the toString method to provide a user-friendly string representation
     * of the news item for display in lists.
     * It shows the title and a snippet of the content.
     */
    @Override
    public String toString() {
        // Display only title and a snippet of content in the list for brevity
        String snippet = content.substring(0, Math.min(content.length(), 70));
        if (content.length() > 70) {
            snippet += "...";
        }
        return String.format("%s - %s", title, snippet);
    }
}