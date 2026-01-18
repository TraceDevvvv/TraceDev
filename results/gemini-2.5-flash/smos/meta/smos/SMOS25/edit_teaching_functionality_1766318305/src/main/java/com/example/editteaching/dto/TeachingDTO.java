package com.example.editteaching.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for Teaching entities.
 * This class is used to transfer teaching data between the client and the server,
 * specifically for creating or updating teaching records. It includes validation
 * annotations to ensure data integrity before processing.
 * It strictly adheres to the data structures and interfaces defined in the system design.
 */
public class TeachingDTO {

    @NotBlank(message = "Teaching name cannot be empty")
    private String name;

    @NotBlank(message = "Course code cannot be empty")
    private String courseCode;

    @NotBlank(message = "Instructor name cannot be empty")
    private String instructor;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date cannot be in the past")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @FutureOrPresent(message = "End date cannot be in the past")
    private LocalDate endDate;

    private String description;

    /**
     * Constructor to create a TeachingDTO object.
     *
     * @param name The name of the teaching.
     * @param courseCode The course code associated with the teaching.
     * @param instructor The instructor teaching the course.
     * @param startDate The start date of the teaching.
     * @param endDate The end date of the teaching.
     * @param description A detailed description of the teaching.
     */
    public TeachingDTO(String name, String courseCode, String instructor,
                       LocalDate startDate, LocalDate endDate, String description) {
        this.name = name;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    /**
     * Retrieves the name of the teaching.
     * @return The teaching's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the course code of the teaching.
     * @return The teaching's course code.
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Retrieves the instructor of the teaching.
     * @return The teaching's instructor.
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Retrieves the start date of the teaching.
     * @return The teaching's start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Retrieves the end date of the teaching.
     * @return The teaching's end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Retrieves the description of the teaching.
     * @return The teaching's description.
     */
    public String getDescription() {
        return description;
    }

    // Setters are typically not needed for DTOs used for input,
    // but can be added if the DTO is also used for output or internal manipulation.
    // For this use case, we assume the DTO is primarily for receiving input.
    // However, for Spring's @RequestBody to work, a default constructor and setters are often implicitly used
    // or Lombok's @Data/@Value can generate them.
    // Explicit setters are added here for completeness and flexibility.

    public void setName(String name) {
        this.name = name;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TeachingDTO{" +
               "name='" + name + '\'' +
               ", courseCode='" + courseCode + '\'' +
               ", instructor='" + instructor + '\'' +
               ", startDate=" + startDate +
               ", endDate=" + endDate +
               ", description='" + description + '\'' +
               '}';
    }
}