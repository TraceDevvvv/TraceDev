'''
Represents a grade for a specific subject.
Contains subject name, numerical score, and letter grade.
'''
package com.chatdev.reportcardsystem.data;
public class SubjectGrade {
    private String subjectName;
    private int score;
    private String grade;
    public SubjectGrade(String subjectName, int score, String grade) {
        this.subjectName = subjectName;
        this.score = score;
        this.grade = grade;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public int getScore() {
        return score;
    }
    public String getGrade() {
        return grade;
    }
    @Override
    public String toString() {
        return subjectName + ": " + score + " (" + grade + ")";
    }
}