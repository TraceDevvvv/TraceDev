package com.example.bookmark;

/**
 * Data Transfer Object for identifying a bookmark, primarily by site ID.
 * In this context, 'siteId' is assumed to be the unique identifier for the bookmark
 * for the purpose of initiating removal, as per the sequence diagram.
 */
public class BookmarkIdentifierDTO {
    public String siteId; // The ID of the site, used here to identify the bookmark for removal.

    /**
     * Constructs a new BookmarkIdentifierDTO.
     * @param siteId The identifier for the site (assumed to be bookmark ID for removal).
     */
    public BookmarkIdentifierDTO(String siteId) {
        this.siteId = siteId;
    }
}