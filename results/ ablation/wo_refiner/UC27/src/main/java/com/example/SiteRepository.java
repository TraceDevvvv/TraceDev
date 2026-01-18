package com.example;

import java.util.List;

/**
 * Interface for site repository implementations.
 */
public interface SiteRepository {
    List<Site> search(SearchForm form) throws ConnectionException;
}