package com.example.editteaching.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * JPA Entity representing a teaching record.
 * This class maps to a database table and holds the detailed information about a teaching.
 * It strictly adheres to the data structures and interfaces defined in the system design.
 */
@Entity
@Table(name = "teachings") // Explicitly define the table name for the entity
public class Teaching {

    @Id // Marks 'id' as the primary key for the entity
    @Column(name = "id", nullable = false, unique = true) // Defines column properties for 'id'
    private String id;

    @Column(name = "name", nullable = false) // Defines column properties for 'name'
    private String name;

    @Column(name = "course_code", nullable = false) // Defines column properties for 'courseCode'
    private String courseCode;

    @Column(name = "instructor", nullable = false) // Defines column properties for 'instructor'
    private String instructor;

    @Column(name = "start_date", nullable = false) // Defines column properties for 'startDate'
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false) // Defines column properties for 'endDate'
    private LocalDate endDate;

    @Column(name = "description") // Defines column properties for 'description', allowing it to be nullable
    private String description;

    /**
     * Default constructor required by JPA.
     * This constructor is used by JPA providers to instantiate entities when loading them from the database.
     */
    public Teaching() {
    }

    /**
     * Constructor to create a Teaching object with all specified details.
     * This constructor is used for creating or reconstructing Teaching instances.
     *
     * @param id The unique identifier for the teaching.
     * @param name The name of the teaching.
     * @param courseCode The course code associated with the teaching.
     * @param instructor The instructor teaching the course.
     * @param startDate The start date of the teaching.
     * @param endDate The end date of the teaching.
     * @param description A detailed description of the teaching.
     */
    public Teaching(String id, String name, String courseCode, String instructor,
                    LocalDate startDate, LocalDate endDate, String description) {
        this.id = id;
        this.name = name;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    /**
     * Retrieves the unique identifier of the teaching.
     * @return The teaching's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the teaching.
     * @param id The new ID for the teaching.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the teaching.
     * @return The teaching's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the teaching.
     * @param name The new name for the teaching.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the course code of the teaching.
     * @return The teaching's course code.
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Sets the course code of the teaching.
     * @param courseCode The new course code for the teaching.
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Retrieves the instructor of the teaching.
     * @return The teaching's instructor.
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Sets the instructor of the teaching.
     * @param instructor The new instructor for the teaching.
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Retrieves the start date of the teaching.
     * @return The teaching's start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the teaching.
     * @param startDate The new start date for the teaching.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieves the end date of the teaching.
     * @return The teaching's end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the teaching.
     * @param endDate The new end date for the teaching.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the description of the teaching.
     * @return The teaching's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the teaching.
     * @param description The new description for the teaching.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Teaching{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", courseCode='" + courseCode + '\'' +
               ", instructor='" + instructor + '\'' +
               ", startDate=" + startDate +
               ", endDate=" + endDate +
               ", description='" + description + '\'' +
               '}';
    }
}