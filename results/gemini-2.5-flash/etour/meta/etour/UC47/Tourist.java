package ModifyTouristData_1702899395;

public class Tourist {
    private String id;
    private String name;
    private String email;
    private String password;

    public Tourist(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Tourist{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               '}';
    }

    /**
     * Validates if the tourist data is sufficient and valid.
     * For simplicity, checks if name and email are not empty.
     * In a real application, more complex validation would be performed.
     * @return true if data is valid, false otherwise.
     */
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() &&
               email != null && !email.trim().isEmpty();
    }
}
