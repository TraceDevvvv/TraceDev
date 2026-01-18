package com.example;

import com.example.controller.LogoutController;
import com.example.model.RegisteredUser;
import com.example.model.Session;
import com.example.repository.DatabaseUserRepository;
import com.example.repository.RedisClient;
import com.example.repository.RedisTokenRepository;
import com.example.service.JwtSessionService;
import com.example.ui.UILayer;
import javax.sql.DataSource;
import java.util.Date;

/**
 * Main class to run the secure logout use case.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        RedisClient redisClient = new RedisClient();
        RedisTokenRepository tokenRepo = new RedisTokenRepository(redisClient);
        JwtSessionService sessionService = new JwtSessionService(tokenRepo);
        // Simulate DataSource (null for simulation)
        DataSource dataSource = null;
        DatabaseUserRepository userRepo = new DatabaseUserRepository(dataSource);

        // Create a user and session for demonstration
        String userId = "user1";
        RegisteredUser user = new RegisteredUser("alice", "alice@example.com", true);
        userRepo.addUser(userId, user);
        Session session = new Session(userId, "sample-jwt-token-123", 
                                      new Date(System.currentTimeMillis() + 3600000)); // expires in 1 hour
        sessionService.addSession(userId, session);

        // Create controller and UI
        LogoutController controller = new LogoutController(sessionService, userRepo);
        UILayer ui = new UILayer(controller);

        System.out.println("Starting secure logout demonstration...");
        System.out.println("User logged in: " + user.isAuthenticated());
        System.out.println("Session valid: " + sessionService.isSessionValid(userId));

        // Trigger logout from UI (sequence diagram step 1)
        ui.accessLogoutFunctionality(userId);

        // Simulate the UI displaying confirmation dialog (sequence diagram step 2)
        ui.displayConfirmationDialog(userId);

        // Simulate user confirming logout request (sequence diagram step 3)
        ui.confirmLogoutRequest(userId);

        // Simulate UI displaying success notification (sequence diagram step 4)
        ui.displaySuccessNotification();

        // Verify results
        System.out.println("User logged in after logout: " + user.isAuthenticated());
        System.out.println("Session valid after logout: " + sessionService.isSessionValid(userId));
        System.out.println("Token blacklisted: " + tokenRepo.isBlacklisted("sample-jwt-token-123"));
    }
}