/**
 * Register class represents a digital register entry in the system.
 * It stores the class name and register ID.
 */
public class Register {
    private String className;
    private String registerId;
    public Register(String className, String registerId) {
        this.className = className;
        this.registerId = registerId;
    }
    public String getClassName() {
        return className;
    }
    public String getRegisterId() {
        return registerId;
    }
    @Override
    public String toString() {
        return "Register[Class: " + className + ", ID: " + registerId + "]";
    }
}