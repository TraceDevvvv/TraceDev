/**
 * Value object representing a news item.
 * Contains all properties of a news entry and validation logic.
 * Immutable after creation (except through setters).
 */
public class NewsItem {
    private int id;
    private String title;
    private String author;
    private String date;
    private String content;
    /**
     * Constructs a complete news item.
     */
    public NewsItem(int id, String title, String author, String date, String content) {
        this.id = id;
        this.title = title != null ? title.trim() : "";
        this.author = author != null ? author.trim() : "";
        this.date = date != null ? date.trim() : "";
        this.content = content != null ? content.trim() : "";
    }
    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDate() { return date; }
    public String getContent() { return content; }
    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title != null ? title.trim() : ""; }
    public void setAuthor(String author) { this.author = author != null ? author.trim() : ""; }
    public void setDate(String date) { this.date = date != null ? date.trim() : ""; }
    public void setContent(String content) { this.content = content != null ? content.trim() : ""; }
    /**
     * Validates the news item according to use case requirements.
     * Returns true if all required fields are present and non-empty.
     */
    public boolean isValid() {
        return !title.isEmpty() && 
               !author.isEmpty() && 
               !date.isEmpty() && 
               !content.isEmpty();
    }
    /**
     * Compares two news items for equality (by ID).
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NewsItem other = (NewsItem) obj;
        return id == other.id;
    }
    /**
     * Generates hash code based on ID.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
    /**
     * Returns a formatted string representation for debugging.
     */
    @Override
    public String toString() {
        return String.format("NewsItem[ID=%d, Title='%s', Author='%s', Date='%s']", 
                id, title, author, date);
    }
}