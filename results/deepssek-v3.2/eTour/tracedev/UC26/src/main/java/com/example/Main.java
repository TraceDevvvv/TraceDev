
package com.example;

import com.example.controller.EditFeedbackCommentController;
import com.example.dto.FeedbackDTO;
import com.example.dto.UpdateCommentCommand;
import com.example.dto.OperationResult;
import com.example.repository.FeedbackRepository;
import com.example.repository.FeedbackRepositoryImpl;
import com.example.service.AuthenticationService;
import com.example.service.ConnectionManager;
import com.example.service.ErrorHandler;
import com.example.service.NotificationService;
import com.example.strategy.CommentLengthStrategy;
import com.example.strategy.ValidationStrategy;
import com.example.ui.FeedbackUI;
import javax.sql.DataSource;
import java.util.List;

/**
 * Main class to run the application.
 * Simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize serv and dependencies
        ConnectionManager connectionManager = new ConnectionManager();
        AuthenticationService authService = new AuthenticationService();
        ErrorHandler errorHandler = new ErrorHandler();
        NotificationService notificationService = new NotificationService();
        ValidationStrategy validationStrategy = new CommentLengthStrategy();
        // Simulated DataSource (e.g., HikariDataSource, but we'll mock it)
        DataSource dataSource = new javax.sql.DataSource() {
            // Mock implementation
            @Override
            public java.sql.Connection getConnection() {
                return null;
            }
            @Override
            public java.sql.Connection getConnection(String username, String password) {
                return null;
            }
            @Override
            public java.io.PrintWriter getLogWriter() {
                return null;
            }
            @Override
            public void setLogWriter(java.io.PrintWriter out) {}
            @Override
            public void setLoginTimeout(int seconds) {}
            @Override
            public int getLoginTimeout() {
                return 0;
            }
            @Override
            public java.util.logging.Logger getParentLogger() {
                return null;
            }
            @Override
            public <T> T unwrap(Class<T> iface) {
                return null;
            }
            @Override
            public boolean isWrapperFor(Class<?> iface) {
                return false;
            }
        };
        FeedbackRepository feedbackRepository = new FeedbackRepositoryImpl(dataSource, connectionManager);

        // Create controller
        EditFeedbackCommentController controller = new EditFeedbackCommentController(
                feedbackRepository,
                validationStrategy,
                authService,
                notificationService,
                errorHandler
        );

        // Create UI
        FeedbackUI ui = new FeedbackUI(controller, errorHandler);

        // Simulate sequence diagram

        System.out.println("=== Starting Use Case: Edit Feedback Comment ===\n");

        // 1. Login (simulated by authentication service)
        System.out.println("1. User logs in (session established)");
        boolean sessionValid = authService.verifySession();
        System.out.println("   Session valid: " + sessionValid + "\n");

        // 2. User views list of sites (from SearchSite) - sequence message m1
        System.out.println("2. User views list of sites (from SearchSite)");
        ui.ViewsListOfSitesFromSearchSite();
        ui.viewSites(authService.getCurrentUser());
        System.out.println();

        // 3. User selects a site - sequence message m2
        System.out.println("3. User selects a site");
        ui.SelectsASite();
        // Assume selection of site S1
        ui.selectFeedbackFromSite("S1");
        System.out.println();

        // 4. UI displays feedback list - sequence message m7
        System.out.println("4. UI displays feedback list");
        ui.DisplaysFeedbackList();
        List<com.example.dto.FeedbackDTO> feedbacks = controller.loadSiteFeedback("S1");
        ui.displayFeedbackList(feedbacks);
        System.out.println();

        // 5. User selects feedback from list - sequence message m8
        System.out.println("5. User selects feedback from list");
        ui.SelectsFeedbackFromList();
        ui.loadAndDisplayFeedback("F1");
        System.out.println();

        // 6. User activates change comment function - sequence message m9
        System.out.println("6. User activates 'change comment' function");
        ui.ActivatesChangeCommentFunction();
        // (This is implicit in loading feedback above)

        // 7. UI displays feedback in form - sequence message m14
        System.out.println("7. UI displays feedback in form");
        ui.DisplaysFeedbackInForm();
        FeedbackDTO feedback = controller.loadFeedback("F1");
        ui.displayFeedbackInForm(feedback);
        System.out.println();

        // 8. User submits form to edit - sequence message m15
        System.out.println("8. User submits form (to edit)");
        ui.SubmitsFormToEdit();
        // (This would typically show an edit form; simulated by directly calling edit)

        // 9. UI displays edit comment form - sequence message m16
        System.out.println("9. UI displays edit comment form");
        ui.DisplaysEditCommentForm();
        ui.displayEditCommentForm(feedback);
        System.out.println();

        // 10. User edits comment - sequence message m17
        System.out.println("10. User edits comment");
        ui.EditsComment();
        // (Simulated by preparing new comment)

        // 11. User submits edited form - sequence message m18
        System.out.println("11. User submits edited form");
        ui.SubmitsEditedForm();
        ui.editComment("F1", "This is an updated comment that is within length limit.");
        System.out.println();

        // 12. UI displays confirmation message - sequence message m29
        System.out.println("12. UI displays confirmation message");
        ui.DisplaysConfirmationMessage();
        // Called inside editComment already, but we call again for traceability
        OperationResult result = controller.updateFeedbackComment(new UpdateCommentCommand("F1", "Dummy"));
        ui.displayConfirmationMessage(result);
        System.out.println();

        // 13. Test validation error (comment too long) - sequence message m32
        System.out.println("13. Testing validation error (comment too long)");
        String longComment = "a".repeat(600); // Exceeds 500 characters
        ui.editComment("F1", longComment);
        ui.DisplaysErrorActivatesErroredUseCase();
        ui.displaysErrorActivatesErroredUseCase("Comment must not exceed 500 characters.");
        System.out.println();

        // 14. Test connection interruption
        System.out.println("14. Testing connection interruption");
        connectionManager.simulateInterruption();
        ui.editComment("F1", "Another comment");
        connectionManager.restoreConnection(); // Restore for further operations

        System.out.println("\n=== Use Case Completed ===");
    }
}
