package com.example;

import java.util.List;

/**
 * Defines the contract for data access operations related to Tag entities.
 * This is part of the Data Access Layer (Repository Pattern).
 */
public interface ITagRepository {

    /**
     * Retrieves all existing tags from the data store.
     *
     * @return A list of all tags.
     * @throws ConnectionException if connection to the database fails.
     */
    List<Tag> findAll() throws ConnectionException;

    /**
     * Deletes a list of tags identified by their IDs from the data store.
     *
     * @param tagIds A list of tag IDs to be deleted.
     * @throws ConnectionException if connection to the database fails.
     */
    void deleteByIds(List<String> tagIds) throws ConnectionException;
}