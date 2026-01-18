package com.example.delaysystem.model;

import java.time.Duration;

/**
 * Represents a student in the system.
 */
public class Student {
    private String studentId;
    private String name;
    private boolean hasDelay;
    private Duration delayTime;

    /**
     * Constructs a new Student object.
     *
     * @param studentId The unique identifier for the student.
     * @param name The name of the student.
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.hasDelay = false; // Initialize to no delay
        this.delayTime = null; // Initialize delay time to null
    }

    /**
     * Gets the unique identifier of the student.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the delay status for the student.
     *
     * @param status True if the student has a delay, false otherwise.
     */
    public void setHasDelay(boolean status) {
        this.hasDelay = status;
    }

    /**
     * Sets the duration of the student's delay.
     *
     * @param time The duration of the delay.
     */
    public void setDelayTime(Duration time) {
        this.delayTime = time;
    }

    /**
     * Gets the delay status of the student.
     *
     * @return True if the student has a delay, false otherwise.
     */
    public boolean getHasDelay() {
        return hasDelay;
    }

    /**
     * Gets the duration of the student's delay.
     *
     * @return The duration of the delay.
     */
    public Duration getDelayTime() {
        return delayTime;
    }

    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               ", hasDelay=" + hasDelay +
               ", delayTime=" + delayTime +
               '}';
    }
}