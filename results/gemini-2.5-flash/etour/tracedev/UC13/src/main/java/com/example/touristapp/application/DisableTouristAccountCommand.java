package com.example.touristapp.application;

/**
 * Command to disable a tourist account.
 * Carries necessary information for the operation.
 */
public class DisableTouristAccountCommand implements ICommand {
    private final String accountId;
    private final String operatorId;

    /**
     * Constructs a DisableTouristAccountCommand.
     * @param accountId The ID of the account to disable.
     * @param operatorId The ID of the operator performing the action.
     */
    public DisableTouristAccountCommand(String accountId, String operatorId) {
        if (accountId == null || accountId.trim().isEmpty()) {
            throw new IllegalArgumentException("Account ID cannot be null or empty.");
        }
        if (operatorId == null || operatorId.trim().isEmpty()) {
            throw new IllegalArgumentException("Operator ID cannot be null or empty.");
        }
        this.accountId = accountId;
        this.operatorId = operatorId;
    }

    /**
     * Gets the ID of the account to be disabled.
     * @return The account ID.
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Gets the ID of the operator performing the action.
     * @return The operator ID.
     */
    public String getOperatorId() {
        return operatorId;
    }
}