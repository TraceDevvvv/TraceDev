package com.example.teachingapp.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a teaching entity with its details.
 * This class holds all the necessary information about a specific teaching
 * that an administrator might want to view.
 */
public class Teaching {

    private String teachingId;
    private String title;
    private String description;
    private String instructorName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private int capacity;
    private int enrolledStudentsCount; // Number of students currently enrolled

    /**
     * Constructs a new Teaching instance.
     *
     * @param teachingId The unique identifier for the teaching.
     * @param title The title or name of the teaching.
     * @param description A detailed description of the teaching.
     * @param instructorName The name of the instructor teaching this course.
     * @param startDate The start date of the teaching.
     * @param endDate The end date of the teaching.
     * @param location The physical or virtual location where the teaching takes place.
     * @param capacity The maximum number of students that can enroll.
     * @param enrolledStudentsCount The current number of students enrolled.
     */
    public Teaching(String teachingId, String title, String description, String instructorName,
                    LocalDate startDate, LocalDate endDate, String location, int capacity,
                    int enrolledStudentsCount) {
        this.teachingId = teachingId;
        this.title = title;
        this.description = description;
        this.instructorName = instructorName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.capacity = capacity;
        this.enrolledStudentsCount = enrolledStudentsCount;
    }

    /**
     * Gets the unique identifier for the teaching.
     * @return The teaching ID.
     */
    public String getTeachingId() {
        return teachingId;
    }

    /**
     * Gets the title of the teaching.
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the description of the teaching.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the name of the instructor.
     * @return The instructor's name.
     */
    public String getInstructorName() {
        return instructorName;
    }

    /**
     * Gets the start date of the teaching.
     * @return The start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date of the teaching.
     * @return The end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Gets the location of the teaching.
     * @return The location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the maximum capacity for the teaching.
     * @return The capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the current count of enrolled students.
     * @return The number of enrolled students.
     */
    public int getEnrolledStudentsCount() {
        return enrolledStudentsCount;
    }

    /**
     * Provides a string representation of the Teaching object,
     * useful for displaying its details.
     * @return A formatted string containing all teaching details.
     */
    @Override
    public String toString() {
        return "Teaching Details:\n" +
               "  ID: " + teachingId + "\n" +
               "  Title: " + title + "\n" +
               "  Description: " + description + "\n" +
               "  Instructor: " + instructorName + "\n" +
               "  Start Date: " + startDate + "\n" +
               "  End Date: " + endDate + "\n" +
               "  Location: " + location + "\n" +
               "  Capacity: " + capacity + "\n" +
               "  Enrolled: " + enrolledStudentsCount + "\n" +
               "  Available Slots: " + (capacity - enrolledStudentsCount);
    }

    /**
     * Compares this Teaching object to the specified object. The result is true if and only if
     * the argument is not null and is a Teaching object that has the same teachingId as this object.
     * @param o The object to compare this Teaching against.
     * @return true if the given object represents a Teaching equivalent to this teaching, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teaching teaching = (Teaching) o;
        return Objects.equals(teachingId, teaching.teachingId);
    }

    /**
     * Returns a hash code value for the object.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(teachingId);
    }
}