package com.system;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents the screen state that displays students and late entry status.
 */
public class ScreenState {
    private Date selectedDate;
    private List<Student> studentList;

    public ScreenState() {
        this.studentList = new ArrayList<>();
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    /**
     * Updates the student list on the screen.
     * @param studentList the new list of students.
     */
    public void update(List<Student> studentList) {
        this.studentList = studentList;
        display();
    }

    /**
     * Displays current screen state.
     */
    public void display() {
        System.out.println("ScreenState: Displaying for date " + selectedDate);
        for (Student s : studentList) {
            System.out.println("  Student: " + s.getName() + " (ID: " + s.getStudentId() + 
                               "), Late Entry: " + s.getHasLateEntry());
        }
    }

    /**
     * Notifies that a delay has been removed for a specific student.
     * Updates the screen to reflect the change.
     * @param studentId the ID of the student whose delay was removed.
     */
    public void notifyDelayRemoved(String studentId) {
        for (Student s : studentList) {
            if (s.getStudentId().equals(studentId)) {
                s.setHasLateEntry(false);
                break;
            }
        }
        System.out.println("ScreenState: Delay removed for student " + studentId);
        display();
    }
}