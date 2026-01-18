package com.example.usecase.interactor;

import com.example.domain.entity.Bookmark;
import com.example.domain.entity.Site;
import com.example.domain.entity.Tourist;
import com.example.usecase.command.InsertSiteCommand;
import com.example.usecase.interfaces.IBookmarkRepository;
import com.example.usecase.interfaces.IInsertSiteCommand;
import com.example.usecase.interfaces.ISiteRepository;
import com.example.usecase.interfaces.ITouristRepository;

import java.util.Date;
import java.util.UUID;

/**
 * Use Case Interactor: Application layer for inserting preference site
 */
public class InsertPreferenceSiteInteractor implements IInsertSiteCommand {
    private ISiteRepository siteRepository;
    private IBookmarkRepository bookmarkRepository;
    private ITouristRepository touristRepository;

    public InsertPreferenceSiteInteractor(ISiteRepository siteRepo, IBookmarkRepository bookmarkRepo, ITouristRepository touristRepo) {
        this.siteRepository = siteRepo;
        this.bookmarkRepository = bookmarkRepo;
        this.touristRepository = touristRepo;
    }

    @Override
    public void execute(InsertSiteCommand command) {
        // Step 1: Find tourist (requirement 4)
        Tourist tourist = touristRepository.findTouristById(command.getTouristId());
        if (tourist == null) {
            throw new IllegalArgumentException("Tourist not found: " + command.getTouristId());
        }

        // Step 2: Find site
        Site site = siteRepository.findSiteById(command.getSiteId());
        if (site == null) {
            throw new IllegalArgumentException("Site not found: " + command.getSiteId());
        }

        // Step 3: Create and save bookmark (requirement 9)
        Bookmark bookmark = createBookmark(command.getTouristId(), command.getSiteId());
        bookmarkRepository.saveBookmark(bookmark);
    }

    public Bookmark createBookmark(String touristId, String siteId) {
        // Generate a unique bookmark ID
        String bookmarkId = UUID.randomUUID().toString();
        Date addedDate = new Date(); // current date
        
        return new Bookmark(bookmarkId, touristId, siteId, addedDate);
    }
}