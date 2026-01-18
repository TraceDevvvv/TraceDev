package com.example.dto;

/**
 * Data Transfer Object for TeachingAssignment.
 */
public class TeachingAssignmentDTO {
    private String assignmentId;
    private TeachingDTO teaching;
    private boolean assigned;

    public TeachingAssignmentDTO() {}

    public TeachingAssignmentDTO(String assignmentId, TeachingDTO teaching, boolean assigned) {
        this.assignmentId = assignmentId;
        this.teaching = teaching;
        this.assigned = assigned;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public TeachingDTO getTeaching() {
        return teaching;
    }

    public void setTeaching(TeachingDTO teaching) {
        this.teaching = teaching;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}