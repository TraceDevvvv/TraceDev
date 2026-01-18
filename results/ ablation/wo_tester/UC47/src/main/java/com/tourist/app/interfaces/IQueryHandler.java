package com.tourist.app.interfaces;

/**
 * Generic interface for query handlers (CQRS pattern).
 * @param <TQuery> the type of query
 * @param <TResult> the type of result
 */
public interface IQueryHandler<TQuery, TResult> {
    /**
     * Handles the given query and returns a result.
     * @param query the query to handle
     * @return the query result
     */
    TResult Handle(TQuery query);
}