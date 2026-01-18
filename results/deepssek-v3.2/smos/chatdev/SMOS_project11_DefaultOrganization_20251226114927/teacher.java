/**
 * Represents a Teacher object with name and associated teachings.
 * Used by TeachingManagementForm to assign/remove teachings.
 */
import java.util.HashSet;
import java.util.Set;
public class Teacher {
    private String name;
    private Set<String> teachings;
    public Teacher(String name) {
        this.name = name;
        this.teachings = new HashSet<>();
    }
    public String getName() {
        return name;
    }
    public void addTeaching(String teaching) {
        teachings.add(teaching);
    }
    public void removeTeaching(String teaching) {
        teachings.remove(teaching);
    }
    public Set<String> getTeachings() {
        return new HashSet<>(teachings);
    }
}