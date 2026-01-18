package com.example.menu;

import java.util.Objects;

/**
 * REQ-R10: Command to encapsulate the action of deleting a daily menu for a specific day.
 * Implements the Command interface to allow for parameterizing requests, queuing, or logging.
 * This command directly interacts with the DailyMenuRepository to perform the deletion.
 */
public class DeleteDailyMenuCommand implements Command {
    private final DayOfWeek dayOfWeek;
    private final DailyMenuRepository dailyMenuRepository; // Added to allow command to execute deletion directly // REQ-R10

    /**
     * REQ-R10: Constructs a new DeleteDailyMenuCommand.
     *
     * @param dayOfWeek The day of the week for which the menu should be deleted.
     * @param dailyMenuRepository The repository responsible for persisting DailyMenu data.
     */
    public DeleteDailyMenuCommand(DayOfWeek dayOfWeek, DailyMenuRepository dailyMenuRepository) {
        this.dayOfWeek = Objects.requireNonNull(dayOfWeek, "DayOfWeek cannot be null");
        this.dailyMenuRepository = Objects.requireNonNull(dailyMenuRepository, "DailyMenuRepository cannot be null");
    }

    /**
     * Gets the day of the week associated with this command.
     * @return The DayOfWeek for which the menu is to be deleted.
     */
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * REQ-R10: Executes the command, orchestrating the deletion using the dailyMenuRepository.
     * This method now performs the actual deletion operation.
     *
     * @throws NetworkConnectionException if a network connection issue occurs during deletion.
     */
    @Override
    public void execute() throws NetworkConnectionException {
        // The command itself interacts with the repository to perform the deletion.
        System.out.println("[DeleteDailyMenuCommand] Executing deletion for " + dayOfWeek);
        dailyMenuRepository.delete(dayOfWeek); // Delegate deletion to the repository
        System.out.println("[DeleteDailyMenuCommand] Deletion for " + dayOfWeek + " completed by repository.");
    }
}