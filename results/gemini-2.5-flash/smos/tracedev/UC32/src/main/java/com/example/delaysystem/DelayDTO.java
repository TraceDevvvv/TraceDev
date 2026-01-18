package com.example.delaysystem;

import java.util.Date;
import java.util.Objects;

/**
 * Data Transfer Object for Delay information.
 * Used for transferring delay data between layers.
 */
public class DelayDTO {
    public String id;
    public Date date;
    public int durationMinutes;
    public String reason;

    /**
     * Default constructor.
     */
    public DelayDTO() {
    }

    /**
     * Constructor with all fields.
     */
    public DelayDTO(String id, Date date, int durationMinutes, String reason) {
        this.id = id;
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.reason = reason;
    }

    /**
     * Converts this DTO to a Delay domain object.
     *
     * @return A new Delay object populated with data from this DTO.
     */
    public Delay toDelay() {
        // Assume simple field-to-field mapping.
        return new Delay(this.id, this.date, this.durationMinutes, this.reason);
    }

    /**
     * Populates this DTO with data from a Delay domain object.
     *
     * @param delay The Delay object to convert from.
     */
    public void fromDelay(Delay delay) {
        // Assume simple field-to-field mapping.
        if (delay != null) {
            this.id = delay.getId();
            this.date = delay.getDate();
            this.durationMinutes = delay.getDuration();
            this.reason = delay.getReason();
        }
    }

    @Override
    public String toString() {
        return "DelayDTO{" +
               "id='" + id + '\'' +
               ", date=" + date +
               ", durationMinutes=" + durationMinutes +
               ", reason='" + reason + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DelayDTO delayDTO = (DelayDTO) o;
        return durationMinutes == delayDTO.durationMinutes &&
               Objects.equals(id, delayDTO.id) &&
               Objects.equals(date, delayDTO.date) &&
               Objects.equals(reason, delayDTO.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, durationMinutes, reason);
    }
}