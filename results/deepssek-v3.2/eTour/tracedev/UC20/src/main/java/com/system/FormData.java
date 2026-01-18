package com.system;

/**
 * Data transfer object for the banner insertion form.
 */
public class FormData {
    private boolean validImage;
    private String pointName;
    private int currentBannerCount;
    private int maxBanners;

    public FormData() {}

    public boolean isValidImage() { return validImage; }
    public void setValidImage(boolean validImage) { this.validImage = validImage; }
    public String getPointName() { return pointName; }
    public void setPointName(String pointName) { this.pointName = pointName; }
    public int getCurrentBannerCount() { return currentBannerCount; }
    public void setCurrentBannerCount(int currentBannerCount) { this.currentBannerCount = currentBannerCount; }
    public int getMaxBanners() { return maxBanners; }
    public void setMaxBanners(int maxBanners) { this.maxBanners = maxBanners; }
}