package com.example.application;

/**
 * Use case interface for deleting a banner.
 */
public interface DeleteBannerUseCase {
    DeleteBannerResult execute(DeleteBannerCommand command);
}