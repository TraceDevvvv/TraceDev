package com.example.repository;

import com.example.dto.BookmarkDTO;
import com.example.entity.Bookmark;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of BookmarkRepository.
 * Creates Bookmark entities and converts them to DTOs.
 */
public class BookmarkRepositoryImpl implements BookmarkRepository {
    @Override
    public List<BookmarkDTO> findByUserId(String userId) {
        // Simulating database interaction - in real scenario would fetch from DB
        // This method corresponds to sequence diagram main flow (bookmarks found / empty)
        
        // Simulate connection interruption (for alternative flow)
        // Uncomment to simulate connection error:
        // if (Math.random() > 0.9) throw new ConnectionException("Connection to server ETOUR interrupted");
        
        // Simulate returning bookmarks based on user ID
        List<BookmarkDTO> bookmarks = new ArrayList<>();
        if ("user123".equals(userId)) {
            // Create some sample bookmarks
            Bookmark bookmark1 = new Bookmark("1", "Google", "https://google.com", new Date());
            Bookmark bookmark2 = new Bookmark("2", "GitHub", "https://github.com", new Date());
            
            // Convert entities to DTOs (as indicated by «creates» relationship)
            bookmarks.add(convertToDTO(bookmark1));
            bookmarks.add(convertToDTO(bookmark2));
        }
        // If no bookmarks, empty list is returned (handled in sequence diagram alt)
        return bookmarks;
    }
    
    /**
     * Converts a Bookmark entity to a BookmarkDTO.
     * @param bookmark the entity
     * @return the DTO
     */
    private BookmarkDTO convertToDTO(Bookmark bookmark) {
        BookmarkDTO dto = new BookmarkDTO();
        dto.setId(bookmark.getId());
        dto.setName(bookmark.getName());
        dto.setUrl(bookmark.getUrl());
        dto.setCreatedDate(bookmark.getCreatedDate());
        return dto;
    }
}