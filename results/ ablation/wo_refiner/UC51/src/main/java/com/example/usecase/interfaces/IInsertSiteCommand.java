package com.example.usecase.interfaces;

import com.example.usecase.command.InsertSiteCommand;

/**
 * Port: Interface for executing insert site commands
 */
public interface IInsertSiteCommand {
    void execute(InsertSiteCommand command);
}