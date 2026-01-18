package com.example.delaysystem.model;

import java.time.Duration;

/**
 * Represents a single instance of a student's delay for a specific class.
 */
public class DelayData {
    private String studentId;
    private String classId;
    private Duration delayTime; // Using java.time.Duration for precise time representation

    /**
     * Constructs a new DelayData object.
     *
     * @param studentId The ID of the student who was delayed.
     * @param classId The ID of the class session for which the delay occurred.
     * @param delayTime The duration of the delay.
     */
    public DelayData(String studentId, String classId, Duration delayTime) {
        this.studentId = studentId;
        this.classId = classId;
        this.delayTime = delayTime;
    }

    /**
     * Gets the student ID associated with this delay.
     *
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the class ID associated with this delay.
     *
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Gets the duration of the delay.
     *
     * @return The delay time as a Duration object.
     */
    public Duration getDelayTime() {
        return delayTime;
    }

    @Override
    public String toString() {
        return "DelayData{" +
               "studentId='" + studentId + '\'' +
               ", classId='" + classId + '\'' +
               ", delayTime=" + delayTime +
               '}';
    }
}