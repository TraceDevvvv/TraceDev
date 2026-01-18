package com.tourist.app.interfaces;

import com.tourist.domain.Tourist;

/**
 * Tourist-specific repository interface.
 */
public interface ITouristRepository extends IRepository<Tourist, String> {
    // No additional methods; inherits GetById and Update
}