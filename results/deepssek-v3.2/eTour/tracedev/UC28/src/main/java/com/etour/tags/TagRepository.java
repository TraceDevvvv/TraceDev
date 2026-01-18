package com.etour.tags;

import java.util.List;

/**
 * Repository interface for Tag entities.
 */
public interface TagRepository {
    List<Tag> findAll();
    void deleteById(String tagId);
    void deleteAllById(List<String> tagIds);
}