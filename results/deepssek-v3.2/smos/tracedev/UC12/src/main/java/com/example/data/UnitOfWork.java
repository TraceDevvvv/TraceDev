package com.example.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of IUnitOfWork.
 * Manages a list of transactions/operations and coordinates with DataContext.
 * Corresponds to UnitOfWork in the class diagram.
 */
public class UnitOfWork implements IUnitOfWork {
    private List<Object> transactions; // List of pending operations/entities
    private DataContext dataContext;

    public UnitOfWork(DataContext dataContext) {
        this.dataContext = dataContext;
        this.transactions = new ArrayList<>();
    }

    public List<Object> getTransactions() {
        return new ArrayList<>(transactions);
    }

    @Override
    public void registerUpdate(Object entity) {
        // Register an entity that has been updated.
        transactions.add(entity);
        System.out.println("Registered update for entity: " + entity.getClass().getSimpleName());
    }

    @Override
    public boolean commit() {
        // Attempt to save all pending changes.
        // In a real implementation, this would involve a transaction boundary.
        System.out.println("UnitOfWork commit started.");
        boolean success = dataContext.saveChanges(transactions);
        if (success) {
            transactions.clear();
            System.out.println("UnitOfWork commit succeeded.");
        } else {
            System.out.println("UnitOfWork commit failed.");
        }
        return success;
    }

    @Override
    public void rollback() {
        // Rollback all pending changes.
        System.out.println("UnitOfWork rollback called.");
        dataContext.rollback();
        transactions.clear();
    }
}