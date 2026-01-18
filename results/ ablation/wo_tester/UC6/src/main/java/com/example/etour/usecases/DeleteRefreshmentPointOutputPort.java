package com.example.etour.usecases;

/**
 * Output port for the Delete Refreshment Point use case.
 */
public interface DeleteRefreshmentPointOutputPort {
    void presentSuccess(String message);
    void presentError(String error);
}