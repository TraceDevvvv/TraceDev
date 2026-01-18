package com.example.infrastructure;

/**
 * Value object representing image data.
 */
public class ImageData {
    private String fileName;
    private long fileSize;
    private String mimeType;
    private byte[] binaryData;
    
    public ImageData(String fileName, long fileSize, String mimeType, byte[] binaryData) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.mimeType = mimeType;
        this.binaryData = binaryData;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public long getFileSize() {
        return fileSize;
    }
    
    public String getMimeType() {
        return mimeType;
    }
    
    public byte[] getBinaryData() {
        return binaryData;
    }
}