package com.school.model;

/**
 * Domain entity representing a professor.
 */
public class Professor {
    private int _id;
    private String _name;
    private boolean _isLoggedIn;

    public Professor() {}

    public Professor(int id, String name, boolean isLoggedIn) {
        this._id = id;
        this._name = name;
        this._isLoggedIn = isLoggedIn;
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

    public boolean getIsLoggedIn() {
        return _isLoggedIn;
    }

    public void setIsLoggedIn(boolean status) {
        this._isLoggedIn = status;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _isLoggedIn=" + _isLoggedIn +
                '}';
    }
}