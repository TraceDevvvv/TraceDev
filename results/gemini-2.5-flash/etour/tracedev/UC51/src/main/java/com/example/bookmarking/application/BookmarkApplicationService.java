package com.example.bookmarking.application;

import com.example.bookmarking.domain.Bookmark;
import com.example.bookmarking.infrastructure.BookmarkRepository;
import com.example.bookmarking.infrastructure.NotificationService;

/**
 * Application Service for managing bookmark-related operations.
 * It orchestrates domain objects and infrastructure serv to fulfill use cases.
 */
public class BookmarkApplicationService {
    /** Repository for Bookmark persistence. */
    private BookmarkRepository bookmarkRepository;
    /** Service for sending notifications. */
    private NotificationService notificationService;

    /**
     * Constructs a BookmarkApplicationService with necessary dependencies.
     *
     * @param bookmarkRepository The repository for bookmark persistence.
     * @param notificationService The service for sending notifications.
     */
    public BookmarkApplicationService(BookmarkRepository bookmarkRepository, NotificationService notificationService) {
        this.bookmarkRepository = bookmarkRepository;
        this.notificationService = notificationService;
    }

    /**
     * Adds a new bookmark for a specific site by a tourist.
     * This method orchestrates the creation of a Bookmark domain object,
     * its persistence, and sends a notification.
     *
     * @param siteId The unique identifier of the site to be bookmarked.
     * @param touristId The unique identifier of the tourist creating the bookmark.
     * @return The newly created and persisted Bookmark object.
     */
    public Bookmark addBookmark(String siteId, String touristId) {
        System.out.println("AppService: Receiving request to add bookmark for site ID: " + siteId + ", tourist ID: " + touristId);

        // 1. Create a new Bookmark instance in the Domain Layer.
        Bookmark newBookmark = new Bookmark(siteId, touristId);
        System.out.println("AppService: Created new Bookmark domain object: " + newBookmark.id);

        // 2. Persist the bookmark using the repository.
        // This implicitly calls ETOURBookmarkRepository.save() via polymorphism.
        Bookmark savedBookmark = bookmarkRepository.save(newBookmark);
        System.out.println("AppService: Bookmark " + savedBookmark.id + " persisted.");

        // 3. Send a success notification.
        notificationService.sendNotification("Site '" + siteId + "' added to favorites successfully for tourist '" + touristId + "'.");
        System.out.println("AppService: Notification sent for bookmark addition.");

        return savedBookmark;
    }
}