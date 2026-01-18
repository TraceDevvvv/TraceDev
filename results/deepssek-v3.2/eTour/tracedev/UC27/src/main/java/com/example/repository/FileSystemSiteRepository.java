package com.example.repository;

import com.example.domain.Site;
import com.example.service.IndexService;
import com.example.service.index.IndexEntry;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;

/**
 * File system implementation of SiteRepository.
 */
public class FileSystemSiteRepository implements SiteRepository {
    private IndexService indexService;

    /**
     * Constructs a FileSystemSiteRepository with the specified IndexService.
     * @param indexService the index service
     */
    public FileSystemSiteRepository(IndexService indexService) {
        this.indexService = indexService;
    }

    /**
     * Finds sites by criteria using the IndexService.
     * @param criteria the search criteria
     * @return list of sites matching the criteria
     * @throws RepositoryException if an error occurs (e.g., connection interruption)
     */
    @Override
    public List<Site> findByCriteria(String criteria) throws RepositoryException {
        try {
            // Search the index for entries matching the criteria
            List<IndexEntry> indexEntries = indexService.searchIndex(criteria);
            // Convert index entries to Site objects
            return convertToSites(indexEntries);
        } catch (Exception e) {
            // If there is a connection error or any other exception, throw RepositoryException
            throw new RepositoryException("Connection to server ETOUR interrupted", e);
        }
    }

    /**
     * Converts a list of IndexEntry to a list of Site.
     * @param entries the list of index entries
     * @return the list of sites
     */
    public List<Site> convertToSites(List<IndexEntry> entries) {
        List<Site> sites = new ArrayList<>();
        for (IndexEntry entry : entries) {
            // Extract name and lastModified from metadata. Assume defaults if not present.
            Map<String, String> metadata = entry.getMetadata();
            String name = metadata.getOrDefault("name", "Unnamed");
            String lastModifiedStr = metadata.getOrDefault("lastModified", null);
            Date lastModified = lastModifiedStr != null ? new Date(Long.parseLong(lastModifiedStr)) : new Date();
            // Create a Site object and add to list
            Site site = new Site(name, entry.getPath(), lastModified);
            sites.add(site);
        }
        return sites;
    }
}