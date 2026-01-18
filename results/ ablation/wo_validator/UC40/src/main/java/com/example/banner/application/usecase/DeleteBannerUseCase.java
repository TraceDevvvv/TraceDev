package com.example.banner.application.usecase;

import com.example.banner.application.command.DeleteBannerCommand;

/**
 * Use case interface for deleting a banner.
 */
public interface DeleteBannerUseCase {
    void execute(DeleteBannerCommand command);
}