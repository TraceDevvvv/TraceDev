'''
Represents a parent user in the system.
'''
package model;
import java.util.List;
/**
 * Represents a parent user in the system.
 */
public class ParentUser {
    private String username;
    private List<Student> children;
    /**
     * Constructs a new ParentUser.
     * @param username The username of the parent.
     * @param children A list of Student objects associated with this parent.
     */
    public ParentUser(String username, List<Student> children) {
        this.username = username;
        this.children = children;
    }
    /**
     * Gets the username of the parent.
     * @return The parent's username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets the list of children associated with this parent.
     * @return A list of Student objects.
     */
    public List<Student> getChildren() {
        return children;
    }
}