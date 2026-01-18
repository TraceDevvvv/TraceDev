package com.system.ports;

import com.system.dto.ChangeRequest;
import com.system.dto.ChangeResponse;

/**
 * Input port for password change use case.
 */
public interface PasswordChangeInputPort {
    ChangeResponse handlePasswordChange(ChangeRequest request);
}