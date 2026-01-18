package com.etour.dto;

import com.etour.domain.Favorite;
import java.util.Date;

/**
 * Data Transfer Object for Favorite, used to transfer data between layers.
 */
public class FavoriteDTO {
    private String siteId;
    private String siteName;
    private Date addedDate;

    // Constructor from Favorite (as per class diagram)
    public FavoriteDTO(Favorite favorite) {
        this.siteId = favorite.getSiteId();
        this.siteName = favorite.getSiteName();
        this.addedDate = favorite.getAddedDate();
    }

    // Getters
    public String getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public Date getAddedDate() {
        return addedDate;
    }
}