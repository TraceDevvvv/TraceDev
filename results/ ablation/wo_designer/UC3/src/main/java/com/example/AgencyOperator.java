// Agency Operator actor class
public class AgencyOperator {
    private String id;
    private String name;
    private boolean loggedIn;
    
    public AgencyOperator(String id, String name) {
        this.id = id;
        this.name = name;
        this.loggedIn = true; // Simulating logged in state
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}