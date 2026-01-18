'''
Represents an academic year in the system.
Manages classes and courses for a specific academic period.
'''
package com.chatdev.reportcardsystem.model;
import java.util.ArrayList;
import java.util.List;
public class AcademicYear {
    private String yearId;
    private String displayName;
    private List<String> classes;
    public AcademicYear(String yearId, String displayName) {
        this.yearId = yearId;
        this.displayName = displayName;
        this.classes = new ArrayList<>();
    }
    public void addClass(String className) {
        if (!classes.contains(className)) {
            classes.add(className);
        }
    }
    public void removeClass(String className) {
        classes.remove(className);
    }
    public String getYearId() {
        return yearId;
    }
    public String getDisplayName() {
        return displayName;
    }
    public List<String> getClasses() {
        return new ArrayList<>(classes);
    }
    @Override
    public String toString() {
        return displayName;
    }
}