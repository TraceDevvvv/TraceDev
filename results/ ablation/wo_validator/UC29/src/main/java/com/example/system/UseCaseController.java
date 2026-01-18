package com.example.system;

import java.util.Stack;

/**
 * Singleton controller coordinating duplicate tag handling use case.
 */
public class UseCaseController extends BusinessLogicLayer {
    private static UseCaseController instance;
    private CommandHistory commandHistory;

    private UseCaseController() {
        this.commandHistory = new CommandHistory();
    }

    public static synchronized UseCaseController getInstance() {
        if (instance == null) {
            instance = new UseCaseController();
        }
        return instance;
    }

    /**
     * Coordinates the duplicate tag handling process.
     * @param searchTag the duplicate search tag
     */
    public void coordinateDuplicateTagHandling(String searchTag) {
        System.out.println("Controller: Handling duplicate tag - " + searchTag);
        Command recoveryCommand = createRecoveryCommand();
        recoveryCommand.execute();
        commandHistory.push(recoveryCommand);
        System.out.println("Controller: Recovery completed.");
    }

    @Override
    public void executeUseCase() {
        coordinateDuplicateTagHandling("default_tag");
    }

    /**
     * Creates a recovery command for rolling back state.
     * @return the recovery command
     */
    protected Command createRecoveryCommand() {
        SystemStateDAO stateDAO = new SystemStateDAO();
        return new RecoverPreviousStateCommand(stateDAO);
    }
}