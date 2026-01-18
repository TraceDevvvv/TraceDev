package com.etour.registration.ports;

import com.etour.registration.domain.Account;
import com.etour.registration.exception.PersistenceException;

/**
 * Port/Interface for account persistence operations.
 */
public interface AccountRepository {
    Account save(Account account) throws PersistenceException;
    boolean existsByUsername(String username) throws PersistenceException;
    boolean existsByEmail(String email) throws PersistenceException;
}