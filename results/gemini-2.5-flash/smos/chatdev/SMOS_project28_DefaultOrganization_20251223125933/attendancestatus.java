/**
 * Enumerates the possible attendance statuses for a student.
 */
package com.chatdev.register.model;
public enum AttendanceStatus {
    PRESENT("Present"),
    ABSENT("Absent"),
    LATE("Late"); // Lateness is a separate flag, but can also be a status
    private final String displayValue;
    /**
     * Constructs an AttendanceStatus enum with a display value.
     *
     * @param displayValue The string representation of the status for display.
     */
    AttendanceStatus(String displayValue) {
        this.displayValue = displayValue;
    }
    /**
     * Returns the display value of the attendance status.
     *
     * @return The display value.
     */
    public String getDisplayValue() {
        return displayValue;
    }
    /**
     * Returns the AttendanceStatus enum from its display value.
     *
     * @param text The display value string.
     * @return The corresponding AttendanceStatus, or null if not found.
     */
    public static AttendanceStatus fromString(String text) {
        for (AttendanceStatus b : AttendanceStatus.values()) {
            if (b.displayValue.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null; // Or throw IllegalArgumentException
    }
}