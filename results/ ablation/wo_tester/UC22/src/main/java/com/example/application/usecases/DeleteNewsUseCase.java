package com.example.application.usecases;

import com.example.application.common.Result;

/**
 * Use case interface for deleting news.
 */
public interface DeleteNewsUseCase {
    Result execute(DeleteNewsCommand command);
}