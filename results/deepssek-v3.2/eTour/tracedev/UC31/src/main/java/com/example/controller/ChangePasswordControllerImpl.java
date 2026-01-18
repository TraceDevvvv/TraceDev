package com.example.controller;

import com.example.service.ChangePasswordService;
import com.example.dto.ChangePasswordRequest;
import com.example.dto.ChangePasswordResult;
import com.example.exception.NetworkException;

/**
 * Implementation of the change password controller.
 */
public class ChangePasswordControllerImpl implements IChangePasswordController {
    private ChangePasswordService changePasswordService;

    public ChangePasswordControllerImpl(ChangePasswordService changePasswordService) {
        this.changePasswordService = changePasswordService;
    }

    @Override
    public ChangePasswordResult handleChangePassword(ChangePasswordRequest request) {
        try {
            return changePasswordService.changePassword(request);
        } catch (NetworkException e) {
            // Handle network exception (exit condition: connection loss)
            return new ChangePasswordResult(false, "Connection error: " + e.getDetails());
        } catch (Exception e) {
            return new ChangePasswordResult(false, "Unexpected error: " + e.getMessage());
        }
    }
}