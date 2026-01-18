package etour.domain;

/**
 * Domain entity representing a Tourist.
 * Traces to: Entry Conditions: Tourist HAS search preferences
 */
public class Tourist {
    private String id;
    private String name;

    public Tourist(String id, String name) {
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

    public boolean authenticate() {
        // Simplified authentication logic.
        // In a real system, this would check credentials.
        return id != null && !id.trim().isEmpty();
    }

    /**
     * Accesses functionality - corresponds to sequence diagram message m2
     */
    public void accessesFunctionality() {
        // Implementation of the message from sequence diagram
        System.out.println("Tourist accesses functionality");
    }

    /**
     * Edits a field (key, value) - corresponds to sequence diagram message m24
     * @param key the field name
     * @param value the new value
     */
    public void editsField(String key, String value) {
        System.out.println("Tourist edits field: " + key + " = " + value);
    }

    /**
     * Submits the form - corresponds to sequence diagram message m29
     */
    public void submitsForm() {
        System.out.println("Tourist submits form");
    }

    /**
     * Cancels confirmation - corresponds to sequence diagram message m32
     */
    public void cancelConfirmation() {
        System.out.println("Tourist cancels confirmation");
    }

    /**
     * Confirms action - corresponds to sequence diagram message m38
     */
    public void confirms() {
        System.out.println("Tourist confirms");
    }
}