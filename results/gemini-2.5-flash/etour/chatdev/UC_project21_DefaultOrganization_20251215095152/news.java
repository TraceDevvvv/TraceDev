/**
 * `News` class models a news article with essential attributes.
 * It holds information such as title, content, author, and publication date.
 */
public class News {
    private String title;
    private String content;
    private String author;
    private String publicationDate; // For simplicity, storing as String. Could be java.time.LocalDate for better date handling.
    /**
     * Constructs a new `News` object with provided details.
     *
     * @param title The headline or title of the news article.
     * @param content The main body or text of the news article.
     * @param author The individual or entity who wrote the news.
     * @param publicationDate The date when the news was published, expected in "YYYY-MM-DD" format.
     */
    public News(String title, String content, String author, String publicationDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.publicationDate = publicationDate;
    }
    /**
     * Retrieves the title of the news article.
     *
     * @return A `String` representing the title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the title of the news article.
     *
     * @param title The new title to be set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Retrieves the content of the news article.
     *
     * @return A `String` representing the content.
     */
    public String getContent() {
        return content;
    }
    /**
     * Sets the content of the news article.
     *
     * @param content The new content to be set.
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * Retrieves the author of the news article.
     *
     * @return A `String` representing the author.
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Sets the author of the news article.
     *
     * @param author The new author to be set.
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * Retrieves the publication date of the news article.
     *
     * @return A `String` representing the publication date.
     */
    public String getPublicationDate() {
        return publicationDate;
    }
    /**
     * Sets the publication date of the news article.
     *
     * @param publicationDate The new publication date to be set.
     */
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
    /**
     * Provides a string representation of the `News` object.
     * This method is useful for logging, debugging, and displaying news details.
     *
     * @return A formatted `String` containing the news's title, author, publication date, and content.
     */
    @Override
    public String toString() {
        return "Title: " + title + "\n" +
               "Author: " + author + "\n" +
               "Publication Date: " + publicationDate + "\n" +
               "Content: " + content;
    }
}