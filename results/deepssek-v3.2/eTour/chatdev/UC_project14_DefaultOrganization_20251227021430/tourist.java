/**
 * Represents a tourist account in the system
 * Implements the exit condition: returns accounts that meet search criteria
 */
public class Tourist {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String nationality;
    public Tourist(String id, String name, String email, String phone, String nationality) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nationality = nationality;
    }
    // Getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getNationality() {
        return nationality;
    }
    // For debugging and logging
    @Override
    public String toString() {
        return "Tourist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}