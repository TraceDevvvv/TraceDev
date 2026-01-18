package com.example.usecase.interfaces;

import com.example.domain.entity.Bookmark;

/**
 * Port: Interface for bookmark repository operations
 */
public interface IBookmarkRepository {
    Bookmark saveBookmark(Bookmark bookmark);
}