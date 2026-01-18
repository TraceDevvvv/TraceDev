package com.example.repository;

import com.example.model.Account;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of AccountRepository.
 */
public class AccountRepositoryImpl implements AccountRepository {
    private Map<String, Account> store = new HashMap<>();

    @Override
    public Account save(Account account) {
        store.put(account.getAccountId(), account);
        System.out.println("Account saved: " + account.getAccountId());
        return account;
    }

    @Override
    public Account findById(String id) {
        return store.get(id);
    }
}