package domain;

/**
 * Represents an agency operator.
 */
public class AgencyOperator {
    private String id;
    private String name;

    public AgencyOperator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the operator is logged in.
     * Assumption: Always returns true for simulation.
     * @return true if logged in, false otherwise.
     */
    public boolean isLogged() {
        return true;
    }
}