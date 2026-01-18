'''
SMOSSystem.java
Main system class that handles login, session management, and SMOS server connection.
Simulates the overall system flow according to the use case.
'''
import java.time.LocalDateTime;
import java.util.logging.Logger;
public class SMOSSystem {
    private static final Logger LOGGER = Logger.getLogger(SMOSSystem.class.getName());
    private boolean isAdminLoggedIn = false;
    private boolean isSMOSConnected = false;
    private LocalDateTime lastLoginTime;
    public boolean loginAsAdmin(String username, String password) {
        if (username == null || password == null) {
            LOGGER.warning("Login attempt with null credentials");
            return false;
        }
        if ("admin".equals(username.trim()) && "admin123".equals(password)) {
            isAdminLoggedIn = true;
            lastLoginTime = LocalDateTime.now();
            LOGGER.info("Administrator login successful at " + lastLoginTime);
            return true;
        }
        LOGGER.warning("Login failed: invalid credentials for username: " + username);
        return false;
    }
    public void logout() {
        if (isAdminLoggedIn) {
            isAdminLoggedIn = false;
            disconnectFromSMOS();
            LOGGER.info("Administrator logged out");
        }
    }
    public void connectToSMOS() {
        if (!isAdminLoggedIn) {
            LOGGER.severe("Cannot connect to SMOS: user not logged in as administrator");
            throw new IllegalStateException("User must be logged in as administrator");
        }
        isSMOSConnected = true;
        LOGGER.info("Connected to SMOS server");
    }
    public void disconnectFromSMOS() {
        if (isSMOSConnected) {
            isSMOSConnected = false;
            LOGGER.info("Disconnected from SMOS server");
        } else {
            LOGGER.warning("SMOS server was not connected");
        }
    }
    public boolean isAdminLoggedIn() {
        return isAdminLoggedIn;
    }
    public boolean isSMOSConnected() {
        return isSMOSConnected;
    }
    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }
}