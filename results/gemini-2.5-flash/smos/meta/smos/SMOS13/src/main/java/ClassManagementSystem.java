```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

// --- Helper Classes ---

/**
 * Represents different roles a user can have in the system.
 */
enum UserRole {
    ADMINISTRATOR,
    STUDENT,
    TEACHER
}

/**
 * Represents a user in the system.
 */
class User {
    private String username;
    private UserRole role;

    public User(String username, UserRole role) {
        this.username = username;
        this.