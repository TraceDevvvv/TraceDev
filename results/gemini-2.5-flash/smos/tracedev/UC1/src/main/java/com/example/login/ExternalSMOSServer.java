package com.example.login;

import java.util.HashMap;
import java.util.Map;

/**
 * External System: Simulates an external SMOS (System Management and Operating System) server
 * that can query user data for authentication.
 * // Added to satisfy requirement R13
 */
public class ExternalSMOSServer {
    // A simplified in-memory "database" of user data on the SMOS server
    private final Map<String, UserData> smosUserData = new HashMap<>();

    public ExternalSMOSServer() {
        // Populate with mock SMOS user data
        smosUserData.put("testuser", new UserData("user-001", "testuser", String.valueOf("password123".hashCode())));
        smosUserData.put("admin", new UserData("user-002", "admin", String.valueOf("securepass".hashCode())));
        smosUserData.put("network_down_user_id", new UserData("user-004", "network_down", String.valueOf("anypass".hashCode())));
    }

    /**
     * Queries user data from the SMOS server based on username and password hash.
     * This method simulates the interaction with an external system.
     * // Modified to satisfy requirement R13
     * @param username The username to query.
     * @param passwordHash The hashed password to verify.
     * @return UserData if credentials match, otherwise null.
     *         Simulates a ConnectionException if username is 'network_down'.
     */
    public UserData queryUserData(String username, String passwordHash) throws ConnectionException {
        System.out.println("ExternalSMOSServer: Querying user data for '" + username + "'...");

        // Simulate connection interruption for the 'network_down' scenario
        if ("network_down".equals(username)) {
            System.err.println("ExternalSMOSServer: Simulating ConnectionException for 'network_down'.");
            throw new ConnectionException("SMOS server is offline or unreachable."); // R13
        }

        UserData storedData = smosUserData.get(username);
        if (storedData != null && storedData.getPasswordHash().equals(passwordHash)) {
            System.out.println("ExternalSMOSServer: User data found and credentials match for '" + username + "'.");
            return storedData;
        } else {
            System.out.println("ExternalSMOSServer: User data not found or credentials mismatch for '" + username + "'.");
            return null;
        }
    }
}