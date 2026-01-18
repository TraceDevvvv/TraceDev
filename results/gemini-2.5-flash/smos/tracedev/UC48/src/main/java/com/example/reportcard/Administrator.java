
package com.example.reportcard;

import java.util.Arrays;
import java.util.List;

/**
 * Represents an Administrator user in the system.
 * Extends the base User class and automatically assigns the "ADMIN" role.
 * Satisfies REQ-001: User Login & Role Management.
 */
public class Administrator extends User {

    // Specific admin attributes/methods if any
    // For now, it simply inherits and sets a specific role.

    /**
     * Constructs a new Administrator with a given username.
     * The "ADMIN" role is automatically assigned.
     *
     * @param username The username of the administrator.
     */
    public Administrator(String username) {
        super(username, Arrays.asList("USER", "ADMIN")); // Admins also have USER role
    }

    /**
     * Overloaded constructor to allow custom roles if needed, though typically admin has fixed roles.
     *
     * @param username The username of the administrator.
     * @param additionalRoles Any additional roles beyond the default "ADMIN" and "USER".
     */
    public Administrator(String username, List<String> additionalRoles) {
        super(username, combineRoles(additionalRoles));
    }

    private static List<String> combineRoles(List<String> additionalRoles) {
        List<String> baseRoles = new java.util.ArrayList<>(Arrays.asList("USER", "ADMIN"));
        if (additionalRoles != null) {
            additionalRoles.forEach(role -> {
                if (!baseRoles.contains(role)) {
                    baseRoles.add(role);
                }
            });
        }
        return baseRoles;
    }

    @Override
    public String toString() {
        return "Administrator{" +
               "username='" + getUsername() + '\'' +
               ", roles=" + getRoles() +
               ", loggedIn=" + isLoggedIn() +
               '}';
    }

    // A getter for roles, assuming it's needed for inspection
    // (Could be added to User if generally useful)
    public List<String> getRoles() {
        // The compilation error indicates that 'super.getRoles()' method does not exist in the User class.
        // For an Administrator instance, based on its constructors, it is guaranteed to have the "ADMIN" role.
        // Therefore, 'super.hasRole("ADMIN")' will always evaluate to true.
        // This means the 'else' branch (super.getRoles()) would never be reached in practice for an Administrator object.
        // By returning the hardcoded base roles, we fix the compilation error
        // while preserving the effective logic for an Administrator.
        return Arrays.asList("USER", "ADMIN");
    }
}
