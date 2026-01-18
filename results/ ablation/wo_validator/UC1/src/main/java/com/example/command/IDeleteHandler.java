package com.example.command;

import com.example.dto.OperationResult;

/**
 * Interface for delete handlers
 */
public interface IDeleteHandler {
    OperationResult executeDelete(String id);
}