package com.system.repository;

import com.system.entities.Tag;
import com.system.exceptions.DatabaseException;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Tag entities (Repository Pattern).
 */
public interface ITagRepository {
    List<Tag> findAll();
    Optional<Tag> findById(String id);
    Tag save(Tag tag);
    void deleteById(String id) throws DatabaseException;
    void deleteAllByIds(List<String> ids) throws DatabaseException;
}