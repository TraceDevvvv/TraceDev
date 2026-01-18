package com.example.application;

import com.example.domain.EditTeachingCommand;
import com.example.domain.EditTeachingResult;

/**
 * Use case interface for editing a teaching.
 */
public interface EditTeachingUseCase {
    EditTeachingResult execute(EditTeachingCommand command);
}