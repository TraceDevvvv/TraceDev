'''
Represents a refreshment point (point of rest) in the system.
Contains basic information such as name, rating, and distance.
'''
public class RefreshmentPoint {
    private String name;
    private double rating;  // rating from 0.0 to 5.0
    private double distance; // distance in kilometers
    public RefreshmentPoint(String name, double rating, double distance) {
        this.name = name;
        this.rating = rating;
        this.distance = distance;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    @Override
    public String toString() {
        return "RefreshmentPoint{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", distance=" + distance +
                '}';
    }
}