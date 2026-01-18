package com.example.classregistry;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a single entry in a class register for a specific date.
 * It includes information about absences, disciplinary notes, delays, and justifications.
 */
public class RegisterEntry {
    private LocalDate date;
    private int absences;
    private String disciplinaryNotes;
    private int delays;
    private String justification;

    /**
     * Constructs a new RegisterEntry instance.
     *
     * @param date The date for this register entry.
     * @param absences The number of absences recorded for this date.
     * @param disciplinaryNotes Any disciplinary notes for this date. Can be empty.
     * @param delays The number of delays recorded for this date.
     * @param justification Any justification for absences or delays. Can be empty.
     */
    public RegisterEntry(LocalDate date, int absences, String disciplinaryNotes, int delays, String justification) {
        this.date = Objects.requireNonNull(date, "Date cannot be null");
        if (absences < 0) {
            throw new IllegalArgumentException("Absences cannot be negative.");
        }
        this.absences = absences;
        this.disciplinaryNotes = (disciplinaryNotes == null) ? "" : disciplinaryNotes;
        if (delays < 0) {
            throw new IllegalArgumentException("Delays cannot be negative.");
        }
        this.delays = delays;
        this.justification = (justification == null) ? "" : justification;
    }

    /**
     * Returns the date of this register entry.
     * @return The date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the number of absences for this date.
     * @return The number of absences.
     */
    public int getAbsences() {
        return absences;
    }

    /**
     * Returns the disciplinary notes for this date.
     * @return The disciplinary notes string. Will be an empty string if none.
     */
    public String getDisciplinaryNotes() {
        return disciplinaryNotes;
    }

    /**
     * Returns the number of delays for this date.
     * @return The number of delays.
     */
    public int getDelays() {
        return delays;
    }

    /**
     * Returns the justification for absences or delays for this date.
     * @return The justification string. Will be an empty string if none.
     */
    public String getJustification() {
        return justification;
    }

    /**
     * Compares this RegisterEntry to the specified object. The result is true if and only if
     * the argument is not null and is a RegisterEntry object that represents the same date.
     *
     * @param o The object to compare this RegisterEntry against.
     * @return true if the given object represents a RegisterEntry equivalent to this register entry, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterEntry that = (RegisterEntry) o;
        return date.equals(that.date);
    }

    /**
     * Returns a hash code for this RegisterEntry. The hash code is based on the date.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    /**
     * Returns a string representation of this RegisterEntry.
     *
     * @return A string containing the date, absences, disciplinary notes, delays, and justification.
     */
    @Override
    public String toString() {
        return "RegisterEntry{" +
               "date=" + date +
               ", absences=" + absences +
               ", disciplinaryNotes='" + disciplinaryNotes + '\'' +
               ", delays=" + delays +
               ", justification='" + justification + '\'' +
               '}';
    }
}