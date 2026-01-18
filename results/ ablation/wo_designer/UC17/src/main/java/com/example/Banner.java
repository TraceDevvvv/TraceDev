package com.example;

public class Banner {
    private String bannerId;
    private String refreshmentPointId;
    private String imagePath;

    public Banner(String bannerId, String refreshmentPointId, String imagePath) {
        this.bannerId = bannerId;
        this.refreshmentPointId = refreshmentPointId;
        this.imagePath = imagePath;
    }

    public String getBannerId() {
        return bannerId;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}