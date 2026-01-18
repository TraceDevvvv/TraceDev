package com.example;

/**
 * Represents image data including content and metadata.
 */
public class ImageData {
    private String fileName;
    private String mimeType;
    private long size;
    private byte[] content;

    public ImageData(String fileName, String mimeType, long size, byte[] content) {
        this.fileName = fileName;
        this.mimeType = mimeType;
        this.size = size;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public long getSize() {
        return size;
    }

    public byte[] getContent() {
        return content;
    }

    /**
     * Validates the image data.
     * @return ValidationResult indicating if the image is valid.
     */
    public ValidationResult validate() {
        // Actual validation should be delegated to validators
        // This is a placeholder that always returns valid
        // The real validation is performed by ImageValidator implementations
        return new ValidationResult(true, null);
    }
}