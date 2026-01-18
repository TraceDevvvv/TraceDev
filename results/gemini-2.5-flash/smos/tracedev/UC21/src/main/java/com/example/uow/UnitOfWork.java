package com.example.uow;

/**
 * Concrete implementation of the IUnitOfWork interface.
 * For this example, it simulates transaction management with print statements.
 * In a real application, this would interact with a database transaction manager.
 */
public class UnitOfWork implements IUnitOfWork {

    private boolean inTransaction = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void begin() {
        if (inTransaction) {
            System.out.println("UOW: Warning: Already in a transaction. Nested transactions are not supported by this simple UOW.");
            return;
        }
        System.out.println("UOW: Transaction started.");
        inTransaction = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commit() {
        if (!inTransaction) {
            System.out.println("UOW: Error: No transaction to commit.");
            return;
        }
        System.out.println("UOW: Transaction committed successfully.");
        inTransaction = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollback() {
        if (!inTransaction) {
            System.out.println("UOW: Error: No transaction to rollback.");
            return;
        }
        System.out.println("UOW: Transaction rolled back.");
        inTransaction = false;
    }
}