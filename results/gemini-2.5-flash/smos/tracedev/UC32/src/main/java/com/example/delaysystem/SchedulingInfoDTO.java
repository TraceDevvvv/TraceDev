package com.example.delaysystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Data Transfer Object for Scheduling Information.
 * Used for transferring scheduling data, including associated delays, between layers.
 */
public class SchedulingInfoDTO {
    public Date date;
    public List<DelayDTO> delays;

    /**
     * Default constructor.
     */
    public SchedulingInfoDTO() {
        this.delays = new ArrayList<>();
    }

    /**
     * Constructor with date and delays.
     */
    public SchedulingInfoDTO(Date date, List<DelayDTO> delays) {
        this.date = date;
        this.delays = delays != null ? new ArrayList<>(delays) : new ArrayList<>();
    }

    /**
     * Converts this DTO to a SchedulingInformation domain object.
     *
     * @return A new SchedulingInformation object populated with data from this DTO.
     */
    public SchedulingInformation toSchedulingInformation() {
        SchedulingInformation info = new SchedulingInformation(this.date);
        if (this.delays != null) {
            this.delays.stream()
                    .map(DelayDTO::toDelay)
                    .forEach(info::addDelay);
        }
        return info;
    }

    /**
     * Populates this DTO with data from a SchedulingInformation domain object.
     *
     * @param info The SchedulingInformation object to convert from.
     */
    public void fromSchedulingInformation(SchedulingInformation info) {
        if (info != null) {
            this.date = info.getDate();
            this.delays = info.getDelays().stream()
                    .map(delay -> {
                        DelayDTO delayDto = new DelayDTO();
                        delayDto.fromDelay(delay);
                        return delayDto;
                    })
                    .collect(Collectors.toList());
        }
    }

    @Override
    public String toString() {
        return "SchedulingInfoDTO{" +
               "date=" + date +
               ", delays=" + delays +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingInfoDTO that = (SchedulingInfoDTO) o;
        return Objects.equals(date, that.date) &&
               Objects.equals(delays, that.delays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, delays);
    }
}