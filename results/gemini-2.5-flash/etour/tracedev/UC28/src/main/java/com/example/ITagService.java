package com.example;

import java.util.List;

/**
 * Defines the contract for business logic operations related to Tag entities.
 * This is part of the Service Layer (Service Layer Pattern).
 */
public interface ITagService {

    /**
     * Retrieves a list of all existing tags.
     *
     * @return A list of Tag objects.
     * @throws ConnectionException if an underlying data access operation fails due to a connection issue.
     */
    List<Tag> getExistingTags() throws ConnectionException;

    /**
     * Deletes a list of tags identified by their IDs.
     *
     * @param tagIds A list of String IDs of the tags to be deleted.
     * @throws ConnectionException if an underlying data access operation fails due to a connection issue.
     */
    void deleteTags(List<String> tagIds) throws ConnectionException;
}