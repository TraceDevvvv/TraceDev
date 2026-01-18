import java.time.LocalDateTime;

/**
 * Represents a News entity in the system.
 * This class holds the data for a single news item.
 */
public class News {
    private int id;
    private String title;
    private String content;
    private LocalDateTime publishDate;

    /**
     * Default constructor.
     */
    public News() {
    }

    /**
     * Parameterized constructor to create a News object with all fields.
     *
     * @param id          the unique identifier of the news
     * @param title       the title of the news
     * @param content     the content/body of the news
     * @param publishDate the date and time when the news was published
     */
    public News(int id, String title, String content, LocalDateTime publishDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the news.
     *
     * @return the news ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the news.
     *
     * @param id the news ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the news.
     *
     * @return the news title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the news.
     *
     * @param title the news title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content of the news.
     *
     * @return the news content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the news.
     *
     * @param content the news content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the publish date of the news.
     *
     * @return the publish date
     */
    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    /**
     * Sets the publish date of the news.
     *
     * @param publishDate the publish date to set
     */
    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * Returns a string representation of the News object.
     * Useful for displaying news in a readable format.
     *
     * @return string representation of the news
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Title: " + title + " | Published: " + publishDate;
    }
}