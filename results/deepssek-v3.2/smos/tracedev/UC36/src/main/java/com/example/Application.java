
package com.example;

import com.example.application.handler.InsertJustificationCommandHandler;
import com.example.application.usecase.InsertJustificationUseCase;
import com.example.external.SMOSServer;
import com.example.infrastructure.persistence.AbsenceRepository;
import com.example.infrastructure.persistence.AbsenceRepositoryImpl;
import com.example.infrastructure.persistence.JustificationRepository;
import com.example.infrastructure.persistence.JustificationRepositoryImpl;
import com.example.infrastructure.transaction.TransactionManager;
import com.example.presentation.controller.JustificationController;

/**
 * Main application class with Spring Boot configuration.
 */
public class Application {
    public static void main(String[] args) {
        // SpringApplication.run(Application.class, args);
    }

    public SMOSServer smosServer() {
        return new SMOSServer();
    }

    public JustificationRepository justificationRepository(SMOSServer smosServer) {
        return new JustificationRepositoryImpl(smosServer);
    }

    public AbsenceRepository absenceRepository() {
        return new AbsenceRepositoryImpl();
    }

    public TransactionManager transactionManager() {
        return new TransactionManager();
    }

    public InsertJustificationUseCase insertJustificationUseCase(JustificationRepository justificationRepository,
                                                                 AbsenceRepository absenceRepository,
                                                                 TransactionManager transactionManager) {
        return new InsertJustificationCommandHandler(justificationRepository, absenceRepository, transactionManager);
    }

    public JustificationController justificationController(InsertJustificationUseCase useCase) {
        return new JustificationController(useCase);
    }
}
