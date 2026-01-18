package com.example.application.usecase;

/**
 * Command object for deleting a class.
 */
public class DeleteClassCommand {
    private String classId;

    public DeleteClassCommand(String classId) {
        this.classId = classId;
    }

    public String getClassId() {
        return classId;
    }
}