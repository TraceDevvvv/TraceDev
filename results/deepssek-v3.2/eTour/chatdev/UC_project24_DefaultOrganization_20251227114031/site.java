/**
 * Data class representing a Site.
 */
public class Site {
    private int id;
    private String name;
    public Site(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}