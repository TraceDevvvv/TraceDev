package com.example.application.usecase;

import com.example.application.command.InsertJustificationCommand;
import com.example.application.result.JustificationResult;

/**
 * Use case interface for inserting a justification.
 */
public interface InsertJustificationUseCase {
    JustificationResult execute(InsertJustificationCommand command);
}