package com.example.command;

import com.example.domain.DayOfWeek;
import com.example.infrastructure.IDailyMenuRepository;

/**
 * Factory for creating commands related to daily menus.
 */
public class DailyMenuCommandFactory {
    private IDailyMenuRepository repository;

    public DailyMenuCommandFactory(IDailyMenuRepository repository) {
        this.repository = repository;
    }

    public ICommand createDeleteCommand(DayOfWeek day) {
        return new DeleteDailyMenuCommand(day, repository);
    }
}