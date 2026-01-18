'''
Model class representing a Restaurant Operator.
Contains operator information and authentication status.
'''
public class RestaurantOperator {
    private String username;
    private String password;
    private String restaurantName;
    private boolean isAuthenticated;
    public RestaurantOperator(String username, String password, String restaurantName) {
        this.username = username;
        this.password = password;
        this.restaurantName = restaurantName;
        this.isAuthenticated = false;
    }
    public boolean authenticate(String inputUsername, String inputPassword) {
        if (username.equals(inputUsername) && password.equals(inputPassword)) {
            isAuthenticated = true;
            return true;
        }
        return false;
    }
    public boolean isAuthenticated() {
        return isAuthenticated;
    }
    public String getUsername() {
        return username;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void logout() {
        isAuthenticated = false;
    }
}