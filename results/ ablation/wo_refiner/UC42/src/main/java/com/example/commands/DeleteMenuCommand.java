package com.example.commands;

import com.example.enums.DayOfWeek;

/**
 * Command object representing a request to delete a menu.
 */
public class DeleteMenuCommand {
    public DayOfWeek day;
    public boolean confirmed;

    public DeleteMenuCommand(DayOfWeek day) {
        this.day = day;
        this.confirmed = false;
    }

    /**
     * Executes the command logic (though actual deletion is handled by handler).
     */
    public void execute() {
        // Execution logic is delegated to DeleteMenuCommandHandler
        System.out.println("DeleteMenuCommand for " + day + " ready to execute.");
    }

    /**
     * Cancels the pending deletion.
     */
    public void cancel() {
        this.confirmed = false;
        System.out.println("DeleteMenuCommand cancelled for " + day);
    }

    /**
     * Sets the confirmed flag.
     */
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    /**
     * Gets the confirmed flag.
     */
    public boolean getConfirmed() {
        return confirmed;
    }
}