package com.example;

/**
 * Default implementation of the TransactionManager interface.
 * This is a mock implementation for demonstration purposes.
 */
public class DefaultTransactionManager implements TransactionManager {

    private Logger logger;
    private boolean inTransaction; // Tracks if a transaction is active

    /**
     * Constructs a DefaultTransactionManager with a given logger.
     *
     * @param logger The logger to use for transaction logging.
     */
    public DefaultTransactionManager(Logger logger) {
        this.logger = logger;
        this.inTransaction = false;
    }

    @Override
    public void beginTransaction() {
        if (inTransaction) {
            logger.warn("Attempted to begin a new transaction while one is already active.");
            return;
        }
        logger.info("Transaction started.");
        inTransaction = true;
    }

    @Override
    public void commit() {
        if (!inTransaction) {
            logger.warn("Attempted to commit, but no transaction is active.");
            return;
        }
        logger.info("Transaction committed successfully.");
        inTransaction = false;
    }

    @Override
    public void rollback() {
        if (!inTransaction) {
            logger.warn("Attempted to rollback, but no transaction is active.");
            return;
        }
        logger.warn("Transaction rolled back.");
        inTransaction = false;
    }
}