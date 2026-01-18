package com.example.tag;

import java.util.List;

/**
 * Repository interface for Tag entities.
 */
public interface TagRepository {
    List<Tag> findAll();
    List<Tag> searchTags(String criteria);
    void deleteById(Long id);
    boolean existsById(Long id);
}