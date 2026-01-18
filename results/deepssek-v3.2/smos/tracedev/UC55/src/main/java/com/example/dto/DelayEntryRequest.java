package com.example.dto;

import com.example.dto.StudentDelayDTO;
import java.util.List;

/**
 * Request object for delay entry submission.
 * Contains all data needed to process delay entries.
 */
public class DelayEntryRequest {
    private String classId;
    private String staffId;
    private List<StudentDelayDTO> studentDelays;

    public DelayEntryRequest(String classId, String staffId, List<StudentDelayDTO> studentDelays) {
        this.classId = classId;
        this.staffId = staffId;
        this.studentDelays = studentDelays;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public List<StudentDelayDTO> getStudentDelays() {
        return studentDelays;
    }

    public void setStudentDelays(List<StudentDelayDTO> studentDelays) {
        this.studentDelays = studentDelays;
    }
}