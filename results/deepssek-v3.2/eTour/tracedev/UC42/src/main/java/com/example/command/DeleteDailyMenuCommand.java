package com.example.command;

import com.example.domain.DayOfWeek;
import com.example.infrastructure.IDailyMenuRepository;
import com.example.infrastructure.ServiceUnavailableException;

/**
 * Concrete command for deleting a daily menu.
 */
public class DeleteDailyMenuCommand implements ICommand {
    private DayOfWeek targetDay;
    private IDailyMenuRepository repository;

    public DeleteDailyMenuCommand(DayOfWeek targetDay, IDailyMenuRepository repository) {
        this.targetDay = targetDay;
        this.repository = repository;
    }

    @Override
    public void execute() {
        try {
            repository.deleteByDay(targetDay);
        } catch (ServiceUnavailableException e) {
            throw e; // Re-throw for higher-level handling
        }
    }

    @Override
    public void undo() {
        // Undo not required for the current sequence, but implemented for completeness.
        // In a real scenario, we might need to restore the deleted menu (not implemented here).
        throw new UnsupportedOperationException("Undo is not supported for delete operation.");
    }

    /**
     * Executes DELETE FROM daily_menus WHERE day = ? via repository.
     * Corresponds to message m17 in sequence diagram.
     */
    public void executeDeleteFromDatabase() {
        // The actual deletion is performed in repository.deleteByDay()
        // This method is added for traceability of message m17.
        repository.deleteByDay(targetDay);
    }

    /**
     * Simulates the DELETE statement as per message m17 and m28.
     */
    public void deleteFromDailyMenus() {
        // This method is a direct mapping of the SQL message.
        // The actual implementation is in repository.
        System.out.println("DELETE FROM daily_menus WHERE day = " + targetDay);
    }
}