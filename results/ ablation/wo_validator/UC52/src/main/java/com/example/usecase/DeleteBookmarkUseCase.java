package com.example.usecase;

import com.example.dto.DeleteBookmarkCommand;
import com.example.dto.DeleteBookmarkResult;
import com.example.repository.BookmarkRepository;
import com.example.service.NotificationService;
import com.example.unitofwork.UnitOfWork;
import com.example.model.Bookmark;

/**
 * Use case for deleting a bookmark.
 */
public class DeleteBookmarkUseCase {
    private BookmarkRepository bookmarkRepository;
    private UnitOfWork unitOfWork;
    private NotificationService notificationService;

    public DeleteBookmarkUseCase(BookmarkRepository repository, UnitOfWork uow, NotificationService notificationService) {
        this.bookmarkRepository = repository;
        this.unitOfWork = uow;
        this.notificationService = notificationService;
    }

    public DeleteBookmarkResult execute(DeleteBookmarkCommand command) {
        // Check for cancellation token (Alternative Flow: Tourist Cancels)
        if ("CANCEL".equalsIgnoreCase(command.getConfirmationToken()) || 
            "cancel".equalsIgnoreCase(command.getConfirmationToken())) {
            return new DeleteBookmarkResult(false, "Operation cancelled");
        }

        try {
            // Begin transaction
            unitOfWork.begin();
            
            // Find bookmark
            Bookmark bookmark = bookmarkRepository.findBookmarkByUserAndSite(
                command.getUserId(), 
                command.getSiteId()
            );
            
            // Validate bookmark exists
            if (bookmark == null) {
                unitOfWork.rollback();
                return new DeleteBookmarkResult(false, "Bookmark not found");
            }
            
            // Send removal notification
            notificationService.sendRemovalNotification(command.getUserId(), command.getSiteId());
            
            // Delete bookmark
            bookmarkRepository.deleteBookmark(bookmark);
            
            // Commit transaction
            unitOfWork.commit();
            
            return new DeleteBookmarkResult(true, "Bookmark deleted");
            
        } catch (Exception e) {
            // Alternative Flow: Server Interruption
            try {
                unitOfWork.rollback();
            } catch (Exception rollbackEx) {
                // Log rollback exception
            }
            return new DeleteBookmarkResult(false, "Server interrupted: " + e.getMessage());
        }
    }
}