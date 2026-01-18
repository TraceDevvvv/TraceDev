package com.example.viewmodel;

import com.example.presenter.PasswordPresenter;

/**
 * ViewModel implementing PasswordPresenter interface.
 */
public class PasswordViewModel implements PasswordPresenter {
    private String errorMessage;
    private boolean isValid;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean valid) {
        this.isValid = valid;
    }

    @Override
    public void displayError(String message) {
        this.errorMessage = message;
        this.isValid = false;
    }

    @Override
    public void displaySuccess() {
        this.errorMessage = null;
        this.isValid = true;
    }
}