
package com.example.delay;

import java.util.Date;

/**
 * Main class to demonstrate the runnable system.
 * Simulates the sequence diagram flows.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        SMOSServerGateway gateway = new SMOSServerGateway();
        DelayRepository repository = new DelayRepositoryImpl(gateway);
        ChangeDelayValidator validator = new ChangeDelayValidator();
        ChangeDelayCommandHandler handler = new ChangeDelayCommandHandler(repository, validator);
        DelayChangeController controller = new DelayChangeController(handler, repository);
        Administrator admin = new Administrator();

        // Simulate entry condition: prior use case performed
        SviewTetTingloregistration priorUseCase = new SviewTetTingloregistration();
        priorUseCase.perform();

        // Simulate main flow
        Date today = new Date();
        admin.login();
        admin.selectDate(today);
        controller.onDateSelected(today);

        // Admin edits delay
        DelayDTO dto = new DelayDTO(today, 30);
        admin.editDelay(dto);
        controller.handleEditRequest(dto);
        admin.clickSave();

        ChangeDelayCommand command = new ChangeDelayCommand(today, 30);
        controller.onSave(command);

        // Simulate interruption flow
        System.out.println("\n--- Simulating interruption flow ---");
        admin.interrupt();
        controller.interrupt();

        // Simulate connection error flow
        System.out.println("\n--- Simulating connection error flow ---");
    }
}
