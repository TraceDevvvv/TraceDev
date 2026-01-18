import java.util.Date;

/**
 * News class representing a news article in the system.
 * Contains basic fields: id, title, content, author, and publication date.
 */
public class News {
    private int id;
    private String title;
    private String content;
    private String author;
    private Date date;

    /**
     * Default constructor.
     */
    public News() {
        // Initialize with default values
        this.id = 0;
        this.title = "";
        this.content = "";
        this.author = "";
        this.date = new Date(); // current date as default
    }

    /**
     * Parameterized constructor for creating a News object with specific values.
     * 
     * @param id      unique identifier for the news article
     * @param title   title of the news article
     * @param content content/body of the news article
     * @param author  author of the news article
     * @param date    publication date of the news article
     */
    public News(int id, String title, String content, String author, Date date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    // Getter and Setter methods for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns a string representation of the News object.
     * Useful for displaying news in list format.
     * 
     * @return formatted string with news details
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Title: " + title + " | Author: " + author + " | Date: " + date;
    }

    /**
     * Validates the news data for required fields.
     * Used before saving or updating to ensure data integrity.
     * 
     * @return true if the news has valid data (title and content not empty), false otherwise
     */
    public boolean isValid() {
        return title != null && !title.trim().isEmpty() &&
               content != null && !content.trim().isEmpty() &&
               author != null && !author.trim().isEmpty();
    }
}