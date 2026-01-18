"""
Model class representing a refreshment point
Contains all data associated with a refreshment point
"""
import java.util.Arrays;
import java.util.List;
public class RefreshmentPoint {
    private int id;
    private String name;
    private String location;
    private int capacity;
    private String[] refreshments;
    // Constructor
    public RefreshmentPoint(int id, String name, String location, int capacity, String[] refreshments) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.refreshments = refreshments != null ? refreshments : new String[0];
    }
    // Getters and setters
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
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public String[] getRefreshments() {
        return refreshments;
    }
    public List<String> getRefreshmentsList() {
        return Arrays.asList(refreshments);
    }
    public void setRefreshments(String[] refreshments) {
        this.refreshments = refreshments != null ? refreshments : new String[0];
    }
    @Override
    public String toString() {
        return "RefreshmentPoint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", refreshments=" + Arrays.toString(refreshments) +
                '}';
    }
}