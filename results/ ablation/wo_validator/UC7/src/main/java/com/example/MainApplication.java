
package com.example;

import com.example.adapters.*;
import com.example.domain.ConventionActivationInteractor;
import com.example.ports.*;
import com.example.ui.ActivationForm;
import com.example.ui.ConventionController;
import com.example.infrastructure.ConventionJpaRepository;
import com.example.infrastructure.ETOURServiceClient;
import com.example.infrastructure.EmailService;
import com.example.infrastructure.SMSService;
import com.example.infrastructure.SessionManager;

/**
 * Main application class to set up dependencies and run a sample scenario.
 * This simulates the sequence diagram flow.
 */
public class MainApplication {
    public static void main(String[] args) {
        // Setup adapters with their external dependencies (simplified with dummy implementations)
        ConventionJpaRepository jpaRepository = new ConventionJpaRepository();
        ConventionRepository conventionRepository = new ConventionRepositoryAdapter(jpaRepository);
        ETOURServiceClient etourClient = new ETOURServiceClient();
        RefreshmentPointRequestGateway refreshmentGateway = new RefreshmentPointRequestGatewayAdapter(etourClient);
        EmailService emailService = new EmailService();
        SMSService smsService = new SMSService();
        NotificationPort notificationPort = new NotificationAdapter(emailService, smsService);
        SessionManager sessionManager = new SessionManager();
        ServerConnectionPort serverConnectionPort = new ServerConnectionAdapter(sessionManager);

        // Setup interactor
        ConventionActivationInteractor interactor = new ConventionActivationInteractor(
                conventionRepository, refreshmentGateway, notificationPort, serverConnectionPort);

        // Setup UI layer
        ActivationForm form = new ActivationForm();
        ConventionController controller = new ConventionController(interactor, form);
    }
}
