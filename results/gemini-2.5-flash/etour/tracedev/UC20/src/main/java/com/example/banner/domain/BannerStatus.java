package com.example.banner.domain;

/**
 * Enum representing the possible statuses of a banner.
 * Banners can be active, pending approval, archived, or marked as invalid.
 */
public enum BannerStatus {
    /** The banner is currently active and displayed. */
    ACTIVE,
    /** The banner is pending approval or further action before activation. */
    PENDING,
    /** The banner is no longer active but kept for historical purposes. */
    ARCHIVED,
    /** The banner is considered invalid due to content or other issues. */
    INVALID
}