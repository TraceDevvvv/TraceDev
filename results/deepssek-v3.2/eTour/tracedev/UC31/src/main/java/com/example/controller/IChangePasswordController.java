package com.example.controller;

import com.example.dto.ChangePasswordRequest;
import com.example.dto.ChangePasswordResult;

/**
 * Controller interface for handling change password requests.
 */
public interface IChangePasswordController {
    ChangePasswordResult handleChangePassword(ChangePasswordRequest request);
}