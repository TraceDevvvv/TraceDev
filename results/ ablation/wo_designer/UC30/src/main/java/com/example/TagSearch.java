// Represents a TagSearch entity with id and name
public class TagSearch {
    private int id;
    private String name;

    public TagSearch(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagSearch [id=" + id + ", name=" + name + "]";
    }
}