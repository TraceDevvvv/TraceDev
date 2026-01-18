public class AgencyOperator {
    private static AgencyOperator instance;
    private boolean isLoggedIn;
    
    private AgencyOperator() {
        this.isLoggedIn = false;
    }
    
    // Singleton pattern to ensure only one operator instance
    public static AgencyOperator getInstance() {
        if (instance == null) {
            instance = new AgencyOperator();
        }
        return instance;
    }
    
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}