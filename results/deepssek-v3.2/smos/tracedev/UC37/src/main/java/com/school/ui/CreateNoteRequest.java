package com.school.ui;

import com.school.application.CreateDisciplinaryNoteCommand;

import java.util.Date;

/**
 * Request DTO implementing the command interface.
 */
public class CreateNoteRequest implements CreateDisciplinaryNoteCommand {
    private String studentId;
    private Date date;
    private String teacherId;
    private String description;

    public CreateNoteRequest(String studentId, Date date, String teacherId, String description) {
        this.studentId = studentId;
        this.date = date;
        this.teacherId = teacherId;
        this.description = description;
    }

    @Override
    public String getStudentId() {
        return studentId;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getTeacherId() {
        return teacherId;
    }

    @Override
    public String getDescription() {
        return description;
    }
}