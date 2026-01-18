package com.example.dto;

/**
 * Data Transfer Object for Teaching.
 */
public class TeachingDTO {
    private String teachingId;
    private String teachingCode;
    private String teachingName;

    public TeachingDTO() {}

    public TeachingDTO(String teachingId, String teachingCode, String teachingName) {
        this.teachingId = teachingId;
        this.teachingCode = teachingCode;
        this.teachingName = teachingName;
    }

    public String getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(String teachingId) {
        this.teachingId = teachingId;
    }

    public String getTeachingCode() {
        return teachingCode;
    }

    public void setTeachingCode(String teachingCode) {
        this.teachingCode = teachingCode;
    }

    public String getTeachingName() {
        return teachingName;
    }

    public void setTeachingName(String teachingName) {
        this.teachingName = teachingName;
    }
}