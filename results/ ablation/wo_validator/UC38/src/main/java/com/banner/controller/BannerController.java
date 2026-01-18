
package com.banner.controller;

import com.banner.command.AddBannerCommand;
import com.banner.handler.AddBannerCommandHandler;
import com.banner.model.AddBannerRequest;
import com.banner.model.Result;

/**
 * Controller for banner operations.
 */
public class BannerController {
    private AddBannerCommandHandler addBannerCommandHandler;

    public BannerController(AddBannerCommandHandler addBannerCommandHandler) {
        this.addBannerCommandHandler = addBannerCommandHandler;
    }

    public Result addBanner(AddBannerRequest request) {
        // Convert MultipartFile to ImageFile (simplified).
        // In reality, we'd need to extract dimensions and data from the MultipartFile.
        // We assume AddBannerRequest already provides an ImageFile for simplicity.
        // So we use request.getImageFile() as ImageFile directly.
        AddBannerCommand command = new AddBannerCommand(
                request.getPointOfRestaurantId(),
                request.getImageFile());

        Result result = addBannerCommandHandler.handle(command);

        // If result is success and message indicates confirmation needed,
        // we would ask for confirmation. We assume that's handled by frontend.
        // So we just return the result.
        return result;
    }

    // Additional endpoints for confirmation and cancellation as per sequence diagram.
    // Since sequence diagram shows confirmOperation() and cancelOperation(),
    // we add them but they are not in the class diagram. We'll add for completeness.

    public Result confirmOperation() {
        // In reality, we would have stored pending command and now process it.
        // We'll simulate by creating a new command and handling.
        // This is a simplified implementation.
        return new Result(true, "Operation confirmed", null);
    }

    public Result cancelOperation() {
        return new Result(true, "Operation cancelled", null);
    }
}
