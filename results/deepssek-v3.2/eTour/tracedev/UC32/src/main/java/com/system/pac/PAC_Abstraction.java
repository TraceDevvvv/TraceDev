package com.system.pac;

import com.system.adapter.PasswordChangeAdapter;
import com.system.dto.ChangeRequest;
import com.system.dto.ChangeResponse;

/**
 * Abstraction component in PAC pattern, encapsulates core business logic adapter.
 */
public class PAC_Abstraction {
    private PasswordChangeAdapter adapter;

    public PAC_Abstraction(PasswordChangeAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * Changes password using the adapter.
     */
    public ChangeResponse changePassword(ChangeRequest request) {
        return adapter.handlePasswordChange(request);
    }
}