package com.example.banner;

/**
 * Represents a banner displayed at a refreshment point.
 */
public class Banner {
    
    private int id;
    private byte[] imageData;
    private int refreshmentPointId;
    
    /**
     * Constructs a Banner with the given ID, image data, and refreshment point ID.
     * @param id the banner ID
     * @param imageData the image data as bytes
     * @param refreshmentPointId the associated refreshment point ID
     */
    public Banner(int id, byte[] imageData, int refreshmentPointId) {
        this.id = id;
        this.imageData = imageData.clone();
        this.refreshmentPointId = refreshmentPointId;
    }
    
    /**
     * Returns the banner ID.
     * @return the ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Returns the image data.
     * @return the image data bytes
     */
    public byte[] getImageData() {
        return imageData.clone();
    }
    
    /**
     * Sets new image data.
     * @param imageData the new image data
     */
    public void setImageData(byte[] imageData) {
        this.imageData = imageData.clone();
    }
    
    /**
     * Returns the refreshment point ID.
     * @return the refreshment point ID
     */
    public int getRefreshmentPointId() {
        return refreshmentPointId;
    }
    
    /**
     * Sets the refreshment point ID.
     * @param refreshmentPointId the new refreshment point ID
     */
    public void setRefreshmentPointId(int refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }
}