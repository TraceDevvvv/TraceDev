// Simple class representing a Site with name and path.
public class Site {
    private String name;
    private String path;

    public Site(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Site{name='" + name + "', path='" + path + "'}";
    }
}