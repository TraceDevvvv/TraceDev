package com.example;

import com.example.controller.FavoritesController;
import com.example.dto.BookmarkDTO;
import com.example.repository.BookmarkRepository;
import com.example.repository.BookmarkRepositoryImpl;
import com.example.service.UserAuthenticationService;
import com.example.usecase.ViewFavoritesUseCase;
import java.util.List;

/**
 * Main class to demonstrate the system.
 * Runs scenarios corresponding to sequence diagrams.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        BookmarkRepository repository = new BookmarkRepositoryImpl();
        ViewFavoritesUseCase useCase = new ViewFavoritesUseCase(repository);
        UserAuthenticationService authService = new UserAuthenticationService();
        FavoritesController controller = new FavoritesController(useCase, authService);
        
        System.out.println("=== Testing Main Flow (Authenticated with bookmarks) ===");
        List<BookmarkDTO> favorites1 = controller.getFavorites("user123");
        System.out.println("Favorites found: " + favorites1.size());
        for (BookmarkDTO dto : favorites1) {
            System.out.println("  - " + dto.getName() + ": " + dto.getUrl());
        }
        
        System.out.println("\n=== Testing Main Flow (Authenticated no bookmarks) ===");
        List<BookmarkDTO> favorites2 = controller.getFavorites("user456");
        System.out.println("Favorites found: " + favorites2.size());
        
        System.out.println("\n=== Testing Alternative Flow (Not Authenticated) ===");
        List<BookmarkDTO> favorites3 = controller.getFavorites("");
        System.out.println("Favorites found: " + favorites3.size());
        
        // Note: Connection interrupted flow would be triggered by throwing ConnectionException
        // in BookmarkRepositoryImpl.findByUserId() method
    }
}