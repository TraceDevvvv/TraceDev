package com.example.reportcardeditor;

/**
 * Represents a single subject with its name and the grade obtained by a student.
 * This class is immutable once created.
 */
public class Subject {
    private final String name;
    private final double grade;

    /**
     * Constructs a new Subject instance.
     *
     * @param name The name of the subject (e.g., "Mathematics", "Physics").
     * @param grade The grade obtained in the subject. Must be between 0.0 and 100.0.
     * @throws IllegalArgumentException if the grade is out of the valid range.
     */
    public Subject(String name, double grade) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject name cannot be null or empty.");
        }
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Grade must be between 0.0 and 100.0.");
        }
        this.name = name;
        this.grade = grade;
    }

    /**
     * Returns the name of the subject.
     *
     * @return The subject name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the grade obtained in the subject.
     *
     * @return The subject grade.
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Provides a string representation of the Subject object.
     *
     * @return A string in the format "SubjectName: Grade".
     */
    @Override
    public String toString() {
        return String.format("%s: %.2f", name, grade);
    }

    /**
     * Compares this Subject to the specified object. The result is true if and only if
     * the argument is not null and is a Subject object that has the same name (case-insensitive)
     * and grade as this object.
     *
     * @param o The object to compare this Subject against.
     * @return true if the given object represents a Subject equivalent to this Subject, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (Double.compare(subject.grade, grade) != 0) return false;
        return name.equalsIgnoreCase(subject.name);
    }

    /**
     * Returns a hash code for this Subject.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(grade);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}