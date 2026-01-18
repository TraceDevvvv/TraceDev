package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Main class to run the system.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        DataSource dataSource = new DataSource();
        ErrorHandler errorHandler = new ErrorHandler();
        SMOSClient smosClient = new SMOSClient(errorHandler);
        UserRepository userRepository = new UserRepositoryImpl(dataSource, smosClient);
        UserValidator validator = new UserValidator();
        ViewUserDetailsUseCase viewUseCase = new ViewUserDetailsUseCase(userRepository);
        EditUserUseCase editUseCase = new EditUserUseCase(userRepository, validator, viewUseCase);
        AuthenticationService authService = new AuthenticationService();
        UserControllerImpl controller = new UserControllerImpl(editUseCase, authService, errorHandler);
        UserInterface ui = new UserInterface(controller);

        // New participants matching sequence diagram
        Administrator admin = new Administrator("admin1", "System Administrator");
        AuthService authServiceParticipant = new AuthService(authService);
        Controller controllerParticipant = new Controller(controller, authService);
        Validator validatorParticipant = new Validator(validator);
        UseCase useCaseParticipant = new UseCase(editUseCase);
        UI uiParticipant = new UI(ui);
        SMOS smosParticipant = new SMOS(smosClient, errorHandler);
        Repository repositoryParticipant = new Repository(userRepository);

        // Simulate administrator login (R-Entry-Admin) - message m1
        authServiceParticipant.loginAsAdministrator(admin);
        System.out.println("Administrator logged in: " + authService.isAdmin());
        // message m2
        System.out.println(authServiceParticipant.authenticationSuccessful());

        // Simulate viewing user details (Entry Condition) - message m3
        User user = viewUseCase.execute("user123");
        uiParticipant.displayUserDetails(user);

        // Simulate edit button click - message m4
        Map<String, Object> changes = new HashMap<>();
        changes.put("name", "New Name");
        changes.put("age", 30);
        uiParticipant.clicksEditButton("user123", changes);

        // Simulate interruption - message m25
        uiParticipant.cancelOperation();
        // message m27
        System.out.println(controllerParticipant.operationCancelled());
        // message m28
        uiParticipant.operationCancelled();

        // Additional simulation for other sequence messages
        EditUserRequest sampleRequest = new EditUserRequest("user123", changes);
        EditUserCommand cmd = controllerParticipant.constructEditUserCommandFromRequest(sampleRequest);
        ValidationResult syntax = validatorParticipant.validateInputSyntax(cmd.getEditUserDTO());
        ValidationResult business = validatorParticipant.validateBusinessRules(cmd.getEditUserDTO(), user);
        EditUserResult failedResult = useCaseParticipant.createFailedEditUserResult("test", java.util.Collections.singletonList("error"));
        EditUserResponse failedResponse = controllerParticipant.createFailedEditUserResponse("test", java.util.Collections.singletonList("error"));
        EditUserResult successResult = useCaseParticipant.createSuccessfulEditUserResult("success");
        EditUserResponse successResponse = controllerParticipant.createSuccessfulEditUserResponse("success");
        uiParticipant.displayDataErrorNotification(java.util.Collections.singletonList("data error"));
        uiParticipant.displayConnectionError("connection lost");
        uiParticipant.displaySuccessMessage("Operation succeeded");

        // Simulate SMOS messages
        try {
            smosClient.sendData(user);
        } catch (Exception e) {
            smosParticipant.connectionException(e);
        }
        System.out.println(smosParticipant.dataSentSuccessfully());
        System.out.println(repositoryParticipant.saveFailed());
    }
}