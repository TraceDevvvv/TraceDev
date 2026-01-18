package com.tourist.app.serv;

import com.tourist.app.interfaces.IAuthenticationService;

/**
 * Authentication service implementation.
 * Note: ITokenValidator is not defined in the UML, so we assume a simple implementation.
 */
public class AuthenticationService implements IAuthenticationService {
    private ITokenValidator tokenValidator;

    /**
     * Constructor.
     * @param validator the token validator
     */
    public AuthenticationService(ITokenValidator validator) {
        this.tokenValidator = validator;
    }

    @Override
    public boolean IsAuthenticated(String userId) {
        // Simplified authentication check.
        // In a real scenario, the tokenValidator would validate a token.
        return tokenValidator.isValid(userId);
    }

    @Override
    public String GetCurrentUser() {
        // Simplified: return a dummy user id.
        // In real implementation, this would extract user from current context.
        return "current-user-id";
    }
}