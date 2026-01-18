package com.example.dto;

import java.io.File;

/**
 * Form data for image upload.
 */
public class ImageUploadForm {
    private File selectedFile;
    private String bannerId;

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }
}