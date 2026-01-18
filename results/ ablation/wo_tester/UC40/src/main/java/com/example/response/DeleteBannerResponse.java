package com.example.response;

import com.example.entity.Banner;
import java.util.List;
import java.util.Collections;

/**
 * Response object for delete banner operations.
 * Contains success status, message, deleted banner ID, and optionally a list of banners.
 */
public class DeleteBannerResponse {
    private boolean success;
    private String message;
    private String deletedBannerId;
    private List<Banner> banners;

    /**
     * Constructor for deletion responses.
     */
    public DeleteBannerResponse(boolean success, String message, String deletedBannerId) {
        this.success = success;
        this.message = message;
        this.deletedBannerId = deletedBannerId;
        this.banners = Collections.emptyList();
    }

    /**
     * Constructor for retrieval responses (includes banners list).
     */
    public DeleteBannerResponse(boolean success, String message, String deletedBannerId, List<Banner> banners) {
        this.success = success;
        this.message = message;
        this.deletedBannerId = deletedBannerId;
        this.banners = banners != null ? banners : Collections.emptyList();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getDeletedBannerId() {
        return deletedBannerId;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    @Override
    public String toString() {
        return "DeleteBannerResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", deletedBannerId='" + deletedBannerId + '\'' +
                ", bannersCount=" + banners.size() +
                '}';
    }
}