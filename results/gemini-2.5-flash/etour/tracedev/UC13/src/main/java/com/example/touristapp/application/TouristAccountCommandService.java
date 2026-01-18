package com.example.touristapp.application;

import com.example.touristapp.domain.AccountStatus;
import com.example.touristapp.domain.TouristAccount;
import com.example.touristapp.infrastructure.AuditLogService;
import com.example.touristapp.infrastructure.ITouristAccountRepository;
import com.example.touristapp.infrastructure.RepositoryException;

/**
 * Service responsible for handling commands related to TouristAccount state changes.
 * This class orchestrates domain actions and infrastructure interactions (persistence, logging).
 */
public class TouristAccountCommandService {
    private final ITouristAccountRepository touristAccountRepository;
    private final AuditLogService auditLogService;

    /**
     * Constructs a TouristAccountCommandService.
     * @param repo The repository to interact with TouristAccount data.
     * @param logService The service to log audit events.
     */
    public TouristAccountCommandService(ITouristAccountRepository repo, AuditLogService logService) {
        if (repo == null) {
            throw new IllegalArgumentException("TouristAccountRepository cannot be null.");
        }
        if (logService == null) {
            throw new IllegalArgumentException("AuditLogService cannot be null.");
        }
        this.touristAccountRepository = repo;
        this.auditLogService = logService;
    }

    /**
     * Handles the EnableTouristAccountCommand.
     * Fetches the account, enables it, saves the changes, and logs the event.
     * @param command The command containing account and operator IDs.
     * @throws RepositoryException if there's an issue with data persistence (e.g., ETOUR server connection).
     * @throws IllegalArgumentException if the account is not found.
     */
    public void handle(EnableTouristAccountCommand command) throws RepositoryException {
        System.out.println("[Service] Handling EnableTouristAccountCommand for account: " + command.getAccountId());
        
        TouristAccount touristAccount = touristAccountRepository.findById(command.getAccountId());
        if (touristAccount == null) {
            throw new IllegalArgumentException("Tourist account with ID " + command.getAccountId() + " not found.");
        }

        AccountStatus oldStatus = touristAccount.getStatus();
        touristAccount.enable();
        AccountStatus newStatus = touristAccount.getStatus();

        if (oldStatus != newStatus) { // Only save and log if status actually changed
            touristAccountRepository.save(touristAccount);
            auditLogService.logEvent(
                "Account Status Changed",
                command.getAccountId(),
                command.getOperatorId(),
                newStatus
            );
        } else {
            System.out.println("[Service] Account " + command.getAccountId() + " was already enabled. No state change, no save or audit log.");
        }
        System.out.println("[Service] Successfully handled EnableTouristAccountCommand for account: " + command.getAccountId());
    }

    /**
     * Handles the DisableTouristAccountCommand.
     * Fetches the account, disables it, saves the changes, and logs the event.
     * @param command The command containing account and operator IDs.
     * @throws RepositoryException if there's an issue with data persistence (e.g., ETOUR server connection).
     * @throws IllegalArgumentException if the account is not found.
     */
    public void handle(DisableTouristAccountCommand command) throws RepositoryException {
        System.out.println("[Service] Handling DisableTouristAccountCommand for account: " + command.getAccountId());
        
        TouristAccount touristAccount = touristAccountRepository.findById(command.getAccountId());
        if (touristAccount == null) {
            throw new IllegalArgumentException("Tourist account with ID " + command.getAccountId() + " not found.");
        }

        AccountStatus oldStatus = touristAccount.getStatus();
        touristAccount.disable();
        AccountStatus newStatus = touristAccount.getStatus();
        
        if (oldStatus != newStatus) { // Only save and log if status actually changed
            touristAccountRepository.save(touristAccount);
            auditLogService.logEvent(
                "Account Status Changed",
                command.getAccountId(),
                command.getOperatorId(),
                newStatus
            );
        } else {
            System.out.println("[Service] Account " + command.getAccountId() + " was already disabled. No state change, no save or audit log.");
        }
        System.out.println("[Service] Successfully handled DisableTouristAccountCommand for account: " + command.getAccountId());
    }

    /**
     * Retrieves the details of a specific tourist account.
     * This method is added to fulfill the class diagram specification,
     * even though query operations are typically handled by a separate QueryService in CQRS.
     * @param accountId The ID of the account to retrieve.
     * @return The TouristAccount object if found.
     * @throws RepositoryException if there's an issue with data retrieval (e.g., ETOUR server connection).
     * @throws IllegalArgumentException if the account is not found.
     */
    public TouristAccount getAccountDetails(String accountId) throws RepositoryException {
        System.out.println("[CommandService] Fetching account details for ID: " + accountId);
        TouristAccount account = touristAccountRepository.findById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Tourist account with ID " + accountId + " not found.");
        }
        return account;
    }
}