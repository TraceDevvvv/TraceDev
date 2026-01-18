package com.example.application.repository;

import com.example.application.entity.TagEntity;

/**
 * Interface for Tag repository operations.
 */
public interface TagRepository {
    boolean exists(String tag);
    void save(TagEntity tag);
}