package com.school.model;

/**
 * Domain entity representing a student.
 */
public class Student {
    private int _id;
    private String _name;
    private int _classId;

    public Student() {}

    public Student(int id, String name, int classId) {
        this._id = id;
        this._name = name;
        this._classId = classId;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public int getClassId() {
        return _classId;
    }

    public void setClassId(int classId) {
        this._classId = classId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _classId=" + _classId +
                '}';
    }
}