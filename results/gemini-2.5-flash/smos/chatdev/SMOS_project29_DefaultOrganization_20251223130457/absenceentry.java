'''
Represents an absence or delay record for a student on a specific date.
'''
package com.chatdev.absencetracker.model;
import java.time.LocalDate;
import java.util.Objects;
public class AbsenceEntry {
    private String studentId;
    private LocalDate date;
    private boolean isAbsent;
    private boolean isDelayed;
    '''
    Constructs a new AbsenceEntry object.
    @param studentId The ID of the student this entry is for.
    @param date The date of the absence/delay.
    @param isAbsent True if the student is marked absent, false otherwise.
    @param isDelayed True if the student is marked delayed, false otherwise.
    '''
    public AbsenceEntry(String studentId, LocalDate date, boolean isAbsent, boolean isDelayed) {
        this.studentId = studentId;
        this.date = date;
        this.isAbsent = isAbsent;
        this.isDelayed = isDelayed;
    }
    '''
    Returns the ID of the student associated with this entry.
    @return The student's ID.
    '''
    public String getStudentId() {
        return studentId;
    }
    '''
    Returns the date of this absence/delay entry.
    @return The date of the entry.
    '''
    public LocalDate getDate() {
        return date;
    }
    '''
    Checks if the student was marked absent for this entry.
    @return true if absent, false otherwise.
    '''
    public boolean isAbsent() {
        return isAbsent;
    }
    '''
    Checks if the student was marked delayed for this entry.
    @return true if delayed, false otherwise.
    '''
    public boolean isDelayed() {
        return isDelayed;
    }
    '''
    Sets the absence status for this entry.
    @param absent New absence status.
    '''
    public void setAbsent(boolean absent) {
        isAbsent = absent;
    }
    '''
    Sets the delay status for this entry.
    @param delayed New delay status.
    '''
    public void setDelayed(boolean delayed) {
        isDelayed = delayed;
    }
    '''
    Compares this AbsenceEntry object to another object for equality.
    Equality is determined by the student ID and the date.
    @param o The object to compare with.
    @return true if the objects are equal, false otherwise.
    '''
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbsenceEntry that = (AbsenceEntry) o;
        return studentId.equals(that.studentId) && date.equals(that.date);
    }
    '''
    Returns a hash code value for the object.
    Based on the student ID and date.
    @return A hash code value for this object.
    '''
    @Override
    public int hashCode() {
        return Objects.hash(studentId, date);
    }
    '''
    Returns a string representation of the AbsenceEntry object.
    @return A string containing the student ID, date, and status.
    '''
    @Override
    public String toString() {
        return "AbsenceEntry{" +
               "studentId='" + studentId + '\'' +
               ", date=" + date +
               ", isAbsent=" + isAbsent +
               ", isDelayed=" + isDelayed +
               '}';
    }
}