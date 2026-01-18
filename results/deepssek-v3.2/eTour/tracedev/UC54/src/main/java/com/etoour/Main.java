
package com.etoour;

import com.etoour.ui.EditFeedbackUI;
import com.etoour.controller.FeedbackEditController;
import com.etoour.model.Feedback;
import com.etoour.model.Tourist;
import com.etoour.repository.FeedbackRepositoryImpl;
import com.etoour.service.FeedbackService;
import com.etoour.utility.ConnectionManager;
import com.etoour.utility.ErrorHandler;
import com.etoour.utility.NotificationService;
import java.util.Date;

/**
 * Main class to demonstrate the flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        FeedbackRepositoryImpl repository = new FeedbackRepositoryImpl();
        NotificationService notifier = new NotificationService();
        ConnectionManager connectionManager = new ConnectionManager("http://etour.server");
        ErrorHandler errorHandler = new ErrorHandler(connectionManager);
        FeedbackService service = new FeedbackService(repository, notifier, errorHandler, connectionManager);
        FeedbackEditController controller = new FeedbackEditController(service, errorHandler);
        EditFeedbackUI ui = new EditFeedbackUI(controller);

        // Create a sample tourist
        Tourist tourist = new Tourist("user123", "JohnDoe", "john@example.com");

        // Create and store a sample feedback
        Feedback feedback = new Feedback("fb001", "site001", tourist.getUserId(),
                "Great experience!", 5, new Date(), new Date());
    }
}
